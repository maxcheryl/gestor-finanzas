ALTER TABLE ingreso_fijo
    ADD KEY fk_ingreso_fijo_usuario (usuario_id),
    ADD KEY idx_ingreso_fijo_entidad_cat (entidad_financiera_id, categoria_id),
    ADD KEY idx_ingreso_fijo_fecha (fecha);


ALTER TABLE ingreso_extra
    ADD KEY fk_ingreso_extra_usuario (usuario_id),
    ADD KEY idx_ingreso_extra_entidad_cat (entidad_financiera_id, categoria_id),
    ADD KEY idx_ingreso_extra_fecha (fecha);