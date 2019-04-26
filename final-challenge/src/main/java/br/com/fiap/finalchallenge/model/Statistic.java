package br.com.fiap.finalchallenge.model;

import io.swagger.annotations.ApiModelProperty;

public class Statistic {
	
	@ApiModelProperty( notes = "The transaction total sum")
	private double sum;
	
	@ApiModelProperty( notes = "The minimum transaction value")
	private double min;
	
	@ApiModelProperty( notes = "The maximum transaction value")
	private double max;
	
	@ApiModelProperty( notes = "The avarage transaction value")
	private double avg;
	
	@ApiModelProperty( notes = "The total transaction quantity")
	private long count;
	
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
	

}
