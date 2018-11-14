package com.d2c.frame.spark;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import com.d2c.frame.spark.config.SparkConfig;

public class SparkHandler implements Serializable {

	private static final long serialVersionUID = -1018213054058183844L;
	
	private SparkConfig config;
	
	private SparkSession spark;
	
	private JavaSparkContext jsc;
	
	private JavaStreamingContext jssc;
	
	private JavaInputDStream<ConsumerRecord<Object, Object>> kafkaDStream;
	
	public SparkHandler(SparkConfig config, SparkSession spark) {
		this.config = config;
		this.spark = spark;
	}

	/**
	 * 设置RDD中的数据写到磁盘
	 * <p>防止StackOverflowError内存栈溢出错误
	 * <p>如果spark分布式集群任务，必须写入到分布式文件服务器中
	 * <br>如: hdfs://bda00:8020/user
	 */
	public void addCheckpointDir(){
		getJsc().setCheckpointDir(config.getCheckpointDir());
	}
	
	public SparkConfig getConfig() {
		return config;
	}

	public SparkSession getSparkSession() {
		return spark;
	}

	public SparkContext getSparkContext() {
		return spark.sparkContext();
	}

	public JavaSparkContext getJsc() {
		if(jsc == null){
			jsc = new JavaSparkContext(getSparkContext());
		}
		return jsc;
	}

	public JavaStreamingContext getJssc() {
		return getJssc(config.getStreamTime());
	}

	public JavaStreamingContext getJssc(long second) {
		if(jssc == null){
			jssc = new JavaStreamingContext(getJsc(), Durations.seconds(second));
		}
		return jssc;
	}

	public JavaInputDStream<ConsumerRecord<Object, Object>> getKafkaDStream(){
		return kafkaDStream;
	}

	public JavaInputDStream<ConsumerRecord<Object, Object>> getKafkaDStream(String... topics){
		return getKafkaDStream(Arrays.asList(topics));
	}
	
	public JavaInputDStream<ConsumerRecord<Object, Object>> getKafkaDStream(Collection<String> topics){
		if(kafkaDStream == null){
			kafkaDStream = KafkaUtils.createDirectStream(getJssc(),
					LocationStrategies.PreferConsistent(),
					ConsumerStrategies.Subscribe(topics, config.getKafkaParams()));
		}
		return kafkaDStream;
	}

}
