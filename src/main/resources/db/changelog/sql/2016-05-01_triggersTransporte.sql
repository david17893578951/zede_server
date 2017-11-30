CREATE OR REPLACE FUNCTION sumar_flete() RETURNS TRIGGER AS $sumar_flete$
  DECLARE
  BEGIN
  	UPDATE zed_solicitud_ingreso_cab
   		SET sin_flete=(SELECT COALESCE(SUM(t.tra_flete),0) FROM zed_solicitud_ingreso_transporte_det t WHERE t.sin_numero=NEW.sin_numero)
 		WHERE sin_numero=NEW.sin_numero;
   RETURN NULL;
  END;
$sumar_flete$ LANGUAGE plpgsql;

CREATE TRIGGER sumar_flete
    AFTER INSERT OR UPDATE ON zed_solicitud_ingreso_transporte_det
    FOR EACH ROW
    EXECUTE PROCEDURE sumar_flete();

CREATE OR REPLACE FUNCTION restar_flete() RETURNS TRIGGER AS $restar_flete$
  DECLARE
  BEGIN
  	UPDATE zed_solicitud_ingreso_cab
   		SET sin_flete=(SELECT COALESCE(SUM(t.tra_flete),0) FROM zed_solicitud_ingreso_transporte_det t WHERE t.sin_numero=OLD.sin_numero)
 		WHERE sin_numero=OLD.sin_numero;
   RETURN NULL;
  END;
$restar_flete$ LANGUAGE plpgsql;

CREATE TRIGGER restar_flete
    AFTER DELETE ON zed_solicitud_ingreso_transporte_det
    FOR EACH ROW
    EXECUTE PROCEDURE restar_flete();
