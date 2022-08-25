//package com.meme.mongo.entity;
//import lombok.Data;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.time.LocalDateTime;
//import java.util.Date;
//
//@Document
//@Data
//public class AdapterLogEntity {
//
//    /**
//     * 租户号
//     */
//    private String tenantCode;
//
//    /**
//     * 业务域
//     */
//    private String domainCode;
//
//    /**
//     * 渠道CODE
//     */
//    private String channelCode;
//
//    /**
//     * 组织CODE
//     */
//    private String orgCode;
//
//    /**
//     * 日志编码
//     */
//    private String logCode;
//
//    /**
//     * 本次业务编码
//     */
//    private String mainCode;
//
//    /**
//     * 业务类型或消息队列名称
//     */
//    private String bizType;
//
//    /**
//     * 业务子类型
//     */
//    private String subBizType;
//
//    /**
//     * 单据编号
//     */
//    private String targetCode;
//
//    /**
//     * 发送时间记录，重推会覆盖
//     */
//    private Date sendTime;
//
//    /**
//     * 1:POST,2:GET,3:WEB Service,4:File,5:Adapter(MAIL)
//     */
//    private String sendType;
//
//    /**
//     * 失败原因编码
//     */
//    private String failType;
//
//    /**
//     * 失败原因描述
//     */
//    private String failReason;
//
//    /**
//     * 消息内容
//     */
//    private String content;
//
//    /**
//     * 是否有效
//     */
//    private String isValid;
//
//    /**
//     * 录入时间 此条记录插入数据库的时间，也可视为下单时间
//     */
//    private LocalDateTime createTime;
//
//}