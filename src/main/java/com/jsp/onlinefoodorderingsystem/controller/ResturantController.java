package com.jsp.onlinefoodorderingsystem.controller;



import java.util.List;
import java.util.Set;

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
import com.jsp.onlinefoodorderingsystem.entity.Order;
import com.jsp.onlinefoodorderingsystem.entity.Resturant;
import com.jsp.onlinefoodorderingsystem.responseStructure.ResponseStructure;
import com.jsp.onlinefoodorderingsystem.service.ResturantService;

@RestController
@RequestMapping("/resturants/api")
public class ResturantController {
	
	@Autowired
	private ResturantService resturantService;
	
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Resturant>> createResturant(@RequestBody Resturant r){
        Resturant savedObject= resturantService.createResturant(r);
//	   ResponseStructure apiResponse= new ResponseStructure<>();
//	    apiResponse.setData(savedObject);
//	    apiResponse.setStauscode(HttpStatus.CREATED.value());
//	    apiResponse.setMessage("object stored successfully");
	    ResponseStructure<Resturant> apiResponse= new ResponseStructure<>(HttpStatus.CREATED.value(), "object saved successfully", savedObject);
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
	    
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseStructure<Resturant>> getById(@PathVariable Integer id){
		Resturant object= resturantService.getById(id);
		ResponseStructure<Resturant> response=new ResponseStructure<>(HttpStatus.OK.value(), " resturants details", object);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<ResponseStructure<Resturant>> deleteResturantById(@PathVariable Integer id){
//		resturantService.deleteResturantById(id);
//		ResponseStructure rs= new ResponseStructure<>(HttpStatus.OK.value(), "resturant is deleted", "with"+id+" is deleted");
//		return ResponseEntity.ok(rs);
//	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<Resturant>> deleteResturantById(@PathVariable Integer id){
		resturantService.deleteResturantById(id);
	    return	new ResponseEntity<> (HttpStatus.NO_CONTENT);
		}
	
	
	
	@GetMapping("/findall")
	public ResponseEntity<ResponseStructure<Page<Resturant>>> findalll(@RequestParam(defaultValue = "0",required = false)int pageNum,
			@RequestParam(defaultValue = "5",required = false) int pageSize,
			@RequestParam(defaultValue = "name",required = false) String sortBy){
		   Page<Resturant> all= resturantService.findallResurants(pageNum, pageSize, sortBy);
		ResponseStructure<Page<Resturant>> response=new ResponseStructure<>(HttpStatus.OK.value(), "all resturants details", all);
	  return ResponseEntity.ok(response);
	}
	
	@PutMapping("/updated/{id}")
	public ResponseEntity<ResponseStructure<Resturant>> updateResturant(@PathVariable Integer id,@RequestBody Resturant updatedResturant){
		Resturant updated= resturantService.updateResturant(id, updatedResturant);
		ResponseStructure<Resturant> rs= new ResponseStructure<>(HttpStatus.OK.value(), "resturanted updated", updated);
		 return  new ResponseEntity<>(rs,HttpStatus.OK);
	}
	
	
	
	
	@PostMapping("/{restaurantId}/assignFood")
	public ResponseEntity<ResponseStructure<Resturant>> assignFood(@PathVariable Integer restaurantId, @RequestBody Set<Integer> food){
		Resturant resturant = resturantService.assignFood(restaurantId, food);
		ResponseStructure<Resturant> apiResponse = new ResponseStructure<>(HttpStatus.OK.value(), "ASSIGNED", resturant);
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/{id}/getallFoods")
	public ResponseEntity<ResponseStructure<List<Food>>> getFoodByresturantId(@PathVariable Integer id){
		ResponseStructure<List<Food>> apiresponse= new ResponseStructure<>();
		apiresponse.setData(resturantService.findFoodByResturantId(id));
		apiresponse.setMessage("food items food");
		apiresponse.setStatuscode(HttpStatus.OK.value());
		return ResponseEntity.ok(apiresponse);
	}
	
	
	@GetMapping("{id}/getAllOrders")
	public ResponseEntity<ResponseStructure<List<Order>>> getOrderresturantId(@PathVariable Integer id){
		ResponseStructure<List<Order>> apiresponse= new ResponseStructure<List<Order>>(HttpStatus.OK.value(), "Orders found", resturantService.findOrderResturantId(id));
		return ResponseEntity.ok(apiresponse);
	}
	
	
	
}
