package com.order.restoran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.restoran.entity.TypeTable;
import com.order.restoran.repository.TypeTableRepository;

@Service
public class TypeTableService extends CrudImpl<TypeTable> {

	public TypeTableRepository repository;

	@Autowired
	public TypeTableService(TypeTableRepository repository) {
		super(repository);
		this.repository = repository;
	}

	public TypeTable findByName(String name) {
		return repository.findByNameIgnoreCase(name);
	}

}
