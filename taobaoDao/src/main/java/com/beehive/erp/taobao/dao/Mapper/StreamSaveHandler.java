package com.beehive.erp.taobao.dao.Mapper;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.ObjectTypeHandler;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 */
@MappedJdbcTypes(JdbcType.LONGVARBINARY)
public class StreamSaveHandler extends BaseTypeHandler<InputStream> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, InputStream inputStream, JdbcType jdbcType) throws SQLException {
        System.out.println("just here");
        preparedStatement.setBinaryStream(i,inputStream);
    }

    @Override
    public InputStream getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getBlob(s).getBinaryStream();
        //return resultSet.getBinaryStream(s);
    }

    @Override
    public InputStream getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getBinaryStream(i);
    }

    @Override
    public InputStream getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
