package com.d2c.common.core.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 异步方法
 * @author wull
 */
@Target({METHOD})
@Retention(RUNTIME)
@Documented
public @interface AsyncMethod {

}
