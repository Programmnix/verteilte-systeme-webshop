package com.example.webshop;

import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;


public class WebshopVaadinServletSessionInitListener
		implements
			BootstrapListener {

	@Override
	public void modifyBootstrapFragment(BootstrapFragmentResponse response) {
		
		System.out.println("modifyBootstrapFragment");
	}

	@Override
	public void modifyBootstrapPage(BootstrapPageResponse response) {
		System.out.println("modifyBootstrapFragment");

	}

}
