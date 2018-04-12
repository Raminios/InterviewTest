package com.example.engine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.ProductResponse;
import com.example.model.ProductResponses;

@Component
public class ProductManager {

    private DatabaseManager databaseManager;

    @Autowired
    public ProductManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public ProductResponse findProduct(String productNumber) {
        ProductResponses productResponses = databaseManager.getProducts();
        return productResponses.findOneByProductNumber(productNumber);
    }
    
    public ProductResponse insertProduct(String productNumber, String productName, String productDescription) {
    	ProductResponse product = new ProductResponse();
    	product.setProductDescription(productDescription);
    	product.setProductName(productDescription);
    	product.setProductNumber(productNumber);
    	
        ProductResponses productResponses = databaseManager.getProducts();
        List<ProductResponse> products = productResponses.getProductResponses();
        products.add(product);
        
        productResponses.setProductResponses(products);
        
        databaseManager.setProducts(productResponses);
        
        return product;
    }
}
