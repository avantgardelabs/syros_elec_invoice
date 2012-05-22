
package ar.com.agtech.syros.esphora.conector.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ar.com.agtech.syros.esphora.conector.artifacts.FEPtoVentaResponse;


/**
 * <p>Java class for FEParamGetPtosVentaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FEParamGetPtosVentaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://ar.gov.afip.dif.FEV1/}FEPtoVentaResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FEParamGetPtosVentaResponse", propOrder = {
    "_return"
})
public class FEParamGetPtosVentaResponse {

    @XmlElement(name = "return")
    protected FEPtoVentaResponse _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link FEPtoVentaResponse }
     *     
     */
    public FEPtoVentaResponse getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link FEPtoVentaResponse }
     *     
     */
    public void setReturn(FEPtoVentaResponse value) {
        this._return = value;
    }

}
