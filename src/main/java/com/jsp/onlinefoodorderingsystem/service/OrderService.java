package com.jsp.onlinefoodorderingsystem.service;



import com.jsp.onlinefoodorderingsystem.dto.BillResponse;
import com.jsp.onlinefoodorderingsystem.dto.OrderRequest;
import com.jsp.onlinefoodorderingsystem.dto.PaymentDto;
import com.jsp.onlinefoodorderingsystem.entity.Order;
import com.jsp.onlinefoodorderingsystem.entity.OrderStatus;




public interface OrderService {
	
	BillResponse generateBill(OrderRequest orderRequest);
	
	String payAndPlaceOrder(PaymentDto payment);
	
	
	 void deleteOrder(Integer id);
	 
	 Order getOrder(Integer id);
	 
	 Order updateStatusByAdmin(OrderStatus status, Integer id);
	 
	 String CancelOrder(Integer id);
	 
	
}
