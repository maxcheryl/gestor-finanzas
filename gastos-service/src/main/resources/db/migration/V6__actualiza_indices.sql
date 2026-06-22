ALTER TABLE gasto
    ADD KEY idx_gasto_usuario_id (usuario_id),
    ADD KEY idx_gasto_categoria_id (categoria_id),
    ADD KEY idx_gasto_categoria_pers_id (categoria_pers_id),
    ADD KEY idx_gasto_fecha (fecha);

ALTER TABLE gasto_recurrente
    ADD KEY idx_gasto_recurrente_usuario_id (usuario_id),
    ADD KEY idx_gasto_recurrente_categoria_id (categoria_id),
    ADD KEY idx_gasto_recurrente_categoria_pers_id (categoria_pers_id);