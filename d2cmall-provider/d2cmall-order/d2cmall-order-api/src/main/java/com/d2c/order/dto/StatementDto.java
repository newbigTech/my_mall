package com.d2c.order.dto;

import java.util.List;

import com.d2c.order.model.Statement;
import com.d2c.order.model.StatementItem;

public class StatementDto extends Statement {

	private static final long serialVersionUID = 1L;

	/**
	 * 对账单明细
	 */
	private List<StatementItem> statementItems;

	private String operation;

	public List<StatementItem> getStatementItems() {
		return statementItems;
	}

	public void setStatementItems(List<StatementItem> statementItems) {
		this.statementItems = statementItems;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
