package com.kang.domain;


public class Store {
//	private String name;
//	private String address;
	
	private ProductCatalog catalog = new ProductCatalog();
	private Register register = new Register(catalog);
	
	public Register getRegister(){
		return register;
	}
}
