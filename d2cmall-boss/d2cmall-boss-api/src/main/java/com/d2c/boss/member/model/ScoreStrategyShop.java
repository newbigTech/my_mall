package com.d2c.boss.member.model;

import java.math.BigDecimal;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * 店铺积分活动 TODO
 */
@Table(name = "crm_score_strategy_shop")
public class ScoreStrategyShop extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 门店编号
	 */
	private String shop;
	/**
	 * 积分系数
	 */
	private BigDecimal pointRatio = new BigDecimal(1);

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public BigDecimal getPointRatio() {
		return pointRatio;
	}

	public void setPointRatio(BigDecimal pointRatio) {
		this.pointRatio = pointRatio;
	}

}
