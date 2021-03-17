/*
Navicat MySQL Data Transfer

Source Server         : 120.79.50.47
Source Server Version : 50642
Source Host           : 120.79.50.47:3308
Source Database       : wtwdiotdb

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2021-03-13 08:56:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_atcmd
-- ----------------------------
DROP TABLE IF EXISTS `tb_atcmd`;
CREATE TABLE `tb_atcmd` (
  `Id` int(11) NOT NULL,
  `flag` varchar(50) DEFAULT NULL,
  `cmd` varchar(100) DEFAULT NULL,
  `acttimer` int(2) DEFAULT NULL,
  `filter` varchar(100) DEFAULT NULL,
  `display` varchar(100) DEFAULT NULL,
  `pass` varchar(50) DEFAULT NULL,
  `failed` varchar(50) DEFAULT NULL,
  `end` varchar(100) DEFAULT NULL,
  `function` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_atcmd
-- ----------------------------
INSERT INTO `tb_atcmd` VALUES ('1', 'HANDSHAKE', '\r\nrn', '1', null, null, null, null, '#', '设备接入...');
INSERT INTO `tb_atcmd` VALUES ('2', 'ALIID', 'xj_get_ali_data', '0', 'dn:', null, null, null, 'XJ_GET_ALI_DATA=OK', '读取四元组');
INSERT INTO `tb_atcmd` VALUES ('3', 'DEVID', 'xj_get_devid', '0', 'device id:', '', '', '', 'XJ_GET_DEVID=OK', '读取ID');
INSERT INTO `tb_atcmd` VALUES ('4', 'MARKREAD', 'xj_get_test_mark', '0', 'test mark:', null, null, null, 'XJ_GET_TEST_MARK=OK', '站位测试');
INSERT INTO `tb_atcmd` VALUES ('5', 'SW', 'xj_get_version', '0', 'version:', null, null, null, 'XJ_GET_VERSION=OK', '版本匹配');
INSERT INTO `tb_atcmd` VALUES ('6', 'GPIO', 'xj_gpio_test,1,2,3,4', '0', '', null, 'XJ_GPIO_TEST=OK', 'XJ_GPIO_TEST=FAIL', 'XJ_GPIO_TEST=OK', '测试GPIO');
INSERT INTO `tb_atcmd` VALUES ('7', 'RMAC', 'xj_mac', '0', 'device mac:', null, null, null, 'XJ_MAC=OK', '读取MAC');
INSERT INTO `tb_atcmd` VALUES ('8', 'WMAC', 'xj_mac ', '0', 'device mac:', null, null, null, 'XJ_MAC=OK', '写入MAC');
INSERT INTO `tb_atcmd` VALUES ('9', 'SNREAD', 'xj_get_sn', '0', 'device sn:', null, null, 'XJ_GET_SN=FAIL', 'XJ_GET_SN=OK', '读取SN');
INSERT INTO `tb_atcmd` VALUES ('10', 'BIND', 'xj_get_bindinfo', '0', null, 'device sn:,device id:,device mac:', null, null, 'XJ_GET_BINDINFO=OK', '绑定信息');
INSERT INTO `tb_atcmd` VALUES ('11', 'RSSI', 'xj_dut_start 1', '0', 'ap list number is', null, null, null, 'XJ_DUT_START=OK', '信号强度');
INSERT INTO `tb_atcmd` VALUES ('12', 'SCAN', 'xj_wifi_scan', '0', 'name:,rssi:,mac:,CH:', null, null, null, 'XJ_WIFI_SCAN=OK', 'WiFi搜索');
INSERT INTO `tb_atcmd` VALUES ('13', 'SNWRITE', 'xj_set_sn ', '0', null, null, null, null, 'XJ_SET_SN=OK', '写入SN');
INSERT INTO `tb_atcmd` VALUES ('14', 'MARKWRITE', 'xj_set_test_mark ', '0', null, null, null, 'XJ_SET_TEST_MARK=FAIL', 'XJ_SET_TEST_MARK=OK', '写入标记');
