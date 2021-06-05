package com.example.microservices.camle.microserviceb.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.microservices.camle.microserviceb.model.CurrencyExchange;

@Component
public class CurrencyExchangeProcessor {
	Logger logger = LoggerFactory.getLogger(CurrencyExchange.class);
	void processor(CurrencyExchange currencyExchange){
		logger.info("Permorm some processing on ConversionMultiple that is {}",currencyExchange.getConversionMultiple());
	}
}
