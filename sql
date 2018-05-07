 create database blog default charset utf8 collate utf8_general_ci;
 //创建数据库并指定默认字符集
 GRANT ALL PRIVILEGES ON blog.* TO test@'%'  IDENTIFIED BY '123456' WITH GRANT OPTION;
//给test用户此数据库的权限

//用户表
CREATE TABLE user
(
  id          INT AUTO_INCREMENT     PRIMARY KEY,
  groupid     INT  NULL,
  username    VARCHAR(50)  NOT NULL,
  password    VARCHAR(50)  NOT NULL,
  image  VARCHAR(500) NULL                  COMMENT '预留',
  email       VARCHAR(50)  NULL              COMMENT '预留',
  phone       VARCHAR(20)  NULL             COMMENT '预留',
  question    VARCHAR(100) NULL             COMMENT '预留',
  answer      VARCHAR(100) NULL             COMMENT '预留',
  role        VARCHAR(1)   DEFAULT '1'      COMMENT '角色2-管理员,1-普通用户',
  status VARCHAR(1) DEFAULT '1' COMMENT '类别状态1-正常'类别状态2-禁止登陆,
  createtime DATETIME     NOT NULL     COMMENT '创建时间',
  updatetime DATETIME     NOT NULL     COMMENT '最后一次更新时间',
  UNIQUE (username)

);

CREATE TABLE comment
(
  id             INT AUTO_INCREMENT    PRIMARY KEY,
  groupid        INT     NULL,
  authorid       INT     NOT NULL,
  authorinfo       VARCHAR(100)     NULL,
  content         VARCHAR(300)         NOT NULL,
  ischecked      VARCHAR(1) DEFAULT '1'            COMMENT '预留，1表示已检查' ,
  status VARCHAR(1) DEFAULT '1' COMMENT '类别状态1-正常',
  createtime DATETIME     NOT NULL     COMMENT '创建时间',
  updatetime DATETIME     NOT NULL     COMMENT '最后一次更新时间'
);

CREATE TABLE group
(
  id          INT    AUTO_INCREMENT   PRIMARY KEY,
  type         VARCHAR(1) DEFAULT '1'   COMMENT '组类型，扩展预留',
  groupname   VARCHAR(50)  NOT NULL             COMMENT '组名称',
  createby    INT          NOT NULL             COMMENT '创建该组的用户',
  status      VARCHAR(1) DEFAULT '1' COMMENT '类别状态1-正常',
  groupcheck    VARCHAR(50)   NULL         COMMENT'验证数字',
  createtime DATETIME     NOT NULL     COMMENT '创建时间',
  updatetime DATETIME     NOT NULL     COMMENT '最后一次更新时间'
);

CREATE TABLE day
(
  id             INT AUTO_INCREMENT    PRIMARY KEY,
  groupid          INT     NULL,
  authorid       INT     NOT NULL,
  authorinfo       VARCHAR(100)     NULL,
  title            VARCHAR(20)      NOT NULL
  content         VARCHAR(300)      NULL,
  image          VARCHAR(300)      NULL,
  time           DATE               NOT NULL,
  ischecked      VARCHAR(1) DEFAULT '1'            COMMENT '预留，1表示已检查' ,
  status VARCHAR(1) DEFAULT '1' COMMENT '类别状态1-正常',
  createtime DATETIME     NOT NULL     COMMENT '创建时间',
  updatetime DATETIME     NOT NULL     COMMENT '最后一次更新时间'
);


CREATE TABLE image
(
  id             INT AUTO_INCREMENT    PRIMARY KEY,
  groupid          INT     NULL,
  authorid       INT     NOT NULL,
  authorinfo       VARCHAR(100)     NULL,
  title            VARCHAR(20)       NULL,
  content         VARCHAR(300)      NULL,
  image          VARCHAR(300)     NOT NULL,
  time           DATE              NULL,
  ischecked      VARCHAR(1) DEFAULT '1'            COMMENT '预留，1表示正常' ,
  status VARCHAR(1) DEFAULT '1' COMMENT '类别状态1-正常',
  createtime DATETIME     NOT NULL     COMMENT '创建时间',
  updatetime DATETIME     NOT NULL     COMMENT '最后一次更新时间'
);

