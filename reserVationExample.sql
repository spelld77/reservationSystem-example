CREATE DATABASE  IF NOT EXISTS `reserve` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `reserve`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: reserve
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary view structure for view `all_seat_time`
--

DROP TABLE IF EXISTS `all_seat_time`;
/*!50001 DROP VIEW IF EXISTS `all_seat_time`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `all_seat_time` AS SELECT 
 1 AS `id`,
 1 AS `start_time`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `reserve`
--

DROP TABLE IF EXISTS `reserve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserve` (
  `id` int NOT NULL AUTO_INCREMENT,
  `reserve_date` date DEFAULT NULL,
  `start_time` int DEFAULT NULL,
  `seat_id` varchar(10) DEFAULT NULL,
  `user_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `start_time` (`start_time`),
  KEY `seat_id` (`seat_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `reserve_ibfk_1` FOREIGN KEY (`start_time`) REFERENCES `reserve_time` (`start_time`),
  CONSTRAINT `reserve_ibfk_2` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`id`),
  CONSTRAINT `reserve_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=277 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserve`
--

LOCK TABLES `reserve` WRITE;
/*!40000 ALTER TABLE `reserve` DISABLE KEYS */;
INSERT INTO `reserve` VALUES (49,'2021-12-01',10,'B6','scott'),(51,'2021-12-01',10,'C7','scott'),(104,'2021-12-01',11,'A0','scott'),(109,'2021-12-08',14,'A9','scott'),(115,'2021-12-01',9,'A2','scott'),(120,'2021-12-01',9,'A5','scott'),(123,'2021-12-01',16,'D4','scott'),(124,'2021-12-01',9,'C2','scott'),(129,'2021-12-03',9,'D7','scott'),(253,'2021-12-10',13,'C2','scott'),(254,'2021-12-01',9,'A0','scott'),(256,'2021-12-01',9,'B0','scott'),(258,'2021-11-11',9,'A3','hello'),(276,'2022-09-01',9,'A0','admin');
/*!40000 ALTER TABLE `reserve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserve_time`
--

DROP TABLE IF EXISTS `reserve_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserve_time` (
  `start_time` int NOT NULL,
  PRIMARY KEY (`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserve_time`
--

LOCK TABLES `reserve_time` WRITE;
/*!40000 ALTER TABLE `reserve_time` DISABLE KEYS */;
INSERT INTO `reserve_time` VALUES (9),(10),(11),(13),(14),(15),(16);
/*!40000 ALTER TABLE `reserve_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `id` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES ('A0'),('A1'),('A2'),('A3'),('A4'),('A5'),('A6'),('A7'),('A8'),('A9'),('B0'),('B1'),('B2'),('B3'),('B4'),('B5'),('B6'),('B7'),('B8'),('B9'),('C0'),('C1'),('C2'),('C3'),('C4'),('C5'),('C6'),('C7'),('C8'),('C9'),('D0'),('D1'),('D2'),('D3'),('D4'),('D5'),('D6'),('D7'),('D8'),('D9'),('E0'),('E1'),('E2'),('E3'),('E4'),('E5'),('E6'),('E7'),('E8'),('E9');
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(30) NOT NULL,
  `pw` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','admin'),('hello','world'),('root','root'),('scott','tiger');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `all_seat_time`
--

/*!50001 DROP VIEW IF EXISTS `all_seat_time`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `all_seat_time` AS select `a`.`id` AS `id`,`b`.`start_time` AS `start_time` from (`seat` `a` join `reserve_time` `b`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-25 13:32:52
