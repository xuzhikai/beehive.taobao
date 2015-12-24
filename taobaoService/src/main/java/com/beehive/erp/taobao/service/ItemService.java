package com.beehive.erp.taobao.service;

import com.beehive.erp.model.Item;

import java.util.List;

/**
 * Created by 863 on 2015/12/23.
 */
public interface ItemService {

    List<Item> selectByShopId(Integer shopId);

    void deleteByShopId(Integer shopId);

    void insertSelective(Item item);

    Integer selectByShopIdCount(Integer shopId);
}
