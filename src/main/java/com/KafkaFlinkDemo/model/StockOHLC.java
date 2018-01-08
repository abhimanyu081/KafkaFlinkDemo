package com.KafkaFlinkDemo.model;

import java.math.BigDecimal;

import org.apache.flink.api.common.serialization.SerializationSchema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StockOHLC implements SerializationSchema<StockOHLC>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BigDecimal ZERO = BigDecimal.ZERO;
	BigDecimal MAX_VALUE = BigDecimal.valueOf(Double.MAX_VALUE);
	
	
	private String dateTime;
	private Integer interval;
	private String symbol;
	private BigDecimal open=ZERO;
	private BigDecimal high=ZERO;
	private BigDecimal low=MAX_VALUE;
	private BigDecimal close=ZERO;
	
	
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
	@Override
	public byte[] serialize(StockOHLC element) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsBytes(element);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
