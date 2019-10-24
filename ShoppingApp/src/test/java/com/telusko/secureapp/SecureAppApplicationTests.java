package com.telusko.secureapp;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.when;

import com.xebia.app.SecureAppApplication;
import com.xebia.app.controller.HomeController;
import com.xebia.app.dto.Product;
import com.xebia.app.repository.ProductsRepository;
import com.xebia.app.service.ProductsService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecureAppApplication.class)
public class SecureAppApplicationTests {

	MockMvc mockMvc;
	
	@InjectMocks
	private HomeController homeController;
	
	@Autowired
	private ProductsService productsService;
	@MockBean
	private ProductsRepository productsRepository;
	
	
	
	@Before
	public void setup() throws Exception{
		// TODO Auto-generated method stub
		mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();

	}
	
	@Test
	public void testfetchProducts() throws Exception {
		
		when(productsRepository.getProducts()).thenReturn(
				Stream.of(new Product("product1", 20, 1), new Product("product2", 50, 0)).collect(Collectors.toList()));
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/Products")
				).andExpect(MockMvcResultMatchers.status().isOk());
		
		
	}
	
	
	@Test
	public void getProductsTest() throws Exception {

		when(productsRepository.getProducts()).thenReturn(
				Stream.of(new Product("product1", 20, 1), new Product("product2", 50, 0)).collect(Collectors.toList()));

		assertEquals(2, productsService.getProducts().size());
	}
	
	@Test
	public void calculateConditionBasedDiscountTest() throws Exception {

		String userType="E";
		List<Product> filteredProducts=Stream.of(new Product("product1", 40, 0), new Product("product2", 60, 0)).collect(Collectors.toList());
		int daysUserExistInSystem = 3;

		assertEquals(30, productsService.calculateConditionBasedDiscount(userType, filteredProducts, daysUserExistInSystem));
	}

}

