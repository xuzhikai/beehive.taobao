package com.beehive.erp.taobao.service;

import com.beehive.erp.model.Shoprules;

/**
 * Created by 863 on 2015/12/24.
 */
public interface ShoprulesService {
    int selectByShopIdCount(Integer shopId);

    void insertSelective(Shoprules shoprules);

    void deleteByShopId(Integer shopId);
}
