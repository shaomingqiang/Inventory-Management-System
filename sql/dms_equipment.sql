/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50703
 Source Host           : localhost:3306
 Source Schema         : dms_equipment

 Target Server Type    : MySQL
 Target Server Version : 50703
 File Encoding         : 65001

 Date: 13/03/2020 13:01:49
*/
CREATE DATABASE IF NOT EXISTS `dms_equipment` /*!40100 DEFAULT CHARACTER SET utf8 */;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
USE `dms_equipment`;
-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `be_id` int(11) NULL DEFAULT NULL COMMENT '借用设备id，外键',
  `be_time` datetime(0) NULL DEFAULT NULL COMMENT '设备借用时间',
  `re_id` int(11) NULL DEFAULT NULL COMMENT '归还设备id,外键',
  `re_time` datetime(0) NULL DEFAULT NULL COMMENT '设备归还时间',
  `remarks` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注信息',
  `u_id` int(11) NULL DEFAULT NULL COMMENT '操作人员id，外键',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `be_id`(`be_id`) USING BTREE,
  INDEX `re_id`(`re_id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  CONSTRAINT `borrow_ibfk_1` FOREIGN KEY (`be_id`) REFERENCES `equipment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `borrow_ibfk_2` FOREIGN KEY (`re_id`) REFERENCES `equipment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `borrow_ibfk_3` FOREIGN KEY (`u_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室名称',
  `h_id` int(11) NULL DEFAULT NULL COMMENT '所在医院id，外键',
  `remarks` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注信息',
  `delete_tag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0是在用，1是不用',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `h_id`(`h_id`) USING BTREE,
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`h_id`) REFERENCES `hospital` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for equipment
-- ----------------------------
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sn` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备序列号',
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备状态，10:在库新机，20:在院，30:出库，40:借用，50:在库返修，60:审核，70:故障',
  `in_time` datetime(0) NULL DEFAULT NULL COMMENT '入库时间',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `h_id` int(11) NULL DEFAULT NULL COMMENT '所在医院id，外键',
  `d_id` int(11) NULL DEFAULT NULL COMMENT '所在科室id，外键',
  `et_id` int(11) NULL DEFAULT NULL COMMENT '设备类型id，外键',
  `property_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '财产编号',
  `delete_tag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0是在用，1是不用',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `h_id`(`h_id`) USING BTREE,
  INDEX `d_id`(`d_id`) USING BTREE,
  INDEX `et_id`(`et_id`) USING BTREE,
  CONSTRAINT `equipment_ibfk_1` FOREIGN KEY (`h_id`) REFERENCES `hospital` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `equipment_ibfk_2` FOREIGN KEY (`d_id`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `equipment_ibfk_3` FOREIGN KEY (`et_id`) REFERENCES `equipment_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for equipment_record
-- ----------------------------
DROP TABLE IF EXISTS `equipment_record`;
CREATE TABLE `equipment_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `e_id` int(11) NULL DEFAULT NULL COMMENT '设备id，外键',
  `change_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '改变的状态类型',
  `change_time` datetime(0) NULL DEFAULT NULL COMMENT '记录时间',
  `operator` int(11) NULL DEFAULT NULL COMMENT '操作员',
  `h_id` int(11) NULL DEFAULT NULL COMMENT '所属医院编号',
  `d_id` int(11) NULL DEFAULT NULL COMMENT '所属科室编号',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `e_id`(`e_id`) USING BTREE,
  CONSTRAINT `equipment_record_ibfk_1` FOREIGN KEY (`e_id`) REFERENCES `equipment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for equipment_type
-- ----------------------------
DROP TABLE IF EXISTS `equipment_type`;
CREATE TABLE `equipment_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备型号',
  `count` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备数量',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '设备描述',
  `delete_tag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0是在用，1是不用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for hospital
-- ----------------------------
DROP TABLE IF EXISTS `hospital`;
CREATE TABLE `hospital`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院名称',
  `province` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院所在省份',
  `level` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院级别',
  `remarks` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '医院描述',
  `delete_tag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0是在用，1是不用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for rework
-- ----------------------------
DROP TABLE IF EXISTS `rework`;
CREATE TABLE `rework`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `me_id` int(11) NULL DEFAULT NULL COMMENT '返修设备id，外键',
  `me_time` datetime(0) NULL DEFAULT NULL COMMENT '设备返修时间',
  `fe_id` int(11) NULL DEFAULT NULL COMMENT '场内归还设备id,外键',
  `fe_time` datetime(0) NULL DEFAULT NULL COMMENT '场内归还设备时间',
  `remarks` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注信息',
  `u_id` int(11) NULL DEFAULT NULL COMMENT '操作人员id，外键',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `me_id`(`me_id`) USING BTREE,
  INDEX `fe_id`(`fe_id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  CONSTRAINT `rework_ibfk_1` FOREIGN KEY (`me_id`) REFERENCES `equipment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rework_ibfk_2` FOREIGN KEY (`fe_id`) REFERENCES `equipment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rework_ibfk_3` FOREIGN KEY (`u_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `dept` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在部门',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id，外键',
  `delete_tag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0是在用，1是不用',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
