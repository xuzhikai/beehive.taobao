package com.beehive.erp.taobao.dao;

import com.beehive.common.dal.BaseDao;
import com.beehive.erp.model.Shopinfo;
import com.beehive.erp.model.TestData;

/**
 * Created by 863 on 2015/12/22.
 */
public interface ShopinfoDao extends BaseDao<Shopinfo> {
    int insertSelective(Shopinfo o);

    int selectByShopid(Integer shopid);

    void  deleteByShopid(Integer shopid);
}
