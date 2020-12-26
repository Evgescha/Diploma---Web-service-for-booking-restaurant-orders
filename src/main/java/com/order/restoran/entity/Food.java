package com.order.restoran.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Food extends AbstractEntity {

	String name;

	float price;

	@Column(length = 3000)
	String description;

	String image;

	@ManyToOne
	@JoinColumn(name = "type_food_id")
	TypeFood typeFood;

	@ManyToOne
	@JoinColumn(name = "order_id")
	Order order;

	@Override
	public String toString() {
		return name + ", " + price + ", " + typeFood;
	}

}
