package com.beehive.erp.taobao.order.biz.jms;

import com.beehive.erp.schedule.runtime.jms.receive.JmsListener;
import com.beehive.erp.taobao.order.biz.UpdateBizHandler;
import com.beehive.erp.taobao.order.biz.UpdateItemBizHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 *
 */
public class UpdateItemTopicListener extends JmsListener {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UpdateItemTopicListener.class);

    @Autowired
    private UpdateItemBizHandler updateItemBizHandler;

    @Override
    public void messageHandler(Object msg) throws Exception {
        logger.info("timing begin update item");
        try{
            //获取时间
            updateItemBizHandler.update(new Date());
        }catch (Exception exp)
        {
            logger.error("update item error:",exp);
            throw exp;
        }

        logger.info("timing end update item");
    }
}
