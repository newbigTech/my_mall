package com.d2c.boss.sys.model;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * 行政区划
 */
@Table(name = "sys_area")
public class Area extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 国家
	 */
	public String country;
	/**
	 * 行政级别
	 */
	private String level;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 区，县
	 */
	private String district;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	protected String getDisplayName() {
		return "区域信息";
	}

}
