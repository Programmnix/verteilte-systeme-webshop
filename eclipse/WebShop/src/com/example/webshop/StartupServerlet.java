package com.example.webshop;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.vaadin.server.VaadinServlet;



@WebServlet(loadOnStartup=1)
public class StartupServerlet extends VaadinServlet {


	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		DemoDataGenerator.create();
	}

	
}
