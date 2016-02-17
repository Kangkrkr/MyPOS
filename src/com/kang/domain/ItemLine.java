package com.kang.domain;

public class ItemLine {
	
	private String itemName;
	private int price;
	private int quantity;
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getTotalPrice() {
		return getPrice()*getQuantity();
	}
	
	@Override
	public String toString(){
		return getItemName()+'\t'+getPrice()+" X "+getQuantity()+"\t = "+getTotalPrice();
	}
}
