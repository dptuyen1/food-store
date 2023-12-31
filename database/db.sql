CREATE DATABASE  IF NOT EXISTS `food-store` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `food-store`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: food-store
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idcategory_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Thức ăn'),(2,'Nước uống'),(3,'Ăn vặt'),(4,'Combo gia đình'),(5,'Tráng miệng');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `value` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idcoupon_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
INSERT INTO `coupon` VALUES (1,'NEWMEMBER',10.00);
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `details`
--

DROP TABLE IF EXISTS `details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `unit_price` decimal(10,2) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `product_id` int NOT NULL,
  `invoice_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `iddetails_UNIQUE` (`id`),
  KEY `fk_details_invoice1_idx` (`invoice_id`),
  KEY `fk_details_product1_idx` (`product_id`),
  CONSTRAINT `fk_details_invoice1` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_details_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `details`
--

LOCK TABLES `details` WRITE;
/*!40000 ALTER TABLE `details` DISABLE KEYS */;
INSERT INTO `details` VALUES (1,73500.00,1,5,1),(2,79000.00,1,20,1),(3,24500.00,1,7,2),(4,38000.00,1,11,2),(5,15000.00,1,24,2),(6,10000.00,1,21,3),(7,5000.00,1,23,3),(8,15000.00,1,25,3),(9,28000.00,1,15,4),(10,57500.00,2,3,5),(11,180000.00,1,18,5),(12,99000.00,1,19,5),(13,24500.00,1,7,6),(14,35000.00,1,14,6),(15,99000.00,1,19,6),(16,67500.00,2,4,7),(17,24500.00,1,9,7),(18,15000.00,1,25,7),(19,82000.00,1,1,8),(20,24500.00,1,8,8),(21,24500.00,1,9,8),(22,79000.00,1,20,8),(23,24500.00,2,9,9),(24,28000.00,2,12,9),(25,35000.00,1,14,9),(26,10000.00,1,21,9),(27,28000.00,1,12,10),(28,161000.00,1,17,10),(29,15000.00,3,24,10);
/*!40000 ALTER TABLE `details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `total_price` decimal(10,2) DEFAULT NULL,
  `total_quantity` int DEFAULT NULL,
  `discount_price` decimal(10,2) DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `status_id` int NOT NULL,
  `shopping_id` int NOT NULL,
  `payment_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idinvoice_UNIQUE` (`id`),
  KEY `fk_invoice_status1_idx` (`status_id`),
  KEY `fk_invoice_shopping1_idx` (`shopping_id`),
  KEY `fk_invoice_payment1_idx` (`payment_id`),
  KEY `fk_invoice_user1_idx` (`user_id`),
  CONSTRAINT `fk_invoice_payment1` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_invoice_shopping1` FOREIGN KEY (`shopping_id`) REFERENCES `shopping` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_invoice_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_invoice_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,137250.00,2,15250.00,'2023-10-24 00:28:03',2,2,1,3),(2,69750.00,3,7750.00,'2022-10-24 00:28:38',2,2,2,3),(3,30000.00,3,0.00,'2021-11-24 00:28:59',2,2,1,3),(4,25200.00,1,2800.00,'2020-07-24 00:29:16',2,2,1,3),(5,394000.00,4,0.00,'2020-10-24 00:29:58',2,2,2,3),(6,158500.00,3,0.00,'2019-10-24 00:30:35',2,1,1,2),(7,174500.00,4,0.00,'2019-10-24 00:30:46',2,1,1,2),(8,210000.00,4,0.00,'2023-08-24 00:30:56',2,1,1,2),(9,150000.00,6,0.00,'2021-02-24 00:31:16',2,1,1,2),(10,234000.00,5,0.00,'2023-05-24 00:32:30',2,1,1,2);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idpayment_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,'PAYMENT_CASH'),(2,'PAYMENT_MOMO');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `price` decimal(10,2) DEFAULT NULL,
  `image` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `active` bit(1) DEFAULT b'1',
  `category_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idproduct_UNIQUE` (`id`),
  KEY `fk_product_category1_idx` (`category_id`),
  CONSTRAINT `fk_product_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Gà giòn (2 miếng)',82000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432286/food-store/rqkycskvf1o4jawbz6ak.png',_binary '',1),(2,'Gà tắm nước mắm',112500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432286/food-store/k62rfe78auc2yassltoc.jpg',_binary '',1),(3,'Gà không xương (3 miếng)',57500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432286/food-store/qolkqqbj2zdmmlxpja8r.jpg',_binary '',1),(4,'Gà không xương đặc biệt',67500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432286/food-store/u32ojxayjnvcmp3rqv5h.png',_binary '',1),(5,'Gà và hải sản',73500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432287/food-store/eyjvqqb4iovemg4fq76h.jpg',_binary '',1),(6,'Dasani',24500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432285/food-store/qoe7exstdy564xfbu9xk.jpg',_binary '',2),(7,'Coca Cola',24500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432285/food-store/tkontki6yw5t0k1uvlea.png',_binary '',2),(8,'Sprite',24500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432285/food-store/humow8rmluccoedh4bci.jpg',_binary '',2),(9,'Fanta',24500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432285/food-store/btaeppia11vgu0camp9i.jpg',_binary '',2),(10,'Coke Zero',24500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432286/food-store/f6u53kr7sscgdktx5vph.jpg',_binary '',2),(11,'Khoai tây chiên (Lớn)',38000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432287/food-store/kipcyivbgahal2vnxhim.jpg',_binary '',3),(12,'Popcorn sốt phô mai',28000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432287/food-store/g4mrm52drehhe8njcbeb.jpg',_binary '',3),(13,'Chả cá rong biển',37500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432288/food-store/cal6fmdualzmo4ib19qa.png',_binary '',3),(14,'Snack cá',35000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432289/food-store/kktbrpru3rjib4nbp7rt.jpg',_binary '',3),(15,'Donut tôm',28000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432287/food-store/fhjj7dum44ynschwqaod.jpg',_binary '',3),(16,'Mua 1 Tặng 1 - 2 Miếng gà + Nước',99000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432284/food-store/gzkosr4wamj51rpv7yj9.jpg',_binary '',4),(17,'Mua 1 Tặng 1 - 3 Miếng gà + 2 Nước',161000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432284/food-store/ypc0lpynaoyhhuml4c5s.jpg',_binary '',4),(18,'FLASH SALE MAYO B - NHẬP MÃ MAYOB',180000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432284/food-store/ox2ghp1a2ydhcyeh0u8t.png',_binary '',4),(19,'COMBO HAPPY MEAL 2 (GIÁ GỐC 150K)',99000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432284/food-store/drjaygrm1qcl5iynwftn.png',_binary '',4),(20,'COMBO HAPPY MEAL 1 - GIÁ GỐC 107K',79000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432284/food-store/ouz5mdkgngg4ddnmykk4.png',_binary '',4),(21,'Bánh xoài đào',10000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432284/food-store/yguaocqqmb5k3skep1rw.png',_binary '',5),(22,'Tropical Sundae',20000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432284/food-store/soxzrrtmt1jfdnjxqrgg.png',_binary '',5),(23,'Kem sữa tươi (Cúp)',5000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432285/food-store/ndfccz3imbbquqxdmogf.png',_binary '',5),(24,'Kem sundae dâu',15000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432285/food-store/tdf5nswfsycy7o1rtdyt.png',_binary '',5),(25,'Kem sundae socola',15000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432285/food-store/gpmiarcvvndmuwfvfwvg.png',_binary '',5);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `expired_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `user_id` int NOT NULL,
  `coupon_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idpromotion_UNIQUE` (`id`),
  KEY `fk_promotion_user1_idx` (`user_id`),
  KEY `fk_promotion_coupon1_idx` (`coupon_id`),
  CONSTRAINT `fk_promotion_coupon1` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_promotion_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
INSERT INTO `promotion` VALUES (1,'2023-10-24 00:26:04','2023-11-24 00:26:04',3,1);
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `user_id` int NOT NULL,
  `product_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idreview_UNIQUE` (`id`),
  KEY `fk_review_user1_idx` (`user_id`),
  KEY `fk_review_product1_idx` (`product_id`),
  CONSTRAINT `fk_review_product1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_review_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idrole_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_STAFF'),(3,'ROLE_CUSTOMER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopping`
--

DROP TABLE IF EXISTS `shopping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopping` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idshopping_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping`
--

LOCK TABLES `shopping` WRITE;
/*!40000 ALTER TABLE `shopping` DISABLE KEYS */;
INSERT INTO `shopping` VALUES (1,'SHOPPING_INSTORE'),(2,'SHOPPING_ONLINE');
/*!40000 ALTER TABLE `shopping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idstatus_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'STATUS_PENDING'),(2,'STATUS_PAID'),(3,'STATUS_CANCELED');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `last_name` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `email` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `address` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `password` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `avatar` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `iduser_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_user_role_idx` (`role_id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Tuyền','Đặng','dptuyen1@gmail.com','157/20 Phạm Văn Chiêu, phường 14, quận Gò Vấp','0932012306','admin','$2a$12$zx98MTNJesWJIqj3EOZx4.cAVHX6/MmHTpApTbCNYCGy/IXtbc/KW','https://res.cloudinary.com/dzbcst18v/image/upload/v1697432282/food-store/de4lcqdcsuc7kltij8kd.png','2023-10-04 12:12:19',1),(2,'Tuyền','Đặng','dptuyen1@gmail.com','157/20 Phạm Văn Chiêu, phường 14, quận Gò Vấp','0932012306','staff','$2a$12$zx98MTNJesWJIqj3EOZx4.cAVHX6/MmHTpApTbCNYCGy/IXtbc/KW','https://res.cloudinary.com/dzbcst18v/image/upload/v1697432282/food-store/o8mojndh7jusgo5c2yz6.png','2023-10-20 00:38:15',2),(3,'Tuyền','Đặng','dptuyen1@gmail.com','157/20 Phạm Văn Chiêu, phường 14, quận Gò Vấp','0932012306','customer','$2a$10$xjqiFNfq5BR.soKZb9TUJuCmwpXfhGPelrKPXNYP/RdlUAJ7cqVP6','https://res.cloudinary.com/dzbcst18v/image/upload/v1697432282/food-store/dx5prvnb76hb3okxj1xl.png','2023-10-20 09:00:47',3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-24  1:38:19
