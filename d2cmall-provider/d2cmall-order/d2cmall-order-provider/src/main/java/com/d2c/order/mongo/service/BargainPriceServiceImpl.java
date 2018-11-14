package com.d2c.order.mongo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.common.base.exception.BusinessException;
import com.d2c.common.mq.enums.MqEnum;
import com.d2c.member.model.MemberInfo;
import com.d2c.member.model.MemberLotto.LotteryOpportunityEnum;
import com.d2c.msg.model.SmsLog.SmsLogType;
import com.d2c.msg.service.MsgUniteService;
import com.d2c.msg.support.MsgBean;
import com.d2c.msg.support.PushBean;
import com.d2c.msg.support.SmsBean;
import com.d2c.order.mongo.dao.BargainHelpMongoDao;
import com.d2c.order.mongo.dao.BargainMongoDao;
import com.d2c.order.mongo.enums.BargainStatus;
import com.d2c.order.mongo.model.BargainHelpDO;
import com.d2c.order.mongo.model.BargainPriceDO;
import com.d2c.order.query.BargainPriceSearcher;
import com.d2c.product.model.BargainPromotion;
import com.d2c.product.model.BargainRule;
import com.d2c.product.search.model.SearcherProduct;
import com.d2c.product.search.service.ProductSearcherQueryService;
import com.d2c.product.service.BargainPromotionService;
import com.d2c.product.service.BargainRuleService;

@Service("bargainPriceService")
public class BargainPriceServiceImpl implements BargainPriceService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BargainMongoDao bargainMongoDao;
	@Autowired
	private BargainHelpMongoDao bargainHelpMongoDao;
	@Reference
	private ProductSearcherQueryService productSearcherQueryService;
	@Autowired
	private BargainPromotionService bargainPromotionService;
	@Autowired
	private BargainRuleService bargainRuleService;
	@Autowired
	private MsgUniteService msgUniteService;

	@Override
	public List<BargainPriceDO> findPage(PageModel page) {
		return bargainMongoDao.findBySearcher(null, page);
	}

	@Override
	public PageResult<BargainPriceDO> findBySearcher(PageModel page, BargainPriceSearcher searcher) {
		PageResult<BargainPriceDO> pager = new PageResult<>(page);
		long totalCount = bargainMongoDao.countBySearcher(searcher);
		if (totalCount > 0) {
			List<BargainPriceDO> list = bargainMongoDao.findBySearcher(searcher, page);
			pager.setList(list);
		}
		pager.setTotalCount((int) totalCount);
		return pager;

	}

	/**
	 * 创建个人砍价
	 */
	@Override
	public BargainPriceDO create(MemberInfo memberInfo, Long promotionId) {
		BargainPromotion bargainPromotion = bargainPromotionService.findById(promotionId);
		if (bargainPromotion == null || bargainPromotion.isOver()) {
			throw new BusinessException("商品不在活动期内，看看其他砍价商品");
		}
		SearcherProduct product = productSearcherQueryService.findById(bargainPromotion.getProductId().toString());
		if (product == null || product.getMark() < 1 || product.getStore() < 1) {
			throw new BusinessException("该商品已砍价完，看看其他砍价商品");
		}
		BargainPriceDO bean = new BargainPriceDO(memberInfo, product.getMinPrice(), bargainPromotion,
				product.getName());
		bean.setCreateDate(new Date());
		bean = bargainMongoDao.insert(bean);
		if (bean.getId() != null) {
			bargainPromotionService.updateCount(promotionId);
			// 增加抽奖次数
			Map<String, Object> map = new HashMap<>();
			map.put("memberId", memberInfo.getId());
			map.put("lotteryOpportunityEnum", LotteryOpportunityEnum.BARGAIN.name());
			MqEnum.AWARD_QUALIFIED.send(map);
		}
		return bean;
	}

	/**
	 * 帮助好友砍一刀 TODO
	 */
	@Override
	public BigDecimal addBargainHelp(String unionId, String id, String helpUserName, String headPic, Long helperId) {
		BargainPriceDO oldPrice = this.getBargainPriceById(id);
		if (oldPrice == null) {
			throw new BusinessException("该砍价单不存在");
		}
		BargainPriceDO bargainPrice = this.updateBargainProccess(id);
		BigDecimal subPrice = new BigDecimal(0);

		try {
			if (bargainPrice != null && BargainStatus.BEGIN.name().equals(bargainPrice.getStatus())) {
				BigDecimal originPrice = new BigDecimal(bargainPrice.getPrice()).setScale(2, RoundingMode.HALF_UP);
				if (unionId.equals(bargainPrice.getUnionId())) {
					throw new BusinessException("不能自己砍自己发起的砍价商品哦");
				}
				// 活动结束否
				BargainPromotion bargainPromotion = bargainPromotionService.findById(bargainPrice.getBargainId());
				if (bargainPromotion == null || bargainPromotion.isOver()) {
					// TODO 提示语要不要改一下，库存的要不要控制一下
					throw new BusinessException("活动不在活动期间，看看其他砍价商品");
				}
				if (originPrice.compareTo(bargainPromotion.getMinPrice()) <= 0) {
					throw new BusinessException("该砍价商品已经砍到最低价了，看看其他砍价商品");
				}
				// 是否该用户已经砍过了
				BargainPriceDO newBargain = bargainMongoDao.findByIdAndUnionId(id, unionId);
				if (newBargain != null) {
					throw new BusinessException("您已经砍过该商品了");
				}
				// 知道低于此时价格的等级
				BargainRule bargainRule = bargainRuleService.findUpperRule(bargainPrice.getBargainId(), originPrice);
				if (bargainRule == null) {
					// 找最后一级,防止最后一级比最低价大
					bargainRule = bargainRuleService.findLast(bargainPrice.getBargainId());
				}
				if (bargainRule != null) {
					subPrice = new BigDecimal(
							Math.random() * (bargainRule.getMax() - bargainRule.getMin() + 1) + bargainRule.getMin())
									.setScale(0, RoundingMode.DOWN);
					if (originPrice.subtract(subPrice).compareTo(bargainPrice.getBargain().getMinPrice()) < 0) {
						subPrice = originPrice.subtract(bargainPrice.getBargain().getMinPrice()).setScale(2);
					}
				}
				// 插入助力列表
				if (subPrice.compareTo(new BigDecimal(0)) > 0) {
					BargainHelpDO helper = new BargainHelpDO(bargainPrice.getId(), helperId, helpUserName,
							subPrice.doubleValue(), headPic, unionId);
					bargainPrice
							.setPrice((originPrice.subtract(subPrice)).setScale(2, RoundingMode.HALF_UP).doubleValue());
					// 修改时间
					bargainPrice.setGmtModified(new Date());
					long result = updateHelper(bargainPrice, helper);
					if (result < 1) {
						subPrice = null;
					} else if (bargainPrice.getPrice() < bargainRule.getGradePrice().doubleValue()
							&& bargainPrice.getPrice() + subPrice.doubleValue() >= bargainRule.getGradePrice()
									.doubleValue()) {
						String subject = "砍价活动降价通知";
						String content = "您发起的砍价商品" + bargainPrice.getProductName() + "已经砍至" + bargainPrice.getPrice()
								+ "元了，邀请好友砍至最低价";
						PushBean pushBean = new PushBean(bargainPrice.getMemberId(), content, 61);
						pushBean.setAppUrl("/bargain/promotion/list");
						MsgBean msgBean = new MsgBean(bargainPrice.getMemberId(), 61, subject, content);
						msgBean.setAppUrl("/bargain/promotion/list");
						msgBean.setPic(bargainPrice.getBargain().getCoverPic());
						SmsBean smsBean = new SmsBean(null, bargainPrice.getLoginCode(), SmsLogType.REMIND, content);
						msgUniteService.sendMsg(smsBean, pushBean, msgBean, null);
					}
				}
				// 增加抽奖次数
				Map<String, Object> map = new HashMap<>();
				map.put("memberId", helperId);
				map.put("lotteryOpportunityEnum", LotteryOpportunityEnum.BARGAIN.name());
				MqEnum.AWARD_QUALIFIED.send(map);
			}
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage());
		} finally {
			bargainMongoDao.updateBargainBegin(id);
		}
		return subPrice;
	}

	private long updateHelper(BargainPriceDO bargainPrice, BargainHelpDO helper) {
		bargainPrice.addHelper(helper);
		try {
			bargainHelpMongoDao.insert(helper);
		} catch (Exception e) {
			logger.error("帮助列表插入报错", e);
		}
		return bargainMongoDao.updateHelper(bargainPrice);
	}

	/**
	 * 砍价详情
	 */
	@Override
	public BargainPriceDO getBargainPriceById(String id) {
		return bargainMongoDao.findById(id);
	}

	/**
	 * 获得我的砍价清单
	 */
	@Override
	public PageResult<BargainPriceDO> findMyBargainList(Long memberId, PageModel page) {
		PageResult<BargainPriceDO> dtos = new PageResult<>(page);
		long totalCount = bargainMongoDao.countMine(memberId);
		if (totalCount > 0) {
			List<BargainPriceDO> list = bargainMongoDao.findMyBargainList(memberId, page.getPageNumber(),
					page.getPageSize());
			dtos.setList(list);
		}
		dtos.setTotalCount((int) totalCount);
		return dtos;
	}

	/**
	 * 将砍价设置成待支付
	 */
	@Override
	public BargainPriceDO updateBargainForPay(String bargainId) {
		return bargainMongoDao.updateStatus(bargainId, BargainStatus.ORDERED);
	}

	/**
	 * 设置砍价成功
	 */
	@Override
	public BargainPriceDO updateBargainSuccess(String bargainId) {
		return bargainMongoDao.updateBargainSuccess(bargainId);
	}

	@Override
	public BargainPriceDO updateBargainStatus(String bargainId, BargainStatus status) {
		return bargainMongoDao.updateStatus(bargainId, status);
	}

	@Override
	public int updateBargainPromotion(BargainPromotion bargainPromotion) {
		return bargainMongoDao.updateBargainPromotion(bargainPromotion);
	}

	/**
	 * 设置朋友帮助砍价中
	 */
	@Override
	public BargainPriceDO updateBargainProccess(String id) {
		return bargainMongoDao.updateBargainProccess(id);
	}

	/**
	 * 根据id和unionId查找
	 * 
	 * @param id
	 * @param unionId
	 * @return
	 */
	@Override
	public BargainPriceDO findByIdAndUnionId(String id, String unionId) {
		return bargainMongoDao.findByIdAndUnionId(id, unionId);
	}

	@Override
	public BargainPriceDO findByUnionId(String unionId) {
		return bargainMongoDao.findByUnionId(unionId);
	}

	@Override
	public List<BargainPriceDO> findLatest(Long promotionId, PageModel page, Date date) {
		return bargainMongoDao.findLatest(promotionId, date, page);
	}

}
