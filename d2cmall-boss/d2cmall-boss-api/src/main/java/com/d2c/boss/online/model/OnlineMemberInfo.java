package com.d2c.boss.online.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * crm实体类 - 主账号
 */
@Table(name = "online_memberinfo")
public class OnlineMemberInfo extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 会员账号（唯一值）
	 */
	private String loginCode;
	/**
	 * 会员类型：0、普通会员，1、分销商，2、经销商
	 */
	private int type;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 真实姓名
	 */
	private String name;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 区县
	 */
	private String district;
	/**
	 * 街道
	 */
	private String street;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 微信号
	 */
	private String weixin;
	/**
	 * QQ号
	 */
	private String qq;
	/**
	 * email
	 */
	private String email;
	/**
	 * 会员头像
	 */
	private String headPic;
	/**
	 * 身高
	 */
	private BigDecimal height;
	/**
	 * 体重
	 */
	private BigDecimal weight;
	/**
	 * 胸围
	 */
	private BigDecimal chest;
	/**
	 * 腰围
	 */
	private BigDecimal waistline;
	/**
	 * 臀围
	 */
	private BigDecimal hipline;
	/**
	 * 脚长
	 */
	private BigDecimal footLength;
	/**
	 * 注册来源 1、官网，2、ios，3、android， 4、wap，5、线下门店
	 */
	private String source;
	/**
	 * 注册来源门店
	 */
	private String shopCode;
	/**
	 * 推荐人
	 */
	private String referee;

	private int upStatus;

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getChest() {
		return chest;
	}

	public void setChest(BigDecimal chest) {
		this.chest = chest;
	}

	public BigDecimal getWaistline() {
		return waistline;
	}

	public void setWaistline(BigDecimal waistline) {
		this.waistline = waistline;
	}

	public BigDecimal getHipline() {
		return hipline;
	}

	public void setHipline(BigDecimal hipline) {
		this.hipline = hipline;
	}

	public BigDecimal getFootLength() {
		return footLength;
	}

	public void setFootLength(BigDecimal footLength) {
		this.footLength = footLength;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getReferee() {
		return referee;
	}

	public void setReferee(String referee) {
		this.referee = referee;
	}

	public int getUpStatus() {
		return upStatus;
	}

	public void setUpStatus(int upStatus) {
		this.upStatus = upStatus;
	}

}
