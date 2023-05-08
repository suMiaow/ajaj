package com.meme.retry.dao;

import com.meme.retry.model.RetryInfo;

import java.util.List;

public interface RetryDao {

    /**
     * 保存重试信息
     *
     * @param retryInfo 重试信息
     */
    void save(RetryInfo retryInfo);

    /**
     * 获取待重试信息
     *
     * @return 待重试信息
     */
    List<RetryInfo> peekNeedRetryNow();

    /**
     * 删除重试信息
     *
     * @param retryInfo 重试信息
     */
    void delete(RetryInfo retryInfo);

    /**
     * 将重试信息移入死信队列
     *
     * @param retryInfo 重试信息
     */
    void moveToDLQ(RetryInfo retryInfo);
}
