/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : nbms

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2017-09-02 10:08:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `book_type_his`
-- ----------------------------
DROP TABLE IF EXISTS `book_type_his`;
CREATE TABLE `book_type_his` (
  `BOOK_TYPE_ID` char(2) DEFAULT NULL,
  `BOOK_TYPE_NAME` varchar(10) DEFAULT NULL,
  `BOOK_TYPE_PARENT_ID` char(32) DEFAULT NULL,
  `BOOK_TYPE_LEVEL` int(10) DEFAULT NULL,
  `BOOK_TYPE_SEQ` varchar(4) DEFAULT '',
  `BOOK_TYPE_REMARK` varchar(10) DEFAULT NULL,
  `USER_ID` char(32) DEFAULT NULL,
  `OPERATE_TIME` datetime DEFAULT NULL,
  `OPERATE_TYPE` char(1) DEFAULT NULL,
  `OPERATE_REMARK` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book_type_his
-- ----------------------------
INSERT INTO `book_type_his` VALUES ('00', '全部', null, null, '1', null, null, null, null, null);
INSERT INTO `book_type_his` VALUES ('00', '全部', null, null, '1', 'bbb', '111', '2016-07-30 11:51:00', 'U', 'aaa');
INSERT INTO `book_type_his` VALUES ('00', '全部', 'ROOT', null, '1', 'bbb', '111', '2016-07-30 22:20:26', 'U', 'bbb');
INSERT INTO `book_type_his` VALUES ('00', '全部', 'ROOT', null, '1', 'aaa', '111', '2016-07-30 22:27:19', 'U', 'bbb');
INSERT INTO `book_type_his` VALUES ('00', '全部', 'ROOT', '0', '1', 'aaa', '111', '2016-08-06 00:07:39', 'U', 'bbb');
INSERT INTO `book_type_his` VALUES ('00', '全部', null, null, '1', 'aaa', '111', '2016-08-06 00:07:39', 'U', 'bbb');
INSERT INTO `book_type_his` VALUES ('00', '全部', 'ROOT', '0', '1', 'aaa', '111', '2016-08-06 00:09:20', 'U', 'bbb');
INSERT INTO `book_type_his` VALUES ('00', '全部', null, null, '1', 'aaa', '111', '2016-08-06 00:09:20', 'U', 'bbb');
INSERT INTO `book_type_his` VALUES ('00', '全部', null, null, '1', 'aaa', '111', '2016-08-06 00:09:20', 'U', 'bbb');

-- ----------------------------
-- Table structure for `t_book`
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `BOOK_ID` varchar(32) NOT NULL COMMENT '图书ID',
  `BOOK_NAME` varchar(10) NOT NULL COMMENT '图书名称',
  `BOOK_AUTHOR` varchar(10) NOT NULL COMMENT '图书作者',
  `BOOK_IMAGE` varchar(10) DEFAULT NULL COMMENT '图书图片',
  `BOOK_PUBLIC_TIME` datetime DEFAULT NULL COMMENT '图书出版时间',
  `BOOK_TYPE_ID` varchar(32) DEFAULT NULL COMMENT '图书类型ID',
  `BOOK_PRICE` int(11) DEFAULT NULL COMMENT '图书价格',
  `BOOK_IS_ONLINE` char(1) DEFAULT '0' COMMENT '图书是否上线 1 上线 0 下线',
  PRIMARY KEY (`BOOK_ID`),
  KEY `BOOK_TYPE_ID` (`BOOK_TYPE_ID`) USING HASH,
  KEY `BOOK_NAME` (`BOOK_NAME`),
  KEY `BOOK_AUTHOR` (`BOOK_AUTHOR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES ('0392be5e79dc4613a688e1bf11e2def2', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:09:06', null, null, '0');
INSERT INTO `t_book` VALUES ('0457a66360b04bd0b5d6f6e703f5ad7f', 'Java初级程序设计', 'Unkonw', null, '2016-08-11 21:55:29', null, null, '0');
INSERT INTO `t_book` VALUES ('0573e0b87b0a4533a46b09a1c997a604', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:07:39', null, null, '0');
INSERT INTO `t_book` VALUES ('07ded0c9ded5468fbb5ababb31d27731', 'Java初级程序设计', 'Unkonw', null, '2016-07-30 12:45:20', null, null, '0');
INSERT INTO `t_book` VALUES ('0842579b9fc84c49978f1f654843f328', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:09:06', null, null, '0');
INSERT INTO `t_book` VALUES ('09d2cd239b0d4b0fa9710899c2caa6ce', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:38:24', null, null, '0');
INSERT INTO `t_book` VALUES ('0f6ef8883e554610a47a63addd6242c4', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:07:39', null, null, '0');
INSERT INTO `t_book` VALUES ('0fb3f126890145e99081f1641fa0285d', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:36:21', null, null, '0');
INSERT INTO `t_book` VALUES ('144dbb8f1a6e42a3be2d2ac02b396e24', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 23:02:48', null, null, '0');
INSERT INTO `t_book` VALUES ('150ec132a68a4a95a810bf99c94f10ba', 'Java高级程序设计', 'aaa', null, '2016-07-29 21:37:00', null, null, '0');
INSERT INTO `t_book` VALUES ('1b814138ff594b58aef3958173e9d0ec', 'Java高级程序设计', 'aaa', null, '2016-07-29 21:37:00', null, null, '0');
INSERT INTO `t_book` VALUES ('1ee999c091864ababb1a0ed8aaf13318', 'Java初级程序设计', 'Unkonw', null, '2016-07-30 12:32:54', null, null, '0');
INSERT INTO `t_book` VALUES ('20c891092fe5435799e1db9ccbe27459', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:35:21', null, null, '0');
INSERT INTO `t_book` VALUES ('2861933cea7b425e9a53381f20af4fbb', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:09:06', null, null, '0');
INSERT INTO `t_book` VALUES ('33b5720decce4babb902f34055c5980f', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:09:06', null, null, '0');
INSERT INTO `t_book` VALUES ('34cb1a6b267344bab3551b7eb4155710', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:32:57', null, null, '0');
INSERT INTO `t_book` VALUES ('3697f0c820bb46ca8e0ef9da54418947', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:07:39', null, null, '0');
INSERT INTO `t_book` VALUES ('3f1ac6c8550648b29db103d8beb13f2e', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:09:06', null, null, '0');
INSERT INTO `t_book` VALUES ('4185eefe42e348f898388fc32d07000a', 'Java高级程序设计', 'aaa', null, '2016-07-29 21:37:00', null, null, '0');
INSERT INTO `t_book` VALUES ('438b7fb0c9fe46728cfdaed3982f6938', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:33:16', null, null, '0');
INSERT INTO `t_book` VALUES ('501b66cf275a4270880704710de9455a', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:38:24', null, null, '0');
INSERT INTO `t_book` VALUES ('51db2b49d2c84443ba9e915cb59922d9', 'Java高级程序设计', 'aaa', null, '2016-07-29 21:37:00', null, null, '0');
INSERT INTO `t_book` VALUES ('555389fb18ba4f92accd23142b9183c8', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:32:40', null, null, '0');
INSERT INTO `t_book` VALUES ('5a60c47e1a95430780d065a277c889b7', 'Java初级程序设计', 'Unkonw', null, '2016-08-11 21:41:35', null, null, '0');
INSERT INTO `t_book` VALUES ('5f7e166914604e36ab5e6c3bed48aa34', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 23:02:48', null, null, '0');
INSERT INTO `t_book` VALUES ('6ceea04220b24a16a81d8e11fa48cee8', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:36:20', null, null, '0');
INSERT INTO `t_book` VALUES ('6d00ada1d9014e1f8e782dcee80380e6', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:07:39', null, null, '0');
INSERT INTO `t_book` VALUES ('70e79187651b45579d9ea8cc867bba63', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:35:21', null, null, '0');
INSERT INTO `t_book` VALUES ('7126a06738b6426194c9d0521d777b86', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:36:21', null, null, '0');
INSERT INTO `t_book` VALUES ('713ae9587e44439988dbd24bb50b813b', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:40:08', null, null, '0');
INSERT INTO `t_book` VALUES ('72fcac83c13e4fc0a18474a2dd8eb590', 'Java初级程序设计', 'Unkonw', null, '2016-08-11 21:42:15', null, null, '0');
INSERT INTO `t_book` VALUES ('76c4b7b57f4d444d8d4d8c78ab363f01', 'Java高级程序设计', 'aaa', null, '2016-07-29 21:37:00', null, null, '0');
INSERT INTO `t_book` VALUES ('77549cabe4a747bdb9b7a103584b8abe', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:07:39', null, null, '0');
INSERT INTO `t_book` VALUES ('7bbf43a8f0644b0fb708f03ad804c00c', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:32:40', null, null, '0');
INSERT INTO `t_book` VALUES ('7dcac2daf06f4354881d5d0ddc9929ea', 'Java初级程序设计', 'Unkonw', null, '2016-08-11 21:49:14', null, null, '0');
INSERT INTO `t_book` VALUES ('84088b0d0aff45e38f70e03cbeb33a90', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:36:20', null, null, '0');
INSERT INTO `t_book` VALUES ('85606bc1eb4f4e7883ee642533e25118', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:35:21', null, null, '0');
INSERT INTO `t_book` VALUES ('87d2c906d5b5445db9dd1f20ddf2098c', 'Java初级程序设计', 'Unkonw', null, '2016-08-11 21:55:29', null, null, '0');
INSERT INTO `t_book` VALUES ('884fee0ce6594bd2b414d11e14ca4a44', 'Java高级程序设计', 'aaa', null, '2016-07-29 21:37:00', null, null, '0');
INSERT INTO `t_book` VALUES ('88c72464fcaa4b858355e8e91960baa4', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:09:06', null, null, '0');
INSERT INTO `t_book` VALUES ('8c3b6e84cfb74c81bed9e0380b69e579', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:35:21', null, null, '0');
INSERT INTO `t_book` VALUES ('8dc90ed63a59484ab6e1182cf9513fd9', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:07:39', null, null, '0');
INSERT INTO `t_book` VALUES ('932f490f882e4c44b10db6de64444823', 'Java初级程序设计', 'Unkonw', null, '2016-08-01 23:18:12', null, null, '0');
INSERT INTO `t_book` VALUES ('94f8646b6fae4a8198f4bbfbc7777f8f', 'Java高级程序设计', 'aaa', null, '2016-07-29 21:37:00', null, null, '0');
INSERT INTO `t_book` VALUES ('9a9af9415e334970bf2d2e9f139dfadf', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:40:08', null, null, '0');
INSERT INTO `t_book` VALUES ('9d260f59265a432d989ee5a306cbadff', 'Java高级程序设计', 'aaa', null, '2016-07-29 21:37:00', null, null, '0');
INSERT INTO `t_book` VALUES ('9e852ac42b34455b82a35e319b62c2ff', 'Java高级程序设计', 'aaa', null, '2016-07-29 21:37:00', null, null, '0');
INSERT INTO `t_book` VALUES ('9eb76d6176e04c9a8d109d7aede09cba', 'Java初级程序设计', 'Unkonw', null, '2016-07-30 12:32:54', null, null, '0');
INSERT INTO `t_book` VALUES ('aad57e7f4d174f369781b995d99369ce', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:32:40', null, null, '0');
INSERT INTO `t_book` VALUES ('affe86b96121473e9351164de98204ae', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:35:45', null, null, '0');
INSERT INTO `t_book` VALUES ('b0c69d8353a14c0fa9975a750ce6ff2a', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:33:16', null, null, '0');
INSERT INTO `t_book` VALUES ('b23b200b2d2d4783a253232acd354f97', 'Java高级程序设计', 'aaa', null, '2016-07-29 21:37:00', null, null, '0');
INSERT INTO `t_book` VALUES ('baefa2cb48ee4f5c89003319a9cdc200', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:09:06', null, null, '0');
INSERT INTO `t_book` VALUES ('bcc29e95df7d4574b8c4af618868a882', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:07:39', null, null, '0');
INSERT INTO `t_book` VALUES ('bcfd90b92ec14d80987da33a95d4b5a4', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:35:45', null, null, '0');
INSERT INTO `t_book` VALUES ('c1258dca59bc4b4a99ed227dfdb49f79', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:09:06', null, null, '0');
INSERT INTO `t_book` VALUES ('c19f7a61fd0f43e3944128e6bd46e655', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:07:39', null, null, '0');
INSERT INTO `t_book` VALUES ('c6657d2bebd646e3889281f046665c6f', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:09:06', null, null, '0');
INSERT INTO `t_book` VALUES ('c7fc8b9ca6474154b8426310363a61ae', 'Java初级程序设计', 'Unkonw', null, '2016-08-11 21:49:14', null, null, '0');
INSERT INTO `t_book` VALUES ('cb960a0daefb49af810e2cec087b649b', 'Java初级程序设计', 'Unkonw', null, '2016-08-11 21:41:35', null, null, '0');
INSERT INTO `t_book` VALUES ('cddaa0e0eb2f413e86ad833d3f49233f', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:07:39', null, null, '0');
INSERT INTO `t_book` VALUES ('d07c715809884503a80a5a08bc965c4c', 'Java初级程序设计', 'Unkonw', null, '2016-07-30 12:45:20', null, null, '0');
INSERT INTO `t_book` VALUES ('d682db562f5540eab9d05ed259a5fca1', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:32:40', null, null, '0');
INSERT INTO `t_book` VALUES ('d6de1793833e4aaaa026b69c18994828', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:32:57', null, null, '0');
INSERT INTO `t_book` VALUES ('d7e2438a3b8e43599d4fe9bd308f5733', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:32:57', null, null, '0');
INSERT INTO `t_book` VALUES ('e201c47e65b340cea7e7486b15ab9b09', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:07:39', null, null, '0');
INSERT INTO `t_book` VALUES ('e7726dee046b45bbb7f0ba80b5cfb3e0', 'Java初级程序设计', 'Unkonw', null, '2016-08-11 21:42:15', null, null, '0');
INSERT INTO `t_book` VALUES ('e8c1b7f1a4ac45d4a7b76dd39f404d61', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:35:45', null, null, '0');
INSERT INTO `t_book` VALUES ('ec20c647482044a5baf4b1222669bda7', 'Java初级程序设计', 'Unkonw', null, '2016-08-01 23:18:12', null, null, '0');
INSERT INTO `t_book` VALUES ('f0fece458d2c465eb3e758a06d57f96a', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:38:24', null, null, '0');
INSERT INTO `t_book` VALUES ('f4687884da9940cbbdc5e7dea759db6f', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:35:45', null, null, '0');
INSERT INTO `t_book` VALUES ('f5d822f6efff468d8f41adf90bf41b79', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:33:16', null, null, '0');
INSERT INTO `t_book` VALUES ('f6f8e59ccf18405497ab2d941d7a4a96', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:32:57', null, null, '0');
INSERT INTO `t_book` VALUES ('f8eb4f412b214e13a91f65397bf11a27', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:38:24', null, null, '0');
INSERT INTO `t_book` VALUES ('f90d47ed8b2e4494a565e702042deac5', 'Java初级程序设计', 'Unkonw', null, '2016-09-20 22:33:16', null, null, '0');
INSERT INTO `t_book` VALUES ('fb3e0e4a991b44a18427129a4b10086c', 'Java高级程序设计', 'aaa', null, '2016-08-06 00:09:06', null, null, '0');

-- ----------------------------
-- Table structure for `t_book_his`
-- ----------------------------
DROP TABLE IF EXISTS `t_book_his`;
CREATE TABLE `t_book_his` (
  `BOOK_ID` char(32) DEFAULT NULL COMMENT '图书ID',
  `BOOK_NAME` varchar(10) DEFAULT NULL COMMENT '图书名称',
  `BOOK_AUTHOR` varchar(10) DEFAULT NULL COMMENT '图书作者',
  `BOOK_TYPE_ID` char(32) DEFAULT NULL COMMENT '图书类型ID',
  `BOOK_IMAGE` varchar(10) DEFAULT NULL COMMENT '图书图片',
  `BOOK_PUBLIC_TIME` varchar(10) DEFAULT NULL COMMENT '图书出版时间',
  `BOOK_PRICE` int(11) DEFAULT NULL COMMENT '图书价格',
  `USER_ID` char(32) DEFAULT NULL COMMENT '入库人ID',
  `OPERATE_TIME` varchar(10) DEFAULT NULL COMMENT '入库时间',
  `OPERATE_TYPE` char(1) DEFAULT NULL COMMENT '入库类型',
  `OPERATE_REMARK` varchar(10) DEFAULT NULL COMMENT '入库描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book_his
-- ----------------------------
INSERT INTO `t_book_his` VALUES ('3f05a4c5f43f46e5ae20552db5bc59e4', null, null, null, 'aa.jpg', null, null, null, null, 'U', null);
INSERT INTO `t_book_his` VALUES ('3f05a4c5f43f46e5ae20552db5bc59e4', null, null, null, 'bb.jpg', null, null, null, null, 'U', null);
INSERT INTO `t_book_his` VALUES ('3f05a4c5f43f46e5ae20552db5bc59e4', null, null, null, 'aa.jpg', null, null, null, null, 'U', null);
INSERT INTO `t_book_his` VALUES ('3f05a4c5f43f46e5ae20552db5bc59e4', 'Java编程', 'aaa', '8967c83c209d4d0aa63e03b378e0642b', 'aa.jpg', '2016-04-27', null, null, null, 'U', null);
INSERT INTO `t_book_his` VALUES ('3f05a4c5f43f46e5ae20552db5bc59e4', 'Java编程', 'aaa', '8967c83c209d4d0aa63e03b378e0642b', 'aa.jpg', '2016-04-27', null, null, null, 'U', null);

-- ----------------------------
-- Table structure for `t_book_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_book_type`;
CREATE TABLE `t_book_type` (
  `BOOK_TYPE_ID` char(4) NOT NULL,
  `BOOK_TYPE_NAME` varchar(10) NOT NULL,
  `BOOK_TYPE_PARENT_ID` char(4) DEFAULT NULL,
  `BOOK_TYPE_LEVEL` int(10) DEFAULT NULL,
  `BOOK_TYPE_SEQ` varchar(4) NOT NULL DEFAULT '',
  `BOOK_TYPE_REMARK` varchar(10) DEFAULT NULL,
  KEY `BOOK_TYPE_NAME` (`BOOK_TYPE_NAME`),
  KEY `BOOK_TYPE_ID` (`BOOK_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book_type
-- ----------------------------
INSERT INTO `t_book_type` VALUES ('00', '全部', 'ROOT', '0', '1', 'aaa');
INSERT INTO `t_book_type` VALUES ('001', '文学类', '00', '1', '1', '文学类');
INSERT INTO `t_book_type` VALUES ('002', '政治类', '00', '1', '2', '政治类');
INSERT INTO `t_book_type` VALUES ('003', '地理类', '00', '1', '3', '地理类');
INSERT INTO `t_book_type` VALUES ('0001', '精品美文', '001', '2', '1', '精品美文');
INSERT INTO `t_book_type` VALUES ('0002', '诗歌集', '001', '2', '2', '诗歌集');
INSERT INTO `t_book_type` VALUES ('0003', '阅读', '001', '2', '3', '阅读');

-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `MENU_ID` char(14) NOT NULL COMMENT '菜单ID',
  `MENU_PARENT_ID` char(34) DEFAULT NULL COMMENT '父级菜单ID',
  `MENU_NAME` varchar(10) NOT NULL COMMENT '菜单名称',
  `MENU_URL` varchar(100) NOT NULL COMMENT '菜单URL',
  `MENU_TYPE` char(1) DEFAULT NULL,
  `MENU_VALID` char(1) DEFAULT '1' COMMENT '菜单是否可见 1有效: 0:无效',
  `MENU_VISIBLE` char(1) DEFAULT NULL,
  `MENU_LEVEL` int(11) NOT NULL COMMENT '菜单层级',
  `MENU_CREATE_TIME` datetime DEFAULT NULL COMMENT '菜单创建时间',
  `MENU_MODIFY_TIME` datetime DEFAULT NULL,
  `MENU_ACCOUNT` varchar(32) DEFAULT NULL COMMENT '创建人ID',
  `MENU_REMARK` varchar(10) DEFAULT NULL COMMENT '菜单说明',
  `MENU_SEQ` varchar(10) DEFAULT NULL,
  `MENU_OPCODE` char(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('M1000000001', 'M1000000000', 'ROOT', '#', '0', '1', '1', '0', '2016-10-27 22:08:33', null, null, 'ROOT', null, '10000');
INSERT INTO `t_menu` VALUES ('M1000000002', 'M1000000001', '系统管理', '#', '0', '1', '1', '1', '2016-10-27 22:08:33', '2016-11-04 21:13:31', null, '系统管理', '1', '11000');
INSERT INTO `t_menu` VALUES ('M1000000003', 'M1000000002', '菜单管理', '/system/menu/index.action', '0', '1', '1', '2', '2016-10-27 22:08:33', null, null, '菜单管理', '1', '11100');
INSERT INTO `t_menu` VALUES ('M1000000004', 'M1000000002', '角色管理', '/system/role/index.action', '0', '1', '1', '2', '2016-10-27 22:18:17', null, null, '角色管理', '2', '11200');
INSERT INTO `t_menu` VALUES ('M1000000005', 'M1000000002', '权限管理', '/system/permission/index.action', '0', '1', '1', '2', '2016-10-27 22:46:50', null, null, '权限管理', '3', '11300');
INSERT INTO `t_menu` VALUES ('M1000000006', 'M1000000002', '组织管理', '/system/org/index.action', '0', '1', '1', '2', '2016-10-29 09:50:12', '2016-11-08 22:59:41', null, '组织管理', '4', '11400');
INSERT INTO `t_menu` VALUES ('M1000000011', 'M1000000041', '图书管理', '/basic/book/index.action', '0', '1', '1', '2', '2017-05-28 17:27:40', null, null, '图书管理', '1', '12100');
INSERT INTO `t_menu` VALUES ('M1000000012', 'M1000000003', '菜单查询', '/system/menu/querya.action', '1', '1', '0', '3', '2017-05-28 17:27:43', null, null, '菜单查询', '1', '11110');
INSERT INTO `t_menu` VALUES ('M1000000013', 'M1000000003', '菜单新建', '/system/menu/create.action', '1', '1', '0', '3', '2017-05-28 17:27:47', null, null, '菜单新建', '2', '11120');
INSERT INTO `t_menu` VALUES ('M1000000014', 'M1000000002', '用户管理', '/system/user/index.action', '0', '1', '1', '2', '2017-04-16 13:03:11', null, null, '用户管理', '5', '11500');
INSERT INTO `t_menu` VALUES ('M1000000022', 'M1000000002', '登录退出管理', '#', '0', '1', '1', '2', '2017-05-28 16:20:33', null, null, '登录退出管理', '6', '11600');
INSERT INTO `t_menu` VALUES ('M1000000023', 'M1000000022', '系统登录页', '/system/loginOut/toLogin.action', '0', '1', '1', '3', '2017-05-28 16:27:01', null, null, '系统登录页', '1', '13100');
INSERT INTO `t_menu` VALUES ('M1000000024', 'M1000000022', '系统登录', '/system/loginOut/login.action', '1', '1', '0', '3', '2017-05-28 16:28:00', null, null, '系统登录', '2', '13200');
INSERT INTO `t_menu` VALUES ('M1000000025', 'M1000000022', '系统首页', ' /system/loginOut/content.action', '0', '1', '1', '3', '2017-05-28 16:30:28', null, null, '系统首页', '3', '13300');
INSERT INTO `t_menu` VALUES ('M1000000026', 'M1000000004', '角色创建', ' /system/role/create.action', '1', '1', '0', '3', '2017-05-28 16:43:21', null, null, '角色创建', '1', '12100');
INSERT INTO `t_menu` VALUES ('M1000000027', 'M1000000004', '角色修改', '/system/role/modify.action', '1', '1', '0', '3', '2017-05-28 16:44:59', null, null, '角色修改', '2', '12200');
INSERT INTO `t_menu` VALUES ('M1000000029', 'M1000000004', '角色查询', '/system/role/query.action', '1', '1', '0', '3', '2017-05-28 16:45:02', null, null, '角色查询', '4', '12400');
INSERT INTO `t_menu` VALUES ('M1000000028', 'M1000000004', '角色删除', '/system/role/delete.action', '1', '1', '0', '3', '2017-05-28 16:45:01', null, null, '角色删除', '3', '12300');
INSERT INTO `t_menu` VALUES ('M1000000030', 'M1000000003', '菜单修改', ' /system/menu/modify.action', '1', '1', '0', '3', '2017-05-28 16:48:53', null, null, '菜单修改', '3', '11130');
INSERT INTO `t_menu` VALUES ('M1000000031', 'M1000000003', '菜单删除', ' /system/menu/delete.action', '1', '1', '0', '3', '2017-05-28 16:48:54', null, null, '菜单删除', '4', '11140');
INSERT INTO `t_menu` VALUES ('M1000000032', 'M1000000022', '系统菜单加载', '/system/loginOut/navigation.action', '1', '1', '0', '3', '2017-05-28 16:54:25', null, null, '系统菜单加载', '4', '13400');
INSERT INTO `t_menu` VALUES ('M1000000033', 'M1000000006', '组织创建', '/system/org/create.action', '1', '1', '0', '3', '2017-05-28 17:23:21', null, null, '组织创建', '1', '11410');
INSERT INTO `t_menu` VALUES ('M1000000034', 'M1000000006', '组织修改', '/system/org/modify.action', '1', '1', '0', '3', '2017-05-28 17:23:22', null, null, '组织修改', '2', '11420');
INSERT INTO `t_menu` VALUES ('M1000000035', 'M1000000006', '组织删除', '/system/org/delete.action', '1', '1', '0', '3', '2017-05-28 17:23:22', null, null, '组织删除', '3', '11430');
INSERT INTO `t_menu` VALUES ('M1000000036', 'M1000000006', '组织查询', '/system/org/query.action', '1', '1', '0', '3', '2017-05-28 17:23:23', null, null, '组织查询', '4', '11440');
INSERT INTO `t_menu` VALUES ('M1000000037', 'M1000000011', '图书入库', ' /basic/book/create.action', '1', '1', '0', '3', '2017-05-28 17:29:41', null, null, '图书入库', '1', '12110');
INSERT INTO `t_menu` VALUES ('M1000000038', 'M1000000011', '图书修改', ' /basic/book/modify.action', '1', '1', '0', '3', '2017-05-28 17:29:42', null, null, '图书修改', '2', '12120');
INSERT INTO `t_menu` VALUES ('M1000000039', 'M1000000011', '图书删除', '/basic/book/modify.action', '1', '1', '0', '3', '2017-05-28 17:29:42', null, null, '图书删除', '3', '12130');
INSERT INTO `t_menu` VALUES ('M1000000040', 'M1000000011', '图书查询', '/basic/book/query.action', '1', '1', '0', '3', '2017-05-28 17:29:43', null, null, '图书查询', '4', '12140');
INSERT INTO `t_menu` VALUES ('M1000000041', 'M1000000001', '图书业务管理', '#', '0', '1', '1', '1', '2017-05-28 17:38:24', null, null, '图书业务管理', '2', '12000');
INSERT INTO `t_menu` VALUES ('M1000000042', 'M1000000022', '系统退出', '/system/loginOut/logout.action', '1', '1', '0', '3', '2017-05-30 16:40:48', null, null, '系统退出', '5', '13500');
INSERT INTO `t_menu` VALUES ('M1000000044', 'M1000000005', '权限新建', '/system/permission/create.action', '1', '1', '0', '3', '2017-05-30 17:02:07', null, null, '权限新建', '1', '11310');
INSERT INTO `t_menu` VALUES ('M1000000045', 'M1000000005', '权限修改', '/system/permission/modify.action', '1', '1', '0', '3', '2017-05-30 17:03:26', null, null, '权限修改', '2', '11320');
INSERT INTO `t_menu` VALUES ('M1000000046', 'M1000000005', '权限删除', '/system/permission/delete.action', '1', '1', '0', '3', '2017-05-30 17:04:22', null, null, '权限删除', '3', '11330');
INSERT INTO `t_menu` VALUES ('M1000000047', 'M1000000005', '权限查询', '/system/permission/query.action', '1', '1', '0', '3', '2017-05-30 17:05:13', null, null, '权限查询', '4', '11340');
INSERT INTO `t_menu` VALUES ('M1000000048', 'M1000000014', '用户新建', '/system/user/create.action', '1', '1', '0', '3', '2017-05-30 17:12:02', null, null, '用户新建', '1', '11510');
INSERT INTO `t_menu` VALUES ('M1000000049', 'M1000000014', '用户修改', '/system/user/modify.action', '1', '1', '0', '3', '2017-05-30 17:17:41', null, null, '用户修改', '2', '11520');
INSERT INTO `t_menu` VALUES ('M1000000050', 'M1000000014', '用户查询', '/system/user/query.action', '1', '1', '1', '3', '2017-09-02 09:04:21', null, null, '用户查询', '3', '11521');

-- ----------------------------
-- Table structure for `t_org`
-- ----------------------------
DROP TABLE IF EXISTS `t_org`;
CREATE TABLE `t_org` (
  `ORG_ID` varchar(10) NOT NULL,
  `ORG_PARENT_ID` varchar(10) DEFAULT NULL,
  `ORG_NAME` varchar(20) DEFAULT NULL,
  `ORG_IS_VALID` char(1) DEFAULT NULL,
  `ORG_CREATE_TIME` datetime DEFAULT NULL,
  `ORG_MODIFY_TIME` datetime DEFAULT NULL,
  `ORG_USER_ID` varchar(32) DEFAULT NULL,
  `ORG_USER_NAME` varchar(20) DEFAULT NULL,
  `ORG_REMARK` varchar(50) DEFAULT NULL,
  `ORG_SEQ` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_org
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `ROLE_ID` char(32) NOT NULL,
  `TOLE_PATENT_ID` varchar(32) DEFAULT NULL,
  `ROLE_NAME` varchar(10) DEFAULT NULL,
  `ROLE_CREATE_TIME` datetime DEFAULT NULL,
  `ROLE_MODIFY_TIME` datetime DEFAULT NULL,
  `ROLE_VISIBLE` char(255) DEFAULT NULL,
  `ROLE_VALID` char(1) DEFAULT NULL,
  `ROLE_LEVEL` int(11) DEFAULT NULL,
  `ROLE_SEQ` int(11) DEFAULT NULL,
  `ROLE_ACCOUNT` varchar(10) DEFAULT NULL,
  `ROLE_REMARK` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('R1000000001', 'R1000000000', '管理员', null, null, '1', '1', null, '1', null, '管理员');
INSERT INTO `t_role` VALUES ('R1000000004', 'R1000000001', '学生', '2017-06-27 22:11:19', null, '1', '1', null, '1', null, '学生');
INSERT INTO `t_role` VALUES ('R1000000011', 'R1000000001', '老师', '2017-08-22 23:27:42', null, '1', '1', '1', '2', null, '老师');

-- ----------------------------
-- Table structure for `t_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `PERM_ID` varchar(32) DEFAULT NULL,
  `ROLE_ID` char(32) DEFAULT NULL,
  `MENU_ID` char(32) DEFAULT NULL,
  `VALUE` varchar(20) DEFAULT NULL,
  `SEQ` int(11) DEFAULT NULL,
  `VALID` char(1) DEFAULT NULL,
  `VISIBLE` char(1) DEFAULT NULL,
  `ACCOUNT` varchar(10) DEFAULT NULL,
  `REMARK` varchar(10) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `MODIFY_TIME` datetime DEFAULT NULL,
  KEY `ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `t_role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('P1000000003', 'R1000000001', 'M1000000003', null, null, null, null, null, null, null, null);
INSERT INTO `t_role_permission` VALUES ('P1000000004', 'R1000000001', 'M1000000004', null, null, null, null, null, null, null, null);
INSERT INTO `t_role_permission` VALUES ('P1000000005', 'R1000000001', 'M1000000005', null, null, null, null, null, null, null, null);
INSERT INTO `t_role_permission` VALUES ('P1000000006', 'R1000000001', 'M1000000006', null, null, null, null, null, null, null, null);
INSERT INTO `t_role_permission` VALUES ('P1000000007', 'R1000000001', 'M1000000007', null, null, null, null, null, null, null, null);
INSERT INTO `t_role_permission` VALUES ('P1000000008', 'R1000000001', 'M1000000008', null, null, null, null, null, null, null, null);
INSERT INTO `t_role_permission` VALUES ('P1000000010', 'R1000000001', 'M1000000010', null, null, null, null, null, null, null, null);
INSERT INTO `t_role_permission` VALUES ('P1000000012', 'R1000000001', 'M1000000012', null, null, null, null, null, null, null, null);
INSERT INTO `t_role_permission` VALUES ('P1000000013', 'R1000000001', 'M1000000013', null, null, null, null, null, null, null, null);
INSERT INTO `t_role_permission` VALUES ('P1000000001', 'R1000000001', 'M1000000001', null, null, null, null, null, null, null, null);
INSERT INTO `t_role_permission` VALUES ('P1000000002', 'R1000000001', 'M1000000002', null, null, null, null, null, null, null, null);
INSERT INTO `t_role_permission` VALUES ('P1000000011', 'R1000000001', 'M1000000041', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_seq`
-- ----------------------------
DROP TABLE IF EXISTS `t_seq`;
CREATE TABLE `t_seq` (
  `SEQ_NAME` char(10) NOT NULL COMMENT '序列名称',
  `SEQ_PREFIX` char(1) DEFAULT NULL,
  `SEQ_VALUE` int(10) DEFAULT NULL COMMENT '序列值',
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_seq
-- ----------------------------
INSERT INTO `t_seq` VALUES ('BOOKTYPE', 'B', '1000000003');
INSERT INTO `t_seq` VALUES ('MENU', 'M', '1000000052');
INSERT INTO `t_seq` VALUES ('PERMISSION', 'P', '1000000003');
INSERT INTO `t_seq` VALUES ('ROLE', 'R', '1000000011');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `ACCOUNT` varchar(20) NOT NULL,
  `PASSWORD` varchar(10) NOT NULL,
  `USER_TYPE_ID` char(2) DEFAULT NULL,
  `USER_TYPE_NAME` varchar(10) DEFAULT NULL,
  `USER_ID` char(32) NOT NULL,
  `USER_NAME` varchar(20) DEFAULT NULL,
  `USER_SEX` char(1) DEFAULT NULL,
  `USER_AGE` int(11) DEFAULT NULL,
  `BIRTHDAY` varchar(15) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `ROLE_IDS` varchar(50) DEFAULT NULL,
  UNIQUE KEY `ACCOUNT` (`ACCOUNT`) USING BTREE,
  KEY `USER_NAME` (`USER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('Chens', '111', '', null, 'fbb9024d1a154048a65091e492ffcdd9', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('eee', '111', '', null, 'fbb9024d1a154048a65091e492ffcdd9', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('fff', '111', '', null, 'fbb9024d1a154048a65091e492ffcdd9', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('Yang', '123', null, null, '6433168829b04b43a07a2453c840749e', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('Yuangy', '222', '', null, '32ef5a50112340c980e3b336b6d7d8be', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('Zhan', '111', null, null, '11111111111111111111111111111111', null, null, null, null, null, null);
INSERT INTO `t_user` VALUES ('zhang', '123', '', null, '6557d6e6381f4ef98c2615972ea73aef', null, null, null, null, null, 'R1000000001');
INSERT INTO `t_user` VALUES ('Zhangc', '222', '', null, '32a05f15396e434a80c13fdf8ad46a2f', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `ACCOUNT` varchar(32) DEFAULT NULL,
  `ROLE_ID` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for `user_book_detail_relation`
-- ----------------------------
DROP TABLE IF EXISTS `user_book_detail_relation`;
CREATE TABLE `user_book_detail_relation` (
  `BORROW_ID` char(32) DEFAULT NULL COMMENT '借阅ID',
  `USER_ID` char(32) DEFAULT NULL COMMENT '用户ID',
  `BOOK_ID` char(32) DEFAULT NULL COMMENT '图书ID',
  `BOOK_NAME` varchar(10) DEFAULT NULL COMMENT '图书名称',
  `BORROW_IS_RETURN` char(1) DEFAULT NULL COMMENT '是否已归还',
  `BORROW_RETUNR_TIME` varchar(10) DEFAULT NULL COMMENT '归还时间',
  UNIQUE KEY `USER_ID` (`USER_ID`) USING BTREE,
  UNIQUE KEY `BOOK_ID` (`BOOK_ID`) USING BTREE,
  UNIQUE KEY `BORROW_ID` (`BORROW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_book_detail_relation
-- ----------------------------

-- ----------------------------
-- Table structure for `user_book_relation`
-- ----------------------------
DROP TABLE IF EXISTS `user_book_relation`;
CREATE TABLE `user_book_relation` (
  `BORROW_ID` char(32) NOT NULL COMMENT '借阅ID',
  `USER_ID` char(32) NOT NULL COMMENT '用户ID',
  `BORROW_DESC` varchar(30) DEFAULT NULL COMMENT '借阅表述',
  `BORROW_DATE` varchar(10) DEFAULT NULL COMMENT '借阅时间',
  `BORROW_NUM` int(11) DEFAULT NULL COMMENT '借阅图书数量',
  `RETURN_NUM` int(11) DEFAULT NULL COMMENT '已还图书数量',
  UNIQUE KEY `USER_ID` (`USER_ID`) USING BTREE,
  UNIQUE KEY `BORROW_ID` (`BORROW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_book_relation
-- ----------------------------

-- ----------------------------
-- Table structure for `user_type`
-- ----------------------------
DROP TABLE IF EXISTS `user_type`;
CREATE TABLE `user_type` (
  `USER_TYPE_ID` char(2) NOT NULL,
  `USER_TYPE_NAME` varchar(10) NOT NULL,
  `USER_REMARK` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_type
-- ----------------------------

-- ----------------------------
-- Function structure for `QUERY_SEQ`
-- ----------------------------
DROP FUNCTION IF EXISTS `QUERY_SEQ`;
DELIMITER ;;
CREATE DEFINER=`skip-grants user`@`skip-grants host` FUNCTION `QUERY_SEQ`(SEQ_NAME VARCHAR(10)) RETURNS int(11)
BEGIN
		UPDATE t_seq SET SEQ_VALUE=LAST_INSERT_ID(SEQ_VALUE+1) 
       WHERE SEQ_NAME = SEQ_NAME;
	  RETURN LAST_INSERT_ID();
  END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `QUERY_SEQ_VALUE`
-- ----------------------------
DROP FUNCTION IF EXISTS `QUERY_SEQ_VALUE`;
DELIMITER ;;
CREATE DEFINER=`skip-grants user`@`skip-grants host` FUNCTION `QUERY_SEQ_VALUE`(seqName VARCHAR(10)) RETURNS varchar(15) CHARSET utf8
BEGIN
	  DECLARE seq_pre CHAR(2) DEFAULT '';
		UPDATE t_seq SET SEQ_VALUE=LAST_INSERT_ID(SEQ_VALUE+1) WHERE SEQ_NAME = seqName;
		SELECT SEQ_PREFIX INTO seq_pre FROM t_seq WHERE SEQ_NAME = seqName;
	  RETURN CONCAT(CONCAT(seq_pre,''), LAST_INSERT_ID());
END
;;
DELIMITER ;
