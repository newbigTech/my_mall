package com.d2c.order.handle;

import java.math.BigDecimal;

import com.d2c.product.model.Promotion;

/**
 * REDUCE_PRICE(1, "直减", 0)
 *
 */
public class ReducePriceMethod implements ProcessPriceBehavior {

	@Override
	public BigDecimal process(Promotion promotion, PromotionCalculateItem calItem) {
		if (promotion.isOver()) {
			return new BigDecimal(0);
		}
		BigDecimal off = new BigDecimal(promotion.getSolution());
		if (calItem.getProductPrice().compareTo(off) < 0) {
			return new BigDecimal(0);
		}
		calItem.setPromotionPrice(off);
		calItem.setPromotionAmount(off.multiply(new BigDecimal(calItem.getQuantity())));
		calItem.setPromotionName(promotion.getName());
		return off;
	}

	@Override
	public String getName() {
		return "直减";
	}

}
