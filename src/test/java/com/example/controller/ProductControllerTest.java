package com.example.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.engine.ProductManager;
import com.example.model.ProductResponse;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private ProductManager productManager;

    @Test
    public void shouldReturnProduct() throws Exception {
    	ProductResponse productResponse = new ProductResponse();
    	productResponse.setProductDescription("TestProductDescription");
    	productResponse.setProductName("TestProductName");
    	productResponse.setProductNumber("TestProductNumber");
    	
		Mockito.when(productManager.findProduct("TestProductNumber")).thenReturn(productResponse);
		
		mvc.perform(get("/product/TestProductNumber")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.product_description", is(productResponse.getProductDescription())))
				.andExpect(jsonPath("$.product_name", is(productResponse.getProductName())))
				.andExpect(jsonPath("$.product_number", is(productResponse.getProductNumber())));

    }
    
    @Test
    public void shouldReturn404() throws Exception {
    	mvc.perform(get("/product/TestProductNumber")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

    }
    
    @Test
    public void shouldSaveProduct() throws Exception {
    	ProductResponse productResponse = new ProductResponse();
    	productResponse.setProductDescription("TestProductDescription");
    	productResponse.setProductName("TestProductName");
    	productResponse.setProductNumber("TestProductNumber");
    	
		Mockito.when(productManager.insertProduct(productResponse.getProductNumber(), productResponse.getProductName(), productResponse.getProductDescription())).thenReturn(productResponse);
    	
		mvc.perform(put("/product/" + productResponse.getProductNumber() + "?productName=" + productResponse.getProductName() + "&productDescription=" + productResponse.getProductDescription(), productResponse)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
		    	.andExpect(jsonPath("$.product_description", is(productResponse.getProductDescription())))
				.andExpect(jsonPath("$.product_name", is(productResponse.getProductName())))
				.andExpect(jsonPath("$.product_number", is(productResponse.getProductNumber())));
    }
    
    @Test
    public void shouldReturn500() throws Exception {
    	ProductResponse productResponse = new ProductResponse();
    	productResponse.setProductDescription("TestProductDescription");
    	productResponse.setProductName("TestProductName");
    	productResponse.setProductNumber("TestProductNumber");
    	
    	mvc.perform(put("/product/" + productResponse.getProductNumber() + "?productName=" + productResponse.getProductName() + "&productDescription=" + productResponse.getProductDescription(), productResponse)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError());
    }
    
}
