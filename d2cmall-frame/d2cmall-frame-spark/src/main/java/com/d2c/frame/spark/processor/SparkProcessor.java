package com.d2c.frame.spark.processor;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.d2c.common.base.utils.BeanUt;
import com.d2c.frame.spark.annotation.SparkStream;
import com.d2c.frame.spark.aop.SparkAop;
import com.d2c.frame.spark.stream.BaseSparkStream;

@Component
public class SparkProcessor implements BeanPostProcessor {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SparkAop sparkAop;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Class<?> clz = bean.getClass();
		// 继承BaseSparkStream
		if(clz.getSuperclass().equals(BaseSparkStream.class)){
			for(Method md : BeanUt.getMethods(clz, SparkStream.class)){
				sparkAop.aroundStream(bean, md);
			}
		}
		return bean;
	}

}

