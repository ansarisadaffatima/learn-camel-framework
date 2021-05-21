package com.example.camel.micorservice.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.camel.micorservice.rest.model.Order;

@Service
public class OrderService {
	
	private List<Order> orders = new ArrayList<Order>();
	
	@PostConstruct
	public void loadOrders(){
		
		orders.add(new Order(1,"Mobile",9000));
		orders.add(new Order(67,"Book",400));
		orders.add(new Order(1,"TV",15000));
		orders.add(new Order(1,"Table",2000));
		orders.add(new Order(1,"Chair",1500));
	}

	public List<Order> getAllOrders(){
		return orders;
	}
	
	public Order addOrder(Order order) {
		orders.add(order);
		return order;
	}
}
