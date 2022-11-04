package com.spring.microservices;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfigurations {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder)
	{
		return builder.routes().route(p -> p.path("/get")
				.filters(f -> f
						.addRequestHeader("MyHeader", "MyURI")
						.addRequestParameter("param", "MyValue"))
				.uri("http://httpbin.org:80"))
				
				.route(p -> p.path("/currency-exchange/**")
				.uri("lb://CurrencyExchangeService"))
				
				.route(p -> p.path("/currency-conversion-feign/**")
						.uri("lb://CurrencyConversionService"))
				
				.route(p -> p.path("/currency-conversion/**")
						.uri("lb://CurrencyConversionService"))
				
				.route(p -> p.path("/currency-conversion-new/**")
						.filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)", "/currency-conversion-feign/"))
						.uri("lb://CurrencyConversionService"))
				
				.build();
	}

}
