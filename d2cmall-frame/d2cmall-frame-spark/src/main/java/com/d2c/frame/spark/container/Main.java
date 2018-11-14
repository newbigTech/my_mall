package com.d2c.frame.spark.container;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.d2c.common.base.utils.BeanUt;
import com.d2c.frame.spark.container.spring.SpringContainer;

/**
 * Main主函数
 * @author wull
 */
public class Main {

    public static final String SHUTDOWN_HOOK_KEY = "spark.shutdown.hook";
    
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    
    private static volatile boolean running = true;

    public static void main(String[] args) {
        try {
            
            final List<Container> containers = new ArrayList<Container>();
            containers.add(BeanUt.newInstance(SpringContainer.class));
            
            if ("true".equals(System.getProperty(SHUTDOWN_HOOK_KEY))) {
	            Runtime.getRuntime().addShutdownHook(new Thread() {
	                public void run() {
	                    for (Container container : containers) {
	                        try {
	                            container.stop();
	                            logger.info("Spark " + container.getClass().getSimpleName() + " stopped!");
	                        } catch (Throwable t) {
	                            logger.error(t.getMessage(), t);
	                        }
	                        synchronized (Main.class) {
	                            running = false;
	                            Main.class.notify();
	                        }
	                    }
	                }
	            });
            }
            
            for (Container container : containers) {
                container.start();
                logger.info("Spark " + container.getClass().getSimpleName() + " started!");
            }
            logger.info("Spark service server started!");
        } catch (RuntimeException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            System.exit(1);
        }
        synchronized (Main.class) {
            while (running) {
                try {
                    Main.class.wait();
                } catch (Throwable e) {
                }
            }
        }
    }
    
}
