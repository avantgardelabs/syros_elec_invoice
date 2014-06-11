
package ar.com.agtech.syros.fecae.implementations.esphora.artifacts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FECAEDetRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FECAEDetRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ar.gov.afip.dif.FEV1/}FEDetRequest">
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
@XmlType(name = "FECAEDetRequest")
@XmlSeeAlso({
    FECompConsResponse.class
})
public class FECAEDetRequest
    extends FEDetRequest
{

	@Override
	public String toString() {
		return "FECAEDetRequest [concepto=" + concepto + ", docTipo=" + docTipo
				+ ", docNro=" + docNro + ", cbteDesde=" + cbteDesde
				+ ", cbteHasta=" + cbteHasta + ", cbteFch=" + cbteFch
				+ ", impTotal=" + impTotal + ", impTotConc=" + impTotConc
				+ ", impNeto=" + impNeto + ", impOpEx=" + impOpEx
				+ ", impTrib=" + impTrib + ", impIVA=" + impIVA
				+ ", fchServDesde=" + fchServDesde + ", fchServHasta="
				+ fchServHasta + ", fchVtoPago=" + fchVtoPago + ", monId="
				+ monId + ", monCotiz=" + monCotiz + ", cbtesAsoc=" + cbtesAsoc
				+ ", tributos=" + tributos + ", iva=" + iva + ", opcionales="
				+ opcionales + "]";
	}
	

}
