package com.example.webshop.model;


public class NoUser extends User {

	@Override
	public boolean isUser() {
		return false;
	}
}
