package com.xebia.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xebia.app.dto.Product;
import com.xebia.app.service.ProductsService;

/**
 * @author ANKIT Created On : 10/24/2019 This is main controller class having
 *         all the resources like to show login page, home page and to calculate
 *         discount based on various conditions and invoking service methods for
 *         further processing
 */
@RestController
public class HomeController {
	/**
	 * Autowiring productsService
	 */
	@Autowired
	private ProductsService productsService;

	/**
	 * @param username
	 * @param password
	 * @return ModelAndView
	 */
	@GetMapping("/Products")
	public ModelAndView fetchProducts(@RequestParam("username") final String username) {
		ModelAndView model = null;

		try {

			final String userType = productsService.getUserType(username);
				model = new ModelAndView("home.jsp");
			
			
			
			// Below method is invoked to fetch all the products
			final List<Product> products = productsService.getProducts();

			// setting output parameters in model
			model.addObject("products", products);
			model.addObject("usertype", userType);
			model.addObject("username", username);
		} catch (Exception e) {
			//logging
			model = new ModelAndView("loginfailed.jsp");
		}
		return model;
	}

	/**
	 * @param selectedProducts
	 * @param userType
	 * @param userName
	 * @return ModelAndView
	 * 
	 * Below API is to calculate net payable amount by the customer
	 */
	@GetMapping("/calculate")
	public ModelAndView calculateDiscount(
			@RequestParam("selectedProducts") final String[] selectedProducts, @RequestParam("usertype") final String userType,
			@RequestParam("username") final String userName) {
		long discount;

		final ModelAndView model = new ModelAndView("home.jsp");
		try {

			// Below method is invoked to fetch all the products selected i.e product price,
			// type
			final List<Product> products = productsService.getSelectedProductDetails(selectedProducts);

			// filter products based on product type if it is grocery then removing it from
			// the list
			final List<Product> filteredProducts = productsService.getFilteredProducts(products);

			// To fetch the number of days logged in user exists in the application
			final int daysUserExistInSystem = productsService.userExistenceDays(userName);

			discount = productsService.calculateConditionBasedDiscount(userType, filteredProducts,
					daysUserExistInSystem);

			final long totalPrice = products.stream().mapToInt(fp -> fp.getProductPrice()).sum();

			final long discountedPrice = totalPrice - discount;

			// Below method is invoked to fetch all the products used to repaint the home
			// screen
			final List<Product> productss = productsService.getProducts();

			model.addObject("products", productss);
			model.addObject("usertype", userType);
			model.addObject("username", userName);
			model.addObject("discountedPrice", discountedPrice);
		} catch (Exception e) {
				//logging
				
		}

		return model;
	}

	@RequestMapping("/login")
	public ModelAndView loginPage() {
		return new ModelAndView("login.jsp");
	}

	@RequestMapping("/logout-success")
	public ModelAndView logoutPage() {
		return new ModelAndView("logout.jsp");
	}

}
