package com.KafkaFlinkDemo.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LiveStockQuote {
	
	@JsonProperty(value="1. open")
	private BigDecimal open;
	
	@JsonProperty(value="2. high")
	private BigDecimal high;
	
	@JsonProperty(value="3. low")
	private BigDecimal low;
	
	@JsonProperty(value="4. close")
	private BigDecimal close;
	
	@JsonProperty(value="5. volume")
	private BigInteger volume;
	
	public Long timestamp;
	
	private String dateTime;
	
	private String symbol;
	
	
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public BigDecimal getOpen() {
		return open;
	}
	public void setOpen(BigDecimal open) {
		this.open = open;
	}
	public BigDecimal getHigh() {
		return high;
	}
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	public BigDecimal getLow() {
		return low;
	}
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	public BigDecimal getClose() {
		return close;
	}
	public void setClose(BigDecimal close) {
		this.close = close;
	}

	public BigInteger getVolume() {
		return volume;
	}
	public void setVolume(BigInteger volume) {
		this.volume = volume;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	@Override
	public String toString() {
		return "LiveStockQuote [open=" + open + ", high=" + high + ", low=" + low + ", close=" + close + ", volume="
				+ volume + ", timestamp=" + timestamp + ", dateTime=" + dateTime + ", symbol=" + symbol + "]";
	}
	
	

	


}
