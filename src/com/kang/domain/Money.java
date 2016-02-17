package com.kang.domain;

public class Money {
	private int total = 0;
	private int price = 0;
	
	public Money(){}
	
	public Money(int price){
		this.price = price;
	}

	public int getTotal() {
		return total;
	}

	public int getPrice(){
		return price;
	}
	
	public Money add(Money money){
		total += money.getPrice();
		return this;
	}
	
	public Money minus(Money money){
		total -= money.getTotal();
		return this;
	}
	
	public Money times(int quantity){
		return new Money(price * quantity);
	}
}
