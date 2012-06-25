
package ar.com.agtech.syros.fecae.implementations.esphora.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.OpcionalTipoResponse;


/**
 * <p>Java class for FEParamGetTiposOpcionalResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FEParamGetTiposOpcionalResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://ar.gov.afip.dif.FEV1/}OpcionalTipoResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FEParamGetTiposOpcionalResponse", propOrder = {
    "_return"
})
public class FEParamGetTiposOpcionalResponse {

    @XmlElement(name = "return")
    protected OpcionalTipoResponse _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link OpcionalTipoResponse }
     *     
     */
    public OpcionalTipoResponse getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link OpcionalTipoResponse }
     *     
     */
    public void setReturn(OpcionalTipoResponse value) {
        this._return = value;
    }

}
