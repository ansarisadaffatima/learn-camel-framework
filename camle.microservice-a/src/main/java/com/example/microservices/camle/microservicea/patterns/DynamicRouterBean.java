package com.example.microservices.camle.microservicea.patterns;

import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DynamicRouterBean {
	Logger log = LoggerFactory.getLogger(DynamicRouterBean.class);
	int invocations;
	String dynamicRouter(@Body String body, @ExchangeProperties Map<String,String> exchangeProperties, @Headers Map<String,String> headers){
		log.info("{} {} {}",exchangeProperties,headers,body);

		invocations++;
		if(invocations % 3 == 0){
			return "direct:endpoint1";
		}
		if(invocations % 3 == 1){
			return "direct:endpoint2,direct:endpoint3";
		}
		return null;
	}
}
