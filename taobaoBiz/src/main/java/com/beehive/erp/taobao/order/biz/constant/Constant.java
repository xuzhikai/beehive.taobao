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

    /**获取商品信息的字段*/
    public static String ITEM_FIELDS = "itemid,title,price,oriprice,goods_no,auction_status,quantity,auction_desc,pic_url,category_id,limit_buy,viceImage,sku,recommend,create_time,modify_time";

    /**更新外卖店营业规则成功日志*/
    public static String UP_SHOPERULES_SECESS = "外卖店营业规则更新成功！";

    /**更新外卖店营业规则失败日志*/
    public static String UP_SHOPERULES_FAILED = "外卖店营业规则更新失败！";

    /**更新商品成功日志*/
    public static String UP_ITEM_SECESS = "商品信息更新成功！";

    /**更新商品失败日志*/
    public static String UP_ITEM_FAILED = "商品信息更新失败！";


}
