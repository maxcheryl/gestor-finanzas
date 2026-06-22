CREATE TABLE ingreso_extra
(
    id                    INT AUTO_INCREMENT,
    usuario_id            INT  NOT NULL,
    monto DOUBLE NOT NULL,
    fecha                 DATE NOT NULL,
    descripcion           VARCHAR(255),
    entidad_financiera_id INT  NOT NULL,
    categoria_id          INT,
    CONSTRAINT pk_ingreso_extra PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;