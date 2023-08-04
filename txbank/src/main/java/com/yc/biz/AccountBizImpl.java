package com.yc.biz;

import com.yc.bean.Account;
import com.yc.bean.OpRecord;
import com.yc.bean.OpType;
import com.yc.dao.AccountDao;
import com.yc.dao.OpRecoedDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service//spring托管
@Log4j2//日志
@Transactional//事务
public class AccountBizImpl implements AccountBiz {

    @Autowired
    private AccountDao accountDao; //账单对象
    @Autowired
    private OpRecoedDao opRecoedDao; //日志对象

    //spring对异常处理
    //正常的程序异常

    //开户操作 返回新账号的id
    @Override
    public Account openAccount(double money) {
        int accountid = this.accountDao.insert(money);
        //包装日志信息
        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(accountid);
        opRecord.setOpmoney(money);
        opRecord.setOptype(OpType.DEPOSITE);
        this.opRecoedDao.insertOpRecord(opRecord);
        //返回新的账户信息
        Account a = new Account();
        a.setAccountid(accountid);
        a.setMoney(money);
        return a;
    }

    @Override
    public Account deposite(int accountid, double money, Integer transferId) {
        Account a = null;
        try {
            a = this.accountDao.findById(accountid);
        } catch (Exception e) {
            log.error(e.getMessage());//TODO:封装保存日志的操作
            throw new RuntimeException("查无此账户：" + accountid + "无法完成存款操作");
        }
        //存款时 金额累加
        a.setMoney(a.getMoney() + money);

        this.accountDao.update(accountid, a.getMoney());

        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(accountid);
        opRecord.setOpmoney(money);
        if (transferId != null) {
            //表示为转账
            opRecord.setOptype(OpType.TRANSFER);
            opRecord.setTransferid(transferId);
        } else {
            //表示单纯的存款
            opRecord.setOptype(OpType.DEPOSITE);
        }
        this.opRecoedDao.insertOpRecord(opRecord);
        return a;
    }

    @Override
    public Account withdraw(int accountid, double money, Integer transferId) {
        Account a = null;
        try {
            a = this.accountDao.findById(accountid);
        } catch (Exception e) {
            log.error(e.getMessage());//TODO:封装保存日志的操作
            throw new RuntimeException("查无此账户：" + accountid + "无法完成取款操作");
        }
        //取款 减去金额 钱不够的话就报异常 不能取出
        a.setMoney(a.getMoney() - money);
        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(accountid);
        opRecord.setOpmoney(money);
        if (transferId != null) {
            opRecord.setOptype(OpType.TRANSFER);
            opRecord.setTransferid(transferId);
        } else {
            opRecord.setOptype(OpType.WITHDRAW);
        }
        //dao -> datasource -> connection -> jdbc 隐式事务提交 -> 一条sql提交一次 commit
        this.opRecoedDao.insertOpRecord(opRecord); //先插入日志
        this.accountDao.update(accountid, a.getMoney()); //再减金额
        return a;
    }

    @Override
    public Account withdraw(int accountid, double money) {
        return this.withdraw(accountid, money, null);
    }

    @Override
    public Account transfer(int accountid, double money, int toAccountId) {
        //从accountid转money到toAccountId
        this.deposite(toAccountId, money, accountid); // 收款方
        //从account取money
        Account a = this.withdraw(accountid, money, toAccountId);
        return a;
    }

    @Override
    public Account deposite(int accountid, double money) {
        return this.deposite(accountid, money, null);
    }

    @Override
    public Account findAccount(int accountid) {
        return this.accountDao.findById(accountid);
    }
}
