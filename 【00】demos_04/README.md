# 环境

- 编程语言：Java 1.8
- IDE：intelliJ IDEA 2020.2
- 数据库：MySQL 8.0
- Web容器：Tomcat 9.0.37
- ssm：Spring + SpringMVC + Mybatis 
- 版本管理工具：Maven 3.6.3

# 01：ssm在线视频学习网站 - 模拟

### 一、数据库

1. 课程类型 course_type

   id、类目名称 name、类目状态 flag

2. 课程专题 course_topic

   id、标题 title、课程封面 cover_url、课程简介 intro、简介URL intro_url、课程浏览次数 views、课程状态 flag、**课程分类** course_type_id、创建时间 create_time、VIP会员 vip_flag、课件下载 courseware_url

3. 课程视频 course_video

   id、视频名字 name、是否试看 free_flag、**课程专题ID** course_topic_id、视频状态 flag、创建时间 create_time、视频播放地址1 video_url1、视频播放地址2 video_url2

4. 用户表 user

   id、用户名 name、密码 passsword、电话 number、email、会员状态 vip_flag、注册时间  create_time

5. 工具类型 tool_type

   id、名称 name、状态 flag

6. 工具表 tool_item

   id、名称 name、地址URL tool_url、封面图片 cover_url、**类型ID** tool_type_id

7. 横幅图片 banner

   id、横幅图片URL banner_img_url、状态 flag、类型 type、创建时间 create_time、目标地址 banner_url

```mysql
# 数据库
CREATE SCHEMA `ssm4` DEFAULT CHARACTER SET utf8 ;

# 课程类型
CREATE TABLE `ssm4`.`course_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `flag` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

# 课程专题
CREATE TABLE `ssm4`.`course_topic` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NULL,
  `cover_url` VARCHAR(255) NULL,
  `intro` VARCHAR(255) NULL,
  `intro_url` VARCHAR(255) NULL,
  `views` INT NULL,
  `flag` TINYINT NULL,
  `course_type_id` INT NULL,
  `create_time` DATETIME NULL,
  `vip_flag` TINYINT NULL,
  `courseware_url` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

# 课程视频
CREATE TABLE `ssm4`.`course_video` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `free_flag` TINYINT NULL,
  `course_type_id` INT NULL,
  `flag` TINYINT NULL,
  `create_time` DATETIME NULL,
  `video_url1` VARCHAR(255) NULL,
  `video_url2` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

# 用户
CREATE TABLE `ssm4`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `password` VARCHAR(255) NULL,
  `number` VARCHAR(255) NULL,
  `email` VARCHAR(255) NULL,
  `vip_flag` TINYINT NULL,
  `create_time` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

# 工具类型
CREATE TABLE `ssm4`.`tool_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `flag` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

# 工具
CREATE TABLE `ssm4`.`tool_item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `tool_url` VARCHAR(255) NULL,
  `cover_url` VARCHAR(255) NULL,
  `tool_type_id` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

# 横幅图片
CREATE TABLE `ssm4`.`banner` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `banner_img_url` VARCHAR(255) NULL,
  `flag` TINYINT NULL,
  `type` INT NULL COMMENT '广告图分类，区分展示页面',
  `create_time` DATETIME NULL,
  `banner_url` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;
```

### 二、前端页面

**1、头页面**

- 注册

  > 正则、验证码、ajax验证

- 登录

  > ajax验证、自动登录、忘记密码

- 自动登录

  > 使用token记录自动登录状态，保存用户登录状态48小时（2天）；关闭浏览器，下次打开浏览器，如果没有超过时间，仍然是登录状态
  >
  > token不能保存在session中，因为浏览器关闭session结束了；正常实现（Redis）或者保存在全局的数据 application
  
    浏览器（cookie）
    token = xxxxxxxxxxxxxxxxxxxxxxxxx (登录凭证，48小时失效)
  
    服务器（）
    1 登录成功，返回token给浏览器保存，自动登录凭证。
    2 token生成：时间、用户、IP、浏览器信息、（MD5）
  
    用户打开浏览器
    1 token发送到服务器
    2 服务器根据token获取对应的值，
    3 验证token是否有效：时间、用户、IP、浏览器信息、（MD5）

- 恢复用户登录

  > 拦截器，判断token是否有效（时间、用户、IP、浏览器信息）+ 时间是否超时
  >
  > 如果有效，直接session放入user，变成已登录
  >
  > 如果失效，什么都不做

- 忘记密码（email找回密码）

  1. 跳转找回密码页面
  2. 填写用户email、验证码，点击发送邮件（修改密码链接）
     - 实际路径：http://www.duyi.com?m=xxxxxxxxxxx&u=email&t=xxx
     - 路径混淆：http://www.duyi.com?token=xxxxxxxxxxx，token=（m=xxxxxxxxxxx&u=email）base64混淆
  3. 打开邮箱，访问链接
  4. 服务器接处理URL（修改密码链接），
     - 验证URL是否合法（时间是否超时，是否是服务器生成，是否有篡改）
     - 跳转修改密码页面
     - 输入密码，提交密码修改用户密码