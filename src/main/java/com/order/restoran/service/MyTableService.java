package com.order.restoran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.restoran.entity.MyTable;
import com.order.restoran.repository.MyTableRepository;

@Service
public class MyTableService extends CrudImpl<MyTable> {

	public MyTableRepository repository;

	@Autowired
	public MyTableService(MyTableRepository repository) {
		super(repository);
		this.repository = repository;
	}


}
