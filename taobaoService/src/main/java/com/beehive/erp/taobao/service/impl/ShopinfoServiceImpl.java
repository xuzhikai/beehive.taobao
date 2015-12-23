package com.beehive.erp.taobao.service.impl;

import com.beehive.erp.model.Shopinfo;
import com.beehive.erp.taobao.dao.ShopinfoDao;
import com.beehive.erp.taobao.service.ShopinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 863 on 2015/12/22.
 */
@Service
public class ShopinfoServiceImpl implements ShopinfoService{

    @Autowired
    private ShopinfoDao shopinfoDao;



    @Override
    public int insert(Shopinfo shopinfo) {
        return shopinfoDao.insertSelective(shopinfo);
    }

    @Override
    public int selectByShopid(Integer shopid) {
        return shopinfoDao.selectByShopid(shopid);
    }

    @Override
    public void deleteByShopid(Integer shopid) {
        shopinfoDao.deleteByShopid(shopid);
    }

    @Override
    public List<Shopinfo> findAll() {
        return shopinfoDao.findAll();
    }
}

