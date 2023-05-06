package com.meme.retry.dao;

import com.meme.retry.model.RetryInfo;
import lombok.SneakyThrows;

import java.util.List;

public interface RetryDao {

    @SneakyThrows
    void save(RetryInfo retryInfo);

    @SneakyThrows
    List<RetryInfo> peek();

    void delete(RetryInfo retryInfo);

    void moveToDLQ(RetryInfo retryInfo);
}
