package com.jsp.onlinefoodorderingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jsp.onlinefoodorderingsystem.entity.Food;
import com.jsp.onlinefoodorderingsystem.entity.Order;
import com.jsp.onlinefoodorderingsystem.entity.Resturant;

public interface ResturantRepository extends JpaRepository<Resturant, Integer> {
	
	     @Query("SELECT r.food FROM Resturant r where r.id=:resturantId")// this is becoz it join table in food entity 
	     List<Food> findFoodByResturantId(@Param(value="resturantId")Integer id);
	     
	     
	     @Query("select r.orders from Resturant r where r.id=:resturantId")
	     List<Order> findOrderByResturantId(@Param(value = "resturantId") Integer id);

}


