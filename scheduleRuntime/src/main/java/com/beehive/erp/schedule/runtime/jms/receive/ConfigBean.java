package com.beehive.erp.schedule.runtime.jms.receive;

public class ConfigBean {

    private String key;
    private String value;

    public ConfigBean(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ConfigBean [key=" + key + ", value=" + value + "]";
    }

}
