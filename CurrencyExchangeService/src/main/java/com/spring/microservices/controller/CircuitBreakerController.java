package com.spring.microservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	@Autowired
	private RestTemplate restTemplate;
	
	
	@GetMapping("/sample-api")
	@Retry(name = "sample-api",fallbackMethod = "hardCodedResponse")
//	@CircuitBreaker(name = "default",fallbackMethod = "hardCodedResponse")
//	@RateLimiter(name = "default")
	public String sampleAPI()
	{
		logger.info("sample-api");
		ResponseEntity<String> responseEntity = 
				restTemplate.getForEntity("http://localhost:8000/test",String.class);
		return responseEntity.getBody();
//		return "HardCodedResponse";
	}
	
	public String hardCodedResponse(Exception ex)
	{
		return "fallBackHardCodedResponse";
	}
}
