/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.25 : Database - jiakao
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jiakao` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `jiakao`;

/*Table structure for table `dict_item` */

DROP TABLE IF EXISTS `dict_item`;

CREATE TABLE `dict_item` (
  `id` smallint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `value` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '值',
  `sn` smallint unsigned NOT NULL DEFAULT '0' COMMENT '排列顺序，默认0。值越大，就排在越前面',
  `disabled` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否禁用。0代表不禁用（启用），1代表禁用',
  `type_id` smallint unsigned NOT NULL DEFAULT '0' COMMENT '所属的类型',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `dict_item_name_type_id_uindex` (`name`,`type_id`) USING BTREE,
  UNIQUE KEY `dict_item_value_type_id_uindex` (`value`,`type_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='数据字典条目';

/*Data for the table `dict_item` */

insert  into `dict_item`(`id`,`name`,`value`,`sn`,`disabled`,`type_id`) values (1,'程序员','coder',8,1,1),(2,'教师','teacher',0,0,1),(3,'司机','driver',6,1,1),(6,'666','7777',0,0,0),(7,'课程合集','0',0,0,115),(8,'科目2','2',0,0,115),(9,'科目3','3',0,0,115),(10,'用户','11',0,1,98),(11,'广东','da',0,0,1);

/*Table structure for table `dict_type` */

DROP TABLE IF EXISTS `dict_type`;

CREATE TABLE `dict_type` (
  `id` smallint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `value` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '值',
  `intro` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `dict_type_name_uindex` (`name`) USING BTREE,
  UNIQUE KEY `dict_type_value_uindex` (`value`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='数据字典类型';

/*Data for the table `dict_type` */

insert  into `dict_type`(`id`,`name`,`value`,`intro`) values (1,'职业','job','一份工作'),(13,'哈哈12','haha12','12'),(16,'哈哈15','haha15','15'),(17,'哈哈16','haha16','16'),(19,'哈哈18','haha18','18'),(20,'哈哈19','haha19','19'),(22,'哈哈21','haha21','21'),(23,'哈哈22','haha22','22'),(24,'哈哈23','haha23','23'),(25,'哈哈24','haha24','24'),(26,'哈哈25','haha25','25'),(27,'哈哈26','haha26','26'),(28,'哈哈27','haha27','27'),(29,'哈哈28','haha28','28'),(30,'哈哈29','haha29','29'),(31,'哈哈30','haha30','30'),(34,'哈哈33','haha33','33'),(35,'哈哈34','haha34','34'),(36,'哈哈35','haha35','35'),(37,'哈哈36','haha36','36'),(38,'哈哈37','haha37','37'),(39,'哈哈38','haha38','38'),(42,'哈哈41','haha41','41'),(44,'哈哈43','haha43','43'),(45,'哈哈44','haha44','44'),(46,'哈哈45','haha45','45'),(47,'哈哈46','haha46','46'),(48,'哈哈47','haha47','47'),(49,'哈哈48','haha48','48'),(50,'哈哈49','haha49','49'),(51,'哈哈50','haha50','50'),(52,'哈哈51','haha51','51'),(53,'哈哈52','haha52','52'),(54,'哈哈53','haha53','53'),(55,'哈哈54','haha54','54'),(56,'哈哈55','haha55','55'),(57,'哈哈56','haha56','56'),(58,'哈哈57','haha57','57'),(59,'哈哈58','haha58','58'),(60,'哈哈59','haha59','59'),(61,'哈哈60','haha60','60'),(62,'哈哈61','haha61','61'),(63,'哈哈62','haha62','62'),(64,'哈哈63','haha63','63'),(65,'哈哈64','haha64','64'),(66,'哈哈65','haha65','65'),(67,'哈哈66','haha66','66'),(68,'哈哈67','haha67','67'),(69,'哈哈68','756567657','68'),(70,'哈哈69','haha69','69'),(71,'哈哈70','haha70','70'),(72,'哈哈71','haha71','71'),(73,'哈哈72','haha72','72'),(74,'哈哈73','haha73','73'),(75,'哈哈74','haha74','74'),(76,'哈哈75','haha75','75'),(77,'哈哈76','haha76','76'),(88,'哈哈87','haha87','87'),(91,'哈哈90','haha90','90'),(93,'哈哈92','haha92','92'),(94,'哈哈93','haha93','93'),(95,'哈哈94','haha94','94'),(96,'哈哈95','haha95','95'),(98,'哈哈97','haha97','978'),(99,'哈哈98','haha98','98'),(100,'哈哈99','haha99','99'),(101,'654546','765768768','789789789'),(112,'123','123','123'),(115,'科2科3课程类型','course_type','科2科3课程类型科2科3课程类型科2科3课程类型科2科3课程类型'),(116,'dad','d','');

/*Table structure for table `exam_place` */

DROP TABLE IF EXISTS `exam_place`;

CREATE TABLE `exam_place` (
  `id` smallint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `province_id` smallint unsigned NOT NULL DEFAULT '0' COMMENT '考场是哪个省份的',
  `city_id` smallint unsigned NOT NULL DEFAULT '0' COMMENT '考场是哪个城市的',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '考场的具体地址',
  `latitude` decimal(10,7) NOT NULL DEFAULT '0.0000000' COMMENT '纬度',
  `longitude` decimal(10,7) NOT NULL DEFAULT '0.0000000' COMMENT '经度',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `exam_place_city_id_name_uindex` (`city_id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='考场';

/*Data for the table `exam_place` */

insert  into `exam_place`(`id`,`name`,`province_id`,`city_id`,`address`,`latitude`,`longitude`) values (1,'白云考场',3,5,'白云区棠下','0.0000000','0.0000000'),(2,'金牛考场',6,9,'金牛区','0.0000000','0.0000000'),(3,'电瓶车考场',10,11,'很多电瓶车的考场','2.0000000','1.0000000'),(4,'保安考场',3,12,'','0.0000000','0.0000000'),(5,'天河考场',3,5,'天河机场\n','0.0000000','0.0000000');

/*Table structure for table `exam_place_course` */

DROP TABLE IF EXISTS `exam_place_course`;

CREATE TABLE `exam_place_course` (
  `id` mediumint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
  `type` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '课程类型：0是课程合集，2是科目2，3是科目3',
  `intro` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `video` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '视频',
  `cover` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '封面',
  `place_id` smallint unsigned NOT NULL DEFAULT '0' COMMENT '考场',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='考场课程';

/*Data for the table `exam_place_course` */

insert  into `exam_place_course`(`id`,`create_time`,`name`,`price`,`type`,`intro`,`video`,`cover`,`place_id`) values (1,'2021-01-08 21:58:35','倒车入库','99.99',0,'这是一门比较难的课程','','upload/img/51f5048f-dddc-430c-9038-3bff9a5f89a9.jpg',5),(5,'2023-03-28 11:46:27','半坡启动','998.00',0,'','','',1);

/*Table structure for table `plate_region` */

DROP TABLE IF EXISTS `plate_region`;

CREATE TABLE `plate_region` (
  `id` smallint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `plate` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '车牌',
  `pinyin` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '拼音',
  `parent_id` smallint unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `plate_region_parent_id_name_uindex` (`parent_id`,`name`) USING BTREE,
  KEY `plate_region_parent_id_plate_index` (`parent_id`,`plate`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='带有车牌的区域';

/*Data for the table `plate_region` */

insert  into `plate_region`(`id`,`name`,`plate`,`pinyin`,`parent_id`) values (3,'广东','奥','GUANG_DONG',0),(4,'福建','闽','FU_JIAN',0),(5,'广州','A','guangzhou',3),(6,'四川','川','SI_CHUAN',0),(8,'贵州','贵','GUI_ZHOU',0),(9,'成都','A','CHENG_DOU',6),(10,'广西','桂','GUANG_XI',0),(11,'南宁','A','NAN_NING',10),(12,'深圳','B','SHEN_ZHEN',3),(13,'珠海','C','ZHU_HAI',3),(14,'汕头','D','SHAN_TOU',3),(15,'福州','A','FU_ZHOU',4),(16,'厦门','J','SHA_MEN',4),(17,'莆田','B','PU_TIAN',4),(18,'茂名','K','MAO_MING',3),(19,'海南','琼','HAI_NAN',0);

/*Table structure for table `sys_resource` */

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `id` tinyint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `uri` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '链接地址',
  `permission` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限标识',
  `type` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '资源类型（0是目录，1是菜单，2是目录）',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '图标',
  `sn` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '序号',
  `parent_id` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '父资源id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `sys_resource_parent_id_name_uindex` (`parent_id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='资源';

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`id`,`name`,`uri`,`permission`,`type`,`icon`,`sn`,`parent_id`) values (1,'系统','','',0,'fa fa-lock',0,0),(2,'元数据','','',0,'fa fa-newspaper-o',1,0),(3,'考试','','',0,'fa fa-mortar-board',2,0),(4,'客户','','',0,'fa fa-users',3,0),(5,'用户','page/sys/user/list.html','',1,'fa fa-user-circle',0,1),(6,'角色','page/sys/role/list.html','',1,'fa fa-user',1,1),(7,'资源','page/sys/resource/list.html','',1,'fa fa-key',2,1),(8,'省份','page/metadata/province/list.html','',1,'fa fa-map-marker',0,2),(9,'城市','page/metadata/city/list.html','',1,'fa fa-location-arrow',1,2),(10,'数据字典类型','page/metadata/dictType/list.html','',1,'fa fa-cube',2,2),(11,'数据字典条目','page/metadata/dictItem/list.html','',1,'fa fa-cubes',3,2),(12,'考场','page/exam/examPlace/list.html','',1,'fa fa-car',0,3),(13,'科1科4','page/exam/k1k4/list.html','',1,'fa fa-pencil',1,3),(14,'科2科3','page/exam/examPlaceCourse/list.html','',1,'fa fa-video-camera',2,3),(15,'交易','','',0,'fa fa-money',4,0),(16,'驾校','page/customer/school/list.html','',1,'fa fa-university',0,4),(17,'教练','page/customer/coach/list.html','',1,'fa fa-male',1,4),(18,'学员','page/customer/student/list.html','',1,'fa fa-child',2,4),(19,'提现','page/deal/withdraw/list.html','',1,'fa fa-cny',0,15),(20,'订单','page/deal/order/list.html','',1,'fa fa-ticket',1,15),(21,'查询','','sysUser:list',2,'',0,5),(22,'添加','','sysUser:add',2,'',0,5),(23,'修改','','sysUser:update',2,'',0,5),(24,'删除','','sysUser:remove',2,'',0,5),(25,'查询','','sysRole:list',2,'',0,6),(26,'添加','','sysRole:add',2,'',0,6),(27,'修改','','sysRole:update',2,'',0,6),(28,'删除','','sysRole:remove',2,'',0,6),(29,'查询','','sysResource:list',2,'',0,7),(30,'添加','','sysResource:add',2,'',0,7),(31,'修改','','sysResource:update',2,'',0,7),(32,'删除','','sysResource:remove',2,'',0,7),(33,'查询','','province:list',2,'',0,8),(34,'添加','','province:add',2,'',0,8),(35,'修改','','province:update',2,'',0,8),(36,'删除','','province:remove',2,'',0,8),(37,'查询','','city:list',2,'',0,9),(38,'添加','','city:add',2,'',0,9),(39,'修改','','city:update',2,'',0,9),(40,'删除','','city:remove',2,'',0,9),(41,'查询','','dictType:list',2,'',0,10),(42,'添加','','dictType:add',2,'',0,10),(43,'修改','','dictType:update',2,'',0,10),(44,'删除','','dictType:remove',2,'',0,10),(45,'查询','','dictItem:list',2,'',0,11),(46,'添加','','dictItem:add',2,'',0,11),(47,'修改','','dictItem:update',2,'',0,11),(48,'删除','','dictItem:remove',2,'',0,11),(49,'查询','','examPlace:list',2,'',0,12),(50,'添加','','examPlace:add',2,'',0,12),(51,'修改','','examPlace:update',2,'',0,12),(52,'删除','','examPlace:remove',2,'',0,12),(53,'查询','','examPlaceCourse:list',2,'',0,14),(54,'添加','','examPlaceCourse:add',2,'',0,14),(55,'修改','','examPlaceCourse:update',2,'',0,14),(56,'删除','','examPlaceCourse:remove',2,'',0,14);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` tinyint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `sys_role_name_uindex` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`) values (4,'客服'),(1,'总经理'),(2,'销售经理');

/*Table structure for table `sys_role_resource` */

DROP TABLE IF EXISTS `sys_role_resource`;

CREATE TABLE `sys_role_resource` (
  `role_id` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '角色id',
  `resource_id` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '资源id',
  PRIMARY KEY (`resource_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色-资源';

/*Data for the table `sys_role_resource` */

insert  into `sys_role_resource`(`role_id`,`resource_id`) values (1,21),(2,21),(1,22),(2,22),(1,23),(2,23),(1,24),(1,25),(2,25),(4,25),(1,26),(2,26),(1,27),(2,27),(1,28),(1,29),(2,29),(4,29),(1,30),(2,30),(1,31),(2,31),(1,32),(1,33),(2,33),(4,33),(1,34),(2,34),(1,35),(2,35),(1,36),(1,37),(2,37),(4,37),(1,38),(2,38),(1,39),(2,39),(1,40),(1,41),(2,41),(4,41),(1,42),(2,42),(1,43),(2,43),(1,44),(1,45),(2,45),(4,45),(1,46),(2,46),(1,47),(2,47),(1,48),(1,49),(2,49),(4,49),(1,50),(2,50),(1,51),(2,51),(1,52),(1,53),(2,53),(4,53),(1,54),(2,54),(1,55),(2,55),(1,56);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` smallint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nickname` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `username` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '登录用的用户名',
  `password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '登录用的密码',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建的时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后一次登录的时间',
  `status` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '账号的状态，0是正常，1是锁定',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `sys_user_username_uindex` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户（可以登录后台系统的）';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`nickname`,`username`,`password`,`create_time`,`login_time`,`status`) values (1,'mj666','mj666','10ce015cbc7f405b4f9648d1ee68c3ec','2021-01-15 20:54:16','2021-01-22 22:20:59',0),(3,'mj777','xiaoshoujingli','4a68ee2565e67e908d66992337497b3c','2021-01-15 21:14:32','2021-01-28 21:46:48',0),(5,'123','zongjngli','d0c91300e4b8ddf0d6083b1b9e5c6b57','2021-01-18 20:54:01','2021-01-29 22:15:11',0),(6,'666','kefu','0444e11e0501438bda1af664f36974de','2021-01-18 21:21:32','2021-01-28 22:23:12',0),(12,'lihua','dad','11d8f6bff1af545e799a6f218fb4d07f','2023-03-31 10:09:49','2023-04-10 18:38:53',0),(13,'dada','dada','11d8f6bff1af545e799a6f218fb4d07f','2023-04-03 16:05:45','2023-04-03 16:49:11',0),(14,'123','123','11d8f6bff1af545e799a6f218fb4d07f','2023-04-03 16:05:59','2023-04-03 20:02:23',0),(15,'1234','1234','11d8f6bff1af545e799a6f218fb4d07f','2023-04-03 20:05:18','2023-04-03 20:42:12',0);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `role_id` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '角色id',
  `user_id` smallint unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户-角色';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`role_id`,`user_id`) values (2,1),(2,3),(1,5),(4,6),(1,12),(2,13),(4,14),(4,15);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
