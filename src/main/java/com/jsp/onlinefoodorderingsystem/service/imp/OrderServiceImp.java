package com.jsp.onlinefoodorderingsystem.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.jsp.onlinefoodorderingsystem.dto.BillResponse;
import com.jsp.onlinefoodorderingsystem.dto.OrderItemRequest;
import com.jsp.onlinefoodorderingsystem.dto.OrderRequest;
import com.jsp.onlinefoodorderingsystem.dto.PaymentDto;
import com.jsp.onlinefoodorderingsystem.entity.Food;
import com.jsp.onlinefoodorderingsystem.entity.Order;
import com.jsp.onlinefoodorderingsystem.entity.OrderItem;
import com.jsp.onlinefoodorderingsystem.entity.OrderStatus;
import com.jsp.onlinefoodorderingsystem.entity.Resturant;
import com.jsp.onlinefoodorderingsystem.entity.User;
import com.jsp.onlinefoodorderingsystem.exception.PaymentFailedException;
import com.jsp.onlinefoodorderingsystem.repository.OrderRepository;
import com.jsp.onlinefoodorderingsystem.service.FoodService;
import com.jsp.onlinefoodorderingsystem.service.OrderService;
import com.jsp.onlinefoodorderingsystem.service.ResturantService;
import com.jsp.onlinefoodorderingsystem.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {
	
	
	private final ResturantService restaurantService;
	private final FoodService foodService;
	private final OrderRepository orderRepository;
	private final UserService userService;

	@Override
	public BillResponse generateBill(OrderRequest orderRequest) {
		Resturant restaurant = restaurantService.getById(orderRequest.getResturantId());
		StringBuilder summary = new StringBuilder();
		
		float totalPrice=0;
		
		for(OrderItemRequest orderItem : orderRequest.getOrderItems()) {
			Food food = foodService.getFoodById(orderItem.getFoodId());
			float price = food.getPrice() * orderItem.getQuantity();
			totalPrice+=price;
			summary.append(food.getName()).append(" X ").append(orderItem.getQuantity())
				.append(" = ").append(price).append("\n");
		}
		
		return new BillResponse(restaurant.getName(),summary.toString(),totalPrice);
	}

	@Override
	public String payAndPlaceOrder(PaymentDto payment) {
		//simulate payment
		   if(payment.isPaymentSuccessful()) {
			Order order = new Order();
			order.setStatus(OrderStatus.PLACED);
			
			Resturant restaurant = restaurantService.getById(payment.getRestaurantId());
			
			//set restaurant to order
			order.setResturant(restaurant);
			
			//set user to order
			User user= userService.getUser(payment.getUserId());
			order.setUser(user);
			
			List<OrderItem> items = new ArrayList();
			double totalPrice=0;
			
			for(OrderItemRequest request : payment.getOrderItems()) {
				Food food = foodService.getFoodById(request.getFoodId());
				
				OrderItem orderItem = new OrderItem();
				orderItem.setFood(food);
				orderItem.setQuantity(request.getQuantity());
				orderItem.setOrder(order);
				
				items.add(orderItem);
				
				double price = food.getPrice() * request.getQuantity();
				totalPrice += price;
			}
			
			order.setTotalPrice(totalPrice);
			order.setOrderItems(items);
			orderRepository.save(order);
			return "Order has been placed";
		}
		else {
			throw new PaymentFailedException("Payment was not successful, hence order cannot be placed");
		}
	}

	@Override
	public void deleteOrder(Integer id) {
		 orderRepository.delete(getOrder(id));
	}

	@Override
	public Order getOrder(Integer id) {
		
		return orderRepository.findById(id).orElseThrow(()-> new NoSuchElementException("order with id:"+id+" is not found"));
	}

	@Override
	public Order updateStatusByAdmin(OrderStatus status, Integer id) {
		Order order=getOrder(id);
		order.setStatus(status);
		return orderRepository.save(order);
	}

	@Override
	public String CancelOrder(Integer id) {
		
		Order order=getOrder(id);
		order.setStatus(OrderStatus.CANCELLED);
		orderRepository.save(order);
		return "Order is cancelled";
	}

	
	
	

}
