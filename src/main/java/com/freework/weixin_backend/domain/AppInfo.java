package com.freework.weixin_backend.domain;

import java.util.Date;

public class AppInfo {
    private String innerAppid;

    private String wxAppid;

    private String wxAppsecret;

    private String wxAppname;

    private String isEnabled;

    private String remark;

    private Date createDate;

    public String getInnerAppid() {
        return innerAppid;
    }

    public void setInnerAppid(String innerAppid) {
        this.innerAppid = innerAppid == null ? null : innerAppid.trim();
    }

    public String getWxAppid() {
        return wxAppid;
    }

    public void setWxAppid(String wxAppid) {
        this.wxAppid = wxAppid == null ? null : wxAppid.trim();
    }

    public String getWxAppsecret() {
        return wxAppsecret;
    }

    public void setWxAppsecret(String wxAppsecret) {
        this.wxAppsecret = wxAppsecret == null ? null : wxAppsecret.trim();
    }

    public String getWxAppname() {
        return wxAppname;
    }

    public void setWxAppname(String wxAppname) {
        this.wxAppname = wxAppname == null ? null : wxAppname.trim();
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled == null ? null : isEnabled.trim();
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