package com.kang.domain;


public class Register {
	private ProductCatalog catalog;
	private Sale currentSale;
	
	public Register(ProductCatalog catalog){
		this.catalog = catalog;
	}

	public void makeNewSale(){
		currentSale = new Sale();
	}

	public void enterItem(ItemID id, int quantity){
		ProductDescription desc = catalog.getProductDescription(id.getId());
		currentSale.makeLineItem(desc, quantity);
	}
	
	public void endSale(){
		currentSale.becomeComplete();
	}
	
	public void makePayment(Money cashTendered){
		currentSale.makePayment(cashTendered);
	}
	
	public Sale getCurrentSale(){
		return currentSale;
	}
}
