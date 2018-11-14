package com.d2c.chest.provider.service;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.d2c.chest.api.entity.DimenKeyDO;
import com.d2c.chest.api.service.DimenKeyService;
import com.d2c.mybatis.service.ListServiceImpl;

/**
 * @author wull
 */
@Service(protocol = "dubbo")
public class DimenKeyServiceImpl extends ListServiceImpl<DimenKeyDO> implements DimenKeyService {
	
	public List<DimenKeyDO> findDimenKeys(Integer ruleId){
		return this.findByFieldName("ruleId", ruleId);
	}
	
	public DimenKeyDO createDimenKey(Integer ruleId, String fieldName, String fieldValue){
		return save(new DimenKeyDO(ruleId, fieldName, fieldValue));
	}
	
}
