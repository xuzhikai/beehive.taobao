package com.beehive.erp.taobao.dao;

import com.beehive.erp.model.Source;

import java.util.List;

/**
 * Created by 863 on 2015/12/24.
 */
public interface SourceDao {

    List<Source> findAll();

    Source selectBySourceId(String sourceId);
}
