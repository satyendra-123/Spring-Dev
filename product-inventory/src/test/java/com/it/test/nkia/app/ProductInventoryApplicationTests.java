package com.it.test.nkia.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import com.it.test.nkia.app.entity.Product;


/**
 * 
 * @author kotiys
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProductInventoryApplicationTests {
			
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void contextLoads() {
		
	}
	
	@Test
	public void testProducts_Service() {
		ResponseEntity<List<Product>> response;
		try {
			response = restTemplate.exchange(
					  new URI("/products"),
					  HttpMethod.GET,
					  null,
					  new ParameterizedTypeReference<List<Product>>(){});
			List<Product> prodcts = response.getBody();	
			assertThat(prodcts.size()).isEqualTo(2);
		} catch (RestClientException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
