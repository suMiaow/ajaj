package com.meme.retry.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.meme.retry.dao.RetryDao;
import com.meme.retry.model.RetryInfo;
import com.meme.retry.model.RetryStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class RetryService {

    private final RetryDao retryDao;
    Executor executor = Executors.newFixedThreadPool(10, new ThreadFactoryBuilder().setNameFormat("retry-pool-%d").build());

    public RetryService(RetryDao retryDao) {
        this.retryDao = retryDao;
    }

    public void retry() {

        List<RetryInfo> retryInfoList = retryDao.peek();
        if (CollectionUtils.isNotEmpty(retryInfoList)) {
            retryInfoList.forEach(retryInfo -> {
                executor.execute(() -> {
                    if (retryInfo.needRetryNow()) {
                        boolean retrySuccess = false;
                        log.info("retry start");
                        // TODO retry
                        log.info("retry result: {}", retrySuccess);
                        retryInfo.incrRetryCount();
                        if (retrySuccess) {
                            retryInfo.setRetryStatus(RetryStatus.SUCCESS);
                            retryDao.delete(retryInfo);
                        } else {
                            retryInfo.setRetryStatus(RetryStatus.FAIL);
                            retryDao.save(retryInfo);
                        }
                    } else if (retryInfo.needFinalizeNow()) {
                        log.info("finalize");
                        // TODO timeout
                        retryDao.delete(retryInfo);
                    } else if (retryInfo.isDead()) {
                        retryDao.moveToDLQ(retryInfo);
                    } else {
                        log.error("invalid retry info: {}", retryInfo);
                    }
                });
            });
        }
    }

    public void save(RetryInfo retryInfo) {
        retryDao.save(retryInfo);
    }
}
