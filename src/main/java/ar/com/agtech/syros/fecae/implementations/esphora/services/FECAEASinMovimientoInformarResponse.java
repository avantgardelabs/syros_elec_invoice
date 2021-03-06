
package ar.com.agtech.syros.fecae.implementations.esphora.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.FECAEASinMovResponse;


/**
 * <p>Java class for FECAEASinMovimientoInformarResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FECAEASinMovimientoInformarResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://ar.gov.afip.dif.FEV1/}FECAEASinMovResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FECAEASinMovimientoInformarResponse", propOrder = {
    "_return"
})
public class FECAEASinMovimientoInformarResponse {

    @XmlElement(name = "return")
    protected FECAEASinMovResponse _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link FECAEASinMovResponse }
     *     
     */
    public FECAEASinMovResponse getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link FECAEASinMovResponse }
     *     
     */
    public void setReturn(FECAEASinMovResponse value) {
        this._return = value;
    }

}
