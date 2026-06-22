CREATE TABLE gasto (
                       id INT AUTO_INCREMENT,
                       usuario_id INT NOT NULL,
                       monto DECIMAL(12,2) NOT NULL,
                       fecha DATE NOT NULL,
                       descripcion VARCHAR(255) NOT NULL,
                       categoria_id INT NOT NULL,
                       categoria_pers_id INT NULL,

                       CONSTRAINT pk_gasto PRIMARY KEY (id)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;