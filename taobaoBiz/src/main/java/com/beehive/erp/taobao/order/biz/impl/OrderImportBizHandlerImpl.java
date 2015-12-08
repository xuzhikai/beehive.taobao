package com.beehive.erp.taobao.order.biz.impl;

import com.beehive.erp.taobao.order.biz.OrderImportBizHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 */
@Service
public class OrderImportBizHandlerImpl implements OrderImportBizHandler {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(OrderImportBizHandlerImpl.class);



    private AtomicBoolean isRun=new AtomicBoolean(false);

    @Override
    public boolean orderImport(Date startDate) {
        //如果有在处理，那么直接忽略此消息
        if(isRun.compareAndSet(false,true))
        {
            logger.info("begin order import");
            try
            {
                //TODO:
            }
            catch (Exception exp)
            {
                logger.error("order import error:", exp);
                throw new RuntimeException(exp.getMessage());
            }
            finally {
                isRun.set(false);
                logger.info("end order import");
            }
            return true;
        }
        else
        {
            logger.error("order choose is running!!!");
            return false;
        }
    }
}
