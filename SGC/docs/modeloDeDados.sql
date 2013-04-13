-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.16-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema sg_condominio
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ sg_condominio;
USE sg_condominio;

--
-- Table structure for table `sg_condominio`.`tbaluguel`
--

DROP TABLE IF EXISTS `tbaluguel`;
CREATE TABLE `tbaluguel` (
  `idaluguel` int(11) NOT NULL auto_increment,
  `data` date NOT NULL,
  `horainicio` time NOT NULL,
  `horafim` time NOT NULL,
  `idespaco` int(11) NOT NULL,
  `status` int(11) default NULL COMMENT '1 - Solicitacao de reserva\\n2 - Reservado\\n3 - Alugado\\n4 - Cancelado\\n\\n',
  PRIMARY KEY  (`idaluguel`),
  KEY `fk_tbaluguel_tbespacoalugavel1` (`idespaco`),
  CONSTRAINT `fk_tbaluguel_tbespacoalugavel1` FOREIGN KEY (`idespaco`) REFERENCES `tbespacoalugavel` (`idespaco`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg_condominio`.`tbaluguel`
--

/*!40000 ALTER TABLE `tbaluguel` DISABLE KEYS */;
INSERT INTO `tbaluguel` (`idaluguel`,`data`,`horainicio`,`horafim`,`idespaco`,`status`) VALUES 
 (29,'2099-02-02','08:00:00','10:00:00',1,1),
 (30,'2010-02-02','08:00:00','10:00:00',1,1),
 (32,'2030-12-12','00:00:00','13:00:00',1,1),
 (34,'2010-10-10','07:30:00','09:00:00',1,0),
 (35,'2010-10-10','07:30:00','09:00:00',1,0),
 (36,'2010-10-10','07:30:00','09:00:00',1,0),
 (37,'2010-10-10','07:29:00','09:00:00',1,0),
 (38,'2011-01-10','07:20:00','07:40:00',1,0),
 (39,'2013-10-22','05:00:00','21:30:00',1,0),
 (40,'2013-10-22','05:00:00','21:30:00',1,0);
/*!40000 ALTER TABLE `tbaluguel` ENABLE KEYS */;


--
-- Table structure for table `sg_condominio`.`tbandamentoservico`
--

DROP TABLE IF EXISTS `tbandamentoservico`;
CREATE TABLE `tbandamentoservico` (
  `idtbandamentoservico` int(11) NOT NULL auto_increment,
  `data` datetime default NULL,
  `descricao` text,
  `operacao` int(11) default NULL COMMENT '1 - abertura\\n2 - fechamento\\n3 - atribuido\\n',
  `idservico` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  PRIMARY KEY  (`idtbandamentoservico`),
  KEY `fk_tbandamentoservico_tbservico1` (`idservico`),
  KEY `fk_tbandamentoservico_tbusuario1` (`idusuario`),
  CONSTRAINT `fk_tbandamentoservico_tbservico1` FOREIGN KEY (`idservico`) REFERENCES `tbservico` (`idservico`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbandamentoservico_tbusuario1` FOREIGN KEY (`idusuario`) REFERENCES `tbusuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg_condominio`.`tbandamentoservico`
--

/*!40000 ALTER TABLE `tbandamentoservico` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbandamentoservico` ENABLE KEYS */;


--
-- Table structure for table `sg_condominio`.`tbapartamento`
--

DROP TABLE IF EXISTS `tbapartamento`;
CREATE TABLE `tbapartamento` (
  `idapartamento` int(11) NOT NULL auto_increment,
  `numero` varchar(5) NOT NULL,
  `descricao` text NOT NULL,
  `idtorre` int(11) NOT NULL,
  PRIMARY KEY  (`idapartamento`),
  KEY `fk_tbapartamento_tbtorre_idx` (`idtorre`),
  CONSTRAINT `fk_tbapartamento_tbtorre` FOREIGN KEY (`idtorre`) REFERENCES `tbtorre` (`idtorre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg_condominio`.`tbapartamento`
--

/*!40000 ALTER TABLE `tbapartamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbapartamento` ENABLE KEYS */;


--
-- Table structure for table `sg_condominio`.`tbespaco`
--

DROP TABLE IF EXISTS `tbespaco`;
CREATE TABLE `tbespaco` (
  `idespaco` int(11) NOT NULL auto_increment,
  `nome` varchar(45) NOT NULL,
  `descricao` text NOT NULL,
  `idtorre` int(11) NOT NULL,
  PRIMARY KEY  (`idespaco`),
  KEY `fk_tbespaco_tbtorre1_idx` (`idtorre`),
  CONSTRAINT `fk_tbespaco_tbtorre1` FOREIGN KEY (`idtorre`) REFERENCES `tbtorre` (`idtorre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg_condominio`.`tbespaco`
--

/*!40000 ALTER TABLE `tbespaco` DISABLE KEYS */;
INSERT INTO `tbespaco` (`idespaco`,`nome`,`descricao`,`idtorre`) VALUES 
 (1,'Piscina','Olimpica',1),
 (2,'Churrasqueira','Profissional',1);
/*!40000 ALTER TABLE `tbespaco` ENABLE KEYS */;


--
-- Table structure for table `sg_condominio`.`tbespacoalugavel`
--

DROP TABLE IF EXISTS `tbespacoalugavel`;
CREATE TABLE `tbespacoalugavel` (
  `descricao` varchar(45) default NULL,
  `qtdPessoas` int(11) default NULL,
  `valor` double default NULL,
  `idespaco` int(11) NOT NULL,
  PRIMARY KEY  (`idespaco`),
  CONSTRAINT `fk_tbespacoalugavel_tbespaco1` FOREIGN KEY (`idespaco`) REFERENCES `tbespaco` (`idespaco`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg_condominio`.`tbespacoalugavel`
--

/*!40000 ALTER TABLE `tbespacoalugavel` DISABLE KEYS */;
INSERT INTO `tbespacoalugavel` (`descricao`,`qtdPessoas`,`valor`,`idespaco`) VALUES 
 ('Olimpica',30,200,1),
 ('pROFISSIONAL',20,200,2);
/*!40000 ALTER TABLE `tbespacoalugavel` ENABLE KEYS */;


--
-- Table structure for table `sg_condominio`.`tbleitura`
--

DROP TABLE IF EXISTS `tbleitura`;
CREATE TABLE `tbleitura` (
  `idtbleitura` int(11) NOT NULL,
  `data` datetime default NULL,
  `leitura` int(11) default NULL,
  `idtbtipoleitura` int(11) NOT NULL,
  `idapartamento` int(11) NOT NULL,
  PRIMARY KEY  (`idtbleitura`),
  KEY `fk_tbleitura_tbtipoleitura1` (`idtbtipoleitura`),
  KEY `fk_tbleitura_tbapartamento1` (`idapartamento`),
  CONSTRAINT `fk_tbleitura_tbapartamento1` FOREIGN KEY (`idapartamento`) REFERENCES `tbapartamento` (`idapartamento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbleitura_tbtipoleitura1` FOREIGN KEY (`idtbtipoleitura`) REFERENCES `tbtipoleitura` (`idtbtipoleitura`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg_condominio`.`tbleitura`
--

/*!40000 ALTER TABLE `tbleitura` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbleitura` ENABLE KEYS */;


--
-- Table structure for table `sg_condominio`.`tbnivelusuario`
--

DROP TABLE IF EXISTS `tbnivelusuario`;
CREATE TABLE `tbnivelusuario` (
  `idnivelusuario` int(11) NOT NULL auto_increment,
  `nivel` varchar(45) NOT NULL,
  `descricao` text NOT NULL,
  PRIMARY KEY  (`idnivelusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg_condominio`.`tbnivelusuario`
--

/*!40000 ALTER TABLE `tbnivelusuario` DISABLE KEYS */;
INSERT INTO `tbnivelusuario` (`idnivelusuario`,`nivel`,`descricao`) VALUES 
 (1,'Administrador','O responsavel pelo sistema'),
 (2,'Morador','Responsavel pelo apartamento');
/*!40000 ALTER TABLE `tbnivelusuario` ENABLE KEYS */;


--
-- Table structure for table `sg_condominio`.`tbresponsaveisdoapartamento`
--

DROP TABLE IF EXISTS `tbresponsaveisdoapartamento`;
CREATE TABLE `tbresponsaveisdoapartamento` (
  `idapartamento` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  `idresponsaveisdoapartamento` int(11) NOT NULL,
  PRIMARY KEY  (`idresponsaveisdoapartamento`),
  KEY `fk_tbapartamento_has_tbusuario_tbusuario1_idx` (`idusuario`),
  KEY `fk_tbapartamento_has_tbusuario_tbapartamento1_idx` (`idapartamento`),
  CONSTRAINT `fk_tbapartamento_has_tbusuario_tbapartamento1` FOREIGN KEY (`idapartamento`) REFERENCES `tbapartamento` (`idapartamento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbapartamento_has_tbusuario_tbusuario1` FOREIGN KEY (`idusuario`) REFERENCES `tbusuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg_condominio`.`tbresponsaveisdoapartamento`
--

/*!40000 ALTER TABLE `tbresponsaveisdoapartamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbresponsaveisdoapartamento` ENABLE KEYS */;


--
-- Table structure for table `sg_condominio`.`tbservico`
--

DROP TABLE IF EXISTS `tbservico`;
CREATE TABLE `tbservico` (
  `idservico` int(11) NOT NULL,
  `descricao` varchar(45) NOT NULL,
  `idespaco` int(11) NOT NULL,
  `idtbtiposervico` int(11) NOT NULL,
  PRIMARY KEY  (`idservico`),
  KEY `fk_tbservico_tbespaco1_idx` (`idespaco`),
  KEY `fk_tbservico_tbtiposervico1` (`idtbtiposervico`),
  CONSTRAINT `fk_tbservico_tbespaco1` FOREIGN KEY (`idespaco`) REFERENCES `tbespaco` (`idespaco`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbservico_tbtiposervico1` FOREIGN KEY (`idtbtiposervico`) REFERENCES `tbtiposervico` (`idtbtiposervico`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg_condominio`.`tbservico`
--

/*!40000 ALTER TABLE `tbservico` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbservico` ENABLE KEYS */;


--
-- Table structure for table `sg_condominio`.`tbtipoleitura`
--

DROP TABLE IF EXISTS `tbtipoleitura`;
CREATE TABLE `tbtipoleitura` (
  `idtbtipoleitura` int(11) NOT NULL,
  `nome` varchar(45) default NULL,
  PRIMARY KEY  (`idtbtipoleitura`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg_condominio`.`tbtipoleitura`
--

/*!40000 ALTER TABLE `tbtipoleitura` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbtipoleitura` ENABLE KEYS */;


--
-- Table structure for table `sg_condominio`.`tbtiposervico`
--

DROP TABLE IF EXISTS `tbtiposervico`;
CREATE TABLE `tbtiposervico` (
  `idtbtiposervico` int(11) NOT NULL,
  `nome` varchar(100) default NULL,
  PRIMARY KEY  (`idtbtiposervico`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg_condominio`.`tbtiposervico`
--

/*!40000 ALTER TABLE `tbtiposervico` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbtiposervico` ENABLE KEYS */;


--
-- Table structure for table `sg_condominio`.`tbtorre`
--

DROP TABLE IF EXISTS `tbtorre`;
CREATE TABLE `tbtorre` (
  `idtorre` int(11) NOT NULL auto_increment,
  `nome` varchar(45) NOT NULL,
  `descricao` text NOT NULL,
  PRIMARY KEY  (`idtorre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg_condominio`.`tbtorre`
--

/*!40000 ALTER TABLE `tbtorre` DISABLE KEYS */;
INSERT INTO `tbtorre` (`idtorre`,`nome`,`descricao`) VALUES 
 (1,'Torre Princial','Nada'),
 (2,'Torre Secundária','outro');
/*!40000 ALTER TABLE `tbtorre` ENABLE KEYS */;


--
-- Table structure for table `sg_condominio`.`tbusuario`
--

DROP TABLE IF EXISTS `tbusuario`;
CREATE TABLE `tbusuario` (
  `idusuario` int(11) NOT NULL auto_increment,
  `cpf` varchar(11) NOT NULL,
  `senha` varchar(32) NOT NULL,
  `usuario` varchar(32) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `email` varchar(45) default NULL,
  `idnivelusuario` int(11) NOT NULL,
  PRIMARY KEY  (`idusuario`),
  KEY `fk_tbusuario_tbnivelusuario1_idx` (`idnivelusuario`),
  CONSTRAINT `fk_tbusuario_tbnivelusuario1` FOREIGN KEY (`idnivelusuario`) REFERENCES `tbnivelusuario` (`idnivelusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg_condominio`.`tbusuario`
--

/*!40000 ALTER TABLE `tbusuario` DISABLE KEYS */;
INSERT INTO `tbusuario` (`idusuario`,`cpf`,`senha`,`usuario`,`nome`,`email`,`idnivelusuario`) VALUES 
 (1,'545454','13','nome','Jao','jao@gmail.com',1);
/*!40000 ALTER TABLE `tbusuario` ENABLE KEYS */;


--
-- Table structure for table `sg_condominio`.`tbusuario_has_tbaluguel`
--

DROP TABLE IF EXISTS `tbusuario_has_tbaluguel`;
CREATE TABLE `tbusuario_has_tbaluguel` (
  `idusuario` int(11) NOT NULL,
  `idaluguel` int(11) NOT NULL,
  `datasolicitacao` datetime default NULL,
  `idalugueldoespacao` int(11) NOT NULL,
  PRIMARY KEY  (`idalugueldoespacao`),
  KEY `fk_tbusuario_has_tbaluguel_tbaluguel1` (`idaluguel`),
  KEY `fk_tbusuario_has_tbaluguel_tbusuario1` (`idusuario`),
  CONSTRAINT `fk_tbusuario_has_tbaluguel_tbaluguel1` FOREIGN KEY (`idaluguel`) REFERENCES `tbaluguel` (`idaluguel`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbusuario_has_tbaluguel_tbusuario1` FOREIGN KEY (`idusuario`) REFERENCES `tbusuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sg_condominio`.`tbusuario_has_tbaluguel`
--

/*!40000 ALTER TABLE `tbusuario_has_tbaluguel` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbusuario_has_tbaluguel` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
