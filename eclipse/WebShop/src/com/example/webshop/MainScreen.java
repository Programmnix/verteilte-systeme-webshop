package com.example.webshop;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

public class MainScreen extends HorizontalLayout {
    private Menu menu;

    public MainScreen(WebshopUI ui) {

        setStyleName("main-screen");

        CssLayout viewContainer = new CssLayout();
        viewContainer.addStyleName("valo-content");
        viewContainer.setSizeFull();

        final Navigator navigator = new Navigator(ui, viewContainer);
        //navigator.setErrorView(ErrorView.class);
        menu = new Menu(navigator);
        menu.addView(new ProductOverview(), "Products", "Products", null);
        menu.addView(new CartView(), "Cartview", "Cart", null);
        

        navigator.addView("order", new OrderView());
        navigator.addView("login", new LoginView());

        navigator.addViewChangeListener(viewChangeListener);

        addComponent(menu);
        addComponent(viewContainer);
        setExpandRatio(viewContainer, 1);
        setSizeFull();
    }

    // notify the view menu about view changes so that it can display which view
    // is currently active
    ViewChangeListener viewChangeListener = new ViewChangeListener() {

        @Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            return true;
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
            menu.setActiveView(event.getViewName());
        }

    };
    
    class Test extends Label implements View{

		public Test() {
			super("Hallo");
		}
		@Override
		public void enter(ViewChangeEvent event) {
			// TODO Auto-generated method stub
			
		}
    	
    	
    }
}
