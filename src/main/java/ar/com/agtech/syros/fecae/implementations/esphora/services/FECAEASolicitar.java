
package ar.com.agtech.syros.fecae.implementations.esphora.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FECAEASolicitar complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FECAEASolicitar">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="periodo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="orden" type="{http://www.w3.org/2001/XMLSchema}short"/>
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
@XmlType(name = "FECAEASolicitar", propOrder = {
    "periodo",
    "orden",
    "cuit"
})
public class FECAEASolicitar {

    protected int periodo;
    protected short orden;
    protected long cuit;

    /**
     * Gets the value of the periodo property.
     * 
     */
    public int getPeriodo() {
        return periodo;
    }

    /**
     * Sets the value of the periodo property.
     * 
     */
    public void setPeriodo(int value) {
        this.periodo = value;
    }

    /**
     * Gets the value of the orden property.
     * 
     */
    public short getOrden() {
        return orden;
    }

    /**
     * Sets the value of the orden property.
     * 
     */
    public void setOrden(short value) {
        this.orden = value;
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
