
package ar.com.agtech.syros.esphora.conector.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FECompUltimoAutorizado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FECompUltimoAutorizado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipoComprobante" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="puntoVenta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cuit" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FECompUltimoAutorizado", propOrder = {
    "tipoComprobante",
    "puntoVenta",
    "cuit"
})
public class FECompUltimoAutorizado {

    protected int tipoComprobante;
    protected int puntoVenta;
    protected long cuit;

    /**
     * Gets the value of the tipoComprobante property.
     * 
     */
    public int getTipoComprobante() {
        return tipoComprobante;
    }

    /**
     * Sets the value of the tipoComprobante property.
     * 
     */
    public void setTipoComprobante(int value) {
        this.tipoComprobante = value;
    }

    /**
     * Gets the value of the puntoVenta property.
     * 
     */
    public int getPuntoVenta() {
        return puntoVenta;
    }

    /**
     * Sets the value of the puntoVenta property.
     * 
     */
    public void setPuntoVenta(int value) {
        this.puntoVenta = value;
    }

    /**
     * Gets the value of the cuit property.
     * 
     */
    public long getCuit() {
        return cuit;
    }

    /**
     * Sets the value of the cuit property.
     * 
     */
    public void setCuit(long value) {
        this.cuit = value;
    }

}
