package com.d2c.order.handle;

import java.math.BigDecimal;

import com.d2c.order.model.base.IPromotionInterface;
import com.d2c.product.model.Product;
import com.d2c.product.model.Promotion;

/**
 * APRICE(4, "一口价", 0)
 *
 */
public class ApriceMethod implements ProcessPriceBehavior {

	public BigDecimal process(Promotion promotion, IPromotionInterface pc, Product product) {
		if (promotion.isOver()) {
			return new BigDecimal(0);
		}
		BigDecimal productPrice = pc.getProductPrice();
		BigDecimal aPrice = product.getaPrice();
		return productPrice.subtract(aPrice);
	}

	@Override
	public String getName() {
		return "一口价";
	}

	@Override
	public BigDecimal process(Promotion promotion, PromotionCalculateItem calItem) {
		if (promotion.isOver()) {
			return new BigDecimal(0);
		}
		if (calItem.getaPrice() == null || calItem.getaPrice().compareTo(new BigDecimal(0)) <= 0
				|| calItem.getProductPrice().compareTo(calItem.getaPrice()) < 0) {
			return new BigDecimal(0);
		}
		BigDecimal off = calItem.getProductPrice().subtract(calItem.getaPrice());
		calItem.setPromotionPrice(off);
		calItem.setPromotionAmount(off.multiply(new BigDecimal(calItem.getQuantity())));
		calItem.setPromotionName(promotion.getName());
		return off;
	}

}
