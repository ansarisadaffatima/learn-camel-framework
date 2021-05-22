package com.example.exception.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.example.exception.camel.utility.CamelCustomException;

@Component
public class MyProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("Exception thrown");
		throw new CamelCustomException();
	}

}
