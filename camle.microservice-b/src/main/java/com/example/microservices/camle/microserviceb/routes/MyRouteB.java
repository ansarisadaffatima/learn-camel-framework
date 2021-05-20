package com.example.microservices.camle.microserviceb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRouteB extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("activemq:active-mq-timer")
		.log("Received Message from active-mq-timer");
	}	
}
