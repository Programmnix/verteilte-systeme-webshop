package com.example.webshop;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.EntityManager;

import com.example.webshop.model.Product;
import com.google.gwt.layout.client.Layout.Alignment;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;


public class CartView extends CssLayout implements View{

	private HashMap<Long, Integer> cartList = null;
	private ArrayList<ProductCount> cartProducts = new ArrayList<ProductCount>();
	private BeanItemContainer<ProductCount> container;
	private transient EntityManager em;
	
	private Grid grid = null;
	
	public CartView() {
		setSizeFull();

        addStyleName("crud-view");
		
		VerticalLayout verticalLayout = new VerticalLayout();
		

		
		setJPAContainer();
		
		
		verticalLayout.addComponent(new Label("Cart"));
		
		
		grid = new Grid();
		grid.setWidth("100%");
		grid.setHeight("400px");
		
		
		verticalLayout.addComponent(grid);
	
		
		
		HorizontalLayout footerbar = new HorizontalLayout();
		Button buy = new Button("Buy");
		buy.addStyleName(ValoTheme.BUTTON_PRIMARY);
		buy.addClickListener(new ClickListener() {
			
			
			@Override
			public void buttonClick(ClickEvent event) {
					getUI().getNavigator().navigateTo("order");
			}
		});

		footerbar.addComponent(buy);
		
		verticalLayout.addComponent(footerbar);
		
		
		verticalLayout.setMargin(true);
		
		addComponent(verticalLayout);
		
		
	}
	
	public boolean setJPAContainer(){
		if(em == null){
			em = JPAContainerFactory.createEntityManagerForPersistenceUnit(WebshopUI.PERSISTENCE_UNIT);
			return true;
		}
		return false;
	}
	
	@Override
	public void attach() {
		if(setJPAContainer()){

			//grid.setContainerDataSource(container);
			
		}
		super.attach();
	}


	@Override
	public void enter(ViewChangeEvent event) {
		
		//container.removeAllItems();
		cartProducts.clear();
		
		cartList = CartManager.getCartList();
	
		java.util.Iterator<Long> productIds = cartList.keySet().iterator();
		while (productIds.hasNext())
		{
			Long id = productIds.next();
			try{
				Product product = em.find(Product.class, id);
				
				cartProducts.add(new ProductCount(product, cartList.get(id).intValue()));
			}
			catch(NullPointerException e){
				e.printStackTrace();
			}
		}
		
		container =
			    new BeanItemContainer<ProductCount>(ProductCount.class, cartProducts);
		
		grid.setContainerDataSource(container);
		
		
		
	}
	
	
}
