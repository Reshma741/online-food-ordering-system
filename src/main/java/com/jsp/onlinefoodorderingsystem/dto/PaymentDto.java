package com.jsp.onlinefoodorderingsystem.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentDto {

	
	@NotNull
   // @Schema(description="This filed specifies list of order item request object")
	private List<OrderItemRequest> orderItems;
	private boolean PaymentSuccessful;
	private Integer restaurantId;
    private Integer userId; 
	
}

