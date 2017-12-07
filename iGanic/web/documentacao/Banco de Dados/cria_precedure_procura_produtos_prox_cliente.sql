    
DELIMITER $$
CREATE DEFINER=`root`@`localhost` 
PROCEDURE `procedure_procura_produtos_prox_cliente`( IN latCliente double, IN lngCliente double)

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
    ORDER BY distancia ASC
    LIMIT 10;
    
END$$
DELIMITER ;
