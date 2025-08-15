package com.jsp.onlinefoodorderingsystem.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequest {
	
	@NotNull
	private List<OrderItemRequest> orderItems;
	
	@NotNull(message="resturant id null")
	private Integer resturantId;
	
	

	
	
}
