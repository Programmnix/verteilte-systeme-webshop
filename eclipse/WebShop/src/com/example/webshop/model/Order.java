package com.example.webshop.model;



import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


public class Order {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	private User user;
	
	private double sum;
	

	/*
    @OneToMany(orphanRemoval=true)
    @JoinColumn(name="PRODUCT_ID")
	private List<Product> products; 
	*/
	
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	

	
	/**
	 * @param user
	 * @param products
	 * @param sum
	 */
	public Order(User user, List<Product> products, double sum) {
		super();
		this.user = user;
		//this.products = products;
		this.sum = sum;
	}




	public long getId() {
		return id;
	}

	
	public void setId(long id) {
		this.id = id;
	}

	
	public User getUser() {
		return user;
	}

	
	public void setUser(User user) {
		this.user = user;
	}

	/*
	public List<Product> getProducts() {
		return products;
	}

	
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	*/
	
	public double getSum() {
		return sum;
	}

	
	public void setSum(double sum) {
		this.sum = sum;
	}
	
	

}

