/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql
Source Server Version : 50612
Source Host           : 127.0.0.1:3306
Source Database       : web_test

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2017-12-05 14:44:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for climate
-- ----------------------------
DROP TABLE IF EXISTS `climate`;
CREATE TABLE `climate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `month` varchar(64) NOT NULL,
  `city` varchar(64) NOT NULL,
  `temperature` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of climate
-- ----------------------------
INSERT INTO `climate` VALUES ('1', 'Jan', 'Tokyo', '7');
INSERT INTO `climate` VALUES ('2', 'Feb', 'Tokyo', '6.9');
INSERT INTO `climate` VALUES ('3', 'Mar', 'Tokyo', '9.5');
INSERT INTO `climate` VALUES ('4', 'Apr', 'Tokyo', '14.5');
INSERT INTO `climate` VALUES ('5', 'May', 'Tokyo', '18.2');
INSERT INTO `climate` VALUES ('6', 'June', 'Tokyo', '21.5');
INSERT INTO `climate` VALUES ('7', 'July', 'Tokyo', '25.2');
INSERT INTO `climate` VALUES ('8', 'Aug', 'Tokyo', '26.5');
INSERT INTO `climate` VALUES ('9', 'Sept', 'Tokyo', '23.3');
INSERT INTO `climate` VALUES ('10', 'Oct', 'Tokyo', '18.3');
INSERT INTO `climate` VALUES ('11', 'Nov', 'Tokyo', '13.9');
INSERT INTO `climate` VALUES ('12', 'Doc', 'Tokyo', '9.6');
INSERT INTO `climate` VALUES ('13', 'Jan', 'NewYork', '-0.2');
INSERT INTO `climate` VALUES ('14', 'Feb', 'NewYork', '0.8');
INSERT INTO `climate` VALUES ('15', 'Mar', 'NewYork', '5.7');
INSERT INTO `climate` VALUES ('16', 'Apr', 'NewYork', '11.3');
INSERT INTO `climate` VALUES ('17', 'May', 'NewYork', '17');
INSERT INTO `climate` VALUES ('18', 'June', 'NewYork', '22');
INSERT INTO `climate` VALUES ('19', 'July', 'NewYork', '24.8');
INSERT INTO `climate` VALUES ('20', 'Aug.', 'NewYork', '24.1');
INSERT INTO `climate` VALUES ('21', 'Sept', 'NewYork', '20.1');
INSERT INTO `climate` VALUES ('22', 'Oct', 'NewYork', '14.1');
INSERT INTO `climate` VALUES ('23', 'Nov', 'NewYork', '8.6');
INSERT INTO `climate` VALUES ('24', 'Doc', 'NewYork', '2.5');
INSERT INTO `climate` VALUES ('25', 'Jan', 'Berlin', '-0.9');
INSERT INTO `climate` VALUES ('26', 'Feb', 'Berlin', '0.6');
INSERT INTO `climate` VALUES ('27', 'Mar', 'Berlin', '3.5');
INSERT INTO `climate` VALUES ('28', 'Apr', 'Berlin', '8.4');
INSERT INTO `climate` VALUES ('29', 'May', 'Berlin', '13.5');
INSERT INTO `climate` VALUES ('30', 'June', 'Berlin', '17');
INSERT INTO `climate` VALUES ('31', 'July', 'Berlin', '18.6');
INSERT INTO `climate` VALUES ('32', 'Aug', 'Berlin', '17.9');
INSERT INTO `climate` VALUES ('33', 'Sept', 'Berlin', '14.3');
INSERT INTO `climate` VALUES ('34', 'Oct', 'Berlin', '9');
INSERT INTO `climate` VALUES ('35', 'Nov', 'Berlin', '3.9');
INSERT INTO `climate` VALUES ('36', 'Doc', 'Berlin', '1');
INSERT INTO `climate` VALUES ('37', 'Jan', 'Lodon', '3.9');
INSERT INTO `climate` VALUES ('38', 'Feb', 'Lodon', '4.2');
INSERT INTO `climate` VALUES ('39', 'Mar', 'Lodon', '5.7');
INSERT INTO `climate` VALUES ('40', 'Apr', 'Lodon', '8.5');
INSERT INTO `climate` VALUES ('41', 'May', 'Lodon', '11.9');
INSERT INTO `climate` VALUES ('42', 'June', 'Lodon', '15.2');
INSERT INTO `climate` VALUES ('43', 'July', 'Lodon', '17');
INSERT INTO `climate` VALUES ('44', 'Aug', 'Lodon', '16.6');
INSERT INTO `climate` VALUES ('45', 'Sept', 'Lodon', '14.2');
INSERT INTO `climate` VALUES ('46', 'Oct', 'Lodon', '10.3');
INSERT INTO `climate` VALUES ('47', 'Nov', 'Lodon', '6.6');
INSERT INTO `climate` VALUES ('48', 'Doc', 'Lodon', '4.8');

-- ----------------------------
-- Table structure for depart
-- ----------------------------
DROP TABLE IF EXISTS `depart`;
CREATE TABLE `depart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parentid` bigint(20) NOT NULL,
  `short_name` varchar(128) NOT NULL,
  `full_name` varchar(512) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of depart
-- ----------------------------
INSERT INTO `depart` VALUES ('1', '0', '西游记', '西游记');
INSERT INTO `depart` VALUES ('2', '1', '东胜神洲', '西游记/东胜神洲');
INSERT INTO `depart` VALUES ('3', '2', '傲来国', '西游记/东胜神洲/傲来国');
INSERT INTO `depart` VALUES ('4', '3', '花果山', '西游记/东胜神洲/傲来国/花果山');
INSERT INTO `depart` VALUES ('5', '4', '水帘洞', '西游记/东胜神洲/傲来国/花果山/水帘洞');
INSERT INTO `depart` VALUES ('6', '3', '坎源山', '西游记/东胜神洲/傲来国/坎源山');
INSERT INTO `depart` VALUES ('7', '6', '水脏洞', '西游记/东胜神洲/傲来国/坎源山/水脏洞');
INSERT INTO `depart` VALUES ('8', '1', '南赡部洲', '西游记/南赡部洲');
INSERT INTO `depart` VALUES ('9', '8', '浮屠山', '西游记/南赡部洲/浮屠山');
INSERT INTO `depart` VALUES ('10', '8', '观音院', '西游记/南赡部洲/观音院');
INSERT INTO `depart` VALUES ('11', '8', '黑风山', '西游记/南赡部洲/黑风山');
INSERT INTO `depart` VALUES ('12', '8', '黄风岭', '西游记/南赡部洲/黄风岭');
INSERT INTO `depart` VALUES ('13', '8', '流沙河', '西游记/南赡部洲/流沙河');
INSERT INTO `depart` VALUES ('14', '8', '蛇盘山', '西游记/南赡部洲/蛇盘山');
INSERT INTO `depart` VALUES ('15', '8', '唐', '西游记/南赡部洲/唐');
INSERT INTO `depart` VALUES ('16', '15', '双叉岭', '西游记/南赡部洲/唐/双叉岭');
INSERT INTO `depart` VALUES ('17', '15', '长安', '西游记/南赡部洲/唐/长安');
INSERT INTO `depart` VALUES ('18', '17', '化生寺', '西游记/南赡部洲/唐/长安/化生寺');
INSERT INTO `depart` VALUES ('19', '8', '小须弥山', '西游记/南赡部洲/小须弥山');
INSERT INTO `depart` VALUES ('20', '1', '西牛贺洲', '西游记/西牛贺洲');
INSERT INTO `depart` VALUES ('21', '20', '灌洲', '西游记/西牛贺洲/灌洲');
INSERT INTO `depart` VALUES ('22', '21', '灌江口', '西游记/西牛贺洲/灌洲/灌江口');
INSERT INTO `depart` VALUES ('23', '20', '灵台方寸山 ', '西游记/西牛贺洲/灵台方寸山 ');
INSERT INTO `depart` VALUES ('24', '23', '斜月三星洞', '西游记/西牛贺洲/灵台方寸山/斜月三星洞');
INSERT INTO `depart` VALUES ('25', '20', '梅山', '西游记/西牛贺洲/梅山');
INSERT INTO `depart` VALUES ('26', '20', '南海', '西游记/西牛贺洲/南海');
INSERT INTO `depart` VALUES ('27', '26', '普陀落伽山', '西游记/西牛贺洲/南海/普陀落伽山');
INSERT INTO `depart` VALUES ('28', '20', '乌斯藏', '西游记/西牛贺洲/乌斯藏');
INSERT INTO `depart` VALUES ('29', '28', '福陵山 ', '西游记/西牛贺洲/乌斯藏/福陵山 ');
INSERT INTO `depart` VALUES ('30', '29', '高老庄', '西游记/西牛贺洲/乌斯藏/福陵山/高老庄');
INSERT INTO `depart` VALUES ('31', '29', '云栈洞', '西游记/西牛贺洲/乌斯藏/福陵山/云栈洞');

-- ----------------------------
-- Table structure for test_field_type
-- ----------------------------
DROP TABLE IF EXISTS `test_field_type`;
CREATE TABLE `test_field_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `f_str` varchar(64) NOT NULL,
  `f_date` date NOT NULL,
  `f_time` time NOT NULL,
  `f_datetime` datetime NOT NULL,
  `f_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test_field_type
-- ----------------------------
INSERT INTO `test_field_type` VALUES ('1', '孙悟空', '2016-09-08', '08:10:15', '2016-09-08 05:26:40', '2016-09-06 19:47:01');
