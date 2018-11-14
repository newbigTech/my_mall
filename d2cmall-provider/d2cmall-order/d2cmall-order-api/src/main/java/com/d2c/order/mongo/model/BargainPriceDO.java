package com.d2c.order.mongo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.d2c.common.mongodb.model.BaseMongoDO;
import com.d2c.member.model.MemberInfo;
import com.d2c.order.mongo.enums.BargainStatus;
import com.d2c.product.model.BargainPromotion;

@Document
public class BargainPriceDO extends BaseMongoDO {

	private static final long serialVersionUID = -4903284310787030896L;

	/**
	 * 砍价活动ID
	 */
	@Indexed
	private Long bargainId;
	/**
	 * 会员ID
	 */
	@Indexed
	private Long memberId;
	/**
	 * 微信
	 */
	@Indexed
	private String unionId;
	/**
	 * 砍价状态 BargainStatus(只分是否支付成功)
	 */
	@Indexed
	private String status;
	/**
	 * 砍价实时价格
	 */
	private Double price;
	/**
	 * 初始价
	 */
	private Double originalPrice;
	/**
	 * 砍价活动(//TODO 排序和参团人数是不正确的)
	 */
	private BargainPromotion bargain;
	/**
	 * 砍价助力列表
	 */
	private List<BargainHelpDO> helpers = new ArrayList<>();
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 账号
	 */
	private String loginCode;
	/**
	 * 创建时间
	 */
	@Indexed
	private Date createDate;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 头像
	 */
	private String pic;

	public BargainPriceDO() {
		this.status = BargainStatus.BEGIN.name();
	}

	public BargainPriceDO(MemberInfo memberInfo, BigDecimal originalPrice, BargainPromotion bargain,
			String productName) {
		this();
		this.memberId = memberInfo.getId();
		this.originalPrice = originalPrice.doubleValue();
		this.productName = productName;
		this.bargain = bargain;
		this.price = originalPrice.doubleValue();
		this.bargainId = bargain.getId();
		this.loginCode = memberInfo.getLoginCode();
		this.nickname = memberInfo.getDisplayName();
		this.pic = memberInfo.getHeadPic();
	}

	public void addHelper(BargainHelpDO bean) {
		helpers.add(bean);
	}

	// ************** private ****************

	public Long getBargainId() {
		return bargainId;
	}

	public void setBargainId(Long bargainId) {
		this.bargainId = bargainId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BargainPromotion getBargain() {
		return bargain;
	}

	public void setBargain(BargainPromotion bargain) {
		this.bargain = bargain;
	}

	public List<BargainHelpDO> getHelpers() {
		return helpers;
	}

	public void setHelpers(List<BargainHelpDO> helpers) {
		this.helpers = helpers;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getStatusName() {
		if (BargainStatus.BUYED.name().equals(this.getStatus())) {
			return "购买成功";
		} else if (BargainStatus.ORDERED.name().equals(this.getStatus())) {
			return "等待支付";
		} else if (bargain.getEndDate().getTime() <= new Date().getTime() - 24 * 60 * 60 * 100) {
			return "支付商品超时";
		} else if (bargain.isOver()) {
			return "砍价结束";
		} else if (this.getPrice() == bargain.getMinPrice().doubleValue()) {
			return "砍价成功";
		} else {
			return "砍价中";
		}
	}

}
