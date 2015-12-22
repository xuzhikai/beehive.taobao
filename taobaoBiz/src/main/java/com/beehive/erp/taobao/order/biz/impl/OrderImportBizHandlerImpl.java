package com.beehive.erp.taobao.order.biz.impl;

import com.beehive.erp.model.Shopinfo;
import com.beehive.erp.taobao.order.biz.OrderImportBizHandler;
import com.beehive.erp.taobao.order.biz.convert.ShopConvert;
import com.beehive.erp.taobao.service.ShopinfoService;
import com.beehive.erp.taobao.service.impl.ShopinfoServiceImpl;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.WaimaiShopListRequest;
import com.taobao.api.response.WaimaiShopListResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
    /**请求地址*/
    private  static  final String URL = "http://gw.api.taobao.com/router/rest";

    private static  final String APPKEY = "23255719";

    private static  final String APPSECRET = "f04c82c731897104369949251386490b";

    private  static final String SESSIONKEY = "6101409db4699aa1b190a67de14c11f9fd2551c94424ffc258346705";

    private AtomicBoolean isRun=new AtomicBoolean(false);

    @Autowired
    private ShopinfoService shopinfoService = new ShopinfoServiceImpl();

    @Override
    public boolean orderImport(Date startDate) {
        //如果有在处理，那么直接忽略此消息
        if(isRun.compareAndSet(false,true))
        {
            logger.info("begin order import");
            try
            {
                //TODO:监听器调用，处理开始
                //获取套淘点点商店列表
                List<Shopinfo> shopinfos = getShopList();

                if (null!=shopinfos && shopinfos.size()>0){
                    //循环所有商铺信息
                    for (Shopinfo shopinfo:shopinfos){
                        //商铺ID
                        int shopid = shopinfo.getShopid();
                        //查询是否存在该商铺
                        int count = shopinfoService.selectByShopid(shopid);

                        if(count>0){
                            //如果存在先删除
                            shopinfoService.deleteByShopid(shopid);
                        } else{
                            //否则插入
                            shopinfoService.insert(shopinfo);
                        }
                    }
                }else{
                    logger.info("淘点点应用汇总没有商铺信息！");
                }
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

    /**
     * 获取商户列表，并将json解析为List
     * @return
     * @throws Exception
     */
    private List<Shopinfo> getShopList()throws Exception{
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, APPSECRET);

        WaimaiShopListRequest req = new WaimaiShopListRequest();

        req.setPage(1L);

        req.setPageSize(20l);

        WaimaiShopListResponse res = client.execute(req,SESSIONKEY);

        ShopConvert shopConvert = new ShopConvert();

        return shopConvert.analyticJson(res.getBody());

    }
}
