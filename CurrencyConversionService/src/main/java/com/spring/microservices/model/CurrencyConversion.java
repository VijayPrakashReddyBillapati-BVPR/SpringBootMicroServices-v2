package com.spring.microservices.model;

import java.math.BigDecimal;

public class CurrencyConversion {

	private long ID;
	private String from;
	private String to;
	private BigDecimal quantity;
	private BigDecimal conversionMultiple;
	private BigDecimal totalcalculatedAmount;
	private String environment;
	
	public CurrencyConversion(long iD, String from, String to, BigDecimal quantity, BigDecimal conversionMultiple,
			BigDecimal totalcalculatedAmount, String environment) {
		super();
		ID = iD;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
		this.quantity = quantity;
		this.totalcalculatedAmount = totalcalculatedAmount;
		this.environment = environment;
	}
	
	public CurrencyConversion() {
		super();
	}
	
	
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}
	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getTotalcalculatedAmount() {
		return totalcalculatedAmount;
	}
	public void setTotalcalculatedAmount(BigDecimal totalcalculatedAmount) {
		this.totalcalculatedAmount = totalcalculatedAmount;
	}
	
}
