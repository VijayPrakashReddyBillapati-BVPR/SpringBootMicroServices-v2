package com.spring.microservices.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.microservices.model.CurrencyConversion;

//@FeignClient(name = "CurrencyExchangeService",url = "http://localhost:8000")
@FeignClient(name = "CurrencyExchangeService")
public interface GetCurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion getCurrencyExchanges(@PathVariable String from,@PathVariable String to);
}
