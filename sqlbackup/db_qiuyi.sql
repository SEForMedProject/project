-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2017-03-14 09:59:34
-- 服务器版本： 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_qiuyi`
--

-- --------------------------------------------------------

--
-- 表的结构 `tb_admin`
--

CREATE TABLE `tb_admin` (
  `id` tinyint(2) NOT NULL COMMENT '管理员id',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '管理员姓名',
  `password` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT '管理员密码'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `tb_comment`
--

CREATE TABLE `tb_comment` (
  `id` int(11) NOT NULL COMMENT '评论id',
  `patient_id` int(11) DEFAULT NULL COMMENT '病人id',
  `doctor_id` int(11) DEFAULT NULL COMMENT '医生id',
  `time` datetime DEFAULT NULL COMMENT '评论时间',
  `rate` tinyint(3) DEFAULT NULL COMMENT '给医生的评级',
  `content` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '内容'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `tb_department`
--

CREATE TABLE `tb_department` (
  `id` int(11) UNSIGNED NOT NULL COMMENT '科室id',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '科室名称',
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '科室描述'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `tb_doctor`
--

CREATE TABLE `tb_doctor` (
  `id` int(11) UNSIGNED NOT NULL COMMENT '医生id',
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '姓名',
  `title` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '头衔',
  `hospital_id` int(7) DEFAULT NULL COMMENT '医院id',
  `department_id` tinyint(5) UNSIGNED DEFAULT NULL COMMENT '科室id',
  `good_at` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '擅长疾病',
  `rating` tinyint(3) UNSIGNED DEFAULT NULL COMMENT '评级（1-10）',
  `num_treatment` smallint(5) UNSIGNED DEFAULT NULL COMMENT '治疗患者数目',
  `num_reservation` smallint(5) UNSIGNED DEFAULT NULL COMMENT '咨询量',
  `introduction` text COLLATE utf8_unicode_ci COMMENT '医生介绍',
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '病人评价',
  `icon_id` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_type` bit(1) NOT NULL DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `tb_hospital`
--

CREATE TABLE `tb_hospital` (
  `id` int(11) UNSIGNED NOT NULL COMMENT '医院id',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '医院名',
  `rank` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '医院等级',
  `address` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '医院地址',
  `telephone` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系方式',
  `home_page` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '医院主页',
  `introduction` text COLLATE utf8_unicode_ci COMMENT '医院介绍'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `tb_ill`
--

CREATE TABLE `tb_ill` (
  `id` int(11) UNSIGNED NOT NULL COMMENT '疾病id',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '疾病姓名',
  `department_id` tinyint(7) UNSIGNED NOT NULL COMMENT '科室id',
  `alias` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '别名',
  `symptom` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '症状',
  `body_part` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发病部位',
  `prone_group` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '易感人群',
  `related_ill_id` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '相关疾病编号',
  `definition` text COLLATE utf8_unicode_ci COMMENT '疾病定义',
  `etiology` text COLLATE utf8_unicode_ci COMMENT '病因',
  `clinical_feature` text COLLATE utf8_unicode_ci COMMENT '临床特征'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `tb_keywords`
--

CREATE TABLE `tb_keywords` (
  `id` int(11) NOT NULL COMMENT '关键字id',
  `keyword` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '关键字名',
  `num_search` int(11) DEFAULT NULL COMMENT '搜索次数'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `tb_patient`
--

CREATE TABLE `tb_patient` (
  `id` int(11) UNSIGNED NOT NULL COMMENT '用户id',
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户姓名',
  `password` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `tel` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '电话',
  `address` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '地址',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `icon_id` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '头像id',
  `search_info` text COLLATE utf8_unicode_ci COMMENT '经常查询的信息',
  `user_type` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='患者信息';

-- --------------------------------------------------------

--
-- 表的结构 `tb_question`
--

CREATE TABLE `tb_question` (
  `id` int(11) NOT NULL COMMENT '问题id',
  `content` text COLLATE utf8_unicode_ci COMMENT '内容',
  `patient_id` int(11) DEFAULT NULL COMMENT '病人id',
  `doctor_id` int(11) DEFAULT NULL COMMENT '医生id',
  `department_id` int(11) DEFAULT NULL COMMENT '科室id',
  `num_browse` int(11) DEFAULT NULL COMMENT '浏览次数',
  `ask_time` datetime DEFAULT NULL COMMENT '问询时间'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `tb_reply`
--

CREATE TABLE `tb_reply` (
  `id` int(11) NOT NULL COMMENT '回复id',
  `content` text COLLATE utf8_unicode_ci COMMENT '内容',
  `reple_time` datetime DEFAULT NULL COMMENT '回复时间',
  `patient_id` int(11) DEFAULT NULL COMMENT '患者id',
  `doctor_id` int(11) DEFAULT NULL COMMENT '医生id',
  `department_id` int(11) DEFAULT NULL COMMENT '部门id'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `tb_search_info`
--

CREATE TABLE `tb_search_info` (
  `id` int(10) UNSIGNED NOT NULL COMMENT '查询信息id',
  `user_type` bit(1) DEFAULT NULL COMMENT '查询信息用户类型',
  `user_id` int(11) UNSIGNED DEFAULT NULL COMMENT '用户id',
  `content` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '内容'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_admin`
--
ALTER TABLE `tb_admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_comment`
--
ALTER TABLE `tb_comment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_department`
--
ALTER TABLE `tb_department`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_doctor`
--
ALTER TABLE `tb_doctor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_hospital`
--
ALTER TABLE `tb_hospital`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `hospital_name` (`name`);

--
-- Indexes for table `tb_ill`
--
ALTER TABLE `tb_ill`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ill_name` (`name`);

--
-- Indexes for table `tb_keywords`
--
ALTER TABLE `tb_keywords`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_patient`
--
ALTER TABLE `tb_patient`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_question`
--
ALTER TABLE `tb_question`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKA4FC2F23EC71F3E4` (`patient_id`),
  ADD KEY `FKA4FC2F233CDD5030` (`department_id`);

--
-- Indexes for table `tb_reply`
--
ALTER TABLE `tb_reply`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKFABD79D9741D2E84` (`doctor_id`),
  ADD KEY `FKFABD79D9EC71F3E4` (`patient_id`);

--
-- Indexes for table `tb_search_info`
--
ALTER TABLE `tb_search_info`
  ADD PRIMARY KEY (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `tb_admin`
--
ALTER TABLE `tb_admin`
  MODIFY `id` tinyint(2) NOT NULL AUTO_INCREMENT COMMENT '管理员id';
--
-- 使用表AUTO_INCREMENT `tb_comment`
--
ALTER TABLE `tb_comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id';
--
-- 使用表AUTO_INCREMENT `tb_department`
--
ALTER TABLE `tb_department`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '科室id';
--
-- 使用表AUTO_INCREMENT `tb_doctor`
--
ALTER TABLE `tb_doctor`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '医生id';
--
-- 使用表AUTO_INCREMENT `tb_hospital`
--
ALTER TABLE `tb_hospital`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '医院id';
--
-- 使用表AUTO_INCREMENT `tb_ill`
--
ALTER TABLE `tb_ill`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '疾病id';
--
-- 使用表AUTO_INCREMENT `tb_keywords`
--
ALTER TABLE `tb_keywords`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关键字id';
--
-- 使用表AUTO_INCREMENT `tb_patient`
--
ALTER TABLE `tb_patient`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id';
--
-- 使用表AUTO_INCREMENT `tb_question`
--
ALTER TABLE `tb_question`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '问题id';
--
-- 使用表AUTO_INCREMENT `tb_reply`
--
ALTER TABLE `tb_reply`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '回复id';
--
-- 使用表AUTO_INCREMENT `tb_search_info`
--
ALTER TABLE `tb_search_info`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '查询信息id';
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
