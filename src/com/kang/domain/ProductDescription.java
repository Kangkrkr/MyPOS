package com.kang.domain;


public class ProductDescription {
	private ItemID id;
	private Money price;
	private String description;
	
	public ProductDescription(ItemID id, Money price, String description){
		this.id = id;
		this.price = price;
		this.description = description;
	}
	
	public ItemID getItemID(){
		return id;
	}
	
	public Money getPrice(){
		return price;
	}
	
	public String getDescription(){
		return description;
	}
	
	// 각 물품을 리스트의 아이템에 출력하고자 할때 사용.
	@Override
	public String toString(){
		return "ID : "+id.getId()+"           가격 : "+price.getPrice()+"          물품명 : "+description;
	}
}
