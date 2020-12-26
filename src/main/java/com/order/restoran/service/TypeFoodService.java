package com.order.restoran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.restoran.entity.TypeFood;
import com.order.restoran.repository.TypeFoodRepository;

@Service
public class TypeFoodService extends CrudImpl<TypeFood> {

	public TypeFoodRepository repository;

	@Autowired
	public TypeFoodService(TypeFoodRepository repository) {
		super(repository);
		this.repository = repository;
	}

	public TypeFood findByName(String name) {
		return repository.findByNameIgnoreCase(name);
	}

}
