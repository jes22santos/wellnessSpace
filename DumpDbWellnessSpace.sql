-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: dbwellness
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `booking_seq`
--

DROP TABLE IF EXISTS `booking_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_seq`
--

LOCK TABLES `booking_seq` WRITE;
/*!40000 ALTER TABLE `booking_seq` DISABLE KEYS */;
INSERT INTO `booking_seq` VALUES (10);
/*!40000 ALTER TABLE `booking_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `id_booking` int NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `notes` text,
  `id_customer` int NOT NULL,
  `id_staff` int NOT NULL,
  `id_treatment` int NOT NULL,
  `id_status` int NOT NULL,
  PRIMARY KEY (`id_booking`),
  KEY `id_customer` (`id_customer`),
  KEY `id_staff` (`id_staff`),
  KEY `id_treatment` (`id_treatment`),
  KEY `id_status` (`id_status`),
  CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`id_customer`) REFERENCES `customers` (`id_customer`),
  CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`id_staff`) REFERENCES `staffs` (`id_staff`),
  CONSTRAINT `bookings_ibfk_3` FOREIGN KEY (`id_treatment`) REFERENCES `treatments` (`id_treatment`),
  CONSTRAINT `bookings_ibfk_4` FOREIGN KEY (`id_status`) REFERENCES `status` (`id_status`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (1,'2020-08-04','10:00:00','Focus on my belly',1,1,9,4),(2,'2020-09-04','14:00:00','problems after marriage ',1,3,15,3),(3,'2020-09-04','14:00:00','',1,3,15,1),(4,'2020-08-07','12:00:00','Cellulite on my legs',1,1,12,3),(5,'2020-08-09','09:00:00','loose weights',1,1,9,1),(6,'2020-08-11','15:00:00','acne evaluation',1,2,1,1),(7,'2020-08-26','09:00:00','',1,2,1,1),(9,'2020-08-26','15:00:00','',1,2,2,1);
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id_category` int NOT NULL AUTO_INCREMENT,
  `n_category` varchar(30) NOT NULL,
  PRIMARY KEY (`id_category`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Body Treatments'),(2,'Facial Treatments'),(3,'Mental Treatments'),(4,'Massages');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_seq`
--

DROP TABLE IF EXISTS `customer_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_seq`
--

LOCK TABLES `customer_seq` WRITE;
/*!40000 ALTER TABLE `customer_seq` DISABLE KEYS */;
INSERT INTO `customer_seq` VALUES (5);
/*!40000 ALTER TABLE `customer_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id_customer` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `weight` double NOT NULL,
  `height` double NOT NULL,
  `birthday` date NOT NULL,
  `allergies` text,
  `id_user` int NOT NULL,
  `phone` varchar(20) NOT NULL,
  PRIMARY KEY (`id_customer`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Jessica','dos Santos',86.5,1.75,'1991-07-16','Prawns',1,'0833214242'),(2,'Ana Paula','Oliveira',75.3,1.68,'1990-12-02','',4,'0833214242'),(3,'Roberta','Aguiar',65.7,1.6,'1990-04-14','Dipirona',5,'0833214242'),(4,'Eoin','Halpin',93.4,1.74,'1980-06-26','',6,'0833214242');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (7);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prod_book`
--

DROP TABLE IF EXISTS `prod_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prod_book` (
  `id_prodBook` int NOT NULL AUTO_INCREMENT,
  `id_product` int NOT NULL,
  `id_booking` int NOT NULL,
  `quantity` int NOT NULL,
  `id_prod_book` int NOT NULL,
  PRIMARY KEY (`id_prodBook`),
  KEY `id_product` (`id_product`),
  KEY `id_booking` (`id_booking`),
  CONSTRAINT `prod_book_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`),
  CONSTRAINT `prod_book_ibfk_2` FOREIGN KEY (`id_booking`) REFERENCES `bookings` (`id_booking`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prod_book`
--

LOCK TABLES `prod_book` WRITE;
/*!40000 ALTER TABLE `prod_book` DISABLE KEYS */;
INSERT INTO `prod_book` VALUES (1,1,1,1,5),(2,2,1,1,6),(7,2,5,1,11),(13,1,5,1,13);
/*!40000 ALTER TABLE `prod_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prod_book_seq`
--

DROP TABLE IF EXISTS `prod_book_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prod_book_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prod_book_seq`
--

LOCK TABLES `prod_book_seq` WRITE;
/*!40000 ALTER TABLE `prod_book_seq` DISABLE KEYS */;
INSERT INTO `prod_book_seq` VALUES (14);
/*!40000 ALTER TABLE `prod_book_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_seq`
--

DROP TABLE IF EXISTS `product_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_seq`
--

LOCK TABLES `product_seq` WRITE;
/*!40000 ALTER TABLE `product_seq` DISABLE KEYS */;
INSERT INTO `product_seq` VALUES (4);
/*!40000 ALTER TABLE `product_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id_product` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `cost` double NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id_product`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Bioderma Body Lotion ',19.5,24),(2,'La Roche-Posay Hydrate Vitamin C',39.99,8),(3,'Hyalu Serum B5 La Roche Posay',38.5,15);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id_role` int NOT NULL AUTO_INCREMENT,
  `role` varchar(30) NOT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_seq`
--

DROP TABLE IF EXISTS `staff_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_seq`
--

LOCK TABLES `staff_seq` WRITE;
/*!40000 ALTER TABLE `staff_seq` DISABLE KEYS */;
INSERT INTO `staff_seq` VALUES (5);
/*!40000 ALTER TABLE `staff_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staffs`
--

DROP TABLE IF EXISTS `staffs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staffs` (
  `id_staff` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `credentiaL` varchar(100) NOT NULL,
  `id_user` int NOT NULL,
  `id_category` int NOT NULL,
  PRIMARY KEY (`id_staff`),
  KEY `id_user` (`id_user`),
  KEY `id_category` (`id_category`),
  CONSTRAINT `staffs_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`),
  CONSTRAINT `staffs_ibfk_2` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staffs`
--

LOCK TABLES `staffs` WRITE;
/*!40000 ALTER TABLE `staffs` DISABLE KEYS */;
INSERT INTO `staffs` VALUES (1,'Jessica','Halpin','CRE13882',2,1),(2,'Regiane','Junglos','CRE177488',3,2),(3,'Mayara','Camile','CRE177999',7,3),(4,'Beatriz','Souza','CRE177478',10,4);
/*!40000 ALTER TABLE `staffs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id_status` int NOT NULL AUTO_INCREMENT,
  `status` varchar(100) NOT NULL,
  PRIMARY KEY (`id_status`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Booked'),(2,'Needs Return'),(3,'Cancelled'),(4,'Concluded');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_seq`
--

DROP TABLE IF EXISTS `status_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_seq`
--

LOCK TABLES `status_seq` WRITE;
/*!40000 ALTER TABLE `status_seq` DISABLE KEYS */;
INSERT INTO `status_seq` VALUES (5);
/*!40000 ALTER TABLE `status_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_slot`
--

DROP TABLE IF EXISTS `time_slot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_slot` (
  `timeslot` time NOT NULL,
  PRIMARY KEY (`timeslot`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_slot`
--

LOCK TABLES `time_slot` WRITE;
/*!40000 ALTER TABLE `time_slot` DISABLE KEYS */;
/*!40000 ALTER TABLE `time_slot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timeslot`
--

DROP TABLE IF EXISTS `timeslot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timeslot` (
  `timeslot` time NOT NULL,
  PRIMARY KEY (`timeslot`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timeslot`
--

LOCK TABLES `timeslot` WRITE;
/*!40000 ALTER TABLE `timeslot` DISABLE KEYS */;
INSERT INTO `timeslot` VALUES ('09:00:00'),('10:00:00'),('11:00:00'),('12:00:00'),('14:00:00'),('15:00:00'),('16:00:00'),('17:00:00');
/*!40000 ALTER TABLE `timeslot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treat_seq`
--

DROP TABLE IF EXISTS `treat_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treat_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treat_seq`
--

LOCK TABLES `treat_seq` WRITE;
/*!40000 ALTER TABLE `treat_seq` DISABLE KEYS */;
INSERT INTO `treat_seq` VALUES (19);
/*!40000 ALTER TABLE `treat_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatments`
--

DROP TABLE IF EXISTS `treatments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treatments` (
  `id_treatment` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `cost` double NOT NULL,
  `duration` time NOT NULL,
  `details` text NOT NULL,
  `id_category` int DEFAULT NULL,
  `id` int NOT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_treatment`),
  KEY `id_category` (`id_category`),
  CONSTRAINT `treatments_ibfk_1` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatments`
--

LOCK TABLES `treatments` WRITE;
/*!40000 ALTER TABLE `treatments` DISABLE KEYS */;
INSERT INTO `treatments` VALUES (1,'Facial Evaluation',25,'00:30:00',' Evaluation for future treatment and budget of it.',2,0,NULL),(2,'Facial Deep Cleansing with peel',40,'01:30:00',' A deep cleansing facial includes: Skin analysis, Makeup removal, Skin cleansing, Physical Exfoliation, Steaming, Extractions, Calm mask, Tonic, Serum/moisturizer and Broad spectrum sun protection.',2,0,NULL),(3,'Acne Treatment',50,'01:00:00','Effective pioneering acne treatments using acids and peelings',2,0,NULL),(4,'Facial Lymphatic Drainage',50,'01:00:00','A lymphatic drainage facial is a soothing and relaxing massage using gentle but effective brushing motions to reduce swelling in the eye and neck area',2,0,NULL),(5,'Microneedling',165,'02:00:00','Microneedling works by stimulating the production of new collagen in the skin. This is achieved by puncturing the dermis with microscopic needles.',2,0,NULL),(6,'Facial Botox',200,'01:00:00','Facial Botox is a non- surgical skin rejuvenation procedure. It control expression lines and wrinkles',2,0,NULL),(7,'Relaxing Massage',50,'01:00:00','Traditional Relaxing Massage, also known as Swedish massage, is performed with oils and aromatherapy that incorporate relaxing essences.',4,0,NULL),(8,'Muscular Massage',50,'01:00:00','Massage to improve pain, stiff neck, bad form, among other injuries. Sports massage.',4,0,NULL),(9,'Classic Lymph Drainage',100,'01:00:00','Classic Lymphatic Drainage Massage is known to have a positive effect on people with fluid retention/puffiness (oedema) and also problematic skin (including cellulite).',1,0,NULL),(10,'Modeling Lymph Drainage',60,'01:00:00','Merges lymphatic drainage and modeling massage. The objective is to drain and model.',1,0,NULL),(11,'Lipofat',60,'01:00:00','Lipofat treatment is a fantastic tool in the fight against localized fat, cellulite and water retention. ',1,0,NULL),(12,'Cellulite Treatment',80,'01:00:00','The treatment to improve the factors that cause cellulite includes oxygenation, detox massages, liquid drainage.',1,0,NULL),(13,'Detrox Drainage',75,'01:00:00','It includes Detox with green clay and wrap with thermic blanket, the cuppings massage with the most localized fat accumulation sites, along with drainage',1,0,NULL),(14,'Enzymes treatments',150,'02:00:00','The enzymatic lipolysis treatment makes the localized fat to be broken and thus removed from the site naturally through metabolism.',1,0,NULL),(15,'Couple`s Counselling',150,'01:00:00','Activities and methods that help couples to find the emotional balance.',3,0,NULL),(16,'Individual Counselling',100,'01:00:00','Consultation to help control feelings of anger, stress, anxiety, loneliness, among others',3,0,NULL),(17,'Depression Treatment',100,'01:00:00','Intensive treatment to control and overcome depression through activities, conversation and medication.',3,0,NULL),(18,'Bullying',100,'01:00:00','Guidance on how to deal and react against bullying, and monitoring possible psychological damage.',3,0,NULL);
/*!40000 ALTER TABLE `treatments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_seq`
--

DROP TABLE IF EXISTS `user_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_seq`
--

LOCK TABLES `user_seq` WRITE;
/*!40000 ALTER TABLE `user_seq` DISABLE KEYS */;
INSERT INTO `user_seq` VALUES (11);
/*!40000 ALTER TABLE `user_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `id_role` int NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `constraint_username` (`username`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `fk_id_role` (`id_role`),
  CONSTRAINT `fk_id_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'jes22santos@gmail.com','$2a$10$LYFzoflseAE33Y1XtQsQNeE2Umzk77r7tU2D9F0tuIjmhLPvSOQMG',2),(2,'jessica_apdsantos22@yahoo.com.br','$2a$10$6iOm.PhMBpUXWCNluM9X.unmNC.FPj1vceLKAPdz/43JFAC8RevJC',1),(3,'rjunglosdemacedo@gmail.com','$2a$10$p03NjJNatXGg0PBIaSTnMOSbS/9SLQXYsgm.nSHn4g6nZgHBT/nQC',1),(4,'o.anapaulaalves@gmail.com','$2a$10$Go.uwdXIS9GLrMN4EbfKpeSC9fyPbvs5mNttiLZ2Z0COoHi8Afjd.',2),(5,'aguiar.rt@gmail.com','$2a$10$5ubT68.K96ngetyguY2wzu6h7MZWuv46iNeKuPhscd5tCrq7BgFI6',2),(6,'halpo_75@hotmail.com','$2a$10$1j7jt1qwqF5Wp41a0k006uBIMa.xxR8LYUc5g7E7U1n5IrhMc6Q.C',2),(7,'ma.camile@hotmail.com','$2a$10$MTNX30THcpxypKHNN1sHEeGGadzAyIIBrN75IxMFLOWg2wZgxraHu',1),(8,'jes22santos@outlook.com','$2a$10$xdnkeNRds2cG7M4BNyROPe2cYJejNlpafmiC16M4TCBgfKAQUmU1C',2),(9,'ana.garbelini@hotmail.com','$2a$10$AAFh2XW2MZEQL9xJnRtFTeqiUxJWjsb2sKUAd4.fdj..WMbVG9lw.',2),(10,'beatrizsouza_2004@hotmail.com','$2a$10$lrLl/wayBgFuaEzOoI8Fe.cTdGvYnv5IkNIbxIMBIg2pPgAO3bZ62',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'dbwellness'
--

--
-- Dumping routines for database 'dbwellness'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-15 21:22:48
