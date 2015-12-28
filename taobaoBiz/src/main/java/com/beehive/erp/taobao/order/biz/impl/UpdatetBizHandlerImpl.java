package com.beehive.erp.taobao.order.biz.impl;

import com.beehive.erp.model.*;
import com.beehive.erp.taobao.order.biz.OrderImportBizHandler;
import com.beehive.erp.taobao.order.biz.UpdateBizHandler;
import com.beehive.erp.taobao.order.biz.constant.Constant;
import com.beehive.erp.taobao.order.biz.convert.ItemConvert;
import com.beehive.erp.taobao.order.biz.convert.ShopConvert;
import com.beehive.erp.taobao.order.biz.convert.ShoprulesConvert;
import com.beehive.erp.taobao.service.*;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.WaimaiItemlistGetRequest;
import com.taobao.api.request.WaimaiShopBusinessrulesGetRequest;
import com.taobao.api.request.WaimaiShopBusinessrulesUpdateRequest;
import com.taobao.api.request.WaimaiShopListRequest;
import com.taobao.api.response.WaimaiItemlistGetResponse;
import com.taobao.api.response.WaimaiShopBusinessrulesGetResponse;
import com.taobao.api.response.WaimaiShopBusinessrulesUpdateResponse;
import com.taobao.api.response.WaimaiShopListResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UpdatetBizHandlerImpl implements UpdateBizHandler {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UpdatetBizHandlerImpl.class);
    /**请求地址*/
    private  static  final String URL = "http://gw.api.taobao.com/router/rest";

    private static  final String APPKEY = "23255719";

    private static  final String APPSECRET = "f04c82c731897104369949251386490b";

    //private  static final String SESSIONKEY = "6101409db4699aa1b190a67de14c11f9fd2551c94424ffc258346705";

    private AtomicBoolean isRun=new AtomicBoolean(false);

    @Autowired
    private SourceService sourceService;

    @Autowired
    private VUpShoprulesService vUpShoprulesService;

    @Autowired
    private UpLogService upLogService;

    private Map<String,String> sourceMap = null;

    @Override
    public boolean update(Date startDate) {
        UpLog upLog = new UpLog();
        upLog.setUpType("shoprules");
        upLog.setUpCode("1");
        //如果有在处理，那么直接忽略此消息
        if(isRun.compareAndSet(false,true))
        {
            logger.info("begin shoprules update");
            try
            {
                //TODO:监听器调用，处理开始.

                //查询所有的SESSIONKEY
                List<Source> sourceList = sourceService.findAll();
                sourceMap = new HashMap<String, String>();
                //编辑sourceMap<SourceId,SessionKey>
                if (null!=sourceList&&sourceList.size()>0){
                    for (Source source:sourceList){
                        sourceMap.put(source.getSourceId(),source.getSessionKey());
                    }
                }else{
                    logger.info("没有SESSIONKEY!");
                    upLog.setUpDesc("没有SESSIONKEY!");
                    upLog.setCreateDate(new Timestamp(System.currentTimeMillis()));
                    upLogService.insertSelective(upLog);
                    return false;
                }
                //查询所有要更新的外卖店营业规则---查询视图
                List<VUpShoprules> vUpShoprulesList=vUpShoprulesService.findAll();
                //循环更新
                if (null!=vUpShoprulesList && vUpShoprulesList.size()>0){
                    for (VUpShoprules vUpShoprules:vUpShoprulesList){
                        try {
                            String successFlag = updateShoprules(vUpShoprules);
                            //更新成功
                            if ("0".equals(successFlag)){
                                upLog.setUpId(vUpShoprules.getShopid().toString());
                                upLog.setUpDesc(Constant.UP_SHOPERULES_SECESS);
                                upLog.setUpCode("0");
                                upLog.setCreateDate(new Timestamp(System.currentTimeMillis()));
                                upLogService.insertSelective(upLog);
                            }else{
                                //更新失败继续下一条
                                upLog.setUpId(vUpShoprules.getShopid().toString());
                                upLog.setUpDesc(Constant.UP_SHOPERULES_FAILED);
                                upLog.setCreateDate(new Timestamp(System.currentTimeMillis()));
                                upLogService.insertSelective(upLog);
                                continue;
                            }
                        }catch (Exception e){
                            upLog.setUpId(vUpShoprules.getShopid().toString());
                            upLog.setUpDesc(Constant.UP_SHOPERULES_FAILED);
                            upLog.setCreateDate(new Timestamp(System.currentTimeMillis()));
                            upLogService.insertSelective(upLog);
                            continue;
                        }
                    }
                }else{
                    logger.info("没有要更新的店铺规则");
                    upLog.setUpDesc("没有要更新的店铺规则");
                    upLog.setCreateDate(new Timestamp(System.currentTimeMillis()));
                    upLog.setUpCode("0");
                    upLogService.insertSelective(upLog);
                    return false;
                }
                System.out.println("===================更新外卖店营业规则执行完成！========================");
            }
            catch (Exception exp)
            {
                logger.error("shoprules update error:", exp);
                throw new RuntimeException(exp.getMessage());
            }
            finally {
                isRun.set(false);
                logger.info("end shoprules update");
            }
            return true;
        }
        else
        {
            logger.error("shoprules update is running!!!");
            return false;
        }
    }

    /**
     * 更新商店
     * @param vUpShoprules
     * @return
     * @throws Exception
     */
    public String updateShoprules(VUpShoprules vUpShoprules)throws Exception{
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, APPSECRET);
        String sessionKey = sourceMap.get(vUpShoprules.getSourceId());
        WaimaiShopBusinessrulesUpdateRequest req = new WaimaiShopBusinessrulesUpdateRequest();
        req.setMinimumAmount(vUpShoprules.getMinimumAmount().toString());
        req.setDeliveryAmount(vUpShoprules.getDeliveryAmount().toString());
        req.setFullAmount(vUpShoprules.getFullAmount().toString());
        req.setMobile(vUpShoprules.getMobile());
        req.setEarlyMinutes(Long.valueOf(vUpShoprules.getEarlyMinutes()));
        req.setSupportDays(Long.valueOf(vUpShoprules.getSupportDays()));
        req.setShopid(Long.valueOf(vUpShoprules.getShopid()));
        req.setDeliveryTime(vUpShoprules.getDeliveryTime());
        req.setAreaRange(vUpShoprules.getAreaRange());
        req.setDeliveryArea(vUpShoprules.getDeliveryArea());
        WaimaiShopBusinessrulesUpdateResponse rsp = client.execute(req, sessionKey);
        //JSON格式数据解析对象
        JSONObject jb = JSONObject.fromObject(rsp.getBody());

        JSONObject ja = jb.getJSONObject("waimai_shop_businessrules_update_response");

        return ja.getString("ret_code");
    }
}
