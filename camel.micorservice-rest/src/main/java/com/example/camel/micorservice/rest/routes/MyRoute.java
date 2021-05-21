package com.example.camel.micorservice.rest.routes;

import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.example.camel.micorservice.rest.model.Order;
import com.example.camel.micorservice.rest.processor.OrderProcessor;
import com.example.camel.micorservice.rest.service.OrderService;

@Component
public class MyRoute extends RouteBuilder{
	
	@Autowired
	private OrderService service;
	
	@BeanInject
	OrderProcessor processor;

	@Override
	public void configure() throws Exception {

		restConfiguration().component("servlet").port(9090).host("localhost").bindingMode(RestBindingMode.json);
		
		// Sample
		rest().get("/hello-world").produces(MediaType.APPLICATION_JSON_VALUE).route().setBody(constant("Welcome to Rest API Demo")).endRest();
		
		// Get All Orders
		rest().get("/getAllOrders").produces(MediaType.APPLICATION_JSON_VALUE).route().setBody(()->service.getAllOrders()).endRest();
		
		// Adding Order
		rest().post("/addOrder").consumes(MediaType.APPLICATION_JSON_VALUE).type(Order.class).outType(Order.class).route().process(processor).endRest();
	}

}
