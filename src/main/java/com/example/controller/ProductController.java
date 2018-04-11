package com.example.controller;

import com.example.engine.ProductManager;
import com.example.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductManager productManager;

    @Autowired
    public ProductController(ProductManager productManager) {
        this.productManager = productManager;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Products getProduct() {
        return productManager.getProducts();
    }

}
