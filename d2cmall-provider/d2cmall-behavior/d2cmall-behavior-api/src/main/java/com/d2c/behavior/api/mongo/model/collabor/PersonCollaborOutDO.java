package com.d2c.behavior.api.mongo.model.collabor;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.d2c.common.mongodb.model.SuperMongoDO;

/**
 * 用户协同过滤推荐商品表
 * 
 * @author wull
 */
@Document
public class PersonCollaborOutDO extends SuperMongoDO {

	private static final long serialVersionUID = 1790646873633368072L;

	/**
	 * 会员ID
	 */
	@Id
	private Long memberId;

	/**
	 * 商品推荐数据
	 */
	private List<ProductRatingDO> ratings;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public List<ProductRatingDO> getRatings() {
		return ratings;
	}

	public void setRatings(List<ProductRatingDO> ratings) {
		this.ratings = ratings;
	}

}
