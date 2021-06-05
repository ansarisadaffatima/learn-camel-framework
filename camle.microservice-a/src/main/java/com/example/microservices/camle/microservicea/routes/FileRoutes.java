package com.example.microservices.camle.microservicea.routes;

import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileRoutes extends RouteBuilder{

	@Autowired
	DeciderBeans deciderBeans;

	@Override
	public void configure() throws Exception {
		from("file:files/input")
		.routeId("Input-Files-Route")
		.transform().body(String.class)
		.choice()
		.when(simple("${file:ext} ends with 'xml'"))
		.log("XML File")
		//.when(simple("${body} contains 'USD'"))
		//.log("Not XML but conatins USD")
		.when(method(deciderBeans))
		.otherwise()
		.log("Not XML file")
		.end()
		.log("${body}")
		//.to("direct://log-file-values")
		.to("file:files/output");

		from("direct:log-file-values")
		.log("${messageHistory} ${file:absolute.path}")
		.log("${file:name} ${file:name.ext} ${file:name.noext} ${file:onlyname}")
		.log("${file:onlyname.noext} ${file:parent} ${file:path} ${file:absolute}")
		.log("${file:size} ${file:modified}")
		.log("${routeId} ${camelId} ${body}");


	}

}

@Component
class DeciderBeans{
	Logger log = LoggerFactory.getLogger(DeciderBeans.class);

	public boolean isThisConditionMet(@Body String body, @Headers Map<String, String> headers,@ExchangeProperties Map<String,String> exchangeProperties){
		log.info("DeciderBeans {} {} {}",body,headers,exchangeProperties);
		return true;

	}
}
