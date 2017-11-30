CREATE OR REPLACE FUNCTION fn_log_audit() RETURNS trigger AS
$$
BEGIN
  IF (TG_OP = 'DELETE') THEN
    INSERT INTO zed_aud_actividad ("nombre_tabla", "operacion", "valor_anterior", "valor_nuevo", "fecha_modificacion", "nombre_usuario", "aud_ip_cliente")
           VALUES (TG_TABLE_NAME, 'Delete', OLD, NULL, now(), USER, inet_client_addr());
    RETURN OLD;
  ELSIF (TG_OP = 'UPDATE') THEN
    INSERT INTO zed_aud_actividad ("nombre_tabla", "operacion", "valor_anterior", "valor_nuevo", "fecha_modificacion", "nombre_usuario", "aud_ip_cliente")
           VALUES (TG_TABLE_NAME, 'Update', OLD, NEW, now(), USER, inet_client_addr());
    RETURN NEW;
  ELSIF (TG_OP = 'INSERT') THEN
    INSERT INTO zed_aud_actividad ("nombre_tabla", "operacion", "valor_anterior", "valor_nuevo", "fecha_modificacion", "nombre_usuario", "aud_ip_cliente")
           VALUES (TG_TABLE_NAME, 'Insert', NULL, NEW, now(), USER, inet_client_addr());
    RETURN NEW;
  END IF;
  RETURN NULL;
END;
$$
LANGUAGE 'plpgsql' VOLATILE COST 100;

ALTER FUNCTION fn_log_audit() OWNER TO postgres;

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_factura_producto_det FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_producto FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_numeracion FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_solicitud_ingreso_acompanamiento_det FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_operador FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_item FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_producto_insumos FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_operador_usuario FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_solicitud_ingreso_cab FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_solicitud_ingreso_observacion_det FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_solicitud_ingreso_transporte_det FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zes_solicitud_ingreso_factura_det FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_solicitud_ingreso_historial FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_catalogo FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_solicitud_ingreso_carga_suelta_det FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();

CREATE TRIGGER tbl_atributos_tg_audit AFTER INSERT OR UPDATE OR DELETE
ON zed_solicitud_ingreso_contenedor_det FOR EACH ROW EXECUTE PROCEDURE fn_log_audit();
