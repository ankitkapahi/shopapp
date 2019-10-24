/**
 * 
 */
package com.xebia.app.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xebia.app.dto.Product;
import com.xebia.app.enumerations.DiscountCalculationStrategyEnum;
import com.xebia.app.repository.ProductsRepository;
import com.xebia.app.service.ProductsService;

/**
 * @author ANKIT
 * Created On : 10/24/2019
 */
@Component("productsService")
public class ProductsServiceImpl implements ProductsService {

	/**
	 * autowiring products repository
	 */
	@Autowired
	private ProductsRepository productsRepository;

	/**
	 * constants to compare user type saved in database and based on which discount
	 * is being calculated
	 */
	private static final String EMPLOYEE_USER_TYPE_CODE = "E";
	/**
	 * * constants to compare user type saved in database and based on which
	 * discount is being calculated
	 */
	private static final String AFFILIATED_USER_TYPE_CODE = "A";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xebia.app.service.ProductsService#getProducts() Below method is
	 * invoked to fetch all the products
	 */
	@Override
	public List<Product> getProducts() {
		return productsRepository.getProducts();
	}

	@Override
	public String getUserType(final String username) {
		// TODO Auto-generated method stub
		return productsRepository.getUserType(username);
	}

	@Override
	public List<Product> getSelectedProductDetails(final String[] selectedProducts) {
		return productsRepository.getSelectedProductDetails(selectedProducts);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xebia.app.service.ProductsService#getFilteredProducts(java.util.List)
	 * filter products based on product type if it is grocery then removing it from
	 * the list
	 */
	@Override
	public List<Product> getFilteredProducts(final List<Product> products) {

		final List<Product> filteredProducts = new ArrayList<Product>();

		final Iterator<Product> itr = products.iterator();

		while (itr.hasNext()) {
			Product product = itr.next();
			if (product.getIsGrocery() == 0) {
				filteredProducts.add(product);
			}
		}

		return filteredProducts;
	}

	/**
	 * @param strategyEnum
	 * @param filteredProducts
	 * @return this method invokes strategy specific method based on input strategy
	 */
	private long performDiscountCalculation(final DiscountCalculationStrategyEnum strategyEnum,
			final List<Product> filteredProducts) {
		// TODO Auto-generated method stub
		return strategyEnum.execute(filteredProducts);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xebia.app.service.ProductsService#userExistenceDays(java.lang.String)
	 * Method is to fetch number of days between current date and user creation date
	 */
	@Override
	public int userExistenceDays(final String userName) {
		return productsRepository.userExistenceDays(userName);
	}

	/**
	 * @param userType
	 * @param filteredProducts
	 * @param daysUserExistInSystem
	 * @return discount this method calculates discount and return it to controller
	 */
	public long calculateConditionBasedDiscount(final String userType, final List<Product> filteredProducts,
			final int daysUserExistInSystem) {
		long discount;
		if (EMPLOYEE_USER_TYPE_CODE.equalsIgnoreCase(userType)) {
			discount = performDiscountCalculation(DiscountCalculationStrategyEnum.DISCOUNT_FOR_EMPLOYEE,
					filteredProducts);
		} else if (AFFILIATED_USER_TYPE_CODE.equalsIgnoreCase(userType)) {
			discount = performDiscountCalculation(DiscountCalculationStrategyEnum.DISCOUNT_FOR_AFFILIATED_USER,
					filteredProducts);
		} else if (2 <= (daysUserExistInSystem / 365)) {
			discount = performDiscountCalculation(DiscountCalculationStrategyEnum.DISCOUNT_FOR_OLD_CUSTOMERS,
					filteredProducts);
		} else {
			discount = performDiscountCalculation(DiscountCalculationStrategyEnum.DISCOUNT_FOR_EVERY_100$,
					filteredProducts);
		}
		return discount;
	}

}
