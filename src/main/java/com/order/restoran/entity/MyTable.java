package com.order.restoran.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class MyTable extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "type_table_id")
	TypeFood typeTable;

	int peopleCount;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "table")
	private List<Order> orders = new ArrayList<Order>();

}
