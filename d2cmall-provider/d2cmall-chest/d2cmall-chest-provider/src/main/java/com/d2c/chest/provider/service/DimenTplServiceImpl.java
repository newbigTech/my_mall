package com.d2c.chest.provider.service;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.d2c.chest.api.entity.DimenTplDO;
import com.d2c.chest.api.service.DimenTplService;
import com.d2c.chest.provider.enums.DimenTplEnum;
import com.d2c.mybatis.service.ListServiceImpl;

/**
 * @author wull
 */
@Service(protocol = "dubbo")
public class DimenTplServiceImpl extends ListServiceImpl<DimenTplDO> implements DimenTplService {

	@Override
	public List<DimenTplDO> findDimenTplByRuleCode(String ruleCode){
		List<DimenTplDO> list = findByFieldName("ruleCode", ruleCode);
		if(list.isEmpty()){
			list = createDefault(ruleCode);
		}
		return list;
	}
	
	/**
	 * 创建默认模板
	 */
	private List<DimenTplDO> createDefault(String ruleCode){
		return saveAll(DimenTplEnum.findDimenTplByCode(ruleCode));
	}
	
}
