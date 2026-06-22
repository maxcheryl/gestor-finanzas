ALTER TABLE presupuesto_mensual
    ADD KEY fk_presupuesto_usuario (usuario_id);

ALTER TABLE presupuesto_mensual
    ADD KEY idx_presupuesto_periodo (anio, mes);