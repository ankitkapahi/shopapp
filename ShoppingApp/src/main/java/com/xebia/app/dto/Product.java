package com.xebia.app.dto;

/**
 * @author ANKIT
 *  Created On : 10/24/2019
 *  product DTO
 *
 */
public class Product {
	
	public Product(String productName, int productPrice, int isGrocery) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.isGrocery = isGrocery;
	}
	public Product() {
		super();
	}
	private String productName ;
	private int productPrice;
	private int isGrocery;
	
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getIsGrocery() {
		return isGrocery;
	}
	public void setIsGrocery(int isGrocery) {
		this.isGrocery = isGrocery;
	}
	

}
