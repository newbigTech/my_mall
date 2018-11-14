package com.d2c.frame.spark.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SparkConfig implements Serializable {

	private static final long serialVersionUID = 573296176709690433L;

	@Value("${app.name}")
	private String appName;

	@Value("${spark.master}")
	private String sparkMaster;

	@Value("${spark.group:dev}")
	private String sparkGroup;

	@Value("${spark.checkpoint.dir}")
	private String checkpointDir;

	/**
	 * 实时数据处理间隔 30 秒
	 */
	@Value("${spark.streaming.time: 30}")
	private long streamTime;
	
	@Value("${mongo.host}")
	private String mongoHost;

	@Value("${mongo.port}")
	private int mongoPort;

	@Value("${mongo.dbname}")
	private String mongoDbname;

	@Value("${kafka.bootstrap.servers}")
	private String kafkaUrl;
	
	@Value("${kafka.consumer.group.id:d2c}")
	private String groupId;

	
	//*************** public ***************

	public String getMongoUrl(){
		return getMongoUrl(mongoDbname);
	}
	
	public String getMongoUrl(String dbName){
		if(dbName == null) dbName = mongoDbname;
		return "mongodb://" + mongoHost + ":" + mongoPort + "/" + dbName ;
	}
	
	/**
	 * kafka 参数配置
	 */
	public Map<String, Object> getKafkaParams(){
		Map<String, Object> map = new HashMap<>();
        map.put("bootstrap.servers", kafkaUrl);
        map.put("group.id", groupId);
        map.put("key.serializer", "com.d2c.common.kafka.serializer.ObjectSerializer");
        map.put("key.deserializer", "com.d2c.common.kafka.serializer.ObjectDeserializer");
        map.put("value.serializer", "com.d2c.common.kafka.serializer.ObjectSerializer");
        map.put("value.deserializer", "com.d2c.common.kafka.serializer.ObjectDeserializer");
        return map;
	}
	
	//************** get ***************

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getSparkMaster() {
		return sparkMaster;
	}

	public void setSparkMaster(String sparkMaster) {
		this.sparkMaster = sparkMaster;
	}

	public String getMongoHost() {
		return mongoHost;
	}

	public void setMongoHost(String mongoHost) {
		this.mongoHost = mongoHost;
	}

	public int getMongoPort() {
		return mongoPort;
	}

	public void setMongoPort(int mongoPort) {
		this.mongoPort = mongoPort;
	}

	public String getMongoDbname() {
		return mongoDbname;
	}

	public void setMongoDbname(String mongoDbname) {
		this.mongoDbname = mongoDbname;
	}

	public String getCheckpointDir() {
		return checkpointDir;
	}

	public void setCheckpointDir(String checkpointDir) {
		this.checkpointDir = checkpointDir;
	}

	public long getStreamTime() {
		return streamTime;
	}

	public void setStreamTime(long streamTime) {
		this.streamTime = streamTime;
	}

	public String getKafkaUrl() {
		return kafkaUrl;
	}

	public void setKafkaUrl(String kafkaUrl) {
		this.kafkaUrl = kafkaUrl;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getSparkGroup() {
		return sparkGroup;
	}

	public void setSparkGroup(String sparkGroup) {
		this.sparkGroup = sparkGroup;
	}

}
