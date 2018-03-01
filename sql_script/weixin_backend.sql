DROP TABLE IF EXISTS `app_info`;
CREATE TABLE `app_info` (
  `inner_appid` varchar(16) NOT NULL COMMENT '内部APPID',
  `wx_appid` varchar(32) NOT NULL COMMENT '微信appID',
  `wx_appsecret` varchar(36) NOT NULL COMMENT '微信AppSecret',
  `wx_appname` varchar(64) DEFAULT NULL COMMENT '微信APP的名字',
  `is_enabled` char(2) DEFAULT NULL COMMENT '是否启用(1启用，0禁用)',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '微信应用表';

DROP TABLE IF EXISTS `wx_user_info`;
CREATE TABLE `wx_user_info` (
  `user_id` varchar(36) NOT NULL COMMENT '内部用户ID',
  `inner_appid` varchar(16) NOT NULL COMMENT '内部APPID',
  `open_id` varchar(36) NOT NULL COMMENT '微信OPENID',
  `nick_name` varchar(128) NOT NULL COMMENT '微信昵称',
  `gender` varchar(4) DEFAULT NULL COMMENT '性别',
  `city` varchar(64) DEFAULT NULL COMMENT '城市',
  `province` varchar(64) DEFAULT NULL COMMENT '省',
  `country` varchar(64) DEFAULT NULL COMMENT '国家',
  `avatar_url` varchar(256) DEFAULT NULL COMMENT '头像URL',
  `union_id` varchar(36) DEFAULT NULL COMMENT '微信unionId',
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  INDEX idx_appid_openid(`inner_appid`,`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '微信用户表';


DROP TABLE IF EXISTS `user_login_record`;
CREATE TABLE `user_login_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID自增',
  `user_id` varchar(36) NOT NULL COMMENT '内部用户ID',
  `inner_appid` varchar(16) NOT NULL COMMENT '内部APPID',
  `result` int(11) DEFAULT '0' COMMENT '登陆结果码',
  `remark` varchar(256) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  INDEX idx_userid(`user_id`),
  INDEX idx_appid(`inner_appid`)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户登陆日志表';

DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID自增',
  `inner_appid` varchar(16) NOT NULL COMMENT '内部APPID',
  `title` varchar(36) NOT NULL COMMENT '标题',
  `head_image` varchar(128) NOT NULL COMMENT '标题图片的URL地址',
  `author_name` varchar(128) NOT NULL COMMENT '作者名字(来源)',
  `type` int(11) DEFAULT '0' COMMENT '业务类型',
  `category` int(11) DEFAULT '1' COMMENT '分类: 1:视频，2:文字图片',
  `play_url` varchar(128) DEFAULT NULL COMMENT '视频对应的URL地址',
  `status` int(11) DEFAULT '0' COMMENT '状态: 0:初始; 1:正常; 2:删除',
  `create_date` datetime NOT NULL,
  `remark` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX idx_createdate(`create_date`),
  INDEX idx_appid(`inner_appid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '内容表';

INSERT INTO `content` VALUES (1, '1', '美女最爱的俏皮蜈蚣辫编发教程，学会之后上街回头率满满！', 'http://boscdn.bpc.baidu.com/v1/mediaspot/49e02af52ce6a51962a012ab4651733b.png','百度APP','0',1,'https://vd3.bdstatic.com/mda-hmvgrnst92mf39i0/mda-hmvgrnst92mf39i0.mp4',1,'2018-03-10 15:40:39',NULL);