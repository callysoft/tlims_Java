-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 14, 2019 at 10:19 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tlims-kechdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `ad_contact`
--

CREATE TABLE `ad_contact` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_date` datetime NOT NULL,
  `status` bit(1) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ad_contact`
--

INSERT INTO `ad_contact` (`id`, `code`, `created_date`, `last_modified_date`, `status`, `version`, `email`, `name`, `phone_number`) VALUES
(1, '141b9b9909664ac79bc60f8021dece1c', '2019-07-14 08:59:48', '2019-07-14 08:59:48', b'1', 0, 'goody11@gmail.com', 'Ndumanya Goodluck', '09026105532');

-- --------------------------------------------------------

--
-- Table structure for table `ad_favorites`
--

CREATE TABLE `ad_favorites` (
  `id` bigint(20) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  `code` varchar(255) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(255) NOT NULL,
  `status` bit(1) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ad_image`
--

CREATE TABLE `ad_image` (
  `ad_id` bigint(20) NOT NULL,
  `images` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ad_image`
--

INSERT INTO `ad_image` (`ad_id`, `images`) VALUES
(1, 'default.png');

-- --------------------------------------------------------

--
-- Table structure for table `ad_item`
--

CREATE TABLE `ad_item` (
  `ad_category` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  `code` varchar(255) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(255) NOT NULL,
  `status` bit(1) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  `ad_type` varchar(255) DEFAULT NULL,
  `archived` bit(1) DEFAULT NULL,
  `authorized` bit(1) DEFAULT NULL,
  `brand_cd` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `cat_cd` varchar(255) DEFAULT NULL,
  `cat` varchar(255) DEFAULT NULL,
  `featured` bit(1) DEFAULT NULL,
  `item_condition` varchar(255) DEFAULT NULL,
  `negotiable` bit(1) DEFAULT NULL,
  `price_amt` decimal(38,10) DEFAULT NULL,
  `price_ccy` varchar(3) DEFAULT NULL,
  `sponsored` bit(1) DEFAULT NULL,
  `cat_type_cd` varchar(255) DEFAULT NULL,
  `sub_cat_type` varchar(255) DEFAULT NULL,
  `sub_cat_cd` varchar(255) DEFAULT NULL,
  `sub_cat` varchar(255) DEFAULT NULL,
  `description` longtext,
  `title` varchar(255) NOT NULL,
  `contact_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ad_item`
--

INSERT INTO `ad_item` (`ad_category`, `id`, `last_modified_date`, `code`, `created_by`, `created_date`, `modified_by`, `status`, `version`, `ad_type`, `archived`, `authorized`, `brand_cd`, `brand`, `cat_cd`, `cat`, `featured`, `item_condition`, `negotiable`, `price_amt`, `price_ccy`, `sponsored`, `cat_type_cd`, `sub_cat_type`, `sub_cat_cd`, `sub_cat`, `description`, `title`, `contact_id`) VALUES
('electronic', 1, '2019-07-14 09:16:04', 'aecd02b92b4392768a9e7aa139f48d5c', 'goody11@gmail.com', '2019-07-14 08:59:48', 'SYSTEM', b'1', 1, NULL, b'0', b'1', NULL, NULL, 'electronics', 'Electronics', b'0', 'USED', b'1', '65000.0000000000', 'NGN', b'0', 'cd_players', 'CD Players', 'electronics_audio', 'Audio & music equipment', '<span style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\">WARRANTY : 1YR</span><br style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\"><span style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\">BRAND NEW SATALLITE SMART WI-FI ANDROID TV</span><br style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\"><span style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\">50 INCHES</span><br style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\"><span style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\">SMART TV(Connects to internet)</span><br style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\"><span style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\">ULTRA SLIM LED</span><br style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\"><span style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\">ULTRA HIGH DEFINITION</span><br style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\"><span style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\">3 HDMI</span><br style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\"><span style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\">3 USB</span><br style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\"><span style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\">VGA</span><br style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\"><span style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\">STERIO AUDIO</span><br style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\"><span style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\">ENERGY SAVING</span><br style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\"><span style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\">PICTURE-IN-PICTURE(PIP)</span><br style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\"><span style=\"color: rgb(112, 118, 118); font-family: Arial, Helvetica, sans-serif; font-size: 14px; letter-spacing: normal;\">Please call showroom for more enquiries</span>', '*NASCO SMART 50INCHES CURVE ANDROID TV', 1);

-- --------------------------------------------------------

--
-- Table structure for table `cat_beauty`
--

CREATE TABLE `cat_beauty` (
  `age_group` varchar(255) DEFAULT NULL,
  `benefits` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `formulation` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `scent` varchar(255) DEFAULT NULL,
  `skin_type` varchar(255) DEFAULT NULL,
  `target_area` varchar(255) DEFAULT NULL,
  `tone` varchar(255) DEFAULT NULL,
  `v_package` varchar(255) DEFAULT NULL,
  `volume` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cat_comm_tool`
--

CREATE TABLE `cat_comm_tool` (
  `contact_for_price` bit(1) DEFAULT NULL,
  `deck_no` varchar(255) DEFAULT NULL,
  `max_temperature` varchar(255) DEFAULT NULL,
  `power_source` varchar(255) DEFAULT NULL,
  `shape` varchar(255) DEFAULT NULL,
  `tray_no` varchar(255) DEFAULT NULL,
  `voltage` varchar(255) DEFAULT NULL,
  `weight` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cat_electronic`
--

CREATE TABLE `cat_electronic` (
  `cores_no_cd` varchar(255) DEFAULT NULL,
  `cores_no` varchar(255) DEFAULT NULL,
  `elect_make_cd` varchar(255) DEFAULT NULL,
  `elect_make` varchar(255) DEFAULT NULL,
  `model_cd` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `sub_type_cd` varchar(255) DEFAULT NULL,
  `sub_type` varchar(255) DEFAULT NULL,
  `pcssr_cd` varchar(255) DEFAULT NULL,
  `pcssr` varchar(255) DEFAULT NULL,
  `ram_cd` varchar(255) DEFAULT NULL,
  `ram` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cat_electronic`
--

INSERT INTO `cat_electronic` (`cores_no_cd`, `cores_no`, `elect_make_cd`, `elect_make`, `model_cd`, `model`, `sub_type_cd`, `sub_type`, `pcssr_cd`, `pcssr`, `ram_cd`, `ram`, `id`) VALUES
(NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `cat_fashion`
--

CREATE TABLE `cat_fashion` (
  `closure` varchar(255) DEFAULT NULL,
  `color` longtext,
  `display_cd` varchar(255) DEFAULT NULL,
  `display_nm` varchar(255) DEFAULT NULL,
  `fashion_size` longtext,
  `fashion_style` longtext,
  `fastening` longtext,
  `features` longtext,
  `gender` varchar(255) DEFAULT NULL,
  `stone_cd` varchar(255) DEFAULT NULL,
  `stone_nm` varchar(255) DEFAULT NULL,
  `mat` longtext,
  `mat_2` longtext,
  `movement_cd` varchar(255) DEFAULT NULL,
  `movement_nm` varchar(255) DEFAULT NULL,
  `type_list` longtext,
  `id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cat_job`
--

CREATE TABLE `cat_job` (
  `company_name` varchar(255) DEFAULT NULL,
  `job_type` varchar(255) DEFAULT NULL,
  `mini_qualification` varchar(5000) DEFAULT NULL,
  `minimum_exp` varchar(255) DEFAULT NULL,
  `requirements` varchar(5000) DEFAULT NULL,
  `responsibilities` varchar(5000) DEFAULT NULL,
  `salary_from` varchar(255) DEFAULT NULL,
  `salary_to` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cat_kid`
--

CREATE TABLE `cat_kid` (
  `age_group` varchar(255) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cat_mobile`
--

CREATE TABLE `cat_mobile` (
  `color` varchar(255) DEFAULT NULL,
  `is_exchangeable` bit(1) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `os` varchar(255) DEFAULT NULL,
  `ram` varchar(255) DEFAULT NULL,
  `screen_size` varchar(255) DEFAULT NULL,
  `storage_capacity` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cat_repair`
--

CREATE TABLE `cat_repair` (
  `id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cat_vehicle`
--

CREATE TABLE `cat_vehicle` (
  `color` varchar(255) DEFAULT NULL,
  `exchangeable` bit(1) DEFAULT NULL,
  `make` varchar(255) DEFAULT NULL,
  `mileage` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `registered` bit(1) DEFAULT NULL,
  `transmission` varchar(255) DEFAULT NULL,
  `trim` varchar(255) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `core_msg`
--

CREATE TABLE `core_msg` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_date` datetime NOT NULL,
  `status` bit(1) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  `content` longtext,
  `post_id` varchar(255) DEFAULT NULL,
  `post_title` varchar(255) DEFAULT NULL,
  `post_code` varchar(255) DEFAULT NULL,
  `recipient` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `contact_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `gb_category`
--

CREATE TABLE `gb_category` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_date` datetime NOT NULL,
  `status` bit(1) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  `data_code` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `description` longtext,
  `title` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gb_category`
--

INSERT INTO `gb_category` (`id`, `code`, `created_date`, `last_modified_date`, `status`, `version`, `data_code`, `image`, `description`, `title`, `parent_id`) VALUES
(5, '048fc6fb092e8e0be1803212b8b91c60', '2019-03-05 19:24:42', '2019-03-05 19:24:42', b'0', 0, 'agriculture', '', NULL, 'Agriculture & Food', NULL),
(6, 'fad1227f9e0c01d05d4201f8ae1c419c', '2019-03-05 19:25:07', '2019-03-05 19:25:07', b'0', 0, 'animals', '', NULL, 'Animals & Pets', NULL),
(7, 'a13e5e98b017620c09cc0208042e7e99', '2019-03-05 19:25:25', '2019-03-05 19:25:25', b'1', 0, 'kids', 'toy.jpg', NULL, 'Babies & Kids', NULL),
(8, '894c81cb32e9cb36cca8001fa77beb74', '2019-03-05 19:25:42', '2019-03-05 19:25:42', b'1', 0, 'comm_tools', 'hdd.jpg', NULL, 'Commercial Equipment & Tools', NULL),
(9, '9eb22ffc0dbf0f2fefb0676d80089cb5', '2019-03-05 19:25:57', '2019-03-05 19:25:57', b'1', 0, 'electronics', 'camera.jpg', NULL, 'Electronics', NULL),
(10, '0286d2c3b08c2c5bf9e4348089c65cb2', '2019-03-05 19:26:12', '2019-03-05 19:26:12', b'1', 0, 'fashion', 'fashion.jpg', NULL, 'Fashion ', NULL),
(11, '9c92f28637acac8fee85708a1733392d', '2019-03-05 19:26:27', '2019-03-05 19:26:27', b'1', 0, 'beauty', 'catalog.jpg', NULL, 'Health & Beauty', NULL),
(12, '902dea1955709ec6a6841f342ceb1da2', '2019-03-05 19:26:47', '2019-03-05 19:26:47', b'1', 0, 'mobile', 'mobile.jpg', NULL, 'Mobile phones & Tablets', NULL),
(13, '013d1cb0699d91aac5222329135387b9', '2019-03-05 19:26:59', '2019-03-05 19:26:59', b'1', 0, 'estate', 'house.jpg', NULL, 'Real Estate', NULL),
(14, '2ca1db5f981d07bc7622dfd2abb4c92e', '2019-03-05 19:27:11', '2019-03-05 19:27:11', b'1', 0, 'repair', 'hdd.jpg', NULL, 'Repair & Construction', NULL),
(15, '608d0228e6ffe13440fe84ec2ad0305c', '2019-03-05 19:27:24', '2019-03-05 19:27:24', b'1', 0, 'vehicles', 'car-2.jpg', NULL, 'Vehicles', NULL),
(16, '71b7eb1ec9334b6aa97f9d720624edf9', '2019-03-05 19:28:40', '2019-03-05 19:28:40', b'1', 0, 'agric_farm', '', NULL, 'Farm machinery & Equipment', 5),
(17, '6d3c903bddd103d10ea51267d31dd48c', '2019-03-05 19:29:17', '2019-03-05 19:29:17', b'1', 0, 'agric_feeds', '', NULL, 'Feeds supplements & seeds', 5),
(18, '50145882407be559ee5b4f0c516d6a78', '2019-03-05 19:29:37', '2019-03-05 19:29:37', b'1', 0, 'agric_livestocks', '', NULL, 'Livestocks & poultry', 5),
(19, 'bc9456d077045342f7b333f6c5a5af91', '2019-03-05 19:29:57', '2019-03-05 19:29:57', b'1', 0, 'agric_meals', '', NULL, 'Meals & drinks', 5),
(20, 'a04f308ae509c46a2d3fd4327b146a07', '2019-03-05 19:30:26', '2019-03-05 19:30:26', b'1', 0, 'animal_birds', '', NULL, 'Birds', 6),
(21, 'd39976065a1e57f6817ae0a54110724c', '2019-03-05 19:30:51', '2019-03-05 19:30:51', b'1', 0, 'animal_cats', '', NULL, 'Cats & Kittens', 6),
(22, '4fae59373fc62f7cbc7a09e8a6140609', '2019-03-05 19:31:13', '2019-03-05 19:31:13', b'1', 0, 'animal_dogs', '', NULL, 'Dogs & puppies', 6),
(23, '99d78da43dd47e7f0963dcc967c44dd3', '2019-03-05 19:31:29', '2019-03-05 19:31:29', b'1', 0, 'animal_fish', '', NULL, 'Fish', 6),
(24, 'ff15fba91a52abd868e301bc913114be', '2019-03-05 19:31:50', '2019-03-05 19:31:50', b'1', 0, 'animal_other', '', NULL, 'Other animals', 6),
(25, 'cb489b98e32b38f37edb7affee5ec4f4', '2019-03-05 19:32:17', '2019-03-05 19:32:17', b'1', 0, 'kids_accessories', '', NULL, 'Babies & kids accessories', 7),
(26, '8a8583268587b8d5ea7a0c583e9b259b', '2019-03-05 19:32:33', '2019-03-05 19:32:33', b'1', 0, 'kids_care', '', NULL, 'Baby care', 7),
(27, '2bcec7e55e7e127624c26e3e53d51379', '2019-03-05 19:32:59', '2019-03-05 19:32:59', b'1', 0, 'kids_clothing', '', NULL, 'Children\'s clothing', 7),
(28, '9fe3d364ba95fb2e8f0b4cc4f99bb974', '2019-03-05 19:33:23', '2019-03-05 19:33:23', b'1', 0, 'kids_gear', '', NULL, 'Children\'s Gear & Safety', 7),
(29, '45d5d2080a82163028045b8d66a428d0', '2019-03-05 19:33:44', '2019-03-05 19:33:44', b'1', 0, 'kids_shoes', '', NULL, 'Children\'s Shoes', 7),
(30, '668cac754042c5c0a8393e5ce6e0b0fd', '2019-03-05 19:34:14', '2019-03-05 19:34:14', b'1', 0, 'comm_ovens', '', NULL, 'Industrial ovens', 8),
(31, '4183fb54cce2bf3f15d025300964f25a', '2019-03-05 19:34:40', '2019-03-05 19:34:40', b'1', 0, 'comm_man_equip', '', NULL, 'Manufacturing equipment', 8),
(32, '27b7b96e1261c049e0ec3373596001b2', '2019-03-05 19:35:03', '2019-03-05 19:35:03', b'1', 0, 'comm_man_tools', '', NULL, 'Manufacturing materials & tools', 8),
(33, '6ca0ce4a6afe65cf9a46adb4562e7573', '2019-03-05 19:35:28', '2019-03-05 19:35:28', b'1', 0, 'comm_medical', '', NULL, 'Medical equipment', 8),
(34, '46cf9e4e9c04168a7f44502dfccd9c69', '2019-03-05 19:35:51', '2019-03-05 19:35:51', b'1', 0, 'comm_print_equip', '', NULL, 'Printing equipment', 8),
(35, '2f5173f4462fd75ca76843f5867a7d0a', '2019-03-05 19:36:10', '2019-03-05 19:36:10', b'1', 0, 'comm_restaurant', '', NULL, 'Restaurant & catering equipment', 8),
(36, 'cbefb2ae490799d22291c0961d837472', '2019-03-05 19:36:32', '2019-03-05 19:36:32', b'1', 0, 'comm_safety', '', NULL, ' Safety equipment', 8),
(37, 'd4dbf899f8cec7d11a1c5224162a529b', '2019-03-05 19:36:52', '2019-03-05 19:36:52', b'1', 0, 'comm_stationery', '', NULL, 'Stationery', 8),
(38, '1e5885286ac1aafb5691097327c7c22f', '2019-03-05 19:37:13', '2019-03-05 19:37:13', b'1', 0, 'comm_salon', '', NULL, 'Salon equipment', 8),
(39, '4cb2daae583705d8997024b1450e916f', '2019-03-05 19:37:35', '2019-03-05 19:37:35', b'1', 0, 'electronics_audio', '', NULL, 'Audio & music equipment', 9),
(40, '0e68efca872d4de5333720be5381a598', '2019-03-05 19:37:57', '2019-03-05 19:37:57', b'1', 0, 'electronics_cameras', '', NULL, 'Cameras', 9),
(41, 'd9e1beac87410fb01a24003f373306b3', '2019-03-05 19:38:30', '2019-03-05 19:38:30', b'1', 0, 'electronics_vidcam', '', NULL, 'Video cameras & Accessories', 9),
(42, '149ec9e531db435c99641511e1052d65', '2019-03-05 19:38:59', '2019-03-05 19:38:59', b'1', 0, 'electronics_comp_hardware', '', NULL, 'Computer hardware', 9),
(43, '22fdb3dc6f07c2ad2056e6d214e90320', '2019-03-05 19:39:29', '2019-03-05 19:39:29', b'1', 0, 'electronics_comp_access', '', NULL, 'Computer accessories', 9),
(44, 'ac46da3a754a6e9f0fe6a4b3104d745f', '2019-03-05 19:39:55', '2019-03-05 19:39:55', b'1', 0, 'electronics_laptops', '', NULL, ' Laptops and computer', 9),
(45, 'f506a81ea0dbc52da04866cad54f8663', '2019-03-05 19:40:29', '2019-03-05 19:40:29', b'1', 0, 'electronics_tv_equip', '', NULL, 'TV & DVD Equipment', 9),
(46, '1ae48e474d105daeb7693ea5f66601aa', '2019-03-05 19:41:07', '2019-03-05 19:41:07', b'1', 0, 'electronics_videos', '', NULL, 'Video Games', 9),
(47, 'c92d9cced6d42232b5843e36cb717e78', '2019-03-05 19:41:34', '2019-03-05 19:41:34', b'1', 0, 'electronics_vid_games', '', NULL, ' Video Games Consoles', 9),
(48, 'b27aaea28c87e40c3ec3d3ef557ca241', '2019-03-05 19:41:52', '2019-03-05 19:41:52', b'1', 0, 'fashion_bags', '', NULL, 'Bags', 10),
(49, '90fdae8c6a38c39eaa3e82e68ff9b2f1', '2019-03-05 19:42:11', '2019-03-05 19:42:11', b'1', 0, 'fashion_clothing', '', NULL, 'Clothing', 10),
(50, '4bfa37f168db63db8659d34f44653212', '2019-03-05 19:42:28', '2019-03-05 19:42:28', b'1', 0, 'fashion_jewelry', '', NULL, 'Jewelry', 10),
(51, 'd67039a96a0e625fed6c2416690018dd', '2019-03-05 19:42:47', '2019-03-05 19:42:47', b'1', 0, 'fashion_shoes', '', NULL, 'Shoes', 10),
(52, 'a0858235d7ade8664fbe273e10765cbc', '2019-03-05 19:43:53', '2019-03-05 19:43:53', b'1', 0, 'fashion_watches', '', NULL, 'Watches', 10),
(53, 'a307150041b43ea5a526eff2aa8dd2a5', '2019-03-05 19:44:13', '2019-03-05 19:44:13', b'1', 0, 'fashion_wedding', '', NULL, 'Wedding wears', 10),
(54, '5afba2fc01fcacf1b6d034e924acf9e3', '2019-03-05 19:44:40', '2019-03-05 19:44:40', b'1', 0, 'beauty_body', '', NULL, 'Bath & Body', 11),
(55, 'ba0d9de8e30aa8049522f1d1b739585f', '2019-03-05 19:45:01', '2019-03-05 19:45:01', b'1', 0, 'beauty_fragrance', '', NULL, 'Fragrance', 11),
(56, '5dea25fe3c6884507f18ea66d2c32606', '2019-03-05 19:45:22', '2019-03-05 19:45:22', b'1', 0, 'beauty_hair', '', NULL, 'Hair Beauty', 11),
(57, 'ed1651698d9a23008793c294b6cfd8f1', '2019-03-05 19:45:41', '2019-03-05 19:45:41', b'1', 0, 'beauty_makeup', '', NULL, 'Makeup', 11),
(58, 'f27abd48f65346f0a7e057bf88a60747', '2019-03-05 19:46:46', '2019-03-05 19:46:46', b'1', 0, 'beauty_sexual', '', NULL, 'Sexual wellness', 11),
(59, '8a43b379cf0b99758dbd1428c04d391f', '2019-03-05 19:47:13', '2019-03-05 19:47:13', b'1', 0, 'beauty_skin', '', NULL, 'Skin care', 11),
(60, 'b42061789fb2b08ce0c1a508cce02ef9', '2019-03-05 19:47:33', '2019-03-05 19:47:33', b'1', 0, 'beauty_supplements', '', NULL, 'Vitamins & Supplements', 11),
(61, '9002b46ce4e8fb4ceafb73a9c5d30115', '2019-03-05 19:48:11', '2019-03-05 19:48:11', b'1', 0, 'mobile_phones', '', NULL, 'Mobile phones', 12),
(62, 'a19438d502e5f8e1bf0c1170925c3c50', '2019-03-05 19:48:29', '2019-03-05 19:48:29', b'1', 0, 'mobile_tablets', '', NULL, 'Tablets', 12),
(63, 'ddb8e7f35aed9b2c6a9b1b8831743f79', '2019-03-05 19:48:54', '2019-03-05 19:48:54', b'1', 0, 'mobile_accessories', '', NULL, 'Mobile phones & tablets accessories', 12),
(64, '902dea1955709ec6a6841f342cda2', '2019-03-05 19:26:47', '2019-03-05 19:26:47', b'1', 0, 'job', 'jobs.jpg', NULL, 'Jobs', NULL),
(65, '1533758c59dc3452902b59dc34690de4', '2019-06-30 18:24:46', '2019-06-30 18:24:46', b'1', 0, 'acc_fin', '', NULL, 'Accounting and Finance', 64),
(66, '11e936ef87b8660836ef4d59a799a6d5', '2019-06-30 18:25:34', '2019-06-30 18:25:34', b'1', 0, 'act_ent', '', NULL, 'Arts and Entertainment', 64),
(67, 'eaadd67953b674662e0ebccd3c01727d', '2019-07-04 22:35:22', '2019-07-04 22:35:22', b'1', 0, 'b_materials', '', NULL, 'Building Materials', 14),
(68, '50fbfdc49436d6b60a63227ec3c4a10c', '2019-07-04 22:35:50', '2019-07-04 22:35:50', b'1', 0, 'elect_equip', '', NULL, 'Electrical Equipments', 14),
(69, '7735dcaa1581e6007298b3de5652107c', '2019-07-04 22:36:10', '2019-07-04 22:36:10', b'1', 0, 'elect_tools', '', NULL, 'Electrical Tools', 14),
(70, '1eeed1ed7b49ad564ade1bd303decb75', '2019-07-04 22:36:22', '2019-07-04 22:36:22', b'1', 0, 'hand_tools', '', NULL, 'Hand Tools', 14),
(71, 'a9bbecea4ce1e770ddea4824a428078b', '2019-07-04 22:36:48', '2019-07-04 22:36:48', b'1', 0, 'measure_layout', '', NULL, 'Measuring and Layout Tools', 14),
(72, '0583e722dc49871da8e3d55cdafc48cd', '2019-07-04 22:37:20', '2019-07-04 22:37:20', b'1', 0, 'oth_repairs', '', NULL, 'Other Repair and Construction Items', 14),
(73, '0d1dabd8b40dc63874b598c589559ee9', '2019-07-04 22:37:55', '2019-07-04 22:37:55', b'1', 0, 'plumb_water', '', NULL, 'Plumbing and Water Supply', 14),
(74, '6d5a8b0bccd69122ab8762dee7ad056d', '2019-07-04 22:38:16', '2019-07-04 22:38:16', b'1', 0, 'solar_energy', '', NULL, 'Solar Energy', 14),
(75, '76d4b9890e727df8919f3d492cecf58f', '2019-07-04 22:38:26', '2019-07-04 22:38:26', b'1', 0, 'windows', '', NULL, 'Windows', 14);

-- --------------------------------------------------------

--
-- Table structure for table `gb_picklist`
--

CREATE TABLE `gb_picklist` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_date` datetime NOT NULL,
  `status` bit(1) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  `cat_cd` varchar(255) DEFAULT NULL,
  `cat_nm` varchar(255) DEFAULT NULL,
  `prnt_pcklst_type` varchar(255) DEFAULT NULL,
  `data_code` varchar(255) NOT NULL,
  `picklist_type` varchar(255) NOT NULL,
  `subcat_cd` varchar(255) DEFAULT NULL,
  `subcat_nm` varchar(255) DEFAULT NULL,
  `description` longtext,
  `title` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gb_picklist`
--

INSERT INTO `gb_picklist` (`id`, `code`, `created_date`, `last_modified_date`, `status`, `version`, `cat_cd`, `cat_nm`, `prnt_pcklst_type`, `data_code`, `picklist_type`, `subcat_cd`, `subcat_nm`, `description`, `title`, `parent_id`) VALUES
(68, '2baea42a2b2a4b756b7156270cd0d575', '2019-03-07 04:03:46', '2019-03-07 04:03:46', b'1', 0, 'agriculture', 'Agriculture & Food', NULL, 'cows', 'ITEM_TYPE', 'agric_livestocks', 'Livestocks & poultry', NULL, 'Cows', NULL),
(67, 'a652a206e4afea51ed507625b6fb5c01', '2019-03-06 22:42:40', '2019-03-06 22:42:40', b'1', 0, 'agriculture', 'Agriculture & Food', NULL, 'chicken', 'ITEM_TYPE', 'agric_livestocks', 'Livestocks & poultry', NULL, 'Chicken', NULL),
(69, 'cea9b2de34f12178ceea08dda87071d7', '2019-03-07 04:04:16', '2019-03-07 04:04:16', b'1', 0, 'agriculture', 'Agriculture & Food', NULL, 'ducks', 'ITEM_TYPE', 'agric_livestocks', 'Livestocks & poultry', NULL, 'Ducks', NULL),
(70, 'e7d1bb8dddfa91017feaf0f52326a3b5', '2019-03-07 04:06:40', '2019-03-07 04:06:40', b'1', 0, 'animals', 'Animals & Pets', NULL, 'eagle', 'ITEM_TYPE', 'animal_birds', 'Birds', NULL, 'Eagle', NULL),
(71, '0b4fc1e8fca6087ed6fee7de4a2e0737', '2019-03-07 04:09:49', '2019-03-07 04:09:49', b'1', 0, 'animals', 'Animals & Pets', NULL, 'crow', 'ITEM_TYPE', 'animal_birds', 'Birds', NULL, 'Crow', NULL),
(72, '47846ac4407c4663d8150d5b55059edb', '2019-03-07 04:10:18', '2019-03-07 04:10:18', b'1', 0, 'animals', 'Animals & Pets', NULL, 'owl', 'ITEM_TYPE', 'animal_birds', 'Birds', NULL, 'Owl', NULL),
(73, 'cde0ef87f4a6ac18a1db131de88af995', '2019-03-07 04:16:14', '2019-03-07 04:16:14', b'1', 0, NULL, NULL, NULL, 'black', 'COLOR', NULL, NULL, NULL, 'Black', NULL),
(74, '7e33e7607ebf72f820676e993e9a7067', '2019-03-07 04:16:33', '2019-03-07 04:16:33', b'1', 0, NULL, NULL, NULL, 'gray', 'COLOR', NULL, NULL, NULL, 'Gray', NULL),
(75, '68e4397770e730fea642f5d62f8b4fbf', '2019-03-07 04:16:52', '2019-03-07 04:16:52', b'1', 0, NULL, NULL, NULL, 'silver', 'COLOR', NULL, NULL, NULL, 'Silver', NULL),
(76, '0964e453391e63bbd20ee92376b4f54e', '2019-03-07 04:19:34', '2019-03-07 04:19:34', b'1', 0, 'kids', 'Babies & Kids', NULL, 'baby', 'AGE_GROUP', 'kids_clothing', 'Children\'s clothing', NULL, 'Baby', NULL),
(77, 'bd48b069fad4f1ec5804acff85dc8c48', '2019-03-07 04:20:37', '2019-03-07 04:20:37', b'1', 0, 'kids', 'Babies & Kids', NULL, 'kids', 'AGE_GROUP', 'kids_clothing', 'Children\'s clothing', NULL, 'Kids', NULL),
(78, '4c1f81c27cda01ff2a29324dcfdc5046', '2019-03-07 04:20:53', '2019-03-07 04:20:53', b'1', 0, 'kids', 'Babies & Kids', NULL, 'teens', 'AGE_GROUP', 'kids_clothing', 'Children\'s clothing', NULL, 'Teens', NULL),
(79, '238e189e12daf80cc40e764c85ad1f3e', '2019-03-07 04:21:27', '2019-03-07 04:21:27', b'1', 0, 'kids', 'Babies & Kids', NULL, 'boxers', 'ITEM_TYPE', 'kids_clothing', 'Children\'s clothing', NULL, 'Boxers', NULL),
(80, 'f8ea1601aeb3eb6179420c0d71b0e372', '2019-03-07 04:21:50', '2019-03-07 04:21:50', b'1', 0, 'kids', 'Babies & Kids', NULL, 'bodysuits', 'ITEM_TYPE', 'kids_clothing', 'Children\'s clothing', NULL, 'Bodysuits', NULL),
(81, '88750e325a4b680deee176988b90eaee', '2019-03-07 04:22:11', '2019-03-07 04:22:11', b'1', 0, 'kids', 'Babies & Kids', NULL, 'caps', 'ITEM_TYPE', 'kids_clothing', 'Children\'s clothing', NULL, 'Caps', NULL),
(82, '6f896ff0511861eec23e5de32acdec52', '2019-03-07 04:22:42', '2019-03-07 04:22:42', b'1', 0, 'kids', 'Babies & Kids', NULL, 'coats', 'ITEM_TYPE', 'kids_clothing', 'Children\'s clothing', NULL, 'Coats', NULL),
(83, '8c2a5b661960a30efca5308e3ea7d105', '2019-03-07 04:24:34', '2019-03-07 04:24:34', b'1', 0, 'kids', 'Babies & Kids', NULL, '16', 'SIZE', 'kids_shoes', 'Children\'s Shoes', NULL, '16', NULL),
(84, '74af5bc19f09f23f302eb7c305f18626', '2019-03-07 04:24:57', '2019-03-07 04:24:57', b'1', 0, 'kids', 'Babies & Kids', NULL, '17', 'SIZE', 'kids_shoes', 'Children\'s Shoes', NULL, '17', NULL),
(85, 'e1d570ab65a9f96a7161103ce1baec10', '2019-03-07 04:25:25', '2019-03-07 04:25:25', b'1', 0, 'kids', 'Babies & Kids', NULL, 'boots', 'ITEM_TYPE', 'kids_shoes', 'Children\'s Shoes', NULL, 'Boots', NULL),
(86, 'b80feaca4a1d18a9a594762bb9dd0ffc', '2019-03-07 04:25:48', '2019-03-07 04:25:48', b'1', 0, 'kids', 'Babies & Kids', NULL, 'clogs', 'ITEM_TYPE', 'kids_shoes', 'Children\'s Shoes', NULL, 'Clogs', NULL),
(87, '43013e351e28c176c09aa0e6efadbfaa', '2019-03-07 04:26:06', '2019-03-07 04:26:06', b'1', 0, 'kids', 'Babies & Kids', NULL, 'crib', 'ITEM_TYPE', 'kids_shoes', 'Children\'s Shoes', NULL, 'Crib', NULL),
(88, '4051606b68019ca75fed176afdcd69a8', '2019-03-07 04:26:50', '2019-03-07 04:26:50', b'1', 0, 'kids', 'Babies & Kids', NULL, 'buckles', 'FASTEN', 'kids_shoes', 'Children\'s Shoes', NULL, 'Buckles', NULL),
(89, '81138c049cb6dce6924a3b52e73a2413', '2019-03-07 04:27:12', '2019-03-07 04:27:12', b'1', 0, 'kids', 'Babies & Kids', NULL, 'buttons', 'FASTEN', 'kids_shoes', 'Children\'s Shoes', NULL, 'Buttons', NULL),
(90, '7dae6a4e62fee178d666ed35c216c316', '2019-03-07 04:27:33', '2019-03-07 04:27:33', b'1', 0, 'kids', 'Babies & Kids', NULL, 'velcro', 'FASTEN', 'kids_shoes', 'Children\'s Shoes', NULL, 'Velcro', NULL),
(91, '44b1c3d6aaa72e78a4d1094c2495f694', '2019-03-07 04:37:43', '2019-03-07 04:37:43', b'1', 0, 'comm_tools', 'Commercial Equipment & Tools', NULL, 'gas', 'POWER_SOURCE', 'comm_ovens', 'Industrial ovens', NULL, 'Gas', NULL),
(92, 'e65392475b1a76d99bb175d2abbb9e1e', '2019-03-07 04:38:08', '2019-03-07 04:38:08', b'1', 0, 'comm_tools', 'Commercial Equipment & Tools', NULL, 'electric', 'POWER_SOURCE', 'comm_ovens', 'Industrial ovens', NULL, 'Electric', NULL),
(93, 'd461f7e24ce76311ea717bed2a8014a4', '2019-03-07 04:38:28', '2019-03-07 04:38:28', b'1', 0, 'comm_tools', 'Commercial Equipment & Tools', NULL, 'curing', 'ITEM_TYPE', 'comm_ovens', 'Industrial ovens', NULL, 'Curing', NULL),
(94, '7dfb56bfd3d08c691f1668c35a258740', '2019-03-07 04:38:43', '2019-03-07 04:38:43', b'1', 0, 'comm_tools', 'Commercial Equipment & Tools', NULL, 'baking', 'ITEM_TYPE', 'comm_ovens', 'Industrial ovens', NULL, 'Baking', NULL),
(95, 'b65d5637698007cd5780e37f192e9b29', '2019-03-07 04:39:04', '2019-03-07 04:39:04', b'1', 0, 'comm_tools', 'Commercial Equipment & Tools', NULL, 'batch', 'ITEM_TYPE', 'comm_ovens', 'Industrial ovens', NULL, 'Batch', NULL),
(96, 'a4045567782de32ea1da8fe206a349c3', '2019-03-07 04:39:30', '2019-03-07 04:39:30', b'1', 0, 'comm_tools', 'Commercial Equipment & Tools', NULL, 'horizontal', 'SHAPE', 'comm_ovens', 'Industrial ovens', NULL, 'Horizontal', NULL),
(97, 'd39f5eb674ade632e7a13e5cdf94ecd2', '2019-03-07 04:40:01', '2019-03-07 04:40:01', b'1', 0, 'comm_tools', 'Commercial Equipment & Tools', NULL, 'vertical', 'SHAPE', 'comm_ovens', 'Industrial ovens', NULL, 'Vertical', NULL),
(98, '80d6067f8ede334b476958934d0fdc6a', '2019-03-07 04:40:58', '2019-03-07 04:40:58', b'1', 0, 'animals', 'Animals & Pets', NULL, 'akita', 'BREED', 'animal_dogs', 'Dogs & puppies', NULL, 'Akita', NULL),
(99, 'ab4ffec14945686b43bc00cf7e0d503e', '2019-03-07 04:41:19', '2019-03-07 04:41:19', b'1', 0, 'animals', 'Animals & Pets', NULL, 'barbet', 'BREED', 'animal_dogs', 'Dogs & puppies', NULL, 'Barbet', NULL),
(100, '3651a5af4d761414d09b04e2942128ac', '2019-03-07 04:41:53', '2019-03-07 04:41:53', b'1', 0, 'animals', 'Animals & Pets', NULL, 'purebreed', 'BREED_TYPE', 'animal_dogs', 'Dogs & puppies', NULL, 'Pure Breed', NULL),
(101, '0093abf6184538235c14f6646967ac2d', '2019-03-07 04:42:14', '2019-03-07 04:42:14', b'1', 0, 'animals', 'Animals & Pets', NULL, 'mixed_breed', 'BREED_TYPE', 'animal_dogs', 'Dogs & puppies', NULL, 'Mixed Breed', NULL),
(102, '2089a981570658eda899fb2149bddaa2', '2019-03-07 04:42:38', '2019-03-07 04:42:38', b'1', 0, 'animals', 'Animals & Pets', NULL, 'bombay', 'BREED', 'animal_cats', 'Cats & Kittens', NULL, 'Bombay', NULL),
(103, '1b87c43f0e21a229d70dce5b4ff1209b', '2019-03-07 04:43:01', '2019-03-07 04:43:01', b'1', 0, 'animals', 'Animals & Pets', NULL, 'burmilla', 'BREED', 'animal_cats', 'Cats & Kittens', NULL, 'Burmilla', NULL),
(104, 'cd680a1e38baa06f29c153e085cec33a', '2019-03-07 04:43:38', '2019-03-07 04:43:38', b'1', 0, 'animals', 'Animals & Pets', NULL, 'mixed', 'BREED_TYPE', 'animal_cats', 'Cats & Kittens', NULL, 'Mixed Breed', NULL),
(105, '6cd342e83aa61049a12e24a82d85cc98', '2019-03-07 04:44:02', '2019-03-07 04:44:02', b'1', 0, 'animals', 'Animals & Pets', NULL, 'pure_breed', 'BREED_TYPE', 'animal_cats', 'Cats & Kittens', NULL, 'Pure Breed', NULL),
(106, '92f2289b064328d3baf1b9277d55e889', '2019-03-07 04:45:24', '2019-03-07 04:45:24', b'1', 0, 'animals', 'Animals & Pets', NULL, 'adult', 'AGE_GROUP', '', '', NULL, 'Adult', NULL),
(107, '294fc815e870ba36a36f387982a4ea8a', '2019-03-07 04:45:43', '2019-03-07 04:45:43', b'1', 0, 'animals', 'Animals & Pets', NULL, 'baby', 'AGE_GROUP', '', '', NULL, 'Baby', NULL),
(108, '71a6cbcecb30fbdc3d315f4d55c5b50d', '2019-03-07 04:48:11', '2019-03-07 04:48:11', b'1', 0, 'electronics', 'Electronics', NULL, 'audio_cables', 'ITEM_TYPE', 'electronics_audio', 'Audio & music equipment', NULL, 'Audio Cables', NULL),
(109, 'e0d034e246166c1b29a8cb62f4ea0f36', '2019-03-07 04:48:41', '2019-03-07 04:48:41', b'1', 0, 'electronics', 'Electronics', NULL, 'cd_players', 'ITEM_TYPE', 'electronics_audio', 'Audio & music equipment', NULL, 'CD Players', NULL),
(110, 'c27353aa712992fd2aeb87f52d53329f', '2019-03-07 04:49:10', '2019-03-07 04:49:10', b'1', 0, 'electronics', 'Electronics', NULL, 'dj_mixers', 'ITEM_TYPE', 'electronics_audio', 'Audio & music equipment', NULL, 'DJ Mixers', NULL),
(111, 'e4c1038034b6d56afc483c5036a8c61f', '2019-03-07 04:49:48', '2019-03-07 04:49:48', b'1', 0, 'electronics', 'Electronics', NULL, 'adapters', 'ITEM_TYPE', 'electronics_vidcam', 'Video cameras & Accessories', NULL, 'Adapters', NULL),
(112, '2c56c30bce88297cac33da17c2505984', '2019-03-07 04:50:15', '2019-03-07 04:50:15', b'1', 0, 'electronics', 'Electronics', NULL, 'cases', 'ITEM_TYPE', 'electronics_vidcam', 'Video cameras & Accessories', NULL, 'Cases', NULL),
(113, '377cce3d16b47a242f63de645c9a3e50', '2019-03-07 04:50:43', '2019-03-07 04:50:43', b'1', 0, 'electronics', 'Electronics', NULL, 'canon', 'ITEM_MAKE', 'electronics_vidcam', 'Video cameras & Accessories', NULL, 'Canon', NULL),
(114, 'f8826a7c1d2aa1421f41f7287a3b2cd5', '2019-03-07 04:50:57', '2019-03-07 04:50:57', b'1', 0, 'electronics', 'Electronics', NULL, 'cisco', 'ITEM_MAKE', 'electronics_vidcam', 'Video cameras & Accessories', NULL, 'Cisco', NULL),
(115, '1ea4ba77ea66336cb74694104850a1e1', '2019-03-07 04:51:56', '2019-03-07 04:51:56', b'1', 0, 'electronics', 'Electronics', NULL, 'adapters', 'ITEM_TYPE', 'electronics_comp_access', 'Computer accessories', NULL, 'Adapters', NULL),
(116, 'd70e5e70902c49debb315ed6880de5a0', '2019-03-07 04:52:29', '2019-03-07 04:52:29', b'1', 0, 'electronics', 'Electronics', NULL, 'cables', 'ITEM_TYPE', 'electronics_comp_access', 'Computer accessories', NULL, 'Cables', NULL),
(117, '004beec8c557152b563cdc508bc026cc', '2019-03-07 04:53:45', '2019-03-07 04:53:45', b'1', 0, 'electronics', 'Electronics', NULL, 'Acer', 'BRAND', 'electronics_comp_access', 'Computer accessories', NULL, 'Acer', NULL),
(118, '590cca6e403d2cba282436ee0026929b', '2019-03-07 04:54:02', '2019-03-07 04:54:02', b'1', 0, 'electronics', 'Electronics', NULL, 'Apple', 'BRAND', 'electronics_comp_access', 'Computer accessories', NULL, 'apple', NULL),
(119, '255ccaa6f4f5021325030d37b790e44b', '2019-03-07 04:55:02', '2019-03-07 04:55:02', b'1', 0, 'electronics', 'Electronics', NULL, 'desktop', 'ITEM_TYPE', 'electronics_laptops', ' Laptops and computer', NULL, 'Desktop & All-in-ones', NULL),
(120, 'a24275c3f7508981fa73b9411ab69a88', '2019-03-07 04:55:18', '2019-03-07 04:55:18', b'1', 0, 'electronics', 'Electronics', NULL, 'laptop', 'ITEM_TYPE', 'electronics_laptops', ' Laptops and computer', NULL, 'Laptop', NULL),
(121, '487ff72b84de371c6a0a51274a93a362', '2019-03-07 04:55:52', '2019-03-07 04:55:52', b'1', 0, 'electronics', 'Electronics', NULL, 'servers', 'ITEM_TYPE', 'electronics_laptops', ' Laptops and computer', NULL, 'Servers', NULL),
(122, 'd3b2db9d83f99a83153b0a3e7ef36f95', '2019-03-07 04:59:36', '2019-03-07 04:59:36', b'1', 0, 'electronics', 'Electronics', NULL, 'amd', 'PROCESSOR', 'electronics_tv_equip', 'TV & DVD Equipment', NULL, 'AMD', NULL),
(123, 'abdda6e533cd8883477af4d67ea5eac4', '2019-03-07 05:00:07', '2019-03-07 05:00:07', b'1', 0, 'electronics', 'Electronics', NULL, 'corei5', 'PROCESSOR', 'electronics_laptops', ' Laptops and computer', NULL, 'Intel Core i5', NULL),
(124, 'e3e405e10e1694977e8457e600251268', '2019-03-07 05:01:25', '2019-03-07 05:01:25', b'1', 0, 'electronics', 'Electronics', NULL, 'single', 'CORES', 'electronics_laptops', ' Laptops and computer', NULL, 'Single Core', NULL),
(125, 'de34046ad5ef63a4e90b1568323cdcb1', '2019-03-07 05:01:58', '2019-03-07 05:01:58', b'1', 0, 'electronics', 'Electronics', NULL, 'dual', 'CORES', 'electronics_laptops', ' Laptops and computer', NULL, 'Dual Core', NULL),
(126, 'f1c5b057d5e7b20ea254ae9715ecce47', '2019-03-07 05:02:25', '2019-03-07 05:02:25', b'1', 0, 'electronics', 'Electronics', NULL, '1', 'RAM', 'electronics_laptops', ' Laptops and computer', NULL, '1GB', NULL),
(127, '6bb9dc89f35a26f814e1cfa8626d7938', '2019-03-07 05:02:48', '2019-03-07 05:02:48', b'1', 0, 'electronics', 'Electronics', NULL, '4', 'RAM', 'electronics_laptops', ' Laptops and computer', NULL, '4GB', NULL),
(128, '1a5ce45f2a4a7cf66c47731b0345c711', '2019-03-07 05:03:20', '2019-03-07 05:03:20', b'1', 0, 'electronics', 'Electronics', NULL, '256', 'STORE_CAPACITY', 'electronics_laptops', ' Laptops and computer', NULL, '256GB', NULL),
(129, '185774b5e4f26cf653c158954b7fe521', '2019-03-07 05:03:52', '2019-03-07 05:03:52', b'1', 0, 'electronics', 'Electronics', NULL, '500', 'STORE_CAPACITY', 'electronics_laptops', ' Laptops and computer', NULL, '500GB', NULL),
(130, '1dfb26cb80042742a088a231fcbb4a73', '2019-03-07 05:04:22', '2019-03-07 05:04:22', b'1', 0, 'electronics', 'Electronics', NULL, 'hdd', 'STORE_TYPE', 'electronics_laptops', ' Laptops and computer', NULL, 'HDD', NULL),
(131, '6b5ca61477e023eac8eec3a620721c54', '2019-03-07 05:04:48', '2019-03-07 05:04:48', b'1', 0, 'electronics', 'Electronics', NULL, 'ssd', 'STORE_TYPE', 'electronics_laptops', ' Laptops and computer', NULL, 'SSD', NULL),
(132, 'e87948096313af973c81e36f7409c834', '2019-03-07 05:05:23', '2019-03-07 05:05:23', b'1', 0, 'electronics', 'Electronics', NULL, '10.1', 'SIZE', 'electronics_laptops', ' Laptops and computer', NULL, '10.1', NULL),
(133, 'c53ee0339e12e19e39dad75e79556b6d', '2019-03-07 05:05:58', '2019-03-07 05:05:58', b'1', 0, 'electronics', 'Electronics', NULL, '14', 'SIZE', 'electronics_laptops', ' Laptops and computer', NULL, '14', NULL),
(134, '8071ca449ca5bf65d5d36e7504875cf9', '2019-03-07 05:07:05', '2019-03-07 05:07:05', b'1', 0, 'electronics', 'Electronics', NULL, 'windows8', 'OS', 'electronics_laptops', ' Laptops and computer', NULL, 'Windows 8', NULL),
(135, 'd060d074dec624fd49bb142078da0d12', '2019-03-07 05:07:21', '2019-03-07 05:07:21', b'1', 0, 'electronics', 'Electronics', NULL, 'linux', 'OS', 'electronics_laptops', ' Laptops and computer', NULL, 'Linux', NULL),
(136, 'c08754850e76cfbbebf7e9824b1d66f0', '2019-03-07 05:14:50', '2019-03-07 05:14:50', b'1', 0, 'electronics', 'Electronics', 'ITEM_TYPE', 'acer', 'BRAND', 'electronics_laptops', ' Laptops and computer', NULL, 'Acer', 119),
(137, '13a506cc04f8b11495cdb72efced66d2', '2019-03-07 05:16:29', '2019-03-07 05:16:29', b'1', 0, 'electronics', 'Electronics', 'ITEM_TYPE', 'lenovo', 'BRAND', 'electronics_laptops', ' Laptops and computer', NULL, 'Lenovo', 119),
(138, '7a0685952d1b114f858035750534b6a4', '2019-03-07 05:16:57', '2019-03-07 05:16:57', b'1', 0, 'electronics', 'Electronics', 'ITEM_TYPE', 'hp', 'BRAND', 'electronics_laptops', ' Laptops and computer', NULL, 'HP', 119),
(139, '6b6e848f6e3b1130b31bab00be3e530e', '2019-03-07 05:17:26', '2019-03-07 05:17:26', b'1', 0, 'electronics', 'Electronics', 'ITEM_TYPE', 'dell', 'BRAND', 'electronics_laptops', ' Laptops and computer', NULL, 'Dell', 120),
(140, '5e75ef3fdfd038b3dbe357302ef65650', '2019-03-07 05:17:52', '2019-03-07 05:17:52', b'1', 0, 'electronics', 'Electronics', 'ITEM_TYPE', 'apple', 'BRAND', 'electronics_laptops', ' Laptops and computer', NULL, 'Apple', 120),
(141, '3e96844534f32931d15adee17eb748d6', '2019-03-07 05:18:18', '2019-03-07 05:18:18', b'1', 0, 'electronics', 'Electronics', 'ITEM_TYPE', 'sony', 'BRAND', 'electronics_laptops', ' Laptops and computer', NULL, 'Sony', 120),
(142, 'f1f2f2da9bcd2f1f0cad36454f6a6249', '2019-03-07 05:18:53', '2019-03-07 05:18:53', b'1', 0, 'electronics', 'Electronics', 'ITEM_TYPE', 'lenovo', 'BRAND', 'electronics_laptops', ' Laptops and computer', NULL, 'Lenovo', 120),
(143, '22cd9e4bd7c5d94545061463bd73f944', '2019-03-07 05:19:45', '2019-03-07 05:19:45', b'1', 0, 'electronics', 'Electronics', 'ITEM_TYPE', 'dell', 'BRAND', 'electronics_laptops', ' Laptops and computer', NULL, 'Dell', 121),
(144, '8c0b4d269aca416ef90d5162cadcef00', '2019-03-07 05:20:21', '2019-03-07 05:20:21', b'1', 0, 'electronics', 'Electronics', 'ITEM_TYPE', 'hp', 'BRAND', 'electronics_laptops', ' Laptops and computer', NULL, 'HP', 121),
(145, '42af15dbf298630e646d17178262bc39', '2019-03-07 05:20:51', '2019-03-07 05:20:51', b'1', 0, 'electronics', 'Electronics', 'ITEM_TYPE', 'others', 'BRAND', 'electronics_laptops', ' Laptops and computer', NULL, 'Others', 121),
(146, '83b6f795c1198a19e3dd7c3de778d9a4', '2019-03-08 03:35:39', '2019-03-08 03:35:39', b'1', 0, 'electronics', 'Electronics', 'BRAND', 'aspiree600', 'MODEL', 'electronics_laptops', ' Laptops and computer', NULL, 'Aspire E600', 136),
(148, 'df640ffe9c8c1251b1035dd1ebcf0ee7', '2019-03-08 03:39:46', '2019-03-08 03:39:46', b'1', 0, 'electronics', 'Electronics', 'BRAND', 'aspire_e700', 'MODEL', 'electronics_laptops', ' Laptops and computer', NULL, 'Aspire E700', 136),
(149, '7184281b674a847853027f98cd0d502c', '2019-03-08 03:40:42', '2019-03-08 03:40:42', b'1', 0, 'electronics', 'Electronics', 'BRAND', 'envy_27', 'MODEL', 'electronics_laptops', ' Laptops and computer', NULL, 'Envy 27', 138),
(150, 'ca880f61c372034982f064e205676f87', '2019-03-08 03:42:36', '2019-03-08 03:42:36', b'1', 0, 'electronics', 'Electronics', 'BRAND', 'pavilion_27', 'MODEL', 'electronics_laptops', ' Laptops and computer', NULL, 'Pavilion 27', 138),
(151, '1d3fa299cf9ea63d4d2868f315ad216c', '2019-03-08 03:56:28', '2019-03-08 03:56:28', b'1', 0, 'electronics', 'Electronics', 'ITEM_TYPE', 'all_in_one', 'SUB_TYPE', 'electronics_laptops', ' Laptops and computer', NULL, 'All-in-one', 119),
(152, '68c48cb8460e044af271c9696639c415', '2019-03-08 03:57:07', '2019-03-08 03:57:07', b'1', 0, 'electronics', 'Electronics', 'ITEM_TYPE', 'workstation', 'SUB_TYPE', 'electronics_laptops', ' Laptops and computer', NULL, 'Workstation', 119),
(153, 'b6b7b49e3038c433b0dd707635e8ee56', '2019-03-08 03:58:15', '2019-03-08 03:58:15', b'1', 0, 'electronics', 'Electronics', 'ITEM_TYPE', 'chrome_book', 'SUB_TYPE', 'electronics_laptops', ' Laptops and computer', NULL, 'Chromebooks', 120),
(154, '91ef8025dc4a27e32f1d622a9fbfe780', '2019-03-08 03:58:57', '2019-03-08 03:58:57', b'1', 0, 'electronics', 'Electronics', 'ITEM_TYPE', 'ultrabook', 'SUB_TYPE', 'electronics_laptops', ' Laptops and computer', NULL, 'Ultrabook', 120),
(155, '205f7a73936e3dd8ca9703b773a6d630', '2019-03-08 04:00:13', '2019-03-08 04:00:13', b'1', 0, 'electronics', 'Electronics', 'BRAND', 'inspiron_11z', 'MODEL', 'electronics_laptops', ' Laptops and computer', NULL, 'Inspiron 11Z', 139),
(156, 'e5407f6fc87f2b79ba920603d78f0b1d', '2019-03-08 04:00:58', '2019-03-08 04:00:58', b'1', 0, 'electronics', 'Electronics', 'BRAND', 'macbook_pro', 'MODEL', 'electronics_laptops', ' Laptops and computer', NULL, 'Macbook Pro', 140),
(157, '37dc26843d98a712415199974e549ada', '2019-03-08 04:01:34', '2019-03-08 04:01:34', b'1', 0, 'electronics', 'Electronics', 'BRAND', 'macbook_air', 'MODEL', 'electronics_laptops', ' Laptops and computer', NULL, 'Macbook Air', 140),
(158, 'c740cf43693c816356beafa89a7a13fe', '2019-03-08 04:02:10', '2019-03-08 04:02:10', b'1', 0, 'electronics', 'Electronics', 'ITEM_TYPE', 'rack', 'SUB_TYPE', 'electronics_laptops', ' Laptops and computer', NULL, 'Rack', 121),
(159, '7b805ab30beb97c99bda6de448079af9', '2019-03-08 04:02:44', '2019-03-08 04:02:44', b'1', 0, 'electronics', 'Electronics', 'ITEM_TYPE', 'tower', 'SUB_TYPE', 'electronics_laptops', ' Laptops and computer', NULL, 'Tower', 121),
(160, '0580d3ee41f05dcef2a990f00232c674', '2019-03-08 04:03:39', '2019-03-08 04:03:39', b'1', 0, 'electronics', 'Electronics', 'BRAND', 'poweredge_fx', 'MODEL', 'electronics_laptops', ' Laptops and computer', NULL, 'PowerEdge FX', 143),
(167, '74a45fff99df7b81e9b288cddd4552ae', '2019-03-10 17:36:24', '2019-03-10 17:36:24', b'1', 0, 'fashion', 'Fashion ', NULL, 'back_pack', 'ITEM_TYPE', 'fashion_bags', 'Bags', NULL, 'Backpacks', NULL),
(168, 'a96c274b9120dffdb3b58e15a8033dde', '2019-03-10 17:36:57', '2019-03-10 17:36:57', b'1', 0, 'fashion', 'Fashion ', NULL, 'hand_bag', 'ITEM_TYPE', 'fashion_bags', 'Bags', NULL, 'Handbags', NULL),
(169, 'b48e92f4b6a078707d977686258606d3', '2019-03-10 17:37:30', '2019-03-10 17:37:30', b'1', 0, 'fashion', 'Fashion ', NULL, 'bella', 'BRAND', 'fashion_bags', 'Bags', NULL, 'Bella', NULL),
(170, 'c590f03cebe2bccfb824004cc6dc2b58', '2019-03-10 17:37:51', '2019-03-10 17:37:51', b'1', 0, 'fashion', 'Fashion ', NULL, 'dkny', 'BRAND', 'fashion_bags', 'Bags', NULL, 'DKNY', NULL),
(171, 'c2a55c8484be7bd83aec2ff09d2ef1fe', '2019-03-10 17:39:46', '2019-03-10 17:39:46', b'1', 0, 'fashion', 'Fashion ', NULL, 'cotton', 'MATERIAL', 'fashion_bags', 'Bags', NULL, 'Cotton', NULL),
(172, '82795659de7fb70cfe2b1d9e783e57c1', '2019-03-10 17:40:04', '2019-03-10 17:40:04', b'1', 0, 'fashion', 'Fashion ', NULL, 'nylon', 'MATERIAL', 'fashion_bags', 'Bags', NULL, 'Nylon', NULL),
(173, '512d6a78b7a97ac722641e8728ab4ad3', '2019-03-10 17:40:22', '2019-03-10 17:40:22', b'1', 0, 'fashion', 'Fashion ', NULL, 'linen', 'MATERIAL', 'fashion_bags', 'Bags', NULL, 'Linen', NULL),
(174, '8447b2071e34e94a9ba4757f09d479d8', '2019-03-10 17:40:40', '2019-03-10 17:40:40', b'1', 0, 'fashion', 'Fashion ', NULL, 'suede', 'MATERIAL', 'fashion_bags', 'Bags', NULL, 'Suede', NULL),
(175, '334f134251864de80abec173464ae6ac', '2019-03-10 17:41:15', '2019-03-10 17:41:15', b'1', 0, 'fashion', 'Fashion ', NULL, 'zipper', 'CLOSURE', 'fashion_bags', 'Bags', NULL, 'Zipper', NULL),
(176, '05af8c319ce0e373dafac6c4484ddede', '2019-03-10 17:41:41', '2019-03-10 17:41:41', b'1', 0, 'fashion', 'Fashion ', NULL, 'hook_loop', 'CLOSURE', 'fashion_bags', 'Bags', NULL, 'Hook and Loop', NULL),
(177, '94dbaed9b46e503e81122e57c570c9d1', '2019-03-10 17:44:07', '2019-03-10 17:44:07', b'1', 0, 'fashion', 'Fashion ', NULL, 'blazer', 'ITEM_TYPE', 'fashion_clothing', 'Clothing', NULL, 'Blazers', NULL),
(178, '17190e3ad78421db91af3bb992c82c88', '2019-03-10 17:44:24', '2019-03-10 17:44:24', b'1', 0, 'fashion', 'Fashion ', NULL, 'bra', 'ITEM_TYPE', 'fashion_clothing', 'Clothing', NULL, 'Bra', NULL),
(179, 'f7a8e6d5e45da23c68563ba0d4925f94', '2019-03-10 17:44:47', '2019-03-10 17:44:47', b'1', 0, 'fashion', 'Fashion ', NULL, 'boxer', 'ITEM_TYPE', 'fashion_clothing', 'Clothing', NULL, 'Boxers', NULL),
(180, 'b629e14ea7d3e4851dbc510069556d48', '2019-03-10 17:45:10', '2019-03-10 17:45:10', b'1', 0, 'fashion', 'Fashion ', NULL, 'jeans', 'ITEM_TYPE', 'fashion_clothing', 'Clothing', NULL, 'Jeans', NULL),
(181, '52d0674228c3d7b12d18f0c18558dd61', '2019-03-10 17:45:37', '2019-03-10 17:45:37', b'1', 0, 'fashion', 'Fashion ', NULL, 'sport', 'STYLE', 'fashion_clothing', 'Clothing', NULL, 'Sport', NULL),
(182, 'b699ca366a42fc1cd429fe523e63bd0d', '2019-03-10 17:46:09', '2019-03-10 17:46:09', b'1', 0, 'fashion', 'Fashion ', NULL, 'casual', 'STYLE', 'fashion_clothing', 'Clothing', NULL, 'Casual', NULL),
(183, '6fbf02756d178961d21afc8d9dfcf10e', '2019-03-10 17:46:39', '2019-03-10 17:46:39', b'1', 0, 'fashion', 'Fashion ', NULL, 'adidas', 'BRAND', 'fashion_clothing', 'Clothing', NULL, 'Adidas', NULL),
(184, '67a5fff5c85cf68bbdcf6105311b4da0', '2019-03-10 17:47:07', '2019-03-10 17:47:07', b'1', 0, 'fashion', 'Fashion ', NULL, 'nike', 'BRAND', 'fashion_clothing', 'Clothing', NULL, 'Nike', NULL),
(185, '3391501ba3f7d5941b26337c25f2e3f4', '2019-03-10 17:47:33', '2019-03-10 17:47:33', b'1', 0, 'fashion', 'Fashion ', NULL, 'burberry', 'BRAND', 'fashion_clothing', 'Clothing', NULL, 'Burberry', NULL),
(186, 'ba9bbac59064cdaa777bfd79f7a9b9ee', '2019-03-10 17:47:47', '2019-03-10 17:47:47', b'1', 0, 'fashion', 'Fashion ', NULL, 'xxl', 'SIZE', 'fashion_clothing', 'Clothing', NULL, 'XXL', NULL),
(187, '0d1014b5f8a289acb175f7adea8d5778', '2019-03-10 17:48:17', '2019-03-10 17:48:17', b'1', 0, 'fashion', 'Fashion ', NULL, 'M', 'SIZE', 'fashion_clothing', 'Clothing', NULL, 'Medium', NULL),
(188, '3b244b898e985dcc8ccd87dfcdb42a85', '2019-03-10 17:48:31', '2019-03-10 17:48:31', b'1', 0, 'fashion', 'Fashion ', NULL, 's', 'SIZE', 'fashion_clothing', 'Clothing', NULL, 'Small', NULL),
(189, '8f14f9282a03bda59d4aa9d1b58d108b', '2019-03-10 17:49:24', '2019-03-10 17:49:24', b'1', 0, 'fashion', 'Fashion ', NULL, 'chains', 'ITEM_TYPE', 'fashion_jewelry', 'Jewelry', NULL, 'Chains', NULL),
(190, 'dbdbe34896be9591c431959681857c23', '2019-03-10 17:49:44', '2019-03-10 17:49:44', b'1', 0, 'fashion', 'Fashion ', NULL, 'bangles', 'ITEM_TYPE', 'fashion_jewelry', 'Jewelry', NULL, 'Bangles', NULL),
(191, '7bbb0364da7bf2dd188a4039e46266b4', '2019-03-10 17:50:05', '2019-03-10 17:50:05', b'1', 0, 'fashion', 'Fashion ', NULL, 'gold', 'MATERIAL', 'fashion_jewelry', 'Jewelry', NULL, 'Gold', NULL),
(192, 'e07fbe598e3d71a05ea862efb3b7ce7f', '2019-03-10 17:50:29', '2019-03-10 17:50:29', b'1', 0, 'fashion', 'Fashion ', NULL, 'nickel', 'MATERIAL', 'fashion_jewelry', 'Jewelry', NULL, 'Nickel', NULL),
(193, 'a43da0cc068945263195cbbadf0f09f3', '2019-03-10 17:50:51', '2019-03-10 17:50:51', b'1', 0, 'fashion', 'Fashion ', NULL, 'diamond', 'STONE', 'fashion_jewelry', 'Jewelry', NULL, 'Diamond', NULL),
(194, '7392e35d397797646927f3229ed4c8f1', '2019-03-10 17:51:12', '2019-03-10 17:51:12', b'1', 0, 'fashion', 'Fashion ', NULL, 'emerald', 'STONE', 'fashion_jewelry', 'Jewelry', NULL, 'Emerald', NULL),
(195, 'c7c6f5b5a9c7276cef9ec7a1633b462c', '2019-03-10 17:51:35', '2019-03-10 17:51:35', b'1', 0, 'fashion', 'Fashion ', NULL, 'boot', 'ITEM_TYPE', 'fashion_shoes', 'Shoes', NULL, 'Boot', NULL),
(196, 'a159ca2e1bddb7fc207c2e0a148e84b8', '2019-03-10 17:51:55', '2019-03-10 17:51:55', b'1', 0, 'fashion', 'Fashion ', NULL, 'sandals', 'ITEM_TYPE', 'fashion_shoes', 'Shoes', NULL, 'Sandals', NULL),
(197, 'd64c871f605c81df44c88a6938835c9a', '2019-03-10 17:52:25', '2019-03-10 17:52:25', b'1', 0, 'fashion', 'Fashion ', NULL, 'adidas', 'BRAND', 'fashion_shoes', 'Shoes', NULL, 'Adidas', NULL),
(198, 'a55f1578d3d3276eaf14e8605a7d3263', '2019-03-10 17:52:50', '2019-03-10 17:52:50', b'1', 0, 'fashion', 'Fashion ', NULL, 'balenciaga', 'BRAND', 'fashion_shoes', 'Shoes', NULL, 'Balenciaga', NULL),
(199, '561934b5a3f9d28af668256aefa5ec80', '2019-03-10 17:53:07', '2019-03-10 17:53:07', b'1', 0, 'fashion', 'Fashion ', NULL, '35', 'SIZE', 'fashion_shoes', 'Shoes', NULL, '35', NULL),
(200, 'dbdf3060e2e0c3ad924515af8b404d7a', '2019-03-10 17:53:19', '2019-03-10 17:53:19', b'1', 0, 'fashion', 'Fashion ', NULL, '40', 'SIZE', 'fashion_shoes', 'Shoes', NULL, '40', NULL),
(201, 'd3b6d2e747e649b3ec04d62a33a86b16', '2019-03-10 17:54:12', '2019-03-10 17:54:12', b'1', 0, 'fashion', 'Fashion ', NULL, 'cotton', 'MATERIAL', 'fashion_shoes', 'Shoes', NULL, 'Cotton', NULL),
(202, '756dca11dfac283b1fbc09807cc14ff0', '2019-03-10 17:54:40', '2019-03-10 17:54:40', b'1', 0, 'fashion', 'Fashion ', NULL, 'velvet', 'MATERIAL', 'fashion_shoes', 'Shoes', NULL, 'Velvet', NULL),
(203, '3c9ae793707b103940081eef41a5df73', '2019-03-10 17:58:37', '2019-03-10 17:58:37', b'1', 0, 'fashion', 'Fashion ', NULL, 'rubber', 'OUTSOLE', 'fashion_shoes', 'Shoes', NULL, 'Rubber', NULL),
(204, 'e3fbd2f53327a52b5dec0278e78b1386', '2019-03-10 18:00:38', '2019-03-10 18:00:38', b'1', 0, 'fashion', 'Fashion ', NULL, 'pvc', 'OUTSOLE', 'fashion_shoes', 'Shoes', NULL, 'PVC', NULL),
(205, '89f7a778d5e3e1c901c171de2ae30e78', '2019-03-10 18:01:10', '2019-03-10 18:01:10', b'1', 0, 'fashion', 'Fashion ', NULL, 'buckle', 'FASTEN', 'fashion_shoes', 'Shoes', NULL, 'Buckle', NULL),
(206, '9c5ef4d4d78685253b08e1a254592a15', '2019-03-10 18:01:31', '2019-03-10 18:01:31', b'1', 0, 'fashion', 'Fashion ', NULL, 'zip', 'FASTEN', 'fashion_shoes', 'Shoes', NULL, 'Zip', NULL),
(207, 'c5cf0698c3b0783fea7bb901a3a6d275', '2019-03-10 18:02:52', '2019-03-10 18:02:52', b'1', 0, 'fashion', 'Fashion ', NULL, 'adidas', 'BRAND', 'fashion_watches', 'Watches', NULL, 'Adidas', NULL),
(208, '8a0ea943f2f45398574d1850c41da3a1', '2019-03-10 18:03:10', '2019-03-10 18:03:10', b'1', 0, 'fashion', 'Fashion ', NULL, 'dior', 'BRAND', 'fashion_watches', 'Watches', NULL, 'Dior', NULL),
(209, 'bb8c1a5e7c77675e05aea5dc5caa7486', '2019-03-10 18:05:00', '2019-03-10 18:05:00', b'1', 0, 'fashion', 'Fashion ', NULL, 'aluminum', 'MATERIAL', 'fashion_watches', 'Watches', NULL, 'Aluminum', NULL),
(210, 'a6c328300885e2bc829c1c8f1dd1565c', '2019-03-10 18:05:13', '2019-03-10 18:05:13', b'1', 0, 'fashion', 'Fashion ', NULL, 'wood', 'MATERIAL', 'fashion_watches', 'Watches', NULL, 'Wood', NULL),
(211, 'cdb1229d83a951767b011905ac40e10e', '2019-03-10 18:05:54', '2019-03-10 18:05:54', b'1', 0, 'fashion', 'Fashion ', NULL, 'business', 'STYLE', 'fashion_watches', 'Watches', NULL, 'Business', NULL),
(212, '770ef4e0434c688775f7efb14435082b', '2019-03-10 18:06:21', '2019-03-10 18:06:21', b'1', 0, 'fashion', 'Fashion ', NULL, 'casual', 'STYLE', 'fashion_watches', 'Watches', NULL, 'Casual', NULL),
(213, '46cf4b36f4df75faab105e4018dd49a3', '2019-03-10 18:06:52', '2019-03-10 18:06:52', b'1', 0, 'fashion', 'Fashion ', NULL, 'compass', 'FEATURE', 'fashion_watches', 'Watches', NULL, 'Compass', NULL),
(214, '206adb256957d928b3feaf6407188b67', '2019-03-10 18:07:21', '2019-03-10 18:07:21', b'1', 0, 'fashion', 'Fashion ', NULL, 'calendar', 'FEATURE', 'fashion_watches', 'Watches', NULL, 'Calendar', NULL),
(215, 'e55d59099dd3c3a153b171dcf395e3d5', '2019-03-11 15:51:49', '2019-03-11 15:51:49', b'1', 0, 'fashion', 'Fashion ', NULL, 'casual', 'STYLE', 'fashion_shoes', 'Shoes', NULL, 'Casual', NULL),
(216, '0f0a77d3e24c4caecff138bfdae1f022', '2019-03-11 15:52:07', '2019-03-11 15:52:07', b'1', 0, 'fashion', 'Fashion ', NULL, 'sport', 'STYLE', 'fashion_shoes', 'Shoes', NULL, 'Sport', NULL),
(217, '4cdf9fd202b2f10a5167f451a02d695a', '2019-03-11 16:03:24', '2019-03-11 16:03:24', b'1', 0, 'fashion', 'Fashion ', NULL, 'mechanical', 'MOVEMENT', 'fashion_watches', 'Watches', NULL, 'Mechanical', NULL),
(218, '22004035b3bc3422c74ed5666f43c4c1', '2019-03-11 16:03:46', '2019-03-11 16:03:46', b'1', 0, 'fashion', 'Fashion ', NULL, 'quartz', 'MOVEMENT', 'fashion_watches', 'Watches', NULL, 'Quartz', NULL),
(219, '82eb5ebb5026058cadc4b196ae0576a8', '2019-03-11 16:04:10', '2019-03-11 16:04:10', b'1', 0, 'fashion', 'Fashion ', NULL, 'analog', 'DISPLAY', 'fashion_watches', 'Watches', NULL, 'Analog', NULL),
(220, '5d667bbddbc740f67f05ff6cb98fabe1', '2019-03-11 16:04:33', '2019-03-11 16:04:33', b'1', 0, 'fashion', 'Fashion ', NULL, 'digital', 'DISPLAY', 'fashion_watches', 'Watches', NULL, 'Digital', NULL),
(226, 'dd31ff15e4b82955caafd172a87786d7', '2019-06-02 19:49:26', '2019-06-02 19:49:26', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'hair_dye', 'ITEM_TYPE', 'beauty_hair', 'Hair Beauty', NULL, 'Hair Dye', NULL),
(227, '4cfa37b70b07d54da32713234769c56c', '2019-06-02 19:49:58', '2019-06-02 19:49:58', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'creams', 'ITEM_TYPE', 'beauty_hair', 'Hair Beauty', NULL, 'Creams', NULL),
(228, '3ca2890d3c5ef1ab556468d293e27a9c', '2019-06-02 19:52:47', '2019-06-02 19:52:47', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'brushes', 'ITEM_TYPE', 'beauty_makeup', 'Makeup', NULL, 'Brushes', NULL),
(229, 'b5fab889b6f60d7e92102e0c261d14f6', '2019-06-02 19:53:22', '2019-06-02 19:53:22', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'concealers', 'ITEM_TYPE', 'beauty_makeup', 'Makeup', NULL, 'Concealers', NULL),
(230, '951b0a87d6cb1e63e2538882d8eabf35', '2019-06-02 19:53:50', '2019-06-02 19:53:50', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'aromi', 'BRAND', 'beauty_makeup', 'Makeup', NULL, 'Aromi', NULL),
(231, '3d3617ccec1b4fc5aece85c9a5d721d2', '2019-06-02 19:54:14', '2019-06-02 19:54:14', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'becca', 'BRAND', 'beauty_makeup', 'Makeup', NULL, 'Becca', NULL),
(232, '61d02ab056550f9c110a857318e6eec6', '2019-06-02 19:54:43', '2019-06-02 19:54:43', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'light', 'TONE', 'beauty_makeup', 'Makeup', NULL, 'Light', NULL),
(233, 'f8c30af2a30b0430fb5e280e79c7d6d0', '2019-06-02 19:55:05', '2019-06-02 19:55:05', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'medium', 'TONE', 'beauty_makeup', 'Makeup', NULL, 'Medium', NULL),
(234, 'f79162c12a7c61b8373325a2123f5a53', '2019-06-02 20:48:44', '2019-06-02 20:48:44', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'oil', 'FORMULA', 'beauty_fragrance', 'Fragrance', NULL, 'Oil', NULL),
(235, '8ca5b4863692353d1efb1a071d527edd', '2019-06-02 20:49:05', '2019-06-02 20:49:05', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'spray', 'FORMULA', 'beauty_fragrance', 'Fragrance', NULL, 'Spray', NULL),
(237, 'fa611e1227003fe3fa569f0a87ed91ee', '2019-06-02 21:05:22', '2019-06-02 21:05:22', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'adidas', 'BRAND', 'beauty_fragrance', 'Fragrance', NULL, 'Adidas', NULL),
(238, 'c7ea562f4c9f79deddcbcb7a3a379500', '2019-06-02 21:05:43', '2019-06-02 21:05:43', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'aramis', 'BRAND', 'beauty_fragrance', 'Fragrance', NULL, 'Aramis', NULL),
(239, '7e7966cc5ddbc383f54645ff575efef8', '2019-06-02 21:08:20', '2019-06-02 21:08:20', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'citrus', 'SCENT', 'beauty_fragrance', 'Fragrance', NULL, 'Citrus', NULL),
(240, '364677afdf45d0568a4e6870564d4eef', '2019-06-02 21:08:50', '2019-06-02 21:08:50', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'woody', 'SCENT', 'beauty_fragrance', 'Fragrance', NULL, 'Woody', NULL),
(245, '8b46284bf11208f337a3dc1ef686213e', '2019-06-02 22:30:06', '2019-06-02 22:30:06', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'lubricants', 'ITEM_TYPE', 'beauty_sexual', 'Sexual wellness', NULL, 'Lubricants', NULL),
(247, '057acc880813743ccb627fc174cdeafa', '2019-06-02 22:35:36', '2019-06-02 22:35:36', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'creams', 'ITEM_TYPE', 'beauty_skin', 'Skin care', NULL, 'Creams', NULL),
(248, '3c2747f8a6c38424717bc9874519161a', '2019-06-02 22:35:56', '2019-06-02 22:35:56', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'body', 'TARGET_AREA', 'beauty_skin', 'Skin care', NULL, 'Body', NULL),
(249, '0c94347edb5e54971dc62adfa35f23bb', '2019-06-02 22:36:34', '2019-06-02 22:36:34', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'dry', 'SKIN', 'beauty_skin', 'Skin care', NULL, 'Dry', NULL),
(250, '4a9f9fbdae8adcffe60e80514117fcde', '2019-06-02 22:37:09', '2019-06-02 22:37:09', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'whitening', 'BENEFIT', 'beauty_skin', 'Skin care', NULL, 'Whitening', NULL),
(252, '7c5557b90494fa9c27ec27cd79c94a3c', '2019-06-02 22:45:30', '2019-06-02 22:45:30', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'protein', 'ITEM_TYPE', 'beauty_supplements', 'Vitamins & Supplements', NULL, 'Protein', NULL),
(253, '2ab9bbc9651b46ddfce7b424843c0744', '2019-06-02 22:45:56', '2019-06-02 22:45:56', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'gel', 'FORMULA', 'beauty_supplements', 'Vitamins & Supplements', NULL, 'Gel', NULL),
(254, 'e3f4c10582a2e908db99cb9b8f4a1767', '2019-06-02 22:46:18', '2019-06-02 22:46:18', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'baby', 'AGE_GROUP', 'beauty_supplements', 'Vitamins & Supplements', NULL, 'Baby', NULL),
(255, 'e250f41abf563c941ccb982f51177ca4', '2019-06-02 22:46:50', '2019-06-02 22:46:50', b'1', 0, 'beauty', 'Health & Beauty', NULL, 'bottle', 'PACKAGE', 'beauty_supplements', 'Vitamins & Supplements', NULL, 'Bottle', NULL),
(257, '32aa9ee910849fe20b4c76ea7370a3a6', '2019-06-02 23:19:08', '2019-06-02 23:19:08', b'1', 0, 'mobile', 'Mobile phones & Tablets', NULL, 'cases', 'ITEM_TYPE', 'mobile_accessories', 'Mobile phones & tablets accessories', NULL, 'Cases', NULL),
(259, 'ccbe10cf2c56079612631694c9a8635d', '2019-06-14 03:44:16', '2019-06-14 03:44:16', b'1', 0, 'mobile', 'Mobile phones & Tablets', NULL, '4inches', 'SIZE', 'mobile_phones', 'Mobile phones', NULL, '< 4 Inches', NULL),
(260, '21f14a108b4e13c0a980bfbd330eb632', '2019-06-14 03:44:40', '2019-06-14 03:44:40', b'1', 0, 'mobile', 'Mobile phones & Tablets', NULL, '45inches', 'SIZE', 'mobile_phones', 'Mobile phones', NULL, '4-5 Inches', NULL),
(261, 'feece419d792a2a6e9fd8b3adc1d64ba', '2019-06-14 03:45:19', '2019-06-14 03:45:19', b'1', 0, 'mobile', 'Mobile phones & Tablets', NULL, '6.1inches', 'SIZE', 'mobile_phones', 'Mobile phones', NULL, '6.1 > Inches', NULL),
(268, '829d037f6e7bb6c2ac10eb2630c9ef6d', '2019-06-14 03:49:20', '2019-06-14 03:49:20', b'1', 0, 'mobile', 'Mobile phones & Tablets', NULL, 'apple', 'BRAND', 'mobile_phones', 'Mobile phones', NULL, 'Apple', NULL),
(269, 'c07cc33fab8e54657f6799ac7b4aa7da', '2019-06-14 03:49:52', '2019-06-14 03:49:52', b'1', 0, 'mobile', 'Mobile phones & Tablets', NULL, 'samsung', 'BRAND', 'mobile_phones', 'Mobile phones', NULL, 'Samsung', NULL),
(270, '54070f4b79ce38cb2a76b7c63d4b95c9', '2019-06-14 03:50:47', '2019-06-14 03:50:47', b'1', 0, 'mobile', 'Mobile phones & Tablets', 'BRAND', 'galaxy_a3', 'MODEL', 'mobile_phones', 'Mobile phones', NULL, 'Galaxy A3', 269),
(271, 'd95e5e74d6ac7438e9da441ad1a23393', '2019-06-14 03:51:35', '2019-06-14 03:51:35', b'1', 0, 'mobile', 'Mobile phones & Tablets', 'BRAND', 'galaxy_j1', 'MODEL', 'mobile_phones', 'Mobile phones', NULL, 'Galaxy J1', 269),
(272, '3b7c470cbf83ae474d1caa4774f7693a', '2019-06-14 03:54:09', '2019-06-14 03:54:09', b'1', 0, 'mobile', 'Mobile phones & Tablets', 'BRAND', 'iphone_8', 'MODEL', 'mobile_phones', 'Mobile phones', NULL, 'iPhone 8', 268),
(273, '6c95d78def4e86085ec04c35cb5b7dc2', '2019-06-14 03:54:41', '2019-06-14 03:54:41', b'1', 0, 'mobile', 'Mobile phones & Tablets', 'BRAND', 'iphone_7', 'MODEL', 'mobile_phones', 'Mobile phones', NULL, 'iPhone 7', 268),
(274, '0819149c8d3e7f967870fbabeba08dc8', '2019-06-14 03:55:45', '2019-06-14 03:55:45', b'1', 0, 'mobile', 'Mobile phones & Tablets', NULL, 'apple', 'BRAND', 'mobile_tablets', 'Tablets', NULL, 'Apple', NULL),
(275, 'bf5594d84e28dc2516cec5be7b6a1331', '2019-06-14 03:56:03', '2019-06-14 03:56:03', b'1', 0, 'mobile', 'Mobile phones & Tablets', NULL, 'samsung', 'BRAND', 'mobile_tablets', 'Tablets', NULL, 'Samsung', NULL),
(276, 'f3d1da73e32fcb58c81f57eda82e96eb', '2019-06-14 03:57:21', '2019-06-14 03:57:21', b'1', 0, 'mobile', 'Mobile phones & Tablets', 'BRAND', 'galaxy_tab_s4', 'MODEL', 'mobile_tablets', 'Tablets', NULL, 'Galaxy Tab S4', 275),
(277, '1d1543c58005f1d0e78a453598cae939', '2019-06-14 03:58:07', '2019-06-14 03:58:07', b'1', 0, 'mobile', 'Mobile phones & Tablets', 'BRAND', 'ipad_pro', 'MODEL', 'mobile_tablets', 'Tablets', NULL, 'iPad Pro', 274),
(278, '3a110b732a7e7f7980f67c0dbffd0e31', '2019-06-14 03:58:36', '2019-06-14 03:58:36', b'1', 0, 'mobile', 'Mobile phones & Tablets', 'BRAND', 'ipad_air', 'MODEL', 'mobile_tablets', 'Tablets', NULL, 'iPad Air', 274),
(286, 'f02529a7b9c6bdc00e0ddfb879ace057', '2019-06-14 04:06:29', '2019-06-14 04:06:29', b'1', 0, NULL, NULL, NULL, '4gb', 'STORE_CAPACITY', NULL, NULL, NULL, '4 GB', NULL),
(287, 'ea183714df982488c0ef18ad6e0860c7', '2019-06-14 04:06:47', '2019-06-14 04:06:47', b'1', 0, NULL, NULL, NULL, '8gb', 'STORE_CAPACITY', NULL, NULL, NULL, '8 GB', NULL),
(280, '6360bc2eb79f052e4ff1d651d52d72d3', '2019-06-14 03:59:41', '2019-06-14 03:59:41', b'1', 0, 'mobile', 'Mobile phones & Tablets', NULL, '7inches', 'SIZE', 'mobile_tablets', 'Tablets', NULL, '< 7 Inches', NULL),
(281, '5700414eca23c5873992d82f1d847d19', '2019-06-14 04:02:07', '2019-06-14 04:02:07', b'1', 0, NULL, NULL, NULL, 'android', 'OS', NULL, NULL, NULL, 'Android', NULL),
(282, '3d1de47f8c0eb1b56784f0add243a356', '2019-06-14 04:02:21', '2019-06-14 04:02:21', b'1', 0, NULL, NULL, NULL, 'windows', 'OS', NULL, NULL, NULL, 'Windows', NULL),
(283, '7057240bbd22236178c2bd70f9fa2c62', '2019-06-14 04:02:45', '2019-06-14 04:02:45', b'1', 0, NULL, NULL, NULL, 'ios', 'OS', NULL, NULL, NULL, 'iOS', NULL),
(284, '2f88c26fe37334d949efd38791fd1a6a', '2019-06-14 04:03:02', '2019-06-14 04:03:02', b'1', 0, NULL, NULL, NULL, '1gb', 'RAM', NULL, NULL, NULL, '1 GB', NULL),
(285, '1d7a5cd885dc61966b3ca759942b9f4e', '2019-06-14 04:03:16', '2019-06-14 04:03:16', b'1', 0, NULL, NULL, NULL, '2gb', 'RAM', NULL, NULL, NULL, '2 GB', NULL),
(288, 'bbb9ddaa62607a509a2016bc60b190be', '2019-06-30 19:27:23', '2019-06-30 19:27:23', b'1', 0, 'job', 'Jobs', NULL, 'full_time', 'JOB_TYPE', NULL, NULL, NULL, 'Full Time', NULL),
(289, 'b5296341eef7a2f5d71fc257c325bcb6', '2019-06-30 19:27:53', '2019-06-30 19:27:53', b'1', 0, 'job', 'Jobs', NULL, 'part_time', 'JOB_TYPE', NULL, NULL, NULL, 'Part-Time', NULL),
(290, 'c0de8e81ab6c52dec7c28b8713c11f6c', '2019-06-30 19:28:37', '2019-06-30 19:28:37', b'1', 0, 'job', 'Jobs', NULL, 'less1', 'JOB_EXPERIENCE', NULL, NULL, NULL, 'Less than 1 year', NULL),
(291, '36b5f54dd0b7300657cc7833037516c3', '2019-07-04 22:39:13', '2019-07-04 22:39:13', b'1', 0, 'repair', 'Repair & Construction', NULL, 'r_blocks', 'ITEM_TYPE', 'b_materials', 'Building Materials', NULL, 'Blocks', NULL),
(292, '585e6ad171188bbe584ce072469c020e', '2019-07-04 22:39:29', '2019-07-04 22:39:29', b'1', 0, 'repair', 'Repair & Construction', NULL, 'r_bricks', 'ITEM_TYPE', 'b_materials', 'Building Materials', NULL, 'Bricks', NULL),
(293, 'f5a3d764a2a63568a1b8735d0198ee04', '2019-07-04 22:40:11', '2019-07-04 22:40:11', b'1', 0, 'repair', 'Repair & Construction', NULL, 'r_solar_light', 'ITEM_TYPE', 'solar_energy', 'Solar Energy', NULL, 'Solar Lights', NULL),
(294, '6990720dedf82ef247f4552e2aa15239', '2019-07-04 22:40:33', '2019-07-04 22:40:33', b'1', 0, 'repair', 'Repair & Construction', NULL, 'r_solar_fans', 'ITEM_TYPE', 'solar_energy', 'Solar Energy', NULL, 'Solar Fans', NULL),
(295, '24e0fe1e5dd5b06ae324d717f91b6a13', '2019-07-04 22:41:01', '2019-07-04 22:41:01', b'1', 0, 'repair', 'Repair & Construction', NULL, 'r_solar_pnls', 'ITEM_TYPE', 'solar_energy', 'Solar Energy', NULL, 'Solar Panels', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `sec_user`
--

CREATE TABLE `sec_user` (
  `id` bigint(20) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  `code` varchar(255) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(255) NOT NULL,
  `status` bit(1) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sec_user`
--

INSERT INTO `sec_user` (`id`, `last_modified_date`, `code`, `created_by`, `created_date`, `modified_by`, `status`, `version`, `display_name`, `email`, `first_name`, `last_name`, `password`, `phone_number`, `role`) VALUES
(1, '2019-06-16 21:33:47', 'd72d357f620229b46ceb360ea407252b', 'SYSTEM', '2019-06-16 21:33:47', 'SYSTEM', b'0', 0, 'Ndumanya Goodluck', 'goody11@gmail.com', 'Goodluck', 'Ndumanya', '$2a$10$CojtG0/Lg5mVzJEzCF6YU.HOE3bxHGIrfI6K7og7bIDVVXvlOk9mu', '09026105532', 'ADMIN');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ad_contact`
--
ALTER TABLE `ad_contact`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_tir65h3pbquwcdgjuxqkjotp5` (`code`);

--
-- Indexes for table `ad_favorites`
--
ALTER TABLE `ad_favorites`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_trxi0evbu03lg1ax4ckede10p` (`code`);

--
-- Indexes for table `ad_image`
--
ALTER TABLE `ad_image`
  ADD KEY `FKr5lo5bwau2cu3lx3dei97638h` (`ad_id`);

--
-- Indexes for table `ad_item`
--
ALTER TABLE `ad_item`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_gtmi7gk4acyp96c5iosopaguf` (`code`),
  ADD KEY `FKed7fq3w5cuw1s4xuyxylxclk7` (`contact_id`);

--
-- Indexes for table `cat_beauty`
--
ALTER TABLE `cat_beauty`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cat_comm_tool`
--
ALTER TABLE `cat_comm_tool`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cat_electronic`
--
ALTER TABLE `cat_electronic`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cat_fashion`
--
ALTER TABLE `cat_fashion`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cat_job`
--
ALTER TABLE `cat_job`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cat_kid`
--
ALTER TABLE `cat_kid`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cat_mobile`
--
ALTER TABLE `cat_mobile`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cat_repair`
--
ALTER TABLE `cat_repair`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cat_vehicle`
--
ALTER TABLE `cat_vehicle`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `core_msg`
--
ALTER TABLE `core_msg`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_56ha3x55o01jytuauwh1be8tc` (`code`),
  ADD KEY `FK4p4ravtsrlsf7f5h7t9rry27n` (`contact_id`);

--
-- Indexes for table `gb_category`
--
ALTER TABLE `gb_category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_rkqqsd6iiable30anim62ttew` (`code`),
  ADD KEY `FKmaglw4p3w59k62c4lefhl3u5q` (`parent_id`);

--
-- Indexes for table `gb_picklist`
--
ALTER TABLE `gb_picklist`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_bwr07jpjy68v3mi8hp7ooyrg6` (`code`),
  ADD KEY `FKs5d5gm3lalb9e7bs0r7jkgs1x` (`parent_id`);

--
-- Indexes for table `sec_user`
--
ALTER TABLE `sec_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_3nthro2p8j6v4xwpqsjb9x33g` (`code`),
  ADD UNIQUE KEY `UK_numjsbs0wblvnqi7po8eyeo6y` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ad_contact`
--
ALTER TABLE `ad_contact`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ad_favorites`
--
ALTER TABLE `ad_favorites`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ad_item`
--
ALTER TABLE `ad_item`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `core_msg`
--
ALTER TABLE `core_msg`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `gb_category`
--
ALTER TABLE `gb_category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- AUTO_INCREMENT for table `gb_picklist`
--
ALTER TABLE `gb_picklist`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=296;

--
-- AUTO_INCREMENT for table `sec_user`
--
ALTER TABLE `sec_user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
