package webservice;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@javax.jws.WebService(name="TP1 SOAP Conversion", targetNamespace="http://www.polytechNancy.fr")
public class Conversion {
	@WebMethod(operationName="Convertir")
	public double conversion(@WebParam(name="mt") double montant) {
		return montant*1.6;
	}
}
