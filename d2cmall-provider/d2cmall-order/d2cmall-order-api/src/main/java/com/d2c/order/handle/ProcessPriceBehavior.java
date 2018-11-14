package com.d2c.order.handle;

import java.math.BigDecimal;

import com.d2c.product.model.Promotion;

public interface ProcessPriceBehavior {

	BigDecimal process(Promotion promotion, PromotionCalculateItem calItem);

	String getName();

}
