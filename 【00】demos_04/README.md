# 环境

- 编程语言：Java 1.8
- IDE：intelliJ IDEA 2020.2
- 数据库：MySQL 8.0
- Web容器：Tomcat 9.0.37
- ssm：Spring + SpringMVC + Mybatis 
- 版本管理工具：Maven 3.6.3

# 01：ssm在线视频学习网站 - 模拟



- **数据库：**

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

