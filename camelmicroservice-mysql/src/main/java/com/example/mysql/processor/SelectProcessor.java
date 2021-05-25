package com.example.mysql.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.example.mysql.model.Employee;

@Component
public class SelectProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
			   //the camel jdbc select query has been executed. We get the list of employees.
				ArrayList<Map<String, String>> dataList = (ArrayList<Map<String, String>>) exchange.getIn()
						.getBody();
				List<Employee> employees = new ArrayList<Employee>();
				System.out.println(dataList);
				for (Map<String, String> data : dataList) {
					Employee employee = new Employee();
					employee.setEmpId(data.get("empId"));
					employee.setEmpName(data.get("empName"));
					employees.add(employee);
				}
				exchange.getIn().setBody(employees);
	}

}
