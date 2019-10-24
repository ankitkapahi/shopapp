package com.xebia.app.enumerations;

import java.util.List;

import com.xebia.app.dto.Product;

public enum DiscountCalculationStrategyEnum {
	
	 DISCOUNT_FOR_EMPLOYEE {
		    @Override
		    public long execute(List<Product> filteredProducts) {
		      int sum=filteredProducts.stream().mapToInt(fp -> fp.getProductPrice()).sum();
		      
		      return (sum*30)/100;
		    }
		  },
		  
	 DISCOUNT_FOR_AFFILIATED_USER {
		    @Override
		    public long execute(List<Product> filteredProducts) {
		    	int sum=filteredProducts.stream().mapToInt(fp -> fp.getProductPrice()).sum();
			      
			      return (sum*10)/100;
		    }
		  },
	
	DISCOUNT_FOR_OLD_CUSTOMERS {
	    @Override
	    public long execute(List<Product> filteredProducts) {
	    	int sum=filteredProducts.stream().mapToInt(fp -> fp.getProductPrice()).sum();
		      
		      return (sum*5)/100;
	    }
	  },
	  
	  DISCOUNT_FOR_EVERY_100$ {
		    @Override
		    public long execute(List<Product> filteredProducts) {
		    	int sum=filteredProducts.stream().mapToInt(fp -> fp.getProductPrice()).sum();
			      long remainder = sum/100;
			      return remainder*5;
		    }
		  };

		  public abstract long execute(List<Product> filteredProducts);

}
