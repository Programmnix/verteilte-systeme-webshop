package com.example.webshop;

import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;

import org.apache.tools.ant.types.selectors.ExtendSelector;
import org.eclipse.persistence.internal.jpa.config.persistenceunit.DataServiceImpl;
import org.eclipse.persistence.jpa.config.DataService;

import com.example.webshop.model.Product;
import com.google.gwt.resources.css.InterfaceGenerator;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.server.VaadinSession;


public class ProductTable extends Table implements Serializable{
	
	private int setJPACount = 0;
	transient JPAContainer<Product>  products = null;
	
	public boolean setJPAContainer(){
		if(products == null){
			setJPACount++;
			products = JPAContainerFactory.makeNonCached(Product.class, WebshopUI.PERSISTENCE_UNIT);

			setContainerDataSource(products);
			
			
			System.out.println("JPACount would called: " + setJPACount);
			return true;
		}
		return false;
	}
	
	
	
	@Override
	public void attach() {
		boolean test = setJPAContainer();
		if(test){
			resetPageBuffer();
			setContainerDataSource(products);
		}
		
		super.attach();
		
	}



	public ProductTable(){
		setSizeFull();
		
		
		//GeneratedPropertyContainer gpc = new GeneratedPropertyContainer(products);
		setJPAContainer();
		
		addGeneratedColumn("Cart", new Table.ColumnGenerator() {
	        public Component generateCell(Table table, Object itemId, Object columnId) {
	            //BeanItem<MyBean> item = myContainer.getItem(itemId);
	            //MyBean bean = item.getBean();
	            Button b = new Button("AddCart");
	            b.addClickListener(new MyClickListener(itemId));
	            return b;
	        }
	     });
		setVisibleColumns("name", "price", "id", "Cart");
		
	}
	
	
	public void setFilter(String filter){
		
		//persons.addContainerFilter(new CustomFilter(filter));
		
	}
	

	
	class CustomFilter implements Container.Filter{

		private String filter;
		public CustomFilter(String filter) {
			this.filter = filter;
		}
		
		@Override
		public boolean passesFilter(Object itemId, Item item)
				throws UnsupportedOperationException {
			
			
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean appliesToProperty(Object propertyId) {
			// TODO Auto-generated method stub
			return false;
		}
		
		
	}
	
	class MyClickListener implements ClickListener {
		
		
		private Object itemId;

		public MyClickListener(Object itemId) {
			// TODO Auto-generated constructor stub
			this.itemId =itemId;
		}
		
		@Override
		public void buttonClick(ClickEvent event) {
			// TODO Auto-generated method stub
			Notification.show("Add to Cart: " + itemId);
			CartManager.addProduct(products.getItem(itemId).getEntity());						
		}
	}

}
