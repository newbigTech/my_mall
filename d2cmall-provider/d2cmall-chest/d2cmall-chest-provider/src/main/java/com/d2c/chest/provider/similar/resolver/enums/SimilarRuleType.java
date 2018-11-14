package com.d2c.chest.provider.similar.resolver.enums;

import static com.d2c.chest.provider.similar.resolver.enums.ArithType.NORMAL_ARITH;
import static com.d2c.chest.provider.similar.resolver.enums.ArithType.NORMAL_RATE_ARITH;
import static com.d2c.chest.provider.similar.resolver.enums.ArithType.SIMPLE_LINE_ARITH;
import static com.d2c.chest.provider.similar.resolver.enums.DistanceType.BASIC_DIST;
import static com.d2c.chest.provider.similar.resolver.enums.DistanceType.CATEGORY_DEFINE_DIST;
import static com.d2c.chest.provider.similar.resolver.enums.DistanceType.EUCLIDEAN_DIST;
import static com.d2c.chest.provider.similar.resolver.enums.DistanceType.SIMPLE_EQUALS_DIST;

import com.d2c.chest.api.entity.SimilarRuleDO;
import com.d2c.chest.provider.similar.resolver.SimilarResolver;

public enum SimilarRuleType {

	STRING_EQUALS_TYPE(SIMPLE_EQUALS_DIST, SIMPLE_LINE_ARITH, "简单数据相同相似"),
	NORMAL_RATE_TYPE(BASIC_DIST, NORMAL_RATE_ARITH, "数值比率正态分布"),
	NORMAL_TYPE(BASIC_DIST, NORMAL_ARITH, "数值正态分布"),
	DATE_NORMAL_TYPE(BASIC_DIST, NORMAL_ARITH, "日期正态分布"),
	EUCLIDEAM_DIMEN_TYPE(EUCLIDEAN_DIST, NORMAL_ARITH, "欧式距离相似"),

	CATEGORY_DEFINE_TYPE(CATEGORY_DEFINE_DIST, SIMPLE_LINE_ARITH, "自定义品类分级");

	DistanceType distanceType;
	ArithType arithType;
	String remark;
	
	SimilarRuleType(DistanceType distanceType, ArithType arithType, String remark) {
		this.distanceType = distanceType;
		this.arithType = arithType;
		this.remark = remark;
	}

	/**
	 * 是否多维离散模型
	 */
	public boolean isDimens(){
		return this.distanceType.isDimen;
	}
	
	/**
	 * 根据类型创建处理解析器
	 */
	public static SimilarResolver createResolver(SimilarRuleDO rule) {
		SimilarRuleType st = valueOf(rule.getRuleType());
		SimilarResolver resolver = new SimilarResolver();
		resolver.setArith(st.arithType.create(rule));
		resolver.setDistance(st.distanceType.create(rule));
		return resolver;
	}
	
	@Override
	public String toString() {
		return this.remark;
	}
	
}
