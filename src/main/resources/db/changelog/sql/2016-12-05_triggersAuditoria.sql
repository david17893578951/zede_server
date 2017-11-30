CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_producto_orden_produccion_det FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_orden_produccion FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_insumo_orden_produccion_det FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_orden_despacho_cab FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_orden_despacho_det FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_insumo_orden_despacho FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_inspeccion_cab FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_inspeccion_det FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_orden_trabajo_cab FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_orden_trabajo_det FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_periodo FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_solicitud_trasnferencia_cab FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_solicitud_transferencia_det FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_ajuste_inventario_cab FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_ajuste_inventario_det FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_solicitud_descarga_salida_cab FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_solicitud_descarga_salida_det FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_kardex FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();