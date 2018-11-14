package com.d2c.frame.spark.stream;

import org.apache.spark.api.java.Optional;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.streaming.api.java.JavaPairDStream;

import com.d2c.common.mongodb.model.SparkStreamMongoDO;
import com.d2c.frame.spark.core.BaseSpark;

/**
 * Spark Streaming 实时数据处理基类
 * @author wull
 */
public abstract class BaseSparkStream extends BaseSpark {

	private static final long serialVersionUID = -8507656802700850305L;
	
	/**
	 * 实时累计数据，带状态修改
	 */
	protected <K, V> JavaPairDStream<K, V> updateStateByKey(JavaPairDStream<K, V> dStream, Function2<V, V, V> func){
		if(dStream == null) return null;
		return dStream.updateStateByKey((beans, lastOp) -> {
			V last = null;
			if(lastOp.isPresent()){
				last = (V) lastOp.get();
				if(last instanceof SparkStreamMongoDO){
					((SparkStreamMongoDO) last).setStreamUpdate(false);
				}
			}
			for(V bean : beans){
				V funcRes = func.call(bean, last);
				if(funcRes != null){
					last = funcRes;
				}
				if(last == null){
					last = bean;
				}
				if(last instanceof SparkStreamMongoDO){
					((SparkStreamMongoDO) last).setStreamUpdate(true);
				}
			}
			return Optional.of(last);
		});
	}

}
