/*
Navicat MySQL Data Transfer

Source Server         : 172.50.57.216
Source Server Version : 50616
Source Host           : 172.50.57.216:3306
Source Database       : wtwdiotdb

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2021-04-24 12:10:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `order_number` varchar(50) DEFAULT NULL COMMENT '订单号',
  `project_name` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品类型/名称',
  `sw_version` varchar(100) DEFAULT NULL COMMENT '内部软件版本号',
  `hw_version` varchar(255) DEFAULT NULL COMMENT '硬件版本号',
  `user_sw_version` varchar(255) DEFAULT NULL COMMENT '在APP端的软件版本号',
  `order_quantity` int(11) DEFAULT '0',
  `cloud_platform` varchar(50) DEFAULT NULL COMMENT '易微联，涂鸦，华为，腾讯等',
  `info` varchar(255) DEFAULT NULL,
  `bak` varchar(255) DEFAULT NULL,
  `at_table` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('1', 'HZCF022012065X', 'RE705F', 'LT02', 'RE705F-V1.3.5-20201218', null, '1.3.2', '0', '酷宅云', '8K五色灯', 'B05-B_WTW-SNL-05_8000_20200921006', 'tb_atcmd_base');
INSERT INTO `tb_order` VALUES ('2', 'HZCF022012000A', 'RE705S', 'LT01', 'RE705S-V1.3.2-20201022', null, null, '0', '酷宅云', '9K', 'B02-F_WTW-SNL-02_9000_20201027008', 'tb_atcmd_base');
INSERT INTO `tb_order` VALUES ('3', '1002', null, 'LT01', null, null, null, '0', '酷宅云', '210个--9K批次', 'B02-F_WTW-SNL-02_210_20201116012', null);
INSERT INTO `tb_order` VALUES ('4', '1004', null, 'LT02', '1.3.2', null, '1.3.2', '0', '酷宅云', '50个', 'B05-B_WTW-SNL-05_50_20201116011', null);
INSERT INTO `tb_order` VALUES ('5', '1005', null, 'LT01', null, null, null, '0', '酷宅云', '10个-9K批次', null, null);
INSERT INTO `tb_order` VALUES ('6', '1006', null, 'LT01', null, null, null, '0', '酷宅云', '10.5k', 'B02-B_WTW-SNL-02_10500_20201110003', null);
INSERT INTO `tb_order` VALUES ('7', '1007', null, 'LT02', null, null, null, '0', '酷宅云', '15.5K', 'B05-B_WTW-SNL-05_15500_20201110004', null);
INSERT INTO `tb_order` VALUES ('8', 'G20-11-27009', 'RE705S', 'B02-F', 'RE705S-V1.3.2-20201022', null, '1.3.2', '0', '酷宅云', '2K', 'B02-F_WTW-SNL-02_2000_20201127005(1001139543,1001139d12)', null);
INSERT INTO `tb_order` VALUES ('9', '1009', 'RE705F', 'LT02', null, null, '1.3.2', '0', '酷宅云', '200', 'B05-B_WTW-SNL-05_200_20201211002', null);
INSERT INTO `tb_order` VALUES ('10', 'G20_12_08013', 'RE706B', 'XIAOJIANG', 'W400.T031.3175', null, null, '0', '小匠', '', null, 'tb_atcmd_xiaojiang');
INSERT INTO `tb_order` VALUES ('11', 'G20_12_08014', 'RE706B', 'XIAOJIANG', 'W400.T031.3175', null, null, '0', '小匠', null, null, 'tb_atcmd_xiaojiang');
INSERT INTO `tb_order` VALUES ('12', 'G20_12_08015', 'RE706B', 'XIAOJIANG', 'W400.T031.3175', null, null, '0', '小匠', null, null, 'tb_atcmd_xiaojiang');
INSERT INTO `tb_order` VALUES ('13', 'G20_12_08016', 'RE706B', 'XIAOJIANG', 'W400.T031.3175', null, null, '0', '小匠', null, null, 'tb_atcmd_xiaojiang');
INSERT INTO `tb_order` VALUES ('14', 'B5_TEST', 'RE706B', 'B5', 'W400.T031.3175', null, null, '0', 'B5测试', null, null, 'tb_atcmd_xiaojiang');
INSERT INTO `tb_order` VALUES ('15', 'G20-12-16003', 'RE701', 'XINBAO', 'RE706-02C-RE701X-M_V1.2.6_20201230', null, null, '0', '涂鸦', '1944', null, 'tb_atcmd_tuya');
INSERT INTO `tb_order` VALUES ('16', 'G20-12-14011', 'RE701', 'XINBAO', 'RE706-02C-RE701X-M_V1.2.6_20201230', null, null, '0', '涂鸦', '1944', null, 'tb_atcmd_tuya');
INSERT INTO `tb_order` VALUES ('17', 'G21-01-07006', 'RE706B', 'XIAOJIANG', 'W400.T031.3175', null, null, '0', '小匠', '300', null, 'tb_atcmd_xiaojiang');
INSERT INTO `tb_order` VALUES ('18', 'DG-PCBA20210104', 'RE706B', 'XIAOJIANG', 'W400.T031.3175', null, null, '0', '小匠', '300', null, 'tb_atcmd_xiaojiang');
INSERT INTO `tb_order` VALUES ('19', 'RE755_0304', 'RE755A', 'B5', 'RE755A_TUYA_I2C_V1.1.3_20210307', null, '1.1.3', '0', '涂鸦', '3500', null, 'tb_atcmd_tuya');
INSERT INTO `tb_order` VALUES ('20', 'N21-02-25012', 'RE705F', 'LT02', 'RE705F-V1.3.5-20201218', null, '1.3.5', '0', '酷宅云', '20K五色灯', null, null);
INSERT INTO `tb_order` VALUES ('21', 'N21-02-25011', 'RE705S', 'LT01', 'RE705S-V1.3.2-20201022', null, '1.3.2', '0', '酷宅云', '20K双色灯', null, null);
INSERT INTO `tb_order` VALUES ('22', 'N21-03-10006', 'RE705S', 'LT01', 'RE705S-V1.3.2-20201022', null, '1.3.2', '0', '酷宅云', '10.6K双色灯', null, null);
INSERT INTO `tb_order` VALUES ('29', 'N21-03-01001', 'RE755A', 'B5', 'RE755A_TUYA_I2C_V1.1.7_20210407', '', '1.1.3', '0', '涂鸦', '3500', '', 'tb_atcmd_tuya');
