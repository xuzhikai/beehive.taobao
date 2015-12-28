package com.beehive.erp.taobao.service.impl;

import com.beehive.erp.model.UpLog;
import com.beehive.erp.taobao.dao.UpLogDao;
import com.beehive.erp.taobao.service.UpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 863 on 2015/12/28.
 */
@Service
public class UpLogServiceImpl implements UpLogService {

    @Autowired
    private UpLogDao upLogDao;


    @Override
    public void insertSelective(UpLog upLog) {
        upLogDao.insertSelective(upLog);
    }
}
