package com.it.test.nkia.app.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.it.test.nkia.app.entity.Order;
import com.it.test.nkia.app.entity.Product;
import com.it.test.nkia.app.repository.OrderRepository;
import com.it.test.nkia.app.repository.ProductRepository;
import com.it.test.nkia.app.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService prodSvc;
	
	@Autowired
	private ProductRepository prodRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	
	@PostMapping(path="/product")
	public Product saveProduct(@RequestBody Product product) {
		return prodRepo.saveAndFlush(product);
	}
	
	@GetMapping(path="/products")
	public List<Product> products() {
		return prodRepo.findAll();
	}
	
	@PostMapping(path="/placeOrder")
	public Order placeOrder(@RequestBody Order order ) {
		
		Set<Product> prdcts = order.getProducts();
		if(!StringUtils.isEmpty(prdcts)) {
			Set<Product> _products = new HashSet<Product>(); 
			for(Product p: prdcts) {
					p = prodRepo.findById(p.getProdId()).get();
					_products.add(p);
			}
			order.setProducts(_products);
		}
		Order placedOrder =  orderRepo.saveAndFlush(order);
		if(null != placedOrder) {
			prodSvc.generateInvoice(placedOrder);
		}		
		return placedOrder;
	}	
}
