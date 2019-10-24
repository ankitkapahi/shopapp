package com.xebia.app.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xebia.app.dto.Product;
import com.xebia.app.mapper.ProductRowMapper;

/**
 * @author ANKIT
 * Created On : 10/24/2019
 */
@Repository
public class ProductsRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * constant for the query to fetch all products
	 */
	private static final String SQL_PRODUCTS = "select * from tb_product";
	/**
	 * constant for the query to fetch user type
	 */
	private static final String SQL_USER_TYPE = "select tut.user_type from tb_user tu, tb_user_type tut where tu.fk_tb_user_type=tut.pk_tb_user_type and tu.user_name = ?";
	/**
	 * constants for the query to fetch selected products
	 */
	private static final String SQL_SELECTED_PRODUCTS = "select * from tb_product where product_name = ?";
	/**
	 * constants for the query to fetch days with which user in the system
	 */
	private static final String SQL_USER_EXISTENCE_DAYS = "select curdate()-created_on from tb_user where user_name = ?";
	
	public List<Product> getProducts() {

		List<Product> products = new ArrayList<Product>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL_PRODUCTS);

		for (Map<String, Object> row : rows) {
			Product product = new Product();
			product.setProductName((String) row.get("product_name"));
			product.setProductPrice((int) row.get("product_price"));
			product.setIsGrocery((int) row.get("is_grocery"));

			products.add(product);
		}

		return products;
	}

	public String getUserType(String username) {
		return jdbcTemplate.queryForObject(SQL_USER_TYPE, new Object[] { username }, String.class);
	}

	public List<Product> getSelectedProductDetails(String[] selectedProducts) {
		List<Product> products = new ArrayList<Product>();
		for (String selectedProduct : selectedProducts) {
			
			Product product = jdbcTemplate.queryForObject(SQL_SELECTED_PRODUCTS, new Object[] { selectedProduct }, new ProductRowMapper());

			products.add(product);
			
		}
		return products;
	}

	public int userExistenceDays(String userName) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(SQL_USER_EXISTENCE_DAYS, new Object[] { userName }, Integer.class);
	}
}