package com.d2c.order.dto;

import java.math.BigDecimal;
import java.util.List;

import com.d2c.order.model.O2oSubscribe;
import com.d2c.order.model.O2oSubscribeItem;

public class O2oSubscribeDto extends O2oSubscribe {

	private static final long serialVersionUID = 1L;

	/**
	 * 预约单明细
	 */
	private List<O2oSubscribeItem> items;

	public List<O2oSubscribeItem> getItems() {
		return items;
	}

	public void setItems(List<O2oSubscribeItem> items) {
		int totalQuantity = 0;
		BigDecimal totalAmount = new BigDecimal(0);
		for (O2oSubscribeItem item : items) {
			if (item.getQuantity() == null) {
				item.setQuantity(1);
			}
			totalQuantity += item.getQuantity();
			totalAmount = totalAmount.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
		}
		this.setTotalQuantity(totalQuantity);
		this.setTotalAmount(totalAmount);
		this.items = items;
	}

}
