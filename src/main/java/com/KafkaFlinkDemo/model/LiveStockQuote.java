package com.KafkaFlinkDemo.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LiveStockQuote {

	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;

	@JsonProperty(value = "1. open")
	private BigDecimal open;

	@JsonProperty(value = "2. high")
	private BigDecimal high;

	@JsonProperty(value = "3. low")
	private BigDecimal low;

	@JsonProperty(value = "4. close")
	private BigDecimal close;

	@JsonProperty(value = "5. volume")
	private BigInteger volume;

	public Long timestamp;

	private String dateTime;

	// properties of batch stokc quotes

	@JsonProperty("1. symbol")
	private String symbol;

	@JsonProperty(value = "2. price")
	private BigDecimal price;

	@JsonProperty(value = "3. volume")
	private BigInteger volume2;

	@JsonProperty("4. timestamp")
	 @JsonFormat
     (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	public Date timestamp2;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigInteger getVolume2() {
		return volume2;
	}

	public void setVolume2(BigInteger volume2) {
		this.volume2 = volume2;
	}

	

	public Date getTimestamp2() {
		return timestamp2;
	}

	public void setTimestamp2(Date timestamp2) {
		this.timestamp2 = timestamp2;
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
