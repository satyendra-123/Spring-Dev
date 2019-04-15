package com.it.test.nkia.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.it.test.nkia.app.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
