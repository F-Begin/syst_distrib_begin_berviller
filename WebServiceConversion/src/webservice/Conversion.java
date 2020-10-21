package webservice;

import javax.jws.WebService;
import javax.jws.WebParam;
import javax.jws.WebMethod;

@WebService(targetNamespace = "http://polytech.fr")
public class Conversion {
	@WebMethod (operationName = "convertir")
	public double conversion(@WebParam(name = "mt") double montant) {
		return montant*1.6;
	}

}
