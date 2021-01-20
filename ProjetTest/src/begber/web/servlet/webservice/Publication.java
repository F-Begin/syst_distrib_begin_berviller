package begber.web.servlet.webservice;

import javax.xml.ws.Endpoint;

public class Publication extends Thread {
	public void run() {
		Endpoint.publish("http://localhost:9090/wsGestions", new Gestions());
		System.out.println("Service publié !");
		System.out.println("Adresse du WSD : http://localhost:9090/wsGestions/?wsdl");
	}
}