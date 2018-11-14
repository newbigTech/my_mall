package com.d2c.boss.member.model;

import java.math.BigDecimal;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * 商品积分活动 TODO
 */
@Table(name = "crm_score_strategy_pro")
public class ScoreStrategyProduct extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 商品款号
	 */
	private String productSn;
	/**
	 * 积分系数
	 */
	private BigDecimal pointRatio = new BigDecimal(1);

	public String getProductSn() {
		return productSn;
	}

	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}

	public BigDecimal getPointRatio() {
		return pointRatio;
	}

	public void setPointRatio(BigDecimal pointRatio) {
		this.pointRatio = pointRatio;
	}

}
