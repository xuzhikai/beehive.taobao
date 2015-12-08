package com.beehive.erp.schedule.runtime.jms.send;

import java.io.Serializable;
import java.util.Map;


public interface JmsSender {
    /**
     * 消息转换发送
     * 
     * @param destinationName
     * @param message
     */
    public void sendConvertMessage(String destinationName, Serializable message);

    /**
     * 消息附加属性发送
     * 
     * @param destinationName
     * @param message
     * @param msgProperties
     */
    public void sendConvertMessage(String destinationName, Serializable message,
                                   Map<String, Object> msgProperties);
}
