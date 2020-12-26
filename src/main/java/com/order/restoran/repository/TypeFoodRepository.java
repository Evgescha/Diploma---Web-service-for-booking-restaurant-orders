package com.order.restoran.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.restoran.entity.TypeFood;

@Repository
public interface TypeFoodRepository extends JpaRepository<TypeFood, Long> {
	TypeFood findByNameIgnoreCase(String name);
}
