SELECT *, 
    (6371 * acos( cos( radians($LAT) ) 
 * cos( radians( latitude ) ) 
 * cos( radians( longitude ) - radians($LNG) ) 
 + sin( radians($LAT) ) 
 * sin( radians( latitude ) ) ) ) AS distancia 
    FROM tec_tecnico
    ORDER BY distancia
    
    
    
DELIMITER $$
CREATE DEFINER=`iganic`@`localhost` 
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
    ORDER BY distancia
    
END$$
DELIMITER; 
