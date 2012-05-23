package ar.com.agtech.syros.esphora.utils;

import java.util.Calendar;
import java.util.Date;

import ar.com.agtech.syros.esphora.conector.elements.Constantes;
import ar.com.agtech.syros.esphora.conector.elements.TipoComprobante;
import ar.com.agtech.syros.esphora.conector.elements.TipoConcepto;
import ar.com.agtech.syros.esphora.conector.elements.TipoDocumento;


/**
 * @author Jorge Morando
 *
 */
public class Util {

	public static String dateTransform(Date date){
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date.getTime());
		String year = cal.get(Calendar.YEAR)  +"";
		String month = cal.get(Calendar.MONTH)+ 1 + "";
		String day = cal.get(Calendar.DATE) + "";
		
		month = month.trim().length()<2?"0"+month:month;
		day = day.trim().length()<2?"0"+day:day;
		
		return year + month + day;
	}
	
	public static Date parseDate(String date){
		date = date.trim();
		String year = date.substring(0, 4);//20120403
		String month = date.substring(4,6);
		String day = date.substring(6,8);
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day));
		return cal.getTime();
	}
	
	public static Integer obtenerParametroTipoConcepto(TipoConcepto tc){
		Integer concepto = null;
		switch (tc) {
		case PRODUCTOS:
			concepto = Constantes.TIPO_CONCEPTO_PRODUCTOS;
			break;
		case SERVICIOS:
			concepto = Constantes.TIPO_CONCEPTO_SERVICIOS;
			break;
		case PRODUCTOS_Y_SERVICIOS:
			concepto = Constantes.TIPO_CONCEPTO_PRODUCTOS_SERVICIOS;
			break;
		}
		return concepto;
	}
	
	public static Integer obtenerParametroTipoComprobante(TipoComprobante tc){
		Integer cbte = null;
		switch (tc) {
		case FACTURA_A:
			cbte = Constantes.TIPO_COMPROBANTE_FACTURA_A;
			break;
		case FACTURA_B:
			cbte = Constantes.TIPO_COMPROBANTE_FACTURA_B;
			break;
		case NOTA_CREDITO_A:
			cbte = Constantes.TIPO_COMPROBANTE_NOTA_CREDITO_A;
			break;
		case NOTA_CREDITO_B:
			cbte = Constantes.TIPO_COMPROBANTE_NOTA_CREDITO_B;
			break;
		case NOTA_DEBITO_A:
			cbte = Constantes.TIPO_COMPROBANTE_NOTA_DEBITO_A;
			break;
		case NOTA_DEBITO_B:
			cbte = Constantes.TIPO_COMPROBANTE_NOTA_DEBITO_B;
			break;
		}
		return cbte;
	}
	
	public static Integer obtenerParametroTipoDocumento(TipoDocumento td){
		Integer doc = null;
		switch (td) {
		case CUIT:
			doc = Constantes.TIPO_DOC_CUIT;
			break;
		case CUIL:
			doc = Constantes.TIPO_DOC_CUIL;
			break;
		case DNI:
			doc = Constantes.TIPO_DOC_DNI;
			break;
		}
		return doc;
	}
	
	
}
