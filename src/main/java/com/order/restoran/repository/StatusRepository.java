package com.order.restoran.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.restoran.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
	Status findByName(String name);
}
