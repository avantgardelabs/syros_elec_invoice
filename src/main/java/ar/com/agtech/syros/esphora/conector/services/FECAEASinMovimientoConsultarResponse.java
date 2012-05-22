
package ar.com.agtech.syros.esphora.conector.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ar.com.agtech.syros.esphora.conector.artifacts.FECAEASinMovConsResponse;


/**
 * <p>Java class for FECAEASinMovimientoConsultarResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FECAEASinMovimientoConsultarResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://ar.gov.afip.dif.FEV1/}FECAEASinMovConsResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FECAEASinMovimientoConsultarResponse", propOrder = {
    "_return"
})
public class FECAEASinMovimientoConsultarResponse {

    @XmlElement(name = "return")
    protected FECAEASinMovConsResponse _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link FECAEASinMovConsResponse }
     *     
     */
    public FECAEASinMovConsResponse getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link FECAEASinMovConsResponse }
     *     
     */
    public void setReturn(FECAEASinMovConsResponse value) {
        this._return = value;
    }

}
