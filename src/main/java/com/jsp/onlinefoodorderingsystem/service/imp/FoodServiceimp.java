package com.jsp.onlinefoodorderingsystem.service.imp;

import java.util.List;
import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jsp.onlinefoodorderingsystem.entity.Food;
import com.jsp.onlinefoodorderingsystem.entity.Resturant;
import com.jsp.onlinefoodorderingsystem.repository.FoodRepository;
import com.jsp.onlinefoodorderingsystem.repository.ResturantRepository;
import com.jsp.onlinefoodorderingsystem.service.FoodService;




@Service
public class FoodServiceimp implements FoodService {

	
	@Autowired
	private FoodRepository foodrepository;
	
	
	@Autowired
	private ResturantRepository resturantrepository;
	
	
	
	@Override
	public Food createFood(Food food) {
	
		return  foodrepository.save(food);
	}

	@Override
	public Food getFoodById(Integer id) {
		
	return foodrepository.findById(id).orElseThrow(()->new NoSuchElementException("Food with "+id+ " is not found"));
	}

	@Override
	public Page<Food> getAllFood(Integer pageNum, Integer pageSize) {
		Pageable pageable= PageRequest.of(pageNum, pageSize);	
		return foodrepository.findAll(pageable);
	}

	@Override
	public Food updateFood(Food food, Integer id) {
		 Food ex=getFoodById(id);
		 ex.setName(food.getName());
	     ex.setDescription(food.getDescription());
		 ex.setPrice(food.getPrice());
		 ex.setResturants(food.getResturants());
		
		
		return foodrepository.save(ex);
	}

	
	@Override
	public void deleteFood(Integer id) {
		
		// foodrepository.delete(getFoodById(id));
		Food food =getFoodById(id);
		List<Resturant> resturants= food.getResturants();
		if(resturants.size()==0) {
			foodrepository.delete(food);
			return;
		}
		for(Resturant resturant:resturants) {
			resturant.getFood().remove(food);
		}
		
		resturantrepository.saveAll(resturants);
		foodrepository.delete(food);
		
	}
	
	
	
	
	
	
	

}
