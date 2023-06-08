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
    private List<Long> stepList = Arrays.asList(5L, 10L, 20L, 30L, 60L, 90L, 120L, 300L, 600L, 1800L);

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
    public long getTimeoutSpanMillis() {
        return this.timeoutSpanUnit.toMillis(this.timeoutSpan);
    }

    @JsonIgnore
    public long getTimeoutTimeMillis() {
        return this.startTimeMillis + getTimeoutSpanMillis();
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
    private boolean isTimeout() {
        return System.currentTimeMillis() > getTimeoutTimeMillis();
    }

    @JsonIgnore
    public boolean isSuccess() {
        return RetryStatus.SUCCESS.equals(this.retryStatus);
    }

    public boolean needRetryNow() {
        return !isTimeout() && getNextRetryTimeMillis() < System.currentTimeMillis() && RetryStatus.FAIL.equals(retryStatus)
                ;
    }

    public boolean needFinalizeNow() {

        return isTimeout()
                && System.currentTimeMillis() < (getTimeoutTimeMillis() + getTimeoutSpanMillis())
                && RetryStatus.FAIL.equals(retryStatus);
    }

    @JsonIgnore
    public boolean isDead() {

        return System.currentTimeMillis() > (getTimeoutTimeMillis() + getTimeoutSpanMillis())
                && RetryStatus.FAIL.equals(retryStatus);
    }

    @JsonIgnore
    public boolean isFinalized() {
        return RetryStatus.FINALIZED.equals(this.retryStatus);
    }

    public void fail() {
        this.retryCount += 1;
        this.retryStatus = RetryStatus.FAIL;
    }

    public void success() {
        this.retryCount += 1;
        this.retryStatus = RetryStatus.SUCCESS;
    }

    public void finalized() {
        this.retryStatus = RetryStatus.FINALIZED;
    }

    @Getter
    @RequiredArgsConstructor
    public enum RetryStatus {

        /**
         * 失败
         */
        FAIL("0"),

        /**
         * 成功
         */
        SUCCESS("1"),

        /**
         * 结束
         */
        FINALIZED("3"),
        ;

        private final String code;

    }

}
