package com.jsp.onlinefoodorderingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.onlinefoodorderingsystem.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Integer>{

}
