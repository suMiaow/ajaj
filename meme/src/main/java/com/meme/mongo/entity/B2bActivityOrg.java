package com.meme.mongo.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * B2B活动按门店选择关联表
 */
@Data
public class B2bActivityOrg {

    /**
     * ID
     */
    private String id;

    /**
     * 系统域编码
     */
    private String domainCode;

    /**
     * 系统租户编码
     */
    private String tenantCode;

    /**
     * B2B活动编码
     */
    private String b2bActivityCode;

    /**
     * 店铺编码
     */
    private String orgCode;

    /**
     * 创建人编号
     */
    private String createUserCode;

    /**
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人编号
     */
    private String updateUserCode;

    /**
     * 更新人姓名
     */
    private String updateUserName;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除（0-未删除，1-已删除）
     */
    private Boolean logicDelete = false;

}
