package com.beehive.erp.taobao.order.biz.impl;

import com.beehive.erp.model.Source;
import com.beehive.erp.model.UpLog;
import com.beehive.erp.model.VUpIteminfo;
import com.beehive.erp.model.VUpShoprules;
import com.beehive.erp.taobao.order.biz.UpdateBizHandler;
import com.beehive.erp.taobao.order.biz.UpdateItemBizHandler;
import com.beehive.erp.taobao.order.biz.constant.Constant;
import com.beehive.erp.taobao.service.SourceService;
import com.beehive.erp.taobao.service.UpLogService;
import com.beehive.erp.taobao.service.VUpIteminfoService;
import com.beehive.erp.taobao.service.VUpShoprulesService;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.WaimaiItemUpdateRequest;
import com.taobao.api.request.WaimaiShopBusinessrulesUpdateRequest;
import com.taobao.api.response.WaimaiItemUpdateResponse;
import com.taobao.api.response.WaimaiShopBusinessrulesUpdateResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UpdatetItemBizHandlerImpl implements UpdateItemBizHandler {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UpdatetItemBizHandlerImpl.class);
    /**请求地址*/
    private  static  final String URL = "http://gw.api.taobao.com/router/rest";

    private static  final String APPKEY = "23255719";

    private static  final String APPSECRET = "f04c82c731897104369949251386490b";

    //private  static final String SESSIONKEY = "6101409db4699aa1b190a67de14c11f9fd2551c94424ffc258346705";

    private AtomicBoolean isRun=new AtomicBoolean(false);

    @Autowired
    private SourceService sourceService;

    @Autowired
    private VUpIteminfoService vUpIteminfoService;

    @Autowired
    private UpLogService upLogService;

    private Map<String,String> sourceMap = null;

    @Override
    public boolean update(Date startDate) {
        UpLog upLog = new UpLog();
        upLog.setUpType("item");
        upLog.setUpCode("1");
        //如果有在处理，那么直接忽略此消息
        if(isRun.compareAndSet(false,true))
        {
            logger.info("begin item update");
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
                //查询所有要更新的商品---查询视图
                List<VUpIteminfo> vUpIteminfoList=vUpIteminfoService.findAll();

                //循环更新
                if (null!=vUpIteminfoList && vUpIteminfoList.size()>0){
                    for (VUpIteminfo vUpIteminfo:vUpIteminfoList){
                        try {
                              Map<String,String> result= updateIteminfo(vUpIteminfo);
                            //更新成功
                            if (vUpIteminfo.getItemId().toString().equals(result.get("result_data"))) {
                                upLog.setUpId(vUpIteminfo.getItemId().toString());
                                upLog.setUpDesc(result.get("useful_msg"));
                                upLog.setUpCode("0");
                                upLog.setCreateDate(new Timestamp(System.currentTimeMillis()));
                                upLogService.insertSelective(upLog);
                            }else{
                                //更新失败继续下一条
                                upLog.setUpId(vUpIteminfo.getItemId().toString());
                                upLog.setUpDesc(Constant.UP_ITEM_FAILED);
                                upLog.setCreateDate(new Timestamp(System.currentTimeMillis()));
                                upLogService.insertSelective(upLog);
                                continue;
                            }
                        }catch (Exception e){
                            upLog.setUpId(vUpIteminfo.getItemId().toString());
                            upLog.setUpDesc(Constant.UP_ITEM_FAILED);
                            upLog.setCreateDate(new Timestamp(System.currentTimeMillis()));
                            upLogService.insertSelective(upLog);
                            continue;
                        }
                    }
                }else{
                    logger.info("没有要更新的商品");
                    upLog.setUpDesc("没有要更新的商品");
                    upLog.setCreateDate(new Timestamp(System.currentTimeMillis()));
                    upLog.setUpCode("0");
                    upLogService.insertSelective(upLog);
                    return false;
                }
                System.out.println("===================更新商品执行完成！========================");
            }
            catch (Exception exp)
            {
                logger.error("item update error:", exp);
                throw new RuntimeException(exp.getMessage());
            }
            finally {
                isRun.set(false);
                logger.info("end item update");
            }
            return true;
        }
        else
        {
            logger.error("item update is running!!!");
            return false;
        }
    }

    /**
     * 更新产品
     * @param vUpIteminfo
     * @return Map
     * @throws Exception
     */
    public Map<String,String> updateIteminfo(VUpIteminfo vUpIteminfo)throws Exception{
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, APPSECRET);
        String sessionKey = sourceMap.get(vUpIteminfo.getSourceId());
        WaimaiItemUpdateRequest req = new WaimaiItemUpdateRequest();

        req.setTitle(vUpIteminfo.getTitle());

        req.setPrice(vUpIteminfo.getPrice().toString());

        req.setOriprice(vUpIteminfo.getOriPrice().toString());
        req.setQuantity(Long.valueOf(vUpIteminfo.getQuantity()));
        req.setPicurl(vUpIteminfo.getPicurl());
        req.setGoodsno(vUpIteminfo.getGoodsno());
        req.setAuctionstatus(Long.valueOf(vUpIteminfo.getAuctionstatus()));
        req.setCategoryid(Long.valueOf(vUpIteminfo.getCategoryid()));
        req.setAuctiondesc(vUpIteminfo.getAuctiondesc());
        req.setInShopId(Long.valueOf(vUpIteminfo.getShopId()));
        req.setItemId(Long.valueOf(vUpIteminfo.getItemId()));
        WaimaiItemUpdateResponse rsp = client.execute(req, sessionKey);

        //JSON格式数据解析对象
        JSONObject jb = JSONObject.fromObject(rsp.getBody());

        JSONObject ja = jb.getJSONObject("waimai_item_update_response").getJSONObject("result");

        Map<String,String> resultMap = new HashMap<String, String>();
        resultMap.put("result_data",ja.getString("result_data"));
        resultMap.put("useful_msg",ja.getString("useful_msg"));

        return resultMap;
    }
}
