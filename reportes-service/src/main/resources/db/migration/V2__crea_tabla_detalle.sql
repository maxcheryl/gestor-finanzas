CREATE TABLE detalle_reporte
(
    id           INT AUTO_INCREMENT,
    reporte_id   INT NOT NULL,
    categoria_id INT NOT NULL,
    total_gastado DOUBLE NOT NULL,
    CONSTRAINT pk_detalle_reporte PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;