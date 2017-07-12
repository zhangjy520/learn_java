DROP TABLE IF EXISTS `he_weather`;

CREATE TABLE `he_weather` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `query_date` varchar(20) DEFAULT NULL COMMENT '查询日期',
  `content` text COMMENT '天气内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
