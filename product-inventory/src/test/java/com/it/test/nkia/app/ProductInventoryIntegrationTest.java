package com.it.test.nkia.app;

import java.util.List;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.it.test.nkia.app.entity.Product;
import com.it.test.nkia.app.repository.OrderRepository;
import com.it.test.nkia.app.repository.ProductRepository;

//@RunWith(SpringRunner.class)
//@Profile("test")
@DataJpaTest
public class ProductInventoryIntegrationTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ProductRepository prodctRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	//@Test
	public void whenAddProduct_thenReturnSavedProduct(){
		//given
		Product prod = new Product();
		prod.setProductName("Mathri");
		
		entityManager.persist(prod);
		entityManager.flush();
		//when
		List<Product> products = prodctRepo.findAll();		
		Product returenedProd = prodctRepo.findById(products.get(0).getProdId()).get();						
		//then
		Assert.assertSame(returenedProd.getProductName(), prod.getProductName());		
	}
}
