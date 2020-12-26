package com.order.restoran.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.order.restoran.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    public User findByUsernameIgnoreCase(String username);  
    public User findByUsername(String username);
}
