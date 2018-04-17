CREATE DATABASE  IF NOT EXISTS `nargilemag` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `nargilemag`;
-- MySQL dump 10.13  Distrib 5.5.59, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: nargilemag
-- ------------------------------------------------------
-- Server version	5.5.59-0ubuntu0.14.04.1

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `categories_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_categories_categories1_idx` (`categories_id`),
  CONSTRAINT `fk_categories_categories1` FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Hookah',NULL),(2,'Tobacco',NULL),(3,'Box of cubes',NULL),(4,'Small hookah',1),(5,'Medium Hookah',1),(6,'Big hookah',1),(7,'Mint',2),(8,'Orange',2),(9,'Blueberry',2),(10,'Low Tier',3),(11,'Mid Tier',3),(12,'High Tier',3);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `characteristics`
--

DROP TABLE IF EXISTS `characteristics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `characteristics` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `unit` varchar(45) DEFAULT NULL,
  `categories_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`categories_id`),
  KEY `fk_characteristics_categories1_idx` (`categories_id`),
  CONSTRAINT `fk_characteristics_categories1` FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characteristics`
--

LOCK TABLES `characteristics` WRITE;
/*!40000 ALTER TABLE `characteristics` DISABLE KEYS */;
INSERT INTO `characteristics` VALUES (1,'height','cm',1),(2,'weight','gr',2),(3,'number',NULL,3);
/*!40000 ALTER TABLE `characteristics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite_products`
--

DROP TABLE IF EXISTS `favorite_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite_products` (
  `users_id` int(50) unsigned NOT NULL,
  `product_id` int(50) NOT NULL,
  PRIMARY KEY (`users_id`,`product_id`),
  KEY `fk_users_has_product_product1_idx` (`product_id`),
  KEY `fk_users_has_product_users1_idx` (`users_id`),
  CONSTRAINT `fk_users_has_product_product1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_product_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_products`
--

LOCK TABLES `favorite_products` WRITE;
/*!40000 ALTER TABLE `favorite_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorite_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `date_of_issue` date NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone_number` varchar(10) NOT NULL,
  `users_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orders_users1_idx` (`users_id`),
  CONSTRAINT `fk_orders_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_has_products`
--

DROP TABLE IF EXISTS `orders_has_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders_has_products` (
  `orders_id` int(50) NOT NULL,
  `products_id` int(50) NOT NULL,
  PRIMARY KEY (`orders_id`,`products_id`),
  KEY `fk_orders_has_products_products1_idx` (`products_id`),
  KEY `fk_orders_has_products_orders1_idx` (`orders_id`),
  CONSTRAINT `fk_orders_has_products_orders1` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_has_products_products1` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_has_products`
--

LOCK TABLES `orders_has_products` WRITE;
/*!40000 ALTER TABLE `orders_has_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders_has_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_ratings`
--

DROP TABLE IF EXISTS `product_ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_ratings` (
  `users_id` int(50) unsigned NOT NULL,
  `products_id` int(50) NOT NULL,
  `rating` int(1) NOT NULL,
  PRIMARY KEY (`users_id`,`products_id`),
  KEY `fk_users_has_products_products1_idx` (`products_id`),
  KEY `fk_users_has_products_users1_idx` (`users_id`),
  CONSTRAINT `fk_users_has_products_products1` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_products_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_ratings`
--

LOCK TABLES `product_ratings` WRITE;
/*!40000 ALTER TABLE `product_ratings` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(350) DEFAULT NULL,
  `price` double unsigned NOT NULL,
  `ammount_in_stock` int(10) NOT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`category_id`),
  KEY `fk_product_category_idx` (`category_id`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products_have_characteristics`
--

DROP TABLE IF EXISTS `products_have_characteristics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products_have_characteristics` (
  `products_id` int(50) NOT NULL,
  `characteristics_id` int(10) NOT NULL,
  `value` varchar(200) NOT NULL,
  PRIMARY KEY (`products_id`,`characteristics_id`),
  KEY `fk_products_has_characteristics_characteristics1_idx` (`characteristics_id`),
  KEY `fk_products_has_characteristics_products1_idx` (`products_id`),
  CONSTRAINT `fk_products_has_characteristics_characteristics1` FOREIGN KEY (`characteristics_id`) REFERENCES `characteristics` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_products_has_characteristics_products1` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products_have_characteristics`
--

LOCK TABLES `products_have_characteristics` WRITE;
/*!40000 ALTER TABLE `products_have_characteristics` DISABLE KEYS */;
/*!40000 ALTER TABLE `products_have_characteristics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(50) unsigned NOT NULL,
  `username` varchar(16) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `is_admin` tinyint(4) NOT NULL,
  `sex` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-17 21:05:34
