
--房间类型
---
CREATE TABLE roomtype(
       typeno VARCHAR2(10) PRIMARY KEY,--客房类型编号
       typename Varchar2(50),--客房类型  1 标准单人间,2 标准双人间,3 豪华单人间,4 豪华双人间,5 商务套房  6.总统套房  
       price number(5),--客房价格   
       beds INT --床位数 
);

--楼层
----
create table floors(
       fid INT primary key,--楼层数
       rooms int not null  --房间数
);

--客房信息(收银员只能查询)
---
create table room ( 
       rno INT primary key,--房号  
       fid INT CONSTRAINT fk_floors_fid REFERENCES floors(fid), --楼层 1 一楼，2 二楼，3 三楼....(根据楼层不同，界面显示房号)      
       typeno VARCHAR2(10) CONSTRAINT fk_roomtype_typeno REFERENCES roomtype(typeno),--客房类型编号
       state INT check(state BETWEEN 0 AND 4)NOT NULL-- 客房状态 (1 住人、2 预定、3 维护、4 清理、0 空）                     
);


--顾客信息表（定过房间的顾客）
--
create table customer(
       cid NUMBER(5) PRIMARY KEY,--顾客编号
       cname VARCHAR2(30) NOT NULL,-- 顾客姓名
       csex VARCHAR2(10) DEFAULT '男' CHECK (csex IN('男','女')), --顾客性别  
       card_id VARCHAR2(18) NOT NULL unique,--身份证号（唯一非空）(18位)--正则表达式
       ctel VARCHAR2(11) NOT NULL,--联系电话（11位）
       ctype INT  CHECK(ctype BETWEEN 1 AND 4),--顾客类型（1 普通顾客、2 vip会员、3、高级会员、4、至尊会员）      
       email varchar2(20),--邮箱    
       state INT CHECK (state IN(0,1))--状态 0 添加信息，1删除信息
);   
create sequence seq_customer_cid increment by 1 start WITH 1000;--创建顾客编号序列

/*--条件查询顾客信息
SELECT C.CID, C.CNAME, C.CSEX, C.CARD_ID, C.CTEL, V.VGRADE, C.EMAIL
  FROM CUSTOMER C, VIP V
 WHERE C.CTYPE = V.VID
   AND C.STATE = 1
   AND C.CTYPE = '2';
   
   */
 
--会员制度
create table vip(
       vid varchar2(20) primary key,--会员id
       vgrade varchar2(20),--会员等级
       sale  float --折扣
);


--管理员登录
--
create table operater(
       opid number(20) primary key,--管理员编号(同时也是界面登录的账号)
       opwd varchar2(30) default 'a' ,--密码（初始密码为a）
       ograde int check(Ograde between 1 and 2),--员工等级（1收银员，2超级管理员）
       oname varchar2(20) not null,--管理员姓名      
       otel  varchar2(11),--联系电话
       state INT CHECK (state IN(0,1))--状态 0 添加信息，1删除信息
);
create sequence seq_operater_opid increment by 1 start with 1000;--创建管理员id序列


----入住信息表（注意：顾客要入住，房间状态要么为空，要么在预定时间（12小时）之前--定时器）

create table check_in(
       orderno VARCHAR2(10)  PRIMARY KEY,--订单号
       cname varchar2(30) not NULL,-- 顾客姓名
       rno INT CONSTRAINT fk_checkin_rno REFERENCES room(rNo),--房号(外键)
       cash int,--押金
       date_in varchar2(20),--入住时间
       date_out varchar2(20) ,--退房时间
       bill_state INT  DEFAULT 0 CHECK (bill_state IN (0,1)) , --结账状态 (1 已结账、0 未结账 ) 
       opid NUMBER(20) constraint fk_checkin_opid references operater(opid) --收银员编号
);

create sequence seq_orderno increment by 2 start WITH 1001 ;--预住订单号序列

--用户房间预订信息(当客户入住之后，把预订信息录入到入住信息里面去)
----
create TABLE reservation( 
     preno number(10) primary key, --预订订单号（主键）
     typeno varchar2(20) , --客房类型编号
     rno INT CONSTRAINT fk_reservation_rno REFERENCES room(rno) ,--房间号
     cname varchar2(10), --顾客姓名
     sex varchar2(20) DEFAULT '男'check (sex in('男','女')), --性别
     tel number(11) NOT NULL, --联系方式
     card_id varchar2(20) not null,--身份证号(18位)
     order_in varchar2(20) NOT null, --预入住时间(精确到小时)
     price number(20),  --客房价格
     ope_tel NUMBER(11) default 10010,--客服电话
     order_out varchar2(20) NOT NULL,  --预退房时间（提前1小时查看用户是否退房，没退则提醒用户退房————定时器）（下拉列表选择）
     order_state INT DEFAULT 0 CHECK (order_state IN(0,1)),--入住状态： 0 未入住 , 1入住 （登记入住之后，顾客入住状态自动变为1。）                             
     email varchar2(40)--邮箱
);
create sequence seq_preno increment BY 2 start WITH 1000 ;--预住订单号序列
------SELECT 
--SELECT * FROM reservation res ;
--select * from roomtype rt;
--select * from customer c ;
--select * from floors f;
--select * from operater o ;
--select * from room r;
--select * from check_in for update;

--DROP table room;
--drop table roomtype;
--drop table floors;
--drop table operater;
--drop table customer;
--drop table check_in;
--drop table reservation;
--select * from roomtype ORDER by typeno asc
commit
--------------------------------------------------------------------------------------------------------------------------------------


--会员制度
insert into vip values(1,'普通顾客',1);
insert into vip values(2,'vip会员',0.90);
insert into vip values(3,'高级会员',0.85);
insert into vip values(4,'至尊会员',0.8);

--房间类型
insert into roomtype(typeno,typename,price,beds) values(1,'标准单人间',120,1);
insert into roomtype(typeno,typename,price,beds) values(2,'标准双人间',180,2);
insert into roomtype(typeno,typename,price,beds) values(3,'豪华单人间',240,1);
insert into roomtype(typeno,typename,price,beds) values(4,'豪华双人间',320,2);
insert into roomtype(typeno,typename,price,beds) values(5,'商务套房',600,1);
insert into roomtype(typeno,typename,price,beds) values(6,'总统套房',900,1);

--楼层
insert INTO floors(fid,rooms) values('1',6);
insert into floors(fid,rooms) values('2',6);
insert into floors(fid,rooms) values('3',6);
insert into floors(fid,rooms) values('4',6);
insert into floors(fid,rooms) values('5',6);
insert into floors(fid,rooms) values('6',6);

--房间
insert into room(rno,fid,typeno,state) values('101',1,1,0);
insert into room(rno,fid,typeno,state) values('102',1,2,0);
insert into room(rno,fid,typeno,state) values('103',1,2,0);
insert into room(rno,fid,typeno,state) values('104',1,4,0);
insert into room(rno,fid,typeno,state) values('105',1,3,0);
insert into room(rno,fid,typeno,state) values('106',1,1,0);

insert into room values('201',2,2,0);
insert into room values('202',2,3,0);
insert into room values('203',2,1,0);
insert into room values('204',2,2,0);
insert into room values('205',2,1,0);
insert into room values('206',2,2,0);


insert into room values('301',3,3,0);
insert into room values('302',3,4,0);
insert into room values('303',3,3,0);
insert into room values('304',3,1,0);
insert into room values('305',3,1,0);
insert into room values('306',3,2,0);


insert into room values('401',4,1,0);
insert into room values('402',4,5,0);
insert into room values('403',4,6,0);
insert into room values('404',4,1,0);
insert into room values('405',4,1,0);
insert into room values('406',4,4,0);

insert into room values('501',5,5,0);
insert into room values('502',5,1,0);
insert into room values('503',5,1,0);
insert into room values('504',5,3,0);
insert into room values('505',5,2,0);
insert into room values('506',5,5,0);


insert into room values('601',6,4,0);
insert into room values('602',6,1,0);
insert into room values('603',6,6,0);
insert into room values('604',6,6,0);
insert into room values('605',6,1,0);
insert into room values('606',6,2,0);

commit;





--订单退订(原因)：
--1.当已预订的用户在超过预入住时间 1小时内没有入住，则取消预约
--2.客户打电话到前台取消预订


--宾馆退房信息（退房之后，（房间信息表）房间状态自动转换为4 清理，然后才可以手动将房间状态转换为 0 空）
 --顾客编号,房号,结账日期,订单号,收银员
       


--删除表数据
--DELETE FROM <表名>WHERE <条件>;

--修改表数据
--UPDATE <表名> SET <列名=值> WHERE <条件>;

--发送邮件的功能


--统计月收入，年收入

