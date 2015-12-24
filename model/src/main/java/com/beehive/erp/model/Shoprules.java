package com.beehive.erp.model;

import javax.lang.model.element.NestingKind;
import java.math.BigDecimal;

public class Shoprules {
    private Integer sid;

    private Integer serviceTag;

    private Integer shopId;

    private Integer cateId;

    private String deliveryTime;

    private BigDecimal deliveryAmount;

    private BigDecimal minimumAmount;

    private BigDecimal fullAmount;

    private Integer earlyMinutes;

    private Integer supportDays;

    private String mobile;

    private String shopName;

    private Integer shopid;

    private Integer cateid;

    private String areaRange;

    private Integer cashOnDelivery;

    private String phone;

    private String notice;

    private String supportReservedDays;

    private String supportRestTimeOrder;

    private String sourceId;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getServiceTag() {
        return serviceTag;
    }

    public void setServiceTag(Integer serviceTag) {
        this.serviceTag = serviceTag;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime == null ? null : deliveryTime.trim();
    }

    public BigDecimal getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(BigDecimal deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public BigDecimal getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(BigDecimal minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public BigDecimal getFullAmount() {
        return fullAmount;
    }

    public void setFullAmount(BigDecimal fullAmount) {
        this.fullAmount = fullAmount;
    }

    public Integer getEarlyMinutes() {
        return earlyMinutes;
    }

    public void setEarlyMinutes(Integer earlyMinutes) {
        this.earlyMinutes = earlyMinutes;
    }

    public Integer getSupportDays() {
        return supportDays;
    }

    public void setSupportDays(Integer supportDays) {
        this.supportDays = supportDays;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public Integer getCateid() {
        return cateid;
    }

    public void setCateid(Integer cateid) {
        this.cateid = cateid;
    }

    public String getAreaRange() {
        return areaRange;
    }

    public void setAreaRange(String areaRange) {
        this.areaRange = areaRange == null ? null : areaRange.trim();
    }

    public Integer getCashOnDelivery() {
        return cashOnDelivery;
    }

    public void setCashOnDelivery(Integer cashOnDelivery) {
        this.cashOnDelivery = cashOnDelivery;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    public String getSupportReservedDays() {
        return supportReservedDays;
    }

    public void setSupportReservedDays(String supportReservedDays) {
        this.supportReservedDays = supportReservedDays == null ? null : supportReservedDays.trim();
    }

    public String getSupportRestTimeOrder() {
        return supportRestTimeOrder;
    }

    public void setSupportRestTimeOrder(String supportRestTimeOrder) {
        this.supportRestTimeOrder = supportRestTimeOrder == null ? null : supportRestTimeOrder.trim();
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
}