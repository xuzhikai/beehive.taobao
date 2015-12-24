package com.beehive.erp.taobao.order.biz.convert;

import java.math.BigDecimal;

import com.beehive.erp.model.Shoprules;
import net.sf.json.JSONObject;

public class ShoprulesConvert {

	public Shoprules analyticJson(String jsonString){
		//JSON格式数据解析对象     
        JSONObject jb = JSONObject.fromObject(jsonString); 
		
        JSONObject ja = jb.getJSONObject("waimai_shop_businessrules_get_response").getJSONObject("result");
        //需店铺营业规则				
        Shoprules shoprules = new Shoprules();

        if(ja.containsKey("area_range")){
            shoprules.setAreaRange(ja.getString("area_range"));
        }else{
            shoprules.setAreaRange("");
        }
    	
    	shoprules.setCashOnDelivery(Integer.valueOf(ja.getString("cash_on_delivery")));
    	
    	shoprules.setCateId(Integer.valueOf(ja.getString("cate_id")));
    	
    	shoprules.setCateid(Integer.valueOf(ja.getString("cateid")));
    	
    	shoprules.setDeliveryAmount(StringToBigDecimal(ja.getString("delivery_amount")));

        if(ja.containsKey("delivery_time")){
            shoprules.setDeliveryTime(ja.getString("delivery_time"));
        }else{
            shoprules.setDeliveryTime("");
        }
    	
    	shoprules.setEarlyMinutes(Integer.valueOf(ja.getString("early_minutes")));
    	
    	shoprules.setFullAmount(StringToBigDecimal(ja.getString("full_amount")));
    	
    	shoprules.setMinimumAmount(StringToBigDecimal(ja.getString("minimum_amount")));
    	
    	shoprules.setMobile(ja.getString("mobile"));
    	
    	shoprules.setPhone(ja.getString("phone"));
    	
    	shoprules.setServiceTag(Integer.valueOf(ja.getString("service_tag")));
    	
    	shoprules.setShopid(Integer.valueOf(ja.getString("shopid")));
    	
    	shoprules.setShopName(ja.getString("shop_name"));
    	
    	shoprules.setShopId(Integer.valueOf(ja.getString("shop_id")));
    	
    	shoprules.setSupportDays(Integer.valueOf(ja.getString("support_days")));
    	
    	shoprules.setSupportReservedDays(ja.getString("support_reserved_days"));
    	
    	shoprules.setSupportRestTimeOrder(ja.getString("support_rest_time_order"));
        	
		return shoprules;
	} 

	public BigDecimal StringToBigDecimal(String price){
		//构造以字符串内容为值的BigDecimal类型的变量bd   
		BigDecimal bd=new BigDecimal(price);   
		//设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)   
		bd=bd.setScale(2, BigDecimal.ROUND_HALF_UP); 
		
		return bd;
	}
}
