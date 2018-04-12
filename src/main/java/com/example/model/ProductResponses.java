package com.example.model;

import java.util.List;
import java.util.stream.Collectors;

public class ProductResponses {
	private List<ProductResponse> productResponses;

    public List<ProductResponse> getProductResponses() {
        return productResponses;
    }

    public void setProductResponses(List<ProductResponse> productResponses) {
        this.productResponses = productResponses;
    }
    
    public ProductResponse findOneByProductNumber(String productNumber) {
    	List<ProductResponse> matchingProducts = productResponses.stream().filter(productResponse -> productResponse.getProductNumber().equals(productNumber)).collect(Collectors.toList());
    	
    	if(matchingProducts.size() == 0) {
    		return null;
    	} else {
        	return matchingProducts.get(0);
    	}
    	//TODO Would be nice to have a robustness check here and throw a "duplicate" exception if there is more than one. 
    }
}
