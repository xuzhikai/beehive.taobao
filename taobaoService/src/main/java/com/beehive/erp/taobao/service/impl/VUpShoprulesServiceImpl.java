package com.beehive.erp.taobao.service.impl;

import com.beehive.erp.model.VUpShoprules;
import com.beehive.erp.taobao.dao.VUpShoprulesDao;
import com.beehive.erp.taobao.service.VUpShoprulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 863 on 2015/12/28.
 */
@Service
public class VUpShoprulesServiceImpl implements VUpShoprulesService {


    @Autowired
    private VUpShoprulesDao vUpShoprulesDao;


    @Override
    public List<VUpShoprules> findAll() {
        return vUpShoprulesDao.findAll();
    }
}
