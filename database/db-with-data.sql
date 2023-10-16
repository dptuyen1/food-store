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
INSERT INTO `category` VALUES (1,'Thức ăn'),(2,'Nước uống'),(3,'Ăn vặt'),(4,'Combo'),(5,'Tráng miệng');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `details`
--

LOCK TABLES `details` WRITE;
/*!40000 ALTER TABLE `details` DISABLE KEYS */;
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
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idinvoice_UNIQUE` (`id`),
  KEY `fk_invoice_shopping1_idx` (`shopping_id`) /*!80000 INVISIBLE */,
  KEY `fk_invoice_user1_idx` (`user_id`) /*!80000 INVISIBLE */,
  KEY `fk_invoice_status1_idx` (`status_id`),
  CONSTRAINT `fk_invoice_shopping1` FOREIGN KEY (`shopping_id`) REFERENCES `shopping` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_invoice_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_invoice_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (54,'Gà giòn (2 miếng)',82000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432286/food-store/rqkycskvf1o4jawbz6ak.png',_binary '',1),(55,'Gà tắm nước mắm',112500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432286/food-store/k62rfe78auc2yassltoc.jpg',_binary '',1),(56,'Gà không xương (3 miếng)',57500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432286/food-store/qolkqqbj2zdmmlxpja8r.jpg',_binary '',1),(57,'Gà không xương đặc biệt',67500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432286/food-store/u32ojxayjnvcmp3rqv5h.png',_binary '',1),(58,'Gà và hải sản',73500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432287/food-store/eyjvqqb4iovemg4fq76h.jpg',_binary '',1),(59,'Dasani',24500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432285/food-store/qoe7exstdy564xfbu9xk.jpg',_binary '',2),(60,'Coca Cola',24500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432285/food-store/tkontki6yw5t0k1uvlea.png',_binary '',2),(61,'Sprite',24500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432285/food-store/humow8rmluccoedh4bci.jpg',_binary '',2),(62,'Fanta',24500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432285/food-store/btaeppia11vgu0camp9i.jpg',_binary '',2),(63,'Coke Zero',24500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432286/food-store/f6u53kr7sscgdktx5vph.jpg',_binary '',2),(64,'Khoai tây chiên (Lớn)',38000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432287/food-store/kipcyivbgahal2vnxhim.jpg',_binary '',3),(65,'Popcorn sốt phô mai',28000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432287/food-store/g4mrm52drehhe8njcbeb.jpg',_binary '',3),(66,'Chả cá rong biển',37500.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432288/food-store/cal6fmdualzmo4ib19qa.png',_binary '',3),(67,'Snack cá',35000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432289/food-store/kktbrpru3rjib4nbp7rt.jpg',_binary '',3),(68,'Donut tôm',28000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432287/food-store/fhjj7dum44ynschwqaod.jpg',_binary '',3),(69,'Mua 1 Tặng 1 - 2 Miếng gà + Nước',99000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432284/food-store/gzkosr4wamj51rpv7yj9.jpg',_binary '',4),(70,'Mua 1 Tặng 1 - 3 Miếng gà + 2 Nước',161000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432284/food-store/ypc0lpynaoyhhuml4c5s.jpg',_binary '',4),(71,'FLASH SALE MAYO B - NHẬP MÃ \"MAYOB\"',180000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432284/food-store/ox2ghp1a2ydhcyeh0u8t.png',_binary '',4),(72,'COMBO HAPPY MEAL 2 (GIÁ GỐC 150K)',99000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432284/food-store/drjaygrm1qcl5iynwftn.png',_binary '',4),(73,'COMBO HAPPY MEAL 1 - GIÁ GỐC 107K',79000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432284/food-store/ouz5mdkgngg4ddnmykk4.png',_binary '',4),(74,'Bánh xoài đào',10000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432284/food-store/yguaocqqmb5k3skep1rw.png',_binary '',5),(75,'Tropical Sundae',20000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432284/food-store/soxzrrtmt1jfdnjxqrgg.png',_binary '',5),(76,'Kem sữa tươi (Cúp)',5000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432285/food-store/ndfccz3imbbquqxdmogf.png',_binary '',5),(77,'Kem sundae dâu',15000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432285/food-store/tdf5nswfsycy7o1rtdyt.png',_binary '',5),(78,'Kem sundae socola',15000.00,'https://res.cloudinary.com/dzbcst18v/image/upload/v1697432285/food-store/gpmiarcvvndmuwfvfwvg.png',_binary '',5);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
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
  `name` varchar(45) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idstatus_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'STATUS_PENDING'),(2,'STATUS_PAID');
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
INSERT INTO `user` VALUES (1,'Tuyền','Đặng','dptuyen1@gmail.com','Gò Vấp','0932012306','admin','$2a$12$Z6YFEAR70t3tCZbMs3qvxun2rAdlYBlGpMs0IbMYPv8K3va59bZqi','https://res.cloudinary.com/dzbcst18v/image/upload/v1697432282/food-store/de4lcqdcsuc7kltij8kd.png','2023-10-16 11:45:33',1),(2,'Tuyền','Đặng','dptuyen1@gmail.com','Gò Vấp','0932012306','staff','$2a$12$Z6YFEAR70t3tCZbMs3qvxun2rAdlYBlGpMs0IbMYPv8K3va59bZqi','https://res.cloudinary.com/dzbcst18v/image/upload/v1697432282/food-store/o8mojndh7jusgo5c2yz6.png','2023-10-16 12:01:35',2),(3,'Tuyền','Đặng','dptuyen1@gmail.com','Gò Vấp','0932012306','customer','$2a$12$Z6YFEAR70t3tCZbMs3qvxun2rAdlYBlGpMs0IbMYPv8K3va59bZqi','https://res.cloudinary.com/dzbcst18v/image/upload/v1697432282/food-store/dx5prvnb76hb3okxj1xl.png','2023-10-16 12:01:35',3);
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

-- Dump completed on 2023-10-16 12:35:48
