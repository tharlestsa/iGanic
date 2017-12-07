
DELIMITER $$
CREATE FUNCTION `cadastraProduto`(
	`nome` VARCHAR(45), 
    `unidade ` VARCHAR(2),
    `preco` DOUBLE,
    `quantidade` DOUBLE,
    `modoProducao` VARCHAR(200),
    `idUsuario` INT)
    RETURNS INT NOT DETERMINISTIC NO SQL SQL SECURITY DEFINER 
    BEGIN
		INSERT INTO `iGanic`.`Produtos`(`idProduto`, `nome`, `unidade`, `preco`, `quantidade`, `modoProducao`, `idUsuario`)
			VALUES(nome, unidade, preco, quantidade, modoProducao, idUsuario);

RETURN LAST_INSERT_ID(); 
END $$