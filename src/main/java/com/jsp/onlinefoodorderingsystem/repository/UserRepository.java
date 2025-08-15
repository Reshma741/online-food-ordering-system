package com.jsp.onlinefoodorderingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.onlinefoodorderingsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	

}
