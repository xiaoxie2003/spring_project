package com.xx.springtest4.user;

import com.xx.springtest4.jabcTemplate.RowMapper;
import com.xx.springtest4.jabcTemplate.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BankAccoutDao extends JdbcTemplate {

    @Autowired  //按类型注入  DataSource接口 -> MyDataSource的实现类
    public BankAccoutDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<BankAccout> findAll(){
        //子类BankAccoutDao调用父类JdbcTemplate方法executeQuery（）
        return super.executeQuery("select * from bank where id = ? ", new RowMapper<Object>() {
            @Override
            public Object mapper(ResultSet rs,int i) throws SQLException {
                BankAccout ba = new BankAccout();
                ba.setId(rs.getInt(1));
                ba.setBalance(rs.getDouble(2));
                return ba;
            }
        },2);  //params为sql语句问号的内容
    }
}
