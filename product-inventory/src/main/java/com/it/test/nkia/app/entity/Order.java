package com.it.test.nkia.app.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="order")
@SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", allocationSize = 1, initialValue = 1)
public class Order {
	
	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "order_sequence")
	private Integer orderId;
	
	@OneToMany(cascade= CascadeType.ALL, orphanRemoval = true)
	private Set<Product> products;
}
