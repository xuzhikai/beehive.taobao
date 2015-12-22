package com.beehive.erp.model;

public class Shopinfo {
    private Integer sid;

    private String subName;

    private Integer shopid;

    private String name;

    private String city;

    private String address;

    private String phone;

    private Integer waitConfirm;

    private Integer digitalWaitConfirm;

    private Integer waitRefund;

    private Integer isOpen;

    private String shopoutid;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName == null ? null : subName.trim();
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getWaitConfirm() {
        return waitConfirm;
    }

    public void setWaitConfirm(Integer waitConfirm) {
        this.waitConfirm = waitConfirm;
    }

    public Integer getDigitalWaitConfirm() {
        return digitalWaitConfirm;
    }

    public void setDigitalWaitConfirm(Integer digitalWaitConfirm) {
        this.digitalWaitConfirm = digitalWaitConfirm;
    }

    public Integer getWaitRefund() {
        return waitRefund;
    }

    public void setWaitRefund(Integer waitRefund) {
        this.waitRefund = waitRefund;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public String getShopoutid() {
        return shopoutid;
    }

    public void setShopoutid(String shopoutid) {
        this.shopoutid = shopoutid == null ? null : shopoutid.trim();
    }
}