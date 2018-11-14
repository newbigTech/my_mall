package com.d2c.chest.provider.service;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.d2c.chest.api.entity.RecomRuleDO;
import com.d2c.chest.api.service.RecomRuleService;
import com.d2c.chest.provider.enums.RecomRuleEnum;
import com.d2c.mybatis.service.ListServiceImpl;

/**
 * 商品推荐表
 * @author wull
 */
@Service(protocol = "dubbo" )
public class RecomRuleServiceImpl extends ListServiceImpl<RecomRuleDO> implements RecomRuleService {
	

	/**
	 * 重建规则
	 */
	public List<RecomRuleDO> findAllRules(){
		List<RecomRuleDO> list = findAll();
		if(list.isEmpty()){
			list = createDefaultRules();
		}
		return list;
	}

	/**
	 * 重建规则
	 */
	public List<RecomRuleDO> rebuildRules(){
		deleteAll();
		return createDefaultRules();
	}
	
	/**
	 * 创建默认规则
	 */
	private List<RecomRuleDO> createDefaultRules(){
		return saveAll(RecomRuleEnum.getAllRules());
	}
	

}
