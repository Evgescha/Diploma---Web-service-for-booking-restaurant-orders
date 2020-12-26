package com.order.restoran.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.restoran.entity.TypeTable;

@Repository
public interface TypeTableRepository extends JpaRepository<TypeTable, Long> {
	TypeTable findByNameIgnoreCase(String name);
}
