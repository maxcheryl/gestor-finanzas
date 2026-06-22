ALTER TABLE meta_mensual
    ADD KEY fk_meta_usuario (usuario_id);

ALTER TABLE meta_mensual
    ADD KEY idx_meta_nombre (nombre);