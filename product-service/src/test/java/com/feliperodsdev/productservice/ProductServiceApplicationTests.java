package com.feliperodsdev.productservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.feliperodsdev.productservice.dtos.CreateProductDto;
import com.feliperodsdev.productservice.model.Product;
import com.feliperodsdev.productservice.repositories.ProductRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class ProductServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductRepositoryImpl productRepository;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
		dymDynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void should_be_able_create_product() throws Exception {
		CreateProductDto productRequest = getProductDto();
		String productRequestString = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/product/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(productRequestString))
				.andExpect(status().isCreated());
		Assertions.assertEquals(3, productRepository.getAllProducts().size());
	}

	@Test
	void should_return_products() throws Exception {
		CreateProductDto productRequest = getProductDto();
		String productRequestString = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/product/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(productRequestString));
		mockMvc.perform(MockMvcRequestBuilders.post("/product/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString));
		mockMvc.perform(MockMvcRequestBuilders.get("/product"))
				.andExpect(status().is(200));
		List<Product> productList = productRepository.getAllProducts();
		Assertions.assertEquals(2, productList.size());
		Assertions.assertEquals("Macbook M1", productList.get(0).getName());
	}

	private CreateProductDto getProductDto(){
		return new CreateProductDto("Macbook M1", "Greatest", BigDecimal.valueOf(2501.7));
	}

}
