package com.example.engine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.model.ProductResponse;
import com.example.model.ProductResponses;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductManagerTest {
	
	@MockBean
	private DatabaseManager databaseManager;
	
	@Autowired
	private ProductManager productManager;	
	
	private ProductResponse testProduct;
	
	@Before 
	public void setup() {
		testProduct = new ProductResponse();
		testProduct.setProductDescription("TestProductDescription");
		testProduct.setProductName("TestProductName");
		testProduct.setProductNumber("TestProductNumber");
	}
	
	@Test
    public void shouldFindProduct() {
		ProductResponses productResponses = new ProductResponses();
		List<ProductResponse> productResponseList = new ArrayList<ProductResponse>();
		productResponseList.add(testProduct);
		productResponses.setProductResponses(productResponseList);
		
		Mockito.when(databaseManager.getProducts()).thenReturn(productResponses);
        ProductResponse response = productManager.findProduct("TestProductNumber");
        assertThat(response.getProductDescription()).isEqualTo(testProduct.getProductDescription());
        assertThat(response.getProductName()).isEqualTo(testProduct.getProductName());
        assertThat(response.getProductNumber()).isEqualTo(testProduct.getProductNumber());
    }

	
	@Test
    public void shouldNotFindProduct() {
		ProductResponses productResponses = new ProductResponses();
		List<ProductResponse> productResponseList = new ArrayList<ProductResponse>();
		productResponses.setProductResponses(productResponseList);
		
		Mockito.when(databaseManager.getProducts()).thenReturn(productResponses);
        ProductResponse response = productManager.findProduct("TestProductNumber");
        assertThat(response).isEqualTo(null);
    }
	
	@Test
    public void shouldWriteProduct() {
		ProductResponses productResponses = new ProductResponses();
		List<ProductResponse> productResponseList = new ArrayList<ProductResponse>();
		productResponses.setProductResponses(productResponseList);
		
		Mockito.when(databaseManager.getProducts()).thenReturn(productResponses);
		Mockito.when(databaseManager.setProducts(any(ProductResponses.class))).thenReturn(true);
		//TODO: Would like to verify that the contents are actually being written here
		
		ProductResponse response = productManager.insertProduct(testProduct.getProductNumber(), testProduct.getProductName(), testProduct.getProductDescription());
    }
}
