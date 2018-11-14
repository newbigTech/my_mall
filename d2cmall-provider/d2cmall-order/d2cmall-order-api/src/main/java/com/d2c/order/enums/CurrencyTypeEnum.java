package com.d2c.order.enums;

import java.util.HashMap;
import java.util.Map;

public enum CurrencyTypeEnum {

	CNY(1, "人民币"), TWD(2, "台币"), HKD(3, "港币");

	private int code;
	private String display;
	private static Map<Integer, CurrencyTypeEnum> holder = new HashMap<Integer, CurrencyTypeEnum>();

	static {
		for (CurrencyTypeEnum currencyType : values()) {
			holder.put(currencyType.getCode(), currencyType);
		}
	}

	public static CurrencyTypeEnum getByCode(Integer i) {
		return holder.get(i);
	}

	CurrencyTypeEnum(int code, String display) {
		this.code = code;
		this.display = display;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

}
