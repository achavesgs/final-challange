package br.com.fiap.finalchallenge.model;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

public class Transaction {

	@ApiModelProperty( notes = "The transaction date")
	private LocalDateTime date;
	
	@ApiModelProperty( notes = "The amount value")
	private double amount;
	
	@ApiModelProperty( notes = "The timestamp value")
	private long timestamp;
	
	
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
