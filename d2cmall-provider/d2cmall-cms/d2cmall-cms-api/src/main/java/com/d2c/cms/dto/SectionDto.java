package com.d2c.cms.dto;

import java.util.List;

import com.d2c.cms.model.Section;
import com.d2c.cms.model.SectionValue;

public class SectionDto extends Section {

	private static final long serialVersionUID = 1L;

	/**
	 * 版块内容
	 */
	private List<SectionValue> sectionValues;

	public List<SectionValue> getSectionValues() {
		return sectionValues;
	}

	public void setSectionValues(List<SectionValue> sectionValues) {
		this.sectionValues = sectionValues;
	}

}
