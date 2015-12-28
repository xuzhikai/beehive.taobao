package com.beehive.erp.taobao.dao;

import com.beehive.erp.model.UpLog;

/**
 * Created by 863 on 2015/12/28.
 */
public interface UpLogDao {
    void insertSelective(UpLog upLog);
}
