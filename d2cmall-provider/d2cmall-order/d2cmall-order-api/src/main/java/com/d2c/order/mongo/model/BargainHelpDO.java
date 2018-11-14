package com.d2c.order.mongo.model;

import java.util.Date;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.d2c.common.mongodb.model.SuperMongoDO;

@Document
public class BargainHelpDO extends SuperMongoDO {

	private static final long serialVersionUID = -6310471283406627019L;

	/**
	 * 砍价用户活动ID
	 */
	@Indexed
	private String bargainId;
	
	/**
	 * unionId
	 */
	@Indexed
	private String helperUnionId;
	/**
	 * 助力会员id
	 */
	@Indexed
	private Long helpMemberId;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 用户头像
	 */
	private String headPic;
	/**
	 * 砍价助力价格
	 */
	private Double subPrice;
	/**
	 * 帮助时间
	 */
	@Indexed
	private Date helpDate;

	public BargainHelpDO() {

	}

	public BargainHelpDO(String bargainId, Long helpMemberId, String userName, Double subPrice, String headPic, String helperUnionId) {
		this.bargainId = bargainId;
		this.helpMemberId = helpMemberId;
		this.userName = userName;
		this.subPrice = subPrice;
		this.headPic = headPic;
		this.helperUnionId = helperUnionId;
		this.helpDate = new Date();
	}

	public String getBargainId() {
		return bargainId;
	}

	public void setBargainId(String bargainId) {
		this.bargainId = bargainId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public Double getSubPrice() {
		return subPrice;
	}

	public void setSubPrice(Double subPrice) {
		this.subPrice = subPrice;
	}

	public Long getHelpMemberId() {
		return helpMemberId;
	}

	public void setHelpMemberId(Long helpMemberId) {
		this.helpMemberId = helpMemberId;
	}

	public String getHelperUnionId() {
		return helperUnionId;
	}

	public void setHelperUnionId(String helperUnionId) {
		this.helperUnionId = helperUnionId;
	}

	public Date getHelpDate() {
		return helpDate;
	}

	public void setHelpDate(Date helpDate) {
		this.helpDate = helpDate;
	}

}
