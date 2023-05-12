/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : ssm_ebooknet

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2020-02-09 13:03:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `avatar`
-- ----------------------------
DROP TABLE IF EXISTS `avatar`;
CREATE TABLE `avatar` (
  `id` smallint(6) NOT NULL,
  `avatar_txt` varchar(20) DEFAULT NULL,
  `avatar_img` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of avatar
-- ----------------------------
INSERT INTO `avatar` VALUES ('1', '040601', 'resources/images/avatars/040601.jpg');
INSERT INTO `avatar` VALUES ('2', '040602', 'resources/images/avatars/040602.jpg');
INSERT INTO `avatar` VALUES ('3', '040603', 'resources/images/avatars/040603.jpg');
INSERT INTO `avatar` VALUES ('4', '040604', 'resources/images/avatars/040604.jpg');
INSERT INTO `avatar` VALUES ('5', '040605', 'resources/images/avatars/040605.jpg');
INSERT INTO `avatar` VALUES ('6', '040606', 'resources/images/avatars/040606.jpg');
INSERT INTO `avatar` VALUES ('7', '040607', 'resources/images/avatars/040607.jpg');
INSERT INTO `avatar` VALUES ('8', '040608', 'resources/images/avatars/040608.jpg');
INSERT INTO `avatar` VALUES ('9', '040609', 'resources/images/avatars/040609.jpg');
INSERT INTO `avatar` VALUES ('10', '040610', 'resources/images/avatars/040610.jpg');

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` bigint(20) NOT NULL,
  `book_title` varchar(500) NOT NULL,
  `book_author` varchar(500) NOT NULL,
  `book_summary` text,
  `type_id` int(11) DEFAULT NULL,
  `download_times` int(11) DEFAULT NULL,
  `book_pubYear` date DEFAULT NULL,
  `book_file` varchar(500) DEFAULT NULL,
  `book_cover` varchar(500) DEFAULT NULL,
  `book_format` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('12190901190924667', '西游记', '吴承恩', '西游记，大家都知道。', '12', '8', '2019-09-01', 'C:/EclipseIDE/apache-tomcat-8.0.47/wtpwebapps/ssm_ebooknet/ebooks/经典文学/古典文学/西游记.pdf', 'C:/EclipseIDE/apache-tomcat-8.0.47/wtpwebapps/ssm_ebooknet/ebooks/bookCovers/经典文学/古典文学/西游记.jpg', 'pdf');
INSERT INTO `book` VALUES ('22190901190924369', '怎么样都喜欢', '砂梨', '怎么样都喜欢', '22', '2', '2019-09-01', 'C:/EclipseIDE/apache-tomcat-8.0.47/wtpwebapps/ssm_ebooknet/ebooks/通俗小说/青春言情/怎么样都喜欢.txt', 'C:/EclipseIDE/apache-tomcat-8.0.47/wtpwebapps/ssm_ebooknet/ebooks/bookCovers/通俗小说/青春言情/怎么样都喜欢.jpg', 'txt');
INSERT INTO `book` VALUES ('31190901190924165', 'JAVA开发手册', '阿里巴巴', '阿里巴巴出品的一个JAVA开发手册', '31', '9', '2019-09-01', 'C:/EclipseIDE/apache-tomcat-8.0.47/wtpwebapps/ssm_ebooknet/ebooks/计算机类/编程语言/JAVA开发手册.pdf', 'C:/EclipseIDE/apache-tomcat-8.0.47/wtpwebapps/ssm_ebooknet/ebooks/bookCovers/计算机类/编程语言/JAVA开发手册.jpg', 'pdf');
INSERT INTO `book` VALUES ('45190901190924380', '故事会', '故事会出品', '故事会，一本故事杂志', '45', '0', '2019-09-01', 'C:/EclipseIDE/apache-tomcat-8.0.47/wtpwebapps/ssm_ebooknet/ebooks/杂志期刊/娱乐休闲/故事会.pdf', 'C:/EclipseIDE/apache-tomcat-8.0.47/wtpwebapps/ssm_ebooknet/ebooks/bookCovers/杂志期刊/娱乐休闲/故事会.jpg', 'pdf');

-- ----------------------------
-- Table structure for `book_type`
-- ----------------------------
DROP TABLE IF EXISTS `book_type`;
CREATE TABLE `book_type` (
  `id` int(11) NOT NULL,
  `book_large_type` int(11) NOT NULL,
  `book_small_type` int(11) NOT NULL,
  `large_type_name` varchar(20) NOT NULL,
  `small_type_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book_type
-- ----------------------------
INSERT INTO `book_type` VALUES ('11', '1', '1', '经典文学', '现代文学');
INSERT INTO `book_type` VALUES ('12', '1', '2', '经典文学', '古典文学');
INSERT INTO `book_type` VALUES ('13', '1', '3', '经典文学', '国外文学');
INSERT INTO `book_type` VALUES ('14', '1', '4', '经典文学', '纪实文学');
INSERT INTO `book_type` VALUES ('15', '1', '5', '经典文学', '诗词歌赋');
INSERT INTO `book_type` VALUES ('21', '2', '1', '通俗小说', '武侠玄幻');
INSERT INTO `book_type` VALUES ('22', '2', '2', '通俗小说', '青春言情');
INSERT INTO `book_type` VALUES ('23', '2', '3', '通俗小说', '悬疑推理');
INSERT INTO `book_type` VALUES ('24', '2', '4', '通俗小说', '历史军事');
INSERT INTO `book_type` VALUES ('25', '2', '5', '通俗小说', '职场生活');
INSERT INTO `book_type` VALUES ('31', '3', '1', '计算机类', '编程语言');
INSERT INTO `book_type` VALUES ('32', '3', '2', '计算机类', '数据库');
INSERT INTO `book_type` VALUES ('33', '3', '3', '计算机类', '人工智能');
INSERT INTO `book_type` VALUES ('34', '3', '4', '计算机类', '移动开发');
INSERT INTO `book_type` VALUES ('35', '3', '5', '计算机类', '图形图像');
INSERT INTO `book_type` VALUES ('41', '4', '1', '杂志期刊', '科学技术');
INSERT INTO `book_type` VALUES ('42', '4', '2', '杂志期刊', '人文艺术');
INSERT INTO `book_type` VALUES ('43', '4', '3', '杂志期刊', '政治军事');
INSERT INTO `book_type` VALUES ('44', '4', '4', '杂志期刊', '经济管理');
INSERT INTO `book_type` VALUES ('45', '4', '5', '杂志期刊', '娱乐休闲');

-- ----------------------------
-- Table structure for `contribution`
-- ----------------------------
DROP TABLE IF EXISTS `contribution`;
CREATE TABLE `contribution` (
  `id` smallint(6) NOT NULL,
  `level_txt` varchar(10) DEFAULT NULL,
  `lowerLimit` int(11) DEFAULT NULL,
  `upperLimit` int(11) DEFAULT NULL,
  `level_img` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contribution
-- ----------------------------
INSERT INTO `contribution` VALUES ('1', '普通会员', '0', '20', '/resources/images/level/level1.jpg');
INSERT INTO `contribution` VALUES ('2', '白银会员', '21', '50', '/resources/images/level/level2.jpg');
INSERT INTO `contribution` VALUES ('3', '黄金会员', '51', '100', '/resources/images/level/level3.jpg');
INSERT INTO `contribution` VALUES ('4', '铂金会员', '101', '200', '/resources/images/level/level4.jpg');
INSERT INTO `contribution` VALUES ('5', '钻石会员', '201', '999999', '/resources/images/level/level5.jpg');

-- ----------------------------
-- Table structure for `feedback`
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginedUser` bigint(20) DEFAULT NULL,
  `contact` varchar(30) DEFAULT NULL,
  `suggestion` varchar(500) DEFAULT NULL,
  `status` int(2) NOT NULL,
  `postTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES ('1', '1', '782996468', '正在测试中。。。', '1', '2018-04-07 19:03:40');
INSERT INTO `feedback` VALUES ('2', '2', 'qq 254540457', '这个网站不错，好评一个。', '0', '2020-02-09 13:03:16');

-- ----------------------------
-- Table structure for `upload`
-- ----------------------------
DROP TABLE IF EXISTS `upload`;
CREATE TABLE `upload` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uploader` bigint(20) NOT NULL,
  `uploadedBook` bigint(11) NOT NULL,
  `uploadedDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of upload
-- ----------------------------
INSERT INTO `upload` VALUES ('4', '2', '12190901190924667', '2019-09-24');
INSERT INTO `upload` VALUES ('5', '2', '22190901190924369', '2019-09-24');
INSERT INTO `upload` VALUES ('6', '2', '31190901190924165', '2019-09-24');
INSERT INTO `upload` VALUES ('7', '2', '45190901190924380', '2019-09-24');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userCode` varchar(20) NOT NULL,
  `userPassword` varchar(20) NOT NULL,
  `userName` varchar(20) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `avatarNum` smallint(11) DEFAULT NULL,
  `contribution` int(11) DEFAULT NULL,
  `creationDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'abc123', '管理员', '', '8', '6', '2018-04-03');
INSERT INTO `user` VALUES ('2', 'user1', '123456', '张熙桐', '254540457@qq.com', '1', '14', '2019-09-24');
