package com.example.webshop;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinServlet;

@WebServlet(value = "/*", asyncSupported = true, loadOnStartup=1)
@VaadinServletConfiguration(productionMode = false, ui = WebshopUI.class, widgetset = "com.example.webshop.widgetset.WebshopWidgetset")
public class MainServlet extends VaadinServlet implements SessionInitListener  {

	static {
		new Thread(new Runnable() {
			
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				DemoDataGenerator.create();
				
			}
		}
		).start();
	}
	public MainServlet() {
		 }

	@Override
	public void sessionInit(SessionInitEvent event) throws ServiceException {
		event.getSession().addBootstrapListener(new WebshopVaadinServletSessionInitListener());
		
	}

}
