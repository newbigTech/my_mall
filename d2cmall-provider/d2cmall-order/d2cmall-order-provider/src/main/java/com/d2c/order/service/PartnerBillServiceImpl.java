package com.d2c.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.codingapi.tx.annotation.TxTransaction;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.member.model.Partner;
import com.d2c.member.service.PartnerService;
import com.d2c.msg.service.MsgUniteService;
import com.d2c.msg.service.WeixinPushService;
import com.d2c.msg.support.MsgBean;
import com.d2c.msg.support.PushBean;
import com.d2c.msg.third.wechat.WeixinPushEntity;
import com.d2c.mybatis.service.ListServiceImpl;
import com.d2c.order.dao.PartnerBillMapper;
import com.d2c.order.model.Order;
import com.d2c.order.model.Order.OrderType;
import com.d2c.order.model.OrderItem;
import com.d2c.order.model.PartnerBill;
import com.d2c.order.model.PartnerGift;
import com.d2c.order.query.PartnerBillSearcher;
import com.d2c.order.service.tx.PartnerTxService;

@Service("partnerBillService")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class PartnerBillServiceImpl extends ListServiceImpl<PartnerBill> implements PartnerBillService {

	@Autowired
	private PartnerBillMapper partnerBillMapper;
	@Autowired
	private PartnerService partnerService;
	@Reference
	private PartnerTxService partnerTxService;
	@Autowired
	private PartnerGiftService partnerGiftService;
	@Autowired
	private WeixinPushService weixinPushService;
	@Autowired
	private MsgUniteService msgUniteService;

	@Override
	public PartnerBill findById(Long id) {
		return this.findOneById(id);
	}

	@Override
	@TxTransaction
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public PartnerBill insert(PartnerBill partnerBill) {
		partnerBill = this.save(partnerBill);
		if (partnerBill.getId() > 0) {
			Partner partner = partnerService.findById(partnerBill.getPartnerId());
			if (partner != null) {
				WeixinPushEntity msgObj = new WeixinPushEntity(partner.getOpenId(), "您有一条新的返利订单",
						partnerBill.getOrderSn(), new Date(), "返利金额：" + partnerBill.getActualAmount()
								.multiply(partnerBill.getPartnerRatio()).setScale(2, BigDecimal.ROUND_HALF_UP) + "元~",
						"/pages/member/bill/list");
				weixinPushService.send(msgObj);
				PushBean pushBean = new PushBean(
						partner.getMemberId(), "您有一条新的返利订单生成，预计收益" + partnerBill.getActualAmount()
								.multiply(partnerBill.getPartnerRatio()).setScale(2, BigDecimal.ROUND_HALF_UP) + "元~",
						82);
				pushBean.setAppUrl("/to/partner/sales");
				MsgBean msgBean = new MsgBean(partner.getMemberId(), 82, "返利单",
						"您有一条新的返利订单生成，预计收益" + partnerBill.getActualAmount().multiply(partnerBill.getPartnerRatio())
								.setScale(2, BigDecimal.ROUND_HALF_UP) + "元~");
				msgBean.setAppUrl("/to/partner/sales");
				msgUniteService.sendPush(pushBean, msgBean);
			}

		}
		return partnerBill;
	}

	@Override
	public PageResult<PartnerBill> findBySearcher(PartnerBillSearcher searcher, PageModel page) {
		PageResult<PartnerBill> pager = new PageResult<>(page);
		Integer toltalCount = partnerBillMapper.countBySearcher(searcher);
		List<PartnerBill> list = new ArrayList<>();
		if (toltalCount > 0) {
			list = partnerBillMapper.findBySearcher(searcher, page);
		}
		pager.setTotalCount(toltalCount);
		pager.setList(list);
		return pager;
	}

	@Override
	public Integer countBySearcher(PartnerBillSearcher searcher) {
		return partnerBillMapper.countBySearcher(searcher);
	}

	@Override
	public int updateStatus(Long id, Integer status) {
		return partnerBillMapper.updateStatus(id, status);
	}

	@Override
	public PartnerBill findByOrderItemId(Long orderItemId) {
		return partnerBillMapper.findByOrderItemId(orderItemId);
	}

	@Override
	@TxTransaction
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public int doClose(Long orderItemId) {
		PartnerBill partnerBill = partnerBillMapper.findByOrderItemId(orderItemId);
		int success = partnerBillMapper.doClose(orderItemId);
		if (success > 0) {
			Partner partner = partnerService.findById(partnerBill.getPartnerId());
			WeixinPushEntity msgObj = new WeixinPushEntity(partner.getOpenId(), "您有一条返利订单已关闭",
					partnerBill.getOrderSn(), new Date(), "返利金额：" + partnerBill.getActualAmount()
							.multiply(partnerBill.getPartnerRatio()).setScale(2, BigDecimal.ROUND_HALF_UP) + "元",
					"/pages/member/bill/list");
			weixinPushService.send(msgObj);
			PushBean pushBean = new PushBean(partner.getMemberId(), "您有一条返利订单已被取消，将损失" + partnerBill.getActualAmount()
					.multiply(partnerBill.getPartnerRatio()).setScale(2, BigDecimal.ROUND_HALF_UP) + "元", 82);
			pushBean.setAppUrl("/to/partner/sales");
			MsgBean msgBean = new MsgBean(partner.getMemberId(), 82, "返利单",
					"您有一条返利订单已被取消，将损失" + partnerBill.getActualAmount().multiply(partnerBill.getPartnerRatio())
							.setScale(2, BigDecimal.ROUND_HALF_UP) + "元");
			msgBean.setAppUrl("/to/partner/sales");
			msgUniteService.sendPush(pushBean, msgBean);
		}
		return success;
	}

	@Override
	public Map<String, Object> findPartnerSummaryToday(Long partnerId, Date beginDate, Date endDate, Integer[] status) {
		return partnerBillMapper.findPartnerSummaryToday(partnerId, beginDate, endDate, status);
	}

	@Override
	public Map<String, Object> findChildrenSummaryToday(Long partnerId, Date beginDate, Date endDate,
			Integer[] status) {
		return partnerBillMapper.findChildrenSummaryToday(partnerId, beginDate, endDate, status);
	}

	@Override
	public List<Map<String, Object>> findBillSummary(Long id, String rid, String ratio) {
		return partnerBillMapper.findBillSummary(id, rid, ratio);
	}

	@Override
	public List<Map<String, Object>> findCountGroupByStatus(Date beginDate, Date endDate) {
		return partnerBillMapper.findCountGroupByStatus(beginDate, endDate);
	}

	@Override
	public List<Map<String, Object>> findCountGroupByLevel(Date beginDate, Date endDate) {
		return partnerBillMapper.findCountGroupByLevel(beginDate, endDate);
	}

	@Override
	@TxTransaction
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public int doHandlePartner(Order order, List<OrderItem> orderItems) {
		if (order.getType() != null && order.getType().equals(OrderType.distribution.name())) {
			return 0;
		}
		if (order.getPartnerId() != null) {
			Partner partner = partnerService.findById(order.getPartnerId());
			if (partner != null && partner.getStatus() >= 0) {
				// 返利单处理
				for (OrderItem orderItem : orderItems) {
					PartnerBill pb = new PartnerBill(orderItem);
					pb.setBuyerCode(order.getLoginCode());
					pb.setPartnerCode(partner.getLoginCode());
					pb.setPartnerLevel(partner.getLevel());
					pb.setExcep(partner.getStatus() == 1 ? 0 : 1);
					// 返利比设置均为0则不生成返利单
					if (pb.getPartnerRatio().compareTo(new BigDecimal(0)) == 0
							&& pb.getParentRatio().compareTo(new BigDecimal(0)) == 0) {
						return 0;
					}
					this.insert(pb);
				}
				// 分销开单时间
				partnerService.updateConsumeDate(partner.getId(), partner.getStatus());
			}
		}
		if (order.getTerminalId() != null) {
			Long parentId = Long.valueOf(order.getTerminalId().split(":")[1]);
			Partner inviter = partnerService.findById(parentId);
			if (inviter != null && inviter.checkAvailable()) {
				if (order.getTerminalId().indexOf("first") != -1) {
					// 首次开店
					Integer level = Integer.valueOf(order.getTerminalId().split(":")[2]);
					partnerService.doCreate(parentId, order.getMemberId(), level);
				}
				PartnerGift gift = new PartnerGift();
				gift.setOrderSn(order.getOrderSn());
				gift.setLoginCode(order.getLoginCode());
				gift.setProductName(orderItems.get(0).getProductName());
				gift.setProductPic(orderItems.get(0).getProductImg());
				List<Long> pointIds = new ArrayList<>();
				switch (inviter.getLevel()) {
				case 0:
					// 邀请人是AM
					gift.setMasterId(inviter.getId());
					gift.setMasterRebate(new BigDecimal(300));
					pointIds.add(inviter.getId());
					break;
				case 1:
					// 邀请人是DM
					if (inviter.getPrestore() > 0 && !inviter.getMemberId().equals(order.getMemberId())) {
						// 会销DM，预存返还
						partnerService.updatePrestore(inviter.getId(), -1, "sys");
						partnerTxService.doGift(inviter.getId(), new BigDecimal(599), "sys", order.getId(),
								order.getOrderSn());
						// 会销DM，返利金额减少
						gift.setParentId(inviter.getId());
						gift.setParentRebate(new BigDecimal(150));
					} else {
						gift.setParentId(inviter.getId());
						gift.setParentRebate(new BigDecimal(250));
					}
					if (inviter.getMasterId() != null) {
						Partner am = partnerService.findById(inviter.getMasterId());
						if (am != null && am.checkAvailable()) {
							gift.setMasterId(am.getId());
							gift.setMasterRebate(new BigDecimal(50));
						}
					}
					pointIds = partnerService.findPointRelation(inviter.getPath());
					break;
				case 2:
					// 邀请人是买手
					gift.setPartnerId(inviter.getId());
					gift.setPartnerRebate(new BigDecimal(150));
					if (inviter.getParentId() != null) {
						Partner parent = partnerService.findById(inviter.getParentId());
						if (parent != null && parent.checkAvailable()) {
							if (parent.getLevel() == 0) {
								gift.setMasterId(parent.getId());
								gift.setMasterRebate(new BigDecimal(150));
							} else if (parent.getLevel() == 1) {
								gift.setParentId(parent.getId());
								gift.setParentRebate(new BigDecimal(100));
								if (inviter.getMasterId() != null) {
									Partner am = partnerService.findById(inviter.getMasterId());
									if (am != null && am.checkAvailable()) {
										gift.setMasterId(am.getId());
										gift.setMasterRebate(new BigDecimal(50));
									}
								}
							}
						}
					}
					pointIds = partnerService.findPointRelation(inviter.getPath());
					break;
				default:
					break;
				}
				// 礼包返利
				if (gift.getPartnerId() != null) {
					partnerTxService.doGift(gift.getPartnerId(), gift.getPartnerRebate(), "sys", order.getId(),
							order.getOrderSn());
				}
				if (gift.getParentId() != null) {
					partnerTxService.doGift(gift.getParentId(), gift.getParentRebate(), "sys", order.getId(),
							order.getOrderSn());
				}
				if (gift.getMasterId() != null) {
					partnerTxService.doGift(gift.getMasterId(), gift.getMasterRebate(), "sys", order.getId(),
							order.getOrderSn());
				}
				partnerGiftService.insert(gift);
				// 积分赠送
				partnerService.updatePoint(pointIds, 1);
			}
		}
		return 1;
	}

}
