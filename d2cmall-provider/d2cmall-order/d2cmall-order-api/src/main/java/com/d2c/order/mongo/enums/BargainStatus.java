package com.d2c.order.mongo.enums;

public enum BargainStatus {

	BEGIN("砍价中"), PROCESS("帮助砍价中"), ORDERED("等待支付"), BUYED("购买成功");

	String remark;

	BargainStatus(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

}
