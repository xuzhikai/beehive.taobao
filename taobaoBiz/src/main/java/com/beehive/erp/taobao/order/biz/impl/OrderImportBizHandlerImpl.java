package com.beehive.erp.taobao.order.biz.impl;

import com.beehive.erp.model.Item;
import com.beehive.erp.model.Shopinfo;
import com.beehive.erp.model.Shoprules;
import com.beehive.erp.model.Source;
import com.beehive.erp.taobao.order.biz.OrderImportBizHandler;
import com.beehive.erp.taobao.order.biz.constant.Constant;
import com.beehive.erp.taobao.order.biz.convert.ItemConvert;
import com.beehive.erp.taobao.order.biz.convert.ShopConvert;
import com.beehive.erp.taobao.order.biz.convert.ShoprulesConvert;
import com.beehive.erp.taobao.service.ItemService;
import com.beehive.erp.taobao.service.ShopinfoService;
import com.beehive.erp.taobao.service.ShoprulesService;
import com.beehive.erp.taobao.service.SourceService;
import com.beehive.erp.taobao.service.impl.ShopinfoServiceImpl;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.WaimaiItemlistGetRequest;
import com.taobao.api.request.WaimaiShopBusinessrulesGetRequest;
import com.taobao.api.request.WaimaiShopListRequest;
import com.taobao.api.response.WaimaiItemlistGetResponse;
import com.taobao.api.response.WaimaiShopBusinessrulesGetResponse;
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

    //private  static final String SESSIONKEY = "6101409db4699aa1b190a67de14c11f9fd2551c94424ffc258346705";

    private AtomicBoolean isRun=new AtomicBoolean(false);

    @Autowired
    private ShopinfoService shopinfoService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private SourceService sourceService;

    @Autowired
    private ShoprulesService shoprulesService;

    @Override
    public boolean orderImport(Date startDate) {
        //如果有在处理，那么直接忽略此消息
        if(isRun.compareAndSet(false,true))
        {
            logger.info("begin order import");
            try
            {
                //TODO:监听器调用，处理开始.
                //查询所有的SESSIONKEY
                List<Source> sourceList = sourceService.findAll();
                if (null!=sourceList&&sourceList.size()>0){
                    for (Source source:sourceList){
                        //淘点点店铺导入
                        shopinfoImport(source);

                        //店铺营业规则导入
                        shoprulesImport();

                        //商品信息导入
                        itemImport();
                    }
                }else{
                    logger.info("没有对应的SESSIONKEY!");
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
    public List<Shopinfo> getShopList(String sessionKey)throws Exception{
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, APPSECRET);

        WaimaiShopListRequest req = new WaimaiShopListRequest();

        req.setPage(Constant.PAGE_NUM_SHOP);

//        req.setStatus(1l);

        req.setPageSize(Constant.PAGE_SIZE_SHOP);

        WaimaiShopListResponse res = client.execute(req,sessionKey);

        ShopConvert shopConvert = new ShopConvert();

        return shopConvert.analyticJson(res.getBody());

    }

    /**
     * 导入店铺信息
     * @throws Exception
     */
    public void shopinfoImport(Source source)throws Exception{
        //获取淘点点商店列表
        List<Shopinfo> shopinfos = getShopList(source.getSessionKey());

        if (null!=shopinfos && shopinfos.size()>0){
            //循环所有商铺信息
            for (Shopinfo shopinfo:shopinfos){
                //商铺ID
                int shopid = shopinfo.getShopid();

                shopinfo.setSourceId(source.getSourceId());
                //查询是否存在该商铺
                int count = shopinfoService.selectByShopid(shopid);

                if(count>0){
                    //如果存在先删除
                    shopinfoService.deleteByShopid(shopid);
                    //插入
                    shopinfoService.insert(shopinfo);
                } else{
                    //否则插入
                    shopinfoService.insert(shopinfo);
                }
            }
        }else{
            logger.info("淘点点应用没有商铺信息！");
        }
    }


    /**
     * 获取外卖商品列表，并将json解析为List
     * @return
     * @throws Exception
     */
    public void itemImport()throws Exception{

        //获取所有的店铺
        List<Shopinfo> shopinfos= shopinfoService.findAll();
        //循环所有店铺
        if (null!=shopinfos && shopinfos.size()>0){
            for (Shopinfo shopinfo:shopinfos){
                //查看该商铺下是否有产品
                int count =itemService.selectByShopIdCount(shopinfo.getShopid());
                //如果有则全部删除
                if (count>0){
                    itemService.deleteByShopId(shopinfo.getShopid());
                }
                //获取同步的产品列表
                List<Item> items = getItemList(shopinfo.getShopid(),shopinfo.getSourceId());

                if (null!=items && items.size()>0){
                    for (Item item:items){
                        item.setShopid(shopinfo.getShopid());
                        item.setSourceId(shopinfo.getSourceId());
                        //插入到db中
                        itemService.insertSelective(item);
                    }
                }else{
                    logger.info("该店铺没有商品！");
                }
            }
        } else{
            logger.info("淘点点应用没有商铺信息！");
        }
    }

    /**
     * 根据商铺ID获取产品json，并解析为list
     * @param shopid
     * @return
     */
    public List<Item> getItemList(Integer shopid,String sourceId)throws Exception{
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, APPSECRET);

        WaimaiItemlistGetRequest req = new WaimaiItemlistGetRequest();
        //商铺id
        req.setShopid(Long.valueOf(shopid));
        //产品状态：全部0
        req.setSalesStatus(Constant.SALES_STATUS);

        req.setPageNo(Constant.PAGE_NUM_ITEM);

        req.setPageSize(Constant.PAGE_SIZE_ITEM);
        //要获取产品的字段
        req.setFields(Constant.ITEM_FIELDS);

        Source source = sourceService.selectBySourceId(sourceId);

        WaimaiItemlistGetResponse rsp = client.execute(req, source.getSessionKey());

        ItemConvert itemConvert = new ItemConvert();

        List<Item> items= itemConvert.analyticJson(rsp.getBody());

        return items;
    }

    /**
     * 店铺营业规则导入
     * @param
     * @throws Exception
     */
    public void shoprulesImport()throws Exception{
        //获取所有的店铺
        List<Shopinfo> shopinfos= shopinfoService.findAll();

        if (null!=shopinfos && shopinfos.size()>0){
            for (Shopinfo shopinfo:shopinfos){

                Shoprules shoprules=  getShoprules(shopinfo.getShopid(),shopinfo.getSourceId());

                shoprules.setSourceId(shopinfo.getSourceId());

                int count =shoprulesService.selectByShopIdCount(shopinfo.getShopid());

                if (count>0){
                    shoprulesService.deleteByShopId(shopinfo.getShopid());
                }
                shoprulesService.insertSelective(shoprules);
            }
        }else{
            logger.info("淘点点应用没有商铺信息！");
        }
    }

    /**
     * 根据店铺id和sessionkey获取店铺营业规则JSON并解析为实例对象
     * @param shopid
     * @param sourceId
     * @return
     * @throws Exception
     */
    public Shoprules getShoprules(Integer shopid,String sourceId)throws Exception{
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, APPSECRET);

        WaimaiShopBusinessrulesGetRequest req = new WaimaiShopBusinessrulesGetRequest();
        //商铺id
        req.setShopid(Long.valueOf(shopid));

        Source source = sourceService.selectBySourceId(sourceId);

        WaimaiShopBusinessrulesGetResponse rsp = client.execute(req, source.getSessionKey());

        ShoprulesConvert shoprulesConvert = new ShoprulesConvert();

        return shoprulesConvert.analyticJson(rsp.getBody());
    }
}
