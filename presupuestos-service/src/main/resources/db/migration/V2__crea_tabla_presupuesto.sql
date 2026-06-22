CREATE TABLE presupuesto_mensual
(
    id                      INT AUTO_INCREMENT,
    usuario_id              INT NOT NULL,
    mes                     INT NOT NULL,
    anio                    INT NOT NULL,
    monto_inicial           INT NOT NULL,
    monto_actual_disponible INT NOT NULL,
    CONSTRAINT pk_presupuesto_mensual PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;