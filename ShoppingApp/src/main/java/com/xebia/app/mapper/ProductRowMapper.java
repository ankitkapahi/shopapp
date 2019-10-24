package com.xebia.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.xebia.app.dto.Product;

public class ProductRowMapper implements RowMapper<Product>{
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

		Product product = new Product();
        
        product.setProductName(rs.getString("product_name"));
        product.setProductPrice(rs.getInt("product_price"));
        product.setIsGrocery(rs.getInt("is_grocery"));

        return product;

    }
}
