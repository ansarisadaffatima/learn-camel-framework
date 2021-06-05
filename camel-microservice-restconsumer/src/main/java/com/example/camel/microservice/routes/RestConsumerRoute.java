package com.example.camel.microservice.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestConsumerRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {

		restConfiguration().host("localhost").port(9090);

		from("timer:rest-api-conumer?period=10000")
		.log("${body}")
		// If want to set any value in get url
		//.setHeader("from",()->"INR")
		//.to("rest:get:/getAllOrders/{from}")
		.to("rest:get:/getAllOrders")
		.log("${body}");

	}

}
