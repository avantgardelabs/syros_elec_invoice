
package ar.com.agtech.syros.fecae.implementations.esphora.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.FECompConsultaResponse;


/**
 * <p>Java class for FECompConsultarResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FECompConsultarResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://ar.gov.afip.dif.FEV1/}FECompConsultaResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FECompConsultarResponse", propOrder = {
    "_return"
})
public class FECompConsultarResponse {

    @XmlElement(name = "return")
    protected FECompConsultaResponse _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link FECompConsultaResponse }
     *     
     */
    public FECompConsultaResponse getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link FECompConsultaResponse }
     *     
     */
    public void setReturn(FECompConsultaResponse value) {
        this._return = value;
    }

}
