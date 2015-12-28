package com.beehive.erp.taobao.service;

import com.beehive.erp.model.UpLog;

/**
 * Created by 863 on 2015/12/28.
 */
public interface UpLogService {
    void insertSelective(UpLog upLog);
}
