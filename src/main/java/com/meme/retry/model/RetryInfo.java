package com.meme.retry.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RetryInfo {

    private String appCode;

    /**
     * 重试逻辑编码
     */
    private String retryCode;

    /**
     * 唯一键
     */
    private String uniqueKey = UUID.randomUUID().toString().replace("-", "");

    /**
     * 索引键
     */
    private String key;

    /**
     * 重试数据
     */
    private String data;

    /**
     * 重试开始时间
     */
    private Long startTimeMillis = System.currentTimeMillis();

    /**
     * 重试超时时间
     */
    @NonNull
    private Long timeoutSpan = 10L;

    /**
     * 重试超时时间单位
     */
    @NonNull
    private TimeUnit timeoutSpanUnit = TimeUnit.MINUTES;

    /**
     * 重试步长间隔
     */
    @NonNull
    private List<Long> stepList = Arrays.asList(10L, 20L, 30L, 60L, 120L, 300L);

    /**
     * 重试步长间隔单位
     */
    @NonNull
    private TimeUnit stepUnit = TimeUnit.SECONDS;

    /**
     * 重试次数
     */
    private Integer retryCount = 0;

    /**
     * 重试状态
     */
    private RetryStatus retryStatus = RetryStatus.FAIL;

    @JsonIgnore
    public long getTimeoutMillis() {
        return this.timeoutSpanUnit.toMillis(this.timeoutSpan);
    }

    @JsonIgnore
    public long getRetryEndTimeMillis() {
        return this.startTimeMillis + getTimeoutMillis();
    }

    @JsonIgnore
    public long getNextRetryTimeMillis() {

        long result = startTimeMillis;

        for (int i = 0; i < retryCount + 1; i++) {
            if (i < stepList.size()) {
                result += stepUnit.toMillis(stepList.get(i));
            } else {
                result += stepUnit.toMillis(stepList.get(stepList.size() - 1));
            }
        }

        return result;
    }

    @JsonIgnore
    public boolean isTimeout() {
        return System.currentTimeMillis() > getRetryEndTimeMillis();
    }

    @JsonIgnore
    public boolean isAllFinished() {
        return RetryStatus.SUCCESS.equals(retryStatus) || RetryStatus.FINALIZED.equals(retryStatus);
    }

    public boolean needRetryNow() {
        return !isTimeout() && getNextRetryTimeMillis() < System.currentTimeMillis() && RetryStatus.FAIL.equals(retryStatus)
                ;
    }

    public boolean needFinalizeNow() {

        return System.currentTimeMillis() < (getRetryEndTimeMillis() + getTimeoutMillis())
                && isTimeout() && RetryStatus.FAIL.equals(retryStatus);
    }

    @JsonIgnore
    public boolean isDead() {

        return System.currentTimeMillis() > (getRetryEndTimeMillis() + getTimeoutMillis())
                && RetryStatus.FAIL.equals(retryStatus);
    }

    public void fail() {
        this.retryCount += 1;
        this.retryStatus = RetryStatus.FAIL;
    }

    public void success() {
        this.retryCount += 1;
        this.retryStatus = RetryStatus.SUCCESS;
    }

    @JsonIgnore
    public void isFinalized() {
        this.retryStatus = RetryStatus.FINALIZED;
    }

    public void incrRetryCount() {
        this.retryCount += 1;
    }

}
