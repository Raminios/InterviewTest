package com.example.engine;

import com.example.model.AisleLocations;
import com.example.model.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductManager {

    private AisleLocationManager aisleLocationManager;

    @Autowired
    public ProductManager(AisleLocationManager aisleLocationManager) {
        this.aisleLocationManager = aisleLocationManager;
    }

    public ProductResponse createProductsResponse() {
        // TODO: put your code

        AisleLocations aisleLocations = aisleLocationManager.getAisleLocations();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductName("name");
        productResponse.setProductDescription("description");
        return productResponse;
    }
}
