package com.KafkaFlinkDemo.dataSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.KafkaFlinkDemo.model.LiveStockQuote;
import com.KafkaFlinkDemo.properties.ApplicationProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LiveStockDataFetcher {

	private Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Autowired
	ApplicationProperties props;

	public static enum API_FUNCTION {
		BATCH_STOCK_QUOTES, TIME_SERIES_INTRADAY

	}

	public static enum INTRADAY_STOCK_QUOTE_INTERVAL {

		ONE_MINUTE("1min"), FIVE_MINUTE("5min"), FIFTEEN_MINUTE("15min"), THIRTY_MINUTE("30min"), SIXTY_MINUTE("60min");

		private String interval;

		public String getInterval() {
			return interval;
		}

		private INTRADAY_STOCK_QUOTE_INTERVAL(String interval) {
			this.interval = interval;
		}

	}

	public static enum QUERY_PARAM_NAMES {

		FUNCTION("function"), SYMBOL("symbol"), SYMBOLS("symbols"), API_KEY("apikey"), TIME_INTERVAL("interval");

		private String paramName;

		private QUERY_PARAM_NAMES(String paramName) {
			this.paramName = paramName;
		}

		public String getParamName() {
			return paramName;
		}

	}

	public ResponseEntity<String> callHttpGet(String url, MultiValueMap<String, String> params) {
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build();
		RestTemplate rest = new RestTemplate();
		System.out.println(uriComponents.toUriString());
		return rest.getForEntity(uriComponents.toUri(), String.class);
	}

	public MultiValueMap<String, String> getHttpGetQueryParams(String function, String interval, String symbol,
			String apiKey) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add(QUERY_PARAM_NAMES.FUNCTION.getParamName(), function);
		params.add(QUERY_PARAM_NAMES.API_KEY.getParamName(), apiKey);
		params.add(QUERY_PARAM_NAMES.SYMBOLS.getParamName(), symbol);
		params.add(QUERY_PARAM_NAMES.TIME_INTERVAL.getParamName(), interval);
		return params;
	}

	public List<LiveStockQuote> callApiForIntradayQuotes1Min(String symbols) {

		ResponseEntity<String> response = callHttpGet(props.getDataApiUrl(),
				getHttpGetQueryParams(API_FUNCTION.BATCH_STOCK_QUOTES.name(),
						INTRADAY_STOCK_QUOTE_INTERVAL.ONE_MINUTE.getInterval(), symbols, props.getStockDataApiKey()));

		if (response != null && response.getBody() != null) {
			try {
				JSONObject responseJson = new JSONObject(response.getBody());

				if (responseJson.has("Stock Quotes")) {
					JSONArray allQuotesJson = responseJson.getJSONArray("Stock Quotes");

					ObjectMapper mapper = new ObjectMapper();
					List<LiveStockQuote> allQuotesList = mapper.readValue(allQuotesJson.toString(),
							new TypeReference<List<LiveStockQuote>>() {});
					if(allQuotesList==null) {
						allQuotesList=new ArrayList<>();
					}
					/*
					 * for (int i=0;i< ) { String key = (String) keys.next(); if (
					 * allQuotesJson.get(key) instanceof JSONObject ) { JSONObject tickJson =
					 * allQuotesJson.getJSONObject(key); LiveStockQuote liveTickObj =
					 * mapper.readValue(tickJson.toString(), LiveStockQuote.class); //Date timeStamp
					 * = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(key);
					 * 
					 * //liveTickObj.setSymbol(symbol);
					 * //liveTickObj.setTimestamp(timeStamp.getTime());
					 * System.out.println(liveTickObj.getSymbol()+" ############");
					 * allQuotesList.add(liveTickObj); } }
					 */
					return allQuotesList;
				}
			} catch (JSONException e) {
				e.printStackTrace();
				LOG.error(e.getMessage());
			} catch (JsonParseException e) {
				e.printStackTrace();
				LOG.error(e.getMessage());
			} catch (JsonMappingException e) {
				e.printStackTrace();
				LOG.error(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				LOG.error(e.getMessage());
			}

		}
		return new ArrayList<LiveStockQuote>(0);
	}
}
