package com.kang.domain;


public class SalesLineItem {
	private int quantity;
	private ProductDescription description;
	
	public SalesLineItem(ProductDescription desc, int quantity) {
		this.description = desc;
		this.quantity = quantity;
	}

	public Money getSubtotal(){
		return description.getPrice().times(quantity);
	}
	
	// 영수증 명세시 각 리스트의 아이템들을 보여주기 위한 메서드.
	@Override
	public String toString(){
		return description.getDescription()+"       "+description.getPrice().getPrice()+" * "+quantity+"      =     "+getSubtotal().getPrice();
	}
}
