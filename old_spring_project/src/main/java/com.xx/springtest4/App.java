package com.xx.springtest4;

import com.xx.springtest4.user.BankAccout;
import com.xx.springtest4.user.BankAccoutDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
//        DataSource ds = (DataSource) ac.getBean("myDataSource");
//
//        Connection con = ds.getConnection();
//        System.out.println(con);
        BankAccoutDao dao = (BankAccoutDao) ac.getBean("bankAccoutDao");
        List<BankAccout> list = dao.findAll();
        for(BankAccout b:list){
            System.out.println(b);
        }
    }
}
