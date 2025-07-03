INSERT INTO `upeu_categoria` (`id_categoria`, `nombre`) VALUES
                                                            (1, 'Juvenil'),
                                                            (2, 'Adulto');
INSERT INTO `upeu_marca` (`id_marca`, `nombre`) VALUES
                                                    (1, 'Puma'),
                                                    (2, 'AdidasU');
INSERT INTO `upeu_unid_medida` (`id_unidad`, `nombre_medida`) VALUES
    (1, 'Unidad');
INSERT INTO `upeu_producto` (`id_producto`, `nombre`, `pu`, `puold`, `stock`, `stockold`, `utilidad`, `id_categoria`, `id_marca`, `id_unidad`) VALUES
                                                                                                                                                   (1, 'Zapatilla', 300, 300, 12, 0, 10, 1, 1, 1),
                                                                                                                                                   (2, 'Zapatillas De Deporte', 100, 10, 10, 10, 10, 1, 1, 1);
INSERT INTO `upeu_cliente` (`dniruc`, `nombres`, `rep_legal`, `tipo_documento`) VALUES
    ('43631917', 'David Mamani', 'David Mamani', 'DNI');
	
	
INSERT INTO `upeu_roles` (`id_rol`, `descripcion`, `nombre`) VALUES
(1, 'Administrador', 'ADMIN'),
(2, 'Admin DBA', 'DBA'),
(3, 'Usuario', 'USER');



INSERT INTO `upeu_usuario` (`id_usuario`, `clave`, `estado`, `user`) VALUES
(1, '$2a$10$3xH86/FINi7tZxuxZx53IesZ0nJndQomWwwpuHZF.IpAp6QBtEyEe', 'Activo', 'davidmp@upeu.edu.pe'),
(2, '$2a$10$3xH86/FINi7tZxuxZx53IesZ0nJndQomWwwpuHZF.IpAp6QBtEyEe', 'Activo', 'dariomp@upeu.edu.pe'),
(3, '$2a$10$zuU/ofkt/SjRaC6tz1cbzOS571mRXhPSEmZb33Qqg9uac9qOS38uC', 'Activo', 'mamanipari@gmail.com'),
(4, '$2a$10$3xH86/FINi7tZxuxZx53IesZ0nJndQomWwwpuHZF.IpAp6QBtEyEe', 'Activo', 'dmp@unsa.edu.pe'),
(5, '$2a$10$3xH86/FINi7tZxuxZx53IesZ0nJndQomWwwpuHZF.IpAp6QBtEyEe', 'Activo', 'dmpp@unsa.edu.pe');


INSERT INTO `upeu_usuario_rol` (`rol_id`, `usuario_id`) VALUES
(1, 1),
(1, 3),
(2, 4),
(2, 5),
(3, 2);
	
	
	
INSERT INTO `upeu_venta` (`id_venta`, `fecha_gener`, `igv`, `num_doc`, `preciobase`, `preciototal`, `serie`, `tipo_doc`, `dniruc`, `id_usuario`) VALUES
                                                                                                                                                     (1, '2024-10-21 05:22:32.000000', 45.76, '001', 254.24, 300, 'B01', 'Boleta', '43631917', 1),
                                                                                                                                                     (2, '2024-10-21 05:27:37.000000', 91.53, '002', 508.47, 600, 'B01', 'Boleta', '43631917', 1),
                                                                                                                                                     (3, '2024-10-21 22:25:40.000000', 45.76, '003', 254.24, 300, 'B01)', 'Boleta', '43631917', 1);
INSERT INTO `upeu_venta_detalle` (`id_venta_detalle`, `cantidad`, `descuento`, `pu`, `subtotal`, `id_producto`, `id_venta`) VALUES
                                                                                                                                (1, 1, 0, 300, 300, 1, 1),
                                                                                                                                (2, 2, 0, 300, 600, 1, 2),
                                                                                                                                (3, 3, 0, 100, 300, 2, 3);