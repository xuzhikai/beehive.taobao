package com.beehive.erp.taobao.service;

import com.beehive.erp.model.Shopinfo;

import java.util.List;

/**
 * Created by 863 on 2015/12/22.
 */

public interface ShopinfoService {
    int insert(Shopinfo shopinfo);

    int selectByShopid(Integer shopid);

    void deleteByShopid(Integer shopid);

    List<Shopinfo> findAll();

}
