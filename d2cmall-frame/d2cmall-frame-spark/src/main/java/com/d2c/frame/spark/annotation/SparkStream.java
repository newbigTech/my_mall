package com.d2c.frame.spark.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.d2c.common.kafka.enums.KafkaEnum;

/**
 * Spark Streaming 方法调用
 * @author wull
 */
@Target({METHOD})
@Retention(RUNTIME)
@Documented
public @interface SparkStream {
	
	String value() default "";

	String topic() default "";
	
	String[] topics() default {};
	
	KafkaEnum topicEnum() default KafkaEnum.TestTopic;
	
	/**
	 * Stream实时读取批量间隔时间(s)
	 */
	long time() default 10;

	/**
	 * 是否开启 Write ahead log 保证数据零丢失(zero-data loss)
	 */
	boolean wal() default false;

	//*************** mongo *****************
	
	String db() default "";
	
	String indb() default "";
	
	String outdb() default "";
	
	String in() default "";
	
	String out() default "";
	
	Class<?> inclz() default Void.class;
	
	Class<?> outclz() default Void.class;

}
