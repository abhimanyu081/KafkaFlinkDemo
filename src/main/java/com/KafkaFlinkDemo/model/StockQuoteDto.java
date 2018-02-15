package com.KafkaFlinkDemo.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class StockQuoteDto{
	
	/**
	 * 
	 */

	BigDecimal ZERO = BigDecimal.ZERO;
	BigDecimal MAX_VALUE = BigDecimal.valueOf(Double.MAX_VALUE);
	
	
	private String dateTime;
	private Integer interval;
	private String symbol;
	private BigDecimal open=ZERO;
	private BigDecimal high=ZERO;
	private BigDecimal low=MAX_VALUE;
	private BigDecimal close=ZERO;
	private BigDecimal price=ZERO;
	private BigInteger volume=BigInteger.ZERO;
	private Date timestamp2=new Date();
	
	
	public StockQuoteDto() {}
	
	public StockQuoteDto(String symbol, BigDecimal price, BigInteger volume, Date timestamp2) {
		super();
		this.symbol = symbol;
		this.price = price;
		this.volume = volume;
		this.timestamp2 = timestamp2;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigInteger getVolume() {
		return volume;
	}
	public void setVolume(BigInteger volume) {
		this.volume = volume;
	}

	public Date getTimestamp2() {
		return timestamp2;
	}

	public void setTimestamp2(Date timestamp2) {
		this.timestamp2 = timestamp2;
	}

	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public Integer getInterval() {
		return interval;
	}
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
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
	@Override
	public String toString() {
		return "StockOHLC [dateTime=" + dateTime + ", interval=" + interval + ", symbol=" + symbol + ", open=" + open
				+ ", high=" + high + ", low=" + low + ", close=" + close + "]";
	}
}
