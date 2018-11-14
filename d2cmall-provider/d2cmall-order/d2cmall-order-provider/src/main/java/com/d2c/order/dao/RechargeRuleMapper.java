package com.d2c.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.d2c.common.api.page.PageModel;
import com.d2c.mybatis.mapper.SuperMapper;
import com.d2c.order.model.RechargeRule;
import com.d2c.order.query.RechargeRuleSearcher;

public interface RechargeRuleMapper extends SuperMapper<RechargeRule> {

	RechargeRule findValidRule();

	List<RechargeRule> findBySearch(@Param("searcher") RechargeRuleSearcher searcher, @Param("pager") PageModel pager);

	int countBySearch(@Param("searcher") RechargeRuleSearcher searcher);

	int doMark(@Param("ruleId") Long ruleId, @Param("man") String man, @Param("status") Integer status);

	int doOverRule();

}
