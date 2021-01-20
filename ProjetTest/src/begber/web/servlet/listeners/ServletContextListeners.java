package begber.web.servlet.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import begber.web.servlet.webservice.Publication;

public class ServletContextListeners implements ServletContextListener{
	
	private Thread webservice;
	
	@SuppressWarnings("deprecation")
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		webservice.destroy();
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		webservice = new Publication();
		webservice.start();
	}
}
