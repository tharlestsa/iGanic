-- MySQL Script generated by MySQL Workbench
-- Ter 28 Nov 2017 13:51:46 BRST
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema iGanic
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema iGanic
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `iGanic` DEFAULT CHARACTER SET utf8 ;
USE `iGanic` ;

-- -----------------------------------------------------
-- Table `iGanic`.`Estados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iGanic`.`Estados` (
  `idEstado` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NULL,
  `uf` VARCHAR(2) NULL,
  PRIMARY KEY (`idEstado`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `iGanic`.`Cidades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iGanic`.`Cidades` (
  `idCidade` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(250) NULL,
  `idEstado` INT NOT NULL,
  PRIMARY KEY (`idCidade`, `idEstado`),
  INDEX `fk_Cidades_Estados1_idx` (`idEstado` ASC),
  CONSTRAINT `fk_Cidades_Estados1`
    FOREIGN KEY (`idEstado`)
    REFERENCES `iGanic`.`Estados` (`idEstado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `iGanic`.`Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iGanic`.`Usuarios` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NOT NULL,
  `cpf` VARCHAR(20) NOT NULL,
  `cel` VARCHAR(15) NOT NULL,
  `email` VARCHAR(70) NOT NULL,
  `endereco` VARCHAR(160) NOT NULL,
  `lat` VARCHAR(20) NULL,
  `lng` VARCHAR(20) NULL,
  `tipo` ENUM('C', 'F') NOT NULL,
  `usuario` VARCHAR(20) NOT NULL,
  `senha` VARCHAR(15) NOT NULL,
  `idCidade` INT NOT NULL,
  PRIMARY KEY (`idUsuario`, `idCidade`),
  UNIQUE INDEX `rg_UNIQUE` (`cpf` ASC),
  UNIQUE INDEX `usuario_UNIQUE` (`usuario` ASC),
  INDEX `fk_Usuarios_Cidades1_idx` (`idCidade` ASC),
  CONSTRAINT `fk_Usuarios_Cidades1`
    FOREIGN KEY (`idCidade`)
    REFERENCES `iGanic`.`Cidades` (`idCidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `iGanic`.`Produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iGanic`.`Produtos` (
  `idProduto` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(80) NOT NULL,
  `unidade` VARCHAR(2) NOT NULL,
  `preco` DOUBLE NOT NULL,
  `quantidade` DOUBLE NOT NULL,
  `modoProducao` VARCHAR(200) NOT NULL,
  `idUsuario` INT NOT NULL,
  PRIMARY KEY (`idProduto`),
  INDEX `fk_Produtos_Usuarios1_idx` (`idUsuario` ASC),
  CONSTRAINT `fk_Produtos_Usuarios1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `iGanic`.`Usuarios` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `iGanic`.`Imagens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iGanic`.`Imagens` (
  `idImagem` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(40) NOT NULL,
  `idProduto` INT NOT NULL,
  PRIMARY KEY (`idImagem`, `idProduto`),
  INDEX `fk_Imagens_Produtos1_idx` (`idProduto` ASC),
  CONSTRAINT `fk_Imagens_Produtos1`
    FOREIGN KEY (`idProduto`)
    REFERENCES `iGanic`.`Produtos` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `iGanic`.`Pedidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iGanic`.`Pedidos` (
  `idPedido` INT NOT NULL AUTO_INCREMENT,
  `data` DATETIME NOT NULL,
  `quantidade` FLOAT NOT NULL,
  `status` ENUM('A', 'C', 'F') NOT NULL COMMENT 'A - Andamento\nC - Cancelado\nF - Finalizado',
  `idUsuario` INT NOT NULL,
  `idProduto` INT NOT NULL,
  PRIMARY KEY (`idPedido`, `idUsuario`),
  INDEX `fk_Pedidos_Usuarios1_idx` (`idUsuario` ASC),
  INDEX `fk_Pedidos_Produtos1_idx` (`idProduto` ASC),
  CONSTRAINT `fk_Pedidos_Usuarios1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `iGanic`.`Usuarios` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedidos_Produtos1`
    FOREIGN KEY (`idProduto`)
    REFERENCES `iGanic`.`Produtos` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `iGanic`.`Promocoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iGanic`.`Promocoes` (
  `idPromocao` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(200) NOT NULL,
  `dataInicial` DATE NOT NULL,
  `dataFinal` DATE NOT NULL,
  `idProduto` INT NOT NULL,
  PRIMARY KEY (`idPromocao`, `idProduto`),
  INDEX `fk_Promocoes_Produtos1_idx` (`idProduto` ASC),
  CONSTRAINT `fk_Promocoes_Produtos1`
    FOREIGN KEY (`idProduto`)
    REFERENCES `iGanic`.`Produtos` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `iGanic`.`Avaliacoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iGanic`.`Avaliacoes` (
  `idAvaliacao` INT NOT NULL AUTO_INCREMENT,
  `nota` INT NOT NULL,
  `comentario` VARCHAR(200) NULL,
  `idProduto` INT NOT NULL,
  PRIMARY KEY (`idAvaliacao`),
  INDEX `fk_Avaliacoes_Produtos1_idx` (`idProduto` ASC),
  CONSTRAINT `fk_Avaliacoes_Produtos1`
    FOREIGN KEY (`idProduto`)
    REFERENCES `iGanic`.`Produtos` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
