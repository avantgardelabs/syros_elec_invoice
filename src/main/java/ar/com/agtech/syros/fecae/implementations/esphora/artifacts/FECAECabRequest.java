
package ar.com.agtech.syros.fecae.implementations.esphora.artifacts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FECAECabRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FECAECabRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ar.gov.afip.dif.FEV1/}FECabRequest">
 *       &lt;sequence>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FECAECabRequest")
public class FECAECabRequest
    extends FECabRequest
{

	@Override
	public String toString() {
		return "FECAECabRequest [cantReg=" + cantReg + ", ptoVta=" + ptoVta
				+ ", cbteTipo=" + cbteTipo + "]";
	}
	

}
