package com.example.webshop;

import java.util.Date;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

import com.example.webshop.model.Product;
import com.example.webshop.model.User;

public class DemoDataGenerator {

	final static String[] password = {"test", "test123", "123test", "testtest"};
	final static String[] surname = {"Dominik", "Kim", "Nina", "Flo", "Christian"};
	final static String[] name = {"Beinrucker", "Klein", "Mausel", "Merkel", "Pertersons"};
	final static Date[] birthdate = {new Date(1990, 5, 23), new Date(1994, 2, 11), new Date(1950, 6, 7), new Date(1940, 1, 23), new Date(1993, 9, 28)};
	final static String[] street = {"Kriegsstr.", "Keiserallee", "Goethestr.", "Siegerplatz", "Erzbergerstr."};
	final static String[] postcode = {"77612.", "87432", "24245", "71623", "67432"};
	final static String[] housenumber = {"77", "87", "24", "71", "67"};
	final static String[] city = {"Karlsruhe", "Frankfurt", "Muenchen", "Berlin", "Wiesbaden"};
	final static String[] products = {"Mainboard", "Festplatte", "Grafikkarte", "Processor", "Arbeitsspeicher"};
	final static String[] description = {"Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."};
	

	private static Random random = new Random(100);
	
	public static int randInt(Object[] arr){
		return (int)(random.nextDouble() * arr.length);
	}
	public static void create() {

		EntityManager em = Persistence
				.createEntityManagerFactory(WebshopUI.PERSISTENCE_UNIT)
				.createEntityManager();

		em.getTransaction().begin();
		User user = new User("D", "K", "test", new Date(), "Teststr", "123", "12345", "Karlsruhe");
		em.persist(user);
		for(int i = 0; i < 100; i++){
			 user = new User(surname[randInt(surname)],
			                             name[randInt(name)], 
			                              password[randInt(password)], 
			                              birthdate[randInt(birthdate)], 
			                              street[randInt(street)],
			                 			 housenumber[randInt(housenumber)], 
			                 			 postcode[randInt(postcode)],
			                 			 city[randInt(city)]);
			em.persist(user);
		}
		Random random = new Random(300);
		for(int i = 0; i < 100; i++){
			Product product = new Product(products[randInt(products)],
									description[randInt(description)], 
									random.nextDouble());
			em.persist(product);
		}
		
		em.getTransaction().commit();
	}
}
