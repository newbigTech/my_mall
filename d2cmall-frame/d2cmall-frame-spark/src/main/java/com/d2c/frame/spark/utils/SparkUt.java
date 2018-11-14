package com.d2c.frame.spark.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.d2c.common.base.utils.StringUt;

/**
 * Spark工具类
 * @author wull
 */
public class SparkUt {
	
	protected final static Logger logger = LoggerFactory.getLogger(SparkUt.class);
	
	/**
	 * collection名称
	 */
	public static String collect(Class<?> clz) {
		return StringUt.lowerFrist(clz.getSimpleName());
	}
	
}
