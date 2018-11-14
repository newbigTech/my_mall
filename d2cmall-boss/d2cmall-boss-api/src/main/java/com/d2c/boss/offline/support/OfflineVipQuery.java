package com.d2c.boss.offline.support;

import java.util.Date;

import com.d2c.common.api.query.model.BaseQuery;

public class OfflineVipQuery extends BaseQuery {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @DSParam(field = "cardNo", description = "会员卡号", op = Operator.LIKE)
	private String cardNo;

	// @DSParam(field = "vipType.name", description = "会员卡名称", op =
	// Operator.LIKE)
	private String vipTypeName;

	// @DSParam(field = "vipType.type", description = "会员卡类型")
	private String type;

	// @DSParam(field = "bindVip.name", description = "持卡人姓名", op =
	// Operator.LIKE)
	private String name;

	// @DSParam(field = "bindVip.mobile", description = "持卡人手机号", op =
	// Operator.LIKE)
	private String mobile;

	// @DSParam(field = "bindVip.email", description = "持卡人邮箱", op =
	// Operator.LIKE)
	private String email;
	// @DSParam(field = "bindVip.sex", description = "性别")
	private String sex;

	// @DSParam(field = "bindVip.birthday", description = "持卡人生日")
	private Date birthday;

	public String getVipTypeName() {
		return vipTypeName;
	}

	public void setVipTypeName(String vipTypeName) {
		this.setType("OFFLINE");
		this.vipTypeName = vipTypeName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
