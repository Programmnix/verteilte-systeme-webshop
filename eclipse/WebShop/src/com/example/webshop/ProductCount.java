package com.example.webshop;

import java.io.Serializable;

import com.example.webshop.model.Product;

public class ProductCount implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Product product;
	private String name;
	private int count;
	private double sum;
	
	public ProductCount(Product product, int count){
		this.product = product;
		this.name = product.getName();
		this.count = count;
		this.sum = product.getPrice() * count;
	}

	
	

	
	
	public Product getProduct() {
		return product;
	}





	
	public void setProduct(Product product) {
		this.product = product;
	}





	public int getCount() {
		return count;
	}

	
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getName() {
		return name;
	}


	
	public void setName(String name) {
		this.name = name;
	}


	
	public double getSum() {
		return sum;
	}


	
	public void setSum(double sum) {
		this.sum = sum;
	}
	
}
