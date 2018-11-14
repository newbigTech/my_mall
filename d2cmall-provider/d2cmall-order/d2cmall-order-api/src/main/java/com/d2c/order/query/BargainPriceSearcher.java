package com.d2c.order.query;

import com.d2c.common.api.query.model.BaseQuery;

/**
 * 发起砍价的列表
 * 
 * @author Administrator
 *
 */
public class BargainPriceSearcher extends BaseQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商品 名称
	 */
	private String productName;
	/**
	 * 会员id
	 */
	private Long memberId;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 账号
	 */
	private String loginCode;
	/**
	 * 活动id
	 */
	private Long bargainId;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public Long getBargainId() {
		return bargainId;
	}

	public void setBargainId(Long bargainId) {
		this.bargainId = bargainId;
	}

}
