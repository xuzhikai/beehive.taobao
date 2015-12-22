package com.beehive.erp.taobao.order.biz.convert;

import java.util.List;

import com.beehive.erp.model.Shopinfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ShopConvert {
    /**
     * 解析JSON格式的字符串为list
     * @param jsonString
     * @return
     */
	public List<Shopinfo> analyticJson(String jsonString){
		//JSON格式数据解析对象     
        JSONObject jb = JSONObject.fromObject(jsonString); 
		
        JSONArray ja = jb.getJSONObject("waimai_shop_list_response").getJSONObject("result")
        					.getJSONObject("takeout_summary_infos").getJSONArray("takeout_shop_summary_info");
        
        List<Shopinfo> shopinfoList = new java.util.LinkedList<Shopinfo>();

        if (ja!=null && ja.size()>0){
            for(int i=0;i<ja.size();i++){
                //需返回的订单对象
                Shopinfo shopinfo = new Shopinfo();
                shopinfo.setAddress(ja.getJSONObject(i).getString("address"));
                shopinfo.setCity(ja.getJSONObject(i).getString("city"));
                shopinfo.setDigitalWaitConfirm(Integer.valueOf(ja.getJSONObject(i).getString("digital_wait_confirm")));
                shopinfo.setIsOpen(Integer.valueOf(ja.getJSONObject(i).getString("is_open")));
                shopinfo.setName(ja.getJSONObject(i).getString("name"));
                shopinfo.setPhone(ja.getJSONObject(i).getString("phone"));
                shopinfo.setShopid(Integer.valueOf(ja.getJSONObject(i).getString("shopid")));
                shopinfo.setSubName(ja.getJSONObject(i).getString("sub_name"));
                shopinfo.setWaitConfirm(Integer.valueOf(ja.getJSONObject(i).getString("wait_confirm")));
                shopinfo.setWaitRefund(Integer.valueOf(ja.getJSONObject(i).getString("wait_refund")));
                shopinfoList.add(shopinfo);
            }
        } else{
            return null;
        }
		return shopinfoList;
	} 
}
