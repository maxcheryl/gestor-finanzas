CREATE TABLE ahorro_acumulado (
                                  id INT AUTO_INCREMENT,
                                  meta_id INT NOT NULL,
                                  usuario_id INT NOT NULL,
                                  saldo_total_ahorrado DECIMAL(12,2) NOT NULL DEFAULT 0.00,

                                  CONSTRAINT pk_ahorro_acumulado PRIMARY KEY (id)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;