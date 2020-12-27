package com.order.restoran.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "myOrders")
@Data
public class Order extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name = "creator_id")
	User creator;

	@JsonIgnore
	@Fetch(value = FetchMode.SELECT)
	@ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
	@JoinTable(name = "order_food", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "food_id"), uniqueConstraints = @UniqueConstraint(name = "orders_foods", columnNames = {
			"order_id", "food_id" }))
	private List<Food> foods = new ArrayList<Food>();

	@ManyToOne
	@JoinColumn(name = "table_id")
	TypeTable table;

	Date dates;

	int peopleCount;

	@ManyToOne
	@JoinColumn(name = "status_id")
	Status status;

	@Override
	public String toString() {
		return "Order [foods=" + foods + ", table=" + table + ", dates=" + dates + ", peopleCount=" + peopleCount
				+ ", status=" + status + "]";
	}

}
