package com.example.engine;

import com.example.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductManager {

    private UpstreamProduct upstreamProduct;
    @Autowired
    public ProductManager(UpstreamProduct upstreamProduct) {
        this.upstreamProduct = upstreamProduct;
    }

    public Products getProducts() {
        // TODO: put your code
        return upstreamProduct.readFile();
    }
}
