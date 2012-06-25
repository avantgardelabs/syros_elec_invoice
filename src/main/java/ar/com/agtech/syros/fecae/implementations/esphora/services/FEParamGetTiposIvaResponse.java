
package ar.com.agtech.syros.fecae.implementations.esphora.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.IvaTipoResponse;


/**
 * <p>Java class for FEParamGetTiposIvaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FEParamGetTiposIvaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://ar.gov.afip.dif.FEV1/}IvaTipoResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FEParamGetTiposIvaResponse", propOrder = {
    "_return"
})
public class FEParamGetTiposIvaResponse {

    @XmlElement(name = "return")
    protected IvaTipoResponse _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link IvaTipoResponse }
     *     
     */
    public IvaTipoResponse getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link IvaTipoResponse }
     *     
     */
    public void setReturn(IvaTipoResponse value) {
        this._return = value;
    }

}
