package com.jsp.onlinefoodorderingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.onlinefoodorderingsystem.entity.OrderItem;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Integer> {

}
