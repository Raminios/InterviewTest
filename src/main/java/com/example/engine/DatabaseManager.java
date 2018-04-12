package com.example.engine;

import java.io.File;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.AisleLocations;
import com.example.model.ProductResponses;
import com.example.utils.Serializer;

@Component
public class DatabaseManager {

    private static final String AISLE_JSON = "aisles.json";
    private static final String PRODUCTS_JSON = "products.json";
    
    private Serializer serializer;

    @Autowired
    public DatabaseManager(Serializer serializer) {
        this.serializer = serializer;
    }

    public AisleLocations getAisleLocations() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(AISLE_JSON)).getFile());
        return serializer.fromFile(file, AisleLocations.class);
    }
    
    public ProductResponses getProducts() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(PRODUCTS_JSON)).getFile());
        return serializer.fromFile(file, ProductResponses.class);
    }
    
    public Boolean setProducts(ProductResponses products) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(PRODUCTS_JSON)).getFile());
        return serializer.toJsonFile(products, file);
    }
}
