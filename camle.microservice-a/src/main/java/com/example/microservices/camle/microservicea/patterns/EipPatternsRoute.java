package com.example.microservices.camle.microservicea.patterns;

import java.util.Arrays;
import java.util.List;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EipPatternsRoute extends RouteBuilder {

	@Autowired
	DynamicRouterBean dynamicRouterBean;

	@Autowired
	Splitter splitter;

	@Override
	public void configure() throws Exception {

		// Multicast can send to multiple endpoints example kafka,activemq
		//		from("timer:multicast-timer?period=10000")
		//		.multicast()
		//		.to("log:something1","log:something2","log:something3");

		// Split pattern
		//		from("file:files/csv")
		//		.unmarshal().csv()
		//		.split(body())
		//		.to("activemq:activemq-split");

		//Split message1,message2,message3
		//		from("file:files/csv")
		//		.convertBodyTo(String.class)
		//		//.split(body(),",")
		//		.split(method(splitter))
		//		.to("activemq:activemq-split");

		// Aggregation pattern
		//		from("file:files/aggregate-json")
		//		.unmarshal().json(JsonLibrary.Jackson,CurrencyExchange.class)
		//		.aggregate(simple("${body.to}"), new ArrayListAggregationStrategy())
		//		.completionSize(3)
		//		.to("log:aggragate-json");

		// Routing slip pattern
		//		String routingSlip = "direct:endpoint1,direct:endpoint3";
		//		from("timer:routingslip?period=10000")
		//		.transform().constant("My Routing Slip")
		//		.routingSlip(simple(routingSlip));

		// Dynamic Routing
		from("timer:dynamicRouter?period=10000")
		.transform().constant("My Dynamic router")
		.dynamicRouter(method(dynamicRouterBean));


		from("direct:endpoint1")
		.to("log:directendpoint1");

		from("direct:endpoint2")
		.to("log:directendpoint2");

		from("direct:endpoint3")
		.to("log:directendpoint3");


	}

}

@Component
class Splitter {
	List<String> convertToListOfString(String body) {
		return Arrays.asList("ABC", "DEF", "GHI");
	}
}
