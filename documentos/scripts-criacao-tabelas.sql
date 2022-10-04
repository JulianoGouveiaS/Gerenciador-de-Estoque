create database if not exists `almoxarifado`;
DROP TABLE IF EXISTS `almoxarifado`.`tmovimentacao` ;
DROP TABLE IF EXISTS `almoxarifado`.`tservico` ;
DROP TABLE IF EXISTS `almoxarifado`.`tproduto` ;
DROP TABLE IF EXISTS `almoxarifado`.`tcliente` ;
DROP TABLE IF EXISTS `almoxarifado`.`tusuario` ;
-- -----------------------------------------------------
-- Table `almoxarifado`.`tusuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `almoxarifado`.`tusuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(150) NULL,
  `login` VARCHAR(50) NULL,
  `senha` VARCHAR(255) NULL,
  `tipo` VARCHAR(50) NULL COMMENT 'ADMINISTRADOR ou OPERADOR',
  `ativo` TINYINT NULL,
  `dataCadastro` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `almoxarifado`.`tcliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `almoxarifado`.`tcliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NULL,
  `cpfCnpj` VARCHAR(14) NULL,
  `telefone` VARCHAR(30) NULL,
  `dataCadastro` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `almoxarifado`.`tproduto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `almoxarifado`.`tproduto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(255) NULL,
  `dataValidade` DATE NULL,
  `dataCadastro` DATETIME NULL,
  `dataUltimaAlteracao` DATETIME NULL,
  `observacao` VARCHAR(255) NULL,
  `usuarioAlteracao` INT NOT NULL COMMENT 'Usuário que realizou a última alteração no cadastro do produto',
  `quantidadeEstoque` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_produto_usuario_alteracao`
    FOREIGN KEY (`usuarioAlteracao`)
    REFERENCES `almoxarifado`.`tusuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `almoxarifado`.`tservico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `almoxarifado`.`tservico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario` INT NULL COMMENT 'Usuário que criou o registro',
  `data` DATETIME NULL COMMENT 'Data que o serviço foi feito',
  `cliente` INT NULL COMMENT 'Cliente para qual o serviço foi prestado',
  `tipo` VARCHAR(45) NULL COMMENT 'VENDA, COMPRA',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_servico_usuario`
    FOREIGN KEY (`usuario`)
    REFERENCES `almoxarifado`.`tusuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_servico_cliente`
    FOREIGN KEY (`cliente`)
    REFERENCES `almoxarifado`.`tcliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `almoxarifado`.`tmovimentacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `almoxarifado`.`tmovimentacao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario` INT NULL COMMENT 'Usuário que realizou a movimentação',
  `tipo` VARCHAR(45) NULL COMMENT 'ENTRADA ou SAIDA',
  `produto` INT NULL COMMENT 'Produto que foi movimentado',
  `quantidade` INT NULL COMMENT 'quantidade movimentada',
  `data` DATETIME NULL COMMENT 'Data da movimentação',
  `servico` INT NULL COMMENT 'Código do serviço que gerou essa movimentação',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_movimentacao_usuario`
    FOREIGN KEY (`usuario`)
    REFERENCES `almoxarifado`.`tusuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_movimentacao_produto`
    FOREIGN KEY (`produto`)
    REFERENCES `almoxarifado`.`tproduto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_movimentacao_servico`
    FOREIGN KEY (`servico`)
    REFERENCES `almoxarifado`.`tservico` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
