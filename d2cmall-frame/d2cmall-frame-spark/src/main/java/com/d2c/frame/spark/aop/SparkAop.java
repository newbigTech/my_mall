package com.d2c.frame.spark.aop;

import static com.d2c.frame.spark.constant.SparkConst.COLLECT_INPUT;
import static com.d2c.frame.spark.constant.SparkConst.COLLECT_OUTPUT;
import static com.d2c.frame.spark.constant.SparkConst.MONGO_URL_INPUT;
import static com.d2c.frame.spark.constant.SparkConst.MONGO_URL_OUTPUT;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.d2c.common.base.exception.BaseException;
import com.d2c.common.base.utils.AnnUt;
import com.d2c.common.base.utils.ReflectUt;
import com.d2c.common.base.utils.StringUt;
import com.d2c.common.core.aop.BaseAop;
import com.d2c.frame.spark.SparkHandler;
import com.d2c.frame.spark.annotation.Spark;
import com.d2c.frame.spark.annotation.SparkStream;
import com.d2c.frame.spark.config.SparkConfig;
import com.d2c.frame.spark.utils.SparkUt;

/**
 * Spark切片方法
 * @author wull
 */
@Aspect
@Component
public class SparkAop extends BaseAop {

	@Autowired
	protected SparkConfig sparkConfig;

	/**
	 * Spark切片方法
	 */
	@Around("@annotation(com.d2c.frame.spark.annotation.Spark)")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		SparkSession session = null;
		Object res = null;
		try {
			Method md = getMethod(point);
			Spark ann = md.getAnnotation(Spark.class);
			SparkSession.Builder builder = SparkSession.builder()
					.appName(sparkConfig.getAppName())
					.master(sparkConfig.getSparkMaster());
			
			//添加mongo参数
			if(AnnUt.isNotDef(ann.in()) || AnnUt.isNotDef(ann.out()) || AnnUt.isNotDef(ann.inclz()) || AnnUt.isNotDef(ann.outclz())){
				String inclzName = AnnUt.isNotDef(ann.inclz()) ? SparkUt.collect(ann.inclz()) : null;
				String outclzName = AnnUt.isNotDef(ann.outclz()) ? SparkUt.collect(ann.outclz()) : null;
			
				builder.config(COLLECT_INPUT, AnnUt.getValue(ann.in(), inclzName, ann.out(), outclzName));
				builder.config(COLLECT_OUTPUT, AnnUt.getValue(ann.out(), outclzName, ann.in(), inclzName));

				String indb = AnnUt.getValue(ann.indb(), ann.db(), sparkConfig.getMongoDbname());
				String outdb = AnnUt.getValue(ann.outdb(), ann.db(), sparkConfig.getMongoDbname());
				builder.config(MONGO_URL_INPUT, sparkConfig.getMongoUrl(indb));
				builder.config(MONGO_URL_OUTPUT, sparkConfig.getMongoUrl(outdb));
			}
			
			logger.info("=== spark 配置 ===" + builder.org$apache$spark$sql$SparkSession$Builder$$options);
			
			session = builder.getOrCreate();
			
			// 替换参数: SparkSession JavaSparkContext
			Object[] args = point.getArgs();
			SparkHandler spark = new SparkHandler(sparkConfig, session);
			int i = 0;
			for(Class<?> argType : md.getParameterTypes()){
				if(argType.isAssignableFrom(SparkHandler.class)){
					args[i] = spark;
				}else if(argType.isAssignableFrom(SparkSession.class)){
					args[i] = spark.getSparkSession();
				}else if(argType.isAssignableFrom(JavaSparkContext.class)){
					args[i] = spark.getJsc();
				}
				i ++;
			}

			try {
				res = point.proceed(args);
			} catch (Exception e) {
				logger.error("Spark 执行异常...方法:" + getClass(point).getSimpleName() + "." + md.getName(), e);
			}
			
		} catch (Exception e) {
			logger.error("执行返回异常...", e);
			throw e;
		} finally {
			if(session != null){
				session.close();
			}
		}
		return res;
	}
	
	/**
	 * SparkStream 标签方法启动执行
	 * <p>
	 * @see SparkStream
	 */
	@Async
	public void aroundStream(Object bean, Method md) {
		logger.info("执行aroundStream");
		JavaStreamingContext jssc = null;
		try {
			jssc = JavaStreamingContext.getOrCreate(getCheckpointDir(bean, md), () ->{
				return buildStream(bean, md);
			});
			jssc.start();
			jssc.awaitTermination();
		} catch (Exception e) {
			logger.error("SparkStream启动异常... bean: " + bean + " method: " + md.getName() + "\n 错误信息: " + e.getMessage(), e);
		} finally {
			if(jssc != null){
				jssc.close();
			}
		}
	}

	public JavaStreamingContext buildStream(Object bean, Method md) {
		logger.info("创建JavaStreamingContext...");
		
		SparkStream ann = md.getAnnotation(SparkStream.class);
		SparkSession.Builder builder = SparkSession.builder()
				.appName(sparkConfig.getAppName())
				.master(sparkConfig.getSparkMaster());
		
		//优雅的关闭
		builder.config("spark.streaming.stopGracefullyOnShutdown", true);
		
		//添加mongo参数
		if(AnnUt.isNotDef(ann.in()) || AnnUt.isNotDef(ann.out()) || AnnUt.isNotDef(ann.inclz()) || AnnUt.isNotDef(ann.outclz())){
			String inclzName = AnnUt.isNotDef(ann.inclz()) ? ann.inclz().getSimpleName() : null;
			String outclzName = AnnUt.isNotDef(ann.outclz()) ? ann.outclz().getSimpleName() : null;
		
			builder.config("spark.mongodb.input.collection", AnnUt.getValue(ann.in(), inclzName, ann.out(), outclzName));
			builder.config("spark.mongodb.output.collection", AnnUt.getValue(ann.out(), inclzName, ann.in(), outclzName));

			String indb = AnnUt.getValue(ann.indb(), ann.db(), sparkConfig.getMongoDbname());
			String outdb = AnnUt.getValue(ann.outdb(), ann.db(), sparkConfig.getMongoDbname());
			builder.config("spark.mongodb.input.uri", sparkConfig.getMongoUrl(indb));
			builder.config("spark.mongodb.output.uri", sparkConfig.getMongoUrl(outdb));
		}
		
		if(ann.wal()){
			builder.config("spark.streaming.receiver.writeAheadLog.enable", true);
		}
		
		SparkSession session = builder.getOrCreate();
		
		// 替换参数: SparkSession JavaSparkContext
		Object[] args = ReflectUt.getNullArgs(md);
		SparkHandler spark = new SparkHandler(sparkConfig, session);
		JavaStreamingContext jssc = spark.getJssc(ann.time());
		jssc.checkpoint(getCheckpointDir(bean, md));
		
		//订阅消息名称topic
		List<String> topics = new ArrayList<>();
		topics.addAll(Arrays.asList(ann.topics()));
		String topic = AnnUt.getValue(ann.topic(), ann.topicEnum().topic(), ann.value());
		if(topics.isEmpty() && AnnUt.isNotDef(topic)){
			topics.add(topic);
		}
		if(topics.isEmpty()){
			throw new BaseException("@SparkStream 监听的 topic, topics Kafka消息队列名称不能为空！");
		}

		spark.getKafkaDStream(topics);
		
		int i = 0;
		for(Class<?> argType : md.getParameterTypes()){
			if(argType.isAssignableFrom(SparkHandler.class)){
				args[i] = spark;
			}else if(argType.isAssignableFrom(SparkSession.class)){
				args[i] = spark.getSparkSession();
			}else if(argType.isAssignableFrom(JavaSparkContext.class)){
				args[i] = spark.getJsc();
			}else if(argType.isAssignableFrom(JavaStreamingContext.class)){
				args[i] = jssc;
			}else if(argType.isAssignableFrom(JavaInputDStream.class)){
				args[i] = spark.getKafkaDStream();
			}
			i ++;
		}
		
		try {
			ReflectUt.invokeMethod(bean, md, args);
		} catch (Exception e) {
			logger.error("SparkStream执行异常...方法:" + bean + "." + md.getName(), e);
		}
			
		return jssc;
	}
	
	private String getCheckpointDir(Object bean, Method md){
		return StringUt.join("/", sparkConfig.getCheckpointDir(), sparkConfig.getSparkGroup(), bean.getClass().getSimpleName(), md.getName());
	}
	
}
