package com.d2c.behavior.provider.property;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.d2c.behavior.api.mongo.dto.load.TagTypeDTO;
import com.d2c.common.core.propery.BaseJsonProperties;
import com.fasterxml.jackson.core.type.TypeReference;

@Component
public class JsonFile extends BaseJsonProperties {

	@Value("classpath:json/tag_type.json") 
	private Resource tagType;
	
	public List<TagTypeDTO> getTagTypeList(){
		return toJson(tagType, new TypeReference<List<TagTypeDTO>>(){});
	}

}
