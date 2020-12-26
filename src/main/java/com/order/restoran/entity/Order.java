package com.order.restoran.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "myOrders")
@Data
public class Order extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name = "creator_id")
	User creator;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private List<Food> foods = new ArrayList<Food>();

	@ManyToOne
	@JoinColumn(name = "table_id")
	MyTable table;

	Date dates;

	int peopleCount;

	@ManyToOne
	@JoinColumn(name = "status_id")
	Status status;
}
