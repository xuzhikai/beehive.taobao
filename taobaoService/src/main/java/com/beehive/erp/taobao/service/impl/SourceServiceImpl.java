package com.beehive.erp.taobao.service.impl;

import com.beehive.erp.model.Source;
import com.beehive.erp.taobao.dao.SourceDao;
import com.beehive.erp.taobao.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 863 on 2015/12/24.
 */
@Service
public class SourceServiceImpl implements SourceService {

    @Autowired
    private SourceDao sourceDao;

    @Override
    public List<Source> findAll() {
        return sourceDao.findAll();
    }
}
