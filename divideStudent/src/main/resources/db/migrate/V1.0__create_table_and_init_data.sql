# noinspection SqlNoDataSourceInspectionForFile
/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50713
Source Host           : 127.0.0.1:3306
Source Database       : gk_platform

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2016-07-28 14:25:54
*/
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_area
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_menu_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_organize
-- ----------------------------
DROP TABLE IF EXISTS `sys_organize`;
CREATE TABLE `sys_organize` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_organize
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限名',
  `useable` int(11) DEFAULT NULL COMMENT '是否正在使用',
  `del_flag` int(11) DEFAULT NULL COMMENT '是否删除',
  `remarks` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'role描述',
  `create_by` int(11) DEFAULT NULL COMMENT '创建者',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `udpate_by` int(11) DEFAULT NULL COMMENT '修改者ID',
  `update_time` bigint(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录帐号',
  `pwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录密码',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '姓名',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '头像地址',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `remarks` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '描述',
  `del_flag` int(11) DEFAULT NULL COMMENT '是否删除',
  `create_by` int(11) DEFAULT NULL COMMENT '创建者ID',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '修改者ID',
  `update_time` bigint(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', 'BsKaDWZj1FpfS8LopdhLSQ==', null, null, null, null, null, null, '1469502834638', null, null);
INSERT INTO `sys_user` VALUES ('2', 'b', 'bqcjSC75Bu3ItSSGUb4v5Q==', null, null, null, null, null, null, '1469502834638', null, null);
INSERT INTO `sys_user` VALUES ('3', 'c', 'MNyBPQbsCvk+UNtnDwQkEA==', null, null, null, null, null, null, '1469515629542', null, null);
INSERT INTO `sys_user` VALUES ('4', 'cc', 'r7V0L3CkvkV9JiVzfqcb5Q==', null, null, null, null, null, null, '1469666916374', null, null);
INSERT INTO `sys_user` VALUES ('5', 'aa', 'NAHAifXAksPu7yJJJXN0yQ==', null, null, null, null, null, null, '1469676340500', null, null);
INSERT INTO `sys_user` VALUES ('6', 'admin1', 'NUWkLbfs9nc1XyNpzWHCZA==', null, null, null, null, null, null, '1469676984327', null, null);
INSERT INTO `sys_user` VALUES ('7', 'dd', 'udFCyKV2k3G65CTWZZ1rzA==', null, null, null, null, null, null, '1469701075182', null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
