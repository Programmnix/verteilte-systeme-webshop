package com.example.webshop.model;



import java.beans.Beans;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;


@Entity
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6966279499444215503L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@Column(length=1000000)
	@Lob
	private String description;
	private double price;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	
	
	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public Product(String name, String description, double price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
	}



	
	public Long getId() {
		return id;
	}



	
	public void setId(Long id) {
		this.id = id;
	}



	
	public void setPrice(double price) {
		this.price = price;
	}



	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getDescription() {
		return description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}

	
	public double getPrice() {
		return price;
	}

	
	public void setPrice(float price) {
		this.price = price;
	}

}
