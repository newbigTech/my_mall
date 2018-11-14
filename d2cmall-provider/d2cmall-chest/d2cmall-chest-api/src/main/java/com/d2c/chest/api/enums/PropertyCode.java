package com.d2c.chest.api.enums;

public enum PropertyCode {
	
	CATEGORY("商品品类"), 
	PRODUCT_STYLE("商品风格");
	
	String remark;
	
	PropertyCode(String remark) {
		this.remark = remark;
	}

}
