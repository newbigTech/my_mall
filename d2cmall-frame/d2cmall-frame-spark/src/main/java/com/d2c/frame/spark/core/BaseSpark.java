package com.d2c.frame.spark.core;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Spark 处理基类
 * @author wull
 */
public abstract class BaseSpark implements Serializable {
	
	private static final long serialVersionUID = 9044627425496112551L;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected SparkTemplate sparkTemplate;

}
