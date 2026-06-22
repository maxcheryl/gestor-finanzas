ALTER TABLE categoria
    ADD KEY idx_categoria_nombre (nombre),
    ADD KEY idx_categoria_tipo (tipo);

ALTER TABLE categoria_personalizada
    ADD KEY idx_categoria_personalizada_nombre (nombre),
    ADD KEY idx_categoria_personalizada_tipo (tipo);