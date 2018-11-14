package com.d2c.boss.product.model;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * crm实体类 - 门店
 */
@Table(name = "crm_shop")
public class Shop extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 门店编号
	 */
	private String code;
	/**
	 * 门店名称
	 */
	private String name;
	/**
	 * 门店地址
	 */
	private String address;
	/**
	 * 营业开始时间
	 */
	private Integer startHour;
	private Integer startMinute;
	/**
	 * 营业结束时间
	 */
	private Integer endHour;
	private Integer endMinute;
	/**
	 * 门店服务
	 */
	private String storeService;
	/**
	 * 电话
	 */
	private String tel;
	/**
	 * 联系人
	 */
	private String linker;

	/**
	 * 百度地图坐标
	 */
	private String bdxy;
	/**
	 * 门店介绍
	 */
	private String description;

	/**
	 * 省
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 区
	 */
	private String district;
	/**
	 * 是否启用
	 */
	private String status;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStartHour() {
		return startHour;
	}

	public void setStartHour(Integer startHour) {
		this.startHour = startHour;
	}

	public Integer getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(Integer startMinute) {
		this.startMinute = startMinute;
	}

	public Integer getEndHour() {
		return endHour;
	}

	public void setEndHour(Integer endHour) {
		this.endHour = endHour;
	}

	public Integer getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(Integer endMinute) {
		this.endMinute = endMinute;
	}

	public String getStoreService() {
		return storeService;
	}

	public void setStoreService(String storeService) {
		this.storeService = storeService;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getLinker() {
		return linker;
	}

	public void setLinker(String linker) {
		this.linker = linker;
	}

	public String getBdxy() {
		return bdxy;
	}

	public void setBdxy(String bdxy) {
		this.bdxy = bdxy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
