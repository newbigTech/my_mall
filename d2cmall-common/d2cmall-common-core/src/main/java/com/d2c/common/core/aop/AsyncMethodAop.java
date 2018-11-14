package com.d2c.common.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.d2c.common.base.exception.BaseException;
import com.d2c.common.base.thread.MyExecutors;
import com.d2c.common.core.annotation.AsyncMethod;

/**
 * 异步调用AOP切片处理类
 * @author wull
 */
@Aspect
@Component
public class AsyncMethodAop extends BaseAop {
	
	/**
	 * 异步调用AsyncMethod
	 * @see AsyncMethod
	 */
    @Around("@annotation(com.d2c.common.core.annotation.AsyncMethod)")  
    public void cacheMethod(ProceedingJoinPoint point) {  
    	MyExecutors.cachePool().submit(new Runnable() {
			@Override
			public void run() {
				try {
					point.proceed(point.getArgs());
				} catch (Throwable e) {
					throw new BaseException("异步调用异常:" + e.getMessage(), e);
				}
			}
		});
    }

	
}
