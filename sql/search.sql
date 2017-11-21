/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : search

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-11-21 17:47:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for doc
-- ----------------------------
DROP TABLE IF EXISTS `doc`;
CREATE TABLE `doc` (
  `doc_id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(512) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `doc_type` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`doc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for index_term
-- ----------------------------
DROP TABLE IF EXISTS `index_term`;
CREATE TABLE `index_term` (
  `term` varchar(32) NOT NULL,
  `doc_list` varchar(255) DEFAULT NULL,
  `frequency` varchar(255) DEFAULT NULL,
  `offset` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`term`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
