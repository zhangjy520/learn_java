/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.17 : Database - gk_git_hub
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`gk_git_hub` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `gk_git_hub`;

/*Table structure for table `change_state_org_class_section` */

DROP TABLE IF EXISTS `change_state_org_class_section`;

CREATE TABLE `change_state_org_class_section` (
  `id` varchar(50) NOT NULL,
  `sync_id` varchar(50) DEFAULT NULL,
  `school_id` varchar(50) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `short_name` varchar(255) DEFAULT NULL COMMENT '简称',
  `limit_age` int(10) DEFAULT NULL COMMENT '入学年龄',
  `section_year` int(10) DEFAULT NULL COMMENT '学制[学校6，初中3，高中3等等]',
  `update_date` bigint(20) DEFAULT NULL COMMENT '接收数据时间',
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  `sync_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学段-学制表';

/*Data for the table `change_state_org_class_section` */

LOCK TABLES `change_state_org_class_section` WRITE;

insert  into `change_state_org_class_section`(`id`,`sync_id`,`school_id`,`name`,`short_name`,`limit_age`,`section_year`,`update_date`,`event`,`source`,`sync_del_flag`,`sync_date`) values ('011e4e4523c44db4b4c5124dbf50b754','92a4db9d3088259d4a2df376b952d663','8373c5da6b0108ef22766073ca1355cc','初中','初',13,3,1492596122824,'MODIFY','gkplat','dgdg.anan,test.sns,0',1492596011000),('11eae8536d7f4f24be3cae8f1c460bc8','23','1','小学',NULL,NULL,6,1490078127858,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077569000),('1231231231','1231231231','1231231231','1231231231','1231231231',1231231231,1231231231,1231231231,'INSERT','gkplat','dgdg.anan,test.sns,0',NULL),('12312313145sdfdsfa123adf12adfadfs','cc','cc','cc','cc',NULL,NULL,NULL,'INSERT','gkplat','dgdg.anan,test.sns,0',NULL),('157a6e63566a4f9bbfc834e9d9946603','10','1','小学','',0,6,1490078127323,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077569000),('241cfffc9d9242b6b236cc76c4021f5a','12','1','初中',NULL,NULL,3,1490078127375,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077569000),('248a81d910054008996c8c17c15f77d2','12312','123','123','123',12,12,1492596122037,'INSERT','gkplat','dgdg.anan,test.sns,0',1492596007000),('284b6d9bb83a4726a8413e04f8d527f3','2','1','小学','小',6,6,1490078127625,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077569000),('3603e911ff1b471d853fc18a3c1d71c3','16','1','高中',NULL,NULL,3,1490078127480,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077569000),('4b598865c5be4e55acc898a68ce477f2','11','1','小学',NULL,NULL,6,1490078127353,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077569000),('78789789','78789789','78789789','78789789','78789789',78789789,78789789,78789789,'78789789','gkplat','dgdg.anan,test.sns,0',NULL),('83720d110e04441ab7d2e1505ea53773','24','1','初中',NULL,NULL,3,1490078127911,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077569000),('926057df81874cf49d078343bc0bbea8','13','1','高中',NULL,NULL,3,1490078127403,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077569000),('95d2ceb2a0dd494f9f2218576f7fd927','14','1','小学',NULL,NULL,6,1490078127425,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077569000),('a522b612f0b34dbcadcf5b80e84fe121','22','1','高中',NULL,NULL,3,1490078127825,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077569000),('a919b6ea344e4c6f919b846a3da175c2','19','1','高中',NULL,NULL,3,1490078127561,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077569000),('b414483795464cae82b9056378dee035','1','1','小学','小',2,3,1490078127275,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077569000),('c61facb0213e4627b5797b278927d6ae','17','1','小学',NULL,NULL,6,1490078127511,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077569000),('c6b6acfdc33741efb59fc6e107bbb12b','a14116666efaa32c80f4887313fc0ea0','058f6eb452aa9291fd3c1706ebafbb4a','高中','高',16,3,1492596122904,'MODIFY','gkplat','test.sns,dgdg.anan,0',1492596011000),('c7d668ea1bf44aca8ec1549b3511776d','18','1','初中',NULL,NULL,3,1490078127533,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077569000),('cbb03b699ce0425485d0e99a1cdac1f4','123123','123','123','123',123,123,1490078127986,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077585000),('cfad4fff5e3e471db00dd032d3dbd6d9','11aaaaaaaa','aaa','aa','a',NULL,NULL,1490078125608,'INSERT','gkplat','dgdg.anan,test.sns,0',1490077592000),('d231a16590b3424c8bee47ea04d52fbc','95bff6440ade21790858d002d6c45681','8373c5da6b0108ef22766073ca1355cc','小学','小',7,6,1492596122881,'MODIFY','gkplat','dgdg.anan,test.sns,0',1492596011000),('e60fd22aa39749159332668f4dd8d010','dd360b179f95632bb6fa7737beee2609','0717f59d099835a28591211813b54114','高中','高',16,3,1492596122938,'MODIFY','gkplat','test.sns,dgdg.anan,0',1492596011000),('f210bc1739ef47a6a985f83bc9bda37a','15','1','初中',NULL,NULL,3,1490078127453,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077569000),('f367c385c0ee476ba307c893946aa083','aa','aa','aa','aa',NULL,NULL,1489039080643,'INSERT','gkplat','dgdg.anan,test.sns,0',1488360009000),('f525b58ff37741b0aa32d306ad607d39','21','1','初中',NULL,NULL,3,1490078127736,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077569000),('fd640599df634bb28982292d8e3ae745','20','1','小学',NULL,NULL,6,1490078127692,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077569000);

UNLOCK TABLES;

/*Table structure for table `change_state_org_department` */

DROP TABLE IF EXISTS `change_state_org_department`;

CREATE TABLE `change_state_org_department` (
  `id` varchar(50) NOT NULL,
  `sync_id` varchar(50) DEFAULT NULL,
  `school_id` varchar(50) DEFAULT NULL,
  `parent_id` varchar(50) DEFAULT NULL,
  `no` varchar(50) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `master_id` varchar(50) DEFAULT NULL,
  `master_name` varchar(100) DEFAULT NULL,
  `update_date` bigint(20) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  `sync_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `change_state_org_department` */

LOCK TABLES `change_state_org_department` WRITE;

insert  into `change_state_org_department`(`id`,`sync_id`,`school_id`,`parent_id`,`no`,`name`,`short_name`,`master_id`,`master_name`,`update_date`,`event`,`source`,`sync_del_flag`,`sync_date`) values ('849a0b5cd78240ac937b67fa0f68a718','135','37','0',NULL,'教务处1111',NULL,'0','赵峻熙',1494318840510,'MODIFY','gkplat','0',1494318823000),('9d6020c1a55042f6836f2a490f30d336','136','37','0',NULL,'组织部1',NULL,'0','刘睿辰',1494318722177,'MODIFY','gkplat','0',1494318687000);

UNLOCK TABLES;

/*Table structure for table `change_state_org_grade_class` */

DROP TABLE IF EXISTS `change_state_org_grade_class`;

CREATE TABLE `change_state_org_grade_class` (
  `id` varchar(50) NOT NULL,
  `sync_id` varchar(50) DEFAULT NULL,
  `school_id` varchar(50) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '班级名称',
  `xd` varchar(50) DEFAULT NULL COMMENT '学段ID',
  `nj` int(10) DEFAULT NULL COMMENT '年级[数字表示 如 2]',
  `bh` int(10) DEFAULT NULL COMMENT '班号[班级顺序]',
  `bjlx` int(10) DEFAULT NULL COMMENT '班级类型[0普通班级，1其他]',
  `xq` varchar(255) DEFAULT NULL COMMENT '校区',
  `rxnd` bigint(20) DEFAULT NULL COMMENT '入学年度',
  `master_id` int(11) DEFAULT NULL COMMENT '班主任ID',
  `master_name` varchar(100) DEFAULT NULL COMMENT '班主任名称',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  `sync_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sync_id` (`sync_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班级表';

/*Data for the table `change_state_org_grade_class` */

LOCK TABLES `change_state_org_grade_class` WRITE;

insert  into `change_state_org_grade_class`(`id`,`sync_id`,`school_id`,`name`,`xd`,`nj`,`bh`,`bjlx`,`xq`,`rxnd`,`master_id`,`master_name`,`update_date`,`event`,`source`,`sync_del_flag`,`sync_date`) values ('0c5afee4f201480da653b5c9547522be','1','21','1111','9',2,NULL,0,'21',NULL,NULL,NULL,1490078132288,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077650000),('13f4a5b17dd04055b8b7dcf278e55f09','213','37','1班','48',1,1,0,'45',NULL,NULL,NULL,1489646700319,'MODIFY','test','dgdg.anan,test.sns,0',1489646675000),('222b65913a454198b4f4e83154ad6fef','101','31','11班','29',2,11,3,'38',NULL,NULL,NULL,1490078132405,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077650000),('252195f7e35f4f399da181be36e8e3d2','102','31','12班','29',2,12,3,'38',NULL,NULL,NULL,1490078132454,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077650000),('44477dc2cf864c038074d7f1600cfd5c','f8800a4a67571ccce9bc47f4310dd016',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1490078133079,'DELETE','gkplat','dgdg.anan,test.sns,0',1490077663000),('48c580ecb83f43eea7187e713d93620f','100','31','10班','29',2,10,3,'38',NULL,NULL,NULL,1490078132363,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077650000),('55255fd9f70248cc9b148c2b9c55d393','10504bc5f9622f590ff5920a16aefbaa','31','2班','db4076d54adedfdc73f72b1d18deaa6c',2,2,2,'38',NULL,NULL,NULL,1490078132646,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077650000),('5ff2d6014d1f4c259ca804410e14dd01','0bad9e4196e913085b4cf1f7fb3cb2be','31','5班','db4076d54adedfdc73f72b1d18deaa6c',2,5,0,'38',NULL,NULL,NULL,1490078132247,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077650000),('7a498e0fe43d4b058006daf1832328bf','105','31','15班','29',2,15,3,'38',NULL,NULL,NULL,1490078132602,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077650000),('85cc1ded85d943568b3054ca4bca667a','104','31','14班','29',2,14,3,'38',NULL,NULL,NULL,1490078132563,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077650000),('a1086f6919a04e4988aa48a434fb15af','0865cb65e01ab95913ab751db8fdbf89','31','11班','db4076d54adedfdc73f72b1d18deaa6c',2,11,0,'38',NULL,NULL,NULL,1490078131878,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077650000),('c5938ba3d5b3499f978520857c173d2f','qweqweqwe',NULL,'qweqwe','qweqwe',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1490078131085,'INSERT','gkplat','dgdg.anan,test.sns,0',1490077661000),('c9872e71e7574727ba8444453d525c73','103','31','13班','29',2,13,3,'38',NULL,NULL,NULL,1490078132526,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077650000),('d254685fa2ae496caf5b66e8ce76054d','10','24','6班','10',2,NULL,NULL,'25',NULL,NULL,NULL,1490078132318,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077650000),('d702f1dd2edb4570b6723f85d2900030','f21397d4880d658cf36bc827e85752d9',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1490078133145,'DELETE','gkplat','dgdg.anan,test.sns,0',1490077665000),('f2ecc0780ea8483f9d594e165f443b26','09d7ca3cd010dd5a3faad05cba60ffa6','31','5班','db4076d54adedfdc73f72b1d18deaa6c',2,5,0,'38',NULL,NULL,NULL,1490078132096,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077650000),('f82636551a914a7cb44eb34e166f538a','0ace1fd24e08c5082e4bf861e2dae58b','31','8班','db4076d54adedfdc73f72b1d18deaa6c',2,8,0,'40',NULL,NULL,NULL,1490078132195,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077650000),('f86de9e7953a44aa9b197eac14a8364b','08ec4a324a9b6d33136390dd490305a9','31','6班','db4076d54adedfdc73f72b1d18deaa6c',2,6,0,'38',NULL,NULL,NULL,1490078132065,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077650000);

UNLOCK TABLES;

/*Table structure for table `change_state_org_school` */

DROP TABLE IF EXISTS `change_state_org_school`;

CREATE TABLE `change_state_org_school` (
  `id` varchar(50) NOT NULL,
  `sync_id` varchar(50) DEFAULT NULL,
  `school_id` varchar(50) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL COMMENT '父级编号',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `ename` varchar(255) DEFAULT NULL COMMENT '学校英文名',
  `xz` varchar(255) DEFAULT NULL COMMENT '校址',
  `type` int(10) DEFAULT NULL COMMENT '机构类型[0 root， 1 教育局， 2 学校]',
  `logo` varchar(255) DEFAULT NULL COMMENT 'logoUrl',
  `bg_picture` varchar(255) DEFAULT NULL COMMENT '背景图片',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `short_flag` varchar(100) DEFAULT NULL,
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  `sync_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_office_parent_id` (`parent_id`) USING BTREE,
  KEY `sys_office_type` (`type`) USING BTREE,
  KEY `sync_id` (`sync_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表【教育局、学校】';

/*Data for the table `change_state_org_school` */

LOCK TABLES `change_state_org_school` WRITE;

insert  into `change_state_org_school`(`id`,`sync_id`,`school_id`,`parent_id`,`name`,`ename`,`xz`,`type`,`logo`,`bg_picture`,`address`,`short_flag`,`update_date`,`event`,`source`,`sync_del_flag`,`sync_date`) values ('1c75ba1bab314ee89276bb67a5564bcd','24',NULL,'0','十六','1','省份地级市市、县级市',0,'school/logo/201611/1479293729735.png','school/bgpic/201611/1479293738026.png','','1116',1490078135311,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077687000),('3349cfffd18c4b64b6d986b5b6d9b480','84bd75db2314be5f7c217fed1bdf6654',NULL,'0','zzzzz',NULL,'省份地级市市、县级市',0,'',NULL,'zzz','zzzzzz',1489472758684,'MODIFY','test','dgdg.anan,test.sns,0',1489472712000),('4f8a1e66d62a4a299c18042fac12b50e','23',NULL,'0','','1','省份地级市市、县级市',0,'',NULL,'','abc',1490078135289,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077687000),('7f350bc0722645598e1350a9361f2dbf','21',NULL,'0','test','1','省份地级市市、县级市',0,'school/logo/201611/1479890006743.png','','','test',1490078135213,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077687000),('a1080edef8bb409cb118bf3371508376','qweqwe',NULL,'qweqwe','qweqwe','qweqwe','qweqw',NULL,NULL,NULL,NULL,NULL,1490078134805,'INSERT','gkplat','dgdg.anan,test.sns,0',1490077676000),('abfd7013fda7442a8ff1abe586763f53','22',NULL,'0','中关村','1','省份地级市市、县级市',0,'school/logo/201611/1479292558084.jpg','','','zgc',1490078135255,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077687000),('b58c58a616254e03b44bcc30e1c5ff23','qwe',NULL,'qwe','qwe','qwe','qwe',NULL,NULL,NULL,NULL,NULL,1490078134805,'INSERT','gkplat','dgdg.anan,test.sns,0',1490077681000),('c4b317e5a9464812b0235af303f79404','37',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1490078136191,'DELETE','gkplat','dgdg.anan,test.sns,0',1490077684000);

UNLOCK TABLES;

/*Table structure for table `change_state_org_school_type` */

DROP TABLE IF EXISTS `change_state_org_school_type`;

CREATE TABLE `change_state_org_school_type` (
  `id` varchar(50) NOT NULL,
  `sync_id` varchar(50) DEFAULT NULL,
  `school_id` varchar(50) DEFAULT NULL COMMENT '学校ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(10) DEFAULT NULL COMMENT '联系电话',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  `sync_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sync_id` (`sync_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构分类';

/*Data for the table `change_state_org_school_type` */

LOCK TABLES `change_state_org_school_type` WRITE;

insert  into `change_state_org_school_type`(`id`,`sync_id`,`school_id`,`name`,`email`,`phone`,`update_date`,`event`,`source`,`sync_del_flag`,`sync_date`) values ('06ddd080cb294badb09f8de98b9f4e29','qqq','qq','qq',NULL,NULL,1490078137798,'INSERT','gkplat','dgdg.anan,test.sns,0',1490077698000),('373915999c294f7eb27e9214b26b218d','41','33','主校区',NULL,'1',1490078138274,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077701000),('3aac69f682ff47c086a155c1bb5698f9','qwe','qwe','qwe',NULL,NULL,1490078137798,'INSERT','gkplat','dgdg.anan,test.sns,0',1490077694000),('79d24828ced943f3b5bb03d7d5150e76','4',NULL,'中关村1小西校区','','1',1490078138205,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077701000),('cd9034215bcf4d589c41063965556b44','40','31','西校区',NULL,'1',1490078138240,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077701000);

UNLOCK TABLES;

/*Table structure for table `change_state_org_title` */

DROP TABLE IF EXISTS `change_state_org_title`;

CREATE TABLE `change_state_org_title` (
  `id` varchar(50) NOT NULL,
  `sync_id` varchar(64) DEFAULT NULL,
  `school_id` varchar(50) DEFAULT NULL,
  `mc` varchar(64) DEFAULT NULL,
  `jb` varchar(64) DEFAULT NULL,
  `px` varchar(64) DEFAULT NULL,
  `update_date` bigint(20) DEFAULT NULL,
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  `sync_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `change_state_org_title` */

LOCK TABLES `change_state_org_title` WRITE;

insert  into `change_state_org_title`(`id`,`sync_id`,`school_id`,`mc`,`jb`,`px`,`update_date`,`event`,`source`,`sync_del_flag`,`sync_date`) values ('3ce5352938e54adb90eb25ffe2125f9a','11','35','大苏打','2','',1490078143583,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077748000),('bd687790e3c14ae7a4dde757cbc0d3bb','10','31','教务处','2','1',1490078143481,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077748000),('cd7dfa871cbb46d792700c26b48c5614','1','','院长','2','2',1490078143448,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077748000),('e9a01a9c03da406880cadddca0b74be3','10aa19175173b4232a8ae489c022edca','31','职务3','2','3',1490078143547,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077748000),('f5039606eb0b4506a69a91c3180abb48','106bed818aef3a5cc5f2ec9bdbfa96d3','37','系主任','2','6',1490078143517,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077748000);

UNLOCK TABLES;

/*Table structure for table `change_state_ref_teacher_class` */

DROP TABLE IF EXISTS `change_state_ref_teacher_class`;

CREATE TABLE `change_state_ref_teacher_class` (
  `id` varchar(50) DEFAULT NULL,
  `sync_teacher_id` varchar(50) DEFAULT NULL COMMENT '老师ID',
  `type` int(11) DEFAULT '0' COMMENT '0：教师， 1：班主任',
  `sync_class_id` varchar(50) DEFAULT NULL COMMENT '班级ID',
  `event` varchar(255) DEFAULT NULL,
  `update_date` bigint(20) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_date` bigint(20) DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='老师-班级';

/*Data for the table `change_state_ref_teacher_class` */

LOCK TABLES `change_state_ref_teacher_class` WRITE;

insert  into `change_state_ref_teacher_class`(`id`,`sync_teacher_id`,`type`,`sync_class_id`,`event`,`update_date`,`source`,`sync_date`,`sync_del_flag`) values ('9243f6073b0944a8912d33ae487d26cf','aaa',0,'233','MODIFY',1494325726381,'gkplat',1494324978000,'0');

UNLOCK TABLES;

/*Table structure for table `change_state_sys_user` */

DROP TABLE IF EXISTS `change_state_sys_user`;

CREATE TABLE `change_state_sys_user` (
  `id` varchar(50) NOT NULL,
  `sync_id` varchar(50) DEFAULT NULL,
  `school_id` varchar(50) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL COMMENT '登录名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `ref_id` varchar(50) DEFAULT NULL COMMENT '用户引用ID',
  `user_type` int(10) DEFAULT NULL COMMENT '用户类型[0:root, 1:教师, 2:学生, 3:家长]',
  `photo_url` varchar(1000) DEFAULT NULL COMMENT '用户头像',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  `sync_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_user_login_name` (`username`),
  KEY `sys_user_update_date` (`update_date`),
  KEY `sync_id` (`sync_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `change_state_sys_user` */

LOCK TABLES `change_state_sys_user` WRITE;

insert  into `change_state_sys_user`(`id`,`sync_id`,`school_id`,`username`,`password`,`ref_id`,`user_type`,`photo_url`,`update_date`,`event`,`source`,`sync_del_flag`,`sync_date`) values ('072a03536e82469696e0033441bf48eb','004edb15430292f019185ae7f9e75a9a','31','JZhekunyi@ceshi','OE43szSeXws9orNa6ooYsQ==','68bb8e075e3bbaedd71dffdeed01e00c',3,NULL,1490078144547,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077774000),('0e14b900e682483da5a0fb3b10819bb9','005cdb5c196b8d16dcdb85ea18d0e9e6','31','JZliujunhao1@ceshi','OE43szSeXws9orNa6ooYsQ==','843da0e3f179a90d4e995b94ad88a9c8',3,NULL,1490078144774,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077774000),('2589a274d9b441759c7e96234b63d44b','16239272574b4a5c7c5ac19e42ddc085',NULL,NULL,NULL,NULL,NULL,NULL,1490078145372,'DELETE','gkplat','dgdg.anan,test.sns,0',1490077795000),('29deff87f9844d82958b818739bda47c','00056cbd37b2d82392782eb777cda1ff','31','biran1@ceshi','OE43szSeXws9orNa6ooYsQ==','56593c84c5e81cb60e6c52df8da61d43',2,NULL,1490078144374,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077774000),('40d7cf3f90f84b83a65fff39f508bcb5','00a3d2908d2919cf3c5b87de0f3af76f','31','lvze@ceshi','OE43szSeXws9orNa6ooYsQ==','47a56d1f7f6466a475e82e44a5a4b478',2,NULL,1490078144864,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077775000),('5f732da64b10462a917b76ef47016ac6','1624',NULL,NULL,NULL,NULL,NULL,NULL,1490078145409,'DELETE','gkplat','dgdg.anan,test.sns,0',1490077795000),('634f777ccbd4409f98788d722db2adaf','1624970515f3ea6da8531f09d7d77acb',NULL,NULL,NULL,NULL,NULL,NULL,1490078145442,'DELETE','gkplat','dgdg.anan,test.sns,0',1490077795000),('725ee3d4ee734b83a16095244821a6d7','a','a','a','a',NULL,NULL,NULL,1490078143976,'INSERT','gkplat','dgdg.anan,test.sns,0',1490077785000),('76a3127ff1644acfb6a57ef3abca30ec','010dec9db6eb6a3cf816b6eb8c526f3e','31','JZnijia1@ceshi','OE43szSeXws9orNa6ooYsQ==','8028da74b9f172b7a015f388a9a69ea0',3,NULL,1490078145017,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077775000),('894eead8acac4a218f75bdced43ad520','004118d89a506f44e2b3d583e177a228','31','JZsunyatong1@ceshi','OE43szSeXws9orNa6ooYsQ==','c4e3ec06628b83bfddd96bdc31ede830',3,NULL,1490078144492,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077774000),('963a6a277816417dbb45ea8244dd3031','0079d803e5ed9aff230463c8e1af38d1','31','JZliuborui1@ceshi','OE43szSeXws9orNa6ooYsQ==','631bf3d0a795e77dfce0cfd107d8b100',3,NULL,1490078144797,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077775000),('a5ef300146c04062b8ad21e1c307e82c','000701cb7a4afe8077a343d12c74f07a','31','litianxue@ceshi','OE43szSeXws9orNa6ooYsQ==','7f2011dad2226ba52889dd47f0d11891',2,NULL,1490078144408,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077774000),('ab5c91c643ff4f768165acf8cf8b00a1','004330b27077c67ba702d0e01cf45afc','31','JZbaihuishuo1@ceshi','OE43szSeXws9orNa6ooYsQ==','fdc665b7f65f0ebac180fdac38ecd0ec',3,NULL,1490078144525,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077774000),('af8cfc3b585b4cc288d1f85b4f7f1fcb','0051e7ac034acf8993bbcc36aecc5f83','31','lenghongxuan1','YWThj7npu4lG1yGSwxfD2Q==','1ead46c760c7631a67e089f5151ad8aa',1,'images/default_tou.png',1490078144721,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077774000),('b3dbefe23dd24e3daa2eecc84ce92ec3','00da1398dc5b812a1ff0af3dac9ec5a3','31','huanghaiyang@ceshi','OE43szSeXws9orNa6ooYsQ==','736156062f324fc1a30f8d8c5ae27fe8',2,NULL,1490078144964,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077775000),('b40a6fd94c7b4b9481dec2d172dd6090','00f9c00fe1fe229e034c7f9d27654893','31','zhenxinyu1@ceshi','OE43szSeXws9orNa6ooYsQ==','2ca0c7230914b334fbe21c9b2669df82',2,NULL,1490078144989,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077775000),('b7632751cd3248269b4d6ee0485bd9b6','4da8e5bbad43b833479586ba11b54790','84bd75db2314be5f7c217fed1bdf6654','admin@zzzzzz','OE43szSeXws9orNa6ooYsQ==','c9ed84e0231f6ff95394271b1ffcd080',1,'images/default_tou.png',1489472758897,'INSERT','test','dgdg.anan,test.sns,0',1489472711000),('b9643a0503e34fb597089a499b876b09','00579477a943e66319b308b445ed7ac0','31','yuxinjun@ceshi','OE43szSeXws9orNa6ooYsQ==','19c3c3b7a732cdfab0b25d92d412b5a9',2,NULL,1490078144747,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077774000),('bd3ee64af89b4a7bb653ce7ce3fa5660','b','b','b','b',NULL,NULL,NULL,1490078143977,'INSERT','gkplat','dgdg.anan,test.sns,0',1490077789000),('bd4cf6974be843f681f1eaf23a25b60c','001804809005377b92b9127f89d8ff01','31','JZpenghaoen1@ceshi','OE43szSeXws9orNa6ooYsQ==','fb1c24bf3f4c2d079848ce981a4e084d',3,NULL,1490078144458,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077774000),('c9ee6244ccae409eb540d865eb63621f','000ac87f7ce4d368185cdef88a368ee2','31','JZxieqiyao1@ceshi','OE43szSeXws9orNa6ooYsQ==','39a8b9bd0a5cb1d53cdd215086d90ced',3,NULL,1490078144430,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077774000),('e38fffbd47804a0bbdfb2381dc634587','00cd7163d051f5ebb96cbfd034d441a8','31','youkaixin1@ceshi','OE43szSeXws9orNa6ooYsQ==','711125ba7fc214b3152595c13bbdca90',2,NULL,1490078144915,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077775000),('f4ba5cbcce7e43eba6cb18c2014f3018','0095fe8a88d29487a56a628cafcc1c9c','31','wangxuandi1','YWThj7npu4lG1yGSwxfD2Q==','bb53c38349db4b255a77b9f3268d6789',1,'images/default_tou.png',1490078144825,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077775000),('ff182a174903468592ceab0409f55c11','00b4791e74a56ec01de965293db4885a','31','JZfangyanxi@ceshi','OE43szSeXws9orNa6ooYsQ==','079e4ea43ba08dfae5113c18fdd0df02',3,NULL,1490078144892,'MODIFY','gkplat','dgdg.anan,test.sns,0',1490077775000);

UNLOCK TABLES;

/*Table structure for table `change_state_user_patriarch` */

DROP TABLE IF EXISTS `change_state_user_patriarch`;

CREATE TABLE `change_state_user_patriarch` (
  `id` varchar(50) NOT NULL,
  `sync_id` varchar(50) DEFAULT NULL,
  `school_id` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `student_id` varchar(50) DEFAULT NULL COMMENT '学生ID【存在冗余字段，多个学生，多个记录】',
  `work` varchar(255) DEFAULT NULL COMMENT '职业',
  `work_at` varchar(255) DEFAULT NULL COMMENT '工作单位',
  `phone` varchar(100) DEFAULT NULL COMMENT '家长登录账号',
  `gender` int(10) DEFAULT NULL COMMENT '性别[0女，1男]',
  `sfjhr` int(10) DEFAULT NULL COMMENT '是否为监护人',
  `sfyqsh` int(10) DEFAULT NULL COMMENT '是否一起生活',
  `patriarch_flag` int(10) DEFAULT NULL COMMENT '家长标识【1父亲，2母亲】',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  `sync_date` bigint(20) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sync_id` (`sync_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='家长信息表';

/*Data for the table `change_state_user_patriarch` */

LOCK TABLES `change_state_user_patriarch` WRITE;

insert  into `change_state_user_patriarch`(`id`,`sync_id`,`school_id`,`name`,`student_id`,`work`,`work_at`,`phone`,`gender`,`sfjhr`,`sfyqsh`,`patriarch_flag`,`update_date`,`event`,`source`,`sync_del_flag`,`sync_date`,`account`) values ('37761877c8844719820149cc4d2837f0','5724ee531e53cdf5110e7071056f7378',NULL,'张三不','1308','一般工人','中南海','13886246591',2,1,2,2,1490928780110,'MODIFY','test','test.sns,dgdg.anan,0',1490869476000,'zhangsanabd1@demo'),('8230aebf79a540fc86155d6933fd7ace','3e9379893f682c833d7f343b8b696574',NULL,'120120015','1313','一般工人','中南海','13886246596',1,1,2,1,1490861160554,'MODIFY','gkplat','test.sns,dgdg.anan,0',1490856235000,'test'),('a06045b8dee14690bea883b04e1a9c34','4fc19481f1230084e5fb965172448257',NULL,'张三人','1311','一般工人','中南海','13886246594',2,1,2,2,1490928780079,'MODIFY','test','test.sns,dgdg.anan,0',1490869346000,'zhaa@demo'),('d2c6335b21ba4794ad5a5566c9b1abdd','4fc19481f1230084e5fb965172448257',NULL,'120120013','1311','一般工人','中南海','13886246594',2,1,2,2,1490861160664,'MODIFY','gkplat','test.sns,dgdg.anan,0',1490856235000,'test'),('e3a82721639640299b5503a2a612a116','3e9379893f682c833d7f343b8b696574',NULL,'张三啊','1313','一般工人','中南海','13886246596',1,1,2,1,1490928780048,'MODIFY','test','test.sns,dgdg.anan,0',1490869230000,'zhangsanab@demo');

UNLOCK TABLES;

/*Table structure for table `change_state_user_student` */

DROP TABLE IF EXISTS `change_state_user_student`;

CREATE TABLE `change_state_user_student` (
  `id` varchar(50) NOT NULL,
  `sync_id` varchar(50) DEFAULT NULL,
  `school_id` varchar(50) DEFAULT NULL,
  `class_id` varchar(50) DEFAULT NULL COMMENT '班级ID',
  `xsxm` varchar(64) DEFAULT NULL COMMENT '学生姓名',
  `xszp` varchar(64) DEFAULT NULL COMMENT '学生照片地址',
  `phone` varchar(100) DEFAULT NULL COMMENT '手机号码',
  `csrq` bigint(20) DEFAULT NULL COMMENT '出生日期',
  `rxrq` bigint(20) DEFAULT NULL COMMENT '入学年度',
  `xsxb` int(10) DEFAULT NULL COMMENT '性别【0位置，1男，2女】',
  `xssg` varchar(255) DEFAULT NULL,
  `xh` varchar(100) DEFAULT NULL COMMENT '学号',
  `xjh` varchar(100) DEFAULT NULL COMMENT '学籍号',
  `qgxjh` varchar(100) DEFAULT NULL COMMENT '全国学籍号',
  `jyid` varchar(100) DEFAULT NULL COMMENT '教育ID号',
  `syd` varchar(100) DEFAULT NULL COMMENT '生源地',
  `yxzjlx` int(10) DEFAULT NULL COMMENT '有效证件类型[1身份证，2护照]',
  `yxzjh` varchar(100) DEFAULT NULL COMMENT '有效证件号',
  `jbs` varchar(255) DEFAULT NULL COMMENT '疾病史',
  `sfsbt` int(10) DEFAULT NULL COMMENT '是否是双胞胎',
  `sbtxh` int(10) DEFAULT NULL COMMENT '双胞胎序号',
  `xslb` int(10) DEFAULT NULL COMMENT '学生类别',
  `gb` varchar(100) DEFAULT NULL COMMENT '国别',
  `mz` varchar(100) DEFAULT NULL COMMENT '民族',
  `jg` varchar(100) DEFAULT NULL COMMENT '籍贯',
  `zzmm` int(10) DEFAULT NULL COMMENT '政治面貌',
  `zslb` int(10) DEFAULT NULL COMMENT '招生类别',
  `lydq` varchar(200) DEFAULT NULL COMMENT '来源地区',
  `hkszd` varchar(200) DEFAULT NULL COMMENT '户口所在地',
  `xjzd` varchar(200) DEFAULT NULL COMMENT '现居住址',
  `hkxz` varchar(100) DEFAULT NULL COMMENT '户口性质',
  `status` int(10) DEFAULT NULL COMMENT '学生状态[0在籍正常，1在籍不在校，2在校不在籍，3不在籍不在校]',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  `sync_date` bigint(20) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sync_id` (`sync_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生管理';

/*Data for the table `change_state_user_student` */

LOCK TABLES `change_state_user_student` WRITE;

insert  into `change_state_user_student`(`id`,`sync_id`,`school_id`,`class_id`,`xsxm`,`xszp`,`phone`,`csrq`,`rxrq`,`xsxb`,`xssg`,`xh`,`xjh`,`qgxjh`,`jyid`,`syd`,`yxzjlx`,`yxzjh`,`jbs`,`sfsbt`,`sbtxh`,`xslb`,`gb`,`mz`,`jg`,`zzmm`,`zslb`,`lydq`,`hkszd`,`xjzd`,`hkxz`,`status`,`update_date`,`event`,`source`,`sync_del_flag`,`sync_date`,`account`) values ('15bdcb4f24a54fe8a184ce77829bd428','1303','37','213','刘子瑜',NULL,NULL,NULL,NULL,2,NULL,'20120005','XS2016090006',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1492595538136,'MODIFY','gkplat','test.sns,dgdg.anan,0',1490856226000,'liuziyu2@demo'),('1ba0a7486bf844cebeb51caa8afc96dd','1300','37','215','由开心',NULL,NULL,NULL,NULL,2,NULL,'20120002','XS2016090003',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,1490928120226,'MODIFY','test','test.sns,dgdg.anan,0',1490928084000,'youkaixin1@demo'),('1c45e850e61e4f9ba361130439241642','1301','37','216','李梦琪',NULL,NULL,NULL,NULL,2,NULL,'20120003','XS2016090004',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,1492595764079,'DELETE','gkplat','test.sns,dgdg.anan,0',1490856226000,'lvboyao1@demo'),('3f7a67be06d041eaa9629fcbd98b3614','1299','37','303','岳书羽',NULL,NULL,NULL,NULL,2,NULL,'20120001','XS2016090002',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,1490928120198,'MODIFY','test','test.sns,dgdg.anan,0',1490928083000,'yueshuyu1@demo'),('97c5453ada3b463a9198b185d54e939a','1302','37','217','吕柏瑶',NULL,NULL,NULL,NULL,2,NULL,'20120004','XS2016090005',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1492595532360,'MODIFY','gkplat','test.sns,dgdg.anan,0',1490856226000,'lvboyao1@demo'),('f1907a1e621a4137a354de098ed7b525','1298','37','213','赵子墨','user/headers/201703/1490864854910.jpg','',0,0,2,NULL,'20120000','','','',NULL,-1,'',NULL,NULL,NULL,-1,'','','',-1,-1,'-1','','','-1',1,1490928120152,'MODIFY','test','test.sns,dgdg.anan,0',1490928081000,'zhaozimo1@demo');

UNLOCK TABLES;

/*Table structure for table `change_state_user_teacher` */

DROP TABLE IF EXISTS `change_state_user_teacher`;

CREATE TABLE `change_state_user_teacher` (
  `id` varchar(50) NOT NULL,
  `sync_id` varchar(50) DEFAULT NULL,
  `school_id` varchar(50) DEFAULT NULL,
  `department_id` varchar(50) DEFAULT NULL COMMENT '部门ID',
  `name` varchar(255) DEFAULT NULL COMMENT '教师名',
  `gender` int(11) DEFAULT NULL COMMENT '性别',
  `identity` varchar(50) DEFAULT NULL COMMENT '身份证号码',
  `is_manage` int(10) DEFAULT '0' COMMENT '是否是管理员[0不是，1是]',
  `title_id` varchar(50) DEFAULT NULL COMMENT '职务ID-[职务管理]',
  `no` varchar(100) DEFAULT NULL COMMENT '职工编号',
  `phone` varchar(20) DEFAULT NULL COMMENT '办公电话',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `start_work` bigint(20) DEFAULT NULL COMMENT '开始工作时间',
  `head_url` varchar(400) DEFAULT NULL COMMENT '头像',
  `zc` varchar(50) DEFAULT NULL COMMENT '职称',
  `sfzrjs` varchar(10) DEFAULT NULL COMMENT '是否专任教师',
  `jg` varchar(20) DEFAULT NULL COMMENT '籍贯',
  `zzmm` varchar(20) DEFAULT NULL COMMENT '政治面貌',
  `rjxk` varchar(20) DEFAULT NULL,
  `xq` varchar(20) DEFAULT NULL COMMENT '校区',
  `zgxl` varchar(20) DEFAULT NULL COMMENT '最高学历',
  `zgbyxx` varchar(20) DEFAULT NULL COMMENT '最高毕业学校',
  `lwxsj` bigint(20) DEFAULT NULL COMMENT '来我校时间',
  `sfhq` varchar(10) DEFAULT NULL COMMENT '是否华侨',
  `sfbzr` varchar(10) DEFAULT NULL COMMENT '是否班主任',
  `wyyz` varchar(20) DEFAULT NULL COMMENT '外语语种',
  `zyjsgwfl` varchar(20) DEFAULT NULL COMMENT '专业技术岗位分类',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `event` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `sync_del_flag` varchar(255) DEFAULT NULL,
  `sync_date` bigint(20) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sync_id` (`sync_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师表：教育局、学校职工';

/*Data for the table `change_state_user_teacher` */

LOCK TABLES `change_state_user_teacher` WRITE;

insert  into `change_state_user_teacher`(`id`,`sync_id`,`school_id`,`department_id`,`name`,`gender`,`identity`,`is_manage`,`title_id`,`no`,`phone`,`mobile`,`email`,`start_work`,`head_url`,`zc`,`sfzrjs`,`jg`,`zzmm`,`rjxk`,`xq`,`zgxl`,`zgbyxx`,`lwxsj`,`sfhq`,`sfbzr`,`wyyz`,`zyjsgwfl`,`update_date`,`event`,`source`,`sync_del_flag`,`sync_date`,`account`) values ('3412967ff60d4a6e852e58ff43cccae8','211','37','0','白帛冉',2,'231457198004057479',0,NULL,'CS201500186',NULL,'18748695186','43453453453@qq.com',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,'1',1490861161360,'MODIFY','gkplat','test.sns,dgdg.anan,0',1490856217000,'baiboran'),('7aa25021c4b749ed89cb64043931dcad','101','37','0','刘梓柯',1,'231457198004057479',0,NULL,'CS201500076',NULL,'18748695076','43453453453@qq.com',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,1490928780267,'MODIFY','test','test.sns,dgdg.anan,0',1490928066000,'liuzike'),('d2f07724cd3f40c59fc7553b9aed9af5','09c9d346c26a756fd9ceae7c94110494','058f6eb452aa9291fd3c1706ebafbb4a','0','愚蠢',1,'',0,'0','',NULL,'','',0,'',NULL,'','','中共党员','','','小学',NULL,0,'','','','1',1490861161313,'MODIFY','gkplat','test.sns,dgdg.anan,0',1490856217000,'yuchun@son3'),('e3d7bac0d1634452b8fb4c23bbb9b6b9','102','37','0','孙宇轩',1,'231457198004057478',0,NULL,'CS201500077',NULL,'18748695077','43453453453@qq.com',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,1490928780288,'MODIFY','test','test.sns,dgdg.anan,0',1490928068000,'sunyuxuan'),('e6b13f7ddfca4ff286a79e7df5a761e2','100','37','35','谭玮瀚',1,'231457198004057478',0,'dffe111f6b52cf6e38e9cfce75c096cd','CS201500075',NULL,'18748695075','43453453453@qq.com',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,1490928780220,'MODIFY','test','test.sns,dgdg.anan,0',1490928065000,'tanweihan'),('f8780cfd05024ce88cbbec85ac496263','25','37','35','管理员',0,NULL,1,'dffe111f6b52cf6e38e9cfce75c096cd',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1',1490861161381,'MODIFY','gkplat','test.sns,dgdg.anan,0',1490856217000,'admin@demo');

UNLOCK TABLES;

/*Table structure for table `db_versions` */

DROP TABLE IF EXISTS `db_versions`;

CREATE TABLE `db_versions` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `db_versions_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `db_versions` */

LOCK TABLES `db_versions` WRITE;

insert  into `db_versions`(`installed_rank`,`version`,`description`,`type`,`script`,`checksum`,`installed_by`,`installed_on`,`execution_time`,`success`) values (1,'1.0.0','create table and init data','SQL','V1.0.0__create_table_and_init_data.sql',-664021626,'root','2017-01-22 19:24:44',2929,1);

UNLOCK TABLES;

/*Table structure for table `detail_obj` */

DROP TABLE IF EXISTS `detail_obj`;

CREATE TABLE `detail_obj` (
  `id` varchar(50) NOT NULL,
  `push_obj_id` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` bigint(20) DEFAULT NULL,
  `mark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `detail_obj` */

LOCK TABLES `detail_obj` WRITE;

insert  into `detail_obj`(`id`,`push_obj_id`,`name`,`create_by`,`create_date`,`mark`) values ('1ba4fe0ab0f2401ea066d107a674ac1d','2','dd顶顶顶',NULL,NULL,NULL),('7a73d2e5a2694d98a8565365ec9ab062','7','ref',NULL,NULL,NULL);

UNLOCK TABLES;

/*Table structure for table `detail_obj_column` */

DROP TABLE IF EXISTS `detail_obj_column`;

CREATE TABLE `detail_obj_column` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `belong` varchar(50) DEFAULT NULL,
  `detail_obj_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `detail_obj_column` */

LOCK TABLES `detail_obj_column` WRITE;

insert  into `detail_obj_column`(`id`,`name`,`belong`,`detail_obj_id`) values ('0bed6f9332ce4340875b09713c9b92aa','parent_id','change_state_org_department','1ba4fe0ab0f2401ea066d107a674ac1d'),('43949142016e48209a2fb93d481c7845','id','change_state_ref_teacher_class','7a73d2e5a2694d98a8565365ec9ab062'),('7076d4aada6b4ad5b58fbcdcd1d62f90','id','change_state_org_department','1ba4fe0ab0f2401ea066d107a674ac1d'),('7a0389cbe2734cfcb70718078f218afc','short_name','change_state_org_department','1ba4fe0ab0f2401ea066d107a674ac1d'),('7da247ca2ca74ce88234f05841347308','sync_teacher_id','change_state_ref_teacher_class','7a73d2e5a2694d98a8565365ec9ab062'),('8b1b673a3890484ba6a9c58149eeb0c1','type','change_state_ref_teacher_class','7a73d2e5a2694d98a8565365ec9ab062'),('af6b553f918b4423ae73c6f99e044a11','school_id','change_state_org_department','1ba4fe0ab0f2401ea066d107a674ac1d'),('c5df24a95d1c4cd69fbd6eeee3572b11','sync_class_id','change_state_ref_teacher_class','7a73d2e5a2694d98a8565365ec9ab062'),('db4ab839055c467a8a48438b0ce534fa','sync_id','change_state_org_department','1ba4fe0ab0f2401ea066d107a674ac1d'),('e4f1ce6786834c76ace189be0abfe1af','name','change_state_org_department','1ba4fe0ab0f2401ea066d107a674ac1d');

UNLOCK TABLES;

/*Table structure for table `layout_headbar` */

DROP TABLE IF EXISTS `layout_headbar`;

CREATE TABLE `layout_headbar` (
  `id` int(11) NOT NULL,
  `css` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `html` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `js` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `is_login` int(11) DEFAULT '0' COMMENT '是否登录',
  `app_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '请求应用的名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `layout_headbar` */

LOCK TABLES `layout_headbar` WRITE;

insert  into `layout_headbar`(`id`,`css`,`html`,`js`,`is_login`,`app_name`) values (1,'*{padding:0;margin:0;box-sizing: border-box;}\r\n        #top-nav{background:#f2f2f2;font-size: 12px;}\r\n        .clear:before{content:\'\';display:block;clear:both;}\r\n        #top-nav>main{width:1140px;margin:0 auto;height:34px;}\r\n        .menu-nav{float:left;}\r\n        ul{list-style: none;}\r\n        .menu-nav>li{float:left;margin-right:9px;width:80px;border-left:1px solid transparent;border-right:1px solid transparent;}\r\n        .menu-nav li a,ul a{color:#666;text-decoration: none;line-height: 34px;}\r\n        .menu-user{float:right;}\r\n        .menu-nav li.menu-list{position:relative;padding:0 10px;}\r\n        .menu-nav li.menu-list:hover{background: #fff;border-color:#E8E8E8;}\r\n        .menu-nav li.menu-list:hover>ul{display:block;}\r\n        li.menu-list:after{content:\'\';position:absolute;right:5px;top:14px;width:8px;height:5px;background:url(/public/imges/header/img/more.png) no-repeat center center;}\r\n        li.menu-list>ul{position:absolute;display:none;width:150px;border:1px solid #e8e8e8;text-align: center;left:-1px;top:33px;background:#fff;z-index: -1;}\r\n        .menu-user>li{position:relative;padding:0 15px;text-align: center;border-left:1px solid transparent;border-right:1px solid transparent;}\r\n        .menu-user>li:hover{border-color:#e8e8e8;background:#fff;}\r\n        .menu-user>li:hover>ul{display:block;}\r\n        .menu-user>li>a{display:inline-block;height:34px;line-height: 34px;padding-left:20px;background:url(/public/imges/header/img/account.png) no-repeat left center;}\r\n        .menu-user>li>ul{display:none;position:absolute;z-index:-1;width:113px;left:-1px;top:33px;border:1px solid #e8e8e8;background: #fff;}\r\n        .menu-user>li>ul li a{position:relative;display: inline-block;width:100%;height:34px;line-height: 34px;}\r\n        .menu-user>li>ul li a:hover{background:#f2f2f2;}\r\n        .menu-user>li>ul li:nth-child(1) a:before{content:\'\';position:absolute;left:11px;top:10px;width:14px;height:14px;background:url(/public/imges/header/img/user.png) no-repeat center center;}\r\n        .menu-user>li>ul li:nth-child(2) a:before{content:\'\';position:absolute;left:11px;top:10px;width:14px;height:14px;background:url(/public/imges/header/img/news.png) no-repeat center center;}\r\n        .menu-user>li>ul li:nth-child(3) a:before{content:\'\';position:absolute;left:11px;top:10px;width:14px;height:14px;background:url(/public/imges/header/img/coleection.png) no-repeat center center;}\r\n        .menu-user>li>ul li:nth-child(4) a:before{content:\'\';position:absolute;left:11px;top:10px;width:14px;height:14px;background:url(/public/imges/header/img/title.png) no-repeat center center;}\r\n','<nav id=\"top-nav\">\r\n        <main class=\"clear\">\r\n            <ul class=\"menu-nav clear\">\r\n                <li><a href=\"http://115.28.175.51/cksns/index.php?s=/forum/index/index\">创客社区</a></li>\r\n                <li><a href=\"http://mooc.com\">创客Mooc</a></li>\r\n                <li><a href=\"http://a\">智能编程云</a></li>\r\n                <li class=\"menu-list\">\r\n                    <a href=\"http://a\">创客官网</a>\r\n                    <ul>\r\n                        <li><a href=\"http://a\">中小学创客教育执委会</a></li>\r\n                    </ul>\r\n                </li>\r\n            </ul>\r\n            <ul class=\"menu-user clear\">\r\n                <li>\r\n                    <a href=\"http://\">用户名</a>\r\n                    <ul>\r\n                        <li class=\"\"><a href=\"http://115.28.175.51/cksns/index.php?s=/ucenter/index/information.html\">用户中心</a></li>\r\n                        <li class=\"\"><a href=\"http://115.28.175.51/cksns/index.php?s=/ucenter/message/message.html\">消息中心</a></li>\r\n                        <li class=\"\"><a href=\"http://115.28.175.51/cksns/index.php?s=/ucenter/collection/index.html\">我的收藏</a></li>\r\n                        <li class=\"\"><a href=\"http://115.28.175.51/cksns/index.php?s=/ucenter/index/rank.html\">我的头衔</a></li>\r\n                        <li class=\"quit\"><a href=\"#\">退出登录</a></li>\r\n                    </ul>\r\n                </li>\r\n\r\n            </ul>\r\n        </main>\r\n    </nav>','sn',0,'sns'),(2,'*{padding:0;margin:0;box-sizing: border-box;}\r\n        #top-nav{background:#f2f2f2;font-size: 12px;}\r\n        .clear:before{content:\'\';display:block;clear:both;}\r\n        #top-nav>main{width:1140px;margin:0 auto;height:34px;}\r\n        .menu-nav{float:left;}\r\n        ul{list-style: none;}\r\n        .menu-nav>li{float:left;margin-right:9px;width:80px;border-left:1px solid transparent;border-right:1px solid transparent;}\r\n        .menu-nav li a,ul a{color:#666;text-decoration: none;line-height: 34px;}\r\n        .menu-user{float:right;}\r\n        .menu-nav li.menu-list{position:relative;padding:0 10px;}\r\n        .menu-nav li.menu-list:hover{background: #fff;border-color:#E8E8E8;}\r\n        .menu-nav li.menu-list:hover>ul{display:block;}\r\n        li.menu-list:after{content:\'\';position:absolute;right:5px;top:14px;width:8px;height:5px;background:url(/public/imges/header/more.png) no-repeat center center;}\r\n        li.menu-list>ul{position:absolute;display:none;width:150px;border:1px solid #e8e8e8;text-align: center;left:-1px;top:33px;background:#fff;z-index: -1;}\r\n        .menu-user>li{position:relative;padding:0 15px;text-align: center;border-left:1px solid transparent;border-right:1px solid transparent;}\r\n        .menu-user>li:hover{border-color:#e8e8e8;background:#fff;}\r\n        .menu-user>li:hover>ul{display:block;}\r\n        .menu-user>li>a{display:inline-block;height:34px;line-height: 34px;padding-left:20px;background:url(/public/imges/header/account.png) no-repeat left center;}\r\n        .menu-user>li>ul{display:none;position:absolute;z-index:-1;width:113px;left:-1px;top:33px;border:1px solid #e8e8e8;background: #fff;}\r\n        .menu-user>li>ul li a{position:relative;display: inline-block;width:100%;height:34px;line-height: 34px;}\r\n        .menu-user>li>ul li a:hover{background:#f2f2f2;}\r\n        .menu-user>li>ul li:nth-child(1) a:before{content:\'\';position:absolute;left:11px;top:10px;width:14px;height:14px;background:url(/public/imges/header/user.png) no-repeat center center;}\r\n        .menu-user>li>ul li:nth-child(2) a:before{content:\'\';position:absolute;left:11px;top:10px;width:14px;height:14px;background:url(/public/imges/header/news.png) no-repeat center center;}\r\n        .menu-user>li>ul li:nth-child(3) a:before{content:\'\';position:absolute;left:11px;top:10px;width:14px;height:14px;background:url(/public/imges/header/coleection.png) no-repeat center center;}\r\n        .menu-user>li>ul li:nth-child(4) a:before{content:\'\';position:absolute;left:11px;top:10px;width:14px;height:14px;background:url(/public/imges/header/title.png) no-repeat center center;}\r\n        .menu-user .quit{border-top:1px solid #e8e8e8;}','<nav id=\"top-nav\">\r\n        <main class=\"clear\">\r\n            <ul class=\"menu-nav clear\">\r\n                <li><a href=\"http://115.28.175.51/cksns/index.php?s=/forum/index/index\">创客社区</a></li>\r\n                <li><a href=\"http://mooc.com\">创客Mooc</a></li>\r\n                <li><a href=\"http://a\">智能编程云</a></li>\r\n                <li class=\"menu-list\">\r\n                    <a href=\"http://a\">创客官网</a>\r\n                    <ul>\r\n                        <li><a href=\"http://a\">中小学创客教育执委会</a></li>\r\n                    </ul>\r\n                </li>\r\n            </ul>\r\n            <ul class=\"menu-user clear\">\r\n               <li id=\"not-login\">\r\n		<a href=\"#\">\r\n		<span onclick=\"window.location.href=\'#\'\">登录</span>\r\n		<span onclick=\"window.location.href=\'#\'\">注册</span>\r\n		</a>\r\n	       </li>\r\n            </ul>\r\n        </main>\r\n</nav>','sn',1,'sns'),(3,'*{padding:0;margin:0;box-sizing: border-box;}\r\n        #top-nav{background:#f2f2f2;font-size: 12px;}\r\n        .clear:before{content:\'\';display:block;clear:both;}\r\n        #top-nav>main{width:1140px;margin:0 auto;height:34px;}\r\n        .menu-nav{float:left;}\r\n        ul{list-style: none;}\r\n        .menu-nav>li{float:left;margin-right:9px;width:80px;border-left:1px solid transparent;border-right:1px solid transparent;}\r\n        .menu-nav li a,ul a{color:#666;text-decoration: none;line-height: 34px;}\r\n        .menu-user{float:right;}\r\n        .menu-nav li.menu-list{position:relative;padding:0 10px;}\r\n        .menu-nav li.menu-list:hover{background: #fff;border-color:#E8E8E8;}\r\n        .menu-nav li.menu-list:hover>ul{display:block;}\r\n        li.menu-list:after{content:\'\';position:absolute;right:5px;top:14px;width:8px;height:5px;background:url(/public/imges/header/more.png) no-repeat center center;}\r\n        li.menu-list>ul{position:absolute;display:none;width:150px;border:1px solid #e8e8e8;text-align: center;left:-1px;top:33px;background:#fff;z-index: -1;}\r\n        .menu-user>li{position:relative;padding:0 15px;text-align: center;border-left:1px solid transparent;border-right:1px solid transparent;}\r\n        .menu-user>li:hover{border-color:#e8e8e8;background:#fff;}\r\n        .menu-user>li:hover>ul{display:block;}\r\n        .menu-user>li>a{display:inline-block;height:34px;line-height: 34px;padding-left:20px;background:url(/public/imges/header/account.png) no-repeat left center;}\r\n        .menu-user>li>ul{display:none;position:absolute;z-index:-1;width:113px;left:-1px;top:33px;border:1px solid #e8e8e8;background: #fff;}\r\n        .menu-user>li>ul li a{position:relative;display: inline-block;width:100%;height:34px;line-height: 34px;}\r\n        .menu-user>li>ul li a:hover{background:#f2f2f2;}\r\n        .menu-user>li>ul li:nth-child(1) a:before{content:\'\';position:absolute;left:11px;top:10px;width:14px;height:14px;background:url(/public/imges/header/user.png) no-repeat center center;}\r\n        .menu-user>li>ul li:nth-child(2) a:before{content:\'\';position:absolute;left:11px;top:10px;width:14px;height:14px;background:url(/public/imges/header/news.png) no-repeat center center;}\r\n        .menu-user>li>ul li:nth-child(3) a:before{content:\'\';position:absolute;left:11px;top:10px;width:14px;height:14px;background:url(/public/imges/header/coleection.png) no-repeat center center;}\r\n        .menu-user>li>ul li:nth-child(4) a:before{content:\'\';position:absolute;left:11px;top:10px;width:14px;height:14px;background:url(/public/imges/header/title.png) no-repeat center center;}\r\n        .menu-user .quit{border-top:1px solid #e8e8e8;}','<nav id=\"top-nav\">\r\n        <main class=\"clear\">\r\n            <ul class=\"menu-nav clear\">\r\n                <li><a href=\"http://asdfdasf\">创客社区</a></li>\r\n                <li><a href=\"http://mooc.com\">创客Mooc</a></li>\r\n                <li><a href=\"http://a\">智能编程云</a></li>\r\n                <li class=\"menu-list\">\r\n                    <a href=\"http://a\">创客官网</a>\r\n                    <ul>\r\n                        <li><a href=\"http://a\">中小学创客教育执委会</a></li>\r\n                    </ul>\r\n                </li>\r\n            </ul>\r\n            <ul class=\"menu-user clear\">\r\n                <li>\r\n                    <a href=\"http://\">用户名</a>\r\n                    <ul>\r\n                        <li class=\"\"><a href=\"http://c\">用户中心</a></li>\r\n                        <li class=\"\"><a href=\"http://c\">消息中心</a></li>\r\n                        <li class=\"\"><a href=\"http://c\">我的收藏</a></li>\r\n                        <li class=\"\"><a href=\"http://c\">我的头衔</a></li>\r\n                        <li class=\"quit\"><a href=\"#\">退出登录</a></li>\r\n                    </ul>\r\n                </li>\r\n\r\n            </ul>\r\n        </main>\r\n    </nav>',NULL,0,'ckmooc'),(4,'*{padding:0;margin:0;box-sizing: border-box;}\r\n        #top-nav{background:#f2f2f2;font-size: 12px;}\r\n        .clear:before{content:\'\';display:block;clear:both;}\r\n        #top-nav>main{width:1140px;margin:0 auto;height:34px;}\r\n        .menu-nav{float:left;}\r\n        ul{list-style: none;}\r\n        .menu-nav>li{float:left;margin-right:9px;width:80px;border-left:1px solid transparent;border-right:1px solid transparent;}\r\n        .menu-nav li a,ul a{color:#666;text-decoration: none;line-height: 34px;}\r\n        .menu-user{float:right;}\r\n        .menu-nav li.menu-list{position:relative;padding:0 10px;}\r\n        .menu-nav li.menu-list:hover{background: #fff;border-color:#E8E8E8;}\r\n        .menu-nav li.menu-list:hover>ul{display:block;}\r\n        li.menu-list:after{content:\'\';position:absolute;right:5px;top:14px;width:8px;height:5px;background:url(/public/imges/header/more.png) no-repeat center center;}\r\n        li.menu-list>ul{position:absolute;display:none;width:150px;border:1px solid #e8e8e8;text-align: center;left:-1px;top:33px;background:#fff;z-index: -1;}\r\n        .menu-user>li{position:relative;padding:0 15px;text-align: center;border-left:1px solid transparent;border-right:1px solid transparent;}\r\n        .menu-user>li:hover{border-color:#e8e8e8;background:#fff;}\r\n        .menu-user>li:hover>ul{display:block;}\r\n        .menu-user>li>a{display:inline-block;height:34px;line-height: 34px;padding-left:20px;background:url(/public/imges/header/account.png) no-repeat left center;}\r\n        .menu-user>li>ul{display:none;position:absolute;z-index:-1;width:113px;left:-1px;top:33px;border:1px solid #e8e8e8;background: #fff;}\r\n        .menu-user>li>ul li a{position:relative;display: inline-block;width:100%;height:34px;line-height: 34px;}\r\n        .menu-user>li>ul li a:hover{background:#f2f2f2;}\r\n        .menu-user>li>ul li:nth-child(1) a:before{content:\'\';position:absolute;left:11px;top:10px;width:14px;height:14px;background:url(/public/imges/header/user.png) no-repeat center center;}\r\n        .menu-user>li>ul li:nth-child(2) a:before{content:\'\';position:absolute;left:11px;top:10px;width:14px;height:14px;background:url(/public/imges/header/news.png) no-repeat center center;}\r\n        .menu-user>li>ul li:nth-child(3) a:before{content:\'\';position:absolute;left:11px;top:10px;width:14px;height:14px;background:url(/public/imges/header/coleection.png) no-repeat center center;}\r\n        .menu-user>li>ul li:nth-child(4) a:before{content:\'\';position:absolute;left:11px;top:10px;width:14px;height:14px;background:url(/public/imges/header/title.png) no-repeat center center;}\r\n        .menu-user .quit{border-top:1px solid #e8e8e8;}','<nav id=\"top-nav\">\r\n        <main class=\"clear\">\r\n            <ul class=\"menu-nav clear\">\r\n                <li><a href=\"http://asdfdasf\">创客社区</a></li>\r\n                <li><a href=\"http://mooc.com\">创客Mooc</a></li>\r\n                <li><a href=\"http://a\">智能编程云</a></li>\r\n                <li class=\"menu-list\">\r\n                    <a href=\"http://a\">创客官网</a>\r\n                    <ul>\r\n                        <li><a href=\"http://a\">中小学创客教育执委会</a></li>\r\n                    </ul>\r\n                </li>\r\n            </ul>\r\n            <ul class=\"menu-user clear\">\r\n               <li id=\"not-login\">\r\n		<a href=\"#\">\r\n		<span onclick=\"window.location.href=\'#\'\">登录</span>\r\n		<span onclick=\"window.location.href=\'#\'\">注册</span>\r\n		</a>\r\n	       </li>\r\n            </ul>\r\n        </main>\r\n</nav>',NULL,1,'ckmooc');

UNLOCK TABLES;

/*Table structure for table `open_accessories` */

DROP TABLE IF EXISTS `open_accessories`;

CREATE TABLE `open_accessories` (
  `id` varchar(50) NOT NULL,
  `identity_photo` varchar(255) DEFAULT NULL COMMENT '身份证图的正面',
  `logo` varchar(255) DEFAULT NULL COMMENT 'logo',
  `licence_scan` varchar(255) DEFAULT NULL COMMENT '营业执照副本',
  `works_scan` varchar(255) DEFAULT NULL COMMENT '作品截图',
  `qualification` varchar(255) DEFAULT '' COMMENT '资格证书',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `open_accessories` */

LOCK TABLES `open_accessories` WRITE;

insert  into `open_accessories`(`id`,`identity_photo`,`logo`,`licence_scan`,`works_scan`,`qualification`) values ('04704c4b555d47e8be1e8aaca3086049','http://file3.ckmooc.com/1.png',NULL,'http://file3.ckmooc.com/1.png',NULL,NULL),('0a15dd5dadd545efbdd1f052337aac7f','http://file3.ckmooc.com/businesslicence.jpg',NULL,'',NULL,NULL),('234826cf162a48cbbe5edfe9389b98ef','',NULL,'',NULL,NULL),('250ea46df6504f938796c6462be25ac4','http://file3.ckmooc.com/1.png',NULL,'http://file3.ckmooc.com/1.png',NULL,NULL),('46fb439164014c28ae8b4a478d51df0d','',NULL,'',NULL,NULL),('56a318c7e1fe489aa43e5ce21d5c1686','http://file3.ckmooc.com/2.gif',NULL,'http://file3.ckmooc.com/2.gif',NULL,NULL),('60679f6850da417bbd16c170eddaf06e','http://file3.ckmooc.com/identity.jpg',NULL,'http://file3.ckmooc.com/businesslicence.jpg',NULL,NULL),('6fd6aebc75af4714813b5523bf0018c5','http://file3.ckmooc.com/1.png',NULL,'http://file3.ckmooc.com/1.png',NULL,NULL),('70f93d48a3684c1d84ed313fdbfcaf8e','',NULL,'',NULL,NULL),('7bd455a13461422e9e81757a2f1420d3','http://file3.ckmooc.com/identity.jpg',NULL,NULL,'http://file3.ckmooc.com/works.jpg','http://file3.ckmooc.com/identity.jpg'),('7ecdb67ccfe34236b479c6099e5e7609','http://file3.ckmooc.com/1.png',NULL,NULL,'http://file3.ckmooc.com/1.png','http://file3.ckmooc.com/o_1bbko2tf22i1nvv8nt24e5bb1d.png'),('8a0f2ad1e4294374bbdd2c76d14b3e73','',NULL,'',NULL,NULL),('9566d4ba991c4f788e46bce769fddfa4','http://file3.ckmooc.com/1.png',NULL,'http://file3.ckmooc.com/1.png',NULL,NULL),('a3e7841a2358439e94b1591887da6d23','http://file3.ckmooc.com/1.png',NULL,'http://file3.ckmooc.com/1.png',NULL,NULL),('a8e4321a140c4c4dae3ff52fc710e61b',NULL,'http://file3.ckmooc.com/works.jpg,http://file3.ckmooc.com/works.jpg',NULL,NULL,NULL),('b781522f1d064fc9bdf187c50b5b7b68',NULL,'http://file3.ckmooc.com/works.jpg,http://file3.ckmooc.com/works.jpg',NULL,NULL,NULL),('b9c4ede49b7b487b87c9c00aec254a3a','http://file3.ckmooc.com/identity.jpg',NULL,NULL,'http://file3.ckmooc.com/works.jpg','http://file3.ckmooc.com/identity.jpg'),('cb7cae801fa344618231bb6ad443d5a9','',NULL,'http://file3.ckmooc.com/1.png',NULL,NULL),('dbaefca4483f4bbca521e505ba7fdc35','http://file3.ckmooc.com/identity.jpg','http://file3.ckmooc.com/identity.jpg',NULL,'http://file3.ckmooc.com/works.jpg','http://file3.ckmooc.com/identity.jpg');

UNLOCK TABLES;

/*Table structure for table `open_app` */

DROP TABLE IF EXISTS `open_app`;

CREATE TABLE `open_app` (
  `id` varchar(50) CHARACTER SET utf8 NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `logo` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `category` int(255) DEFAULT NULL COMMENT '0：教学教务 1互动空间',
  `target_user` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `version` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `is_free` int(11) DEFAULT '0' COMMENT '0：免费 1：不免费',
  `app_screenshot` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `app_abstruct` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `app_url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `demo_account` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `user_id` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `app_secret` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '应用推送到云平台的非网络传输密钥',
  `del_flag` int(50) DEFAULT '0',
  `check_status` int(10) DEFAULT '0' COMMENT '0未提交审核，1已提交审核，2审核成功，3审核失败',
  `app_abbreviation` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '应用简称',
  `create_date` bigint(20) DEFAULT '0' COMMENT '应用创建时间',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `client_Id` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '授权登陆的客户端',
  `client_secret` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '授权登陆的密钥',
  `app_rank` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '应用级别 0区级 1校级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `open_app` */

LOCK TABLES `open_app` WRITE;

insert  into `open_app`(`id`,`name`,`logo`,`category`,`target_user`,`version`,`is_free`,`app_screenshot`,`app_abstruct`,`app_url`,`demo_account`,`user_id`,`app_secret`,`del_flag`,`check_status`,`app_abbreviation`,`create_date`,`update_date`,`client_Id`,`client_secret`,`app_rank`) values ('0435cd6a099344cf817c7ec8d7d0bbb5','qwe','http://file3.ckmooc.com/1.png',0,'教师','123',1,'{http://file3.ckmooc.com/1.png=2}','asdkfjkjasdfkljaldfjkl;aj;sf;dasfkl;ajsd;flsdfasdfdsf','123','123','5bfdee174a3a4871905b3ba837c11f73','JuYVE8',0,3,'a',1489114124436,NULL,'m1HrMnnh','IsNlAfyuojjzZOqr',NULL),('19ba0f029e7a4e18933b4a35aca84063','sns','http://file3.ckmooc.com/3.jpg',0,'null,教师','m没工夫你你',1,'http://file3.ckmooc.com/3.jpg,http://file3.ckmooc.com/mm1.jpg,http://file3.ckmooc.com/mm2.jpg,','sns','f\'n\'h','不v不v','f40933a0d9564d1e8f0288c22aa28284','BENmjo',0,2,'大哥打个副本呢v',1488277334860,NULL,'K6s8Bsfp','U6Em0PTNJzV7Dctg',NULL),('2d4ed760f059484089cda218e7619f7c','qwe','http://file3.ckmooc.com/1.png',0,'教师','adf',1,'http://file3.ckmooc.com/1.png,','sdfjjsadklfjlkdjaslkfjaldfjkadjkfjdkladfkjdjalfjlkjaksfkljak;ljflasfldlslasdkfjkasdjfkljadfskldjaskl','a','adf','5bfdee174a3a4871905b3ba837c11f73','3V9VJo',0,1,'a',1489024307476,NULL,'ZZ4xXpwt','G3xagW66AK2tkL5S',NULL),('c0dc6e056f544f1da163e0a303b8d6d0','三十公分','http://file3.ckmooc.com/3.jpg',0,'null,教师,学生','你赶紧赶紧',1,'http://file3.ckmooc.com/mm1.jpg,http://file3.ckmooc.com/mm2.jpg,','功夫你发现某些果粉','步步高','该国','f40933a0d9564d1e8f0288c22aa28284','t6rdCk',0,3,'能够发挥你',1488277238277,NULL,'vI4oIwC4','eBZ5NYnuENESYRqw',NULL),('c0f067d72ea64272860a5f711d468dd7','智能手环','http://file3.ckmooc.com/u=828511398,854046080&fm=23&gp=0.jpg',0,'教师','一代',1,'http://file3.ckmooc.com/mm1.jpg,http://file3.ckmooc.com/mm2.jpg,http://file3.ckmooc.com/timg.jpg','智能手环是面向学生群体的检测学生健康体制的智能化设备','www.yangguangfenban.com','','5bfdee174a3a4871905b3ba837c11f73','test',0,2,'手环',1486956593759,NULL,NULL,NULL,NULL),('c8a96fa1181b413293697af7c4fb5b03','测试','http://file3.ckmooc.com/1.png',0,'教师','版本',1,'{http://file3.ckmooc.com/1.png=1}','请填写应用介绍，审核通过后将在应用商店该应用详情中体现，便于师生了解该服务概况，不超过500个字gggggggggggggggggggggggggggg','文档','账号','5bfdee174a3a4871905b3ba837c11f73','HnKDsr',0,1,'测试',1489368398611,NULL,'DAvuSDwO','8RlKpyWq6O7SC77I',NULL),('df1e029098034cac86bda2c28aafaf28','a','http://file3.ckmooc.com/1.png',0,'教师','qwe',1,'{http://file3.ckmooc.com/1.png=0}','qweqweqweqweqweqweqweqweqweqweqweqweqweqwe qweqweqweqweqwe','qwe','qwe','5bfdee174a3a4871905b3ba837c11f73','NkETe2',0,0,'app',1489114573960,NULL,'ZQRY5Vbk','6BuymMEQLVDXEQxV',NULL);

UNLOCK TABLES;

/*Table structure for table `open_company` */

DROP TABLE IF EXISTS `open_company`;

CREATE TABLE `open_company` (
  `id` varchar(50) NOT NULL,
  `business_name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `licence_num` varchar(255) DEFAULT NULL COMMENT '营业执照注册号',
  `licence_site` varchar(255) DEFAULT NULL COMMENT '营业执照所在地',
  `corporate_name` varchar(255) DEFAULT NULL COMMENT '法人名字',
  `corporate_identity` varchar(255) DEFAULT NULL COMMENT '法人身份证号',
  `capital` varchar(50) DEFAULT NULL COMMENT '注册资本',
  `address` varchar(255) DEFAULT NULL COMMENT '公司联系地址',
  `company_phone` varchar(50) DEFAULT NULL COMMENT '公司电话',
  `developer_name` varchar(255) DEFAULT NULL,
  `developer_phone` varchar(20) DEFAULT NULL,
  `accessories_id` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `open_company` */

LOCK TABLES `open_company` WRITE;

insert  into `open_company`(`id`,`business_name`,`licence_num`,`licence_site`,`corporate_name`,`corporate_identity`,`capital`,`address`,`company_phone`,`developer_name`,`developer_phone`,`accessories_id`) values ('',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('1dcd587e6b7248c5ac9ac9232734e621','企业名称','111111111111111','2131','1','150204199403100912',NULL,'北京市辖区东城区22','2222','开发者','18201296637','cb7cae801fa344618231bb6ad443d5a9'),('5ccc00535b8a489d93f8de28f73056d0','企业名称','123456781456124','123','姓名','150204199403100912',NULL,'北京市辖区东城区123','183','123','18201296637','04704c4b555d47e8be1e8aaca3086049'),('600f7521fe0449c7bbb1ef588f29e28e','123','111111111111111','adsf','adf','150204199403100912',NULL,'北京市辖区东城区asdf','asdf','123','18333618440','a3e7841a2358439e94b1591887da6d23'),('7aff3cf8840e411bbea8f4e7e9aebd07','测试下','123456456456412','123132','姓名','150204199403100912',NULL,'新疆塔城地区额敏县嗷嗷啊','18333618440','姓名','18333618440','9566d4ba991c4f788e46bce769fddfa4'),('8146bb36a0f3402db724ff3a095e2927','ad','152354855521002','1231321231231','','150204199403100912',NULL,'北京市辖区东城区','','','18333618440','46fb439164014c28ae8b4a478d51df0d'),('9096fa0503294413887fdfd6f9a54063','企业名称','111111111111111','海淀','1','150204199403100912',NULL,'北京市辖区东城区asdf','18333618440','123','18201296637','250ea46df6504f938796c6462be25ac4'),('9d92d271c0b84239a16812f138440fd7','wreqw','111111','fasfsa','ds','222222222222222222',NULL,'北京市辖区东城区fdaf','010-5555666','gsgs','15501178166','56a318c7e1fe489aa43e5ce21d5c1686'),('d4b05c739a1945d6a3998a78896a9150','','scdd','','','372323199104280623',NULL,'北京市辖区东城区','','','15501178166','8a0f2ad1e4294374bbdd2c76d14b3e73'),('d7a7a193497143f180895620dcd86c95','mc','123','123','123','123','1','北京县密云县','123','123','18123132123','60679f6850da417bbd16c170eddaf06e'),('d7ec2f38abd8438788e70616fbef9f0d','企业名称aaa','111111111111111','123','123','150204199403100912',NULL,'北京市辖区东城区asdf','18333618440','开发者','18201296637','234826cf162a48cbbe5edfe9389b98ef'),('f97a73a1982f4a50b88208d786c8206a','名称','111111111111111','qwe','qwe','150204199403100912',NULL,'北京市辖区东城区地方','123213213','aa','15501178166','70f93d48a3684c1d84ed313fdbfcaf8e'),('fdf97c0241e2490eb6c228e5887d0fe5','','123456789123456','123123','姓名','150204199403100912',NULL,'北京市辖区东城区测试地段','测试电话','123','18201296637','6fd6aebc75af4714813b5523bf0018c5'),('feacefc4878f42fc9e29a961a741dd3e','a','123','123','213','213','213','北京市辖区东城区','111111','213','15501178166','0a15dd5dadd545efbdd1f052337aac7f');

UNLOCK TABLES;

/*Table structure for table `open_dynamic` */

DROP TABLE IF EXISTS `open_dynamic`;

CREATE TABLE `open_dynamic` (
  `id` varchar(50) NOT NULL,
  `title` varchar(100) DEFAULT NULL COMMENT '动态标题',
  `content` text COMMENT '动态内容',
  `del_flag` int(10) DEFAULT '0' COMMENT '0未删除 1删除',
  `release_time` bigint(20) DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `open_dynamic` */

LOCK TABLES `open_dynamic` WRITE;

insert  into `open_dynamic`(`id`,`title`,`content`,`del_flag`,`release_time`) values ('11111111111','平台动态标题1','平台动态内容1',0,142856564),('222222222','平台动态标题2','平台动态内容2',0,142856564),('33333333333','平台动态标题3','平台动态内容3',0,142856564),('444444444444','平台动态标题4','平台动态内容4',0,142856564),('555555555555','平台动态标题5','平台动态内容5',0,142856564),('666666666666','平台动态标题6','平台动态内容6',0,142856564),('77777777777','平台动态标题7','平台动态内容7',0,142856564),('888888888888888','平台动态标题8','平台动态内容8',0,142856564),('999999999999','平台动态标题9','平台动态内容9',0,142856564),('00000000000','平台动态标题10','平台动态内容10',0,142856564),('1212121','平台动态标题11','平台动态内容11',0,142856564),('23232323','平台动态标题12','平台动态内容12',0,142856564),('454545454','平台动态标题13','平台动态内容13',0,142856564),('af4c45674ef345b181af753da99e53d7','发布标题1','    发布内容1',0,142856564),('ca159fc519cc4e629804cfa94fe51af1','fdfsf','fdsfsf',0,1487312708103),('a61afe7bd37b4edabdb89a533074744f','发布2','内容2',0,1487312917144),('01f920f08c7f4e08b207c203ecdda838','aaa','　白落梅说过:世间所有相遇，都是久别重逢。那么如今我和你的相遇是前世今生的缘分还是命中注定的情结？\n　　\n　　伴随着一首首悦耳婉转的小调，脑海中那些细碎而美好的记忆碎片就挣脱束缚，慢慢拾掇粘合。你那清晰的容颜便在脑海中闪烁，发出璀璨耀眼的光芒。与你有关的点滴，穿越时空的局限，再度清晰的浮现在我眼前，那一幕幕暖心的瞬间占据着整个脑海。我想，我和你是多有缘，才能在茫茫人海中与你相遇。\n　　\n　　仿佛一切都在冥冥之中注定好了。我心持热枕，对文字死心塌地，而你又恰好为我提供了一个展现、提高自己的平台；所以我们正好天生一对。\n　　\n　　我们因文而结缘。在一笑姐文学艺术群里，我认识了你。我的心被你肚中那一篇篇倾尽情感所撰成的优美文字所牵动，我的眼被那一首首栩栩如生的诗歌画面所感染……欲罢不能的就轻踮了脚尖向你悄悄的靠近靠近，试图走进你，走进有你的世界。\n　　\n　　在一睹你的芳容之后，这颗交织迷离的心就彻底被你捕获。你，博爱、婉约、诚恳、真实。尽自己的所能给文学爱好者提供着一个相互交流，学习的平台：板块多样、涉略广泛，宽广着我们的视野，每一次用情感幻化成的文字都能得到编辑们精彩的评析，似师长的谆谆教诲，或欣赏或鼓励……一切的一切都盈溢着温馨而美好。\n　　\n　　邂逅左岸风，享受字里行间洋溢着的情意。冬去春来，四季交替，曼妙生辉，人间冷暖，细品生活；用从血液中流淌出来的文字谱写着生命的赞歌。以我手写我心，以我心书锦年。\n　　\n　　走进左岸风的象牙塔，不为登顶，而是享受沿途的风景。与不相识却有着同样兴趣爱好的学者们相互交流、学习。以我手书我情，写下初心，岁月静美，一箴一笔足以。',0,1487328803810),('0c6e45fcb63a4e6187807a8215107630','标题哈哈哈','　白落梅说过:世间所有相遇，都是久别重逢。那么如今我和你的相遇是前世今生的缘分还是命中注定的情结？\n　　\n　　伴随着一首首悦耳婉转的小调，脑海中那些细碎而美好的记忆碎片就挣脱束缚，慢慢拾掇粘合。你那清晰的容颜便在脑海中闪烁，发出璀璨耀眼的光芒。与你有关的点滴，穿越时空的局限，再度清晰的浮现在我眼前，那一幕幕暖心的瞬间占据着整个脑海。我想，我和你是多有缘，才能在茫茫人海中与你相遇。\n　　\n　　仿佛一切都在冥冥之中注定好了。我心持热枕，对文字死心塌地，而你又恰好为我提供了一个展现、提高自己的平台；所以我们正好天生一对。\n　　\n　　我们因文而结缘。在一笑姐文学艺术群里，我认识了你。我的心被你肚中那一篇篇倾尽情感所撰成的优美文字所牵动，我的眼被那一首首栩栩如生的诗歌画面所感染……欲罢不能的就轻踮了脚尖向你悄悄的靠近靠近，试图走进你，走进有你的世界。\n　　\n　　在一睹你的芳容之后，这颗交织迷离的心就彻底被你捕获。你，博爱、婉约、诚恳、真实。尽自己的所能给文学爱好者提供着一个相互交流，学习的平台：板块多样、涉略广泛，宽广着我们的视野，每一次用情感幻化成的文字都能得到编辑们精彩的评析，似师长的谆谆教诲，或欣赏或鼓励……一切的一切都盈溢着温馨而美好。\n　　\n　　邂逅左岸风，享受字里行间洋溢着的情意。冬去春来，四季交替，曼妙生辉，人间冷暖，细品生活；用从血液中流淌出来的文字谱写着生命的赞歌。以我手写我心，以我心书锦年。\n　　\n　　走进左岸风的象牙塔，不为登顶，而是享受沿途的风景。与不相识却有着同样兴趣爱好的学者们相互交流、学习。以我手书我情，写下初心，岁月静美，一箴一笔足以。',0,1487328875333),('6f21268b958a40e6a10fd894cb36ee33','dssss','　白落梅说过:世间所有相遇，都是久别重逢。那么如今我和你的相遇是前世今生的缘分还是命中注定的情结？\n　　\n　　伴随着一首首悦耳婉转的小调，脑海中那些细碎而美好的记忆碎片就挣脱束缚，慢慢拾掇粘合。你那清晰的容颜便在脑海中闪烁，发出璀璨耀眼的光芒。与你有关的点滴，穿越时空的局限，再度清晰的浮现在我眼前，那一幕幕暖心的瞬间占据着整个脑海。我想，我和你是多有缘，才能在茫茫人海中与你相遇。\n　　\n　　仿佛一切都在冥冥之中注定好了。我心持热枕，对文字死心塌地，而你又恰好为我提供了一个展现、提高自己的平台；所以我们正好天生一对。\n　　\n　　我们因文而结缘。在一笑姐文学艺术群里，我认识了你。我的心被你肚中那一篇篇倾尽情感所撰成的优美文字所牵动，我的眼被那一首首栩栩如生的诗歌画面所感染……欲罢不能的就轻踮了脚尖向你悄悄的靠近靠近，试图走进你，走进有你的世界。\n　　\n　　在一睹你的芳容之后，这颗交织迷离的心就彻底被你捕获。你，博爱、婉约、诚恳、真实。尽自己的所能给文学爱好者提供着一个相互交流，学习的平台：板块多样、涉略广泛，宽广着我们的视野，每一次用情感幻化成的文字都能得到编辑们精彩的评析，似师长的谆谆教诲，或欣赏或鼓励……一切的一切都盈溢着温馨而美好。\n　　\n　　邂逅左岸风，享受字里行间洋溢着的情意。冬去春来，四季交替，曼妙生辉，人间冷暖，细品生活；用从血液中流淌出来的文字谱写着生命的赞歌。以我手写我心，以我心书锦年。\n　　\n　　走进左岸风的象牙塔，不为登顶，而是享受沿途的风景。与不相识却有着同样兴趣爱好的学者们相互交流、学习。以我手书我情，写下初心，岁月静美，一箴一笔足以。',0,1487328984780),('38bbc06802984f6685defd191d6dfdcc','12','<p>　白落梅说过:世间所有相遇，都是久别重逢。那么如今我和你的相遇是前世今生的<a class=\"keywordlink\" href=\"http://www.jj59.com/zti/yuanfen/\">缘分</a>还是命中注定的情结？<br />　　<br />　　伴随着一首首悦耳婉转的小调，脑海中那些细碎而美好的<a class=\"keywordlink\" href=\"http://www.jj59.com/zti/jiyi/\">记忆</a>碎片就挣脱束缚，慢慢拾掇粘合。你那清晰的容颜便在脑海中闪烁，发出璀璨耀眼的光芒。与你有关的点滴，穿越时空的局限，再度清晰的浮现在我眼前，那一幕幕暖心的瞬间占据着整个脑海。我想，我和你是多有缘，才能在茫茫人海中与你相遇。<br />　　<br />　　仿佛一切都在冥冥之中注定好了。我心持热枕，对<a class=\"keywordlink\" href=\"http://www.jj59.com/\">文字</a>死心塌地，而你又恰好为我提供了一个展现、提高自己的平台；所以我们正好天生一对。<br />　　<br />　　我们因文而结缘。在一笑姐文学艺术群里，我认识了你。我的心被你肚中那一篇篇倾尽<a class=\"keywordlink\" href=\"http://www.jj59.com/qinggantiandi/\">情感</a>所撰成的优美文字所牵动，我的眼被那一首首栩栩如生的诗歌画面所感染&hellip;&hellip;欲罢不能的就轻踮了脚尖向你悄悄的靠近靠近，试图走进你，走进有你的世界。<br />　　<br />　　在一睹你的芳容之后，这颗交织迷离的心就彻底被你捕获。你，博爱、婉约、诚恳、真实。尽自己的所能给文学爱好者提供着一个相互交流，学习的平台：板块多样、涉略广泛，宽广着我们的视野，每一次用情感幻化成的文字都能得到编辑们精彩的评析，似师长的谆谆教诲，或欣赏或鼓励&hellip;&hellip;一切的一切都盈溢着温馨而美好。<br />　　<br />　　邂逅左岸风，享受字里行间洋溢着的情意。冬去春来，四季交替，曼妙生辉，<a class=\"keywordlink\" href=\"http://www.jj59.com/zti/renjian/\">人间</a>冷暖，细品<a class=\"keywordlink\" href=\"http://www.jj59.com/zti/shenghuo/\">生活</a>；用从血液中流淌出来的文字谱写着生命的赞歌。以我手写我心，以我心书锦年。<br />　　<br />　　走进左岸风的象牙塔，不为登顶，而是享受沿途的风景。与不相识却有着同样兴趣爱好的学者们相互交流、学习。以我手书我情，写下初心，岁月静美，一箴一笔足以。</p>',0,1487329521763),('a115023ccb064c83958aa2e497325928','','',1,1487329922246),('c672450410d149d6a6d985b22df49bc4','','',0,1487329924481),('85835fde2e7f4b88a078be517f628f53','','',1,1487329930833),('29ef38e63b444e47b87a0c91328b8d95','','',0,1487329932936),('fd1f2e74703b4a0a810c709ad814ee2b','','',1,1487329936080),('f09cbcc8410d4d7598ec59bba254a29e','','',1,1487329940409),('076457a3b7bf4e2f9dfdbf920e708bf5','','',1,1487330044909),('d73293cf16024953a22cccfe4736fcc9','这是一个测试动态','<p>这是一个测试动态，测试内容不能少于50字还，那我就多写点，测试动态是指并不是正式的动态，所以除了测试没有任何实际意义。这下够吗，没错还没够，那我就多写点吧在，123123123123123123123</p>',0,1489135315785);

UNLOCK TABLES;

/*Table structure for table `open_message` */

DROP TABLE IF EXISTS `open_message`;

CREATE TABLE `open_message` (
  `id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `text` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建日期',
  `user_id` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户引用',
  `status` int(255) DEFAULT NULL COMMENT '2：成功  3：失败（为了和审核状态同步，系统中其他审核都是2为成功3为失败）',
  `isread` int(11) DEFAULT '0' COMMENT '0:未读；1：已读',
  `message_type` int(11) DEFAULT NULL COMMENT '0:用户  1：应用',
  `app_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `open_message` */

LOCK TABLES `open_message` WRITE;

insert  into `open_message`(`id`,`text`,`create_date`,`user_id`,`status`,`isread`,`message_type`,`app_name`) values ('b317d7bfd4f040a0bfa47981a83d2703',NULL,1488959600308,'eb4ec04a4a1adfadsfa994d3a8df09c75',3,0,1,'智能手环'),('51f9382a32184783be264784b8c30d82',NULL,1488961513815,'5bfdee174a3a4871905b3ba837c11f73',3,1,1,'智能手环'),('acb6b35fd2864e42b9d985c69b886c77',NULL,1488961555578,'5bfdee174a3a4871905b3ba837c11f73',3,1,1,'智能手环'),('96dcc58adb0c45cd94b605caf6c9cf6f',NULL,1488961598132,'5bfdee174a3a4871905b3ba837c11f73',3,1,1,'智能手环'),('424d33ce0fa84ed4a59681b7ce6802c7',NULL,1488961719620,'5bfdee174a3a4871905b3ba837c11f73',3,1,1,'智能手环'),('f5980880ff264d55a788856afa43affa','不通过',1488961820055,'5bfdee174a3a4871905b3ba837c11f73',3,1,1,'智能手环'),('4525149f99cc4a3e9315aab95a1aa91a','',1489135173844,'5bfdee174a3a4871905b3ba837c11f73',3,1,1,'qwe'),('42e59f73487b4c71aed00225f7e5f8c7',NULL,1489627057270,'f40933a0d9564d1e8f0288c22aa28284',3,0,0,NULL),('0bfc61e8ca144c24a6652ed7178bea8a',NULL,1489997235517,'5bfdee174a3a4871905b3ba837c11f73',3,1,0,NULL);

UNLOCK TABLES;

/*Table structure for table `open_personal` */

DROP TABLE IF EXISTS `open_personal`;

CREATE TABLE `open_personal` (
  `id` varchar(50) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `identity_card` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `company_name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `contacts_phone` varchar(11) DEFAULT NULL COMMENT '联系人电话',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `works_href` varchar(255) DEFAULT NULL COMMENT '作品展示地址',
  `accessories_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `open_personal` */

LOCK TABLES `open_personal` WRITE;

insert  into `open_personal`(`id`,`name`,`identity_card`,`company_name`,`contacts_phone`,`address`,`works_href`,`accessories_id`) values ('2967183aab15465f92cc400ed676af14','qqq','asss','dvds','','北京市辖区东城区',NULL,'7bd455a13461422e9e81757a2f1420d3'),('707f0ed150264693acf1f650f0cebe89','ssdsd','22222222222222222222222','gegerghs','15501178166','北京市辖区东城区fsdfg',NULL,'b9c4ede49b7b487b87c9c00aec254a3a'),('8a794b78ca6c415f89ea3c754b133b69','测试个人','150204199403100912','单位','18201296637','北京市辖区东城区',NULL,'7ecdb67ccfe34236b479c6099e5e7609'),('f40933a0d9564d1e8f0288c22aa28284','aaa','aaa','aaa','15501178166','北京市辖区东城区',NULL,'dbaefca4483f4bbca521e505ba7fdc35');

UNLOCK TABLES;

/*Table structure for table `open_platform` */

DROP TABLE IF EXISTS `open_platform`;

CREATE TABLE `open_platform` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '云平台名称',
  `identity` varchar(10) DEFAULT NULL COMMENT '云平台唯一标识',
  `url_app` varchar(50) DEFAULT NULL COMMENT '接收应用推送的地址',
  `del_flag` int(10) DEFAULT '0' COMMENT '删除标识：0未删除，1删除',
  `url_visit` varchar(50) DEFAULT NULL COMMENT '平台的访问地址',
  `create_time` bigint(20) DEFAULT NULL COMMENT '平台注册时间',
  `introduce` text COMMENT '应用介绍',
  `init_status` int(10) DEFAULT NULL COMMENT '初始化状态：0未，1已初始化',
  `update_time` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `password` varchar(20) DEFAULT NULL COMMENT '推送协商密码',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `open_platform` */

LOCK TABLES `open_platform` WRITE;

insert  into `open_platform`(`id`,`name`,`identity`,`url_app`,`del_flag`,`url_visit`,`create_time`,`introduce`,`init_status`,`update_time`,`password`) values ('1','谷壳云平台','gkplat','',0,NULL,NULL,'测试平台',0,NULL,'test'),('2','安安云平台','gkplat',NULL,0,NULL,NULL,'安安云平台',0,NULL,NULL),('0a0588ed93b342c09470d5ce43e68e4b','a','gkplat','a',1,'a',1488251866850,'a',0,NULL,'VtYSUF');

UNLOCK TABLES;

/*Table structure for table `open_question` */

DROP TABLE IF EXISTS `open_question`;

CREATE TABLE `open_question` (
  `id` varchar(50) DEFAULT NULL,
  `category` int(10) DEFAULT '0' COMMENT '问题类型',
  `title` varchar(100) DEFAULT NULL COMMENT '问题的标题',
  `content` text COMMENT '问题的具体内容',
  `log_date` bigint(20) DEFAULT NULL COMMENT '问题的录入时间',
  `del_flag` int(8) DEFAULT NULL COMMENT '删除标识'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `open_question` */

LOCK TABLES `open_question` WRITE;

insert  into `open_question`(`id`,`category`,`title`,`content`,`log_date`,`del_flag`) values ('sfsfd',1,'login','<h3>常见</h3><p>谷壳儿云开放平台（以下简称“开放平台”）是以“开放”为核心思想，以“合作共赢”为建设理念的服务平台，其主要目的是为广大应用提供商提供一站式、规范的、标准化的开放能力服务。</p><p>谷壳儿云开放平台将全面整合优质互联网教育资源，为用户带来更多优秀的教育产品，推动教育行业定制、创新、进化，并最终促成新教育体系生态圈的建立，促进教育行业的健康发展。合作伙伴可通过开放平台而接入到全新的谷壳儿云平台。</p><p>谷壳儿云开放平台拥有庞大而真实的谷壳儿云用户资源及用户行为轨迹信息，合作伙伴可通过这些信息，更好的发展自己的产品，并可借助于谷壳儿云的优质推广渠道，让更多的用户体验自己的产品，并实现有效的用户群转化。</p><p>谷壳儿云开放平台，更高的起点，助您更快成长！</p>',2017,0),('geg',4,'其他','<h3>常见</h3><p>谷壳儿云开放平台（以下简称“开放平台”）是以“开放”为核心思想，以“合作共赢”为建设理念的服务平台，其主要目的是为广大应用提供商提供一站式、规范的、标准化的开放能力服务。</p><p>谷壳儿云开放平台将全面整合优质互联网教育资源，为用户带来更多优秀的教育产品，推动教育行业定制、创新、进化，并最终促成新教育体系生态圈的建立，促进教育行业的健康发展。合作伙伴可通过开放平台而接入到全新的谷壳儿云平台。</p><p>谷壳儿云开放平台拥有庞大而真实的谷壳儿云用户资源及用户行为轨迹信息，合作伙伴可通过这些信息，更好的发展自己的产品，并可借助于谷壳儿云的优质推广渠道，让更多的用户体验自己的产品，并实现有效的用户群转化。</p><p>谷壳儿云开放平台，更高的起点，助您更快成长！</p>',2017,0),('dsfsgrh',3,'用户','<h3>一月</h3><p> 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/manager/updateInfo],methods=[POST]}\" onto public java.lang.String cc.gukeer.open.controller.ManageController.registerall(javax.servlet.http.HttpServletRequest,cc.gukeer.open.persistence.entity.Company,cc.gukeer.open.persistence.entity.Personal,org.springframework.ui.Model)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/manager/password/update],methods=[POST]}\" onto public cc.gukeer.common.entity.ResultEntity cc.gukeer.open.controller.ManageController.updatePassword(javax.servlet.http.HttpServletRequest)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/manager/dynamic],methods=[GET]}\" onto public java.lang.String cc.gukeer.open.controller.ManageController.manageCenter4(javax.servlet.http.HttpServletRequest,org.springframework.ui.Model)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/manager/password/page],methods=[GET]}\" onto public java.lang.String cc.gukeer.open.controller.ManageController.updatepasswordPage(javax.servlet.http.HttpServletRequest,org.springframework.ui.Model)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/manager/app/add],methods=[GET]}\" onto public java.lang.String cc.gukeer.open.controller.ManageController.createapplication(javax.servlet.http.HttpServletRequest,org.springframework.ui.Model)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/accessToken]}\" onto public java.lang.Object cc.gukeer.open.controller.OAuthAccessTokenController.token(org.springframework.ui.Model,javax.servlet.http.HttpServletRequest) throws java.net.URISyntaxException,org.apache.oltu.oauth2.common.exception.OAuthSystemException\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/refreshToken]}\" onto public org.springframework.http.HttpEntity cc.gukeer.open.controller.OAuthAccessTokenController.refreshToken(javax.servlet.http.HttpServletRequest) throws org.apache.oltu.oauth2.common.exception.OAuthSystemException\r\n</p><p>一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/authorize]}\" onto public java.lang.Object cc.gukeer.open.controller.OAuthAuthorizeController.authorize(org.springframework.ui.Model,javax.servlet.http.HttpServletRequest) throws java.net.URISyntaxException,org.apache.oltu.oauth2.common.exception.OAuthSystemException\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/client/{id}/update],methods=[POST]}\" onto public java.lang.String cc.gukeer.open.controller.OAuthClientController.update(cc.gukeer.open.persistence.entity.Client,org.springframework.web.servlet.mvc.support.RedirectAttributes)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/client/{id}/delete],methods=[POST]}\" onto public java.lang.String cc.gukeer.open.controller.OAuthClientController.delete(java.lang.Long,org.springframework.web.servlet.mvc.support.RedirectAttributes)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/client],methods=[GET]}\" onto public java.lang.String cc.gukeer.open.controller.OAuthClientController.list(org.springframework.ui.Model)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/client/create],methods=[POST]}\" onto public java.lang.String cc.gukeer.open.controller.OAuthClientController.create(cc.gukeer.open.persistence.entity.Client,org.springframework.web.servlet.mvc.support.RedirectAttributes)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/client/{id}/delete],methods=[GET]}\" onto public java.lang.String cc.gukeer.open.controller.OAuthClientController.showDeleteForm(java.lang.Long,org.springframework.ui.Model)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/client/create],methods=[GET]}\" onto public java.lang.String cc.gukeer.open.controller.OAuthClientController.showCreateForm(org.springframework.ui.Model)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/client/{id}/update],methods=[GET]}\" onto public java.lang.String cc.gukeer.open.controller.OAuthClientController.showUpdateForm(java.lang.Long,org.springframework.ui.Model)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/userInfo]}\" onto public org.springframework.http.HttpEntity cc.gukeer.open.controller.OAuthUserInfoController.userInfo(javax.servlet.http.HttpServletRequest) throws org.apache.oltu.oauth2.common.exception.OAuthSystemException\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/login/check]}\" onto public org.springframework.http.HttpEntity cc.gukeer.open.controller.OAuthUserInfoController.checkLoginStatus(javax.servlet.http.HttpServletRequest) throws org.apache.oltu.oauth2.common.exception.OAuthSystemException\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/register/doregister],methods=[POST]}\" onto public cc.gukeer.common.entity.ResultEntity cc.gukeer.open.controller.RegisterController.register(javax.servlet.http.HttpServletRequest,cc.gukeer.open.persistence.entity.OpenUser,javax.servlet.http.HttpServletResponse) throws javax.mail.MessagingException\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/register/index],methods=[GET]}\" onto public java.lang.String cc.gukeer.open.controller.RegisterController.reg(org.springframework.ui.Model)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/register/activate],methods=[GET]}\" onto public java.lang.String cc.gukeer.open.controller.RegisterController.activate(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,org.springframework.ui.Model)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/register/save],methods=[POST]}\" onto public java.lang.String cc.gukeer.open.controller.RegisterController.registerall(javax.servlet.http.HttpServletRequest,cc.gukeer.open.persistence.entity.Company,cc.gukeer.open.persistence.entity.Personal,cc.gukeer.open.persistence.entity.Accessories,org.springframework.ui.Model)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/register/judgeCode],methods=[POST]}\" onto public cc.gukeer.common.entity.ResultEntity cc.gukeer.open.controller.RegisterController.judge(javax.servlet.http.HttpServletRequest)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/register/emailcheck]}\" onto public java.lang.String cc.gukeer.open.controller.RegisterController.emailChecking(javax.servlet.http.HttpServletRequest,org.springframework.ui.Model)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/register/detail],methods=[GET]}\" onto public java.lang.String cc.gukeer.open.controller.RegisterController.personalCompleteData(javax.servlet.http.HttpServletRequest,org.springframework.ui.Model)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/register/mail/resend],methods=[POST]}\" onto public cc.gukeer.common.entity.ResultEntity cc.gukeer.open.controller.RegisterController.personalCompleteData(javax.servlet.http.HttpServletRequest)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/pwd/index],methods=[GET]}\" onto public java.lang.String cc.gukeer.open.controller.ResetPWDController.index(javax.servlet.http.HttpServletRequest)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/pwd/check],methods=[GET]}\" onto public java.lang.String cc.gukeer.open.controller.ResetPWDController.checkResetLink(javax.servlet.http.HttpServletRequest,org.springframework.ui.Model)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/pwd/update]}\" onto public cc.gukeer.common.entity.ResultEntity cc.gukeer.open.controller.ResetPWDController.updatePWD(javax.servlet.http.HttpServletRequest)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/pwd/mail/send]}\" onto public java.lang.String cc.gukeer.open.controller.ResetPWDController.seekPWD(javax.servlet.http.HttpServletRequest,org.springframework.ui.Model)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/pwd/check/username]}\" onto public cc.gukeer.common.entity.ResultEntity cc.gukeer.open.controller.ResetPWDController.checkUsername(javax.servlet.http.HttpServletRequest)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/shortMessage],methods=[POST]}\" onto public cc.gukeer.common.entity.ResultEntity cc.gukeer.open.controller.ShortMessageController.sendMessage(javax.servlet.http.HttpServletRequest)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/tech/detail],methods=[GET]}\" onto public java.lang.String cc.gukeer.open.controller.TechController.detail(javax.servlet.http.HttpServletRequest,org.springframework.ui.Model)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/tech/index],methods=[GET]}\" onto public java.lang.String cc.gukeer.open.controller.TechController.tech(javax.servlet.http.HttpServletRequest,org.springframework.ui.Model)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/file/upload],methods=[POST]}\" onto public cc.gukeer.common.entity.ResultEntity cc.gukeer.open.controller.UploadController.uploads(org.springframework.web.multipart.MultipartFile,javax.servlet.http.HttpServletRequest) throws java.lang.Exception\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/file/pic/show],methods=[GET]}\" onto public void cc.gukeer.open.controller.UploadController.showPicture(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse) throws java.lang.Exception\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/file/getuptoken],methods=[GET]}\" onto public void cc.gukeer.open.controller.UploadController.makeToken(javax.servlet.http.HttpServletResponse,javax.servlet.http.HttpServletRequest)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/user/index]}\" onto public java.lang.String cc.gukeer.open.controller.UserController.userindex()\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/user/edit/{mark}]}\" onto public java.lang.String cc.gukeer.open.controller.UserController.edit(java.lang.String,javax.servlet.http.HttpServletRequest)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/layout/edit/{flag}]}\" onto public java.lang.String cc.gukeer.support.layout.controller.LayoutController.editview(org.springframework.ui.Model,java.lang.String)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/layout/doedit]}\" onto public cc.gukeer.common.entity.ResultEntity cc.gukeer.support.layout.controller.LayoutController.doEditview(javax.servlet.http.HttpServletRequest,cc.gukeer.support.layout.persistence.entity.Link)\r\n一月 23, 2017 4:04:58 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping register\r\n信息: Mapped \"{[/layout/getheadbar/{appName}/{isLogin}]}\" onto public cc.gukeer.common.entity.ResultEntity cc.gukeer.support.layout.controller.LayoutController.getgkPlatformHeadbar(int,java.lang.String,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse) throws java.net.UnknownHostException\r\n一月 23, 2017 4:04:59 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter initControllerAdviceCache\r\n信息: Looking for @ControllerAdvice: WebApplicationContext for namespace \'springMVC-servlet\': startup date [Mon Jan 23 16:04:57 CST 2017]; parent: Root WebApplicationContext\r\n一月 23, 2017 4:04:59 下午 org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter initControllerAdviceCache\r\n信息: Looking for @ControllerAdvice: WebApplicationContext for namespace \'springMVC-servlet\': startup date [Mon Jan 23 16:04:57 CST 2017]; parent: Root WebApplicationContext\r\n一月 23, 2017 4:04:59 下午 org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver initExceptionHandlerAdviceCache\r\n信息: Detected @ExceptionHandler methods in exceptionController\r\n一月 23, 2017 4:04:59 下午 org.springframework.web.servlet.handler.SimpleUrlHandlerMapping registerHandler\r\n信息: Mapped URL path [/**] onto handler \'org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler#0\'\r\n一月 23, 2017 4:04:59 下午 org.springframework.web.servlet.handler.SimpleUrlHandlerMapping registerHandler\r\n信息: Mapped URL path [/assets/**] onto handler \'org.springframework.web.servlet.resource.ResourceHttpRequestHandler#0\'\r\n一月 23, 2017 4:04:59 下午 org.springframework.web.servlet.handler.SimpleUrlHandlerMapping registerHandler\r\n信息: Mapped URL path [/static/**] onto handler \'org.springframework.web.servlet.resource.ResourceHttpRequestHandler#1\'\r\n一月 23, 2017 4:04:59 下午 org.springframework.web.servlet.DispatcherServlet initServletBean\r\n信息: FrameworkServlet \'springMVC\': initialization completed in 1740 ms\r\n一月 23, 2017 4:05:00 下午 org.apache.jasper.compiler.TldLocationsCache tldScanJar\r\n信息: At least one JAR was scanned for TLDs yet contained no TLDs. Enable debug logging for this logger for a complete list of JARs that were scanned but no TLDs were found in them. Skipping unneeded JARs during scanning can improve startup time and JSP compilation time.\r\n一月 23, 2017 4:05:02 下午 org.apache.catalina.startup.HostConfig deployDirectory\r\n信息: Deploying web application directory D:\\develop\\software\\tomcat701\\apache-tomcat-7.0.68\\webapps\\manager\r\n一月 23, 2017 4:05:02 下午 org.apache.catalina.startup.HostConfig deployDirectory\r\n</p><p>信息: Deployment of web application directory D:\\develop\\software\\tomcat701\\apache-tomcat-7.0.68\\webapps\\manager has finished in 80 ms</p>',2017,0),('hrtjrt',2,'应用审核','<h3>常见</h3><p>谷壳儿云开放平台（以下简称“开放平台”）是以“开放”为核心思想，以“合作共赢”为建设理念的服务平台，其主要目的是为广大应用提供商提供一站式、规范的、标准化的开放能力服务。</p><p>谷壳儿云开放平台将全面整合优质互联网教育资源，为用户带来更多优秀的教育产品，推动教育行业定制、创新、进化，并最终促成新教育体系生态圈的建立，促进教育行业的健康发展。合作伙伴可通过开放平台而接入到全新的谷壳儿云平台。</p><p>谷壳儿云开放平台拥有庞大而真实的谷壳儿云用户资源及用户行为轨迹信息，合作伙伴可通过这些信息，更好的发展自己的产品，并可借助于谷壳儿云的优质推广渠道，让更多的用户体验自己的产品，并实现有效的用户群转化。</p><p>谷壳儿云开放平台，更高的起点，助您更快成长！</p>',2017,0),('jtjgfxj',1,'login','<h3>常见</h3><p>谷壳儿云开放平台（以下简称“开放平台”）是以“开放”为核心思想，以“合作共赢”为建设理念的服务平台，其主要目的是为广大应用提供商提供一站式、规范的、标准化的开放能力服务。</p><p>谷壳儿云开放平台将全面整合优质互联网教育资源，为用户带来更多优秀的教育产品，推动教育行业定制、创新、进化，并最终促成新教育体系生态圈的建立，促进教育行业的健康发展。合作伙伴可通过开放平台而接入到全新的谷壳儿云平台。</p><p>谷壳儿云开放平台拥有庞大而真实的谷壳儿云用户资源及用户行为轨迹信息，合作伙伴可通过这些信息，更好的发展自己的产品，并可借助于谷壳儿云的优质推广渠道，让更多的用户体验自己的产品，并实现有效的用户群转化。</p><p>谷壳儿云开放平台，更高的起点，助您更快成长！</p>',2017,0);

UNLOCK TABLES;

/*Table structure for table `open_user` */

DROP TABLE IF EXISTS `open_user`;

CREATE TABLE `open_user` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `username` varchar(100) DEFAULT NULL COMMENT '登录名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `status` int(11) DEFAULT '0' COMMENT '审核状态',
  `ref_id` int(11) DEFAULT NULL,
  `user_type` int(10) DEFAULT NULL COMMENT '用户类型[0 ：个人用户 1：企业用户 2：管理员用户]',
  `login_flag` int(10) DEFAULT NULL COMMENT '是否可登录',
  `login_mark` varchar(64) DEFAULT NULL COMMENT '用于单点登录的随机字符串验证',
  `create_by` varchar(50) DEFAULT NULL COMMENT '来源',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新者',
  `update_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `agreement_num` int(11) DEFAULT NULL COMMENT '签订的协议编号',
  `del_flag` int(10) DEFAULT '0' COMMENT '删除标记',
  `register_status` int(11) DEFAULT '0' COMMENT '0未激活, 1激活, 2已填写详细资料',
  `current_time_sendEmail` bigint(20) DEFAULT '0' COMMENT '发送邮件的时间戳',
  `random` varchar(50) DEFAULT '""' COMMENT '邮件和短信的验证字符串',
  `last_login_time` date DEFAULT NULL COMMENT '用户最后登陆时间',
  `company_id` varchar(50) DEFAULT NULL COMMENT '企业用户表主键',
  `personal_id` varchar(50) DEFAULT NULL COMMENT '个人用户表主键',
  KEY `sys_user_login_name` (`username`) USING BTREE,
  KEY `sys_user_update_date` (`update_date`) USING BTREE,
  KEY `sys_user_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `open_user` */

LOCK TABLES `open_user` WRITE;

insert  into `open_user`(`id`,`username`,`password`,`status`,`ref_id`,`user_type`,`login_flag`,`login_mark`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`agreement_num`,`del_flag`,`register_status`,`current_time_sendEmail`,`random`,`last_login_time`,`company_id`,`personal_id`) values ('eb4ec04a4a1adfadsfa994d3a8df09c75','admin','VAah64ZhruW/UCQQY3QJOg==',2,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,2,0,'\"\"',NULL,'13','55'),('f40933a0d9564d1e8f0288c22aa28284','2356537782@qq.com','VAah64ZhruW/UCQQY3QJOg==',3,NULL,1,NULL,NULL,NULL,1484026933881,NULL,NULL,NULL,NULL,0,2,1489650191000,'837299',NULL,'f97a73a1982f4a50b88208d786c8206a','707f0ed150264693acf1f650f0cebe89'),('',NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,'\"\"',NULL,NULL,NULL),('5bfdee174a3a4871905b3ba837c11f73','731748081@qq.com','VAah64ZhruW/UCQQY3QJOg==',5,NULL,0,NULL,NULL,NULL,1489976290203,NULL,NULL,NULL,NULL,0,2,1484901110896,'199817',NULL,'','8a794b78ca6c415f89ea3c754b133b69'),('c465af509de341fa92270b9230841f74','827360552@qq.com','VAah64ZhruW/UCQQY3QJOg==',0,NULL,0,NULL,NULL,NULL,1489374150154,NULL,NULL,NULL,NULL,NULL,1,1489374150154,'焳퉻饜潥',NULL,'',NULL);

UNLOCK TABLES;

/*Table structure for table `push_obj` */

DROP TABLE IF EXISTS `push_obj`;

CREATE TABLE `push_obj` (
  `id` varchar(50) NOT NULL,
  `obj_table_name` varchar(255) DEFAULT NULL,
  `obj_name` varchar(255) DEFAULT NULL,
  `is_able` int(2) DEFAULT '0' COMMENT '0为不可用，1为可用',
  `obj_abstract` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `push_obj` */

LOCK TABLES `push_obj` WRITE;

insert  into `push_obj`(`id`,`obj_table_name`,`obj_name`,`is_able`,`obj_abstract`) values ('10','change_state_user_student','学生',1,'学生表，user表关联'),('11','change_state_user_teacher','老师',1,'老师表，user表关联'),('2','change_state_org_department','部门',1,'部门'),('3','change_state_org_grade_class','年级',1,'test'),('4','change_state_org_school','学校',1,'lalala'),('5','change_state_org_school_type','学校类型',1,'职位'),('6','change_state_org_title','职位',1,'职位'),('7','change_state_ref_teacher_class','老师与班级的关系',1,'关联表'),('9','change_state_user_patriarch','父母',1,'家长表，user表关联');

UNLOCK TABLES;

/*Table structure for table `ref_platform_app` */

DROP TABLE IF EXISTS `ref_platform_app`;

CREATE TABLE `ref_platform_app` (
  `id` varchar(50) NOT NULL COMMENT '主键ID',
  `app_id` varchar(50) DEFAULT NULL COMMENT '应用id',
  `platform_id` varchar(50) DEFAULT NULL COMMENT '云平台id',
  `queues` varchar(255) DEFAULT NULL COMMENT '对列名称',
  `app_status` int(10) DEFAULT NULL COMMENT '云应用状态[0操作失败(推送失败),1已上线(推送成功),2修改待同步,3禁用下线(应用已经退出市场，和产品迭代没关系)]',
  `opt_status` int(10) DEFAULT '0' COMMENT '操作状态[0 操作失败，1操作成功]',
  `data_status` int(10) DEFAULT NULL COMMENT '数据初始化状态[0未初始化，1初始化]',
  `sync_status` int(10) DEFAULT NULL COMMENT '是否开启同步[0未开启，1开启]',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `ref_platform_app` */

LOCK TABLES `ref_platform_app` WRITE;

insert  into `ref_platform_app`(`id`,`app_id`,`platform_id`,`queues`,`app_status`,`opt_status`,`data_status`,`sync_status`,`create_time`,`update_time`) values ('ea125efab5b34fd7b7d6561a03369b4a','19ba0f029e7a4e18933b4a35aca84063','1','dgdg.anan',2,1,1,1,1488785204630,1488790796465),('33c8ad5894a849cda85cbaefd6bbc6b4','19ba0f029e7a4e18933b4a35aca84063','1','test.sns',2,1,1,1,1488785204617,1488790796452),('c0f067d72ea64272860a5f711d468dd','c0f067d72ea64272860a5f711d468dd7','1','test.ring',2,1,1,0,NULL,1488793628883),('68ef8d937a8c49c2ba8948a9971901b0','c0f067d72ea64272860a5f711d468dd7','1','ring.pingtai',2,1,1,0,1488772235657,1488793628885),('242c004b20cc4268860b423619964c56','c8a96fa1181b413293697af7c4fb5b03','1',NULL,2,1,1,0,1488789007004,1488789019145),('bd2668a8352f45a39889c79ccd0f6772','c8a96fa1181b413293697af7c4fb5b03','1','test.test',2,1,1,1,1488789007010,1488789019150);

UNLOCK TABLES;

/*Table structure for table `ref_queue_obj` */

DROP TABLE IF EXISTS `ref_queue_obj`;

CREATE TABLE `ref_queue_obj` (
  `id` varchar(50) NOT NULL,
  `plat_app_id` varchar(50) DEFAULT NULL,
  `obj_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ref_queue_obj` */

LOCK TABLES `ref_queue_obj` WRITE;

insert  into `ref_queue_obj`(`id`,`plat_app_id`,`obj_id`) values ('29aa4e1c56754c938d92a99333946133','68ef8d937a8c49c2ba8948a9971901b0','6'),('3b061bb4133f4d22ad45ad25620e2d18','68ef8d937a8c49c2ba8948a9971901b0','5'),('3bb9f3bd3a9e4a96b04d00eed2b03ae7','68ef8d937a8c49c2ba8948a9971901b0','7'),('567c6d4687de479cbfb1ad8cc5b5804e','68ef8d937a8c49c2ba8948a9971901b0','10'),('57a92f192a6046efa936322a8b29cb3e','68ef8d937a8c49c2ba8948a9971901b0','4'),('970523eb44be4c9fb1b50c485b515407','33c8ad5894a849cda85cbaefd6bbc6b4','37bb3183b47a4032a42ecd8bcd39acd3'),('9727d74c5d684d4c9b62ae220ba62206','bd2668a8352f45a39889c79ccd0f6772','7a73d2e5a2694d98a8565365ec9ab062'),('9b7f93b604a1400e8f1279a9a1d5d047','33c8ad5894a849cda85cbaefd6bbc6b4','897b6cd7715b4086b126fac54deccb64'),('ae8d35218fe74c1bababadd51a4e92e7','bd2668a8352f45a39889c79ccd0f6772','1ba4fe0ab0f2401ea066d107a674ac1d'),('b3bc386e3990474c9ac4d9370a8bd765','68ef8d937a8c49c2ba8948a9971901b0','9'),('cb485ee9b0e94afca06fd8bcc5d7f880','68ef8d937a8c49c2ba8948a9971901b0','3'),('d25afc12ba1a402e83bc7b34afd677fa','c0f067d72ea64272860a5f711d468dd','1ba4fe0ab0f2401ea066d107a674ac1d'),('d59a50c4bd37460fb986aeef4fccebc1','68ef8d937a8c49c2ba8948a9971901b0','1'),('db0aa87abfe14bdbbbedfaa4a311cfa9','ea125efab5b34fd7b7d6561a03369b4a','7a73d2e5a2694d98a8565365ec9ab062'),('e66ea57690124dd8ab57832becdab17b','68ef8d937a8c49c2ba8948a9971901b0','11'),('e80d407db3f84bcbac51ca84acf46434','68ef8d937a8c49c2ba8948a9971901b0','2'),('ed93c2c698c5488787cd74cc908ed0e6','33c8ad5894a849cda85cbaefd6bbc6b4','553de3da89ad4c2a929ab25dcb6ac7f0');

UNLOCK TABLES;

/*Table structure for table `route_other` */

DROP TABLE IF EXISTS `route_other`;

CREATE TABLE `route_other` (
  `id` varchar(50) NOT NULL,
  `sync_id` varchar(50) DEFAULT NULL,
  `sync_del_flag` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `route_other` */

LOCK TABLES `route_other` WRITE;

insert  into `route_other`(`id`,`sync_id`,`sync_del_flag`) values ('821164218bef47eabdf0ca7bb7bb6483','136','test.test.Ã¥ÂÂºÃ¦ÂÂ¬Ã¤Â¿Â¡Ã¦ÂÂ¯Ã¨Â¡Â¨'),('85b37b86aab247f583f1a8db3ade1047','135','test.test.åååå'),('a31ce7fc3c8f4106a99a1d8eedc376b6','135','test.test.Ã¥ÂÂºÃ¦ÂÂ¬Ã¤Â¿Â¡Ã¦ÂÂ¯Ã¨Â¡Â¨'),('a83ffb29e05d4cffa0dcb37b228fff05','136','test.test.dd顶顶顶'),('c0c5fd66f5134a34ad3d4930c051fab2','135','test.test.dd顶顶顶'),('e1714f4aa0634745a765ceb8a2288e54','136','test.test.åååå');

UNLOCK TABLES;

/*Table structure for table `route_patriarch` */

DROP TABLE IF EXISTS `route_patriarch`;

CREATE TABLE `route_patriarch` (
  `id` varchar(50) NOT NULL,
  `sync_id` varchar(50) DEFAULT NULL,
  `sync_del_flag` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `route_patriarch` */

LOCK TABLES `route_patriarch` WRITE;

UNLOCK TABLES;

/*Table structure for table `route_student` */

DROP TABLE IF EXISTS `route_student`;

CREATE TABLE `route_student` (
  `id` varchar(50) NOT NULL,
  `sync_id` varchar(50) DEFAULT NULL,
  `sync_del_flag` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `route_student` */

LOCK TABLES `route_student` WRITE;

insert  into `route_student`(`id`,`sync_id`,`sync_del_flag`) values ('0910108c84d94b69ae8da8003724af6d','1303','dgdg.anan.duixiang1'),('1456ce53b7b24a53b4271bbd258d3b5f','1301','test.sns.duixiang2'),('2484bfddc3db45288488649d77c54a98','1302','dgdg.anan.duixiang1'),('3178755764a1472091b709a18481c209','1301','dgdg.anan.duixiang1'),('c60d842becf84bc39453ff35bc0c6d03','1302','test.sns.duixiang2'),('f7544cb1c5784de7a9cbb96dbaabbe5a','1303','test.sns.duixiang2');

UNLOCK TABLES;

/*Table structure for table `route_teacher` */

DROP TABLE IF EXISTS `route_teacher`;

CREATE TABLE `route_teacher` (
  `id` varchar(50) NOT NULL,
  `sync_id` varchar(50) DEFAULT NULL,
  `sync_del_flag` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `route_teacher` */

LOCK TABLES `route_teacher` WRITE;

UNLOCK TABLES;

/*Table structure for table `route_teacher_class` */

DROP TABLE IF EXISTS `route_teacher_class`;

CREATE TABLE `route_teacher_class` (
  `id` varchar(50) NOT NULL,
  `teacher_id` varchar(50) DEFAULT NULL,
  `class_id` varchar(50) DEFAULT NULL,
  `sync_del_flag` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `route_teacher_class` */

LOCK TABLES `route_teacher_class` WRITE;

insert  into `route_teacher_class`(`id`,`teacher_id`,`class_id`,`sync_del_flag`) values ('e3353acab86742349c0d2cab939b24c3','aaa','233','test.test.ref');

UNLOCK TABLES;

/*Table structure for table `sync_mark_time` */

DROP TABLE IF EXISTS `sync_mark_time`;

CREATE TABLE `sync_mark_time` (
  `id` varchar(50) NOT NULL,
  `table_name` varchar(50) DEFAULT NULL,
  `mark_min_time` bigint(20) DEFAULT NULL,
  `plat_identifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sync_mark_time` */

LOCK TABLES `sync_mark_time` WRITE;

insert  into `sync_mark_time`(`id`,`table_name`,`mark_min_time`,`plat_identifier`) values ('496ec99874c84d05b5587aa08c913e7e','sync_org_school',1487583780418,'plat'),('e80afab119fb4c0686717ad0683cb578','sync_class_section',1488244355918,'test'),('fe5bc4f71f8146a496ca18b16b7e42eb','sync_teacher_class',1488166097542,'test');

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
