package com.beehive.erp.model;

import java.sql.Timestamp;
import java.util.Date;

public class UpLog {
    private Long sid;

    private String upType;

    private String upId;

    private String upCode;

    private String upDesc;

    private Timestamp createDate;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getUpType() {
        return upType;
    }

    public void setUpType(String upType) {
        this.upType = upType == null ? null : upType.trim();
    }

    public String getUpId() {
        return upId;
    }

    public void setUpId(String upId) {
        this.upId = upId == null ? null : upId.trim();
    }

    public String getUpCode() {
        return upCode;
    }

    public void setUpCode(String upCode) {
        this.upCode = upCode == null ? null : upCode.trim();
    }

    public String getUpDesc() {
        return upDesc;
    }

    public void setUpDesc(String upDesc) {
        this.upDesc = upDesc == null ? null : upDesc.trim();
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
}