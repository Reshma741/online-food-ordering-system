package com.jsp.onlinefoodorderingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.onlinefoodorderingsystem.dto.BillResponse;
import com.jsp.onlinefoodorderingsystem.dto.OrderRequest;
import com.jsp.onlinefoodorderingsystem.dto.PaymentDto;
import com.jsp.onlinefoodorderingsystem.entity.Order;
import com.jsp.onlinefoodorderingsystem.entity.OrderStatus;
import com.jsp.onlinefoodorderingsystem.responseStructure.ResponseStructure;
import com.jsp.onlinefoodorderingsystem.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/generate-bill")
	public ResponseEntity<ResponseStructure<BillResponse>> generateBill(@RequestBody OrderRequest orderRequest){
		BillResponse response = orderService.generateBill(orderRequest);
		ResponseStructure<BillResponse> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Bill has been generated");
		apiResponse.setStatuscode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
	}
	
	@PostMapping("/pay-and-place-order")
	public ResponseEntity<ResponseStructure<String>> payAndPlaceOrder(@RequestBody PaymentDto payment){
		String data = orderService.payAndPlaceOrder(payment);
		ResponseStructure<String> apiResponse = new ResponseStructure<>();
		apiResponse.setData(data);
		apiResponse.setMessage("Order placed");
		apiResponse.setStatuscode(HttpStatus.CREATED.value());
		
		return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{id}/get")
	public ResponseEntity<ResponseStructure<Order>> getOrder(@PathVariable Integer id){
		ResponseStructure<Order> apiresponse = new ResponseStructure<Order>(HttpStatus.FOUND.value(),"Order is found", orderService.getOrder(id));
		return new ResponseEntity<>(apiresponse,HttpStatus.FOUND);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity deleteOrder(@PathVariable Integer id){
		orderService.deleteOrder(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping("/{id}/updateStatus")
	public ResponseEntity<ResponseStructure<Order>> upadateStatus(@PathVariable Integer id,@RequestParam OrderStatus status){
		ResponseStructure<Order> apiresponse = new ResponseStructure<Order>(HttpStatus.OK.value(), "ststus upadted", orderService.updateStatusByAdmin(status, id));
		return ResponseEntity.ok(apiresponse);
	}
	

	@PatchMapping("/{id}/cancelOrder")
	public ResponseEntity<ResponseStructure<String>> cancelOrder(@PathVariable Integer id){
		ResponseStructure<String> apiresponse= new ResponseStructure<String>(HttpStatus.OK.value(), "order Cancelled", orderService.CancelOrder(id));
		return ResponseEntity.ok(apiresponse);
	}
	
	
}
