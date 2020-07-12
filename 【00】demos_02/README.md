# 环境

- 编程语言：Java 14.0.1
- 数据库：MySQL 8.0（用户名与密码都是`root`，ip及端口是`localhost:3306`）
- JDBC：mysql-connector-java-8.0.20.jar
- IDE：intelliJ IDEA 2020.1.2

# 01：JDBC - 练习

> 知识点：JavaSE + JDBC + MySQL + MVC分层思想

这是一个练习，主要是Java中运用JDBC进行数据库的操纵：

1. 查询人口数在1000到2000之间的城市所属在哪个地区
2. 查询每个国家的城市个数 按照城市个数升序排列
3. 查询各地区城市人口平均数 按照人口平均数降序排列
4. 查询哈尔滨所在的国家的名字
5. 查询各地区名字和人口总数 
6. 查询美国有哪些城市 列出城市名
7. 查询人口最多的城市在哪个国家
8. 查询每个国家的人口总数
9. 查询城市人口数为1500万的国家名字
10. 查询各地区总人数 按照人口数总量降序排列
11. 查询人口总数超过5000的国家名称
12. 查询人口数大于杭州的城市都有哪些

**数据库初始化：**

```mysql
# 如果没有创建该数据库
create database study;

use study;

# country表（存储国家信息）
create table country(
	cid int(5),
	cname varchar(20)
);

alter table country add primary key(cid);

insert into country values(1,'中国');
insert into country values(2,'美国');
insert into country values(3,'日本');

# area表（存储地区信息）
create table area(
	aid int(5),
	aname varchar(20),
	cid int(5)
);

alter table area add primary key(aid);
alter table area add constraint fk_area_country foreign key(cid) references country(cid);

insert into area values(1,'北方',1);
insert into area values(2,'南方',1);
insert into area values(3,'西部',2);
insert into area values(4,'东部',2);
insert into area values(5,'北海道',3);
insert into area values(6,'四国',3);

# city表（存储城市信息）
create table city(
	cityid int(5),
	cityname varchar(20),
	citysize int(10),
	aid int(5)
);

alter table city add primary key(cityid);
alter table city add constraint fk_city_area foreign key(aid) references area(aid);

insert into city values(1,'哈尔滨',750,1);
insert into city values(2,'大连',50,1);
insert into city values(3,'北京',2000,1);
insert into city values(4,'上海',1500,2);
insert into city values(5,'杭州',800,2);
insert into city values(6,'洛杉矶',1200,3);
insert into city values(7,'休斯顿',750,3);
insert into city values(8,'纽约',1000,4);
insert into city values(9,'底特律',500,4);
insert into city values(10,'东京',1500,5);
insert into city values(11,'名古屋',50,5);
insert into city values(12,'大阪',20,6);
```

# 02：银行系统 - 模拟

> 知识点：JavaSE + JDBC + MySQL + MVC分层思想

- **功能**：登录 + 查询 + 存款  + 取款 + 转账 + 开户 + 销户
- **UI：** Swing
- **数据库初始化：**

```mysql
# 如果没有创建该数据库
create database atm;

use atm;

create table atm(
	aname varchar(20),
	apassword varchar(20),
	abalance float(10,2)
);

alter table atm add primary key(aname);

insert into atm values('zhangsan','333',300),('lisi','444',400),('wangwu','555',500);
```

# 03：JDBC连接池 + ORM框架 - 模拟



