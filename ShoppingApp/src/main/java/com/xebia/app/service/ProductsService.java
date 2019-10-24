package com.xebia.app.service;

import java.util.List;

import com.xebia.app.dto.Product;

public interface ProductsService {

	List<Product> getProducts();

	String getUserType(String username);

	List<Product> getSelectedProductDetails(String[] selectedProducts);

	List<Product> getFilteredProducts(List<Product> products);

	int userExistenceDays(String userName);

	long calculateConditionBasedDiscount(String userType, List<Product> filteredProducts, int daysUserExistInSystem);

}
