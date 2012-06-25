
package ar.com.agtech.syros.fecae.implementations.esphora.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import ar.com.agtech.syros.fecae.implementations.esphora.artifacts.FECompConsultaReq;


/**
 * <p>Java class for FECompConsultar complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FECompConsultar">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FECompConsultaReq" type="{http://ar.gov.afip.dif.FEV1/}FECompConsultaReq" minOccurs="0"/>
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
@XmlType(name = "FECompConsultar", propOrder = {
    "feCompConsultaReq",
    "cuit"
})
public class FECompConsultar {

    @XmlElement(name = "FECompConsultaReq")
    protected FECompConsultaReq feCompConsultaReq;
    protected long cuit;

    /**
     * Gets the value of the feCompConsultaReq property.
     * 
     * @return
     *     possible object is
     *     {@link FECompConsultaReq }
     *     
     */
    public FECompConsultaReq getFECompConsultaReq() {
        return feCompConsultaReq;
    }

    /**
     * Sets the value of the feCompConsultaReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link FECompConsultaReq }
     *     
     */
    public void setFECompConsultaReq(FECompConsultaReq value) {
        this.feCompConsultaReq = value;
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
