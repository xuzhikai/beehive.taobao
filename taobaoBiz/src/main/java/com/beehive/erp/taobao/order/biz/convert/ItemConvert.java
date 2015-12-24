package com.beehive.erp.taobao.order.biz.convert;

import com.beehive.erp.model.Item;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 863 on 2015/12/23.
 */
public class ItemConvert {

    /**
     *解析json为list<Item>
     * @param jsonString
     * @return
     */
    public List<Item> analyticJson(String jsonString){
        //JSON格式数据解析对象
        JSONObject jb = JSONObject.fromObject(jsonString);

        java.util.List<Item> items = new java.util.LinkedList<Item>();

        if (jb.getJSONObject("waimai_itemlist_get_response").getJSONObject("data_list").containsKey("top_auction")){
            JSONArray ja = jb.getJSONObject("waimai_itemlist_get_response").getJSONObject("data_list")
                    .getJSONArray("top_auction");

            for(int i=0;i<ja.size();i++){
                //需返回的商品对象
                Item item = new Item();

                item.setAuctionDesc(ja.getJSONObject(i).getString("auction_desc"));

                item.setAuctionStatus(Integer.valueOf(ja.getJSONObject(i).getString("auction_status")));

                item.setCategoryId(ja.getJSONObject(i).getString("category_id"));

                item.setCreateTime(ja.getJSONObject(i).getString("create_time"));

                if(ja.getJSONObject(i).containsKey("goods_no")){
                    item.setGoodsNo(ja.getJSONObject(i).getString("goods_no"));
                }else{
                    item.setGoodsNo("");
                }

                item.setItemId(ja.getJSONObject(i).getString("item_id"));

                item.setLimitBuy(Integer.valueOf(ja.getJSONObject(i).getString("limit_buy")));

                item.setModifyTime(ja.getJSONObject(i).getString("modify_time"));

                item.setOldQuantity(Integer.valueOf(ja.getJSONObject(i).getString("old_quantity")));

                item.setOriPrice(StringToBigDecimal(ja.getJSONObject(i).getString("ori_price")));

                item.setPrice(StringToBigDecimal(ja.getJSONObject(i).getString("price")));

                item.setQuantity(Integer.valueOf(ja.getJSONObject(i).getString("quantity")));

                item.setTitle(ja.getJSONObject(i).getString("title"));

                items.add(item);
            }
        }
        return items;
    }

    /**
     * String类型转为BigDecimal
     * @param price
     * @return
     */
    public BigDecimal StringToBigDecimal(String price){

        //构造以字符串内容为值的BigDecimal类型的变量bd
        BigDecimal bd=new BigDecimal(price);
        //设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
        bd=bd.setScale(2, BigDecimal.ROUND_HALF_UP);

        return bd;
    }

}
