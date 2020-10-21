package webservice;

import javax.xml.ws.Endpoint;

public class Publication {
	
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8686/wsConversion", new Conversion());
		Endpoint.publish("http://localhost:8686/wsGestEtu", new  GestEtudiant());
		System.out.println("Votre service est publié");
		System.out.println("Adresse du WSD : http://localhost:8686/wsConversion/?wsdl");
	}
}