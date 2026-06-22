CREATE TABLE metodo_pago (
                             id INT AUTO_INCREMENT,
                             nombre_metodo VARCHAR(100) NOT NULL,

                             CONSTRAINT pk_metodo_pago PRIMARY KEY (id)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;