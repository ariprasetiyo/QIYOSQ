-- MySQL dump 10.13  Distrib 5.5.58, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: hr-application-spring
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.10-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_audit_trail`
--

DROP TABLE IF EXISTS `sys_audit_trail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_audit_trail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) DEFAULT NULL,
  `created_time` datetime NOT NULL,
  `disabled` bit(1) NOT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_time` datetime NOT NULL,
  `version` varchar(5) DEFAULT NULL,
  `action_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_audit_trail`
--

LOCK TABLES `sys_audit_trail` WRITE;
/*!40000 ALTER TABLE `sys_audit_trail` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_audit_trail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_authorization`
--

DROP TABLE IF EXISTS `sys_authorization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_authorization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) DEFAULT NULL,
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `disabled` bit(1) NOT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` varchar(255) DEFAULT NULL,
  `sys_roles_id` bigint(20) NOT NULL,
  `is_delete` bit(1) NOT NULL,
  `is_insert` bit(1) NOT NULL,
  `is_read` bit(1) NOT NULL,
  `is_update` bit(1) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `sys_menu_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4ddvput2y3elnxqf1cj10rx26` (`sys_roles_id`),
  KEY `FKhq2ryvy52dy43k5ddyhhc2xgy` (`parent_id`),
  KEY `FK26plj38hw0p4qguic3jid2ep0` (`sys_menu_id`),
  CONSTRAINT `FK26plj38hw0p4qguic3jid2ep0` FOREIGN KEY (`sys_menu_id`) REFERENCES `sys_menu` (`id`),
  CONSTRAINT `FK4ddvput2y3elnxqf1cj10rx26` FOREIGN KEY (`sys_roles_id`) REFERENCES `sys_roles` (`id`),
  CONSTRAINT `FKhq2ryvy52dy43k5ddyhhc2xgy` FOREIGN KEY (`parent_id`) REFERENCES `sys_authorization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=246 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_authorization`
--

LOCK TABLES `sys_authorization` WRITE;
/*!40000 ALTER TABLE `sys_authorization` DISABLE KEYS */;
INSERT INTO `sys_authorization` VALUES (69,NULL,'2016-12-11 22:01:14','',NULL,'2016-12-11 22:01:14','1.0',1,'','','','',NULL,1),(70,NULL,'2016-12-11 22:01:14','',NULL,'2016-12-11 22:01:14','1.0',1,'','','','\0',69,2),(71,NULL,'2016-12-11 22:01:14','\0',NULL,'2016-12-11 22:01:14','1.0',1,'\0','','','',69,3),(72,NULL,'2016-12-11 22:01:14','\0',NULL,'2016-12-11 22:01:14','1.0',1,'\0','\0','','',NULL,4),(73,NULL,'2016-12-11 22:01:14','',NULL,'2016-12-11 22:01:14','1.0',1,'','','','',72,5),(74,NULL,'2016-12-11 22:01:14','\0',NULL,'2016-12-11 22:01:14','1.0',1,'\0','','','',73,6),(75,NULL,'2016-12-11 22:01:14','\0',NULL,'2016-12-11 22:01:14','1.0',1,'\0','','','',73,7),(76,NULL,'2016-12-11 22:01:14','',NULL,'2016-12-11 22:01:14','1.0',1,'','','','',72,8),(77,NULL,'2016-12-11 22:01:15','',NULL,'2016-12-11 22:01:15','1.0',1,'\0','','','',72,9),(78,NULL,'2016-12-11 22:01:15','',NULL,'2016-12-11 22:01:15','1.0',1,'\0','','','',72,10),(90,NULL,'2017-02-11 19:21:30','\0',NULL,'2017-02-11 19:21:30','1.0',3,'\0','\0','','\0',NULL,1),(132,NULL,'2017-02-12 17:16:01','',NULL,'2017-02-12 17:16:01','1.0',2,'','','','',NULL,1),(139,NULL,'2017-02-12 22:09:17','',NULL,'2017-02-12 22:09:17','1.0',2,'','','','',NULL,1),(140,NULL,'2017-02-13 22:05:11','',NULL,'2017-02-13 22:05:11','1.0',2,'','','','',132,1),(142,NULL,'2017-02-15 09:56:44','',NULL,'2017-02-15 09:56:44','1.0',2,'','','','',NULL,1),(143,NULL,'2017-02-15 09:59:49','\0',NULL,'2017-02-15 09:59:49','1.0',2,'','','','',NULL,1),(155,NULL,'2017-02-15 11:24:54','\0',NULL,'2017-02-15 11:24:54','1.0',2,'\0','\0','','\0',132,2),(157,NULL,'2017-02-15 11:55:37','',NULL,'2017-02-15 11:55:37','1.0',2,'','','','',NULL,1),(163,NULL,'2017-02-15 18:36:54','',NULL,'2017-02-15 18:36:54','1.0',2,'','','','',NULL,1),(165,NULL,'2017-02-15 18:55:11','',NULL,'2017-02-15 18:55:11','1.0',2,'','','','',NULL,2),(166,NULL,'2017-02-15 18:56:32','',NULL,'2017-02-15 18:56:32','1.0',2,'','','','',NULL,2),(176,NULL,'2017-02-15 19:49:04','',NULL,'2017-02-15 19:49:04','1.0',2,'','','','',132,1),(177,NULL,'2017-02-15 19:49:45','',NULL,'2017-02-15 19:49:45','1.0',2,'','','','',132,1),(179,NULL,'2017-02-15 19:56:30','',NULL,'2017-02-15 19:56:30','1.0',2,'','','','',132,1),(181,NULL,'2017-02-15 20:01:36','',NULL,'2017-02-15 20:01:36','1.0',2,'','','','',132,1),(183,NULL,'2017-02-15 22:07:43','',NULL,'2017-02-15 22:07:43','1.0',2,'','','','',NULL,2),(189,NULL,'2017-02-15 22:19:18','',NULL,'2017-02-15 22:19:18','1.0',2,'','','','',NULL,2),(197,NULL,'2017-02-16 22:40:37','',NULL,'2017-02-16 22:40:37','1.0',2,'','','','',NULL,1),(198,NULL,'2017-02-16 22:41:28','',NULL,'2017-02-16 22:41:28','1.0',2,'','','','',181,1),(203,NULL,'2017-02-18 14:37:32','',NULL,'2017-02-18 14:37:32','1.0',2,'','','','',NULL,1),(204,NULL,'2017-02-18 14:38:04','',NULL,'2017-02-18 14:38:04','1.0',2,'','','','',189,1),(209,NULL,'2017-02-18 17:10:14','',NULL,'2017-02-18 17:10:14','1.0',2,'','','','',NULL,1),(237,NULL,'2017-02-21 22:44:45','',NULL,'2017-02-21 22:44:45','1.0',2,'','','','',NULL,1),(238,NULL,'2017-02-21 22:46:25','',NULL,'2017-02-21 22:46:25','1.0',2,'','','','',NULL,1),(239,NULL,'2017-02-21 22:49:03','',NULL,'2017-02-21 22:49:03','1.0',2,'','','','',NULL,1),(240,NULL,'2017-04-19 15:52:41','',NULL,'2017-04-19 15:52:41','1.0',1,'','','','',73,13),(243,NULL,'2017-04-19 15:56:24','',NULL,'2017-04-19 15:56:24','1.0',1,'','','','',72,11),(244,NULL,'2017-04-19 15:57:01','',NULL,'2017-04-19 15:57:01','1.0',1,'','','','',243,12),(245,NULL,'2017-04-19 18:32:40','',NULL,'2017-04-19 18:32:40','1.0',1,'','','','',NULL,14);
/*!40000 ALTER TABLE `sys_authorization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) DEFAULT NULL,
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `disabled` bit(1) NOT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` varchar(255) DEFAULT NULL,
  `menus_name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'ari','2017-02-08 00:00:00','\0','ari','2017-02-08 00:00:00','1.0','Dashboard',NULL),(2,'ari','2017-02-08 00:00:00','\0','ari','2017-02-08 00:00:00','1.0','Dashboard V1','/Dashboard/V1'),(3,'ari','2017-02-08 00:00:00','\0','ari','2017-02-08 00:00:00','1.0','Dashboard V2','/Dashboard/V2'),(4,'ari','2017-02-08 00:00:00','\0','ari','2017-02-08 00:00:00','1.0','Utility',NULL),(5,'ari','2017-02-08 00:00:00','\0','ari','2017-02-08 00:00:00','1.0','Setting User',NULL),(6,'ari','2017-02-08 00:00:00','\0','ari','2017-02-08 00:00:00','1.0','User','../../admin/v1/user'),(7,'ari','2017-02-08 00:00:00','\0','ari','2017-02-08 00:00:00','1.0','Authorization','../../admin/v1/authorization'),(8,'ari','2017-02-08 00:00:00','\0','ari','2017-02-08 00:00:00','1.0','Profile Setting','admin/v1/setting/calendar'),(9,'ari','2017-02-08 00:00:00','\0','ari','2017-02-08 00:00:00','1.0','Report Setting','admin/v1/reportsetting '),(10,'ari','2017-02-08 00:00:00','\0','ari','2017-02-08 00:00:00','1.0','Calendar Setting','admin/v1/setting/calendar'),(11,'NULL','2017-04-19 00:00:00','\0','NULL','2017-04-19 00:00:00','1.0','Application Setting','NULL'),(12,'NULL','2017-04-19 00:00:00','\0','NULL','2017-04-19 00:00:00','1.0','Add Menu','../../admin/v1/menu'),(13,'NULL','2017-04-19 00:00:00','\0','NULL','2017-04-19 00:00:00','1.0','User Group','../../admin/v1/usergroup'),(14,'NULL','2017-04-19 00:00:00','\0','NULL','2017-04-19 00:00:00','1.0','Basic Web','../../admin/v1/basicweb'),(15,NULL,'2017-04-25 16:10:51','',NULL,'2017-04-25 16:10:51','1.0','test2222','/test/test555555'),(16,NULL,'2017-04-25 15:55:03','\0',NULL,'2017-04-25 15:55:03','1.0','Test Menu 2','Test/menu/menu'),(17,NULL,'2017-04-25 22:20:50','',NULL,'2017-04-25 22:28:02','1.0','aa','asas');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_roles`
--

DROP TABLE IF EXISTS `sys_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) DEFAULT NULL,
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `disabled` bit(1) NOT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` varchar(255) DEFAULT NULL,
  `role_name` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_roles`
--

LOCK TABLES `sys_roles` WRITE;
/*!40000 ALTER TABLE `sys_roles` DISABLE KEYS */;
INSERT INTO `sys_roles` VALUES (1,NULL,'2016-11-20 01:38:52','\0',NULL,'2016-11-20 01:38:52','1.0','admin'),(2,NULL,'2016-11-20 01:38:52','\0',NULL,'2016-11-20 01:38:52','1.0','approval'),(3,NULL,'2016-11-20 01:38:52','\0',NULL,'2016-11-20 01:38:52','1.0','user'),(4,NULL,'2016-11-20 01:38:52','\0',NULL,'2016-11-20 01:38:52','1.0','public');
/*!40000 ALTER TABLE `sys_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) DEFAULT NULL,
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `disabled` bit(1) NOT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` varchar(255) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `no_hp` varchar(13) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(50) NOT NULL,
  `sys_user_roles_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlnx8ypdbi83bcq1vouss9uuxe` (`sys_user_roles_id`),
  CONSTRAINT `FKlnx8ypdbi83bcq1vouss9uuxe` FOREIGN KEY (`sys_user_roles_id`) REFERENCES `sys_roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (12,NULL,'2017-04-16 08:14:39','\0',NULL,'2017-04-16 08:14:39','1.0','prasetiyooo@gmail.com','','','085645480401','1234','ari',1),(15,NULL,'2017-04-19 22:08:47','\0',NULL,'2017-04-19 22:08:47','1.0','coba@coba','','coba','1111111111111','coba','coba',NULL),(16,NULL,'2017-04-04 22:15:20','\0',NULL,'2017-04-04 22:15:20','1.0','coba@coba','','coba','1111111111','12344','coba',NULL),(17,NULL,'2017-04-19 22:02:53','\0',NULL,'2017-09-24 22:35:01','1.0','iiiiiiiiii@iiiiiiiiii','','aaaaaa','1111111111111','dummay','iiiiiiiiii',NULL),(18,NULL,'2017-04-04 22:28:52','\0',NULL,'2017-04-04 22:28:52','1.0','a@cobasaas','','asa','11111111111','123345','aa',NULL),(22,NULL,'2017-04-15 20:20:57','\0',NULL,'2017-04-15 20:20:57','1.0','123456@gmail','\0','123456','123456','123456','123456',NULL),(23,NULL,'2017-04-15 20:21:56','\0',NULL,'2017-04-15 20:21:56','1.0','123456@dda','\0','123456','123456123456','123456','123456',NULL),(25,NULL,'2017-09-23 21:05:32','\0',NULL,'2017-09-23 21:05:32','1.0','coba12334@gmail.com','','test1234','0859792392922','test12345','baru saja',NULL),(26,NULL,'2017-09-24 16:01:16','\0',NULL,'2017-09-24 16:01:16','1.0','vivivi@yahoo.com','','vivivi@yahoo.com','085645462362','cobaasas','coba coba',NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_roles`
--

DROP TABLE IF EXISTS `sys_user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) DEFAULT NULL,
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `disabled` bit(1) NOT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` varchar(255) DEFAULT NULL,
  `sys_roles_id` bigint(20) NOT NULL,
  `sys_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5rp9sy8avs7wgdwvg4q827imf` (`sys_roles_id`),
  KEY `FKd0ut7sloes191bygyf7a3pk52` (`sys_user_id`),
  CONSTRAINT `FK5rp9sy8avs7wgdwvg4q827imf` FOREIGN KEY (`sys_roles_id`) REFERENCES `sys_roles` (`id`),
  CONSTRAINT `FKd0ut7sloes191bygyf7a3pk52` FOREIGN KEY (`sys_user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_roles`
--

LOCK TABLES `sys_user_roles` WRITE;
/*!40000 ALTER TABLE `sys_user_roles` DISABLE KEYS */;
INSERT INTO `sys_user_roles` VALUES (35,NULL,'2017-04-15 21:02:17','\0',NULL,'2017-04-15 21:02:17','1.0',1,12),(74,NULL,'2017-04-19 22:08:47','\0',NULL,'2017-04-19 22:08:47','1.0',4,15),(76,NULL,'2017-09-23 21:05:32','\0',NULL,'2017-09-23 21:05:32','1.0',3,25),(77,NULL,'2017-09-24 16:01:16','\0',NULL,'2017-09-24 16:01:16','1.0',2,26),(78,NULL,'2017-09-24 22:35:01','\0',NULL,'2017-09-24 22:35:01','1.0',1,17),(79,NULL,'2017-09-24 22:35:01','\0',NULL,'2017-09-24 22:35:01','1.0',2,17);
/*!40000 ALTER TABLE `sys_user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-29 13:19:29
