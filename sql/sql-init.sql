# ************************************************************
# Sequel Ace SQL dump
# Version 20046
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# Host: 106.15.102.17 (MySQL 5.5.68-MariaDB)
# Database: product-manager
# Generation Time: 2024-09-14 09:35:44 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE='NO_AUTO_VALUE_ON_ZERO', SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table sys_access_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_access_info`;

CREATE TABLE `sys_access_info` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `content` text COLLATE utf8mb4_unicode_ci,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `sys_access_info` WRITE;
/*!40000 ALTER TABLE `sys_access_info` DISABLE KEYS */;

INSERT INTO `sys_access_info` (`id`, `content`, `create_time`)
VALUES
	(1,'用户信息:[username:admin, name:管理员], 客户端信息:[类型:Computer, 操作系统类型:Mac OS X, ip:0:0:0:0:0:0:0:1, port:64079, 请求方式:GET, URI:/reports/864272287958904832, 请求参数值:mode=multiple]','2024-09-14 17:15:47'),
	(2,'用户信息:[username:admin, name:管理员], 客户端信息:[类型:Computer, 操作系统类型:Mac OS X, ip:0:0:0:0:0:0:0:1, port:64079, 请求方式:GET, URI:/forms/page/864275104153022464/866290810776231936, 请求参数值:readonly=false]','2024-09-14 17:15:49'),
	(3,'用户信息:[username:admin, name:管理员], 客户端信息:[类型:Computer, 操作系统类型:Mac OS X, ip:0:0:0:0:0:0:0:1, port:64079, 请求方式:PUT, URI:/forms/ajax/864287608526680064/866290808335147008, 请求参数值:{\"param\":[[\"参数\",\"1\",\"\"],[\"参数\",\"2\",\"\"]],\"id\":\"866290808335147008\"}]','2024-09-14 17:15:52'),
	(4,'用户信息:[username:admin, name:管理员], 客户端信息:[类型:Computer, 操作系统类型:Mac OS X, ip:0:0:0:0:0:0:0:1, port:64079, 请求方式:PUT, URI:/forms/ajax/864275104153022464/866290810776231936, 请求参数值:{\"id\":\"866290808335147008\",\"code\":\"0009\",\"name\":\"无配件-MultifunctionalCordlessWetandDryVacuumCleaner\",\"category\":\"TEST\",\"supplier\":\"866384383597125632\",\"usdPrice\":\"120\",\"rmbPrice\":\"10\",\"remark\":\"\",\"usdPriceTemplate\":\"[]\",\"usdPriceTemplate_file\":\"\",\"rmbPriceTemplate\":\"[]\",\"rmbPriceTemplate_file\":\"\",\"description\":\"参数描述\",\"accessoryList\":[],\"sellingPoint\":[[\"1.Sucking,moppingandwashingtogether\"],[\"2.180°flatworking\"]],\"leadTime\":[[\"forfirstorder:50days\"],[\"forrepeatorder:35days\"],[\"forsample:10days\"]],\"packingInformation\":[[\"Productpacking:1pcpackedinto1cartonbox\",\"\"],[\"20\",\"384pcs\"],[\"40GP\",\"792pcs\"],[\"40HQ\",\"891pcs\"],[\"Productsize\",\"260*210*1050mm\"],[\"Cartonsize\",\"722*357*280mm\"],[\"Productnetweight\",\"4kg\"],[\"Grossweight\",\"6kg\"]],\"attrInstanceId\":\"866290808335147008\",\"pictures\":[{\"fullPath\":\"pictures/866294524710789120.jpg\",\"extension\":\"jpg\",\"fullName\":\"DC387.jpg\",\"updateTime\":\"2024-09-1410:38:07\",\"url\":\"https://dc-product-manager.oss-cn-shanghai.aliyuncs.com/pictures/866294524710789120.jpg\",\"path\":\"866294524710789120.jpg\",\"groupName\":\"pictures\",\"createBy\":\"1\",\"size\":\"58157\",\"createTime\":\"2024-09-1410:38:07\",\"updateBy\":\"1\",\"name\":\"DC387\",\"id\":\"866294526820524032\",\"contentType\":\"image/jpeg\"},{\"fullPath\":\"pictures/866294545673920512.png\",\"extension\":\"png\",\"fullName\":\"DC370.png\",\"updateTime\":\"2024-09-1410:38:12\",\"url\":\"https://dc-product-manager.oss-cn-shanghai.aliyuncs.com/pictures/866294545673920512.png\",\"path\":\"866294545673920512.png\",\"groupName\":\"pictures\",\"createBy\":\"1\",\"size\":\"619683\",\"createTime\":\"2024-09-1410:38:12\",\"updateBy\":\"1\",\"name\":\"DC370\",\"id\":\"866294546202402816\",\"contentType\":\"image/png\"}],\"certificate\":\"CE,CB,RoHs,CCC\"}]','2024-09-14 17:15:53'),
	(5,'用户信息:[username:admin, name:管理员], 客户端信息:[类型:Computer, 操作系统类型:Mac OS X, ip:0:0:0:0:0:0:0:1, port:64079, 请求方式:GET, URI:/reports/864272287958904832, 请求参数值:mode=multiple]','2024-09-14 17:15:54'),
	(6,'用户信息:[username:admin, name:管理员], 客户端信息:[类型:Computer, 操作系统类型:Mac OS X, ip:127.0.0.1, port:53367, 请求方式:GET, URI:/reports/864272287958904832, 请求参数值:mode=multiple]','2024-09-14 17:31:38'),
	(7,'用户信息:[username:admin, name:管理员], 客户端信息:[类型:Computer, 操作系统类型:Mac OS X, ip:127.0.0.1, port:53367, 请求方式:GET, URI:/forms/page/864275104153022464/866311879989960704, 请求参数值:readonly=false]','2024-09-14 17:31:41'),
	(8,'用户信息:[username:admin, name:管理员], 客户端信息:[类型:Computer, 操作系统类型:Mac OS X, ip:127.0.0.1, port:53367, 请求方式:GET, URI:/forms/page/864275104153022464/866082991015727104, 请求参数值:readonly=false]','2024-09-14 17:32:01'),
	(9,'用户信息:[username:admin, name:管理员], 客户端信息:[类型:Computer, 操作系统类型:Mac OS X, ip:127.0.0.1, port:53367, 请求方式:GET, URI:/forms/page/864275104153022464/865315687927734272, 请求参数值:readonly=false]','2024-09-14 17:32:05'),
	(10,'用户信息:[username:admin, name:管理员], 客户端信息:[类型:Computer, 操作系统类型:Mac OS X, ip:127.0.0.1, port:53368, 请求方式:GET, URI:/forms/page/864275104153022464/865315687927734272, 请求参数值:readonly=false]','2024-09-14 17:33:09'),
	(11,'用户信息:[username:admin, name:管理员], 客户端信息:[类型:Computer, 操作系统类型:Mac OS X, ip:127.0.0.1, port:53366, 请求方式:GET, URI:/reports/864272287958904832, 请求参数值:mode=multiple]','2024-09-14 17:33:24'),
	(12,'用户信息:[username:admin, name:管理员], 客户端信息:[类型:Computer, 操作系统类型:Mac OS X, ip:127.0.0.1, port:53366, 请求方式:GET, URI:/forms/page/864275104153022464/865939682041569280, 请求参数值:readonly=false]','2024-09-14 17:33:26'),
	(13,'用户信息:[username:admin, name:管理员], 客户端信息:[类型:Computer, 操作系统类型:Mac OS X, ip:127.0.0.1, port:53366, 请求方式:GET, URI:/forms/page/864275104153022464/865315687927734272, 请求参数值:readonly=false]','2024-09-14 17:33:28'),
	(14,'用户信息:[username:admin, name:管理员], 客户端信息:[类型:Computer, 操作系统类型:Mac OS X, ip:127.0.0.1, port:53366, 请求方式:GET, URI:/reports/864272287958904832, 请求参数值:mode=multiple]','2024-09-14 17:33:59'),
	(15,'用户信息:[username:admin, name:管理员], 客户端信息:[类型:Computer, 操作系统类型:Mac OS X, ip:0:0:0:0:0:0:0:1, port:54246, 请求方式:GET, URI:/reports/864272287958904832, 请求参数值:mode=multiple]','2024-09-14 17:34:04'),
	(16,'用户信息:[username:admin, name:管理员], 客户端信息:[类型:Computer, 操作系统类型:Mac OS X, ip:0:0:0:0:0:0:0:1, port:54246, 请求方式:GET, URI:/forms/page/864275104153022464/865939682041569280, 请求参数值:readonly=false]','2024-09-14 17:34:06'),
	(17,'用户信息:[username:admin, name:管理员], 客户端信息:[类型:Computer, 操作系统类型:Mac OS X, ip:0:0:0:0:0:0:0:1, port:54246, 请求方式:GET, URI:/products/export-price, 请求参数值:productId=865939682041569280&customerId=866362648948678656&priceType=1&step=0.87&quantity=12]','2024-09-14 17:34:20');

/*!40000 ALTER TABLE `sys_access_info` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_code_description
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_code_description`;

CREATE TABLE `sys_code_description` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `code` varchar(32) NOT NULL COMMENT '外部可见，唯一code',
  `description` varchar(32) DEFAULT NULL,
  `category` enum('MATERIAL','PURCHASING_ORG','PACKAGING','SALES_ORG') DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` bit(1) DEFAULT NULL COMMENT '是否逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='编号-描述 表';



# Dump of table sys_dict
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` bigint(20) DEFAULT NULL,
  `type` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `label` varchar(32) NOT NULL,
  `sort` int(11) DEFAULT NULL,
  `remark` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`type`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `sys_dict` WRITE;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;

INSERT INTO `sys_dict` (`id`, `type`, `name`, `label`, `sort`, `remark`)
VALUES
	(865566493805420544,'PRODUCT_TYPE','DTJ','地毯机',3,NULL),
	(864965443058741248,'PRODUCT_TYPE','PET-VACUUM','宠物吸',2,NULL),
	(866383315727327232,'PRODUCT_TYPE','TEST','测试',9,NULL),
	(695367653333712897,'PRODUCT_TYPE','WASH','洗地机',1,NULL),
	(695367653333712896,'PRODUCT_TYPE','WINCHE','绞盘',0,NULL);

/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_document
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_document`;

CREATE TABLE `sys_document` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `extension` varchar(16) DEFAULT NULL,
  `content_type` varchar(128) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `group_name` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` bit(1) DEFAULT NULL COMMENT '是否逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table sys_form
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_form`;

CREATE TABLE `sys_form` (
  `id` bigint(20) NOT NULL,
  `code` varchar(32) DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  `form_advice_name` varchar(32) DEFAULT NULL,
  `table_name` varchar(32) DEFAULT NULL,
  `repository_name` varchar(32) DEFAULT NULL,
  `storage_strategy` varchar(16) DEFAULT NULL,
  `tpl_name` varchar(32) DEFAULT NULL,
  `additional_info` text,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `sys_form` WRITE;
/*!40000 ALTER TABLE `sys_form` DISABLE KEYS */;

INSERT INTO `sys_form` (`id`, `code`, `name`, `form_advice_name`, `table_name`, `repository_name`, `storage_strategy`, `tpl_name`, `additional_info`, `create_by`, `create_time`, `update_by`, `update_time`, `is_deleted`)
VALUES
	(694980924206493696,'sys_user','用户信息','userFormAdvice','sys_user','userDAO','CREATE_TABLE','tpl/form/form','{\"label-col\":1}',0,'2023-05-30 16:59:02',1,'2024-09-04 12:39:06',b'0'),
	(695312747063197696,'sys_dict','字典','dictFormService','sys_dict','dictDAO','CREATE_TABLE','tpl/form/form-full','{\"showSaveFormBtn\":false}',1,'2023-05-31 14:57:35',1,'2024-09-08 20:07:16',b'0'),
	(859875429241106432,'sys_user_form_tag','用户信息-tag','userFormAdvice','sys_user','userDAO','CREATE_TABLE','demos/student/form-tag','{\"label-col\":1}',1,'2024-08-27 17:30:56',1,'2024-09-03 15:54:21',b'0'),
	(864249380058771456,'t_supplier','供应商信息信息','supplierFormAdvice','t_supplier','supplierDAO','CREATE_TABLE','tpl/form/form-full','{\"showSaveFormBtn\":false}',1,'2024-09-08 19:11:26',1,'2024-09-08 19:20:57',b'0'),
	(864275104153022464,'t_product','产品信息','productFormAdvice','t_product','productDAO','CREATE_TABLE','modules/product/product','{\"showSaveFormBtn\":true}',1,'2024-09-08 20:53:40',1,'2024-09-14 12:05:07',b'0'),
	(864287608526680064,'wash','洗地机参数',NULL,'wash',NULL,'INNER_TABLE','tpl/form/form','{\"showSaveFormBtn\":false,\"label-col\":0}',1,'2024-09-08 21:43:21',1,'2024-09-12 09:23:10',b'0'),
	(866354416721108992,'t_customer','客户信息','customerFormAdvice','t_customer','customerDAO','CREATE_TABLE','tpl/form/form-full','{\"showSaveFormBtn\":false}',1,'2024-09-14 14:36:06',1,'2024-09-14 15:13:47',b'0');

/*!40000 ALTER TABLE `sys_form` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_form_configurer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_form_configurer`;

CREATE TABLE `sys_form_configurer` (
  `id` bigint(20) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `label` varchar(16) NOT NULL,
  `type` varchar(32) NOT NULL,
  `validators` text,
  `options` varchar(5000) DEFAULT NULL,
  `data_source` varchar(32) DEFAULT NULL,
  `default_value` varchar(64) DEFAULT NULL,
  `placeholder` varchar(32) DEFAULT NULL,
  `is_disabled` bit(1) DEFAULT NULL,
  `cpn_value_converter_name` varchar(32) DEFAULT NULL,
  `additional_info` varchar(1024) DEFAULT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `sys_form_configurer` WRITE;
/*!40000 ALTER TABLE `sys_form_configurer` DISABLE KEYS */;

INSERT INTO `sys_form_configurer` (`id`, `name`, `label`, `type`, `validators`, `options`, `data_source`, `default_value`, `placeholder`, `is_disabled`, `cpn_value_converter_name`, `additional_info`, `create_by`, `create_time`, `update_by`, `update_time`, `is_deleted`)
VALUES
	(862387838010822656,'code','用户名','TEXT','[{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"},{\"regex\":\"^[0-9a-zA-Z_\\\\/%\\\\-]{1,}$\",\"message\":\"CODE只能包含数字、字母、下划线、中划线\",\"validatorType\":\"REGEX\"},{\"min\":0,\"max\":16,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 16 个字符\"}]','[]',NULL,NULL,'请输入用户名',NULL,NULL,'{\"tab-index\":\"1\"}',1,'2024-09-03 15:54:21',1,'2024-09-03 15:54:21',b'0'),
	(862387838044377088,'name','姓名','TEXT','[{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"},{\"min\":0,\"max\":16,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 16 个字符\"}]','[]',NULL,NULL,'请输入姓名',NULL,NULL,'{\"tab-index\":\"1\"}',1,'2024-09-03 15:54:21',1,'2024-09-03 15:54:21',b'0'),
	(862387838044377089,'available','可用','SWITCH','[]','[]',NULL,'1',NULL,NULL,NULL,'{\"tab-index\":\"2\"}',1,'2024-09-03 15:54:21',1,'2024-09-03 15:54:21',b'0'),
	(862387838044377090,'roleIds','角色','MULTIPLE_SELECT','[]','[]','sys_role',NULL,NULL,NULL,NULL,'{\"tab-index\":\"2\"}',1,'2024-09-03 15:54:21',1,'2024-09-03 15:54:21',b'0'),
	(862387838044377091,'attachment','附件','FILE','[]','[]',NULL,NULL,NULL,NULL,NULL,'{\"tab-index\":\"2\"}',1,'2024-09-03 15:54:21',1,'2024-09-03 15:54:21',b'0'),
	(862701088350965760,'code','用户名','TEXT','[{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"},{\"regex\":\"^[0-9a-zA-Z_\\\\/%\\\\-]{1,}$\",\"message\":\"CODE只能包含数字、字母、下划线、中划线\",\"validatorType\":\"REGEX\"},{\"min\":0,\"max\":16,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 16 个字符\"}]','[]',NULL,NULL,'请输入用户名',NULL,NULL,'{\"tab-index\":\"1\"}',1,'2024-09-04 12:39:05',1,'2024-09-04 12:39:05',b'0'),
	(862701088376131584,'name','姓名','TEXT','[{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"},{\"min\":0,\"max\":16,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 16 个字符\"}]','[]',NULL,NULL,'请输入姓名',NULL,NULL,'{\"tab-index\":\"1\"}',1,'2024-09-04 12:39:05',1,'2024-09-04 12:39:05',b'0'),
	(862701088376131585,'birthday','出生日期','DATE','[]','[]',NULL,NULL,'请输入出生日期',NULL,NULL,'{\"tab-index\":\"1\"}',1,'2024-09-04 12:39:05',1,'2024-09-04 12:39:05',b'0'),
	(862701088376131586,'available','可用','SWITCH','[]','[]',NULL,'1',NULL,NULL,NULL,'{\"tab-index\":\"2\"}',1,'2024-09-04 12:39:05',1,'2024-09-04 12:39:05',b'0'),
	(862701088376131587,'roleIds','角色','CHECKBOX','[]','[]','sys_role',NULL,NULL,NULL,NULL,'{\"tab-index\":\"2\"}',1,'2024-09-04 12:39:05',1,'2024-09-04 12:39:05',b'0'),
	(862701088376131588,'attachment','附件','FILE','[]','[]',NULL,NULL,NULL,NULL,NULL,'{\"tab-index\":\"2\"}',1,'2024-09-04 12:39:05',1,'2024-09-04 12:39:05',b'0'),
	(864251770119991296,'code','编号','TEXT','[{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"},{\"regex\":\"^[0-9a-zA-Z_\\\\/%\\\\-]{1,}$\",\"message\":\"CODE只能包含数字、字母、下划线、中划线\",\"validatorType\":\"REGEX\"},{\"min\":0,\"max\":16,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 16 个字符\"}]','[]',NULL,NULL,'请输入编号',NULL,NULL,NULL,1,'2024-09-08 19:20:56',1,'2024-09-08 19:20:56',b'0'),
	(864251770140962816,'name','供应商','TEXT','[{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"},{\"min\":0,\"max\":16,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 16 个字符\"}]','[]',NULL,NULL,'请输入供应商',NULL,NULL,NULL,1,'2024-09-08 19:20:56',1,'2024-09-08 19:20:56',b'0'),
	(864251770140962817,'contactName','联系人','TEXT','[{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"},{\"min\":0,\"max\":16,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 16 个字符\"}]','[]',NULL,NULL,'请输入联系人',NULL,NULL,NULL,1,'2024-09-08 19:20:56',1,'2024-09-08 19:20:56',b'0'),
	(864251770140962818,'contactPhone','联系电话','TEXT','[{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"},{\"min\":0,\"max\":16,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 16 个字符\"}]','[]',NULL,NULL,'请输入联系电话',NULL,NULL,NULL,1,'2024-09-08 19:20:56',1,'2024-09-08 19:20:56',b'0'),
	(864263427256823808,'type','分类','SELECT','[{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"},{\"regex\":\"^[0-9a-zA-Z_\\\\/%\\\\-]{1,}$\",\"message\":\"CODE只能包含数字、字母、下划线、中划线\",\"validatorType\":\"REGEX\"},{\"min\":0,\"max\":16,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 16 个字符\"}]','[]','sys_dict_type',NULL,'请输入分类',NULL,NULL,NULL,1,'2024-09-08 20:07:16',1,'2024-09-08 20:07:16',b'0'),
	(864263427277795328,'name','编码','TEXT','[{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"},{\"regex\":\"^[0-9a-zA-Z_\\\\/%\\\\-]{1,}$\",\"message\":\"CODE只能包含数字、字母、下划线、中划线\",\"validatorType\":\"REGEX\"},{\"min\":0,\"max\":16,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 16 个字符\"}]','[]',NULL,NULL,'请输入编码',NULL,NULL,NULL,1,'2024-09-08 20:07:16',1,'2024-09-08 20:07:16',b'0'),
	(864263427277795329,'label','显示值','TEXT','[{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"},{\"min\":0,\"max\":16,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 16 个字符\"}]','[]',NULL,NULL,'请输入显示值',NULL,NULL,NULL,1,'2024-09-08 20:07:16',1,'2024-09-08 20:07:16',b'0'),
	(864263427277795330,'sort','排序号','INTEGER_NUMBER','[]','[]',NULL,'0',NULL,NULL,NULL,NULL,1,'2024-09-08 20:07:16',1,'2024-09-08 20:07:16',b'0'),
	(865550888133988352,'param','其他参数','TABLE','[]','[]',NULL,NULL,'请输入参数',NULL,NULL,'{\"columns\":[{\"name\":\"paramName\",\"label\":\"参数名\",\"cpnType\":\"TEXT\",\"validators\":[{\"min\":0,\"max\":16,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 16 个字符\"},{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"}],\"placeholder\":\"请输入参数名\",\"validatorProperties\":{\"Required.required\":true,\"Length.min\":0,\"Length.max\":16},\"optionMap\":{}},{\"name\":\"paramValue\",\"label\":\"参数值\",\"cpnType\":\"NUMBER_TEXT\",\"validators\":[{\"min\":0,\"max\":16,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 16 个字符\"},{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"}],\"placeholder\":\"请输入参数值\",\"validatorProperties\":{\"Required.required\":true,\"Length.min\":0,\"Length.max\":16},\"optionMap\":{}},{\"name\":\"paramRemark\",\"label\":\"备注\",\"cpnType\":\"TEXT\",\"validators\":[],\"placeholder\":\"请输入备注\",\"validatorProperties\":{},\"optionMap\":{}}]}',1,'2024-09-12 09:23:10',1,'2024-09-12 09:23:10',b'0'),
	(866316416230400000,'code','型号','TEXT','[{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"},{\"regex\":\"^[0-9a-zA-Z_\\\\/%\\\\-]{1,}$\",\"message\":\"CODE只能包含数字、字母、下划线、中划线\",\"validatorType\":\"REGEX\"},{\"min\":0,\"max\":16,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 16 个字符\"}]','[]',NULL,NULL,'请输入型号',NULL,NULL,NULL,1,'2024-09-14 12:05:06',1,'2024-09-14 12:05:06',b'0'),
	(866316416251371520,'name','品名','TEXT','[{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"},{\"min\":0,\"max\":256,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 256 个字符\"}]','[]',NULL,NULL,'请输入品名',NULL,NULL,NULL,1,'2024-09-14 12:05:06',1,'2024-09-14 12:05:06',b'0'),
	(866316416251371521,'category','分类','SELECT','[{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"}]','[]','sys_dict_category',NULL,'请选择分类',NULL,NULL,NULL,1,'2024-09-14 12:05:06',1,'2024-09-14 12:05:06',b'0'),
	(866316416251371522,'supplier','供应商','SEARCH_SELECT','[{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"}]','[]','sys_dict_supplier',NULL,'请选择供应商',NULL,NULL,NULL,1,'2024-09-14 12:05:06',1,'2024-09-14 12:05:06',b'0'),
	(866316416251371523,'rmbPrice','人民币含税','NUMBER_TEXT','[]','[]',NULL,NULL,'请输入人民币含税价格',NULL,NULL,NULL,1,'2024-09-14 12:05:06',1,'2024-09-14 12:05:06',b'0'),
	(866316416251371524,'usdPrice','美金未税','NUMBER_TEXT','[]','[]',NULL,NULL,'请输入美金未税价格',NULL,NULL,NULL,1,'2024-09-14 12:05:06',1,'2024-09-14 12:05:06',b'0'),
	(866316416255565824,'remark','备注','TEXTAREA','[]','[]',NULL,NULL,'请输入备注',NULL,NULL,NULL,1,'2024-09-14 12:05:06',1,'2024-09-14 12:05:06',b'0'),
	(866316416255565825,'description','描述','TEXTAREA','[]','[]',NULL,NULL,'描述',NULL,NULL,NULL,1,'2024-09-14 12:05:06',1,'2024-09-14 12:05:06',b'0'),
	(866316416255565826,'accessoryList','配件','TABLE','[]','[]',NULL,NULL,'请输入配件',NULL,NULL,'{\"columns\":[{\"name\":\"accessoryName\",\"label\":\"配件名\",\"cpnType\":\"TEXT\",\"validators\":[{\"min\":0,\"max\":16,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 16 个字符\"},{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"}],\"placeholder\":\"请输入配件名\",\"optionMap\":{},\"validatorProperties\":{\"Required.required\":true,\"Length.min\":0,\"Length.max\":16}},{\"name\":\"accessoryAmount\",\"label\":\"配件单价\",\"cpnType\":\"NUMBER_TEXT\",\"validators\":[],\"placeholder\":\"请输入配件单价\",\"optionMap\":{},\"validatorProperties\":{}},{\"name\":\"avatarLabel\",\"label\":\"图片\",\"cpnType\":\"LABEL\",\"validators\":[],\"optionMap\":{},\"validatorProperties\":{}}]}',1,'2024-09-14 12:05:06',1,'2024-09-14 12:05:06',b'0'),
	(866316416255565827,'pictures','图片','FILE','[]','[]',NULL,NULL,NULL,NULL,NULL,NULL,1,'2024-09-14 12:05:06',1,'2024-09-14 12:05:06',b'0'),
	(866316416255565828,'usdPriceTemplate','USD报价单','FILE','[]','[]',NULL,NULL,NULL,NULL,NULL,NULL,1,'2024-09-14 12:05:06',1,'2024-09-14 12:05:06',b'0'),
	(866316416255565829,'rmbPriceTemplate','RMB报价单','FILE','[]','[]',NULL,NULL,NULL,NULL,NULL,NULL,1,'2024-09-14 12:05:06',1,'2024-09-14 12:05:06',b'0'),
	(866316416272343040,'certificate','认证','TEXT','[]','[]',NULL,NULL,'请输入认证',NULL,NULL,NULL,1,'2024-09-14 12:05:06',1,'2024-09-14 12:05:06',b'0'),
	(866316416276537344,'sellingPoint','产品卖点','TABLE','[]','[]',NULL,NULL,NULL,NULL,NULL,'{\"columns\":[{\"validators\":[],\"optionMap\":{},\"validatorProperties\":{}}]}',1,'2024-09-14 12:05:06',1,'2024-09-14 12:05:06',b'0'),
	(866316416276537345,'leadTime','交货时间','TABLE','[]','[]',NULL,NULL,NULL,NULL,NULL,'{\"columns\":[{\"validators\":[],\"optionMap\":{},\"validatorProperties\":{}}]}',1,'2024-09-14 12:05:06',1,'2024-09-14 12:05:06',b'0'),
	(866316416276537346,'packingInformation','包装信息','TABLE','[]','[]',NULL,NULL,NULL,NULL,NULL,'{\"columns\":[{\"validators\":[],\"optionMap\":{},\"validatorProperties\":{}},{\"validators\":[],\"optionMap\":{},\"validatorProperties\":{}}]}',1,'2024-09-14 12:05:06',1,'2024-09-14 12:05:06',b'0'),
	(866363897190326272,'code','编号','TEXT','[{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"},{\"regex\":\"^[0-9a-zA-Z_\\\\/%\\\\-]{1,}$\",\"message\":\"CODE只能包含数字、字母、下划线、中划线\",\"validatorType\":\"REGEX\"},{\"min\":0,\"max\":16,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 16 个字符\"}]','[]',NULL,NULL,'请输入编号',NULL,NULL,NULL,1,'2024-09-14 15:13:47',1,'2024-09-14 15:13:47',b'0'),
	(866363897211297792,'name','客户','TEXT','[{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"},{\"min\":0,\"max\":16,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 16 个字符\"}]','[]',NULL,NULL,'请输入供应商',NULL,NULL,NULL,1,'2024-09-14 15:13:47',1,'2024-09-14 15:13:47',b'0'),
	(866363897215492096,'address','地址','TEXT','[]','[]',NULL,NULL,'请输入地址',NULL,NULL,NULL,1,'2024-09-14 15:13:47',1,'2024-09-14 15:13:47',b'0'),
	(866363897215492097,'contactName','联系人','TEXT','[{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"},{\"min\":0,\"max\":16,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 16 个字符\"}]','[]',NULL,NULL,'请输入联系人',NULL,NULL,NULL,1,'2024-09-14 15:13:47',1,'2024-09-14 15:13:47',b'0'),
	(866363897215492098,'whatsApp','Whats App','TEXT','[{\"required\":true,\"validatorType\":\"REQUIRED\",\"message\":\"必填项需要填写\"},{\"min\":0,\"max\":16,\"validatorType\":\"LENGTH\",\"message\":\"长度范围 0 - 16 个字符\"}]','[]',NULL,NULL,'请输入Whats App',NULL,NULL,NULL,1,'2024-09-14 15:13:47',1,'2024-09-14 15:13:47',b'0'),
	(866363897215492099,'remark','备注','TEXTAREA','[]','[]',NULL,NULL,'请输入备注',NULL,NULL,NULL,1,'2024-09-14 15:13:47',1,'2024-09-14 15:13:47',b'0');

/*!40000 ALTER TABLE `sys_form_configurer` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_form_cpn_configurer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_form_cpn_configurer`;

CREATE TABLE `sys_form_cpn_configurer` (
  `id` bigint(20) NOT NULL,
  `form_id` bigint(20) NOT NULL,
  `config_id` bigint(20) NOT NULL,
  `order_num` int(11) DEFAULT NULL,
  `additional_info` text,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `sys_form_cpn_configurer` WRITE;
/*!40000 ALTER TABLE `sys_form_cpn_configurer` DISABLE KEYS */;

INSERT INTO `sys_form_cpn_configurer` (`id`, `form_id`, `config_id`, `order_num`, `additional_info`, `create_by`, `create_time`, `update_by`, `update_time`, `is_deleted`)
VALUES
	(862387839206199296,859875429241106432,862387838010822656,0,NULL,1,'2024-09-03 15:54:21',1,'2024-09-03 15:54:21',b'0'),
	(862387839210393600,859875429241106432,862387838044377088,1,NULL,1,'2024-09-03 15:54:21',1,'2024-09-03 15:54:21',b'0'),
	(862387839210393601,859875429241106432,862387838044377089,2,NULL,1,'2024-09-03 15:54:21',1,'2024-09-03 15:54:21',b'0'),
	(862387839210393602,859875429241106432,862387838044377090,3,NULL,1,'2024-09-03 15:54:21',1,'2024-09-03 15:54:21',b'0'),
	(862387839210393603,859875429241106432,862387838044377091,4,NULL,1,'2024-09-03 15:54:21',1,'2024-09-03 15:54:21',b'0'),
	(862701089336627200,694980924206493696,862701088350965760,0,NULL,1,'2024-09-04 12:39:06',1,'2024-09-04 12:39:06',b'0'),
	(862701089336627201,694980924206493696,862701088376131584,1,NULL,1,'2024-09-04 12:39:06',1,'2024-09-04 12:39:06',b'0'),
	(862701089336627202,694980924206493696,862701088376131585,2,NULL,1,'2024-09-04 12:39:06',1,'2024-09-04 12:39:06',b'0'),
	(862701089336627203,694980924206493696,862701088376131586,3,NULL,1,'2024-09-04 12:39:06',1,'2024-09-04 12:39:06',b'0'),
	(862701089336627204,694980924206493696,862701088376131587,4,NULL,1,'2024-09-04 12:39:06',1,'2024-09-04 12:39:06',b'0'),
	(862701089336627205,694980924206493696,862701088376131588,5,NULL,1,'2024-09-04 12:39:06',1,'2024-09-04 12:39:06',b'0'),
	(864251772804345856,864249380058771456,864251770119991296,0,NULL,1,'2024-09-08 19:20:57',1,'2024-09-08 19:20:57',b'0'),
	(864251772804345857,864249380058771456,864251770140962816,1,NULL,1,'2024-09-08 19:20:57',1,'2024-09-08 19:20:57',b'0'),
	(864251772804345858,864249380058771456,864251770140962817,2,NULL,1,'2024-09-08 19:20:57',1,'2024-09-08 19:20:57',b'0'),
	(864251772808540160,864249380058771456,864251770140962818,3,NULL,1,'2024-09-08 19:20:57',1,'2024-09-08 19:20:57',b'0'),
	(864263431119777792,695312747063197696,864263427256823808,0,NULL,1,'2024-09-08 20:07:17',1,'2024-09-08 20:07:17',b'0'),
	(864263431119777793,695312747063197696,864263427277795328,1,NULL,1,'2024-09-08 20:07:17',1,'2024-09-08 20:07:17',b'0'),
	(864263431119777794,695312747063197696,864263427277795329,2,NULL,1,'2024-09-08 20:07:17',1,'2024-09-08 20:07:17',b'0'),
	(864263431123972096,695312747063197696,864263427277795330,3,NULL,1,'2024-09-08 20:07:17',1,'2024-09-08 20:07:17',b'0'),
	(865550889639743488,864287608526680064,865550888133988352,0,NULL,1,'2024-09-12 09:23:11',1,'2024-09-12 09:23:11',b'0'),
	(866316422752542720,864275104153022464,866316416230400000,0,NULL,1,'2024-09-14 12:05:08',1,'2024-09-14 12:05:08',b'0'),
	(866316422752542721,864275104153022464,866316416251371520,1,NULL,1,'2024-09-14 12:05:08',1,'2024-09-14 12:05:08',b'0'),
	(866316422752542722,864275104153022464,866316416251371521,2,NULL,1,'2024-09-14 12:05:08',1,'2024-09-14 12:05:08',b'0'),
	(866316422752542723,864275104153022464,866316416251371522,3,NULL,1,'2024-09-14 12:05:08',1,'2024-09-14 12:05:08',b'0'),
	(866316422752542724,864275104153022464,866316416251371523,4,NULL,1,'2024-09-14 12:05:08',1,'2024-09-14 12:05:08',b'0'),
	(866316422752542725,864275104153022464,866316416251371524,5,NULL,1,'2024-09-14 12:05:08',1,'2024-09-14 12:05:08',b'0'),
	(866316422752542726,864275104153022464,866316416255565824,6,NULL,1,'2024-09-14 12:05:08',1,'2024-09-14 12:05:08',b'0'),
	(866316422752542727,864275104153022464,866316416255565825,7,NULL,1,'2024-09-14 12:05:08',1,'2024-09-14 12:05:08',b'0'),
	(866316422752542728,864275104153022464,866316416255565826,8,NULL,1,'2024-09-14 12:05:08',1,'2024-09-14 12:05:08',b'0'),
	(866316422752542729,864275104153022464,866316416255565827,9,NULL,1,'2024-09-14 12:05:08',1,'2024-09-14 12:05:08',b'0'),
	(866316422765125632,864275104153022464,866316416255565828,10,NULL,1,'2024-09-14 12:05:08',1,'2024-09-14 12:05:08',b'0'),
	(866316422765125633,864275104153022464,866316416255565829,11,NULL,1,'2024-09-14 12:05:08',1,'2024-09-14 12:05:08',b'0'),
	(866316422765125634,864275104153022464,866316416272343040,12,NULL,1,'2024-09-14 12:05:08',1,'2024-09-14 12:05:08',b'0'),
	(866316422765125635,864275104153022464,866316416276537344,13,NULL,1,'2024-09-14 12:05:08',1,'2024-09-14 12:05:08',b'0'),
	(866316422765125636,864275104153022464,866316416276537345,14,NULL,1,'2024-09-14 12:05:08',1,'2024-09-14 12:05:08',b'0'),
	(866316422765125637,864275104153022464,866316416276537346,15,NULL,1,'2024-09-14 12:05:08',1,'2024-09-14 12:05:08',b'0'),
	(866363900063424512,866354416721108992,866363897190326272,0,NULL,1,'2024-09-14 15:13:47',1,'2024-09-14 15:13:47',b'0'),
	(866363900063424513,866354416721108992,866363897211297792,1,NULL,1,'2024-09-14 15:13:47',1,'2024-09-14 15:13:47',b'0'),
	(866363900063424514,866354416721108992,866363897215492096,2,NULL,1,'2024-09-14 15:13:47',1,'2024-09-14 15:13:47',b'0'),
	(866363900063424515,866354416721108992,866363897215492097,3,NULL,1,'2024-09-14 15:13:47',1,'2024-09-14 15:13:47',b'0'),
	(866363900063424516,866354416721108992,866363897215492098,4,NULL,1,'2024-09-14 15:13:47',1,'2024-09-14 15:13:47',b'0'),
	(866363900063424517,866354416721108992,866363897215492099,5,NULL,1,'2024-09-14 15:13:47',1,'2024-09-14 15:13:47',b'0');

/*!40000 ALTER TABLE `sys_form_cpn_configurer` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_form_cpn_value
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_form_cpn_value`;

CREATE TABLE `sys_form_cpn_value` (
  `id` bigint(20) NOT NULL,
  `value` text,
  `form_cpn_id` bigint(20) NOT NULL,
  `form_id` bigint(20) NOT NULL,
  `config_id` bigint(20) NOT NULL,
  `instance_id` bigint(20) NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `sys_form_cpn_value` WRITE;
/*!40000 ALTER TABLE `sys_form_cpn_value` DISABLE KEYS */;

INSERT INTO `sys_form_cpn_value` (`id`, `value`, `form_cpn_id`, `form_id`, `config_id`, `instance_id`, `create_by`, `create_time`, `update_by`, `update_time`, `is_deleted`)
VALUES
	(866030232186953728,'[[\"2\",\"2\",\"3\"],[\"3\",\"3\",\"\"]]',865550889639743488,864287608526680064,865550888133988352,866020806457597952,1,'2024-09-13 17:07:55',1,'2024-09-13 17:07:55',b'0'),
	(866030271592439808,'[[\"23\",\"3223\",\"23\"],[\"23\",\"23\",\"\"]]',865550889639743488,864287608526680064,865550888133988352,866008500201889792,1,'2024-09-13 17:08:04',1,'2024-09-13 17:08:04',b'0'),
	(866030590615396352,'[]',865550889639743488,864287608526680064,865550888133988352,866030590607007744,1,'2024-09-13 17:09:20',1,'2024-09-13 17:09:20',b'0'),
	(866032023599357952,'[]',865550889639743488,864287608526680064,865550888133988352,866032023586775040,1,'2024-09-13 17:15:02',1,'2024-09-13 17:15:02',b'0'),
	(866347413391056896,'[]',865550889639743488,864287608526680064,865550888133988352,866347412870963200,1,'2024-09-14 14:08:17',1,'2024-09-14 14:08:17',b'0'),
	(866376341904203776,'[[\" Power supply\",\"22.2V @6 Cells 2200mAh 180W\",\"\"],[\"Motor type\",\"Brushless Motor\",\"\"],[\"Suction power\",\"8.5-11KPA\",\"\"],[\"Noise\",\"<75dBA\",\"\"],[\"Charging time\",\"3H\",\"\"],[\"Working time\",\"17-25min\",\"\"],[\"Roller RPM\",\"600\",\"\"],[\"Clean water tank capacity\",\"600ml\",\"\"],[\"Dirty water tank capacity: 500ml\",\"500ml\",\"\"],[\"Filtration system\",\"HEPA\",\"\"],[\"Waterproofing grade\",\"IPX4\",\"\"],[\"Net/Gross weight\",\"4kg/6kg\",\"\"],[\"\",\"LED display\",\"\"],[\"\",\"Power forward booster\",\"\"],[\"\",\"Voice assistance\",\"\"],[\"\",\"Self-cleaning\",\"\"],[\"\",\"Dual detachable battery pack\",\"\"],[\"\",\"Water electrolysis function\",\"\"],[\"\",\"Hot air Drying function\",\"\"]]',865550889639743488,864287608526680064,865550888133988352,865315687751573504,1,'2024-09-14 16:03:14',1,'2024-09-14 16:03:14',b'0'),
	(866385646795661312,'[[\"22\",\"222\",\"222\"],[\"22\",\"2\",\"\"]]',865550889639743488,864287608526680064,865550888133988352,866311877666316288,1,'2024-09-14 16:40:12',1,'2024-09-14 16:40:12',b'0'),
	(866385730673352704,'[[\"1\",\"2\",\"\"]]',865550889639743488,864287608526680064,865550888133988352,866082989816156160,1,'2024-09-14 16:40:32',1,'2024-09-14 16:40:32',b'0'),
	(866385770385022976,'[]',865550889639743488,864287608526680064,865550888133988352,866018919041769472,1,'2024-09-14 16:40:42',1,'2024-09-14 16:40:42',b'0'),
	(866387682639519744,'[[\"Voltage\",\"25.2V\",\"\"],[\"Battery volume\",\"2500mAh\",\"\"],[\" Power\",\"200W\",\"\"],[\"Battery SPEC\",\"BP2926 18650(8cells)\",\"\"],[\"Suction motor\",\"Carbon Brush Motor\",\"\"],[\"Suction power\",\"8000Pa\",\"\"],[\" Suction motor working setting\",\"Standard\",\"\"],[\"Working duration\",\">32min\",\"\"],[\"Roller brush speed\",\"2500rpm with Hi-level/2000rpm with low level\",\"\"],[\"Charger time\",\"<5H\",\"\"],[\"Noise\",\"<72dBA\",\"\"],[\"Floor Brush width\",\"25cm\",\"\"],[\"Roller cleaning width\",\"22.5cm\",\"\"],[\"Motor Fan Diameter\",\"85 mm\",\"\"],[\"Clean water tank volume\",\"600ML\",\"\"],[\"Dirty water tank volume\",\"550ML\",\"\"],[\"Voice guide\",\"Available\",\"\"],[\"Self-clean for roller brush\",\"Available\",\"\"],[\"Drying for roller brush\",\"Available\",\"\"]]',865550889639743488,864287608526680064,865550888133988352,865939679629844480,1,'2024-09-14 16:48:18',1,'2024-09-14 16:48:18',b'0'),
	(866394623625437184,'[[\"参数\",\"1\",\"\"],[\"参数\",\"2\",\"\"]]',865550889639743488,864287608526680064,865550888133988352,866290808335147008,1,'2024-09-14 17:15:52',1,'2024-09-14 17:15:52',b'0');

/*!40000 ALTER TABLE `sys_form_cpn_value` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_permission
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `code` varchar(32) NOT NULL COMMENT '外部可见，唯一code',
  `name` varchar(32) DEFAULT NULL COMMENT '权限名称',
  `pid` bigint(20) DEFAULT NULL COMMENT '父权限',
  `permission_order` int(11) DEFAULT NULL COMMENT 'permission_order',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` bit(1) DEFAULT NULL COMMENT '是否逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

LOCK TABLES `sys_permission` WRITE;
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;

INSERT INTO `sys_permission` (`id`, `code`, `name`, `pid`, `permission_order`, `create_by`, `create_time`, `update_by`, `update_time`, `is_deleted`)
VALUES
	(694587393189089280,'dashboard','仪表盘',0,0,NULL,'2023-05-29 14:55:17',0,'2023-05-29 16:30:36',b'0'),
	(694587393189089281,'sys_management','系统管理',0,9,0,'2023-05-29 14:55:17',0,'2023-05-29 14:55:17',b'0'),
	(694587393189089283,'role_management','角色管理',694587393189089281,1,NULL,'2023-05-29 14:55:17',NULL,'2023-05-29 14:55:17',b'0'),
	(696145672415481856,'sys_user','用户管理',694587393189089281,0,1,'2023-06-02 22:07:20',1,'2023-06-02 22:07:20',b'0'),
	(696145672960741376,'sys_user_read','查看',696145672415481856,0,1,'2023-06-02 22:07:20',1,'2023-06-02 22:07:20',b'0'),
	(696145673485029376,'sys_user_add','新增',696145672415481856,1,1,'2023-06-02 22:07:20',1,'2023-06-02 22:07:20',b'0'),
	(696145674072231936,'sys_user_edit','编辑',696145672415481856,2,1,'2023-06-02 22:07:20',1,'2023-06-02 22:07:20',b'0'),
	(696145674634268672,'sys_user_delete','删除',696145672415481856,3,1,'2023-06-02 22:07:20',1,'2023-06-02 22:07:20',b'0'),
	(696146982695079936,'sys_dict','字典管理',694587393189089281,2,1,'2023-06-02 22:12:32',1,'2023-06-02 22:12:32',b'0'),
	(696146983320031232,'sys_dict_read','查看',696146982695079936,0,1,'2023-06-02 22:12:32',1,'2023-06-02 22:12:32',b'0'),
	(696146983924011008,'sys_dict_add','新增',696146982695079936,1,1,'2023-06-02 22:12:33',1,'2023-06-02 22:12:33',b'0'),
	(696146984569933824,'sys_dict_edit','编辑',696146982695079936,2,1,'2023-06-02 22:12:33',1,'2023-06-02 22:12:33',b'0'),
	(696146985190690816,'sys_dict_delete','删除',696146982695079936,3,1,'2023-06-02 22:12:33',1,'2023-06-02 22:12:33',b'0'),
	(866065023858941952,'manager','管理',NULL,0,1,'2024-09-13 19:26:10',1,'2024-09-13 19:26:10',b'0'),
	(866065223239376896,'t_supplier','供应商管理',866065023858941952,1,1,'2024-09-13 19:26:57',1,'2024-09-13 19:26:57',b'0'),
	(866065223793025024,'t_supplier_read','查看',866065223239376896,0,1,'2024-09-13 19:26:57',1,'2024-09-13 19:26:57',b'0'),
	(866065224292147200,'t_supplier_add','新增',866065223239376896,1,1,'2024-09-13 19:26:57',1,'2024-09-13 19:26:57',b'0'),
	(866065224799657984,'t_supplier_edit','编辑',866065223239376896,2,1,'2024-09-13 19:26:58',1,'2024-09-13 19:26:58',b'0'),
	(866065225328140288,'t_supplier_delete','删除',866065223239376896,3,1,'2024-09-13 19:26:58',1,'2024-09-13 19:26:58',b'0'),
	(866357163667267584,'t_customer','客户管理',866065023858941952,2,1,'2024-09-14 14:47:01',1,'2024-09-14 14:47:01',b'0'),
	(866357164128641024,'t_customer_read','查看',866357163667267584,0,1,'2024-09-14 14:47:01',1,'2024-09-14 14:47:01',b'0'),
	(866357164552265728,'t_customer_add','新增',866357163667267584,1,1,'2024-09-14 14:47:01',1,'2024-09-14 14:47:01',b'0'),
	(866357164967501824,'t_customer_edit','编辑',866357163667267584,2,1,'2024-09-14 14:47:02',1,'2024-09-14 14:47:02',b'0'),
	(866357165370155008,'t_customer_delete','删除',866357163667267584,3,1,'2024-09-14 14:47:02',1,'2024-09-14 14:47:02',b'0');

/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_property
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_property`;

CREATE TABLE `sys_property` (
  `name` varchar(32) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table sys_report
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_report`;

CREATE TABLE `sys_report` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `code` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `tpl_name` varchar(32) DEFAULT NULL,
  `pageable` bit(1) DEFAULT NULL,
  `sidx` varchar(32) DEFAULT NULL,
  `sord` varchar(16) DEFAULT NULL,
  `query_sql` text,
  `summary` bit(1) DEFAULT b'0',
  `report_column_list` text,
  `summary_column_names` text,
  `query_field_list` text,
  `additional_info` text,
  `report_advice_name` varchar(64) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='报表';

LOCK TABLES `sys_report` WRITE;
/*!40000 ALTER TABLE `sys_report` DISABLE KEYS */;

INSERT INTO `sys_report` (`id`, `code`, `name`, `tpl_name`, `pageable`, `sidx`, `sord`, `query_sql`, `summary`, `report_column_list`, `summary_column_names`, `query_field_list`, `additional_info`, `report_advice_name`, `create_by`, `create_time`, `update_by`, `update_time`, `is_deleted`)
VALUES
	(694714180413960192,'sys_user','用户管理','tpl/list/list',b'1','id','ASC',' SELECT sys_user.id, sys_user.code, sys_user.name, sys_user.birthday, IF(sys_user.is_available, \'是\', \'否\') is_available, t.name role_name, u.name create_name, DATE_FORMAT(sys_user.create_time, \'%Y-%m-%d %H:%i:%s\') create_time FROM sys_user\n LEFT JOIN sys_user u on u.id = sys_user.create_by\n LEFT JOIN ( SELECT sys_user.id, GROUP_CONCAT(r.name) name FROM sys_user\n LEFT JOIN sys_user_role ur on sys_user.id = ur.user_id AND ur.is_deleted = 0\n LEFT JOIN sys_role r on r.id = ur.role_id AND r.is_deleted = 0\n group by sys_user.id order by sys_user.id asc) t on t.id = sys_user.id\nWHERE sys_user.code LIKE :code AND sys_user.name LIKE :name AND sys_user.is_available = :is_available AND sys_user.create_time >= :create_time0 AND sys_user.create_time <= :create_time1 AND sys_user.is_deleted = 0',b'0','[{\"name\":\"id\",\"sortable\":false,\"hidden\":true,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"code\",\"label\":\"用户名\",\"sortable\":true,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"name\",\"label\":\"姓名\",\"sortable\":true,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"birthday\",\"label\":\"出生日期\",\"sortable\":true,\"hidden\":false,\"align\":\"center\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"role_name\",\"label\":\"角色\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"is_available\",\"label\":\"是否可用\",\"sortable\":false,\"columnWidth\":80,\"hidden\":false,\"align\":\"center\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"create_name\",\"label\":\"创建人\",\"sortable\":false,\"columnWidth\":100,\"hidden\":false,\"align\":\"center\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"create_time\",\"label\":\"创建时间\",\"sortable\":false,\"columnWidth\":120,\"hidden\":false,\"align\":\"center\",\"tooltip\":false,\"type\":\"text\"}]',NULL,'[{\"name\":\"code\",\"label\":\"用户名\",\"type\":\"TEXT\"},{\"name\":\"name\",\"label\":\"姓名\",\"type\":\"TEXT\"},{\"name\":\"is_available\",\"label\":\"是否可用\",\"type\":\"SELECT\",\"extraData\":\"bol\"},{\"name\":\"create_time\",\"label\":\"创建时间\",\"type\":\"DATE_RANGE\"}]','{\"formId\":\"694980924206493696\",\"formAction\":\"link\"}','userReportAdvice',0,'2023-05-29 23:19:06',1,'2024-09-05 06:09:46',b'0'),
	(695316160014499840,'sys_dict','字典管理','tpl/list/list',b'0','id','ASC','select id, type, name, label, sort from sys_dict where type = :type order by type, sort asc',b'0','[{\"name\":\"id\",\"sortable\":false,\"hidden\":true,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"name\",\"label\":\"编码\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"label\",\"label\":\"显示值\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"type\",\"label\":\"分类\",\"sortable\":true,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"sort\",\"label\":\"排序号\",\"sortable\":false,\"columnWidth\":30,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"}]',NULL,'[{\"name\":\"type\",\"label\":\"分类\",\"type\":\"SELECT\",\"extraData\":\"sys_dict_type\"}]','{\"formId\":\"695312747063197696\"}',NULL,1,'2023-05-31 15:11:09',1,'2024-08-24 14:19:25',b'0'),
	(786015805669142528,'sys_user_search','用户查询','tpl/list/dialog_report_list',b'1','id','ASC',' SELECT sys_user.id, sys_user.code, sys_user.name, IF(sys_user.is_available, \'是\', \'否\') is_available, t.name role_name, u.name create_name, DATE_FORMAT(sys_user.create_time, \'%Y-%m-%d %H:%i:%s\') create_time FROM sys_user\n LEFT JOIN sys_user u on u.id = sys_user.create_by\n LEFT JOIN ( SELECT sys_user.id, GROUP_CONCAT(r.name) name FROM sys_user\n LEFT JOIN sys_user_role ur on sys_user.id = ur.user_id AND ur.is_deleted = 0\n LEFT JOIN sys_role r on r.id = ur.role_id AND r.is_deleted = 0\n group by sys_user.id order by sys_user.id asc) t on t.id = sys_user.id\nWHERE sys_user.code LIKE :code AND sys_user.name LIKE :name AND sys_user.is_available = :is_available AND sys_user.create_time >= :create_time0 AND sys_user.create_time <= :create_time1 AND sys_user.is_deleted = 0 and sys_user.id = :id and sys_user.id IN (:ids)',b'0','[{\"name\":\"id\",\"sortable\":false,\"hidden\":true,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"code\",\"label\":\"用户名\",\"sortable\":true,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"name\",\"label\":\"姓名\",\"sortable\":true,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"role_name\",\"label\":\"角色\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"is_available\",\"label\":\"是否可用\",\"sortable\":false,\"columnWidth\":80,\"hidden\":false,\"align\":\"center\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"create_name\",\"label\":\"创建人\",\"sortable\":false,\"columnWidth\":100,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"create_time\",\"label\":\"创建时间\",\"sortable\":false,\"columnWidth\":120,\"hidden\":false,\"align\":\"center\",\"tooltip\":false,\"type\":\"text\"}]',NULL,'[{\"name\":\"code\",\"label\":\"用户名\",\"type\":\"TEXT\"},{\"name\":\"name\",\"label\":\"姓名\",\"type\":\"TEXT\"},{\"name\":\"is_available\",\"label\":\"是否可用\",\"type\":\"SELECT\",\"extraData\":\"bol\"},{\"name\":\"create_time\",\"label\":\"创建时间\",\"type\":\"DATE_RANGE\"}]',NULL,NULL,1,'2024-04-06 21:32:07',1,'2024-09-05 06:10:25',b'0'),
	(858372486293622784,'sys_document','图片管理','modules/link/list',b'1','create_time','DESC','select id, name, concat(\'https://dc-product-manager.oss-cn-shanghai.aliyuncs.com/\', group_name, \'/\', path) url, extension, content_type, ROUND(size / (1024 * 1024), 1) size, create_time, \'\' copy from sys_document where name like :name and group_name = \'link\'',b'0','[{\"name\":\"id\",\"sortable\":false,\"hidden\":true,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"isImageType\",\"sortable\":false,\"hidden\":true,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"url\",\"label\":\"文件\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"name\",\"label\":\"名称\",\"sortable\":true,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"content_type\",\"label\":\"类型\",\"sortable\":true,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"size\",\"label\":\"大小（M）\",\"sortable\":true,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"create_time\",\"label\":\"创建时间\",\"valueConverterNameList\":[\"localDateTimeConverter\"],\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"copy\",\"label\":\"复制\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"}]',NULL,'[{\"name\":\"name\",\"label\":\"名称\",\"type\":\"TEXT\"}]',NULL,'linkReportAdvice',1,'2024-08-23 13:58:46',1,'2024-09-13 15:34:36',b'0'),
	(859875793323470848,'sys_user_form_tag','用户管理','tpl/list/list',b'1','id','ASC',' SELECT sys_user.id, sys_user.code, sys_user.name, sys_user.birthday, IF(sys_user.is_available, \'是\', \'否\') is_available, t.name role_name, u.name create_name, DATE_FORMAT(sys_user.create_time, \'%Y-%m-%d %H:%i:%s\') create_time FROM sys_user\n LEFT JOIN sys_user u on u.id = sys_user.create_by\n LEFT JOIN ( SELECT sys_user.id, GROUP_CONCAT(r.name) name FROM sys_user\n LEFT JOIN sys_user_role ur on sys_user.id = ur.user_id AND ur.is_deleted = 0\n LEFT JOIN sys_role r on r.id = ur.role_id AND r.is_deleted = 0\n group by sys_user.id order by sys_user.id asc) t on t.id = sys_user.id\nWHERE sys_user.code LIKE :code AND sys_user.name LIKE :name AND sys_user.is_available = :is_available AND sys_user.create_time >= :create_time0 AND sys_user.create_time <= :create_time1 AND sys_user.is_deleted = 0',b'0','[{\"name\":\"id\",\"sortable\":false,\"hidden\":true,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"code\",\"label\":\"用户名\",\"sortable\":true,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"name\",\"label\":\"姓名\",\"sortable\":true,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"birthday\",\"label\":\"出生日期\",\"sortable\":true,\"hidden\":false,\"align\":\"center\",\"tooltip\":false,\"type\":\"date\"},{\"name\":\"role_name\",\"label\":\"角色\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"is_available\",\"label\":\"是否可用\",\"sortable\":false,\"columnWidth\":80,\"hidden\":false,\"align\":\"center\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"create_name\",\"label\":\"创建人\",\"sortable\":false,\"columnWidth\":100,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"create_time\",\"label\":\"创建时间\",\"sortable\":false,\"columnWidth\":120,\"hidden\":false,\"align\":\"center\",\"tooltip\":false,\"type\":\"text\"}]',NULL,'[{\"name\":\"code\",\"label\":\"用户名\",\"type\":\"TEXT\"},{\"name\":\"name\",\"label\":\"姓名\",\"type\":\"TEXT\"},{\"name\":\"is_available\",\"label\":\"是否可用\",\"type\":\"SELECT\",\"extraData\":\"bol\"},{\"name\":\"create_time\",\"label\":\"创建时间\",\"type\":\"DATE_RANGE\"}]','{\"formId\":\"859875429241106432\",\"operator-bar\":true,\"formAction\":\"link\"}','userReportAdvice',1,'2024-08-27 17:32:23',1,'2024-09-05 07:24:58',b'0'),
	(864247895312904192,'t_supplier','供应商','tpl/list/ajax_list',b'1','createTime','DESC','SELECT t_supplier.name AS \"name\",t_supplier.contact_name AS \"contactName\",t_supplier.contact_phone AS \"contactPhone\",t_supplier.code AS \"code\",t_supplier.create_by AS \"createBy\",t_supplier.create_time AS \"createTime\",t_supplier.update_by AS \"updateBy\",t_supplier.update_time AS \"updateTime\",t_supplier.is_deleted AS \"deleted\",t_supplier.id AS \"id\" FROM t_supplier WHERE name = :name AND contact_name LIKE :contact_name AND contact_name LIKE :contactName AND contact_phone = :contact_phone AND contact_phone = :contactPhone AND code LIKE :code AND create_by = :create_by AND create_by = :createBy AND create_time = :create_time AND create_time = :createTime AND update_by = :update_by AND update_by = :updateBy AND update_time = :update_time AND update_time = :updateTime AND is_deleted = 0 AND is_deleted = :deleted AND id = :id',b'0','[{\"name\":\"id\",\"sortable\":false,\"hidden\":true,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"code\",\"label\":\"编号\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"name\",\"label\":\"供应商\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"contactName\",\"label\":\"联系人\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"contactPhone\",\"label\":\"联系电话\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"createBy\",\"label\":\"创建人\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"createTime\",\"label\":\"创建时间\",\"valueConverterNameList\":[\"localDateTimeConverter\"],\"sortable\":false,\"hidden\":false,\"align\":\"center\",\"tooltip\":false,\"type\":\"datetime\"},{\"name\":\"updateBy\",\"label\":\"更新人\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"updateTime\",\"label\":\"更新时间\",\"valueConverterNameList\":[\"localDateTimeConverter\"],\"sortable\":false,\"hidden\":false,\"align\":\"center\",\"tooltip\":false,\"type\":\"datetime\"}]',NULL,'[{\"name\":\"code\",\"label\":\"编号\",\"type\":\"TEXT\"},{\"name\":\"id\",\"label\":\"供应商\",\"type\":\"SEARCH_SELECT\",\"extraData\":\"sys_dict_supplier\"},{\"name\":\"contact_name\",\"label\":\"联系人\",\"type\":\"TEXT\"}]','{\"formId\":\"864249380058771456\",\"operator-bar\":true}','operatorReportAdvice',1,'2024-09-08 19:05:33',1,'2024-09-14 08:54:50',b'0'),
	(864272287958904832,'t_product','产品','tpl/list/list',b'1','createTime','DESC','SELECT t_product.name AS \"name\", t_product.category_code AS \"categoryCode\", t_product.supplier_id supplierId, t_product.picture AS \"pictures\",t_product.remark AS \"remark\",t_product.instance_id AS \"instanceId\",t_product.attr_instance_id AS \"attrInstanceId\",t_product.code AS \"code\",t_product.create_by AS \"createBy\",t_product.create_time AS \"createTime\",t_product.update_by AS \"updateBy\",t_product.update_time AS \"updateTime\",t_product.is_deleted AS \"deleted\",t_product.id AS \"id\" FROM t_product WHERE name LIKE :name AND picture = :picture AND picture = :pictures AND remark = :remark AND instance_id = :instance_id AND instance_id = :instanceId AND supplier_id = :supplierId AND category_code = :categoryCode AND code LIKE :code AND create_by = :create_by AND create_by = :createBy AND create_time = :create_time AND create_time = :createTime AND update_by = :update_by AND update_by = :updateBy AND update_time = :update_time AND update_time = :updateTime AND is_deleted = 0 AND is_deleted = :deleted AND id = :id',b'0','[{\"name\":\"id\",\"sortable\":false,\"hidden\":true,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"code\",\"label\":\"型号\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"avatar\",\"label\":\"图片\",\"sortable\":false,\"hidden\":false,\"align\":\"center\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"name\",\"label\":\"品名\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"categoryCode\",\"label\":\"分类\",\"valueConverterNameList\":[\"dictConverter\"],\"context\":\"sys_dict_category\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"supplierId\",\"label\":\"供应商\",\"valueConverterNameList\":[\"dictConverter\"],\"context\":\"sys_dict_supplier\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"createBy\",\"label\":\"创建人\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"createTime\",\"label\":\"创建时间\",\"valueConverterNameList\":[\"localDateTimeConverter\"],\"sortable\":false,\"columnWidth\":120,\"hidden\":false,\"align\":\"center\",\"tooltip\":false,\"type\":\"datetime\"},{\"name\":\"updateBy\",\"label\":\"更新人\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"updateTime\",\"label\":\"更新时间\",\"valueConverterNameList\":[\"localDateTimeConverter\"],\"sortable\":false,\"columnWidth\":120,\"hidden\":false,\"align\":\"center\",\"tooltip\":false,\"type\":\"datetime\"}]',NULL,'[{\"name\":\"code\",\"label\":\"型号\",\"type\":\"TEXT\"},{\"name\":\"name\",\"label\":\"品名\",\"type\":\"TEXT\"},{\"name\":\"categoryCode\",\"label\":\"分类\",\"type\":\"SELECT\",\"extraData\":\"sys_dict_category\"},{\"name\":\"supplierId\",\"label\":\"供应商\",\"type\":\"SEARCH_SELECT\",\"extraData\":\"sys_dict_supplier\"}]','{\"formId\":\"864275104153022464\",\"operator-bar\":true,\"formAction\":\"link\"}','productReportAdvice',1,'2024-09-08 20:42:28',1,'2024-09-14 13:16:54',b'0'),
	(866352375902806016,'t_customer','客户','tpl/list/list',b'1','createTime','DESC','SELECT t_customer.name AS \"name\",t_customer.contact_name AS \"contactName\",t_customer.whats_app AS \"whatsApp\",t_customer.remark AS \"remark\",t_customer.code AS \"code\",t_customer.create_by AS \"createBy\",t_customer.create_time AS \"createTime\",t_customer.update_by AS \"updateBy\",t_customer.update_time AS \"updateTime\",t_customer.is_deleted AS \"deleted\",t_customer.id AS \"id\" FROM t_customer WHERE name LIKE :name AND contact_name LIKE :contact_name AND contact_name LIKE :contactName AND whatsapp = :whatsapp AND whatsapp = :whatsApp AND remark = :remark AND code LIKE :code AND create_by = :create_by AND create_by = :createBy AND create_time = :create_time AND create_time = :createTime AND update_by = :update_by AND update_by = :updateBy AND update_time = :update_time AND update_time = :updateTime AND is_deleted = 0 AND is_deleted = :deleted AND id = :id',b'0','[{\"name\":\"id\",\"sortable\":false,\"hidden\":true,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"code\",\"label\":\"编号\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"name\",\"label\":\"客户\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"contactName\",\"label\":\"联系人\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"whatsApp\",\"label\":\"联系电话\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"remark\",\"label\":\"备注\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"createBy\",\"label\":\"创建人\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"createTime\",\"label\":\"创建时间\",\"valueConverterNameList\":[\"localDateTimeConverter\"],\"sortable\":false,\"columnWidth\":120,\"hidden\":false,\"align\":\"center\",\"tooltip\":false,\"type\":\"datetime\"},{\"name\":\"updateBy\",\"label\":\"更新人\",\"sortable\":false,\"hidden\":false,\"align\":\"left\",\"tooltip\":false,\"type\":\"text\"},{\"name\":\"updateTime\",\"label\":\"更新时间\",\"valueConverterNameList\":[\"localDateTimeConverter\"],\"sortable\":false,\"columnWidth\":120,\"hidden\":false,\"align\":\"center\",\"tooltip\":false,\"type\":\"datetime\"}]',NULL,'[{\"name\":\"code\",\"label\":\"编号\",\"type\":\"TEXT\"},{\"name\":\"id\",\"label\":\"客户\",\"type\":\"SEARCH_SELECT\",\"extraData\":\"sys_dict_customer\"},{\"name\":\"contact_name\",\"label\":\"联系人\",\"type\":\"TEXT\"}]','{\"formId\":\"866354416721108992\",\"operator-bar\":true}','operatorReportAdvice',1,'2024-09-14 14:28:00',1,'2024-09-14 15:07:42',b'0');

/*!40000 ALTER TABLE `sys_report` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `code` varchar(32) NOT NULL COMMENT '外部可见，唯一code',
  `name` varchar(32) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` bit(1) DEFAULT NULL COMMENT '是否逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;

INSERT INTO `sys_role` (`id`, `code`, `name`, `create_by`, `create_time`, `update_by`, `update_time`, `is_deleted`)
VALUES
	(694587732420202496,'admin','管理员',1,'2023-05-29 14:56:38',1,'2024-09-03 13:02:49',b'0'),
	(862344670758342656,'2GKFO','通用',1,'2024-09-03 13:02:49',1,'2024-09-03 13:02:49',b'0');

/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_role_permission
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  UNIQUE KEY `sys_role_permission_pk` (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sys_role_permission` WRITE;
/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;

INSERT INTO `sys_role_permission` (`role_id`, `permission_id`, `is_deleted`)
VALUES
	(694587732420202496,694587393189089280,b'0'),
	(694587732420202496,694587393189089281,b'0'),
	(694587732420202496,694587393189089283,b'0'),
	(694587732420202496,696145672415481856,b'0'),
	(694587732420202496,696145672960741376,b'0'),
	(694587732420202496,696145673485029376,b'0'),
	(694587732420202496,696145674072231936,b'0'),
	(694587732420202496,696145674634268672,b'0'),
	(694587732420202496,696146982695079936,b'0'),
	(694587732420202496,696146983320031232,b'0'),
	(694587732420202496,696146983924011008,b'0'),
	(694587732420202496,696146984569933824,b'0'),
	(694587732420202496,696146985190690816,b'0'),
	(694587732420202496,866065023858941952,b'0'),
	(694587732420202496,866065223239376896,b'0'),
	(694587732420202496,866065223793025024,b'0'),
	(694587732420202496,866065224292147200,b'0'),
	(694587732420202496,866065224799657984,b'0'),
	(694587732420202496,866065225328140288,b'0'),
	(694587732420202496,866357163667267584,b'0'),
	(694587732420202496,866357164128641024,b'0'),
	(694587732420202496,866357164552265728,b'0'),
	(694587732420202496,866357164967501824,b'0'),
	(694587732420202496,866357165370155008,b'0');

/*!40000 ALTER TABLE `sys_role_permission` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `code` varchar(32) NOT NULL COMMENT '外部可见，唯一code',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `is_available` bit(1) DEFAULT NULL COMMENT '是否可用',
  `attachment` text,
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` bit(1) DEFAULT NULL COMMENT '是否逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;

INSERT INTO `sys_user` (`id`, `code`, `name`, `birthday`, `password`, `is_available`, `attachment`, `create_by`, `create_time`, `update_by`, `update_time`, `is_deleted`)
VALUES
	(1,'admin','管理员','2024-09-03','$2a$10$03ELdomnPVX3GqBd9t3jPuF1eaxrwcLZlAJOg6P1nbZJs0oG4N5vS',b'1','[]',1,'2023-05-29 14:55:03',1,'2024-09-13 15:40:37',b'0'),
	(866052912567066624,'rick','徐徐','2024-09-08','$2a$10$E/hT2LkHAE1WehYuhouqje4w66kqh1beqG6WO1l33lzSJS7awXtg.',b'1','[]',1,'2024-09-13 18:38:02',1,'2024-09-13 20:30:21',b'0'),
	(866277718549897216,'TOM','汤姆','2024-09-02','$2a$10$Gkm6MfPKOh3z85EZFpzaSuL78.W/BAO0Lw/S.n/JNEt/3X0xRxkZ2',b'1','[]',1,'2024-09-14 09:31:20',1,'2024-09-14 09:31:20',b'0'),
	(866278926383620096,'JIM','吉姆','2024-09-08','$2a$10$w/MD.J6D939TlRq8815zv.62zK9B7t.8Dse55tt1Pmzivh1YzQnOy',b'1','[]',1,'2024-09-14 09:36:08',1,'2024-09-14 09:36:22',b'0');

/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  UNIQUE KEY `sys_user_role_pk` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;

INSERT INTO `sys_user_role` (`user_id`, `role_id`, `is_deleted`)
VALUES
	(1,694587732420202496,b'0'),
	(1,862344670758342656,b'0');

/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_customer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_customer`;

CREATE TABLE `t_customer` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `code` varchar(32) NOT NULL COMMENT '外部可见，唯一code',
  `name` varchar(32) DEFAULT NULL,
  `address` varchar(516) DEFAULT NULL,
  `contact_name` varchar(32) DEFAULT NULL,
  `whats_app` varchar(32) DEFAULT NULL,
  `remark` text,
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` bit(1) DEFAULT b'0' COMMENT '是否逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户';

LOCK TABLES `t_customer` WRITE;
/*!40000 ALTER TABLE `t_customer` DISABLE KEYS */;

INSERT INTO `t_customer` (`id`, `code`, `name`, `address`, `contact_name`, `whats_app`, `remark`, `create_by`, `create_time`, `update_by`, `update_time`, `is_deleted`)
VALUES
	(866355618628280320,'HARRY','harry','','harry','','比利时人 市场欧洲',1,'2024-09-14 14:40:53',1,'2024-09-14 15:51:21',b'0'),
	(866362648948678656,'VMOP222','VMop222','17 rennie crescent melksham, SN12 6GL, UK','Kilian Stenner','07850197748','英国人',1,'2024-09-14 15:08:49',1,'2024-09-14 15:51:31',b'0'),
	(866387922088140800,'TEST','测试','苏州市高新区石阳路28号1号厂房','Rick','189122323223','',1,'2024-09-14 16:49:15',1,'2024-09-14 16:49:15',b'0');

/*!40000 ALTER TABLE `t_customer` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_product
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_product`;

CREATE TABLE `t_product` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `code` varchar(32) NOT NULL COMMENT '外部可见，唯一code',
  `name` varchar(516) NOT NULL COMMENT '品名',
  `category_code` varchar(20) NOT NULL,
  `supplier_id` bigint(20) NOT NULL,
  `rmb_price` decimal(10,2) DEFAULT NULL,
  `usd_price` decimal(10,2) DEFAULT NULL,
  `description` text COMMENT '参数描述',
  `picture` text,
  `usd_price_template` text COMMENT 'USD报价模版',
  `rmb_price_template` text COMMENT 'RMB报价模版',
  `remark` text COMMENT '备注',
  `accessory_list` text,
  `selling_point` text,
  `certificate` varchar(512) DEFAULT NULL,
  `lead_time` text,
  `packing_information` text,
  `instance_id` bigint(20) NOT NULL,
  `attr_instance_id` bigint(20) NOT NULL,
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` bit(1) DEFAULT b'0' COMMENT '是否逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品';

LOCK TABLES `t_product` WRITE;
/*!40000 ALTER TABLE `t_product` DISABLE KEYS */;

INSERT INTO `t_product` (`id`, `code`, `name`, `category_code`, `supplier_id`, `rmb_price`, `usd_price`, `description`, `picture`, `usd_price_template`, `rmb_price_template`, `remark`, `accessory_list`, `selling_point`, `certificate`, `lead_time`, `packing_information`, `instance_id`, `attr_instance_id`, `create_by`, `create_time`, `update_by`, `update_time`, `is_deleted`)
VALUES
	(865315687927734272,'DC370','Multifunctional Cordless Wet and Dry Vacuum Cleaner','WASH',864965594942877696,12.00,125.20,'Standard accessories:\n    Flat suction*1\n    2 in 1 Bristle Brush*1','[{\"fullPath\":\"pictures/865658905403891712.png\",\"extension\":\"png\",\"fullName\":\"DC370.png\",\"updateTime\":\"2024-09-12 16:32:24\",\"url\":\"https://dc-product-manager.oss-cn-shanghai.aliyuncs.com/pictures/865658905403891712.png\",\"path\":\"865658905403891712.png\",\"groupName\":\"pictures\",\"createBy\":\"1\",\"size\":\"619683\",\"createTime\":\"2024-09-12 16:32:24\",\"updateBy\":\"1\",\"name\":\"DC370\",\"id\":\"865658905793961984\",\"contentType\":\"image/png\"}]',NULL,NULL,'','[[\"Conductive Tube + Electric Floor Brush with Large Roller\",\"11\",\"https://dc-product-manager.oss-cn-shanghai.aliyuncs.com/pictures/865624499062611968.png\"],[\"Extra Battery Pack\",\"16.5\",\"https://dc-product-manager.oss-cn-shanghai.aliyuncs.com/pictures/865624511603580928.png\"],[\"Anti-mite Mattress Brush with UV beads\",\"5.8\",\"https://dc-product-manager.oss-cn-shanghai.aliyuncs.com/pictures/865624523297300480.png\"]]','[[\"1. Sucking, mopping and washing together\"],[\"2. 180° flat working\"],[\"3. Solid sewage separated from liquid\"],[\"4. Multi-functional standard and optional accessories \"],[\"5. Water electrolysis function\"],[\"6. Anti-mite mattress brush with UV beads\"],[\"7. Hot air drying function\"],[\"8. Outstanding appearance design\"],[\"9. OEM for Haier\"]]','CE, CB, RoHs, CCC','[[\"for first order: 50 days\"],[\"for repeat order: 35 days\"],[\"for sample: 10 days\"]]','[[\"Product packing: 1pc packed into 1carton box\",\"\"],[\"20GP\",\"384pcs\"],[\"40GP\",\"792pcs\"],[\"40HQ\",\"891pcs\"],[\"Product size:\",\"260*210*1050mm\"],[\"Carton size:\",\"722*357*280mm\"],[\"Product net weight:\",\"4kg\"],[\"Gross weight:\",\"6kg\"]]',865315687927734272,865315687751573504,1,'2024-09-11 17:48:34',1,'2024-09-14 16:03:14',b'0'),
	(865939682041569280,'DC387','DC387 Wet Dry Vacuum Cleaner','WASH',864263994964258816,12.00,120.00,'','[{\"fullPath\":\"pictures/865939596905586688.jpg\",\"extension\":\"jpg\",\"fullName\":\"DC387.jpg\",\"updateTime\":\"2024-09-13 11:07:46\",\"url\":\"https://dc-product-manager.oss-cn-shanghai.aliyuncs.com/pictures/865939596905586688.jpg\",\"path\":\"865939596905586688.jpg\",\"groupName\":\"pictures\",\"createBy\":\"1\",\"size\":\"58157\",\"createTime\":\"2024-09-13 11:07:46\",\"updateBy\":\"1\",\"name\":\"DC387\",\"id\":\"865939599246008320\",\"contentType\":\"image/jpeg\"}]','{\"fullPath\":\"template/866387661730914304.xlsx\",\"extension\":\"xlsx\",\"fullName\":\"DC387-USD.xlsx\",\"updateTime\":\"2024-09-14 16:48:13\",\"url\":\"https://dc-product-manager.oss-cn-shanghai.aliyuncs.com/template/866387661730914304.xlsx\",\"path\":\"866387661730914304.xlsx\",\"groupName\":\"template\",\"createBy\":\"1\",\"size\":\"54211\",\"createTime\":\"2024-09-14 16:48:13\",\"updateBy\":\"1\",\"name\":\"DC387-USD\",\"id\":\"866387662452334592\",\"contentType\":\"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet\"}','{\"fullPath\":\"template/866387676348063744.xlsx\",\"extension\":\"xlsx\",\"fullName\":\"DC387-RMB.xlsx\",\"updateTime\":\"2024-09-14 16:48:16\",\"url\":\"https://dc-product-manager.oss-cn-shanghai.aliyuncs.com/template/866387676348063744.xlsx\",\"path\":\"866387676348063744.xlsx\",\"groupName\":\"template\",\"createBy\":\"1\",\"size\":\"54239\",\"createTime\":\"2024-09-14 16:48:16\",\"updateBy\":\"1\",\"name\":\"DC387-RMB\",\"id\":\"866387676624887808\",\"contentType\":\"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet\"}','','[]','[[\"1. Carpet vacuum\"],[\"2. Dual Action Brush Roll Mops and picks up debris from hard floors to clean better than a sponge mop in less time\"],[\"3. Roller heating function\"],[\"4. Minimize Hair Wrap with tangle free pet brush roll test with hair length up to 8 inches\"],[\"4.1. Sucking, mopping and washing together\"],[\"4.2. 180° flat working\"],[\"4.3. Solid sewage separated from liquid\"],[\"4.4. Multi-functional standard and optional accessories \"],[\"5. Water electrolysis function\"],[\"6. Anti-mite mattress brush with UV beads\"],[\"7. Hot air drying function\"],[\"8. Outstanding appearance design\"],[\"9. OEM for Haier\"]]','CE/ETL','[[\"for first order: 50 days\"],[\"for repeat order: 35 days\"],[\"for sample: 10 days\"]]','[[\"Product packing: 1pc packed into 1gift box\",\"\"],[\"20GP\",\"347pcs\"],[\"40GP\",\"867pcs\"],[\"40HQ\",\"900pcs\"],[\"Product size\",\"285*274*1160mm\"],[\"Carton size\",\"352*302*745mm\"],[\"Product packing: 1pc packed into 1carton box\",\"4.5kg\"],[\"20GP\",\"7.8kg\"]]',865939682041569280,865939679629844480,1,'2024-09-13 11:08:06',1,'2024-09-14 16:48:18',b'0'),
	(865947299325452288,'0022','2个配件测试2','TEST',866384383597125632,NULL,12.00,'','[]',NULL,NULL,'','[[\"2\",\"1\",\"https://dc-product-manager.oss-cn-shanghai.aliyuncs.com/pictures/866307193979314176.jpg\"],[\"2\",\"1\",\"https://dc-product-manager.oss-cn-shanghai.aliyuncs.com/pictures/866307208055398400.jpg\"]]','[]','','[]','[]',865947299325452288,866018919041769472,1,'2024-09-13 11:38:22',1,'2024-09-14 16:40:42',b'0'),
	(866082991015727104,'DC001','一个配件电脑','TEST',866384383597125632,2.00,10.00,'222','[{\"fullPath\":\"pictures/866082879094919168.jpg\",\"extension\":\"jpg\",\"fullName\":\"DC387.jpg\",\"updateTime\":\"2024-09-13 20:37:07\",\"url\":\"https://dc-product-manager.oss-cn-shanghai.aliyuncs.com/pictures/866082879094919168.jpg\",\"path\":\"866082879094919168.jpg\",\"groupName\":\"pictures\",\"createBy\":\"1\",\"size\":\"58157\",\"createTime\":\"2024-09-13 20:37:07\",\"updateBy\":\"1\",\"name\":\"DC387\",\"id\":\"866082880491622400\",\"contentType\":\"image/jpeg\"}]',NULL,NULL,'111111111','[[\"信息\",\"2\",\"https://dc-product-manager.oss-cn-shanghai.aliyuncs.com/pictures/866306260939612160.jpg\"]]','[[\"12223\"],[\"232332\"]]','22','[[\"2323232\"]]','[[\"23223\",\"2332\"],[\"2323\",\"22332\"]]',866082991015727104,866082989816156160,1,'2024-09-13 20:37:33',1,'2024-09-14 16:40:32',b'0'),
	(866290810776231936,'0009','无配件-Multifunctional Cordless Wet and Dry Vacuum Cleaner','TEST',866384383597125632,10.00,120.00,'参数描述','[{\"fullPath\":\"pictures/866294524710789120.jpg\",\"extension\":\"jpg\",\"fullName\":\"DC387.jpg\",\"updateTime\":\"2024-09-14 10:38:07\",\"url\":\"https://dc-product-manager.oss-cn-shanghai.aliyuncs.com/pictures/866294524710789120.jpg\",\"path\":\"866294524710789120.jpg\",\"groupName\":\"pictures\",\"createBy\":\"1\",\"size\":\"58157\",\"createTime\":\"2024-09-14 10:38:07\",\"updateBy\":\"1\",\"name\":\"DC387\",\"id\":\"866294526820524032\",\"contentType\":\"image/jpeg\"},{\"fullPath\":\"pictures/866294545673920512.png\",\"extension\":\"png\",\"fullName\":\"DC370.png\",\"updateTime\":\"2024-09-14 10:38:12\",\"url\":\"https://dc-product-manager.oss-cn-shanghai.aliyuncs.com/pictures/866294545673920512.png\",\"path\":\"866294545673920512.png\",\"groupName\":\"pictures\",\"createBy\":\"1\",\"size\":\"619683\",\"createTime\":\"2024-09-14 10:38:12\",\"updateBy\":\"1\",\"name\":\"DC370\",\"id\":\"866294546202402816\",\"contentType\":\"image/png\"}]',NULL,NULL,'','[]','[[\"1. Sucking, mopping and washing together\"],[\"2. 180° flat working\"]]','CE, CB, RoHs, CCC','[[\"for first order: 50 days\"],[\"for repeat order: 35 days\"],[\"for sample: 10 days\"]]','[[\"Product packing: 1pc packed into 1carton box\",\"\"],[\"20\",\"384pcs\"],[\"40GP\",\"792pcs\"],[\"40HQ\",\"891pcs\"],[\"Product size\",\"260*210*1050mm\"],[\"Carton size\",\"722*357*280mm\"],[\"Product net weight\",\"4kg\"],[\"Gross weight\",\"6kg\"]]',866290810776231936,866290808335147008,1,'2024-09-14 10:23:22',1,'2024-09-14 17:15:53',b'0'),
	(866311879989960704,'2223','4个配件','TEST',866384383597125632,2.00,1.00,'','[]',NULL,NULL,'4个配件','[[\"1\",\"1\",\"\"],[\"2\",\"1\",\"\"],[\"3\",\"1\",\"\"],[\"4\",\"1\",\"\"]]','[]','CE, CB, RoHs, CCC','[[\"for first order: 50 days\"],[\"for repeat order: 35 days\"],[\"for sample: 10 days\"]]','[[\"Product packing: 1pc packed into 1carton box\",\"\"],[\"20\",\"384pcs\"],[\"40GP\",\"792pcs\"],[\"40HQ\",\"891pcs\"],[\"Product size\",\"260*210*1050mm\"],[\"Carton size\",\"722*357*280mm\"],[\"Product net weight\",\"4kg\"],[\"Gross weight\",\"6kg\"]]',866311879989960704,866311877666316288,1,'2024-09-14 11:47:05',1,'2024-09-14 16:40:12',b'0');

/*!40000 ALTER TABLE `t_product` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_supplier
# ------------------------------------------------------------

DROP TABLE IF EXISTS `t_supplier`;

CREATE TABLE `t_supplier` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `code` varchar(32) NOT NULL COMMENT '外部可见，唯一code',
  `name` varchar(32) DEFAULT NULL,
  `contact_name` varchar(32) DEFAULT NULL,
  `contact_phone` varchar(32) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` bit(1) DEFAULT b'0' COMMENT '是否逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='供应商';

LOCK TABLES `t_supplier` WRITE;
/*!40000 ALTER TABLE `t_supplier` DISABLE KEYS */;

INSERT INTO `t_supplier` (`id`, `code`, `name`, `contact_name`, `contact_phone`, `create_by`, `create_time`, `update_by`, `update_time`, `is_deleted`)
VALUES
	(864263994964258816,'kh','凯豪','张三','18898797762',1,'2024-09-08 20:09:31',1,'2024-09-13 19:40:15',b'0'),
	(864965594942877696,'JWY','金伍阳','王总','18898767762',1,'2024-09-10 18:37:25',1,'2024-09-13 20:30:34',b'0'),
	(866384383597125632,'TEST','测试','张三','18878765523',1,'2024-09-14 16:35:11',1,'2024-09-14 16:35:11',b'0');

/*!40000 ALTER TABLE `t_supplier` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
