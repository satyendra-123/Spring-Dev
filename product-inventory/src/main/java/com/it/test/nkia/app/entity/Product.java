package com.it.test.nkia.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="product")
@SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1, initialValue = 1)
public class Product {
	
	@Id
	@Column(name="prod_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "product_sequence")
	private Integer prodId;
	
	@Column(name="product_name")
	private String productName;
	
}
