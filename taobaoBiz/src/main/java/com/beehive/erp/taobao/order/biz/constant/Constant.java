package com.beehive.erp.taobao.order.biz.constant;

/**
 * Created by 863 on 2015/12/22.
 */
public class Constant {
    /**用淘宝接口取店铺时，每页显示的条数*/
    public static long PAGE_SIZE_SHOP = 20L;

    /**用淘宝接口取店铺时，页码*/
    public static long PAGE_NUM_SHOP = 1L;

    /**用淘宝接口取每个店铺的产品时，每页显示的条数*/
    public static long PAGE_SIZE_ITEM = 20L;

    /**用淘宝接口取每个店铺的产品时，页码*/
    public static long PAGE_NUM_ITEM = 1L;

    /**根据店铺id获取产品时，产品的状态：出售中宝贝1,仓库中宝贝2,所有宝贝0*/
    public static long SALES_STATUS = 0L;

    public static String ITEM_FIELDS = "itemid,title,price,oriprice,goods_no,auction_status,quantity,auction_desc,pic_url,category_id,limit_buy,viceImage,sku,recommend,create_time,modify_time";
}
