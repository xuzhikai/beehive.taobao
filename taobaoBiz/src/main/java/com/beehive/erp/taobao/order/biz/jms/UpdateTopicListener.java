package com.beehive.erp.taobao.order.biz.jms;

import com.beehive.erp.schedule.runtime.jms.receive.JmsListener;
import com.beehive.erp.taobao.order.biz.OrderImportBizHandler;
import com.beehive.erp.taobao.order.biz.UpdateBizHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created with (TC).
 * User: 徐志凯
 * Date: 13-11-21
 * 橡果国际-系统集成部
 * Copyright (c) 2012-2013 ACORN, Inc. All rights reserved.
 */
public class UpdateTopicListener extends JmsListener {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UpdateTopicListener.class);

    @Autowired
    private UpdateBizHandler updateBizHandler;

    @Override
    public void messageHandler(Object msg) throws Exception {
        logger.info("timing begin order choose");
        try{
            //获取时间
            updateBizHandler.update(new Date());
        }catch (Exception exp)
        {
            logger.error("order choose error:",exp);
            throw exp;
        }

        logger.info("timing end order choose");
    }
}
