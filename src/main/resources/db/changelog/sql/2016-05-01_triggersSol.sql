CREATE OR REPLACE FUNCTION insertar_sol() RETURNS TRIGGER AS $insertar_sol$
  DECLARE
  BEGIN
	INSERT INTO zed_solicitud_ingreso_historial(
            his_codigo, sin_numero, his_fecha_hora, his_fecha, his_estado_anterior, 
            his_estado_nuevo, his_usuario)
	VALUES (default, NEW.sin_numero, NEW.sin_fecha_hora, NEW.sin_fecha, null, 
            NEW.sin_estado, NEW.ope_codigo);
   RETURN NULL;
  END;
$insertar_sol$ LANGUAGE plpgsql;

CREATE TRIGGER insertar_sol
    AFTER INSERT ON zed_solicitud_ingreso_cab
    FOR EACH ROW
    EXECUTE PROCEDURE insertar_sol();
    
CREATE OR REPLACE FUNCTION modificar_sol() RETURNS TRIGGER AS $modificar_sol$
  DECLARE
  BEGIN
	INSERT INTO zed_solicitud_ingreso_historial(
            his_codigo, sin_numero, his_fecha_hora, his_fecha, his_estado_anterior, 
            his_estado_nuevo, his_usuario)
	VALUES (default, OLD.sin_numero, current_timestamp, current_date, OLD.sin_estado, 
            NEW.sin_estado, OLD.ope_codigo);
   RETURN NULL;
  END;
$modificar_sol$ LANGUAGE plpgsql;

CREATE TRIGGER modificar_sol
    AFTER UPDATE ON zed_solicitud_ingreso_cab
    FOR EACH ROW
    EXECUTE PROCEDURE modificar_sol();