package com.d2c.frame.spark.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Spark方法调用
 * @author wull
 */
@Target({METHOD})
@Retention(RUNTIME)
@Documented
public @interface Spark {
	
	String db() default "";
	
	String indb() default "";
	
	String outdb() default "";
	
	String in() default "";
	
	String out() default "";
	
	Class<?> inclz() default Void.class;
	
	Class<?> outclz() default Void.class;

}
