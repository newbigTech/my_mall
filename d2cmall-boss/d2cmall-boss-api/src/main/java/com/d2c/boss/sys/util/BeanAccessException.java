package com.d2c.boss.sys.util;

import com.d2c.common.base.exception.BusinessException;

public class BeanAccessException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public BeanAccessException() {
		super();
	}

	public BeanAccessException(String message) {
		super(message);
	}

}
