/*
MySQL Backup
Source Server Version: 5.6.19
Source Database: wxs1
Date: 2016/06/04 23:19:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `b_access`
-- ----------------------------
DROP TABLE IF EXISTS `b_access`;
CREATE TABLE `b_access` (
  `baccess_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '访问记录',
  `site_id` int(11) NOT NULL COMMENT '空间id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `time` datetime DEFAULT NULL COMMENT '访问时间',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '用户ip',
  `client_type` smallint(6) DEFAULT NULL COMMENT '访问类型',
  PRIMARY KEY (`baccess_id`),
  KEY `baccess_id` (`baccess_id`),
  KEY `site_id` (`site_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_b_access_site` FOREIGN KEY (`site_id`) REFERENCES `b_site` (`site_id`),
  CONSTRAINT `FK_b_access_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `b_backlist`
-- ----------------------------
DROP TABLE IF EXISTS `b_backlist`;
CREATE TABLE `b_backlist` (
  `blacklist_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '黑名单id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `back_id` int(11) NOT NULL COMMENT '好友id',
  `bl_type` int(11) DEFAULT NULL COMMENT '黑名单类型',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`blacklist_id`),
  KEY `blacklist_id` (`blacklist_id`),
  KEY `user_id` (`user_id`),
  KEY `back_id` (`back_id`),
  CONSTRAINT `FK_b_backlistt_user2` FOREIGN KEY (`back_id`) REFERENCES `u_user` (`user_id`),
  CONSTRAINT `FK_b_backlist_user1` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `b_config`
-- ----------------------------
DROP TABLE IF EXISTS `b_config`;
CREATE TABLE `b_config` (
  `bconfig_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '配置id',
  `site_id` int(11) NOT NULL COMMENT '空间id',
  `config_key` varchar(20) DEFAULT NULL COMMENT '配置主键',
  `value` varchar(36) DEFAULT NULL COMMENT '配置参数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`bconfig_id`),
  KEY `bconfig_id` (`bconfig_id`),
  KEY `site_id` (`site_id`),
  CONSTRAINT `FK_b_config_site` FOREIGN KEY (`site_id`) REFERENCES `b_site` (`site_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `b_site`
-- ----------------------------
DROP TABLE IF EXISTS `b_site`;
CREATE TABLE `b_site` (
  `site_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '网站id',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `name` varchar(20) NOT NULL COMMENT '名字',
  `title` varchar(10) NOT NULL COMMENT '标题(空间的url表示)',
  `site_url` varchar(100) NOT NULL COMMENT '域名',
  `site_detail` varchar(250) DEFAULT NULL COMMENT '说明',
  `logo` varchar(50) DEFAULT NULL COMMENT 'logo',
  `style_id` int(11) NOT NULL COMMENT '样式id',
  `flag` smallint(6) DEFAULT NULL COMMENT '空间标志',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_time` datetime DEFAULT NULL COMMENT '最后发帖时间',
  `mode` smallint(6) DEFAULT NULL COMMENT '权限模式',
  `access_code` varchar(50) DEFAULT NULL COMMENT '访问密码',
  `index_name` varchar(10) DEFAULT NULL COMMENT '个性化首页名',
  `diary_name` varchar(10) DEFAULT NULL COMMENT '个性化日记名',
  `sound_name` varchar(10) DEFAULT NULL COMMENT '个性化声音',
  `image_name` varchar(10) DEFAULT NULL COMMENT '个性相册名',
  `friend_name` varchar(10) DEFAULT NULL COMMENT '个性化好友名',
  `site_type` int(11) DEFAULT NULL COMMENT '空间类型',
  `status` smallint(6) DEFAULT NULL COMMENT '记录状态',
  PRIMARY KEY (`site_id`),
  KEY `site_id` (`site_id`),
  KEY `user_id` (`user_id`),
  KEY `name` (`name`),
  KEY `FK_u_site_style` (`style_id`),
  CONSTRAINT `FK_b_site_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`),
  CONSTRAINT `FK_u_site_style` FOREIGN KEY (`style_id`) REFERENCES `sys_style` (`style_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `b_status`
-- ----------------------------
DROP TABLE IF EXISTS `b_status`;
CREATE TABLE `b_status` (
  `bstatus_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '状态id',
  `site_id` int(11) NOT NULL COMMENT '空间编号',
  `time` datetime NOT NULL COMMENT '统计时间',
  `uv_count` int(11) DEFAULT NULL COMMENT '用户数',
  `pv_count` int(11) DEFAULT NULL COMMENT '点击数',
  `type` smallint(6) DEFAULT NULL COMMENT '访问类型',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`bstatus_id`),
  KEY `bstatus_id` (`bstatus_id`),
  KEY `site_id` (`site_id`),
  CONSTRAINT `FK_b_status_site` FOREIGN KEY (`site_id`) REFERENCES `b_site` (`site_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `b_tag`
-- ----------------------------
DROP TABLE IF EXISTS `b_tag`;
CREATE TABLE `b_tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `site_id` int(11) NOT NULL COMMENT '空间标示',
  `ref_id` int(11) NOT NULL COMMENT '应用id',
  `ref_type` smallint(6) DEFAULT NULL COMMENT '应用类型',
  `time` datetime DEFAULT NULL COMMENT '时间',
  `tag_name` varchar(20) DEFAULT NULL COMMENT '标签名',
  PRIMARY KEY (`tag_id`),
  KEY `tag_id` (`tag_id`),
  KEY `site_id` (`site_id`),
  KEY `FK_b_tag_diary` (`ref_id`),
  CONSTRAINT `FK_b_tag_diary` FOREIGN KEY (`ref_id`) REFERENCES `d_diary` (`diary_id`),
  CONSTRAINT `FK_b_tag_site` FOREIGN KEY (`site_id`) REFERENCES `b_site` (`site_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `d_access`
-- ----------------------------
DROP TABLE IF EXISTS `d_access`;
CREATE TABLE `d_access` (
  `daccess_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `diary_id` int(11) NOT NULL COMMENT '日记id',
  `user_id` int(11) NOT NULL COMMENT '浏览人id',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `time` datetime DEFAULT NULL COMMENT '时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` smallint(6) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`daccess_id`),
  KEY `daccess_id` (`daccess_id`),
  KEY `diary_id` (`diary_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_d_access_diary` FOREIGN KEY (`diary_id`) REFERENCES `d_diary` (`diary_id`),
  CONSTRAINT `FK_d_access_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `d_annex`
-- ----------------------------
DROP TABLE IF EXISTS `d_annex`;
CREATE TABLE `d_annex` (
  `annex_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '附件id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `site_id` int(11) DEFAULT NULL COMMENT '空间id',
  `diary_id` int(11) NOT NULL COMMENT '日记id',
  `upload_time` datetime DEFAULT NULL COMMENT '上传时间',
  `download_count` int(11) DEFAULT NULL COMMENT '下次次数',
  `status` smallint(6) DEFAULT NULL COMMENT '状态',
  `validate` smallint(6) DEFAULT NULL COMMENT '附件验证',
  `url` varchar(256) DEFAULT NULL COMMENT 'URL地址',
  `path` varchar(256) DEFAULT NULL COMMENT '附近的路径',
  `file_name` varchar(256) DEFAULT NULL COMMENT '文件类型',
  `file_description` varchar(1024) DEFAULT NULL COMMENT '文件介绍',
  `size` int(11) DEFAULT NULL COMMENT '文件大小',
  `last_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `ext1` int(11) DEFAULT NULL,
  `ext2` int(11) DEFAULT NULL,
  `ext3` varchar(1024) DEFAULT NULL,
  `ext4` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`annex_id`),
  KEY `annex_id` (`annex_id`),
  KEY `site_id` (`site_id`),
  KEY `user_id` (`user_id`),
  KEY `diary_id` (`diary_id`),
  CONSTRAINT `FK_d_annex_diary` FOREIGN KEY (`diary_id`) REFERENCES `d_diary` (`diary_id`),
  CONSTRAINT `FK_d_annex_site` FOREIGN KEY (`site_id`) REFERENCES `b_site` (`site_id`),
  CONSTRAINT `FK_d_annex_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `d_catalog`
-- ----------------------------
DROP TABLE IF EXISTS `d_catalog`;
CREATE TABLE `d_catalog` (
  `catalog_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日记分类id',
  `site_id` int(11) DEFAULT NULL COMMENT '空间id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `catalog_name` varchar(20) NOT NULL COMMENT '分类名',
  `description` varchar(200) DEFAULT NULL COMMENT '说明',
  `create_time` datetime DEFAULT NULL COMMENT '创立时间',
  `article_count` int(11) DEFAULT '0' COMMENT '文章数量',
  `catalog_type` int(11) DEFAULT NULL COMMENT '类型',
  `verifyCode` varchar(20) DEFAULT NULL COMMENT '访问密码',
  `sort_order` smallint(6) DEFAULT NULL COMMENT '排列顺序',
  `ext1` int(11) DEFAULT NULL COMMENT '扩展1',
  `ext2` int(11) DEFAULT NULL,
  `ext3` varchar(1024) DEFAULT NULL,
  `ext4` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`catalog_id`),
  KEY `catalog_id` (`catalog_id`),
  KEY `site_id` (`site_id`),
  KEY `FK_d_catalog_user` (`user_id`),
  CONSTRAINT `FK_d_catalog_site` FOREIGN KEY (`site_id`) REFERENCES `b_site` (`site_id`),
  CONSTRAINT `FK_d_catalog_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `d_diary`
-- ----------------------------
DROP TABLE IF EXISTS `d_diary`;
CREATE TABLE `d_diary` (
  `diary_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日记id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `site_id` int(11) DEFAULT NULL COMMENT '空间id',
  `catalog_id` int(11) NOT NULL COMMENT '空间分类id',
  `type_id` int(11) DEFAULT NULL COMMENT '文章类型',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  `txt` text COMMENT '日志的纯文本',
  `diary_size` int(11) DEFAULT NULL COMMENT '内容字数',
  `reply_count` int(11) DEFAULT '0' COMMENT '回复数',
  `view_count` int(11) DEFAULT '0' COMMENT '浏览数',
  `upvote_count` int(11) DEFAULT '0' COMMENT '点赞数',
  `favorite_count` int(11) DEFAULT '0' COMMENT '收藏数',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `write_time` datetime DEFAULT NULL COMMENT '发表时间',
  `last_read_time` datetime DEFAULT NULL COMMENT '最后阅读时间',
  `last_reply_time` datetime DEFAULT NULL COMMENT '最后回复时间',
  `modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `reply_notify` smallint(6) DEFAULT NULL COMMENT '是否回帖提醒',
  `mood_level` smallint(6) DEFAULT NULL COMMENT '心情指数',
  `locked` smallint(6) DEFAULT NULL COMMENT '是否锁定',
  `contribute` smallint(6) DEFAULT NULL COMMENT '是否投稿',
  `role` smallint(6) DEFAULT NULL COMMENT '权限',
  `tag` varchar(100) DEFAULT NULL COMMENT '标签',
  `annex_id` int(11) DEFAULT NULL COMMENT '附件id',
  `ext1` int(11) DEFAULT NULL COMMENT '扩展1（日志修改次数）',
  `ext2` int(11) DEFAULT NULL COMMENT '扩展2（引用id）',
  `ext3` int(11) DEFAULT NULL COMMENT '扩展3',
  `ext4` int(11) DEFAULT NULL COMMENT '扩展4',
  PRIMARY KEY (`diary_id`),
  KEY `diary_id` (`diary_id`),
  KEY `site_id` (`site_id`),
  KEY `user_id` (`user_id`),
  KEY `FK_d_diary_catalog` (`catalog_id`),
  KEY `FK_d_diary_sys_type` (`type_id`),
  CONSTRAINT `FK_d_diary_catalog` FOREIGN KEY (`catalog_id`) REFERENCES `d_catalog` (`catalog_id`),
  CONSTRAINT `FK_d_diary_site` FOREIGN KEY (`site_id`) REFERENCES `b_site` (`site_id`),
  CONSTRAINT `FK_d_diary_sys_type` FOREIGN KEY (`type_id`) REFERENCES `sys_type` (`type_id`),
  CONSTRAINT `FK_d_diary_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `d_favorite`
-- ----------------------------
DROP TABLE IF EXISTS `d_favorite`;
CREATE TABLE `d_favorite` (
  `dfavorite_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `diary_id` int(11) NOT NULL COMMENT '收藏日记id',
  `user_id` int(11) NOT NULL COMMENT '收藏人id',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端信息',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户单ip',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` smallint(6) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`dfavorite_id`),
  KEY `dfavorite_id` (`dfavorite_id`),
  KEY `diary_id` (`diary_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_d_favorite_diary` FOREIGN KEY (`diary_id`) REFERENCES `d_diary` (`diary_id`),
  CONSTRAINT `FK_d_favorite_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `d_reply1`
-- ----------------------------
DROP TABLE IF EXISTS `d_reply1`;
CREATE TABLE `d_reply1` (
  `dreply_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `diary_id` int(11) NOT NULL COMMENT '日记id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `owner_only` smallint(6) DEFAULT NULL COMMENT '是否允许所有人查看',
  `write_time` datetime DEFAULT NULL COMMENT '发表时间',
  `status` smallint(6) DEFAULT NULL COMMENT '状态 1 有效；0删除',
  PRIMARY KEY (`dreply_id`),
  KEY `dreply_id` (`dreply_id`),
  KEY `diary_id` (`diary_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_d_reply1_diary` FOREIGN KEY (`diary_id`) REFERENCES `d_diary` (`diary_id`),
  CONSTRAINT `FK_d_reply1_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `d_reply2`
-- ----------------------------
DROP TABLE IF EXISTS `d_reply2`;
CREATE TABLE `d_reply2` (
  `dreply1_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论1id',
  `dreply_id` int(11) NOT NULL COMMENT '评论id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `commentator` int(11) NOT NULL COMMENT '被评论人',
  `content` text NOT NULL COMMENT '内容',
  `client_angent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `reply_time` datetime DEFAULT NULL COMMENT '发表时间',
  `reply_id` int(11) DEFAULT NULL COMMENT '引用id',
  `sort_order` smallint(6) DEFAULT NULL COMMENT '排列序号',
  `ext1` int(11) DEFAULT NULL COMMENT '扩展1',
  `ext2` int(11) DEFAULT NULL COMMENT '扩展2',
  `ext3` varchar(1024) DEFAULT NULL COMMENT '扩展3',
  `ext4` varchar(1024) DEFAULT NULL COMMENT '扩展4',
  PRIMARY KEY (`dreply1_id`),
  KEY `dreply1_id` (`dreply1_id`),
  KEY `dreply_id` (`dreply_id`),
  KEY `user_id` (`user_id`),
  KEY `FK_d_reply2_user1` (`commentator`),
  CONSTRAINT `FK_d_reply2_reply1` FOREIGN KEY (`dreply_id`) REFERENCES `d_reply1` (`dreply_id`),
  CONSTRAINT `FK_d_reply2_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`),
  CONSTRAINT `FK_d_reply2_user1` FOREIGN KEY (`commentator`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `d_upvote`
-- ----------------------------
DROP TABLE IF EXISTS `d_upvote`;
CREATE TABLE `d_upvote` (
  `dupvote_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '点赞id',
  `diary_id` int(11) NOT NULL COMMENT '日记id',
  `user_id` int(11) NOT NULL COMMENT '点赞人id',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型（手机、pc）',
  `time` datetime DEFAULT NULL COMMENT '时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` smallint(6) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`dupvote_id`),
  KEY `dupvote_id` (`dupvote_id`),
  KEY `diary_id` (`diary_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_d_upvote_diary` FOREIGN KEY (`diary_id`) REFERENCES `d_diary` (`diary_id`),
  CONSTRAINT `FK_d_upvote_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `i_access`
-- ----------------------------
DROP TABLE IF EXISTS `i_access`;
CREATE TABLE `i_access` (
  `iaccess_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `image_id` int(11) NOT NULL COMMENT '图片id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`iaccess_id`),
  KEY `iaccess_id` (`iaccess_id`),
  KEY `image_id` (`image_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_i_access_sound` FOREIGN KEY (`image_id`) REFERENCES `i_image` (`image_id`),
  CONSTRAINT `FK_i_access_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `i_album`
-- ----------------------------
DROP TABLE IF EXISTS `i_album`;
CREATE TABLE `i_album` (
  `album_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '相册id',
  `site_id` int(11) DEFAULT NULL COMMENT '空间id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `name` varchar(20) DEFAULT NULL COMMENT '相册名称',
  `description` text COMMENT '描述',
  `ablum_type` int(11) DEFAULT NULL COMMENT '相册类型',
  `ablum_count` int(11) DEFAULT NULL COMMENT '图片数',
  `access_question` varchar(200) DEFAULT NULL COMMENT '访问密码',
  `verifycode` varchar(50) DEFAULT NULL COMMENT '访问密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `sort_order` smallint(6) DEFAULT NULL COMMENT '排列顺序',
  `ext1` int(11) DEFAULT NULL,
  `ext2` int(11) DEFAULT NULL,
  `ext3` varchar(1024) DEFAULT NULL,
  `ext4` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`album_id`),
  KEY `album_id` (`album_id`),
  KEY `site_id` (`site_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_i_album_site` FOREIGN KEY (`site_id`) REFERENCES `b_site` (`site_id`),
  CONSTRAINT `FK_i_album_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `i_image`
-- ----------------------------
DROP TABLE IF EXISTS `i_image`;
CREATE TABLE `i_image` (
  `image_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '声音id',
  `album_id` int(11) NOT NULL COMMENT '相册id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `site_id` int(11) DEFAULT NULL COMMENT '网站id',
  `name` varchar(20) DEFAULT NULL COMMENT '原图名字',
  `content` varchar(1024) DEFAULT NULL COMMENT '介绍',
  `file_name` varchar(100) DEFAULT NULL COMMENT '图片名字',
  `file_path` varchar(256) DEFAULT NULL COMMENT '文件路径',
  `url` varchar(256) DEFAULT NULL COMMENT '下载URL',
  `preview_url` varchar(256) DEFAULT NULL COMMENT '浏览图url',
  `tag` varchar(100) DEFAULT NULL COMMENT '标签',
  `time` datetime DEFAULT NULL COMMENT '上传时间',
  `year` smallint(6) DEFAULT NULL COMMENT '年份',
  `mount` smallint(6) DEFAULT NULL COMMENT '上传月份',
  `day` smallint(6) DEFAULT NULL COMMENT '上传日',
  `width` smallint(6) DEFAULT NULL COMMENT '宽度',
  `height` smallint(6) DEFAULT NULL COMMENT '高度',
  `image_type` int(11) DEFAULT NULL COMMENT '图片类型',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `status` smallint(6) DEFAULT NULL COMMENT '状态',
  `reply_count` int(11) DEFAULT NULL COMMENT '评论次数',
  `view_count` int(11) DEFAULT NULL COMMENT '浏览次数',
  `download_count` int(11) DEFAULT NULL COMMENT '下载次数',
  `ext1` int(11) DEFAULT NULL,
  `ext2` int(11) DEFAULT NULL,
  `ext3` varchar(1024) DEFAULT NULL,
  `ext4` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `image_id` (`image_id`),
  KEY `album_id` (`album_id`),
  KEY `user_id` (`user_id`),
  KEY `site_id` (`site_id`),
  CONSTRAINT `FK_i_image_album` FOREIGN KEY (`album_id`) REFERENCES `i_album` (`album_id`),
  CONSTRAINT `FK_i_image_site` FOREIGN KEY (`site_id`) REFERENCES `b_site` (`site_id`),
  CONSTRAINT `FK_i_image_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `i_reply1`
-- ----------------------------
DROP TABLE IF EXISTS `i_reply1`;
CREATE TABLE `i_reply1` (
  `ireply_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `image_id` int(11) NOT NULL COMMENT '图片id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `owner_only` smallint(6) DEFAULT NULL COMMENT '是否允许所有人查看0-允许，1不允许',
  `write_time` datetime DEFAULT NULL COMMENT '发布时间',
  `status` smallint(6) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`ireply_id`),
  KEY `ireply_id` (`ireply_id`),
  KEY `image_id` (`image_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_i_reply1_image` FOREIGN KEY (`image_id`) REFERENCES `i_image` (`image_id`),
  CONSTRAINT `FK_i_reply1_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `i_reply2`
-- ----------------------------
DROP TABLE IF EXISTS `i_reply2`;
CREATE TABLE `i_reply2` (
  `ireply1_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论1-id',
  `ireply_id` int(11) NOT NULL COMMENT '评论编号',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `commentator` int(11) NOT NULL COMMENT '被评论人id',
  `content` text NOT NULL COMMENT '评论内容',
  `client_angent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `reply_time` datetime DEFAULT NULL COMMENT '发布时间',
  `reply_id` datetime DEFAULT NULL,
  `sort_order` smallint(6) DEFAULT NULL COMMENT '排列顺序',
  `ext1` int(11) DEFAULT NULL,
  `ext2` int(11) DEFAULT NULL,
  `ext3` varchar(1024) DEFAULT NULL,
  `ext4` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`ireply1_id`),
  KEY `ireply1_id` (`ireply1_id`),
  KEY `ireply_id` (`ireply_id`),
  KEY `user_id` (`user_id`),
  KEY `FK_i_reply2_user1` (`commentator`),
  CONSTRAINT `FK_i_reply2_ireply1` FOREIGN KEY (`ireply_id`) REFERENCES `i_reply1` (`ireply_id`),
  CONSTRAINT `FK_i_reply2_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`),
  CONSTRAINT `FK_i_reply2_user1` FOREIGN KEY (`commentator`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `i_upvote`
-- ----------------------------
DROP TABLE IF EXISTS `i_upvote`;
CREATE TABLE `i_upvote` (
  `iupvote_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '点赞id',
  `image_id` int(11) NOT NULL COMMENT '图片id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `time` datetime DEFAULT NULL COMMENT '点赞时间',
  `status` smallint(6) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`iupvote_id`),
  KEY `iupvote_id` (`iupvote_id`),
  KEY `image_id` (`image_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_i_upvote_image` FOREIGN KEY (`image_id`) REFERENCES `i_image` (`image_id`),
  CONSTRAINT `FK_i_upvote_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_affiche`
-- ----------------------------
DROP TABLE IF EXISTS `m_affiche`;
CREATE TABLE `m_affiche` (
  `affiche_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '广告id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `title` varchar(20) DEFAULT NULL COMMENT '标题id',
  `content` varchar(1024) DEFAULT NULL COMMENT '内容id',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `expire_time` datetime DEFAULT NULL COMMENT '有效时间',
  `status` smallint(6) DEFAULT NULL COMMENT '状态id',
  PRIMARY KEY (`affiche_id`),
  KEY `affiche_id` (`affiche_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_m_affiche_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_guestbook`
-- ----------------------------
DROP TABLE IF EXISTS `m_guestbook`;
CREATE TABLE `m_guestbook` (
  `guestbook_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `site_id` int(11) DEFAULT NULL COMMENT '空间id',
  `user_id` int(11) NOT NULL COMMENT '用户id（被访问人)',
  `visitor_id` int(11) NOT NULL COMMENT '访客id',
  `content` varchar(1024) NOT NULL COMMENT '内容',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `time` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`guestbook_id`),
  KEY `guestbook_id` (`guestbook_id`),
  KEY `site_id` (`site_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_m_guestbook_site` FOREIGN KEY (`site_id`) REFERENCES `b_site` (`site_id`),
  CONSTRAINT `FK_m_guestbook_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_message`
-- ----------------------------
DROP TABLE IF EXISTS `m_message`;
CREATE TABLE `m_message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '信息id',
  `user_id` int(11) NOT NULL COMMENT '写信息人id',
  `receive_id` int(11) NOT NULL COMMENT '接收人id',
  `content` varchar(1024) DEFAULT NULL COMMENT '内容',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `expire_time` datetime DEFAULT NULL COMMENT '有效时间期限',
  `read_time` datetime DEFAULT NULL COMMENT '阅读时间',
  `status` smallint(6) DEFAULT NULL COMMENT '状态',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  PRIMARY KEY (`message_id`),
  KEY `message_id` (`message_id`),
  KEY `user_id` (`user_id`),
  KEY `receive_id` (`receive_id`),
  CONSTRAINT `FK_m_message_user1` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`),
  CONSTRAINT `FK_m_message_user2` FOREIGN KEY (`receive_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `m_reply`
-- ----------------------------
DROP TABLE IF EXISTS `m_reply`;
CREATE TABLE `m_reply` (
  `mreply_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言回复id',
  `guestbook_id` int(11) NOT NULL COMMENT '留言id',
  `user_id` int(11) NOT NULL COMMENT '评论人id',
  `content` varchar(1024) DEFAULT NULL COMMENT '评论内容',
  `time` datetime DEFAULT NULL COMMENT '时间',
  `reply_id` int(11) DEFAULT NULL COMMENT '引用id',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  PRIMARY KEY (`mreply_id`),
  KEY `mreply_id` (`mreply_id`),
  KEY `guestbook_id` (`guestbook_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_m_reply_guestbook` FOREIGN KEY (`guestbook_id`) REFERENCES `m_guestbook` (`guestbook_id`),
  CONSTRAINT `FK_m_reply_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_blocked_ip`
-- ----------------------------
DROP TABLE IF EXISTS `sys_blocked_ip`;
CREATE TABLE `sys_blocked_ip` (
  `blockedip_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `ip` varchar(50) DEFAULT NULL COMMENT 'ip地址',
  `ipmask` varchar(50) DEFAULT NULL COMMENT 'ip掩码',
  `blocked_type` int(11) DEFAULT NULL COMMENT '阻止方式',
  `time` datetime DEFAULT NULL COMMENT '时间',
  `status` smallint(6) DEFAULT NULL COMMENT '状态',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`blockedip_id`),
  KEY `blockedip_id` (`blockedip_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_city`
-- ----------------------------
DROP TABLE IF EXISTS `sys_city`;
CREATE TABLE `sys_city` (
  `city_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '城市id',
  `province_id` int(11) NOT NULL COMMENT '省id',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `description` text COMMENT '介绍',
  PRIMARY KEY (`city_id`),
  KEY `city_id` (`city_id`),
  KEY `province_id` (`province_id`),
  CONSTRAINT `FK_sys_city_province` FOREIGN KEY (`province_id`) REFERENCES `sys_province` (`province_id`)
) ENGINE=InnoDB AUTO_INCREMENT=438 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_college`
-- ----------------------------
DROP TABLE IF EXISTS `sys_college`;
CREATE TABLE `sys_college` (
  `college_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学院id',
  `school_id` int(11) DEFAULT NULL COMMENT '学院id',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `description` text COMMENT '介绍',
  PRIMARY KEY (`college_id`),
  KEY `college_id` (`college_id`),
  KEY `school_id` (`school_id`),
  CONSTRAINT `FK_sys_college_school` FOREIGN KEY (`school_id`) REFERENCES `sys_school` (`school_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_config`
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `config_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '配置id',
  `config_name` varchar(50) DEFAULT NULL COMMENT '配置key',
  `value` varchar(50) DEFAULT NULL COMMENT '配置参数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`config_id`),
  KEY `config_id` (`config_id`),
  KEY `config_name` (`config_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_illegal`
-- ----------------------------
DROP TABLE IF EXISTS `sys_illegal`;
CREATE TABLE `sys_illegal` (
  `illegal_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `word` varchar(10) NOT NULL COMMENT '非法词',
  `newword` varchar(10) DEFAULT NULL COMMENT '用于替换的字符',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `nullify` tinyint(4) DEFAULT NULL COMMENT '作废标志  1启用 0：作废',
  PRIMARY KEY (`illegal_id`),
  UNIQUE KEY `word` (`word`),
  UNIQUE KEY `UK_pwm3bqcmr2wwmg1ck40u5qu80` (`word`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_invitation_code`
-- ----------------------------
DROP TABLE IF EXISTS `sys_invitation_code`;
CREATE TABLE `sys_invitation_code` (
  `code_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '邀请码id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `wxs_id` int(11) NOT NULL COMMENT '文学社id',
  `code` varchar(16) DEFAULT NULL COMMENT '邀请码',
  `reg_time` datetime DEFAULT NULL COMMENT '注册时间',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `status` smallint(6) DEFAULT NULL COMMENT '状态',
  `flag` smallint(6) DEFAULT NULL COMMENT '标志',
  PRIMARY KEY (`code_id`),
  KEY `code_id` (`code_id`),
  KEY `wxs_id` (`wxs_id`),
  KEY `FK_sys_invitation_code_user` (`user_id`),
  CONSTRAINT `FK_sys_invitation_code_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`),
  CONSTRAINT `FK_sys_invitation_code_wxs` FOREIGN KEY (`wxs_id`) REFERENCES `w_wxs` (`wxs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_link`
-- ----------------------------
DROP TABLE IF EXISTS `sys_link`;
CREATE TABLE `sys_link` (
  `link_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '链接id',
  `name` varchar(10) DEFAULT NULL COMMENT '链接名',
  `link_url` varchar(200) DEFAULT NULL COMMENT '链接url',
  `link_type` int(11) DEFAULT NULL COMMENT '链接类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` smallint(6) DEFAULT NULL COMMENT '状态',
  `sort_order` smallint(6) DEFAULT NULL COMMENT '排列顺序',
  PRIMARY KEY (`link_id`),
  KEY `link_id` (`link_id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_login_record`
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_record`;
CREATE TABLE `sys_login_record` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  PRIMARY KEY (`record_id`),
  KEY `record_id` (`record_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_sys_login_record_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_province`
-- ----------------------------
DROP TABLE IF EXISTS `sys_province`;
CREATE TABLE `sys_province` (
  `province_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '省id',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `description` text COMMENT '介绍',
  PRIMARY KEY (`province_id`),
  KEY `province_id` (`province_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_rank`
-- ----------------------------
DROP TABLE IF EXISTS `sys_rank`;
CREATE TABLE `sys_rank` (
  `rank_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '等级记录id',
  `name` varchar(20) DEFAULT NULL COMMENT '等级名称',
  `rank_high` int(11) NOT NULL COMMENT '分数上限',
  `rank_low` int(11) NOT NULL COMMENT '分数下限',
  `rank_logo` varchar(200) DEFAULT NULL COMMENT '等级logo',
  `time` datetime DEFAULT NULL COMMENT '创建时间',
  `ext1` int(11) DEFAULT NULL,
  `ext2` int(11) DEFAULT NULL,
  `ext3` varchar(1024) DEFAULT NULL,
  `ext4` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`rank_id`),
  KEY `rank_id` (`rank_id`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '配置id',
  `name` varchar(20) DEFAULT NULL COMMENT '配置key',
  `value` varchar(36) DEFAULT NULL COMMENT '配置参数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`role_id`),
  KEY `role_id` (`role_id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_school`
-- ----------------------------
DROP TABLE IF EXISTS `sys_school`;
CREATE TABLE `sys_school` (
  `school_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学校id',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `description` text COMMENT '描述',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `province_id` int(11) DEFAULT NULL COMMENT '省份id',
  `city_id` int(11) DEFAULT NULL COMMENT '城市id',
  `post` smallint(6) DEFAULT NULL COMMENT '邮编',
  PRIMARY KEY (`school_id`),
  KEY `school_id` (`school_id`),
  KEY `FK_sys_school_province` (`province_id`),
  KEY `FK_sys_school_city` (`city_id`),
  CONSTRAINT `FK_sys_school_city` FOREIGN KEY (`city_id`) REFERENCES `sys_city` (`city_id`),
  CONSTRAINT `FK_sys_school_province` FOREIGN KEY (`province_id`) REFERENCES `sys_province` (`province_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_style`
-- ----------------------------
DROP TABLE IF EXISTS `sys_style`;
CREATE TABLE `sys_style` (
  `style_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '样式id',
  `name` varchar(20) DEFAULT NULL COMMENT '样式名称',
  `path` varchar(256) DEFAULT NULL COMMENT '样式类型',
  `status` smallint(6) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创立时间',
  `cover` varchar(50) DEFAULT NULL COMMENT '预览图',
  `ext1` int(11) DEFAULT NULL,
  `ext2` int(11) DEFAULT NULL,
  `ext3` varchar(1024) DEFAULT NULL,
  `ext4` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`style_id`),
  KEY `style_id` (`style_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_topic`
-- ----------------------------
DROP TABLE IF EXISTS `sys_topic`;
CREATE TABLE `sys_topic` (
  `topic_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主题ip',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `topic_key` varchar(20) DEFAULT NULL COMMENT '主题key',
  `description` text COMMENT '介绍',
  `topic_index` int(11) DEFAULT NULL COMMENT '主题索引',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `ext1` int(11) DEFAULT NULL,
  `ext2` int(11) DEFAULT NULL,
  `ext3` varchar(1024) DEFAULT NULL,
  `ext4` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`topic_id`),
  KEY `topic_id` (`topic_id`),
  KEY `user_id` (`user_id`),
  KEY `topic_key` (`topic_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_type`
-- ----------------------------
DROP TABLE IF EXISTS `sys_type`;
CREATE TABLE `sys_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `name` varchar(20) DEFAULT NULL COMMENT '配置key',
  `value` varchar(36) DEFAULT NULL COMMENT '配置参数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级目录id',
  `ext1` int(11) DEFAULT NULL COMMENT '扩展字段1',
  `ext2` int(11) DEFAULT NULL COMMENT '扩展字段2',
  `ext3` varchar(1024) DEFAULT NULL COMMENT '扩展字段3',
  `ext4` varchar(1024) DEFAULT NULL COMMENT '扩展字段4',
  PRIMARY KEY (`type_id`),
  KEY `type_id` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `s_access`
-- ----------------------------
DROP TABLE IF EXISTS `s_access`;
CREATE TABLE `s_access` (
  `saccess_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `sound_id` int(11) NOT NULL COMMENT '声音id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `time` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`saccess_id`),
  KEY `saccess_id` (`saccess_id`),
  KEY `sound_id` (`sound_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_s_access_sound` FOREIGN KEY (`sound_id`) REFERENCES `s_sound` (`sound_id`),
  CONSTRAINT `FK_s_access_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `s_box`
-- ----------------------------
DROP TABLE IF EXISTS `s_box`;
CREATE TABLE `s_box` (
  `box_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '盒子id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `name` varchar(20) DEFAULT NULL COMMENT '名字',
  `description` text COMMENT '说明',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `sound_count` int(11) DEFAULT NULL COMMENT '数量',
  `sort_order` smallint(6) DEFAULT NULL COMMENT '排列顺序',
  `ext1` int(11) DEFAULT NULL,
  `ext2` int(11) DEFAULT NULL,
  `ext3` varchar(1024) DEFAULT NULL,
  `ext4` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`box_id`),
  KEY `box_id` (`box_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_s_box_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `s_reply1`
-- ----------------------------
DROP TABLE IF EXISTS `s_reply1`;
CREATE TABLE `s_reply1` (
  `sreply_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `sound_id` int(11) NOT NULL COMMENT '声音id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `owner_only` smallint(6) DEFAULT NULL COMMENT '是否值允许所有人查看（0-允许，1-不允许）',
  `write_time` datetime DEFAULT NULL COMMENT '评论时间',
  `status` smallint(6) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`sreply_id`),
  KEY `sreply_id` (`sreply_id`),
  KEY `sound_id` (`sound_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_s_reply1_sound` FOREIGN KEY (`sound_id`) REFERENCES `s_sound` (`sound_id`),
  CONSTRAINT `FK_s_reply1_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `s_reply2`
-- ----------------------------
DROP TABLE IF EXISTS `s_reply2`;
CREATE TABLE `s_reply2` (
  `sreply1_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论1-id',
  `sreply_id` int(11) NOT NULL COMMENT '评论id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `commentator` int(11) NOT NULL COMMENT '被评论人id（被引用人）',
  `content` text NOT NULL COMMENT '内容',
  `client_angent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `reply_time` datetime DEFAULT NULL COMMENT '发布时间',
  `reply_id` datetime DEFAULT NULL COMMENT '引用id',
  `sort_order` smallint(6) DEFAULT NULL COMMENT '排列顺序',
  `ext1` int(11) DEFAULT NULL,
  `ext2` int(11) DEFAULT NULL,
  `ext3` varchar(1024) DEFAULT NULL,
  `ext4` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`sreply1_id`),
  KEY `sreply1_id` (`sreply1_id`),
  KEY `sreply_id` (`sreply_id`),
  KEY `user_id` (`user_id`),
  KEY `FK_s_reply2_user1` (`commentator`),
  CONSTRAINT `FK_s_reply2_sreply1` FOREIGN KEY (`sreply_id`) REFERENCES `s_reply1` (`sreply_id`),
  CONSTRAINT `FK_s_reply2_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`),
  CONSTRAINT `FK_s_reply2_user1` FOREIGN KEY (`commentator`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `s_sound`
-- ----------------------------
DROP TABLE IF EXISTS `s_sound`;
CREATE TABLE `s_sound` (
  `sound_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '声音id',
  `box_id` int(11) NOT NULL COMMENT '声音盒子id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `title` varchar(20) DEFAULT NULL COMMENT '标题',
  `content` varchar(1024) DEFAULT NULL COMMENT '内容',
  `url` varchar(256) DEFAULT NULL COMMENT '声音url',
  `file_name` varchar(100) DEFAULT NULL COMMENT '文件名字',
  `file_path` varchar(200) DEFAULT NULL COMMENT '文件路径',
  `time` datetime DEFAULT NULL COMMENT '时间',
  `status` smallint(6) DEFAULT NULL COMMENT '状态',
  `view_count` int(11) DEFAULT NULL COMMENT '试听次数',
  `download_count` int(11) DEFAULT NULL COMMENT '下载次数',
  `ext1` int(11) DEFAULT NULL,
  `ext2` int(11) DEFAULT NULL,
  `ext3` varchar(1024) DEFAULT NULL,
  `ext4` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`sound_id`),
  KEY `sound_id` (`sound_id`),
  KEY `box_id` (`box_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_s_sound_box` FOREIGN KEY (`box_id`) REFERENCES `s_box` (`box_id`),
  CONSTRAINT `FK_s_sound_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `s_upvote`
-- ----------------------------
DROP TABLE IF EXISTS `s_upvote`;
CREATE TABLE `s_upvote` (
  `supvote_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '点赞id',
  `sound_id` int(11) NOT NULL COMMENT '声音id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `time` datetime DEFAULT NULL COMMENT '发布时间',
  `status` smallint(6) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`supvote_id`),
  KEY `supvote_id` (`supvote_id`),
  KEY `sound_id` (`sound_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_s_upvote_sound` FOREIGN KEY (`sound_id`) REFERENCES `s_sound` (`sound_id`),
  CONSTRAINT `FK_s_upvote_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `u_book`
-- ----------------------------
DROP TABLE IF EXISTS `u_book`;
CREATE TABLE `u_book` (
  `ubook_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'bookid',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `name` int(11) NOT NULL COMMENT '名字',
  `book_detaik` text COMMENT '内容简介',
  `cover` varchar(50) DEFAULT NULL COMMENT '封面',
  `time` datetime DEFAULT NULL COMMENT '成立时间',
  `ext1` int(11) DEFAULT NULL COMMENT '扩展1',
  `ext2` int(11) DEFAULT NULL COMMENT '扩展2',
  `ext3` varchar(1024) DEFAULT NULL COMMENT '扩展3',
  `ext4` varchar(1024) DEFAULT NULL COMMENT '扩展4',
  PRIMARY KEY (`ubook_id`),
  KEY `ubook_id` (`ubook_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_u_book_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `u_book_catalog`
-- ----------------------------
DROP TABLE IF EXISTS `u_book_catalog`;
CREATE TABLE `u_book_catalog` (
  `catalog_id` int(11) NOT NULL AUTO_INCREMENT,
  `ubook_id` int(11) NOT NULL,
  `diary_id` int(11) DEFAULT NULL,
  `jointime` datetime DEFAULT NULL,
  `ext1` int(11) DEFAULT NULL,
  `ext2` int(11) DEFAULT NULL,
  `ext3` varchar(1024) DEFAULT NULL,
  `ext4` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`catalog_id`),
  KEY `book_id` (`ubook_id`),
  KEY `diary_id` (`diary_id`),
  CONSTRAINT `FK_u_book_catalog_book` FOREIGN KEY (`ubook_id`) REFERENCES `u_book` (`ubook_id`),
  CONSTRAINT `FK_u_book_catalog_diary` FOREIGN KEY (`diary_id`) REFERENCES `d_diary` (`diary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `u_friend`
-- ----------------------------
DROP TABLE IF EXISTS `u_friend`;
CREATE TABLE `u_friend` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `friend_id` int(11) NOT NULL COMMENT '朋友id',
  `friend_type` int(11) NOT NULL COMMENT '朋友类别',
  `friend_role` int(11) DEFAULT NULL COMMENT '朋友权限',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `group_id` int(11) NOT NULL COMMENT '所属分组',
  `status` smallint(6) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `friend_id` (`friend_id`),
  KEY `FK_u_fenri_f_group` (`group_id`),
  CONSTRAINT `FK_u_fenri_f_group` FOREIGN KEY (`group_id`) REFERENCES `u_friend_group` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `u_friend_group`
-- ----------------------------
DROP TABLE IF EXISTS `u_friend_group`;
CREATE TABLE `u_friend_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分组ip',
  `name` varchar(10) NOT NULL COMMENT '分组名称',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `group_type` int(11) DEFAULT NULL COMMENT '分组类型',
  `group_count` int(11) DEFAULT NULL COMMENT '好友数',
  PRIMARY KEY (`group_id`),
  KEY `group_id` (`group_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_u_friend_group_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `u_sign`
-- ----------------------------
DROP TABLE IF EXISTS `u_sign`;
CREATE TABLE `u_sign` (
  `sign_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '签名编号',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `content` varchar(140) NOT NULL COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` smallint(6) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`sign_id`),
  KEY `sign_id` (`sign_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_u_sign_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `u_user`
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `site_id` int(11) DEFAULT NULL COMMENT '空间编号',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `nickname` varchar(20) NOT NULL COMMENT '昵称',
  `role_id` int(11) NOT NULL COMMENT '用户角色',
  `sex` smallint(6) DEFAULT NULL COMMENT '性别',
  `birth` datetime DEFAULT NULL COMMENT '生日',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `homepage` varchar(100) DEFAULT NULL COMMENT '空间主页',
  `qq` varchar(16) DEFAULT NULL COMMENT 'QQ',
  `blood` varchar(5) DEFAULT NULL COMMENT '血型',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机',
  `province_id` int(11) DEFAULT NULL COMMENT '省',
  `city_id` int(11) DEFAULT NULL COMMENT '城市',
  `school_id` int(11) DEFAULT NULL COMMENT '学校',
  `college_id` int(11) DEFAULT NULL COMMENT '学院',
  `class` varchar(10) DEFAULT NULL COMMENT '班级',
  `portrait` varchar(100) DEFAULT NULL COMMENT '头像',
  `sign_id` int(11) DEFAULT NULL COMMENT '签名档',
  `regtime` datetime DEFAULT NULL COMMENT '注册时间',
  `last_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_ip` varchar(48) DEFAULT NULL COMMENT '最后登录ip',
  `keep_day` smallint(6) DEFAULT NULL COMMENT '保留时间',
  `online_status` smallint(6) DEFAULT NULL COMMENT '在线状态',
  `rank_id` int(11) DEFAULT '1' COMMENT '用户等级',
  `rank` int(11) DEFAULT '0' COMMENT '积分',
  `article_count` int(11) DEFAULT '0' COMMENT '文章数',
  `article_reply_count` int(11) DEFAULT '0' COMMENT '评论数',
  `topic_count` int(11) DEFAULT '0' COMMENT '帖子数',
  `topic_reply_count` int(11) DEFAULT '0' COMMENT '回帖数',
  `image_count` int(11) DEFAULT '0' COMMENT '照片数',
  `image_reply_count` int(11) DEFAULT '0' COMMENT '图片回帖数',
  `guestbook_count` int(11) DEFAULT '0' COMMENT '留言数',
  `tag_count` int(11) DEFAULT '0' COMMENT '收藏数',
  `fans` int(11) DEFAULT '0' COMMENT '粉丝数',
  `follows` int(11) DEFAULT '0' COMMENT '关注数',
  `popedom` int(11) DEFAULT NULL COMMENT '权限',
  `code_id` int(11) DEFAULT NULL COMMENT '邀请码',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `UK_9ujn2ebi367np35n74jlg9yl9` (`username`),
  KEY `user_id` (`user_id`),
  KEY `FK_u_user_rank` (`rank_id`),
  KEY `FK_u_user_sign` (`sign_id`),
  KEY `FK_u_user_site` (`site_id`),
  CONSTRAINT `FK_u_user_rank` FOREIGN KEY (`rank_id`) REFERENCES `sys_rank` (`rank_id`),
  CONSTRAINT `FK_u_user_sign` FOREIGN KEY (`sign_id`) REFERENCES `u_sign` (`sign_id`),
  CONSTRAINT `FK_u_user_site` FOREIGN KEY (`site_id`) REFERENCES `b_site` (`site_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `v_access`
-- ----------------------------
DROP TABLE IF EXISTS `v_access`;
CREATE TABLE `v_access` (
  `vaccess_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `vote_id` int(11) NOT NULL COMMENT '投票id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`vaccess_id`),
  KEY `vaccess_id` (`vaccess_id`),
  KEY `vote_id` (`vote_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_v_access_sound` FOREIGN KEY (`vote_id`) REFERENCES `v_vote` (`vote_id`),
  CONSTRAINT `FK_v_access_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `v_count`
-- ----------------------------
DROP TABLE IF EXISTS `v_count`;
CREATE TABLE `v_count` (
  `vcount_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '计数id',
  `vote_id` int(11) NOT NULL COMMENT '投票id',
  `item_id` int(11) NOT NULL COMMENT '选项id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `time` datetime DEFAULT NULL COMMENT '发布时间',
  `ext1` int(11) DEFAULT NULL,
  `ext2` int(11) DEFAULT NULL,
  `ext3` varchar(1024) DEFAULT NULL,
  `ext4` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`vcount_id`),
  KEY `vcount_id` (`vcount_id`),
  KEY `vote_id` (`vote_id`),
  KEY `item_id` (`item_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_v_count_item` FOREIGN KEY (`item_id`) REFERENCES `v_item` (`item_id`),
  CONSTRAINT `FK_v_count_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`),
  CONSTRAINT `FK_v_count_vote` FOREIGN KEY (`vote_id`) REFERENCES `v_vote` (`vote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `v_item`
-- ----------------------------
DROP TABLE IF EXISTS `v_item`;
CREATE TABLE `v_item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '选项id',
  `vote_id` int(11) NOT NULL COMMENT '主题id',
  `content` varchar(1024) NOT NULL COMMENT '内容id',
  `create_time` datetime DEFAULT NULL COMMENT '创立时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `count` int(11) DEFAULT NULL COMMENT '计数',
  `sort_order` smallint(6) DEFAULT NULL COMMENT '排序',
  `ext1` int(11) DEFAULT NULL,
  `ext2` int(11) DEFAULT NULL,
  `ext3` varchar(1024) DEFAULT NULL,
  `ext4` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `item_id` (`item_id`),
  KEY `vote_id` (`vote_id`),
  CONSTRAINT `FK_v_item_vote` FOREIGN KEY (`vote_id`) REFERENCES `v_vote` (`vote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `v_reply1`
-- ----------------------------
DROP TABLE IF EXISTS `v_reply1`;
CREATE TABLE `v_reply1` (
  `vreply_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `vote_id` int(11) NOT NULL COMMENT '主题id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `owner_only` smallint(6) DEFAULT NULL COMMENT '是否允许所有人查看',
  `write_time` datetime DEFAULT NULL COMMENT '发表时间',
  `status` smallint(6) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`vreply_id`),
  KEY `vreply_id` (`vreply_id`),
  KEY `vote_id` (`vote_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_v_reply1_image` FOREIGN KEY (`vote_id`) REFERENCES `v_vote` (`vote_id`),
  CONSTRAINT `FK_v_reply1_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `v_reply2`
-- ----------------------------
DROP TABLE IF EXISTS `v_reply2`;
CREATE TABLE `v_reply2` (
  `vreply1_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id-1',
  `vreply_id` int(11) NOT NULL COMMENT '评论id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `commentator` int(11) NOT NULL COMMENT '被评论人id',
  `content` text NOT NULL COMMENT '内容',
  `client_angent` varchar(250) DEFAULT NULL COMMENT '浏览器UA',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `reply_time` datetime DEFAULT NULL COMMENT '发布时间',
  `reply_id` datetime DEFAULT NULL COMMENT '引用id',
  `sort_order` smallint(6) DEFAULT NULL COMMENT '排列顺序',
  `ext1` int(11) DEFAULT NULL,
  `ext2` int(11) DEFAULT NULL,
  `ext3` varchar(1024) DEFAULT NULL,
  `ext4` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`vreply1_id`),
  KEY `vreply1_id` (`vreply1_id`),
  KEY `vreply_id` (`vreply_id`),
  KEY `user_id` (`user_id`),
  KEY `FK_v_reply2_user1` (`commentator`),
  CONSTRAINT `FK_v_reply2_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`),
  CONSTRAINT `FK_v_reply2_user1` FOREIGN KEY (`commentator`) REFERENCES `u_user` (`user_id`),
  CONSTRAINT `FK_v_reply2_vreply1` FOREIGN KEY (`vreply_id`) REFERENCES `v_reply1` (`vreply_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `v_upvote`
-- ----------------------------
DROP TABLE IF EXISTS `v_upvote`;
CREATE TABLE `v_upvote` (
  `vupvote_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '点赞id',
  `vote_id` int(11) NOT NULL COMMENT '投票id',
  `user_id` int(11) NOT NULL COMMENT '点赞人id',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端IP',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `time` datetime DEFAULT NULL COMMENT '发布时间',
  `status` smallint(6) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`vupvote_id`),
  KEY `vupvote_id` (`vupvote_id`),
  KEY `vote_id` (`vote_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_v_upvote_image` FOREIGN KEY (`vote_id`) REFERENCES `v_vote` (`vote_id`),
  CONSTRAINT `FK_v_upvote_user` FOREIGN KEY (`vote_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `v_vote`
-- ----------------------------
DROP TABLE IF EXISTS `v_vote`;
CREATE TABLE `v_vote` (
  `vote_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `vconfig_id` int(11) DEFAULT NULL COMMENT '配置id',
  `content` varchar(1024) DEFAULT NULL COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`vote_id`),
  KEY `vote_id` (`vote_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_v_vote_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `w_access`
-- ----------------------------
DROP TABLE IF EXISTS `w_access`;
CREATE TABLE `w_access` (
  `waccess_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `client_agent` varchar(250) DEFAULT NULL,
  `client_ip` varchar(50) DEFAULT NULL,
  `client_type` smallint(6) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`waccess_id`),
  KEY `waccess` (`waccess_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FK_w_access_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `w_book`
-- ----------------------------
DROP TABLE IF EXISTS `w_book`;
CREATE TABLE `w_book` (
  `wbook_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '书id',
  `wxs_id` int(11) NOT NULL COMMENT '文学社id',
  `name` varchar(50) DEFAULT NULL COMMENT '名字',
  `book_dateil` text,
  `cover` varchar(50) DEFAULT NULL COMMENT '封面',
  `create_time` datetime DEFAULT NULL COMMENT '成立时间',
  `ext1` int(11) DEFAULT NULL,
  `ext2` int(11) DEFAULT NULL,
  `ext3` varchar(1024) DEFAULT NULL,
  `ext4` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`wbook_id`),
  KEY `wbook_id` (`wbook_id`),
  KEY `wxs_id` (`wxs_id`),
  CONSTRAINT `FK_w_book_wxs` FOREIGN KEY (`wxs_id`) REFERENCES `w_wxs` (`wxs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `w_book_catalog`
-- ----------------------------
DROP TABLE IF EXISTS `w_book_catalog`;
CREATE TABLE `w_book_catalog` (
  `wcatalog_id` int(11) NOT NULL AUTO_INCREMENT,
  `wbook_id` int(11) NOT NULL,
  `diary_id` int(11) DEFAULT NULL,
  `jointime` datetime DEFAULT NULL,
  `ext1` int(11) DEFAULT NULL,
  `ext2` int(11) DEFAULT NULL,
  `ext3` varchar(1024) DEFAULT NULL,
  `ext4` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`wcatalog_id`),
  KEY `wcatalog_id` (`wcatalog_id`),
  KEY `wbook_id` (`wbook_id`),
  KEY `diary_id` (`diary_id`),
  CONSTRAINT `FK_w_book_catalog_diary` FOREIGN KEY (`diary_id`) REFERENCES `d_diary` (`diary_id`),
  CONSTRAINT `FK_w_book_catalog_wbook` FOREIGN KEY (`wbook_id`) REFERENCES `w_book` (`wbook_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `w_config`
-- ----------------------------
DROP TABLE IF EXISTS `w_config`;
CREATE TABLE `w_config` (
  `wconfig_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '配置id',
  `wxs_id` int(11) NOT NULL COMMENT '文学社id',
  `config_key` varchar(20) DEFAULT NULL COMMENT '配置key',
  `value` varchar(36) DEFAULT NULL COMMENT '配置参数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`wconfig_id`),
  KEY `wconfig_id` (`wconfig_id`),
  KEY `wxs_id` (`wxs_id`),
  CONSTRAINT `FK_w_cofig_wxs` FOREIGN KEY (`wxs_id`) REFERENCES `w_wxs` (`wxs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `w_link`
-- ----------------------------
DROP TABLE IF EXISTS `w_link`;
CREATE TABLE `w_link` (
  `wlink_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '链接id',
  `wxs_id` int(11) NOT NULL COMMENT '文学社id',
  `name` varchar(10) DEFAULT NULL COMMENT '链接名',
  `link_url` varchar(200) DEFAULT NULL COMMENT '链接url',
  `logo` varchar(50) DEFAULT NULL COMMENT 'logo地址',
  `link_type` int(11) DEFAULT NULL COMMENT '链接类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` smallint(6) DEFAULT NULL COMMENT '状态',
  `sort_order` smallint(6) DEFAULT NULL COMMENT '排列顺序',
  `ext1` int(11) DEFAULT NULL,
  `ext2` int(11) DEFAULT NULL,
  `ext3` varchar(1024) DEFAULT NULL,
  `ext4` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`wlink_id`),
  KEY `wlink_id` (`wlink_id`),
  KEY `wxs_id` (`wxs_id`),
  CONSTRAINT `FK_w_link_wxs` FOREIGN KEY (`wxs_id`) REFERENCES `w_wxs` (`wxs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `w_menber`
-- ----------------------------
DROP TABLE IF EXISTS `w_menber`;
CREATE TABLE `w_menber` (
  `menber_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '成员id',
  `wxs_id` int(11) NOT NULL COMMENT '文学社id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `status` smallint(6) DEFAULT NULL COMMENT '状态 0作废,1启用',
  `ext1` int(11) DEFAULT NULL COMMENT '扩展1',
  `ext2` int(11) DEFAULT NULL COMMENT '扩展2',
  `ext3` varchar(1024) DEFAULT NULL COMMENT '扩展3',
  `ext4` varchar(1024) DEFAULT NULL COMMENT '扩展4',
  PRIMARY KEY (`menber_id`),
  KEY `member_id` (`menber_id`),
  KEY `wxs_id` (`wxs_id`),
  KEY `FK_w_menber_u_user` (`user_id`),
  CONSTRAINT `FK_w_menber_u_user` FOREIGN KEY (`user_id`) REFERENCES `u_user` (`user_id`),
  CONSTRAINT `FK_w_menber_w_wxs` FOREIGN KEY (`wxs_id`) REFERENCES `w_wxs` (`wxs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `w_status`
-- ----------------------------
DROP TABLE IF EXISTS `w_status`;
CREATE TABLE `w_status` (
  `wstatus_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '状态id',
  `wxs_id` int(11) NOT NULL COMMENT '文学社id',
  `time` datetime DEFAULT NULL COMMENT '统计时间',
  `uv_count` int(11) DEFAULT NULL COMMENT '用户id',
  `pv_count` int(11) DEFAULT NULL COMMENT '点击次数',
  `type` int(11) DEFAULT NULL COMMENT '访问类型',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`wstatus_id`),
  KEY `wstatus_id` (`wstatus_id`),
  KEY `wxs_id` (`wxs_id`),
  CONSTRAINT `FK_w_status_wxs` FOREIGN KEY (`wxs_id`) REFERENCES `w_wxs` (`wxs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `w_wxs`
-- ----------------------------
DROP TABLE IF EXISTS `w_wxs`;
CREATE TABLE `w_wxs` (
  `wxs_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文学社id',
  `account` varchar(50) NOT NULL COMMENT '账户',
  `password` varchar(48) NOT NULL COMMENT '密码',
  `name` varchar(20) DEFAULT NULL COMMENT '文学社名字',
  `title` varchar(10) DEFAULT NULL COMMENT '唯一的标示 （与空间的url相关）',
  `wxs_url` varchar(100) DEFAULT NULL COMMENT '文学社url',
  `wxs_detail` text COMMENT '文虚设说明',
  `logo` varchar(200) DEFAULT NULL COMMENT 'logo地址',
  `mail` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `style_id` int(11) DEFAULT NULL COMMENT '样式id',
  `school_id` int(11) DEFAULT NULL COMMENT '学校id',
  `flag` smallint(6) DEFAULT NULL COMMENT '空间标志',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_time` datetime DEFAULT NULL COMMENT '最后发帖时间',
  `client_type` smallint(6) DEFAULT NULL COMMENT '客户端类型',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `client_agent` varchar(250) DEFAULT NULL COMMENT '客户端UA',
  `mode` smallint(6) unsigned zerofill DEFAULT NULL COMMENT '权限模式',
  `access_code` varchar(50) DEFAULT NULL COMMENT '访问密码',
  `index_name` varchar(10) DEFAULT NULL COMMENT '个性化首页名',
  `diary_name` varchar(10) DEFAULT NULL COMMENT '个性化日志名',
  `sound_name` varchar(10) DEFAULT NULL COMMENT '个性化相册名',
  `image_name` varchar(10) DEFAULT NULL COMMENT '个性化相册名',
  `site_type` int(11) DEFAULT NULL COMMENT '空间类型',
  `status` smallint(6) DEFAULT NULL COMMENT '状态',
  `ext1` int(11) DEFAULT NULL,
  `ext2` int(11) DEFAULT NULL,
  `ext3` varchar(1024) DEFAULT NULL,
  `ext4` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`wxs_id`),
  KEY `wxs_id` (`wxs_id`),
  KEY `account` (`account`),
  KEY `name` (`name`),
  KEY `FK_w_wxs_school` (`school_id`),
  CONSTRAINT `FK_w_wxs_school` FOREIGN KEY (`school_id`) REFERENCES `sys_school` (`school_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `d_access` VALUES ('1','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 09:47:35','2016-04-17 10:44:27','0'), ('2','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 10:04:28','2016-04-17 10:44:27','0'), ('3','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 10:04:43','2016-04-17 10:44:27','0'), ('4','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 10:04:47','2016-04-17 10:44:27','0'), ('5','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 10:15:57','2016-04-17 10:44:27','0'), ('6','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 10:18:16','2016-04-17 10:44:27','0'), ('7','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 10:19:34','2016-04-17 10:44:27','0'), ('8','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 10:21:43','2016-04-17 10:44:27','0'), ('9','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 10:31:20','2016-04-17 10:44:27','0'), ('10','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 10:32:48','2016-04-17 10:45:17','0'), ('11','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 10:35:44','2016-04-17 10:44:27','0'), ('12','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 10:35:59','2016-04-17 10:44:27','0'), ('13','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 10:38:11','2016-04-17 10:44:27','0'), ('14','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 10:40:56','2016-04-17 10:44:27','0'), ('15','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 10:45:29','2016-04-17 10:44:27','0'), ('16','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 10:50:48','2016-04-17 10:44:27','0'), ('17','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 10:52:31','2016-04-17 10:44:27','0'), ('18','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 14:24:10','2016-04-17 10:44:27','0'), ('19','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 14:25:22','2016-04-17 10:44:27','0'), ('20','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 14:27:30','2016-04-17 10:44:27','0'), ('21','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 14:29:15','2016-04-17 10:44:27','0'), ('22','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 14:29:45','2016-04-17 10:44:27','0'), ('23','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 15:18:26','2016-04-17 10:44:27','0'), ('24','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 15:20:33','2016-04-17 10:44:27','0'), ('25','1','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-16 15:22:14',NULL,'0'), ('26','1','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-17 10:32:21',NULL,'1'), ('27','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-17 10:32:42','2016-04-17 10:44:27','0'), ('28','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-17 10:44:27','2016-04-17 10:45:17','0'), ('29','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-17 10:45:17',NULL,'1'), ('30','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-25 10:30:50','2016-04-25 10:31:03','0'), ('31','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-25 10:31:03','2016-04-25 10:31:07','0'), ('32','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-25 10:31:07','2016-04-25 10:31:12','0'), ('33','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-25 10:31:12','2016-04-25 10:31:22','0'), ('34','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-25 10:31:22','2016-04-25 14:20:06','0'), ('35','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-25 14:20:06','2016-04-25 14:25:00','0'), ('36','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-25 14:25:00','2016-04-25 14:27:10','0'), ('37','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-25 14:27:10','2016-04-25 14:27:31','0'), ('38','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-25 14:27:31','2016-04-25 15:23:06','0'), ('39','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-25 15:23:06','2016-04-26 10:54:39','0'), ('40','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 10:54:39','2016-04-26 11:01:28','0'), ('41','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 11:01:28','2016-04-26 11:48:30','0'), ('42','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 11:48:30','2016-04-26 11:48:33','0'), ('43','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 11:48:33','2016-04-26 11:48:38','0'), ('44','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 11:48:38','2016-04-26 11:48:42','0'), ('45','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 11:48:42','2016-04-26 11:49:14','0'), ('46','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 11:49:14','2016-04-26 14:12:03','0'), ('47','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 14:12:03','2016-04-26 14:34:27','0'), ('48','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 14:34:27','2016-04-26 14:35:11','0'), ('49','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 14:35:11','2016-04-26 14:35:16','0'), ('50','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 14:35:16','2016-04-26 14:35:38','0'), ('51','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 14:35:38','2016-04-26 14:36:34','0'), ('52','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 14:36:34','2016-04-26 14:51:32','0'), ('53','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 14:51:32','2016-04-26 14:53:42','0'), ('54','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 14:53:42','2016-04-26 14:57:13','0'), ('55','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 14:57:13','2016-04-26 14:58:12','0'), ('56','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 14:58:12','2016-04-26 14:58:18','0'), ('57','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 14:58:18','2016-04-26 14:58:22','0'), ('58','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 14:58:22','2016-04-26 15:02:14','0'), ('59','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 15:02:14','2016-04-26 16:36:39','0'), ('60','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 16:36:39','2016-04-26 16:36:47','0'), ('61','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 16:36:47','2016-04-26 16:38:18','0'), ('62','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 16:38:18',NULL,'1'), ('63','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 22:57:27',NULL,'1'), ('64','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 22:58:34',NULL,'1'), ('65','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-26 23:01:51',NULL,'1'), ('66','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0','192.168.1.103','0','2016-04-27 14:28:01',NULL,'1'), ('67','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0','192.168.1.103','0','2016-04-27 15:28:21',NULL,'1'), ('68','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.103','0','2016-04-27 20:25:02',NULL,'1'), ('69','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.43.174','0','2016-05-01 12:02:27',NULL,'1'), ('70','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 09:42:35',NULL,'1'), ('71','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 09:43:00',NULL,'1'), ('72','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 09:46:59',NULL,'1'), ('73','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 09:58:19',NULL,'1'), ('74','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 09:58:32',NULL,'1'), ('75','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 10:25:42',NULL,'1'), ('76','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 10:29:14',NULL,'1'), ('77','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 10:30:19',NULL,'1'), ('78','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 10:31:05',NULL,'1'), ('79','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 10:31:24',NULL,'1'), ('80','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 10:32:32',NULL,'1'), ('81','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 10:33:06',NULL,'1'), ('82','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 10:35:36',NULL,'1'), ('83','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 10:36:05',NULL,'1'), ('84','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 10:36:39',NULL,'1'), ('85','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 10:53:34',NULL,'1'), ('86','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 11:05:39',NULL,'1'), ('87','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 11:17:07',NULL,'1'), ('88','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 14:17:21',NULL,'1'), ('89','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 14:18:21',NULL,'1'), ('90','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 14:19:05',NULL,'1'), ('91','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 14:22:50',NULL,'1'), ('92','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 14:24:26',NULL,'1'), ('93','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 14:27:53',NULL,'1'), ('94','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 14:28:27',NULL,'1'), ('95','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 14:28:52',NULL,'1'), ('96','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 14:32:09',NULL,'1'), ('97','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 14:32:43',NULL,'1'), ('98','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 14:42:22',NULL,'1'), ('99','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 14:43:51',NULL,'1'), ('100','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 14:47:05',NULL,'1');
INSERT INTO `d_access` VALUES ('101','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 15:11:11',NULL,'1'), ('102','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 15:39:10',NULL,'1'), ('103','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 15:41:17',NULL,'1'), ('104','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 15:42:31',NULL,'1'), ('105','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 15:43:03',NULL,'1'), ('106','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 15:45:24',NULL,'1'), ('107','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 15:46:11',NULL,'1'), ('108','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 15:47:54',NULL,'1'), ('109','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 15:49:33',NULL,'1'), ('110','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 15:50:40',NULL,'1'), ('111','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 15:57:23',NULL,'1'), ('112','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 15:57:43',NULL,'1'), ('113','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 15:59:04',NULL,'1'), ('114','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 16:01:33',NULL,'1'), ('115','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 16:32:03',NULL,'1'), ('116','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 16:38:35',NULL,'1'), ('117','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 16:55:34',NULL,'1'), ('118','27','12','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','192.168.1.104','0','2016-05-04 17:01:25',NULL,'1'), ('119','30','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-10 00:00:57',NULL,'1'), ('120','31','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-10 00:05:39',NULL,'1'), ('121','31','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-10 00:06:12',NULL,'1'), ('122','31','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-10 00:06:26',NULL,'1'), ('123','31','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-10 00:08:16',NULL,'1'), ('124','31','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-10 00:09:33',NULL,'1'), ('125','31','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-10 00:09:45',NULL,'1'), ('126','31','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-10 08:35:23',NULL,'1'), ('127','31','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-10 08:40:07',NULL,'1'), ('128','31','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-10 08:40:22',NULL,'1'), ('129','30','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-10 08:50:09',NULL,'1'), ('130','30','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-10 14:24:49',NULL,'1'), ('131','30','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-10 14:29:19',NULL,'1'), ('132','30','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-10 14:30:07',NULL,'1'), ('133','30','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-11 08:56:37',NULL,'1'), ('134','30','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-11 08:57:43',NULL,'1'), ('135','30','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-11 09:03:50',NULL,'1'), ('136','30','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-11 09:05:01',NULL,'1'), ('137','30','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-11 09:12:40',NULL,'1'), ('138','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 15:49:00',NULL,'1'), ('139','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 15:49:07',NULL,'1'), ('140','30','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 15:56:10',NULL,'1'), ('141','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 15:56:14',NULL,'1'), ('142','6','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 15:56:17',NULL,'1'), ('143','5','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 15:56:20',NULL,'1'), ('144','6','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 15:56:25',NULL,'1'), ('145','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 15:56:27',NULL,'1'), ('146','29','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 15:56:29',NULL,'1'), ('147','30','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 15:56:33',NULL,'1'), ('148','30','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 16:16:43',NULL,'1'), ('149','40','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 16:24:34',NULL,'1'), ('150','39','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 16:24:39',NULL,'1'), ('151','38','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 16:24:41',NULL,'1'), ('152','37','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 16:24:43',NULL,'1'), ('153','36','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 16:24:45',NULL,'1'), ('154','35','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 16:24:47',NULL,'1'), ('155','30','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 16:24:49',NULL,'1'), ('156','7','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 16:39:03',NULL,'1'), ('157','38','1','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36','169.254.184.151','0','2016-05-13 16:46:29',NULL,'1'), ('158','35','1','Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko','169.254.184.151','0','2016-05-14 10:57:08',NULL,'1');
INSERT INTO `i_album` VALUES ('1',NULL,NULL,'默认相册',NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `sys_city` VALUES ('1','1','东城区',NULL), ('2','1','西城区',NULL), ('3','1','崇文区',NULL), ('4','1','宣武区',NULL), ('5','1','朝阳区',NULL), ('6','1','丰台区',NULL), ('7','1','石景山区',NULL), ('8','1','海淀区',NULL), ('9','1','门头沟区',NULL), ('10','1','房山区',NULL), ('11','1','通州区',NULL), ('12','1','顺义区',NULL), ('13','1','昌平区',NULL), ('14','1','大兴区',NULL), ('15','1','怀柔区',NULL), ('16','1','平谷区',NULL), ('17','1','密云县',NULL), ('18','1','延庆县',NULL), ('19','2','和平区',NULL), ('20','2','河东区',NULL), ('21','2','河西区',NULL), ('22','2','南开区',NULL), ('23','2','河北区',NULL), ('24','2','红桥区',NULL), ('25','2','塘沽区',NULL), ('26','2','汉沽区',NULL), ('27','2','大港区',NULL), ('28','2','东丽区',NULL), ('29','2','西青区',NULL), ('30','2','津南区',NULL), ('31','2','北辰区',NULL), ('32','2','武清区',NULL), ('33','2','宝坻区',NULL), ('34','2','宁河县',NULL), ('35','2','静海县',NULL), ('36','2','蓟县',NULL), ('37','3','石家庄',NULL), ('38','3','唐山',NULL), ('39','3','秦皇岛',NULL), ('40','3','邯郸',NULL), ('41','3','邢台',NULL), ('42','3','保定',NULL), ('43','3','张家口',NULL), ('44','3','承德',NULL), ('45','3','沧州',NULL), ('46','3','廊坊',NULL), ('47','3','衡水',NULL), ('48','4','太原',NULL), ('49','4','大同',NULL), ('50','4','阳泉',NULL), ('51','4','长治',NULL), ('52','4','晋城',NULL), ('53','4','朔州',NULL), ('54','4','晋中',NULL), ('55','4','运城',NULL), ('56','4','忻州',NULL), ('57','4','临汾',NULL), ('58','4','吕梁',NULL), ('59','5','呼和浩特',NULL), ('60','5','包头',NULL), ('61','5','乌海',NULL), ('62','5','赤峰',NULL), ('63','5','通辽',NULL), ('64','5','鄂尔多斯',NULL), ('65','5','呼伦贝尔',NULL), ('66','5','巴彦淖尔',NULL), ('67','5','乌兰察布',NULL), ('68','5','兴安盟',NULL), ('69','5','锡林郭勒盟',NULL), ('70','5','阿拉善盟',NULL), ('71','6','沈阳',NULL), ('72','6','大连',NULL), ('73','6','鞍山',NULL), ('74','6','抚顺',NULL), ('75','6','本溪',NULL), ('76','6','丹东',NULL), ('77','6','锦州',NULL), ('78','6','营口',NULL), ('79','6','阜新',NULL), ('80','6','辽阳',NULL), ('81','6','盘锦',NULL), ('82','6','铁岭',NULL), ('83','6','朝阳',NULL), ('84','6','葫芦岛',NULL), ('85','7','长春',NULL), ('86','7','吉林',NULL), ('87','7','四平',NULL), ('88','7','辽源',NULL), ('89','7','通化',NULL), ('90','7','白山',NULL), ('91','7','松原',NULL), ('92','7','白城',NULL), ('93','7','延边',NULL), ('94','8','哈尔滨',NULL), ('95','8','齐齐哈尔',NULL), ('96','8','鸡西',NULL), ('97','8','鹤岗',NULL), ('98','8','双鸭山',NULL), ('99','8','大庆',NULL), ('100','8','伊春',NULL);
INSERT INTO `sys_city` VALUES ('101','8','佳木斯',NULL), ('102','8','七台河',NULL), ('103','8','牡丹江',NULL), ('104','8','黑河',NULL), ('105','8','绥化',NULL), ('106','8','大兴安岭',NULL), ('107','9','黄浦区',NULL), ('108','9','卢湾区',NULL), ('109','9','徐汇区',NULL), ('110','9','长宁区',NULL), ('111','9','静安区',NULL), ('112','9','普陀区',NULL), ('113','9','闸北区',NULL), ('114','9','虹口区',NULL), ('115','9','杨浦区',NULL), ('116','9','闵行区',NULL), ('117','9','宝山区',NULL), ('118','9','嘉定区',NULL), ('119','9','浦东新区',NULL), ('120','9','金山区',NULL), ('121','9','松江区',NULL), ('122','9','青浦区',NULL), ('123','9','南汇区',NULL), ('124','9','奉贤区',NULL), ('125','9','崇明县',NULL), ('126','10','南京',NULL), ('127','10','无锡',NULL), ('128','10','徐州',NULL), ('129','10','常州',NULL), ('130','10','苏州',NULL), ('131','10','南通',NULL), ('132','10','连云港',NULL), ('133','10','淮安',NULL), ('134','10','盐城',NULL), ('135','10','扬州',NULL), ('136','10','镇江',NULL), ('137','10','泰州',NULL), ('138','10','宿迁',NULL), ('139','11','杭州',NULL), ('140','11','宁波',NULL), ('141','11','温州',NULL), ('142','11','嘉兴',NULL), ('143','11','湖州',NULL), ('144','11','绍兴',NULL), ('145','11','金华',NULL), ('146','11','衢州',NULL), ('147','11','舟山',NULL), ('148','11','台州',NULL), ('149','11','丽水',NULL), ('150','12','合肥',NULL), ('151','12','芜湖',NULL), ('152','12','蚌埠',NULL), ('153','12','淮南',NULL), ('154','12','马鞍山',NULL), ('155','12','淮北',NULL), ('156','12','铜陵',NULL), ('157','12','安庆',NULL), ('158','12','黄山',NULL), ('159','12','滁州',NULL), ('160','12','阜阳',NULL), ('161','12','宿州',NULL), ('162','12','巢湖',NULL), ('163','12','六安',NULL), ('164','12','亳州',NULL), ('165','12','池州',NULL), ('166','12','宣城',NULL), ('167','13','福州',NULL), ('168','13','厦门',NULL), ('169','13','莆田',NULL), ('170','13','三明',NULL), ('171','13','泉州',NULL), ('172','13','漳州',NULL), ('173','13','南平',NULL), ('174','13','龙岩',NULL), ('175','13','宁德',NULL), ('176','14','南昌',NULL), ('177','14','景德镇',NULL), ('178','14','萍乡',NULL), ('179','14','九江',NULL), ('180','14','新余',NULL), ('181','14','鹰潭',NULL), ('182','14','赣州',NULL), ('183','14','吉安',NULL), ('184','14','宜春',NULL), ('185','14','抚州',NULL), ('186','14','上饶',NULL), ('187','15','济南',NULL), ('188','15','青岛',NULL), ('189','15','淄博',NULL), ('190','15','枣庄',NULL), ('191','15','东营',NULL), ('192','15','烟台',NULL), ('193','15','潍坊',NULL), ('194','15','济宁',NULL), ('195','15','泰安',NULL), ('196','15','威海',NULL), ('197','15','日照',NULL), ('198','15','莱芜',NULL), ('199','15','临沂',NULL), ('200','15','德州',NULL);
INSERT INTO `sys_city` VALUES ('201','15','聊城',NULL), ('202','15','滨州',NULL), ('203','15','荷泽',NULL), ('204','16','郑州',NULL), ('205','16','开封',NULL), ('206','16','洛阳',NULL), ('207','16','平顶山',NULL), ('208','16','安阳',NULL), ('209','16','鹤壁',NULL), ('210','16','新乡',NULL), ('211','16','焦作',NULL), ('212','16','濮阳',NULL), ('213','16','许昌',NULL), ('214','16','漯河',NULL), ('215','16','三门峡',NULL), ('216','16','南阳',NULL), ('217','16','商丘',NULL), ('218','16','信阳',NULL), ('219','16','周口',NULL), ('220','16','驻马店',NULL), ('221','17','武汉',NULL), ('222','17','黄石',NULL), ('223','17','十堰',NULL), ('224','17','宜昌',NULL), ('225','17','襄樊',NULL), ('226','17','鄂州',NULL), ('227','17','荆门',NULL), ('228','17','孝感',NULL), ('229','17','荆州',NULL), ('230','17','黄冈',NULL), ('231','17','咸宁',NULL), ('232','17','随州',NULL), ('233','17','恩施',NULL), ('234','17','神农架',NULL), ('235','18','长沙',NULL), ('236','18','株洲',NULL), ('237','18','湘潭',NULL), ('238','18','衡阳',NULL), ('239','18','邵阳',NULL), ('240','18','岳阳',NULL), ('241','18','常德',NULL), ('242','18','张家界',NULL), ('243','18','益阳',NULL), ('244','18','郴州',NULL), ('245','18','永州',NULL), ('246','18','怀化',NULL), ('247','18','娄底',NULL), ('248','18','湘西',NULL), ('249','19','广州',NULL), ('250','19','韶关',NULL), ('251','19','深圳',NULL), ('252','19','珠海',NULL), ('253','19','汕头',NULL), ('254','19','佛山',NULL), ('255','19','江门',NULL), ('256','19','湛江',NULL), ('257','19','茂名',NULL), ('258','19','肇庆',NULL), ('259','19','惠州',NULL), ('260','19','梅州',NULL), ('261','19','汕尾',NULL), ('262','19','河源',NULL), ('263','19','阳江',NULL), ('264','19','清远',NULL), ('265','19','东莞',NULL), ('266','19','中山',NULL), ('267','19','潮州',NULL), ('268','19','揭阳',NULL), ('269','19','云浮',NULL), ('270','20','南宁',NULL), ('271','20','柳州',NULL), ('272','20','桂林',NULL), ('273','20','梧州',NULL), ('274','20','北海',NULL), ('275','20','防城港',NULL), ('276','20','钦州',NULL), ('277','20','贵港',NULL), ('278','20','玉林',NULL), ('279','20','百色',NULL), ('280','20','贺州',NULL), ('281','20','河池',NULL), ('282','20','来宾',NULL), ('283','20','崇左',NULL), ('284','21','海口',NULL), ('285','21','三亚',NULL), ('286','22','重庆',NULL), ('287','22','万州区',NULL), ('288','22','涪陵区',NULL), ('289','22','渝中区',NULL), ('290','22','大渡口区',NULL), ('291','22','江北区',NULL), ('292','22','沙坪坝区',NULL), ('293','22','九龙坡区',NULL), ('294','22','南岸区',NULL), ('295','22','北碚区',NULL), ('296','22','万盛区',NULL), ('297','22','双桥区',NULL), ('298','22','渝北区',NULL), ('299','22','巴南区',NULL), ('300','22','黔江区',NULL);
INSERT INTO `sys_city` VALUES ('301','22','长寿区',NULL), ('302','22','綦江县',NULL), ('303','22','潼南县',NULL), ('304','22','铜梁县',NULL), ('305','22','大足县',NULL), ('306','22','荣昌县',NULL), ('307','22','璧山县',NULL), ('308','22','梁平县',NULL), ('309','22','城口县',NULL), ('310','22','丰都县',NULL), ('311','22','垫江县',NULL), ('312','22','武隆县',NULL), ('313','22','忠县',NULL), ('314','22','开县',NULL), ('315','22','云阳县',NULL), ('316','22','奉节县',NULL), ('317','22','巫山县',NULL), ('318','22','巫溪县',NULL), ('319','22','石柱土家族自治县',NULL), ('320','22','秀山土家族苗族自治县',NULL), ('321','22','酉阳土家族苗族自治县',NULL), ('322','22','彭水苗族土家族自治县',NULL), ('323','22','江津',NULL), ('324','22','合川',NULL), ('325','22','永川',NULL), ('326','22','南川',NULL), ('327','23','成都',NULL), ('328','23','自贡',NULL), ('329','23','攀枝花',NULL), ('330','23','泸州',NULL), ('331','23','德阳',NULL), ('332','23','绵阳',NULL), ('333','23','广元',NULL), ('334','23','遂宁',NULL), ('335','23','内江',NULL), ('336','23','乐山',NULL), ('337','23','南充',NULL), ('338','23','眉山',NULL), ('339','23','宜宾',NULL), ('340','23','广安',NULL), ('341','23','达州',NULL), ('342','23','雅安',NULL), ('343','23','巴中',NULL), ('344','23','资阳',NULL), ('345','23','阿坝',NULL), ('346','23','甘孜',NULL), ('347','23','凉山',NULL), ('348','24','贵阳',NULL), ('349','24','六盘水',NULL), ('350','24','遵义',NULL), ('351','24','安顺',NULL), ('352','24','铜仁',NULL), ('353','24','黔西南',NULL), ('354','24','毕节',NULL), ('355','24','黔东南',NULL), ('356','24','黔南',NULL), ('357','25','昆明',NULL), ('358','25','曲靖',NULL), ('359','25','玉溪',NULL), ('360','25','保山',NULL), ('361','25','昭通',NULL), ('362','25','丽江',NULL), ('363','25','思茅',NULL), ('364','25','临沧',NULL), ('365','25','楚雄',NULL), ('366','25','红河',NULL), ('367','25','文山',NULL), ('368','25','西双版纳',NULL), ('369','25','大理',NULL), ('370','25','德宏',NULL), ('371','25','怒江',NULL), ('372','25','迪庆',NULL), ('373','26','拉萨',NULL), ('374','26','昌都',NULL), ('375','26','山南',NULL), ('376','26','日喀则',NULL), ('377','26','那曲',NULL), ('378','26','阿里',NULL), ('379','26','林芝',NULL), ('380','27','西安',NULL), ('381','27','铜川',NULL), ('382','27','宝鸡',NULL), ('383','27','咸阳',NULL), ('384','27','渭南',NULL), ('385','27','延安',NULL), ('386','27','汉中',NULL), ('387','27','榆林',NULL), ('388','27','安康',NULL), ('389','27','商洛',NULL), ('390','28','兰州',NULL), ('391','28','嘉峪关',NULL), ('392','28','金昌',NULL), ('393','28','白银',NULL), ('394','28','天水',NULL), ('395','28','武威',NULL), ('396','28','张掖',NULL), ('397','28','平凉',NULL), ('398','28','酒泉',NULL), ('399','28','庆阳',NULL), ('400','28','定西',NULL);
INSERT INTO `sys_city` VALUES ('401','28','陇南',NULL), ('402','28','临夏',NULL), ('403','28','甘南',NULL), ('404','29','西宁',NULL), ('405','29','海东',NULL), ('406','29','海北',NULL), ('407','29','黄南',NULL), ('408','29','海南',NULL), ('409','29','果洛',NULL), ('410','29','玉树',NULL), ('411','29','海西',NULL), ('412','30','银川',NULL), ('413','30','石嘴山',NULL), ('414','30','吴忠',NULL), ('415','30','固原',NULL), ('416','30','中卫',NULL), ('417','31','乌鲁木齐',NULL), ('418','31','克拉玛依',NULL), ('419','31','吐鲁番',NULL), ('420','31','哈密',NULL), ('421','31','昌吉',NULL), ('422','31','博尔塔拉',NULL), ('423','31','巴音郭楞',NULL), ('424','31','阿克苏',NULL), ('425','31','克孜勒苏',NULL), ('426','31','喀什',NULL), ('427','31','和田',NULL), ('428','31','伊犁',NULL), ('429','31','塔城',NULL), ('430','31','阿勒泰',NULL), ('431','31','石河子',NULL), ('432','31','阿拉尔',NULL), ('433','31','图木舒克',NULL), ('434','31','五家渠',NULL), ('435','32','香港',NULL), ('436','33','澳门',NULL), ('437','34','台湾',NULL);
INSERT INTO `sys_config` VALUES ('1','system_email_username','wxs@wenyifan.club','2016-04-02 20:38:40','2016-04-02 20:38:44'), ('2','system_email_password','cjdx@2016','2016-04-02 20:40:52',NULL), ('3','system_email_smtp','smtp.mxhichina.com','2016-04-02 20:42:40',NULL), ('4','system_email_pop3','pop3.mxhichina.com','2016-04-02 20:42:44',NULL), ('5','system_email_port','25','2016-04-02 20:42:46',NULL), ('6','system_email_sender','文学社平台','2016-04-02 20:42:49',NULL);
INSERT INTO `sys_illegal` VALUES ('1','独立台湾会','独****','2016-03-24 21:16:42','2016-05-18 17:43:04','0'), ('2','法轮功','法**','2016-03-24 21:16:42',NULL,'0'), ('3','法轮','法*','2016-03-24 21:16:42',NULL,'0'), ('4','反腐败论坛','反****','2016-03-24 21:16:42',NULL,'0'), ('5','共匪','共*','2016-03-24 21:16:42',NULL,'0'), ('6','轮奸','轮*','2016-03-24 21:16:42',NULL,'0'), ('7','射精','射*','2016-03-24 21:16:42',NULL,'0'), ('8','台湾独立','台***','2016-03-24 21:16:42',NULL,'0'), ('9','天安门事件','天****','2016-03-24 21:16:42',NULL,'0'), ('10','天安门屠杀','天****','2016-03-24 21:16:42',NULL,'0'), ('11','西藏独立','西***','2016-03-24 21:16:42',NULL,'0'), ('12','下体','下*','2016-03-24 21:16:42',NULL,'0'), ('13','淫穴','淫*','2016-03-24 21:16:42',NULL,'0'), ('14','幼齿','幼*','2016-03-24 21:16:42',NULL,'0'), ('15','幼女','幼*','2016-03-24 21:16:42',NULL,'0'), ('16','政治犯','政**','2016-03-24 21:16:42',NULL,'0'), ('17','中国猪','中**','2016-03-24 21:16:42',NULL,'0'), ('18','fuck','f***','2016-03-24 21:16:42',NULL,'0'), ('19','小穴','小*','2016-03-24 21:16:42',NULL,'0'), ('20','傻B','傻*','2016-03-24 21:16:42',NULL,'0'), ('21','傻逼','傻*','2016-03-24 21:16:42',NULL,'0'), ('22','日死你','日**','2016-03-24 21:16:42',NULL,'0'), ('23','我操你','我**','2016-03-24 21:16:42',NULL,'0'), ('24','干死你','干**','2016-03-24 21:16:42',NULL,'0'), ('25','鸡八','鸡*','2016-03-24 21:16:42',NULL,'0'), ('26','鸡巴','鸡*','2016-03-24 21:16:42',NULL,'0'), ('27','操逼','操*','2016-03-24 21:16:42',NULL,'0'), ('28','操比','操*','2016-03-24 21:16:42',NULL,'0'), ('29','大逼','大*','2016-03-24 21:16:42',NULL,'0'), ('30','大b','大*','2016-03-24 21:16:42',NULL,'0'), ('31','贱逼','贱*','2016-03-24 21:16:42',NULL,'0'), ('32','贱比','贱*','2016-03-24 21:16:42',NULL,'0'), ('33','干你娘','干**','2016-03-24 21:16:42',NULL,'0'), ('34','卖逼','卖*','2016-03-24 21:16:42',NULL,'0'), ('35','卖比','卖*','2016-03-24 21:16:42',NULL,'0'), ('36','狗b','狗*','2016-03-24 21:16:42',NULL,'0'), ('37','烂逼','烂*','2016-03-24 21:16:42',NULL,'0'), ('38','烂比','烂*','2016-03-24 21:16:42',NULL,'0'), ('39','大花逼','大**','2016-03-24 21:16:42',NULL,'0'), ('40','靠你妈','靠**','2016-03-24 21:16:42',NULL,'0'), ('41','操你妈','操**','2016-03-24 21:16:42',NULL,'0'), ('42','妈批','妈*','2016-03-24 21:16:42',NULL,'0'), ('43','逼样','逼*','2016-03-24 21:16:42',NULL,'0'), ('44','肉棍','肉*','2016-03-24 21:16:42',NULL,'0'), ('45','二逼','二*','2016-03-24 21:16:42',NULL,'0'), ('46','二B','二*','2016-03-24 21:16:42',NULL,'0'), ('47','肉棒','肉*','2016-03-24 21:16:42','2016-05-19 08:37:32','0'), ('48','你妈','你*','2016-03-24 21:16:42',NULL,'0'), ('49','你娘','你*','2016-03-24 21:16:42',NULL,'0'), ('50','你爸','你*','2016-03-24 21:16:42',NULL,'0'), ('51','shit','s***','2016-03-24 21:16:42',NULL,'0'), ('52','操你','操*','2016-03-24 21:16:42',NULL,'0'), ('53','婊子','婊*','2016-03-24 21:16:42',NULL,'0'), ('54','bitch','b****','2016-03-24 21:16:42',NULL,'0'), ('55','我日','我*','2016-03-24 21:16:42',NULL,'0'), ('56','我操','我*','2016-03-24 21:16:42',NULL,'0'), ('57','日你','日*','2016-03-24 21:16:42',NULL,'0'), ('58','操他','操*','2016-03-24 21:16:42',NULL,'0'), ('59','操蛋','操*','2016-03-24 21:16:42',NULL,'0'), ('60','共产党','共**','2016-03-24 21:16:42',NULL,'0'), ('61','屄','屄','2016-03-24 21:16:42',NULL,'0'), ('62','舔奶','舔*','2016-03-24 21:16:42',NULL,'0'), ('63','小鸡鸡','小**','2016-03-24 21:16:42',NULL,'0'), ('64','强奸','强*','2016-03-24 21:16:42',NULL,'0'), ('65','群奸','群*','2016-03-24 21:16:42',NULL,'0'), ('66','贱人','贱*','2016-03-24 21:16:42',NULL,'0'), ('67','狗娘','狗*','2016-03-24 21:16:42',NULL,'0'), ('68','睾丸','睾*','2016-03-24 21:16:42',NULL,'0'), ('69','包皮','包*','2016-03-24 21:16:42',NULL,'0'), ('70','龟头','龟*','2016-03-24 21:16:42',NULL,'0'), ('71','奶子','奶*','2016-03-24 21:16:42',NULL,'0'), ('72','作爱','作*','2016-03-24 21:16:42',NULL,'0'), ('73','做爱','做*','2016-03-24 21:16:42',NULL,'0'), ('74','打炮','打*','2016-03-24 21:16:42',NULL,'0'), ('75','十八摸','十**','2016-03-24 21:16:42',NULL,'0'), ('76','喷你','喷*','2016-03-24 21:16:42',NULL,'0'), ('77','哈批','哈*','2016-03-24 21:16:42',NULL,'0'), ('78','大比','大*','2016-03-24 21:16:42',NULL,'0'), ('79','精液','精*','2016-03-24 21:16:42',NULL,'0'), ('80','精子','精*','2016-03-24 21:16:42',NULL,'0'), ('81','龟公','龟*','2016-03-24 21:16:42',NULL,'0'), ('82','屁眼','屁*','2016-03-24 21:16:42',NULL,'0'), ('83','你妈的','你**','2016-03-24 21:16:42',NULL,'0'), ('84','他妈的','他**','2016-03-24 21:16:42',NULL,'0'), ('85','去你妈的','去***','2016-03-24 21:16:42',NULL,'0'), ('86','奸淫','奸*','2016-03-24 21:16:42',NULL,'0'), ('87','淫荡','淫*','2016-03-24 21:16:42',NULL,'0'), ('88','烂货','烂*','2016-03-24 21:16:42',NULL,'0'), ('89','破鞋','破*','2016-03-24 21:16:42',NULL,'0'), ('90','贱货','贱*','2016-03-24 21:16:42',NULL,'0'), ('91','肛门','肛*','2016-03-24 21:16:42',NULL,'0'), ('92','阴茎','阴*','2016-03-24 21:16:42',NULL,'0'), ('93','狗卵子','狗**','2016-03-24 21:16:42',NULL,'0'), ('94','狗操','狗*','2016-03-24 21:16:42',NULL,'0'), ('95','猪操','猪*','2016-03-24 21:16:42',NULL,'0'), ('96','发骚','发*','2016-03-24 21:16:42',NULL,'0'), ('97','骚货','骚*','2016-03-24 21:16:42',NULL,'0'), ('98','狗屎','狗*','2016-03-24 21:16:42',NULL,'0'), ('99','妓女','妓*','2016-03-24 21:16:42',NULL,'0'), ('100','招妓','招*','2016-03-24 21:16:42',NULL,'0');
INSERT INTO `sys_illegal` VALUES ('101','阴道','阴*','2016-03-24 21:16:42',NULL,'0'), ('102','阴水','阴*','2016-03-24 21:16:42',NULL,'0'), ('103','阴唇','阴*','2016-03-24 21:16:42',NULL,'0'), ('104','阴蒂','阴*','2016-03-24 21:16:42',NULL,'0'), ('105','狂操','狂*','2016-03-24 21:16:42',NULL,'0'), ('106','淫水','淫*','2016-03-24 21:16:42',NULL,'0'), ('107','杂种','杂*','2016-03-24 21:16:42',NULL,'0'), ('108','插你','插*','2016-03-24 21:16:42',NULL,'0'), ('109','性交','性*','2016-03-24 21:16:42',NULL,'0'), ('110','肛交','肛*','2016-03-24 21:16:42',NULL,'0'), ('111','性欲','性*','2016-03-24 21:16:42',NULL,'0'), ('112','B样','B*','2016-03-24 21:16:42',NULL,'0'), ('113','淫图','淫*','2016-03-24 21:16:42',NULL,'0'), ('114','肉洞','肉*','2016-03-24 21:16:42',NULL,'0'), ('115','交媾','交*','2016-03-24 21:16:42',NULL,'0'), ('116','毛泽东','毛**','2016-03-24 21:16:42',NULL,'0'), ('117','MB','M*','2016-03-24 21:16:42',NULL,'0'), ('118','nnd','n**','2016-03-24 21:19:55',NULL,'0'), ('121','骚逼','骚*','2016-03-25 12:06:07',NULL,'0'), ('122','台独','台*','2016-05-13 22:13:25',NULL,'0'), ('123','login','login','2016-05-14 17:03:43',NULL,'0'), ('124','register','register','2016-05-14 17:03:46',NULL,'0'), ('125','admin','admin','2016-05-14 17:03:56',NULL,'0'), ('126','system','system','2016-05-14 22:12:22',NULL,'0'), ('127','s','s','2016-05-18 10:24:45','2016-05-18 17:55:10','0'), ('128','陈义','陈义','2016-05-18 15:47:35',NULL,'1'), ('129','习近平','习**','2016-05-18 17:55:43',NULL,'1');
INSERT INTO `sys_province` VALUES ('1','北京',NULL), ('2','天津',NULL), ('3','河北',NULL), ('4','山西',NULL), ('5','内蒙古',NULL), ('6','辽宁',NULL), ('7','吉林',NULL), ('8','黑龙江',NULL), ('9','上海',NULL), ('10','江苏',NULL), ('11','浙江',NULL), ('12','安徽',NULL), ('13','福建',NULL), ('14','江西',NULL), ('15','山东',NULL), ('16','河南',NULL), ('17','湖北',NULL), ('18','湖南',NULL), ('19','广东',NULL), ('20','广西',NULL), ('21','海南',NULL), ('22','重庆',NULL), ('23','四川',NULL), ('24','贵州',NULL), ('25','云南',NULL), ('26','西藏',NULL), ('27','陕西',NULL), ('28','甘肃',NULL), ('29','青海',NULL), ('30','宁夏',NULL), ('31','新疆',NULL), ('32','香港',NULL), ('33','澳门',NULL), ('34','台湾',NULL);
INSERT INTO `sys_rank` VALUES ('1','1','10','1',NULL,'2016-03-31 16:55:38',NULL,NULL,NULL,NULL), ('2','2','10','2',NULL,'2016-03-31 16:55:56',NULL,NULL,NULL,NULL), ('3','3','10','3',NULL,'2016-03-31 16:56:11',NULL,NULL,NULL,NULL), ('4','4','10','4',NULL,'2016-03-31 16:56:30',NULL,NULL,NULL,NULL), ('5','5','10','5',NULL,'2016-03-31 16:56:49',NULL,NULL,NULL,NULL), ('6','6','10','6',NULL,'2016-03-31 16:57:16',NULL,NULL,NULL,NULL), ('7','7','10','7',NULL,'2016-03-31 16:57:31',NULL,NULL,NULL,NULL), ('8','8','10','8',NULL,'2016-03-31 16:57:55',NULL,NULL,NULL,NULL), ('9','9','10','9',NULL,'2016-03-31 16:58:17',NULL,NULL,NULL,NULL), ('10','10','10','10',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `sys_style` VALUES ('1','默认','0000','0','2016-02-01 20:50:26',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `sys_type` VALUES ('1','散文',NULL,'2016-04-11 17:18:21',NULL,'0',NULL,NULL,NULL,NULL), ('2','小说',NULL,'2016-04-11 17:18:30',NULL,'0',NULL,NULL,NULL,NULL), ('3','诗歌',NULL,'2016-04-11 17:19:10',NULL,'0',NULL,NULL,NULL,NULL), ('4','随笔',NULL,'2016-04-11 17:19:01',NULL,'0',NULL,NULL,NULL,NULL), ('5','杂文',NULL,'2016-04-11 17:19:06',NULL,'0',NULL,NULL,NULL,NULL);
INSERT INTO `u_sign` VALUES ('1','1','一个好人！！！！','2016-04-06 09:51:09','0'), ('2','1','测试签名档！！！','2016-04-06 09:53:43','0'), ('3','1','测试签名档！！！测试','2016-04-06 09:56:11','0'), ('4','1','测试签名档！！！测试','2016-04-06 09:59:53','0'), ('5','1','测试签名档！！！测试','2016-04-06 10:01:29','0'), ('6','1','签名档','2016-04-06 10:14:05','0'), ('7','1','再牛逼的梦想抵不过傻逼式的坚持','2016-04-06 10:19:58','0'), ('8','1','我的未来不是梦','2016-04-06 10:21:22','0'), ('9','1','','2016-04-06 10:25:57','0'), ('10','1','我就是我，颜色不一样的烟火！！！',NULL,'0'), ('11','1','',NULL,'0'), ('12','1','我一般不说自己有才华，因为别人会说。',NULL,'0'), ('13','12','这是一个签名档',NULL,'1'), ('14','1','我一般不说自己有才华，因为别人会说。哈哈哈哈',NULL,'1');
INSERT INTO `w_wxs` VALUES ('1','cjdx','E10ADC3949BA59ABBE56E057F20F883E','cc文学社',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','169.254.184.151','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL), ('2','cjdx','E10ADC3949BA59ABBE56E057F20F883E','cc文学社',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','169.254.184.151','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL), ('3','dfg','E10ADC3949BA59ABBE56E057F20F883E','zz文学社',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','169.254.184.151','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL), ('4','dfg','E10ADC3949BA59ABBE56E057F20F883E','zz文学社',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','169.254.184.151','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL), ('5','vyuu','E10ADC3949BA59ABBE56E057F20F883E','xxx文学社',NULL,NULL,NULL,NULL,'852416288@qq.com',NULL,NULL,NULL,NULL,NULL,'1','169.254.184.151','Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2657.3 Safari/537.36',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
