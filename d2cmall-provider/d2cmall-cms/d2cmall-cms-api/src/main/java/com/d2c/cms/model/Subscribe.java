package com.d2c.cms.model;

import javax.persistence.Table;

import com.d2c.common.api.annotation.AssertColumn;
import com.d2c.common.api.model.PreUserDO;

/**
 * 实体类 -订阅
 * 
 */
@Table(name = "v_subscribe")
public class Subscribe extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private Long memberId;
	/**
	 * 用户登录号
	 */
	private String memberCode;
	/**
	 * 用户名
	 */
	private String memberName;
	/**
	 * 订阅来源(手机或邮箱)
	 */
	@AssertColumn("订阅方式不能为空")
	private String subscribe;
	/**
	 * 枚举类型
	 */
	private String subType;

	public static enum SubType {
		EMAIL, MOBILE
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

}
