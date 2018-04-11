package com.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class Serializer {
    private ObjectMapper objectMapper;

    @Autowired
    public Serializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> String toJsonString(T response) {
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException("Error serialising " + response);
        }
    }

    public <T> T fromJsonString(String string, Class<T> dataType) {
        try {
            return objectMapper.readValue(string, dataType);
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing " + string);
        }
    }

    public <T> T fromFile(File string, Class<T> dataType) {
        try {
            T t = objectMapper.readValue(string, dataType);
            return t;
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing " + string);
        }
    }
}
