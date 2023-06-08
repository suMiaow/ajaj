package com.meme.retry.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.meme.retry.dao.RetryDao;
import com.meme.retry.model.RetryInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

@Service
@Slf4j
public class RetryService {

    private final RetryDao retryDao;
    Executor executor = new ThreadPoolExecutor(10,
            100,
            5,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(200),
            new ThreadFactoryBuilder().setNameFormat("retry-pool-%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public RetryService(RetryDao retryDao) {
        this.retryDao = retryDao;
    }

    public void retry() { // NOSONAR

        List<RetryInfo> retryInfoList = retryDao.peekNeedRetryNow();
        if (CollectionUtils.isNotEmpty(retryInfoList)) {
            retryInfoList.forEach(retryInfo -> executor.execute(() -> {

                if (retryInfo.needRetryNow()) {
                    boolean retrySuccess = false;
                    try {
                        // TODO retry;
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
                    if (retrySuccess) {
                        retryInfo.success();
                        retryDao.delete(retryInfo);
                    } else {
                        retryInfo.fail();
                        retryDao.save(retryInfo);
                    }

                } else if (retryInfo.needFinalizeNow()) {
                    try {
                        // TODO timeout
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
                    retryInfo.finalized();
                    retryDao.delete(retryInfo);
                } else if (retryInfo.isSuccess() || retryInfo.isFinalized()) {
                    retryDao.delete(retryInfo);
                } else if (retryInfo.isDead()) {
                    retryDao.moveToDLQ(retryInfo);
                } else {
                    log.error("invalid retry info: {}", retryInfo);
                }
            }));
        }
    }

    public void saveRetry(RetryInfo retryInfo) {
        retryDao.save(retryInfo);
    }
}
