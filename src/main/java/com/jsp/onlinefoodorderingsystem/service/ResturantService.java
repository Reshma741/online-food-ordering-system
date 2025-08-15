package com.jsp.onlinefoodorderingsystem.service;


import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.jsp.onlinefoodorderingsystem.entity.Food;
import com.jsp.onlinefoodorderingsystem.entity.Order;
import com.jsp.onlinefoodorderingsystem.entity.Resturant;

public interface ResturantService {
	
     Resturant createResturant(Resturant r);
     
     Resturant getById(Integer id);
     
     void deleteResturantById(Integer id);

      Page findallResurants(int pageNum,int pageSize,String sortBy);
      
  	  Resturant updateResturant(Integer id,Resturant updatedresturant);
  	 
  	  Resturant assignFood(Integer resturantId,Set<Integer> foodId);
     
  	 /// find the list of food items based on resturant id  
       List<Food> findFoodByResturantId(Integer Id);
       
       List<Order> findOrderResturantId(Integer id);
      
   }
