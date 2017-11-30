/**
 *Triggers FACTURA
 **/

CREATE OR REPLACE FUNCTION sumar_factura() RETURNS TRIGGER AS $sumar_factura$
  DECLARE
  BEGIN
  	UPDATE zes_solicitud_ingreso_factura_det 
  	SET fac_valor=(select COALESCE(SUM(fpr_valor_total),0) from zed_factura_producto_det d where d.fac_nro=NEW.fac_nro),
  		fac_peso_neto=(select COALESCE(SUM(fpr_cantidad_unidad_comercial*fpr_peso_neto),0) from zed_factura_producto_det d where d.fac_nro=NEW.fac_nro)
	WHERE fac_nro=NEW.fac_nro; 
	
	UPDATE zed_solicitud_ingreso_cab
	SET sin_peso_neto_total = (select COALESCE(SUM(fd.fpr_cantidad_unidad_comercial*fd.fpr_peso_neto),0) from zes_solicitud_ingreso_factura_det fc, zed_factura_producto_det fd where fc.fac_nro = fd.fac_nro and fc.sin_numero=(select sin_numero from zes_solicitud_ingreso_factura_det where fac_nro=NEW.fac_nro)),
		sin_unidades_comerciales = (select COALESCE(SUM(fd.fpr_cantidad_unidad_comercial),0) from zes_solicitud_ingreso_factura_det fc, zed_factura_producto_det fd where fc.fac_nro = fd.fac_nro and fc.sin_numero=(select sin_numero from zes_solicitud_ingreso_factura_det where fac_nro=NEW.fac_nro)),
		sin_valor_incoterm = (select COALESCE(SUM(fc.fac_valor),0) from zes_solicitud_ingreso_factura_det fc where fc.sin_numero=(select sin_numero from zes_solicitud_ingreso_factura_det where fac_nro=NEW.fac_nro)),
		sin_cantidad_items = (select COUNT(*) from zes_solicitud_ingreso_factura_det fc, zed_factura_producto_det fd where fc.fac_nro = fd.fac_nro and fc.sin_numero=(select sin_numero from zes_solicitud_ingreso_factura_det where fac_nro=NEW.fac_nro))
	WHERE sin_numero=(select sin_numero from zes_solicitud_ingreso_factura_det where fac_nro=NEW.fac_nro); 
   RETURN NULL;
  END;
$sumar_factura$ LANGUAGE plpgsql;

CREATE TRIGGER sumar_factura
    AFTER INSERT ON zed_factura_producto_det
    FOR EACH ROW
    EXECUTE PROCEDURE sumar_factura();

CREATE OR REPLACE FUNCTION restar_factura() RETURNS TRIGGER AS $restar_factura$
  DECLARE
  BEGIN
	UPDATE zes_solicitud_ingreso_factura_det 
  	SET fac_valor=(select COALESCE(SUM(fpr_valor_total),0) from zed_factura_producto_det d where d.fac_nro=OLD.fac_nro),
  		fac_peso_neto=(select COALESCE(SUM(fpr_cantidad_unidad_comercial*fpr_peso_neto),0) from zed_factura_producto_det d where d.fac_nro=OLD.fac_nro)
	WHERE fac_nro=OLD.fac_nro;
	  
  	UPDATE zed_solicitud_ingreso_cab
	SET sin_peso_neto_total = (select COALESCE(SUM(fd.fpr_cantidad_unidad_comercial*fd.fpr_peso_neto),0) from zes_solicitud_ingreso_factura_det fc, zed_factura_producto_det fd where fc.fac_nro = fd.fac_nro and fc.sin_numero=(select sin_numero from zes_solicitud_ingreso_factura_det where fac_nro=OLD.fac_nro)),
		sin_unidades_comerciales = (select COALESCE(SUM(fd.fpr_cantidad_unidad_comercial),0) from zes_solicitud_ingreso_factura_det fc, zed_factura_producto_det fd where fc.fac_nro = fd.fac_nro and fc.sin_numero=(select sin_numero from zes_solicitud_ingreso_factura_det where fac_nro=OLD.fac_nro)),
		sin_valor_incoterm = (select COALESCE(SUM(fc.fac_valor),0) from zes_solicitud_ingreso_factura_det fc where fc.sin_numero=(select sin_numero from zes_solicitud_ingreso_factura_det where fac_nro=OLD.fac_nro)),
		sin_cantidad_items = (select COUNT(*) from zes_solicitud_ingreso_factura_det fc, zed_factura_producto_det fd where fc.fac_nro = fd.fac_nro and fc.sin_numero=(select sin_numero from zes_solicitud_ingreso_factura_det where fac_nro=OLD.fac_nro))
	WHERE sin_numero=(select sin_numero from zes_solicitud_ingreso_factura_det where fac_nro=OLD.fac_nro);
   RETURN NULL;
  END;
$restar_factura$ LANGUAGE plpgsql;

CREATE TRIGGER restar_factura
    AFTER DELETE ON zed_factura_producto_det
    FOR EACH ROW
    EXECUTE PROCEDURE restar_factura();

DROP TRIGGER restar_factura ON zed_factura_producto_det;
DROP TRIGGER sumar_factura ON zed_factura_producto_det;
