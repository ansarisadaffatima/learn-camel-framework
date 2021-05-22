package com.example.exception.camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.exception.camel.processor.MyProcessor;
import com.example.exception.camel.utility.CamelCustomException;

@Component
public class MyRoute extends RouteBuilder{
	
	@Autowired
	MyProcessor processor;

	@Override
	public void configure() throws Exception {
		// File processing will continue encounter of error
		//from("file:input/files?noop=true").process(processor).to("file:output/files");
		
		/*
		 * // Type 1 : Do Catch Block
		 * from("file:input/files?noop=true").doTry().process(new
		 * MyProcessor()).to("file:output/files").doCatch(CamelCustomException.class).
		 * process(new Processor() {
		 * 
		 * public void process(Exchange exchange) throws Exception {
		 * System.out.println("handling ex"); } }).log("Received body ");
		 * 
		 * // File processing will continue
		 * from("file:in?noop=true").process(processor).to("file:out");
		 */
		
		// Type 2 : OnException Block
		onException(CamelCustomException.class).process(new Processor() {

            public void process(Exchange exchange) throws Exception {
                System.out.println("handling ex");
            }
        }).log("Received body ").handled(true);
		
		from("file:input/files?noop=true").process(new MyProcessor()).to("file:output/files");

        from("file:in?noop=true").process(new MyProcessor()).to("file:out");
    
	}

}
