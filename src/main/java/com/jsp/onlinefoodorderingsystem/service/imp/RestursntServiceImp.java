package com.jsp.onlinefoodorderingsystem.service.imp;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jsp.onlinefoodorderingsystem.entity.Food;
import com.jsp.onlinefoodorderingsystem.entity.Order;
import com.jsp.onlinefoodorderingsystem.entity.Resturant;
import com.jsp.onlinefoodorderingsystem.repository.FoodRepository;
import com.jsp.onlinefoodorderingsystem.repository.ResturantRepository;
import com.jsp.onlinefoodorderingsystem.service.ResturantService;

@Service
public class RestursntServiceImp implements ResturantService {
    
	@Autowired
	private ResturantRepository resturantRespository;
	
	
	@Autowired
	private FoodRepository foodRepository;
	
	
	@Override
	public Resturant createResturant(Resturant r) {	
		return resturantRespository.save(r);
				
	}


	


	@Override
	public Resturant getById(Integer id) {
//		Optional<Resturant> optional= resturantRespository.findById(id);
//		if(optional.isPresent()) {
//			return optional.get();
//		}
//		else {
//			
//			throw new NoSuchElementException("resturant with Id :"+id+" not found");
//		}
		return  resturantRespository.findById(id).orElseThrow(()-> new NoSuchElementException(" object with id "+id +" is not found"));
		
	}


	@Override
	public void deleteResturantById(Integer id) {
		Resturant r=getById(id);
		resturantRespository.delete(r); 
	
	}


	@Override
	public Page findallResurants(int pageNum, int pageSize, String sortBy) {
		Sort sort= Sort.by(sortBy).descending();
		Pageable pageable= PageRequest.of(pageNum, pageSize,sort);
		Page page= resturantRespository.findAll(pageable);
		return page;
	}





	@Override
	public Resturant updateResturant(Integer id, Resturant updatedresturant) {
		Resturant existing= getById(id);
		existing.setName(updatedresturant.getName());
		existing.setEmail(updatedresturant.getEmail());
		existing.setAddress(updatedresturant.getAddress());
		existing.setContactNumber(updatedresturant.getContactNumber());
		
	
		return  resturantRespository.save(existing);
	}




	@Override
	public Resturant assignFood(Integer resturantId, Set<Integer> foodId) {
	   Resturant resturant= getById( resturantId);
	   
	   List<Food> foodItems=new ArrayList<>();
	   
	   for(Integer id:foodId) {
		   Food food=foodRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Food with ID:" +id+ "not found"));
		   foodItems.add(food);
	   }
	  
	   resturant.setFood(foodItems);
	   return resturantRespository.save(resturant);
	}





	@Override
	public List<Food> findFoodByResturantId(Integer id) {
		List<Food> foods=resturantRespository.findFoodByResturantId(id);
		if(foods==null || foods.size()==0) {
			throw new NoSuchElementException("resturant with id:"+id+"is not found or food is not assigned with to this resturant");
		}
		else{
			return foods;
		}
			}





	@Override
	public List<Order> findOrderResturantId(Integer id) {
		List<Order> orders=resturantRespository.findOrderByResturantId(id);
	if(orders==null || orders.size()==0) {
		throw new NoSuchElementException("no orders there with this id:" +id);
	}
	else {
		return orders;
	}
	
	}
	
	
	
	
	
	
	
	
	
}
	






		
	
	


