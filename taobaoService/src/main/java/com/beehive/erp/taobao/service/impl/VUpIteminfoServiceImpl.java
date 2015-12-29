package com.beehive.erp.taobao.service.impl;

import com.beehive.erp.model.VUpIteminfo;
import com.beehive.erp.taobao.dao.VUpIteminfoDao;
import com.beehive.erp.taobao.service.VUpIteminfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 863 on 2015/12/29.
 */
@Service
public class VUpIteminfoServiceImpl implements VUpIteminfoService {


    @Autowired
    private VUpIteminfoDao vUpIteminfoDao;

    @Override
    public List<VUpIteminfo> findAll() {
        return vUpIteminfoDao.findAll();
    }
}
