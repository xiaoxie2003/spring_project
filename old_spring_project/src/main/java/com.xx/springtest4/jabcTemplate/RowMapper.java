package com.xx.springtest4.jabcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    /**
     * 对第i行的ResultSet转换成T对象  --》具体由用户决定
     * @param rs
     * @param i
     * @return
     */
    public T mapper(ResultSet rs,int i) throws SQLException;
}
