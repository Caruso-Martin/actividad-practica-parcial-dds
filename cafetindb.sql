CREATE DATABASE  IF NOT EXISTS `cafetindb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cafetindb`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cafetindb
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `idMenu` int unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idMenu`),
  UNIQUE KEY `idMenu_UNIQUE` (`idMenu`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `idProducto` int unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `precio` decimal(26,2) unsigned NOT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  `cantidadDisponible` int unsigned NOT NULL,
  `idStockState` int unsigned NOT NULL,
  `idMenu` int unsigned NOT NULL,
  PRIMARY KEY (`idProducto`),
  UNIQUE KEY `idProducto_UNIQUE` (`idProducto`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  KEY `FK_idStockState_idx` (`idStockState`),
  KEY `FK_idMenu_idx` (`idMenu`),
  CONSTRAINT `FK_idMenu_Producto` FOREIGN KEY (`idMenu`) REFERENCES `menu` (`idMenu`),
  CONSTRAINT `FK_idStockState_Producto` FOREIGN KEY (`idStockState`) REFERENCES `stockstate` (`idStockState`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Espresso',200.00,'Esta preparacion de origen italiano se obtiene con una cafetera espresso. Se sirve poca cantidad de cafe en una taza chica con una caracteristica capa de crema en la superficie debido a la presion. Este cafe tiene un aroma y sabor intenso.',20,3,1),(2,'Macchiato',230.00,'Se trata de un cafe expreso al que se le agrega un poco de leche caliente o espuma de leche.',20,3,1),(3,'Cappucino',250.00,'Es un cafe expreso al que se le agrega leche con espuma, y se puede decorar con cacao o canela espolvoreados por encima. Se sirve en una taza grande y debe quedar el cafe, la leche y la espuma de leche a partes iguales, es decir, un tercio de la taza para cada uno. ',20,3,1),(4,'Latte',280.00,'Es un cafe expreso al que se le agrega leche caliente, siendo las proporciones de un tercio de cafe y dos tercios de leche, aunque pueden variar en funcion del pais.',20,3,1),(5,'Americano',300.00,'Es un cafe expreso al que se le agrega agua caliente y azucar, obteniendo asi un cafe mas suave. Esta bebida es muy popular en Venezuela.',20,3,1),(6,'Irlandes',400.00,'En una copa se echa whisky irlandes, se agrega azucar y cafe fuerte y caliente hasta dos centimetros por debajo del borde. Se remueve bien y se agrega muy despacio nata fria poco batida, que debe quedar flotando en el cafe. Se bebe sin remover mas.',20,3,1),(7,'Turco',500.00,'Este cafe es muy popular en Oriente Medio, norte de africa y los paises balcanicos. Se prepara hirviendo directamente el cafe molido en el agua hasta que adquiere consistencia de harina. Es un cafe muy concentrado y espeso que se sirve en tazas chicas.',20,3,1);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productoxpromocion`
--

DROP TABLE IF EXISTS `productoxpromocion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productoxpromocion` (
  `idProductoXPromocion` int unsigned NOT NULL AUTO_INCREMENT,
  `idProducto` int unsigned NOT NULL,
  `idPromocion` int unsigned NOT NULL,
  PRIMARY KEY (`idProductoXPromocion`),
  UNIQUE KEY `idProductoXPromocion_UNIQUE` (`idProductoXPromocion`),
  KEY `FK_idProducto_ProductoXPromocion_idx` (`idProducto`),
  KEY `FK_idPromocion_ProductoXPromocion_idx` (`idPromocion`),
  CONSTRAINT `FK_idProducto_ProductoXPromocion` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`idProducto`),
  CONSTRAINT `FK_idPromocion_ProductoXPromocion` FOREIGN KEY (`idPromocion`) REFERENCES `promociones` (`idPromocion`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productoxpromocion`
--

LOCK TABLES `productoxpromocion` WRITE;
/*!40000 ALTER TABLE `productoxpromocion` DISABLE KEYS */;
INSERT INTO `productoxpromocion` VALUES (1,1,1),(2,2,1),(3,3,2),(4,4,2),(5,5,3),(6,6,3),(7,6,4),(8,7,4);
/*!40000 ALTER TABLE `productoxpromocion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promociones`
--

DROP TABLE IF EXISTS `promociones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promociones` (
  `idPromocion` int unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(275) NOT NULL,
  `precio` decimal(26,2) unsigned NOT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  `cantidadDisponible` int unsigned NOT NULL,
  `idStockState` int unsigned NOT NULL,
  `idMenu` int unsigned NOT NULL,
  PRIMARY KEY (`idPromocion`),
  UNIQUE KEY `idPromocion_UNIQUE` (`idPromocion`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  KEY `FK_idStockState_idx` (`idStockState`),
  KEY `FK_idMenu_idx` (`idMenu`),
  CONSTRAINT `FK_idMenu_Promocion` FOREIGN KEY (`idMenu`) REFERENCES `menu` (`idMenu`),
  CONSTRAINT `FK_idStockState_Promocion` FOREIGN KEY (`idStockState`) REFERENCES `stockstate` (`idStockState`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promociones`
--

LOCK TABLES `promociones` WRITE;
/*!40000 ALTER TABLE `promociones` DISABLE KEYS */;
INSERT INTO `promociones` VALUES (1,'Espresso + Macchiato',387.00,'Espresso + Macchiato',20,3,1),(2,'Cappucino + Latte',477.00,'Cappucino + Latte',20,3,1),(3,'Americano + Irlandes',630.00,'Americano + Irlandes',20,3,1),(4,'Irlandes + Turco',810.00,'Irlandes + Turco',20,3,1);
/*!40000 ALTER TABLE `promociones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stockstate`
--

DROP TABLE IF EXISTS `stockstate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stockstate` (
  `idStockState` int unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(25) NOT NULL,
  PRIMARY KEY (`idStockState`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  UNIQUE KEY `idStockState_UNIQUE` (`idStockState`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stockstate`
--

LOCK TABLES `stockstate` WRITE;
/*!40000 ALTER TABLE `stockstate` DISABLE KEYS */;
INSERT INTO `stockstate` VALUES (2,'Insuficiente'),(3,'Suficiente'),(1,'Vacio');
/*!40000 ALTER TABLE `stockstate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tienda`
--

DROP TABLE IF EXISTS `tienda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tienda` (
  `idTienda` int unsigned NOT NULL AUTO_INCREMENT,
  `montoTotalRecaudado` decimal(26,2) unsigned NOT NULL,
  `ubicacion` varchar(100) NOT NULL,
  `idMenu` int unsigned NOT NULL,
  PRIMARY KEY (`idTienda`),
  UNIQUE KEY `idTienda_UNIQUE` (`idTienda`),
  KEY `FK_idMenu_Tienda_idx` (`idMenu`),
  KEY `FK_idMenu_Tienda` (`idMenu`),
  CONSTRAINT `FK_idMenu_Tienda` FOREIGN KEY (`idMenu`) REFERENCES `menu` (`idMenu`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tienda`
--

LOCK TABLES `tienda` WRITE;
/*!40000 ALTER TABLE `tienda` DISABLE KEYS */;
INSERT INTO `tienda` VALUES (1,0.00,'Honduras 4881',1);
/*!40000 ALTER TABLE `tienda` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-16 16:33:23
