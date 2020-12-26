package com.order.restoran.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class TypeFood extends AbstractEntity {
	String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "typeFood")
	private List<Food> foods = new ArrayList<Food>();

	@Override
	public String toString() {
		return name;
	}
}
