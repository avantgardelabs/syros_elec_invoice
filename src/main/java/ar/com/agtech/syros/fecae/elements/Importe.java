package ar.com.agtech.syros.fecae.elements;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.log4j.Logger;

import ar.com.agtech.syros.fecae.elements.types.TipoImporte;
import ar.com.agtech.syros.fecae.elements.types.TipoIva;

/**
 * @author Jorge Morando
 *
 */

public class Importe {
	
	private static final Logger log = Logger.getLogger(Importe.class);
	
	private BigDecimal bruto ;
	private BigDecimal neto ;
	private BigDecimal iva ;
	private TipoIva tipoIva;
	private TipoImporte tipo;
	
	private static BigDecimal cent = new BigDecimal("100");
	private static BigDecimal uni = new BigDecimal("1");
	
	/**
	 * Construye una instancia del objeto &quot;Importe&quot; desde el importe base y calculando el iva dependiendo del tipo de<br>
	 * IVA que se suministre como par&aacute;metro. Concluyendo en 2 situaciones:<br>
	 * <ol>
	 * <li><p>Si el importe es de tipo &quot;Neto&quot; entonces se calcular&aacute; el IVA de acuerdo al tipo suministrado<br>
	 * y se manejar&aacute; por separado.</p>
	 * </p><p>
	 * Bruto = <b>Neto</b> * TipoIva / 100 
	 * </p></li>
	 * <li><p>Si el importe es de tipo &quot;Bruto&quot; entonces se calcular&aacute; el IVA de acuerdo al tipo suministrado<br>
	 * sin ser discriminado calculando así el neto sobre el importe suministrado BRUTO
	 * </p><p>
	 * Neto = <b>Bruto</b> / ((TipoIva / 100) + 1 ) 
	 * </p></li>
	 * </ol> 
	 * <p>(#) - Es el par&aacute;metro "importe"</p>
	 * @param importe
	 * @param tipoIva
	 */
	public Importe(Importe.Tipo importe, TipoIva tipoIva){
		if(importe instanceof Importe.Neto){
			this.neto = importe.getImporte();
			this.tipo = TipoImporte.NETO;
		}else if(importe instanceof Importe.Bruto){
			this.bruto = importe.getImporte();
			this.tipo = TipoImporte.BRUTO;
		}
		this.tipoIva = tipoIva;
		calcularImporte();
	}
	
	/**
	 * Construye un importe desde el importe base y calculando el iva dependiendo del tipo de<br>
	 * IVA que se suministre como par&aacute;metro. Concluyendo en 2 situaciones:<br>
	 * <ol>
	 * <li><p>Si discriminarIva es Boolean.TRUE entonces se calcular&aacute; el IVA de acuerdo al tipo suministrado<br>
	 * y se manejar&aacute; por separado, convirti&eacute;ndose as&iacute; el importe suministrado en NETO.</p>
	 * </p><p>
	 * Total = Neto(#) * TipoIva(#) / 100 
	 * </p></li>
	 * <li><p>Si discriminarIva es Boolean.FALSE entonces se calcular&aacute; el IVA de acuerdo al tipo suministrado<br>
	 * sin ser discriminado calculando así el neto sobre el importe suministrado BRUTO
	 * </p><p>
	 * Importe(#) = Neto * TipoIva(#) / 100 
	 * </p></li>
	 * </ol> 
	 * <p>(#) - Es el par&aacute;metro "importe"</p>
	 * @param importe Valor neto o bruto (dependiendo de discriminarIva = TRUE/FALSE)
	 * @param tipoIva Tipo de iva a Calcular sobre el importe
	 * @param discriminarIva Flag que indica si el valor de importe es el total o bruto
	 */
	public Importe(BigDecimal importe, TipoIva tipoIva, TipoImporte tipoImporte){
		this.tipo = tipoImporte;
		this.tipoIva = tipoIva;
		if(tipo.isNeto()){
			this.neto = importe.setScale(2, RoundingMode.HALF_UP);
		}else{
			this.bruto = importe.setScale(2, RoundingMode.HALF_UP);
		}
		calcularImporte();
	}
	
	public BigDecimal getBruto() {
		return bruto;
	}

	public BigDecimal getNeto() {
		return neto;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public TipoIva getTipoIva() {
		return tipoIva;
	}
	
	public Boolean isNeto(){
		return tipo.isNeto();
	}

	public Boolean isBruto(){
		return !tipo.isNeto();
	}

	/**
	 * Calcula el valor del iva al importe neto con respecto al tipo de iva
	 * @param neto Valor al que se le calcular&aacute; el iva
	 * @param tipoIva Tipo de iva que se calcular&aacute;
	 * @return El Importe de iva del neto suministrado
	 */
	public static BigDecimal calcularIvaANeto(BigDecimal neto, TipoIva tipoIva){
		BigDecimal porcIva = traducirIVA(tipoIva); //obtenemos el porcentaje de iva
		BigDecimal intPorcIva = porcIva.divide(cent); //0.IVA
		
		return neto.multiply(intPorcIva).setScale(2,RoundingMode.HALF_UP);
	}
	
	/**
	 * Calcula el valor del iva del importe bruto con respecto al tipo de iva 
	 * @param bruto Valor del que se calcular&aacute; el iva
	 * @param tipoIva Tipo de iva que se calcular&aacute;
	 * @return El Importe de iva del neto suministrado
	 */
	public static BigDecimal calcularIvaDeBruto(BigDecimal bruto, TipoIva tipoIva){
		BigDecimal neto = calcularNeto(bruto,tipoIva);
		return bruto.subtract(neto).setScale(2, RoundingMode.HALF_UP);
	}
	
	/**
	 * Calcula el importe neto teniendo en cuenta el tipo de iva y el importe bruto
	 * @param bruto El importe bruto del que se calcualr&aacute; el iva y subsecuentemente el neto
	 * @param tipoIva el tipo de iva que se tendr&aacute; en cuenta para calcular el neto
	 * @return el importe neto
	 */
	public static BigDecimal calcularNeto(BigDecimal bruto, TipoIva tipoIva){
		
		BigDecimal porcIva = traducirIVA(tipoIva); //obtenemos el porcentaje de iva
		BigDecimal intPorcIva = porcIva.divide(cent);  //calculamos el operando del iva
		BigDecimal multiplicand = intPorcIva.add(uni); //calculamos el multiplicando para el iva

		return bruto.divide(multiplicand, 2, RoundingMode.HALF_UP);
	}
	
	/**
	 * Calcula el importe bruto teniendo en cuenta el tipo de iva
	 * @param neto el importe neto sobre el que se calcualr&aacute; el iva y subsecuentemente el bruto
	 * @param tipoIva el tipo de iva que se tendr&aacute; en cuenta para calcular el bruto
	 * @return el importe bruto
	 */
	public static BigDecimal calcularBruto(BigDecimal neto, TipoIva tipoIva){
		
		BigDecimal porcIva = traducirIVA(tipoIva); //obtenemos el porcentaje de iva
		BigDecimal intPorcIva = porcIva.divide(cent);  //calculamos el operando del iva
		BigDecimal multiplicand = intPorcIva.add(uni); //calculamos el multiplicando para el iva
		porcIva = porcIva.setScale(2, RoundingMode.HALF_UP);//seteamos la escala
		
		return neto.multiply(multiplicand).setScale(2, RoundingMode.HALF_UP);
	}
	
	/* PRIVATE METHODS*/
	private void calcularImporte(){
		if(tipo.isNeto()){
			iva = calcularIvaANeto(neto, tipoIva);
			bruto = calcularBruto(neto, tipoIva);
		}else{
			neto = calcularNeto(bruto, tipoIva);
			iva = calcularIvaDeBruto(bruto, tipoIva);
		}
		log.debug("Bruto: " + bruto);
		log.debug("Neto: " + neto);
		log.debug("IVA: " + iva);
	}
	
	private static BigDecimal traducirIVA(TipoIva tipoIva){
		BigDecimal porcIva;
		switch (tipoIva) {
		case CERO:
			porcIva = new BigDecimal("0");
			break;
		case DIEZ_COMA_CINCO:
			porcIva = new BigDecimal("10.5");
			break;
		case VEINTISIETE:
			porcIva = new BigDecimal("27");
			break;
		default:
			porcIva = new BigDecimal("21");
			break;
		}
		return porcIva;
	}
	
	/* static types */
	
	/**
	 * Tipo de importe (Neto/Bruto) 
	 * <br>Determinará el comportamiento que se tendrá al calcular el importe total de un comprobante fiscal
	 * @see ar.com.agtech.syros.fecae.implementations.esphora.invoices.ComprobanteFiscal
	 * @author Jorge Morando
	 */
	private static interface Tipo {
		BigDecimal getImporte();
	}
	
	public static class Bruto implements Tipo{
		
		private BigDecimal importe;
		
		public Bruto(BigDecimal importe){
			this.importe = importe; 
		}
		
		public BigDecimal getImporte(){
			return this.importe; 
		}
	}
	
	public static class Neto implements Tipo{
		
		private BigDecimal importe;
		
		public Neto(BigDecimal importe){
			this.importe = importe; 
		}
		
		public BigDecimal getImporte(){
			return this.importe; 
		}
	}
	
}
