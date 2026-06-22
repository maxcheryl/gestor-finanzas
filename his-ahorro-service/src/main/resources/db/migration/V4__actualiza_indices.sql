ALTER TABLE ahorro_acumulado
    ADD KEY idx_ahorro_acumulado_meta_id (meta_id),
    ADD KEY idx_ahorro_acumulado_usuario_id (usuario_id);