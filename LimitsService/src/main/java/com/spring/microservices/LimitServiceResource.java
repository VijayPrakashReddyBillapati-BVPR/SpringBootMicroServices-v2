package com.spring.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.microservices.configuration.Configuration;
import com.spring.microservices.model.Limits;

@RestController
public class LimitServiceResource {
	
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public Limits getLimits()
	{
		return new Limits(configuration.getMinimum() ,configuration.getMaximum());
	}

}
