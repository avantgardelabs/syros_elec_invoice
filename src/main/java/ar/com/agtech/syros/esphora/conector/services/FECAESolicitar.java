
package ar.com.agtech.syros.esphora.conector.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import ar.com.agtech.syros.esphora.conector.artifacts.FECAERequest;


/**
 * <p>Java class for FECAESolicitar complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FECAESolicitar">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fer" type="{http://ar.gov.afip.dif.FEV1/}FECAERequest" minOccurs="0"/>
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
@XmlType(name = "FECAESolicitar", propOrder = {
    "fer",
    "cuit"
})
public class FECAESolicitar {

    protected FECAERequest fer;
    protected long cuit;

    /**
     * Gets the value of the fer property.
     * 
     * @return
     *     possible object is
     *     {@link FECAERequest }
     *     
     */
    public FECAERequest getFer() {
        return fer;
    }

    /**
     * Sets the value of the fer property.
     * 
     * @param value
     *     allowed object is
     *     {@link FECAERequest }
     *     
     */
    public void setFer(FECAERequest value) {
        this.fer = value;
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
