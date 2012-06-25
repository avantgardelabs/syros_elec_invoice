package ar.com.agtech.syros.fecae.implementations.esphora.utils;

import java.util.Calendar;
import java.util.Date;

import ar.com.agtech.syros.fecae.elements.types.TipoIva;
import ar.com.agtech.syros.fecae.implementations.esphora.elements.Constants;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoComprobante;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoConcepto;
import ar.com.agtech.syros.fecae.implementations.esphora.types.TipoDocumento;


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
			concepto = Constants.TIPO_CONCEPTO_PRODUCTOS;
			break;
		case SERVICIOS:
			concepto = Constants.TIPO_CONCEPTO_SERVICIOS;
			break;
		case PRODUCTOS_Y_SERVICIOS:
			concepto = Constants.TIPO_CONCEPTO_PRODUCTOS_SERVICIOS;
			break;
		}
		return concepto;
	}
	
	public static Integer obtenerParametroTipoComprobante(TipoComprobante tc){
		Integer cbte = null;
		switch (tc) {
		case FACTURA_A:
			cbte = Constants.TIPO_COMPROBANTE_FACTURA_A;
			break;
		case FACTURA_B:
			cbte = Constants.TIPO_COMPROBANTE_FACTURA_B;
			break;
		case NOTA_CREDITO_A:
			cbte = Constants.TIPO_COMPROBANTE_NOTA_CREDITO_A;
			break;
		case NOTA_CREDITO_B:
			cbte = Constants.TIPO_COMPROBANTE_NOTA_CREDITO_B;
			break;
		case NOTA_DEBITO_A:
			cbte = Constants.TIPO_COMPROBANTE_NOTA_DEBITO_A;
			break;
		case NOTA_DEBITO_B:
			cbte = Constants.TIPO_COMPROBANTE_NOTA_DEBITO_B;
			break;
		}
		return cbte;
	}
	
	public static Integer obtenerParametroTipoDocumento(TipoDocumento td){
		Integer doc = null;
		switch (td) {
		case CUIT:
			doc = Constants.TIPO_DOC_CUIT;
			break;
		case CUIL:
			doc = Constants.TIPO_DOC_CUIL;
			break;
		case DNI:
			doc = Constants.TIPO_DOC_DNI;
			break;
		}
		return doc;
	}
	
	public static int obtenerTipoIvaEsphora(TipoIva tipoIva){
		int ivaEsphora = Constants.TIPO_IVA_VEINTIUNO;
		switch (tipoIva) {
		case CERO:
			ivaEsphora = Constants.TIPO_IVA_CERO;
			break;
		case DIEZ_COMA_CINCO:
			ivaEsphora = Constants.TIPO_IVA_DIEZ_COMA_CINCO;
			break;
		case VEINTISIETE:
			ivaEsphora = Constants.TIPO_IVA_VEINTISIETE;
			break;
		}
		return ivaEsphora;
	}
	
}
