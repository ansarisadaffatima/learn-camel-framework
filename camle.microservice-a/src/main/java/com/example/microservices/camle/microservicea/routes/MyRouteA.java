package com.example.microservices.camle.microservicea.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.microservices.camle.microservicea.Utility;

@Component
public class MyRouteA extends RouteBuilder{

	

	@Autowired
	Utility utility;
	
	@Override
	public void configure() throws Exception {
		//from("timer:my-first-timer")
		//.transform().constant("My Message")
		//.transform().constant("The time is "+LocalDateTime.now())
		//.bean("utility","getCurrentTime")
		//.log(message)
		//.log("${body}")
		//.process(new SimpleProcessor())
		//.to("log:my-first-timer");
		
		// Simple File Movement
		/*
		 * from("file:input/files") .log("Moved") .to("file:output/files");
		 */
		
		from("timer:active-mq-timer?period=10000")
		.transform().constant("My message for Active MQ")
		.log("${body}")
		.to("activemq:active-mq-timer");
	}

	
}

@Component
class SimpleProcessor implements Processor {

	Logger logger = org.slf4j.LoggerFactory.getLogger(SimpleProcessor.class);
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Message Body: {}", exchange.getMessage().getBody());
	}

}

