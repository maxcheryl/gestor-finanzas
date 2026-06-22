ALTER TABLE detalle_reporte
    ADD CONSTRAINT fk_detalle_reporte_reporte
        FOREIGN KEY (reporte_id) REFERENCES reporte_mensual (id);