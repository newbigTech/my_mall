package com.d2c.chest.provider.enums;

import java.util.ArrayList;
import java.util.List;

import com.d2c.chest.api.entity.DimenTplDO;

public enum DimenTplEnum {

//	CATEGORY_THICKNESS("薄厚程度", SimilarRuleEnum.PRODUCT_CATEGORY, "薄", "厚", "品类-薄厚程度"),
//	CATEGORY_OUTERWEAR("内穿外套", SimilarRuleEnum.PRODUCT_CATEGORY, "内穿", "外套",  "品类-内穿外套"),
//	CATEGORY_FORMAL("休闲正式", SimilarRuleEnum.PRODUCT_CATEGORY, "休闲", "正式",  "品类-休闲正式程度");
	;

	String dimenName;
	SimilarRuleEnum rule;
	String lowName;
	String highName;
	double defValue = 0;
	double lowValue = -10;
	double highValue = 10;
	String remark;

	DimenTplEnum(String dimenName, SimilarRuleEnum rule, String lowName, String highName, String remark) {
		this.dimenName = dimenName;
		this.rule = rule;
		this.lowName = lowName;
		this.highName = highName;
		this.remark = remark;
	}

	public DimenTplDO getDimenTpl() {
		DimenTplDO bean = new DimenTplDO();
		bean.setDimenName(dimenName);
		bean.setRuleCode(rule.name());
		bean.setLowName(lowName);
		bean.setHighName(highName);
		bean.setDefValue(defValue);
		bean.setLowValue(lowValue);
		bean.setHighValue(highValue);
		bean.setRemark(remark);
		return bean;
	}

	public static List<DimenTplDO> findDimenTplByCode(String ruleCode) {
		List<DimenTplDO> list = new ArrayList<>();
		for (DimenTplEnum e : values()) {
			if(e.rule.name().equals(ruleCode)){
				list.add(e.getDimenTpl());
			}
		}
		return list;
	}

	@Override
	public String toString() {
		return this.dimenName;
	}

}
