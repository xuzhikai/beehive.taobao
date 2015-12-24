package com.beehive.erp.taobao.service.impl;

import com.beehive.erp.model.Item;
import com.beehive.erp.taobao.dao.ItemDao;
import com.beehive.erp.taobao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 863 on 2015/12/23.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public List<Item> selectByShopId(Integer shopId) {
        return itemDao.selectByShopId(shopId);
    }

    @Override
    public void deleteByShopId(Integer shopId) {
        itemDao.deleteByShopId(shopId);
    }

    @Override
    public void insertSelective(Item item) {
        itemDao.insertSelective(item);
    }

    @Override
    public Integer selectByShopIdCount(Integer shopId) {
        return itemDao.selectByShopIdCount(shopId);
    }
}
