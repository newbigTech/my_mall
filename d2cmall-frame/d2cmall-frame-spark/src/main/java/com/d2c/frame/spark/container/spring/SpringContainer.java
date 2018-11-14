package com.d2c.frame.spark.container.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.d2c.frame.spark.container.Container;

public class SpringContainer implements Container {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringContainer.class);

    public static final String DEFAULT_SPRING_CONFIG = "classpath*:META-INF/spring/*.xml";

    protected static ClassPathXmlApplicationContext context;
    
    public static ClassPathXmlApplicationContext getContext() {
		return context;
	}

	public void start() {
        String configPath = DEFAULT_SPRING_CONFIG;
        context = new ClassPathXmlApplicationContext(configPath.split("[,\\s]+"));
        context.start();
    }

    public void stop() {
        try {
            if (context != null) {
                context.stop();
                context.close();
                context = null;
            }
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }
}
