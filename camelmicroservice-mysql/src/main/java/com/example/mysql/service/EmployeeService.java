package com.example.mysql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mysql.model.Employee;
import com.example.mysql.processor.InserProcessor;
import com.example.mysql.processor.SelectProcessor;

@Service
public class EmployeeService extends RouteBuilder {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	InserProcessor insertProcessor;	
	
	@Autowired
	SelectProcessor selectProcessor;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		}

	@Override
	public void configure() throws Exception {
        
	       	 //Insert Route
		from("direct:insert").process(insertProcessor).to("jdbc:dataSource");
		
		// Select Route
		from("direct:select").setBody(constant("select * from employee")).to("jdbc:dataSource")
				.process(selectProcessor);

		
	}
}
