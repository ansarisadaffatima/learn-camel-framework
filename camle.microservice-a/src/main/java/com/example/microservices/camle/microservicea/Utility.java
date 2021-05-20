package com.example.microservices.camle.microservicea;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class Utility {

	public String getCurrentTime(){
		return "The time is "+LocalDateTime.now();
	}
}
