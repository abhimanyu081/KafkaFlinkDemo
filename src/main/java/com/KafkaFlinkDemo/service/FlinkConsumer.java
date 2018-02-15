package com.KafkaFlinkDemo.service;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer08;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;

import com.KafkaFlinkDemo.constants.StringConstants;
import com.KafkaFlinkDemo.functions.GainerLoserFilterFunction;
import com.KafkaFlinkDemo.functions.StockOHLCFunction;
import com.KafkaFlinkDemo.model.LiveStockQuote;
import com.KafkaFlinkDemo.model.StockQuoteDto;
import com.KafkaFlinkDemo.properties.ApplicationProperties;
import com.KafkaFlinkDemo.sink.RedisMapperForStockQuote;
import com.KafkaFlinkDemo.util.GenericJavaDesrializerSchema;

@Service
public class FlinkConsumer {
	
	private Logger LOG = LoggerFactory.getLogger(getClass().getSimpleName());
	
	@Autowired
	private KafkaTemplate<String, StockQuoteDto> kafkaTemplate;

	@Autowired
	ApplicationProperties props;

	
	public void windowExample() throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", props.getBootstrapServer());
		properties.setProperty("group.id", props.getGroupId());
		properties.setProperty("zookeeper.connect", "localhost:2181");
		DataStream<String> stream = env
				.addSource(new FlinkKafkaConsumer08<String>(props.getKafkaTopicStkQuotes(), new SimpleStringSchema(), properties));
	

		stream.map(new MapFunction<String, String>() {
			private static final long serialVersionUID = -6867736771747690202L;

			@Override
			public String map(String value) throws Exception {
				return "Stream Value: " + value;
			}
		}).print();

		env.execute();

	}
	
	public void createStockOhlcByInterval(int interval) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", props.getBootstrapServer());
		properties.setProperty("group.id", props.getGroupId());
		properties.setProperty("zookeeper.connect", "localhost:2181");
		
		TypeInformation<LiveStockQuote> typeInfo = TypeInformation.of(LiveStockQuote.class);
		DataStream<LiveStockQuote> stream =env.addSource(new FlinkKafkaConsumer08<LiveStockQuote>(props.getKafkaTopicStkQuotes(),
				new GenericJavaDesrializerSchema<LiveStockQuote>(LiveStockQuote.class), properties), typeInfo);

		stream.keyBy("symbol")
		.timeWindow(Time.of(interval, TimeUnit.MINUTES))
		.aggregate(new StockOHLCFunction())
		//.addSink(new SocketClientSink<>("localhost", 5555, new StockOHLC()));
		/*.addSink(new SocketClientSink<StockOHLC>("localhost", 1111, new SerializationSchema<StockOHLC>() {

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
		}));
*/		
		.addSink(new RedisSink<>(getFlinkJedisPoolConfig(), new RedisMapperForStockQuote(StringConstants.REDIS_KEY_GAINERS, RedisCommand.ZADD)));		

		env.execute();
	}
	
	public void filterStockQuote(int interval) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", props.getBootstrapServer());
		properties.setProperty("group.id", props.getGroupId());
		properties.setProperty("zookeeper.connect", "localhost:2181");
		
		TypeInformation<LiveStockQuote> typeInfo = TypeInformation.of(LiveStockQuote.class);
		DataStream<LiveStockQuote> stream =env.addSource(new FlinkKafkaConsumer08<LiveStockQuote>(props.getKafkaTopicStkQuotes(),
				new GenericJavaDesrializerSchema<LiveStockQuote>(LiveStockQuote.class), properties), typeInfo);
		JsonDeserializer<StockQuoteDto> jsonDeserializer = new JsonDeserializer<>(StockQuoteDto.class);
		
		stream.keyBy("symbol")
		.timeWindow(Time.of(interval, TimeUnit.MINUTES))
		.aggregate(new StockOHLCFunction())
		//.writeToSocket("localhost", 9090, new StockOHLC());
		.addSink(new RedisSink<>(getFlinkJedisPoolConfig(), new RedisMapperForStockQuote(StringConstants.REDIS_KEY_GAINERS, RedisCommand.ZADD)));		

		env.execute();
	}
	
	public void getGainerLoser(int interval) throws Exception {
		LOG.info("starting GainerLoser ......");
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", props.getBootstrapServer());
		properties.setProperty("group.id", props.getGroupId());
		properties.setProperty("zookeeper.connect", "localhost:2181");
		
		TypeInformation<StockQuoteDto> typeInfo = TypeInformation.of(StockQuoteDto.class);
		DataStream<StockQuoteDto> stream =env.addSource(new FlinkKafkaConsumer08<StockQuoteDto>(props.getKafkaTopicStkQuotes(),
				new GenericJavaDesrializerSchema<StockQuoteDto>(StockQuoteDto.class), properties), typeInfo);

		LOG.info("stream created .....");
		stream.filter(new GainerLoserFilterFunction())
		.addSink(new RedisSink<>(getFlinkJedisPoolConfig(), new RedisMapperForStockQuote(StringConstants.REDIS_KEY_GAINERS, RedisCommand.ZADD)));
		
		env.execute();
		LOG.info("gAINERS PUSHED TO REDIS");
		
	}
	
	public FlinkJedisPoolConfig getFlinkJedisPoolConfig() {
	return 	new FlinkJedisPoolConfig.Builder()
		.setHost(props.getRedisHost())
		.setPort(props.getRedisPort())
		.setDatabase(props.getRedisDb()).build();
	}
	
	
	

}
