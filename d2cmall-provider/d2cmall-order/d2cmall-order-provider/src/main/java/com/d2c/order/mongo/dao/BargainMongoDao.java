package com.d2c.order.mongo.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.d2c.common.api.page.PageModel;
import com.d2c.common.mongodb.base.ListMongoDao;
import com.d2c.order.mongo.enums.BargainStatus;
import com.d2c.order.mongo.model.BargainPriceDO;
import com.d2c.order.query.BargainPriceSearcher;
import com.d2c.product.model.BargainPromotion;
import com.d2c.util.string.StringUtil;
import com.mongodb.client.result.UpdateResult;

@Component
public class BargainMongoDao extends ListMongoDao<BargainPriceDO> {

	/**
	 * 查找我的砍价列表
	 * 
	 * @param memberId
	 * @param page
	 * @param limit
	 * @return
	 */
	public List<BargainPriceDO> findMyBargainList(Long memberId, Integer page, Integer limit) {
		return this.findPage(new Query(Criteria.where("memberId").is(memberId)), page, limit, "createDate", false);
	}

	/**
	 * 查找我的砍价明细
	 * 
	 * @param memberId
	 * @param bargainId
	 * @return
	 */
	public BargainPriceDO getBargainPrice(Long memberId, Long bargainId) {
		return this.findOne(new Query(Criteria.where("memberId").is(memberId).and("bargainId").is(bargainId)));
	}

	/**
	 * 更新状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	public BargainPriceDO updateStatus(String id, BargainStatus status) {
		return this.updateOne(new Query(Criteria.where("_id").is(id)), Update.update("status", status.name()));
	}

	/**
	 * 更新价格
	 * 
	 * @param id
	 * @param price
	 * @return
	 */
	public BargainPriceDO updatePrice(String id, BigDecimal price) {
		return this.updateOne(new Query(Criteria.where("_id").is(id)), Update.update("price", price));
	}

	/**
	 * 更新活动
	 * 
	 * @param bargainPromotion
	 * @return
	 */
	public int updateBargainPromotion(BargainPromotion bargainPromotion) {
		UpdateResult result = this.update(
				new Query(Criteria.where("bargainId").is(bargainPromotion.getId())
						.andOperator(Criteria.where("status")
								.nin(Arrays.asList(
										new String[] { BargainStatus.BUYED.name(), BargainStatus.ORDERED.name() })))),
				Update.update("bargain", bargainPromotion));
		if (result != null && result.getModifiedCount() > 0) {
			return 1;
		}
		return 0;
	}

	/**
	 * 统计我的砍价活动数量
	 * 
	 * @param memberId
	 * @return
	 */
	public long countMine(Long memberId) {
		return this.count(new Query(Criteria.where("memberId").is(memberId)));

	}

	/**
	 * 查找该帮助人帮助的所有帮助列表
	 * 
	 * @param helpMemberId
	 * @return
	 */
	public List<BargainPriceDO> findByHelper(Long helpMemberId) {
		return this.find(new Query(Criteria.where("helpers.helpMemberId").is(helpMemberId)));
	}

	/**
	 * 根据id和帮助人id查询
	 * 
	 * @param id
	 * @param helperId
	 * @return
	 */
	public BargainPriceDO findByIdAndUnionId(String id, String unionId) {
		return this.findOne(new Query(Criteria.where("_id").is(id).and("helpers.helperUnionId").is(unionId)));
	}

	/**
	 * 后台分页查找
	 * 
	 * @param searcher
	 * @param page
	 * @return
	 */
	public List<BargainPriceDO> findBySearcher(BargainPriceSearcher searcher, PageModel page) {
		return this.findPage(buildQuery(searcher), page.getPageNumber(), page.getPageSize(), "createDate", false);
	}

	public long countBySearcher(BargainPriceSearcher searcher) {
		return this.count(buildQuery(searcher));
	}

	private Query buildQuery(BargainPriceSearcher searcher) {
		Query query = new Query();
		if (searcher != null) {
			Criteria criteria = new Criteria();
			// 加查询条件
			if (searcher.getMemberId() != null) {
				criteria.and("memberId").is(searcher.getMemberId());
			}
			if (StringUtil.isNotBlank(searcher.getProductName())) {
				criteria.and("productName").is(searcher.getProductName());
			}
			if (StringUtil.isNotBlank(searcher.getLoginCode())) {
				criteria.and("loginCode").is(searcher.getLoginCode());
			}
			if (StringUtil.isNotBlank(searcher.getStatus())) {
				criteria.and("status").is(searcher.getStatus());
			}
			if (searcher.getBargainId() != null) {
				criteria.and("bargainId").is(searcher.getBargainId());
			}
			query.addCriteria(criteria);
		}
		return query;
	}

	/**
	 * 设置为帮助中
	 * 
	 * @param bean
	 * @return
	 */
	public BargainPriceDO updateBargainProccess(String id) {
		return this.updateOne(new Query(Criteria.where("_id").is(id).and("status").is(BargainStatus.BEGIN.name())),
				Update.update("status", BargainStatus.PROCESS.name()));
	}

	/**
	 * 设置为初始
	 * 
	 * @param id
	 * @return
	 */
	public BargainPriceDO updateBargainBegin(String id) {
		return this.updateOne(new Query(Criteria.where("_id").is(id).and("status").is(BargainStatus.PROCESS.name())),
				Update.update("status", BargainStatus.BEGIN.name()));
	}

	/**
	 * 设置为购买成功
	 * 
	 * @param id
	 * @return
	 */
	public BargainPriceDO updateBargainSuccess(String id) {
		return this.updateOne(new Query(Criteria.where("_id").is(id).and("status").is(BargainStatus.ORDERED.name())),
				Update.update("status", BargainStatus.BUYED.name()));
	}

	/**
	 * 更新帮助者
	 * 
	 * @param bargainPrice
	 * @return
	 */
	public long updateHelper(BargainPriceDO bargainPrice) {
		Update update = new Update();
		update.set("price", bargainPrice.getPrice());
		update.set("helpers", bargainPrice.getHelpers());
		return this.update(new Query(Criteria.where("_id").is(bargainPrice.getId())), update).getModifiedCount();
	}

	/**
	 * 根据unionId查询
	 * 
	 * @param unionId
	 * @return
	 */
	public BargainPriceDO findByUnionId(String unionId) {
		return this.findOne(new Query(Criteria.where("helpers.helperUnionId").is(unionId)));
	}

	/**
	 * 查询特定时间内砍价
	 * 
	 * @param promotionId
	 * @param date
	 * @param page
	 * @return
	 */
	public List<BargainPriceDO> findLatest(Long promotionId, Date date, PageModel page) {
		Criteria criteria = Criteria.where("bargainId").is(promotionId);
		if (date != null) {
			criteria.and("gmtModified").gte(date);
		}
		return this.findPage(new Query(criteria), page.getPageNumber(), page.getPageSize(), "gmtModified", false);
	}

}
