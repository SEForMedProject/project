-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.24-enterprise-commercial-advanced-log - MySQL Enterprise Server - Advanced Edition (Commercial)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 med_project 的数据库结构
DROP DATABASE IF EXISTS `med_project`;
CREATE DATABASE IF NOT EXISTS `med_project` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `med_project`;


-- 导出  表 med_project.doctor 结构
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE IF NOT EXISTS `doctor` (
  `doctor_id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_name` varchar(50) NOT NULL DEFAULT '0',
  `doctor_age` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`doctor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在导出表  med_project.doctor 的数据：~2 rows (大约)
DELETE FROM `doctor`;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` (`doctor_id`, `doctor_name`, `doctor_age`) VALUES
	(1, 'ccj', 22),
	(2, '草草鸡', 23);
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
