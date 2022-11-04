package com.spring.microservices.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.spring.microservices.model.CurrencyConversion;
import com.spring.microservices.service.GetCurrencyExchangeProxy;

@RestController
public class CurrencyConversionResource {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	WebClient.Builder webClient;
	
	@Autowired
	private GetCurrencyExchangeProxy getCurrencyExchange;
	
	@Value("${currency-exchange-service}")
	private String currencyExchangeService;
	
	
	// Using RestTemplate to Get Data
	@GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity)
	{
		
		Map<String, String> urivariables = new HashMap<String, String>();
		urivariables.put("from", from);
		urivariables.put("to", to);
		ResponseEntity<CurrencyConversion> responseEntity = 
				restTemplate.getForEntity(currencyExchangeService+"/currency-exchange/from/{from}/to/{to}", 
						CurrencyConversion.class,
						urivariables);
		CurrencyConversion currencyConversion = responseEntity.getBody();
		
		currencyConversion.setQuantity(quantity);
		currencyConversion.setTotalcalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
		return currencyConversion;
	}
	
	// Using webClient to Get Data
	@GetMapping("currency-conversion-webflux/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionWebFlux(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity)
	{		
			
				Map<String, String> urivariables = new HashMap<String, String>();
				urivariables.put("from", from);
				urivariables.put("to", to);
				CurrencyConversion currencyConversion = webClient.build().get().uri(currencyExchangeService+"/currency-exchange/from/{from}/to/{to}", urivariables)
				.retrieve().bodyToMono(CurrencyConversion.class).block();
			
		
		currencyConversion.setQuantity(quantity);
		currencyConversion.setTotalcalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
		return currencyConversion;
	}
	
	// Using Feign Clients to Get Data
	@GetMapping("currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity)
	{
		
		CurrencyConversion currencyConversion = getCurrencyExchange.getCurrencyExchanges(from, to);
		
		currencyConversion.setQuantity(quantity);
		currencyConversion.setTotalcalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
		return currencyConversion;
	}
}
