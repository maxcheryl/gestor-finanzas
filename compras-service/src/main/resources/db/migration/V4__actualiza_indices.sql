ALTER TABLE compra_futura
    ADD KEY idx_compra_futura_usuario_id (usuario_id),
    ADD KEY idx_compra_futura_nombre_producto (nombre_producto);