
DELIMITER $$
CREATE FUNCTION `cadastraUsuario` (
     pnome VARCHAR(60),
     pcpf VARCHAR(20),
     pcel VARCHAR(15),
     pemail VARCHAR(70),
     plat DOUBLE,
     plng DOUBLE,
     prua VARCHAR(160),
     pnum VARCHAR(20),
     pcomp VARCHAR(45),
     pbairro VARCHAR(45),
     pcidade VARCHAR(45),
     puf VARCHAR(20),
     ptipo VARCHAR(1),
     pusuario VARCHAR(20),
     psenha VARCHAR(15))
RETURNS INTEGER
BEGIN
	INSERT INTO `iGanic`.`Usuarios`(nome, cpf, cel, email, lat, lng, rua, num, comp, bairro, cidade, uf, tipo, usuario, senha)
        VALUES(pnome, pcpf, pcel, pemail, plat, plng, prua, pnum, pcomp, pbairro, pcidade, puf, ptipo, pusuario, psenha);

RETURN LAST_INSERT_ID();
END $$

