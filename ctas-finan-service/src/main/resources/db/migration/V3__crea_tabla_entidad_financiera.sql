CREATE TABLE entidad_financiera (
                                    id INT AUTO_INCREMENT,
                                    usuario_id INT NOT NULL,
                                    nombre_entidad VARCHAR(150) NOT NULL,
                                    metodo_pago_id INT NOT NULL,

                                    CONSTRAINT pk_entidad_financiera PRIMARY KEY (id)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;