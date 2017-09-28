-- MySQL dump 10.13  Distrib 5.6.19, for linux-glibc2.5 (x86_64)
--
-- Host: 127.0.0.1    Database: stock
-- ------------------------------------------------------
-- Server version	5.5.54-0ubuntu0.14.04.1

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
-- Table structure for table `stock_data`
--
use stock;
DROP TABLE IF EXISTS `stock_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(45) NOT NULL,
  `stock_id` varchar(45) NOT NULL,
  `stock_name` varchar(45) NOT NULL,
  `have_amount` int(8) NOT NULL,
  `sell_amount` int(8) NOT NULL,
  `stock_money` double NOT NULL,
  `buy_money` double NOT NULL,
  `pro_money` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_data`
--

LOCK TABLES `stock_data` WRITE;
/*!40000 ALTER TABLE `stock_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_detail`
--

DROP TABLE IF EXISTS `stock_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_detail` (
  `stock_id` varchar(6) NOT NULL,
  `name` varchar(45) NOT NULL,
  `open_pri` varchar(45) NOT NULL,
  `form_pri` varchar(45) NOT NULL,
  `max_pri` varchar(45) NOT NULL,
  `min_pri` varchar(45) NOT NULL,
  `lastest_pri` varchar(45) NOT NULL,
  `up_pic` varchar(45) NOT NULL,
  `stock_limit` varchar(45) NOT NULL,
  `in_pic` varchar(45) NOT NULL,
  `out_pic` varchar(45) NOT NULL,
  `tra_amount` varchar(45) NOT NULL,
  `tra_number` varchar(45) NOT NULL,
  `priearn` varchar(45) NOT NULL,
  `stock_date` bigint(20) NOT NULL,
  `stock_time` time NOT NULL,
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_detail`
--

LOCK TABLES `stock_detail` WRITE;
/*!40000 ALTER TABLE `stock_detail` DISABLE KEYS */;
INSERT INTO `stock_detail` VALUES ('00001','长和','100.200','100.900','100.900','99.950','100.600','-0.300','-0.297','100.500','100.600','416795993.800','4154071','11.412',1505865600000,'16:08:50');
/*!40000 ALTER TABLE `stock_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_market`
--

DROP TABLE IF EXISTS `stock_market`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_market` (
  `symbol` varchar(6) NOT NULL,
  `name` varchar(255) NOT NULL,
  `buy` varchar(255) NOT NULL,
  `change_percent` varchar(255) NOT NULL,
  `price_change` varchar(255) NOT NULL,
  `stocks_sum` bigint(20) NOT NULL,
  `amount` bigint(20) NOT NULL,
  PRIMARY KEY (`symbol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_market`
--

LOCK TABLES `stock_market` WRITE;
/*!40000 ALTER TABLE `stock_market` DISABLE KEYS */;
INSERT INTO `stock_market` VALUES ('00001','长和','\01\00\01\0','\00\0.\04\00\0%\0','\00\0.\04\0',3857678500,315972070),('00002','中电控股','\08\00\0.\06\05\0','\0-\00\0.\04\03\0%\0','\0-\00\0.\03\05\0',2526450570,165103129),('00003','香港中华煤气','\01\04\0.\07\06\0','\0-\00\0.\04\00\0%\0','\0-\00\0.\00\06\0',13987646483,114148561),('00004','九龙仓集团','\07\01\0.\09\0','\00\0.\02\08\0%\0','\00\0.\02\0',3036227327,129638944),('00005','汇丰控股','76.1','\01\0.\00\06\0%\0','\00\0.\08\0',20407714977,1936653082),('00006','电能实业','\06\07\0.\05\05\0','\00\0.\03\00\0%\0','\00\0.\02\0',2134261654,233892688),('00008','电讯盈科','\04\0.\02\07\0','\01\0.\06\07\0%\0','\00\0.\00\07\0',7719638249,31257098),('00010','恒隆集团','\02\08\0.\04\05\0','\01\0.\09\07\0%\0','\00\0.\05\05\0',1361618242,12867892),('00011','恒生银行','\01\08\00\0.\08\0','\00\0.\02\08\0%\0','\00\0.\05\0',1911842736,125518248),('00012','恒基地产','\05\05\0.\03\0','\02\0.\01\02\0%\0','\01\0.\01\05\0',4001146284,389374200),('00014','希慎兴业','\03\08\0.\01\05\0','\00\0.\03\09\0%\0','\00\0.\01\05\0',1045588559,20383440),('00016','新鸿基地产','\01\03\04\0.\06\0','\0-\00\0.\03\07\0%\0','\0-\00\0.\05\0',2896392274,662164699),('00017','新世界发展','11.46','2.32%','0.26',9819197624,269084793),('00019','太古A','\08\00\0.\02\0','\0-\00\0.\03\01\0%\0','\0-\00\0.\02\05\0',905206000,83245760),('00020','会德丰','\05\07\0.\07\05\0','\00\0.\06\01\0%\0','\00\0.\03\05\0',2040849287,52693094),('00023','东亚银行','\03\04\0.\08\0','\00\0.\05\08\0%\0','\00\0.\02\0',2725556736,40904566),('00027','银河娱乐','\05\02\0.\08\0','\01\0.\00\05\0%\0','\00\0.\05\05\0',4280037893,353956263),('00038','第一拖拉机股份','\03\0.\06\09\0','\00\0.\00\00\0%\0','\00\0',391940000,3681460),('00041','鹰君','\04\03\0.\00\05\0','\01\0.\02\09\0%\0','\00\0.\05\05\0',687931038,9624442),('00066','港铁公司','\04\06\0.\00\05\0','\00\0.\00\00\0%\0','\00\0',6002378127,102824595);
/*!40000 ALTER TABLE `stock_market` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_order`
--

DROP TABLE IF EXISTS `stock_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_order` (
  `order_id` int(10) NOT NULL AUTO_INCREMENT,
  `stock_id` varchar(45) NOT NULL,
  `stock_name` varchar(45) NOT NULL,
  `user` varchar(11) NOT NULL,
  `order_price` double NOT NULL,
  `order_state` int(1) NOT NULL,
  `order_type` int(1) NOT NULL,
  `stock_time` datetime NOT NULL,
  `amount` int(8) NOT NULL,
  `match_time` datetime DEFAULT NULL,
  `match_price` double DEFAULT NULL,
  `match_amount` int(8) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_order`
--

LOCK TABLES `stock_order` WRITE;
/*!40000 ALTER TABLE `stock_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `phone` varchar(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `money` double NOT NULL,
  `randomStr` varchar(8) NOT NULL,
  `createAt` bigint(20) NOT NULL,
  `updateAt` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (7,'13545678370','Mr.Chen','87703060020975942749023556352561653391',100000000,'ZJvOkUMM',1506515041571,1506515041571);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'stock'
--

--
-- Dumping routines for database 'stock'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-28 10:58:27
