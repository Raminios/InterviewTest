package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.engine.ProductManager;
import com.example.exception.NotFoundException;
import com.example.exception.WriteFailException;
import com.example.model.ProductResponse;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductManager productManager;

    @Autowired
    public ProductController(ProductManager productManager) {
        this.productManager = productManager;
    }

    @GetMapping(value = "/{productNumber}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ProductResponse getProduct(@PathVariable String productNumber) throws NotFoundException {
    	ProductResponse product = productManager.findProduct(productNumber);
    	
    	if(product != null) {
    		return product;
    	} else {
    		throw new NotFoundException();
    	}
    }
    
    @PutMapping(value = "/{productNumber}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ProductResponse putProduct(@PathVariable String productNumber, 
						    		@RequestParam(value = "productName", required = true) String productName,
						    		@RequestParam(value = "productDescription", required = true) String productDescription) throws WriteFailException {
    	ProductResponse product = productManager.insertProduct(productNumber, productName, productDescription);
    	
    	if(product != null) {
    		return product;
    	} else {
    		throw new WriteFailException();
    	}
    }

}
