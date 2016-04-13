package com.example.webshop;


import java.util.ArrayList;
import java.util.HashMap;

import javax.jws.soap.SOAPBinding.Use;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.eclipse.persistence.internal.oxm.mappings.Login;
import org.openqa.selenium.WebDriver.Navigation;

import com.example.webshop.model.Order;
import com.example.webshop.model.Product;
import com.example.webshop.model.User;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;


public class OrderView extends Panel implements View {

	private transient EntityManager entityManager;
	private User currentUser = null;
	private TextField surename;
	private TextField name;
	private TextField streetname;
	private TextField housenumber;
	private TextField postcode;
	private TextField cityname;
	private Grid grid;
	
	

	private HashMap<Long, Integer> cartList = null;
	private ArrayList<ProductCount> cartProducts = new ArrayList<ProductCount>();
	private BeanItemContainer<ProductCount> container;
	private Button order;
	
	public OrderView() {
		setSizeFull();
		
		entityManager =  Persistence.createEntityManagerFactory(WebshopUI.PERSISTENCE_UNIT)
				.createEntityManager();
		
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setMargin(true);
		
		verticalLayout.addComponent(buildContactData());
		
		verticalLayout.addComponent(buildPriceList());
		

		verticalLayout.addComponent(buildOderFooter());
		
		setContent(verticalLayout);
		
	}
	
	public VerticalLayout buildContactData() {
		VerticalLayout verticalLayout = new VerticalLayout();
		surename= new TextField("Surename");
		surename.setReadOnly(true);
		name= new TextField("Name");
		name.setReadOnly(true);
		
		HorizontalLayout street = new  HorizontalLayout();
		streetname = new TextField("Street");
		streetname.setReadOnly(true);
		housenumber = new TextField();
		housenumber.setReadOnly(true);
		
		
		street.addComponent(streetname);
		street.addComponent(housenumber);
		
		//Ort
		HorizontalLayout city = new HorizontalLayout();
		postcode = new TextField("City");
		postcode.setReadOnly(true);
		cityname = new TextField();
		cityname.setReadOnly(true);;
		
		city.addComponent(postcode);
		city.addComponent(cityname);
		
		verticalLayout.addComponent(surename);
		verticalLayout.addComponent(name);
		verticalLayout.addComponent(street);
		verticalLayout.addComponent(city);
		
		return verticalLayout;
	}
	
	public Component buildPriceList() {
		VerticalLayout layout= new VerticalLayout();
		layout.setSpacing(true);
		grid = new Grid();
		grid.setWidth("100%");
		grid.setHeight("400px");
		
		layout.addComponent(grid);
		
		return layout;
	}
	
	
	public Component buildOderFooter() {
		HorizontalLayout  horizontalLayout= new HorizontalLayout();
		order = new Button("Order");
		order.addStyleName(ValoTheme.BUTTON_PRIMARY);
		order.addClickListener(new ClickListener() {
			


			@Override
			public void buttonClick(ClickEvent event) {
				
				if(acceptOrder()){
					CartManager.clearCart();
					Notification.show("Successfully orderd", Type.HUMANIZED_MESSAGE);
					getUI().getNavigator().navigateTo("Products");
				}
				else{
					Notification.show("Error", "Please try again later", Type.ERROR_MESSAGE);
					
				}
			}

		});
		horizontalLayout.addComponent(order);
		return horizontalLayout;
		
	}
	
	protected boolean acceptOrder() {
		try{

			EntityManager entityManager = Persistence
					.createEntityManagerFactory(WebshopUI.PERSISTENCE_UNIT)
					.createEntityManager();
			ArrayList<Product> products = new ArrayList<Product>();
			double sum = 0.0;
			for(ProductCount productCount:cartProducts){
				products.add(productCount.getProduct());
				sum += productCount.getSum();
				
			}
			//order.setProducts(products);
			

			entityManager.getTransaction().begin();
			

			User user = entityManager.find(User.class, currentUser.getId());
			Order order = new Order(user, products, sum);
			
			entityManager.persist(order);
			entityManager.getTransaction().commit();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return true;
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		if(LoginManager.isLogedIn()){
			currentUser = LoginManager.getCurrentUser();
			
			updateScreen();
		}
		else{
			LoginManager.forwardToLogin(getUI());
		}

	}
	
	

	private void updateScreen() {
		surename.setReadOnly(false);
		surename.setValue(currentUser.getSurname());
		surename.setReadOnly(true);
		name.setReadOnly(false);
		name.setValue(currentUser.getName());
		name.setReadOnly(true);
		streetname.setReadOnly(false);
		streetname.setValue(currentUser.getStreet());
		streetname.setReadOnly(true);
		housenumber.setReadOnly(false);
		housenumber.setValue(currentUser.getHousenumber());
		housenumber.setReadOnly(true);
		postcode.setReadOnly(false);
		postcode.setValue(currentUser.getPostcode());
		postcode.setReadOnly(true);
		cityname.setReadOnly(false);
		cityname.setValue(currentUser.getCity());
		cityname.setReadOnly(true);
		
		
		
		
		cartProducts.clear();
		
		cartList = CartManager.getCartList();
		
		java.util.Iterator<Long> productIds = cartList.keySet().iterator();
		while (productIds.hasNext())
		{
			Long id = productIds.next();
			try{
				Product product = entityManager.find(Product.class, id);
				
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
