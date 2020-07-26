# 环境

- 编程语言：Java 14.0.1
- IDE：intelliJ IDEA 2020.1.2
- 数据库：MySQL 8.0（用户名与密码都是`root`，ip及端口是`localhost:3306`）
- JDBC：mysql-connector-java-8.0.20.jar

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

