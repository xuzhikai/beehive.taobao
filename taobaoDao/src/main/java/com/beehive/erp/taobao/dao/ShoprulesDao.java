package com.beehive.erp.taobao.dao;

import com.beehive.erp.model.Shoprules;

/**
 * Created by 863 on 2015/12/24.
 */
public interface ShoprulesDao {

    int selectByShopIdCount(Integer shopId);

    void insertSelective(Shoprules shoprules);

    void deleteByShopId(Integer shopId);

}
