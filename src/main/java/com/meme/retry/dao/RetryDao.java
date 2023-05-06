package com.meme.retry.dao;

import com.meme.retry.model.RetryInfo;

import java.util.List;

public interface RetryDao {

    void save(RetryInfo retryInfo);

    List<RetryInfo> peek();

    void delete(RetryInfo retryInfo);

    void moveToDLQ(RetryInfo retryInfo);
}
