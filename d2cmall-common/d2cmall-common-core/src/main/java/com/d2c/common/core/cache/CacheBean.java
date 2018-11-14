package com.d2c.common.core.cache;

import java.io.Serializable;

import com.d2c.common.base.utils.JsonUt;

public class CacheBean implements Serializable {
	
	private static final long serialVersionUID = 5426494620491161300L;

	private Object value;
    
    private Long outTime;
    
    public CacheBean(Object value, long keepTime) {
		this.value = value;
		this.outTime = System.currentTimeMillis() + keepTime;
	}
	
	@Override
	public String toString() {
		return JsonUt.serialize(this);
	}
    
    public CacheBean(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Long getOutTime() {
		return outTime;
	}

	public void setOutTime(Long outTime) {
		this.outTime = outTime;
	}

}
