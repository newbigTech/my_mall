package com.d2c.order.query;

import java.math.BigDecimal;
import java.util.Date;

import com.d2c.common.api.query.model.BaseQuery;

public class CashCardSearcher extends BaseQuery {

	private static final long serialVersionUID = 1L;

	private Long cashCardDefId;

	private String code;

	private Long cashCardId;

	private String name;

	private String loginCode;

	private Integer status;

	private Date beginClaimDate;

	private Date endClaimDate;

	private BigDecimal amount;

	public Long getCashCardDefId() {
		return cashCardDefId;
	}

	public void setCashCardDefId(Long cashCardDefId) {
		this.cashCardDefId = cashCardDefId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getCashCardId() {
		return cashCardId;
	}

	public void setCashCardId(Long cashCardId) {
		this.cashCardId = cashCardId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getBeginClaimDate() {
		return beginClaimDate;
	}

	public void setBeginClaimDate(Date beginClaimDate) {
		this.beginClaimDate = beginClaimDate;
	}

	public Date getEndClaimDate() {
		return endClaimDate;
	}

	public void setEndClaimDate(Date endClaimDate) {
		this.endClaimDate = endClaimDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
