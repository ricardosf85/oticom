-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.41-3ubuntu12.10


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema bdcrud
--

CREATE DATABASE IF NOT EXISTS bdcrud;
USE bdcrud;

--
-- Definition of table `bdcrud`.`cliente`
--

DROP TABLE IF EXISTS `bdcrud`.`cliente`;
CREATE TABLE  `bdcrud`.`cliente` (
  `id_cliente` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `clt_nome` varchar(45) DEFAULT NULL,
  `clt_email` varchar(45) DEFAULT NULL,
  `clt_dataNasc` date DEFAULT NULL,
  `clt_telefone` varchar(20) DEFAULT NULL,
  `clt_sexo` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=MyISAM AUTO_INCREMENT=165 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bdcrud`.`cliente`
--

/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
LOCK TABLES `cliente` WRITE;
INSERT INTO `bdcrud`.`cliente` VALUES  (98,'ddsadsad','sadsada',NULL,'(213)132-1313','M'),
 (99,'ewqeqe','ewqeqe',NULL,NULL,'M'),
 (100,'ewqeqe','ewqeqe',NULL,NULL,'M'),
 (101,'ewqeqe','ewqeqe',NULL,NULL,'M'),
 (102,'ewqe','ewqe',NULL,NULL,'M'),
 (103,'ewqe','ewqe',NULL,NULL,'M'),
 (104,'EDWE','SDASD',NULL,NULL,'F'),
 (105,'EDWE','SDASD',NULL,NULL,'F'),
 (106,'EDWE','SDASD',NULL,NULL,'F'),
 (107,'ewqe','ewqe',NULL,NULL,'M'),
 (108,'rewr','tretet',NULL,NULL,'M'),
 (109,'rewr','tretet',NULL,NULL,'M'),
 (110,'ewqe','wqeqe',NULL,NULL,'M'),
 (111,'eqwew','ewqeq',NULL,NULL,'M'),
 (112,NULL,NULL,NULL,NULL,NULL),
 (113,NULL,NULL,NULL,NULL,NULL),
 (114,NULL,NULL,NULL,NULL,NULL),
 (115,'dsda','sadas',NULL,NULL,'M'),
 (116,'dasdas','dasdasd',NULL,NULL,'M'),
 (117,'dasdsa','adasdas',NULL,NULL,'M'),
 (118,'dasdsa','adasdas',NULL,NULL,'M'),
 (119,'dasdsa','adasdas',NULL,NULL,'M'),
 (120,'ewe','eweq',NULL,NULL,'M'),
 (121,'DASD','DSAD',NULL,NULL,'M'),
 (122,'wewqe','ewqeqwe',NULL,NULL,'M'),
 (123,'ddsdasd','dsad',NULL,NULL,'M'),
 (124,'ewqewqe','eqwewqe',NULL,NULL,'M'),
 (125,'wewqe','ewqeq',NULL,NULL,'M'),
 (126,'','',NULL,'',NULL),
 (127,'','',NULL,'',NULL),
 (128,'','',NULL,'',NULL),
 (129,'ewqeqe','eqwewqe',NULL,NULL,'M'),
 (130,'DSADAS','ASDASD',NULL,NULL,'M'),
 (131,'EWEWQ','EWQEQE',NULL,NULL,'M'),
 (132,'ADSADASD','EWQEWQEWQ',NULL,NULL,'M'),
 (133,'MARCIO CORREIA DE OLIVEIRA','marcioinfomvs@gmail.com',NULL,NULL,'M'),
 (134,'desaasd','adasdasd',NULL,NULL,'M'),
 (135,'dasdda',NULL,NULL,NULL,NULL),
 (136,'qeqweqe',NULL,NULL,NULL,NULL),
 (137,'eweq',NULL,NULL,NULL,NULL),
 (138,'dasdasd',NULL,NULL,NULL,NULL),
 (139,'dsadsa',NULL,NULL,NULL,NULL),
 (140,'dsada',NULL,NULL,NULL,NULL),
 (141,'dsada',NULL,NULL,NULL,NULL),
 (142,'dsada',NULL,NULL,NULL,NULL),
 (143,'dsada',NULL,NULL,NULL,NULL),
 (144,'dsada',NULL,NULL,NULL,NULL),
 (145,'dsadad',NULL,NULL,NULL,NULL),
 (146,'dasdas',NULL,NULL,NULL,NULL),
 (147,'obrigado!','vidafewrwer',NULL,NULL,'F'),
 (148,'dasdas',NULL,NULL,NULL,NULL),
 (149,'malvina correia','mvinal2004@yahoo.com.br',NULL,NULL,'M'),
 (150,'silvani rosa','silvani_df@hotmail.com',NULL,NULL,'F'),
 (151,'Ricardo Farias','ricardo_farias@gmail.com',NULL,'(061)3562-3176','M'),
 (152,'ASDASD','ASDASD',NULL,'(312)3123-1231','M'),
 (153,'jesus e senhor','amaraoproximo@deus.com.br',NULL,'(888)8888-8888','M'),
 (154,'kokin','kokin@gmail.com',NULL,'(123)1231-2312','M'),
 (155,'obrigado senhor ','dfafdsafsf',NULL,'(312)3123-1231','M'),
 (156,'dsadas',NULL,NULL,NULL,NULL),
 (157,'dasda','dasdasd',NULL,'(123)2131-2312','M'),
 (158,'dsADAS','EWQEQWE',NULL,'(312)3123-1231','M'),
 (159,'asaSA','EWEQE',NULL,'(321)3123-1232','M'),
 (160,'312312312312312','ewqeqweqweq',NULL,'(312)3123-1231','M'),
 (161,'dasdasde','efewrferewrwer',NULL,'(213)1231-2312','M'),
 (162,'dsadsa','dasdasd',NULL,'(321)3123-1231','M'),
 (163,'silvani rosa e marcus rosa','marcussilvaini@gmail.com',NULL,'(313)1231-2312','F'),
 (164,'deu certo ate que enfim','marciosucesso@gmail.com',NULL,'(131)2312-3123','M');
UNLOCK TABLES;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;


--
-- Definition of table `bdcrud`.`perfil`
--

DROP TABLE IF EXISTS `bdcrud`.`perfil`;
CREATE TABLE  `bdcrud`.`perfil` (
  `id_perfil` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_perfil`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bdcrud`.`perfil`
--

/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
LOCK TABLES `perfil` WRITE;
INSERT INTO `bdcrud`.`perfil` VALUES  (1,'ROLE_ADM'),
 (2,'ROLE_USER');
UNLOCK TABLES;
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;


--
-- Definition of table `bdcrud`.`produto`
--

DROP TABLE IF EXISTS `bdcrud`.`produto`;
CREATE TABLE  `bdcrud`.`produto` (
  `id_produto` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_cliente` int(10) unsigned NOT NULL,
  `pdt_nome` varchar(45) DEFAULT NULL,
  `pdt_quantd` int(10) unsigned DEFAULT NULL,
  `pdt_prateleira` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_produto`),
  KEY `produto_FKIndex1` (`id_cliente`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bdcrud`.`produto`
--

/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
LOCK TABLES `produto` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;


--
-- Definition of table `bdcrud`.`usuario`
--

DROP TABLE IF EXISTS `bdcrud`.`usuario`;
CREATE TABLE  `bdcrud`.`usuario` (
  `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bdcrud`.`usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
LOCK TABLES `usuario` WRITE;
INSERT INTO `bdcrud`.`usuario` VALUES  (1,0x01,'admin','12345'),
 (2,0x01,'user','12345');
UNLOCK TABLES;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;


--
-- Definition of table `bdcrud`.`usuario_perfil`
--

DROP TABLE IF EXISTS `bdcrud`.`usuario_perfil`;
CREATE TABLE  `bdcrud`.`usuario_perfil` (
  `id_perfil` bigint(20) NOT NULL,
  `id_usuario` bigint(20) NOT NULL,
  KEY `FK57CD23FD9DB9E69B` (`id_perfil`),
  KEY `FK57CD23FD5A9933B9` (`id_usuario`),
  KEY `FK57CD23FD67F47FA6` (`id_perfil`),
  KEY `FK57CD23FDD7B1BC0E` (`id_usuario`),
  KEY `FK57CD23FD28906DBF` (`id_perfil`),
  KEY `FK57CD23FD2A939115` (`id_usuario`),
  CONSTRAINT `FK57CD23FD28906DBF` FOREIGN KEY (`id_perfil`) REFERENCES `perfil` (`id_perfil`),
  CONSTRAINT `FK57CD23FD2A939115` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `FK57CD23FD5A9933B9` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `FK57CD23FD67F47FA6` FOREIGN KEY (`id_perfil`) REFERENCES `perfil` (`id_perfil`),
  CONSTRAINT `FK57CD23FD9DB9E69B` FOREIGN KEY (`id_perfil`) REFERENCES `perfil` (`id_perfil`),
  CONSTRAINT `FK57CD23FDD7B1BC0E` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bdcrud`.`usuario_perfil`
--

/*!40000 ALTER TABLE `usuario_perfil` DISABLE KEYS */;
LOCK TABLES `usuario_perfil` WRITE;
INSERT INTO `bdcrud`.`usuario_perfil` VALUES  (1,1),
 (2,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `usuario_perfil` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
