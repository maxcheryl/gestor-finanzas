CREATE TABLE ingreso_fijo
(
    id                    INT AUTO_INCREMENT,
    usuario_id            INT  NOT NULL,
    monto DOUBLE NOT NULL,
    fecha                 DATE NOT NULL,
    entidad_financiera_id INT  NOT NULL,
    categoria_id          INT,
    CONSTRAINT pk_ingreso_fijo PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;