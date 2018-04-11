package com.example.engine;

import com.example.utils.Serializer;
import com.example.model.AisleLocations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Objects;

@Component
public class AisleLocationManager {

    private static final String PRODUCT_JSON = "product_response.json";
    private Serializer serializer;

    @Autowired
    public AisleLocationManager(Serializer serializer) {
        this.serializer = serializer;
    }

    public AisleLocations getAisleLocations() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(PRODUCT_JSON)).getFile());
        return serializer.fromFile(file, AisleLocations.class);
    }

}
