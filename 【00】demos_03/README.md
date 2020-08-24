# 环境

- 编程语言：Java 14.0.1
- IDE：intelliJ IDEA 2020.1
- 数据库：MySQL 8.0（用户名与密码都是`root`，ip及端口是`localhost:3306`）
- JDBC：mysql-connector-java-8.0.20.jar

- Web容器：Tomcat 9.0.37

# 01：请求与响应 - 模拟

> 知识点：JavaSE

写一个超级简陋的浏览器，和一个超级简陋的服务器，

模拟浏览器与服务器之间，请求与响应的关系。

**浏览器：**

1. <font color="#ed7b35">**Browser**</font> 在控制台中输入url（只需要输入`ip:port/资源名?key=value&key=value...`）
2. <font color="#ed7b35">**Browser**</font> 解析url，获取ip，port，资源名和参数
3. <font color="#ed7b35">**Browser**</font> 使用ip和port创建Socket，与服务器建立连接，并将资源名和参数发送给服务器
4. <font color="#ed7b35">**Browser**</font> 等待服务器的响应....................
5. <font color="#ed7b35">**Browser**</font> 接收服务器回写的响应消息（这里模拟了html标签的使用）
6. <font color="#ed7b35">**Browser**</font> 解析响应信息，并展示出来（解析html标签）
7. <font color="#ed7b35">**Browser**</font> 如果服务器返回来的是form表单，那么浏览器继续发送请求

**服务器：**

- <font color="#f40">**Server**</font> 服务器先启动，服务器是多线程的
- <font color="#f40">**Server**</font> 服务器线程参考server.properties配置文件，获取端口号,生成ServerSocket
- <font color="#f40">**Server**</font> 服务器线程等待浏览器的请求（即监听端口号）..........
- <font color="#f40">**Server**</font> 收到浏览器的请求后，调用ServerHandler类（1.读取消息 2..解析 3.找人做事 4.响应回去）
	1. 读取资源名和参数信息
	2. 解析字符串，并生成HttpServletRequest来存放资源和参数信息
	3. **若是动态资源：** 调用ServletController，参考web.properties配置文件，找到路径对应的controller类（->service->dao->...），将要返回的资源存放在HttpServletResponse中，然后将HttpServletResponse中的内容返回给浏览器
	4. **若是静态资源：** 就直接将文件中的内容返回给浏览器

# 02：银行系统（Tomcat+Servlet） - 模拟

>  知识点：JavaSE + JDBC + MySQL + MVC分层思想 + Tomcat + Servlet

- **功能**：登录 + 查询 + 存款  + 取款 + 转账 + 开户 + 销户
- **UI**：浏览器
- **数据库初始化**：

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

# 03：购物系统（Servlet+JSP） - 模拟

> 知识点：JavaSE + JDBC + MySQL + MVC分层思想 + Tomcat + Servlet + JSP

- **功能**：登录 + 注册+ 购物 + 购物车 + 结算
- **UI**：浏览器
- **数据库初始化**：
  - 创建三张表格：用户表格、商品种类表格、商品表格
  - 商品表格与商品种类表格是多对一的关系

```mysql
# 如果没有创建该数据库
create database shopping;

use shopping;

# 用户表格
create table user(
	uname varchar(20),
	upassword varchar(20),
    ubalance float(10,2)
);

alter table user add primary key(uname);

insert into user values('zhangsan','333',3000);
insert into user values('lisi','444',4000);

# 商品种类表格
create table kind(
	kid int(4),
	kname varchar(20)
);

alter table kind add primary key(kid);

insert into kind values(1,'食品');
insert into kind values(2,'书籍');
insert into kind values(3,'服饰');

# 商品表格
create table commodity(
	cid int(6),
	cname varchar(20),
	cprice float(10,2),
    ccount int(6),
	kid int(4)
);

alter table commodity add primary key(cid);
alter table commodity add constraint fk_commodity_kind foreign key (kid) references kind(kid);

insert into commodity values(1,'费列罗巧克力',10,100,1);
insert into commodity values(2,'安慕希酸奶',8,100,1);
insert into commodity values(3,'百草味草莓干',20,100,1);
insert into commodity values(4,'Head First Java',78,100,2);
insert into commodity values(5,'Java核心技术',87,100,2);
insert into commodity values(6,'算法导论',96,100,2);
insert into commodity values(7,'程序员格子衫',200,100,3);
insert into commodity values(8,'程序员双肩包',150,100,3);
insert into commodity values(9,'程序员假发',300,100,3);
```