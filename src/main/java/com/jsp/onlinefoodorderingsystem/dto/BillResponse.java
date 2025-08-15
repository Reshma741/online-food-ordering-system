package com.jsp.onlinefoodorderingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class BillResponse {
	
	private String resturantName;
	
	private String orderSummary;
	
	private Float  totalPrice;
	

}
