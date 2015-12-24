package com.beehive.erp.taobao.service.impl;

import com.beehive.erp.model.Shoprules;
import com.beehive.erp.taobao.dao.ShoprulesDao;
import com.beehive.erp.taobao.service.ShoprulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 863 on 2015/12/24.
 */
@Service
public class ShoprulesServiceImpl implements ShoprulesService{

    @Autowired
    private ShoprulesDao shoprulesDao;


    @Override
    public int selectByShopIdCount(Integer shopId) {
        return shoprulesDao.selectByShopIdCount(shopId);
    }

    @Override
    public void insertSelective(Shoprules shoprules) {
        shoprulesDao.insertSelective(shoprules);
    }

    @Override
    public void deleteByShopId(Integer shopId) {
        shoprulesDao.deleteByShopId(shopId);
    }
}
