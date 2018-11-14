package com.d2c.frame.spark.job;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Spark执行任务 
 * @author wull
 */
public abstract class BaseSparkTask implements Serializable {
	
	private static final long serialVersionUID = -5751871603585341359L;
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
}
