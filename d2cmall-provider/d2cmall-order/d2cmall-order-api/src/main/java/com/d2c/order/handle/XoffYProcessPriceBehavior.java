package com.d2c.order.handle;

import java.math.BigDecimal;
import java.util.Set;

import com.d2c.product.model.Promotion;

public interface XoffYProcessPriceBehavior {

	BigDecimal process(Promotion promotion, Set<PromotionCalculateItem> set);

	BigDecimal splitAmount(BigDecimal currenPromotion, BigDecimal totalProductAmount, Promotion promotion,
			Set<PromotionCalculateItem> set, PromotionCalculateResult result);

	String getName();

}
