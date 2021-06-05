package com.example.camel.micorservice.rest.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.camel.micorservice.rest.model.Order;
import com.example.camel.micorservice.rest.service.OrderService;

@Component
public class OrderProcessor implements Processor{

	@Autowired
	OrderService service;

	public void process(Exchange exchange) throws Exception {
		service.addOrder(exchange.getIn().getBody(Order.class));
	}

}
