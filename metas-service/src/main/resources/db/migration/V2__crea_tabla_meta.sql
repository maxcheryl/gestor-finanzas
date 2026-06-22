CREATE TABLE meta_mensual
(
    id         INT AUTO_INCREMENT,
    usuario_id INT          NOT NULL,
    nombre     VARCHAR(100) NOT NULL,
    cuota_mensual DOUBLE NOT NULL,
    CONSTRAINT pk_meta_mensual PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;