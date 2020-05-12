/*
 Navicat Premium Data Transfer

 Source Server         : server-localhost
 Source Server Type    : MariaDB
 Source Server Version : 100408
 Source Host           : localhost:3306
 Source Schema         : dms_equipment

 Target Server Type    : MariaDB
 Target Server Version : 100408
 File Encoding         : 65001

 Date: 08/10/2019 15:38:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
