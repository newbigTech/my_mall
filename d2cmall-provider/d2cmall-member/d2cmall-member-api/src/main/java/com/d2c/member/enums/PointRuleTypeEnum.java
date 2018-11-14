package com.d2c.member.enums;

import java.math.BigDecimal;

public enum PointRuleTypeEnum {

	REGISTER(1, "注册"), FIRST(2, "首单"), ORDER(3, "订单") {
		@Override
		public int calculatePoint(String ratio, BigDecimal amount) {
			int point = 0;
			if (amount != null && amount.compareTo(new BigDecimal(0)) > 0) {
				String[] ratios = ratio.split(":");
				if (ratios.length > 1) {
					point = amount.multiply(new BigDecimal(ratios[1])).divide(new BigDecimal(ratios[0])).intValue();
				}
			}
			return point;
		}
	},
	COMMENT(4, "评价"), RECOMEND(5, "推荐评价"), MEMBERSHARE(6, "买家秀"), COMMENTDELETE(7, "退货扣除评论所得积分"), SING(8,
			"签到"), EXCHANGE(9, "兑换"), TASK(10, "日常任务");

	private int code;
	private String display;

	PointRuleTypeEnum(int code, String display) {
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

	public int calculatePoint(String ratios, BigDecimal amount) {
		return Integer.parseInt(ratios);

	}

}
