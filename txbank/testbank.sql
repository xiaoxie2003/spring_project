#银行存 取 转账事务

#创建库
create database testbank;
use testbank;
#账号信息表
create table accounts
(
    accountid int primary key auto_increment,
    balance numeric(10,2)
);

#创建流水表
create table oprecord
(
    id int primary key auto_increment,
    accountid int references accounts(accountid),
    opmoney numeric(10,2),
    optime datatime,
    optype enum('deposite','withdraw','transfer') not null,
    transferid varchar(50)
);

alter table accounts add constraint ck_balance check ( balance>0 );
