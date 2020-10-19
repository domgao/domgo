CREATE TABLE `user` (
 `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 `phone` varchar(32) DEFAULT NULL,
 `pwd` varchar(128) DEFAULT NULL,
 `sex` int(2) DEFAULT NULL,
 `img` varchar(128) DEFAULT NULL,
 `create_time` datetime DEFAULT NULL,
 `role` int(11) DEFAULT NULL COMMENT '1是普通用户,2是管理员',
 `username` varchar(128) DEFAULT NULL,
 `wechat` varchar(128) DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT
CHARSET=utf8mb4;