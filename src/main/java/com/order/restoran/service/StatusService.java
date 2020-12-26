package com.order.restoran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.restoran.entity.Status;
import com.order.restoran.repository.StatusRepository;

@Service
public class StatusService extends CrudImpl<Status> {

	public StatusRepository repository;

	@Autowired
	public StatusService(StatusRepository repository) {
		super(repository);
		this.repository = repository;
	}
	
}
