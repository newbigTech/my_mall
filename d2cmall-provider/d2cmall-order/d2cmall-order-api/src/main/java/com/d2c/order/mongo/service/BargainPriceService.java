package com.d2c.order.mongo.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.member.model.MemberInfo;
import com.d2c.order.mongo.enums.BargainStatus;
import com.d2c.order.mongo.model.BargainPriceDO;
import com.d2c.order.query.BargainPriceSearcher;
import com.d2c.product.model.BargainPromotion;

public interface BargainPriceService {

	/**
	 * 砍价明细列表
	 * 
	 * @param page
	 * @return
	 */
	List<BargainPriceDO> findPage(PageModel page);

	/**
	 * 创建个人砍价
	 * 
	 * @param memberId
	 * @param promotionId
	 * @return
	 */
	BargainPriceDO create(MemberInfo memberInfo, Long promotionId);

	/**
	 * 获得我的砍价清单
	 * 
	 * @param memberId
	 * @param page
	 * @return
	 */
	PageResult<BargainPriceDO> findMyBargainList(Long memberId, PageModel page);

	/**
	 * 帮助好友砍一刀
	 * 
	 * @param unionId
	 * @param id
	 * @param helpUserName
	 * @param headPic
	 * @param helperId
	 * @return
	 */
	BigDecimal addBargainHelp(String unionId, String id, String helpUserName, String headPic, Long helperId);

	/**
	 * 根据id查询个人砍价
	 * 
	 * @param id
	 * @return
	 */
	BargainPriceDO getBargainPriceById(String id);

	/**
	 * 设置砍价成功
	 * 
	 * @param bargainId
	 * @return
	 */
	BargainPriceDO updateBargainSuccess(String bargainId);

	/**
	 * 更改砍价状态
	 * 
	 * @param bargainId
	 * @param status
	 * @return
	 */
	BargainPriceDO updateBargainStatus(String bargainId, BargainStatus status);

	/**
	 * 设置砍价活动结束
	 * 
	 * @param id
	 * @return
	 */
	BargainPriceDO updateBargainProccess(String id);

	/**
	 * 更新未购买成功的活动
	 * 
	 * @param bargainPromotion
	 * @return
	 */
	int updateBargainPromotion(BargainPromotion bargainPromotion);

	/**
	 * 设置成待支付状态
	 * 
	 * @param bargainId
	 * @return
	 */
	BargainPriceDO updateBargainForPay(String bargainId);

	/**
	 * 发起砍价的列表
	 * 
	 * @param page
	 * @param searcher
	 * @return
	 */
	PageResult<BargainPriceDO> findBySearcher(PageModel page, BargainPriceSearcher searcher);

	/**
	 * 根据id和unionId查找
	 * 
	 * @param id
	 * @param unionId
	 * @return
	 */
	BargainPriceDO findByIdAndUnionId(String id, String unionId);

	/**
	 * 根据unionId查找
	 * 
	 * @param unionId
	 * @return
	 */
	BargainPriceDO findByUnionId(String unionId);

	/**
	 * 根据活动查询规定时间内
	 * 
	 * @param promotionId
	 * @param page
	 * @return
	 */
	List<BargainPriceDO> findLatest(Long promotionId, PageModel page, Date date);

}
