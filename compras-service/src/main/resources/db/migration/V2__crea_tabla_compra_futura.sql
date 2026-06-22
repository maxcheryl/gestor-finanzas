CREATE TABLE compra_futura (
                               id INT AUTO_INCREMENT,
                               usuario_id INT NOT NULL,
                               nombre_producto VARCHAR(150) NOT NULL,
                               precio_estimado DECIMAL(12,2) NOT NULL,
                               nota VARCHAR(255),

                               CONSTRAINT pk_compra_futura PRIMARY KEY (id)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;