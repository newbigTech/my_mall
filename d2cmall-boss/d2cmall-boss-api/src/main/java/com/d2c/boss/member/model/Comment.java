package com.d2c.boss.member.model;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * crm实体类 - 评论记录
 */
@Table(name = "crm_comment")
public class Comment extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 会员账号
	 */
	private String loginCode;
	/**
	 * 会员头像
	 */
	private String headPic;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 评论图片
	 */
	private String pic;
	/**
	 * 商品款号
	 */
	private String productSn;
	/**
	 * 颜色尺码
	 */
	private String salesProperty;
	/**
	 * 商品质量评分
	 */
	private int productScore;
	/**
	 * 商品包装评分
	 */
	private int packageScore;
	/**
	 * 配送速度评分
	 */
	private int deliverySpeedScore;
	/**
	 * 物流服务评分
	 */
	private int deliveryServiceScore;
	/**
	 * 设备
	 */
	private String device;
	/**
	 * app版本
	 */
	private String appVersion;

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getProductSn() {
		return productSn;
	}

	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}

	public String getSalesProperty() {
		return salesProperty;
	}

	public void setSalesProperty(String salesProperty) {
		this.salesProperty = salesProperty;
	}

	public int getProductScore() {
		return productScore;
	}

	public void setProductScore(int productScore) {
		this.productScore = productScore;
	}

	public int getPackageScore() {
		return packageScore;
	}

	public void setPackageScore(int packageScore) {
		this.packageScore = packageScore;
	}

	public int getDeliverySpeedScore() {
		return deliverySpeedScore;
	}

	public void setDeliverySpeedScore(int deliverySpeedScore) {
		this.deliverySpeedScore = deliverySpeedScore;
	}

	public int getDeliveryServiceScore() {
		return deliveryServiceScore;
	}

	public void setDeliveryServiceScore(int deliveryServiceScore) {
		this.deliveryServiceScore = deliveryServiceScore;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

}
