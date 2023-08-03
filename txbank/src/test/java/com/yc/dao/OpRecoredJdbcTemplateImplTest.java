package com.yc.dao;

import com.yc.bean.OpRecord;
import com.yc.bean.OpType;
import com.yc.configs.Config;
import com.yc.configs.DataSourceConfig;
import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class, DataSourceConfig.class})
@Log4j2
public class OpRecoredJdbcTemplateImplTest {

    @Autowired
    private OpRecoedDao opRecoedDao;

    @Test
    public void insertOpRecord() {
        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(56);
        opRecord.setOpmoney(500);
        opRecord.setOptype(OpType.DEPOSITE);
        this.opRecoedDao.insertOpRecord(opRecord);
    }

    @Test
    public void findOpRecord() {
        List<OpRecord>list = this.opRecoedDao.findOpRecord(35);
        System.out.println(list);
    }

    @Test
    public void findOpRecordId() {
        List<OpRecord>list = this.opRecoedDao.findOpRecordId(34,"DEPOSITE");
        System.out.println(list);
    }

    @Test
    public void findOprecord() {
    }
}