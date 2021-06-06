package com.example.microservices.camle.microserviceb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.microservices.camle.microserviceb.utility.CurrencyExchangeProcessor;
import com.example.microservices.camle.microserviceb.utility.CurrencyExchangeTransformer;

//@Component
public class MyRouteB extends RouteBuilder {

	@Autowired
	private CurrencyExchangeProcessor processor;

	@Autowired
	private CurrencyExchangeTransformer transformer;

	@Override
	public void configure() throws Exception {

		// Consuming message
		/*from("activemq:active-mq-timer")
		.log("Received Message from active-mq-timer");*/

		// Unmarshal received JOSN message to bean
		/*from("activemq:my-activemq-queue")
		.unmarshal()
		.json(JsonLibrary.Jackson,CurrencyExchange.class)
		.bean(processor)
		.bean(transformer)
		.to("log:Received Message from active-mq-timer");*/

		// Unmarshal received XML message using activemq
		/*from("activemq:my-activemqxml-queue")
		.unmarshal()
		.jacksonxml(CurrencyExchange.class)
		.bean(processor)
		.bean(transformer)
		.to("log:Received Message from active-mq");*/

		// Kafka Topic consumer
		/*from("kafka:myKafkaTopic")
		.unmarshal()
		.json(JsonLibrary.Jackson,CurrencyExchange.class)
		.bean(processor)
		.bean(transformer)
		.to("log:Received Message from Kafka");*/

		from("activemq:activemq-split")
		//.log("${body}")
		.to("log:message-received-a-activemq-split");

	}
}
