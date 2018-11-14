package com.d2c.chest.provider.similar.resolver.enums;

import com.d2c.chest.api.entity.SimilarRuleDO;
import com.d2c.chest.provider.similar.resolver.dist.AbstractDistance;
import com.d2c.chest.provider.similar.resolver.dist.BasicDistance;
import com.d2c.chest.provider.similar.resolver.dist.CategoryDefineDist;
import com.d2c.chest.provider.similar.resolver.dist.EuclideanDistance;
import com.d2c.chest.provider.similar.resolver.dist.SimpleEqualsDistance;
import com.d2c.common.base.utils.BeanUt;

public enum DistanceType {

	SIMPLE_EQUALS_DIST(SimpleEqualsDistance.class, false, "值相等"),
	BASIC_DIST(BasicDistance.class, false, "直线距离"),
	EUCLIDEAN_DIST(EuclideanDistance.class, false, "欧式距离"),
	
	CATEGORY_DEFINE_DIST(CategoryDefineDist.class, false, "自定义品类分级距离");
	
	Class<? extends AbstractDistance> clz;
	boolean isDimen = false;
	String remark;
	
	DistanceType(Class<? extends AbstractDistance> clz, boolean isDimen, String remark) {
		this.clz = clz;
		this.isDimen = isDimen;
		this.remark = remark;
	}
	
	public AbstractDistance create(SimilarRuleDO rule) {
		AbstractDistance bean = BeanUt.newInstance(this.clz);
		bean.init(rule);
		return bean;
	}
	
	@Override
	public String toString() {
		return this.remark;
	}
	
}
