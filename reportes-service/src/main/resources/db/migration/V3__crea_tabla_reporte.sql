CREATE TABLE reporte_mensual
(
    id         INT AUTO_INCREMENT,
    usuario_id INT  NOT NULL,
    fecha      DATE NOT NULL,
    ingresos_totales DOUBLE NOT NULL,
    gastos_totales DOUBLE NOT NULL,
    saldo_neto DOUBLE NOT NULL,
    CONSTRAINT pk_reporte_mensual PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;