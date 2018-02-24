package com.KafkaFlinkDemo.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="nse_quotes")
public class LiveIndexDataDto implements Serializable{

	/**
	 * 
	 */
	@Id
    private String id;
	
	private static final long serialVersionUID = 1L;
	 
	private String OpenPrice;
	private String HighPrice;
	private String LowPrice;
	private String ClosePrice;
	private String Value;
	private String NetChange;
	private String PercentChange;
	private String Volume;
	private String DateTime;
	private String Symbol;
	
	
	
	public String getSymbol() {
		return Symbol;
	}
	public void setSymbol(String symbol) {
		Symbol = symbol;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOpenPrice() {
		return OpenPrice;
	}
	public void setOpenPrice(String openPrice) {
		OpenPrice = openPrice;
	}
	public String getHighPrice() {
		return HighPrice;
	}
	public void setHighPrice(String highPrice) {
		HighPrice = highPrice;
	}
	public String getLowPrice() {
		return LowPrice;
	}
	public void setLowPrice(String lowPrice) {
		LowPrice = lowPrice;
	}
	public String getClosePrice() {
		return ClosePrice;
	}
	public void setClosePrice(String closePrice) {
		ClosePrice = closePrice;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	public String getNetChange() {
		return NetChange;
	}
	public void setNetChange(String netChange) {
		NetChange = netChange;
	}
	
	public String getPercentChange() {
		return PercentChange;
	}
	public void setPercentChange(String percentChange) {
		PercentChange = percentChange;
	}
	public String getVolume() {
		return Volume;
	}
	public void setVolume(String volume) {
		Volume = volume;
	}
	public String getDateTime() {
		return DateTime;
	}
	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}
	
	
	
		
}
