package com.example.mysql.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.example.mysql.model.Employee;

@Component
public class InserProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		//Take the Employee object from the exchange and create the insert query
		Employee employee = exchange.getIn().getBody(Employee.class);
		String query = "INSERT INTO employee(empId,empName)values('" + employee.getEmpId() + "','"
				+ employee.getEmpName() + "')";
	// Set the insert query in body and call camel jdbc
		exchange.getIn().setBody(query);
	}

}
