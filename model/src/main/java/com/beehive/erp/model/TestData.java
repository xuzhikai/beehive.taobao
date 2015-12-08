package com.beehive.erp.model;

import java.io.Serializable;

/**
 * 测试实体
 */
public class TestData implements Serializable {
    private Integer nub;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNub() {
        return nub;
    }

    public void setNub(Integer nub) {
        this.nub = nub;
    }
}
