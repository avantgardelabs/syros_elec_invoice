
package ar.com.agtech.syros.fecae.implementations.esphora.artifacts;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfEvt complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfEvt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Evt" type="{http://ar.gov.afip.dif.FEV1/}Evt" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfEvt", propOrder = {
    "evt"
})
public class ArrayOfEvt {

    @XmlElement(name = "Evt", nillable = true)
    protected List<Evt> evt;

    /**
     * Gets the value of the evt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the evt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEvt().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Evt }
     * 
     * 
     */
    public List<Evt> getEvt() {
        if (evt == null) {
            evt = new ArrayList<Evt>();
        }
        return this.evt;
    }

	@Override
	public String toString() {
		final int maxLen = 10;
		return "ArrayOfEvt [evt="
				+ (evt != null ? evt.subList(0, Math.min(evt.size(), maxLen))
						: null) + "]";
	}

}
