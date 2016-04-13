package com.example.webshop;

import java.util.HashMap;

import com.example.webshop.model.Product;
import com.vaadin.server.VaadinSession;

public abstract class CartManager {
	
	public static final String SESSION_CART_LIST = "session_cart";
	
	public static void addProduct(Product product){
		
		HashMap<Long, Integer> cartlist  = getCartList();
		Integer integer = cartlist.get(product.getId());
		if(integer == null){
			integer = new Integer(0);
		}
		int count = integer.intValue();
		count++;
		
		cartlist.put(product.getId(), new Integer(count));
		
		saveCartList(cartlist);
		
	}
	
	public static void addProduct(Product product, int anzahl){
		
		HashMap<Long, Integer> cartlist  = getCartList();
		Integer integer = cartlist.get(product.getId());
		if(integer == null){
			integer = new Integer(0);
		}
		int count = integer.intValue();
		count += anzahl;
		
		cartlist.put(product.getId(), new Integer(count));
		
		saveCartList(cartlist);
		
	}
	
	public static void removeProduct(Product product){
		HashMap<Long, Integer> cartlist  = getCartList();
		Integer integer = cartlist.get(product.getId());
		if(integer == null){
			integer = new Integer(0);
		}
		int count = integer.intValue();
		count--;
		
		cartlist.put(product.getId(), new Integer(count));
		
		saveCartList(cartlist);
		
	}
	
	public static void removeProduct(Product product, int anzahl){
		HashMap<Long, Integer> cartlist  = getCartList();
		Integer integer = cartlist.get(product.getId());
		if(integer == null){
			integer = new Integer(0);
		}
		int count = integer.intValue();
		count -= anzahl;
		
		cartlist.put(product.getId(), new Integer(count));
		
		saveCartList(cartlist);
		
	}
	
	public static VaadinSession getSession(){
		VaadinSession s = VaadinSession.getCurrent();
		if (s == null) {
            throw new IllegalStateException(
                    "No session found for current thread");
        }
		return s;
	}
	
	public static HashMap<Long, Integer> getCartList(){
		VaadinSession session = getSession();
		HashMap<Long, Integer> cartlist = (HashMap<Long, Integer>) session.getAttribute(SESSION_CART_LIST);
		if(cartlist == null){
			System.out.println("getCartList == null");
			cartlist = new HashMap<Long, Integer>();
		}
		return cartlist;
	}
	
	public static void saveCartList(HashMap<Long, Integer> cartlist) {
		getSession().setAttribute(SESSION_CART_LIST, cartlist);	
	}
	
	public static void clearCart() {
		saveCartList(new HashMap<Long, Integer>());
		
	}
	
	

}
