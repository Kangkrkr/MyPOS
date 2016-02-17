package com.kang.domain;
import java.util.HashMap;
import java.util.Map;


public class ProductCatalog {
	private Map<Integer, ProductDescription> descriptions = new HashMap<>();
	
	public ProductCatalog(){
		
	}

	public ProductDescription getProductDescription(int id) {
		return descriptions.get(id);
	}
	
	public void putItem(int id, Money price, String desc){
		if(!descriptions.containsKey(id)){
			descriptions.put(id, new ProductDescription(new ItemID(id), price, desc));
		}
	}
	
	public Map<Integer, ProductDescription> getDescriptions(){
		return descriptions;
	}
	
	public int getProductAmount(){
		return descriptions.size();
	}
}
