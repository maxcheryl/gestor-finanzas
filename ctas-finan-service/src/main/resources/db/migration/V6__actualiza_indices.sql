ALTER TABLE entidad_financiera
    ADD KEY idx_entidad_financiera_usuario_id (usuario_id),
    ADD KEY idx_entidad_financiera_metodo_pago_id (metodo_pago_id);

ALTER TABLE metodo_pago
    ADD KEY idx_metodo_pago_nombre_metodo (nombre_metodo);