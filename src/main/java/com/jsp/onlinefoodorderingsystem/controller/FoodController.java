package com.jsp.onlinefoodorderingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.onlinefoodorderingsystem.entity.Food;
import com.jsp.onlinefoodorderingsystem.responseStructure.ResponseStructure;
import com.jsp.onlinefoodorderingsystem.service.FoodService;


@RequestMapping("/food/api")
@RestController
public class FoodController {
	
	@Autowired
	private FoodService foodservice;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Food>> createFood(@RequestBody Food food){
		Food saved= foodservice.createFood(food);
		ResponseStructure<Food> rs=new ResponseStructure<Food>(HttpStatus.CREATED.value(), "food object created", saved);
		return new ResponseEntity<>(rs,HttpStatus.CREATED);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<ResponseStructure<Food>> findFoodById(@PathVariable Integer id){
		Food food= foodservice.getFoodById(id);
		ResponseStructure<Food> rs= new ResponseStructure<Food>(HttpStatus.OK.value(), "Food is found", food);
		return ResponseEntity.ok(rs);
	}
	
	
	@GetMapping("/findall")
	public ResponseEntity<ResponseStructure<Page<Food>>> findall(@RequestParam(defaultValue = "0",required = false) int pageNum,@RequestParam(defaultValue = "5",required = false) int pageSize){
		Page<Food> all=foodservice.getAllFood(pageNum, pageSize);
		ResponseStructure<Page<Food>> rs= new ResponseStructure<>(HttpStatus.OK.value(), "List of food items", all);
		return ResponseEntity.ok(rs);
		
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<Food>> updateFood(@PathVariable Integer id,@RequestBody Food updatedfood){
		Food upadatedfood= foodservice.updateFood(updatedfood, id);
		ResponseStructure<Food> rs= new ResponseStructure<Food>(HttpStatus.OK.value(), "Food is with "+id+" is updated", upadatedfood);
		 return  new ResponseEntity<>(rs,HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<Food>> deleteFoodById(@PathVariable Integer id){
		foodservice.deleteFood(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	

}
