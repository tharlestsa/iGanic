    
DELIMITER $$
CREATE DEFINER=`root`@`localhost` 
PROCEDURE `procedure_procura_produtos`( IN latCliente double, IN lngCliente double, IN produtoNome VARCHAR(80))

BEGIN
    SELECT *, 
    (6371 * acos( cos( radians(latCliente) ) 
         * cos( radians( lat ) ) 
         * cos( radians( lng ) - radians(lngCliente) ) 
         + sin( radians(latCliente) ) 
         * sin( radians( lat ) ) ) ) AS distancia 
    FROM `iGanic`.`Usuarios`
    INNER JOIN `iGanic`.`Produtos`
        ON `iGanic`.`Usuarios`.`idUsuario` = `iGanic`.`Produtos`.`idUsuario`
    INNER JOIN `iGanic`.`Imagens`
        ON `iGanic`.`Imagens`.`idProduto` = `iGanic`.`Produtos`.`idProduto`
    WHERE `iGanic`.`Produtos`.`nome` = produtoNome
    ORDER BY distancia ASC
    LIMIT 20;
    
END$$
DELIMITER ;
