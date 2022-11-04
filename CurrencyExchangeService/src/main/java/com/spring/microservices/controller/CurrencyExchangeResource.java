package com.spring.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.microservices.dao.CurrencyExchangeRepository;
import com.spring.microservices.model.CurrencyExchange;

@RestController
public class CurrencyExchangeResource {
	
	
	@Autowired
	private Environment environment;
	@Autowired
	CurrencyExchangeRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from,@PathVariable String to)
	{
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		if(currencyExchange == null)
			throw new RuntimeException("unable to find data for "+from+" to "+to);
		
		String port = environment.getProperty("local.server.port", "8080");
		currencyExchange.setEnvironment(port);
		return currencyExchange;
	}

}
