package com.order.restoran.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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

	@ManyToMany(mappedBy = "foods", fetch = FetchType.EAGER)
	 private List<Order> orders = new ArrayList<Order>();

	@Override
	public String toString() {
		return name + ", " + price + ", " + typeFood;
	}

}
