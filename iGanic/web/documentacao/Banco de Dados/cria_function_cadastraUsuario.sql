
DELIMITER $$
CREATE FUNCTION `cadastraUsuario` (
     pnome VARCHAR(60),
     pcpf VARCHAR(20),
     pcel VARCHAR(15),
     pemail VARCHAR(70),
     pendereco VARCHAR(160),
     plat VARCHAR(20),
     plng VARCHAR(20),
     ptipo VARCHAR(1),
     pusuario VARCHAR(20),
     psenha VARCHAR(15),
     pidCidade INT)
RETURNS INTEGER
BEGIN
	INSERT INTO `iGanic`.`Usuarios`(nome, cpf, cel, email, endereco, lat, lng, tipo, usuario, senha, idCidade)
        VALUES(pnome, pcpf, pcel, pemail, pendereco, plat, plng, ptipo, pusuario, psenha, pidCidade);

RETURN LAST_INSERT_ID();
END $$

