package com.freework.weixin_backend.domain;

import java.util.Date;

public class UserLoginRecord {
    private Long id;

    private String userId;

    private String innerAppid;

    private Integer result;

    private String remark;

    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getInnerAppid() {
        return innerAppid;
    }

    public void setInnerAppid(String innerAppid) {
        this.innerAppid = innerAppid == null ? null : innerAppid.trim();
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}