-- Índices de la tabla "usuario"
ALTER TABLE usuario
    ADD KEY idx_usuario_apellidos (apellido_paterno, apellido_materno),
    ADD KEY idx_usuario_nombre (nombre);