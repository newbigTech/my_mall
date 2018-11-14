package com.d2c.chest.provider.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.d2c.chest.api.entity.DimenValueDO;
import com.d2c.chest.api.service.DimenValueService;
import com.d2c.chest.provider.dao.DimenValueMapper;
import com.d2c.mybatis.service.ListServiceImpl;

/**
 * @author wull
 */
@Service(protocol = "dubbo")
public class DimenValueServiceImpl extends ListServiceImpl<DimenValueDO> implements DimenValueService {

	@Autowired
	private DimenValueMapper mapper;
	
	public List<DimenValueDO> update(List<DimenValueDO> values){
		for(DimenValueDO v : values){
			updateNotNull(v);
		}
		return values;
	}

	public List<DimenValueDO> findDimenValueByKeyId(Integer keyId){
		return findByFieldName("keyId", keyId);
	}

	public List<Double> getDistByValue(Integer ruleId, String fieldValue){
		return mapper.getDistByValue(ruleId, fieldValue);
	}

	public DimenValueDO createDimenValue(Integer keyId, Integer tplId, Double dist){
		return save(new DimenValueDO(keyId, tplId, dist));
	}

}
