package com.example.microservices.camle.microserviceb.utility;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.microservices.camle.microserviceb.model.CurrencyExchange;

@Component
public class CurrencyExchangeTransformer {
	Logger logger = LoggerFactory.getLogger(CurrencyExchangeTransformer.class);
			CurrencyExchange transformer(CurrencyExchange currencyExchange){
		currencyExchange.setConversionMultiple(currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN));
		return currencyExchange;
	}
}
