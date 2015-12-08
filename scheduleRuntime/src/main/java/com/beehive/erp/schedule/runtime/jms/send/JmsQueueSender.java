package com.beehive.erp.schedule.runtime.jms.send;

import java.io.Serializable;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;



public class JmsQueueSender implements JmsSender {
    private JmsTemplate jmsTemplateQueue;

    private Logger logger = LoggerFactory.getLogger("LOG_TYPE.PAFF_COMMON.val");

    public void sendConvertMessage(String destinationName, Serializable message)
            throws RuntimeException {
        try {
            this.jmsTemplateQueue.convertAndSend(destinationName, message);
        } catch (JmsException jmsException) {
            logger.error(jmsException.getMessage(), jmsException);
            throw new RuntimeException(jmsException);
        }
    }


    public void setJmsTemplateQueue(JmsTemplate jmsTemplateQueue) {
        this.jmsTemplateQueue = jmsTemplateQueue;
    }


    @Override
    public void sendConvertMessage(String destinationName, Serializable message,
                                   final Map<String, Object> msgProperties) {
        try {
            this.jmsTemplateQueue.convertAndSend(destinationName, message,
                    new MessagePostProcessor() {
                        @Override
                        public Message postProcessMessage(Message msg) throws JMSException {
                            if (msgProperties != null) {
                                for (Map.Entry<String, Object> entry : msgProperties.entrySet()) {
                                    msg.setObjectProperty(entry.getKey(), entry.getValue());
                                }
                            }
                            return msg;

                        }
                    });
        } catch (JmsException jmsException) {
            logger.error(jmsException.getMessage(), jmsException);
            throw new RuntimeException(jmsException);
        }

    }

}
