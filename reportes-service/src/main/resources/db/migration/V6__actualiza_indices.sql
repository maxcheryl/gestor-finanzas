ALTER TABLE detalle_reporte
    ADD KEY fk_detalle_reporte_mensual (reporte_id),
    ADD KEY fk_detalle_categoria (categoria_id);

ALTER TABLE detalle_reporte
    ADD CONSTRAINT detalle_reporte_ibfk_1 FOREIGN KEY (reporte_id)
        REFERENCES reporte_mensual (id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE reporte_mensual
    ADD KEY idx_reporte_usuario (usuario_id),
    ADD KEY idx_reporte_fecha (fecha);