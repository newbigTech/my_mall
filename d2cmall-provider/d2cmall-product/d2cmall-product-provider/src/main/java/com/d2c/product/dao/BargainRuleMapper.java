package com.d2c.product.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.product.model.BargainRule;

public interface BargainRuleMapper extends SuperMapper<BargainRule> {

	List<BargainRule> findByPromotionId(@Param("promotionId") Long promotionId);

	BargainRule findUpperRule(@Param("promotionId") Long promotionId, @Param("price") BigDecimal price);

	int countByPromotion(@Param("promotionId") Long promotionId);

	BargainRule findLast(@Param("promotionId") Long promotionId);

}
