package com.order.restoran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.restoran.entity.Food;
import com.order.restoran.repository.FoodRepository;

@Service
public class FoodService extends CrudImpl<Food> {

	public FoodRepository repository;

	@Autowired
	public FoodService(FoodRepository repository) {
		super(repository);
		this.repository = repository;
	}


}
