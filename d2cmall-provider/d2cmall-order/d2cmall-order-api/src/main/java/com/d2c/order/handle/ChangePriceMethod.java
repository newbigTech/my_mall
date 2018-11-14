package com.d2c.order.handle;
//package com.d2c.promotion.handle;
//
//import java.math.BigDecimal;
//import java.util.Set;
//
//import com.d2c.promotion.model.IPromotionCondition;
//import com.d2c.promotion.model.Promotion;
//
///**
// * 换购价格
// * 
// * @author xx
// */
//public class ChangePriceMethod implements XoffYProcessPriceBehavior {
//	public BigDecimal process(Promotion promotion,
//			Set<IPromotionCondition> set, IPromotionCondition orderItem) {
//		if (promotion.isOver()) {
//			return new BigDecimal(0);
//		}
//
//		BigDecimal total = new BigDecimal(0);
//		for (IPromotionCondition item : set) {
//			// 预售商品
//			if (!promotion.isCrowd() && item.getCrowdItemId() != null) {
//				return new BigDecimal(0);
//			}
//			total = total.add(item.getTotalPrice().subtract(
//					item.getPromotionAmount()));
//		}
//		String[] solutions = promotion.getSolution().split("\\|");
//		String Y = String.valueOf(orderItem.getProductPrice().intValue());
//		for (String solution : solutions) {
//			String[] X = solution.split("-");
//			if (total.compareTo(new BigDecimal(X[0])) >= 0
//					&& new BigDecimal(X[1]).compareTo(new BigDecimal(0)) > 0)
//				Y = X[1];
//		}
//		// 换购价
//		BigDecimal off = new BigDecimal(Y);
//		return orderItem.getProductPrice().subtract(off);
//	}
//
//	@Override
//	public String getName() {
//		return "换购优惠";
//	}
//
//	@Override
//	public BigDecimal process(Promotion promotion, Set<IPromotionCondition> set) {
//		return new BigDecimal(0);
//	}
//}
