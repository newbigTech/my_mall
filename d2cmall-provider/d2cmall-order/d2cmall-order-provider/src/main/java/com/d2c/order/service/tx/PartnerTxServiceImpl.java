package com.d2c.order.service.tx;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.tx.annotation.TxTransaction;
import com.d2c.member.model.Account;
import com.d2c.member.model.Partner;
import com.d2c.member.service.AccountService;
import com.d2c.member.service.PartnerInviteService;
import com.d2c.member.service.PartnerService;
import com.d2c.msg.service.MsgUniteService;
import com.d2c.msg.service.WeixinPushService;
import com.d2c.msg.support.MsgBean;
import com.d2c.msg.support.PushBean;
import com.d2c.msg.third.wechat.WeixinPushEntity;
import com.d2c.order.handle.PartnerRebateStepHandler;
import com.d2c.order.model.AccountItem;
import com.d2c.order.model.PartnerBill;
import com.d2c.order.model.PartnerCash;
import com.d2c.order.model.PartnerCash.PayType;
import com.d2c.order.model.PartnerItem;
import com.d2c.order.model.PartnerItem.PartnerLogType;
import com.d2c.order.model.PartnerWithhold;
import com.d2c.order.service.AccountItemService;
import com.d2c.order.service.PartnerBillService;
import com.d2c.order.service.PartnerCashService;
import com.d2c.order.service.PartnerItemService;
import com.d2c.order.service.PartnerWithholdService;

@Service(protocol = { "dubbo" })
public class PartnerTxServiceImpl implements PartnerTxService {

	@Autowired
	private PartnerService partnerService;
	@Autowired
	private PartnerItemService partnerItemService;
	@Autowired
	private PartnerBillService partnerBillService;
	@Autowired
	private WeixinPushService weixinPushService;
	@Autowired
	private MsgUniteService msgUniteService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private PartnerCashService partnerCashService;
	@Autowired
	private PartnerInviteService partnerInviteService;
	@Autowired
	private PartnerWithholdService partnerWithholdService;
	@Autowired
	private AccountItemService accountItemService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private void insertLog(PartnerLogType logType, BigDecimal amount, Long partnerId, Long sourceId, String sourceSn,
			String operator) {
		PartnerItem log = new PartnerItem(logType);
		log.setAmount(amount);
		log.setPartnerId(partnerId);
		log.setSourceId(sourceId);
		log.setSourceSn(sourceSn);
		partnerItemService.insert(log);
	}

	@Override
	@TxTransaction(isStart = false)
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public int doBillSuccess(Long id, String operator, Integer off, BigDecimal diffAmount) {
		int success = partnerBillService.updateStatus(id, 8);
		if (success > 0) {
			PartnerBill partnerBill = partnerBillService.findById(id);
			Partner partner = partnerService.findById(partnerBill.getPartnerId());
			if (partner == null) {
				return 0;
			}
			Long partnerId = partnerBill.getPartnerId();
			Long parentId = partnerBill.getParentId();
			Long superId = partnerBill.getSuperId();
			Long masterId = partnerBill.getMasterId();
			Long topId = null;
			Long tempId = null;
			if (partner.getLevel() == 0) {
				tempId = partner.getParentId();
			} else {
				if (partnerBill.getMasterId() != null) {
					Partner master = partnerService.findById(partnerBill.getMasterId());
					if (master != null) {
						tempId = master.getParentId();
					}
				}
			}
			if (tempId != null) {
				Partner top = partnerService.findById(tempId);
				if (top != null && top.getLevel() == 0) {
					topId = top.getId();
				}
			}
			BigDecimal rebate = partnerBill.getActualAmount().multiply(partnerBill.getPartnerRatio());
			BigDecimal diff = diffAmount.multiply(partnerBill.getPartnerRatio());
			if (off == 1) {
				rebate = rebate.subtract(diff);
			}
			final BigDecimal ZERO = new BigDecimal(0);
			PartnerRebateStepHandler handler = new PartnerRebateStepHandler(partner.getLevel());
			BigDecimal[] result = handler.formula(rebate, partnerId, parentId, superId, masterId, topId);
			// BUYER
			if (result[0].compareTo(ZERO) > 0) {
				partnerService.doRebate(partnerId, result[0], partnerBill.getActualAmount(), operator);
				this.insertLog(PartnerLogType.BILL, result[0].add(diff), partnerId, partnerBill.getId(),
						partnerBill.getSn(), operator);
				if (off == 1) {
					this.insertLog(PartnerLogType.DIFF, diff, partnerId, partnerBill.getId(), partnerBill.getSn(),
							operator);
				}
			}
			// DM
			if (result[1].compareTo(ZERO) > 0) {
				partnerService.doRebate(parentId, result[1], partnerBill.getActualAmount(), operator);
				this.insertLog(PartnerLogType.TEAM, result[1], parentId, partnerBill.getId(), partnerBill.getSn(),
						operator);
			}
			// DM1
			if (result[2].compareTo(ZERO) > 0) {
				partnerService.doRebate(superId, result[2], partnerBill.getActualAmount(), operator);
				this.insertLog(PartnerLogType.TEAM, result[2], superId, partnerBill.getId(), partnerBill.getSn(),
						operator);
			}
			// AM
			if (result[3].compareTo(ZERO) > 0) {
				partnerService.doRebate(masterId, result[3], partnerBill.getActualAmount(), operator);
				this.insertLog(PartnerLogType.TEAM, result[3], masterId, partnerBill.getId(), partnerBill.getSn(),
						operator);
			}
			// AM1
			if (result[4].compareTo(ZERO) > 0) {
				partnerService.doRebate(topId, result[4], partnerBill.getActualAmount(), operator);
				this.insertLog(PartnerLogType.TEAM, result[4], topId, partnerBill.getId(), partnerBill.getSn(),
						operator);
			}
			BigDecimal x = result[0].add(diff).setScale(2, BigDecimal.ROUND_HALF_UP);
			WeixinPushEntity msgObj = new WeixinPushEntity(partner.getOpenId(), "您有一条返利订单已完成", partnerBill.getOrderSn(),
					new Date(), "返利金额：" + x + "元", "/pages/member/bill/list");
			weixinPushService.send(msgObj);
			PushBean pushBean = new PushBean(partner.getMemberId(), "您有一条返利订单已完成，收益" + x + "元~", 82);
			pushBean.setAppUrl("/to/partner/sales");
			MsgBean msgBean = new MsgBean(partner.getMemberId(), 82, "返利单", "您有一条返利订单已完成，收益" + x + "元~");
			msgBean.setAppUrl("/to/partner/sales");
			msgUniteService.sendPush(pushBean, msgBean);
		}
		return success;
	}

	@Override
	@TxTransaction(isStart = true)
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public PartnerCash doInsertCash(PartnerCash partnerCash) {
		partnerCash = partnerCashService.insert(partnerCash);
		partnerService.doApplyCash(partnerCash.getPartnerId(), partnerCash.getApplyAmount(), 1);
		if (partnerCash.getPayType().equals(PayType.wallet.name())) {
			logger.error("[提现申请]..." + partnerCash.getPartnerId() + "." + partnerCash.getApplyAmount());
			this.doPayment(partnerCash.getId(), partnerCash.getSn(), "sys", new Date(), "sys");
			Account account = accountService.findByMemberId(partnerCash.getMemberId());
			AccountItem accountItem = new AccountItem(partnerCash, account.getId());
			accountItem = accountItemService.insert(accountItem);
			accountService.doUpdateAmount(account.getId(), accountItem.getFactAmount(),
					accountItem.getFactGiftAmount());
			logger.error("[钱包到账]..." + account.getId() + "." + accountItem.getFactAmount());
		}
		return partnerCash;
	}

	@Override
	@TxTransaction(isStart = true)
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int doRefuse(Long id, String refuseReason, String refuseMan, String confirmOperateMan) {
		int success = partnerCashService.doRefuse(id, refuseReason, refuseMan, confirmOperateMan);
		if (success > 0) {
			PartnerCash partnerCash = partnerCashService.findById(id);
			partnerService.doApplyCash(partnerCash.getPartnerId(), partnerCash.getApplyAmount(), -1);
			Partner partner = partnerService.findById(partnerCash.getPartnerId());
			BigDecimal x = partnerCash.getApplyAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
			WeixinPushEntity msgObj = new WeixinPushEntity(partner.getOpenId(),
					"提现单号:" + partnerCash.getSn() + " 审核不通过", partnerCash.getCreateDate(), x, "提现失败",
					"失败原因：" + refuseReason, "/pages/member/withdrawal/list");
			weixinPushService.send(msgObj);
			PushBean pushBean = new PushBean(partner.getMemberId(), "您申请的提现审核不通过，申请金额：¥ " + x + "，原因：" + refuseReason,
					81);
			pushBean.setAppUrl("/to/partner/cash");
			MsgBean msgBean = new MsgBean(partner.getMemberId(), 81, "提现失败",
					"您申请的提现审核不通过，申请金额：¥ " + x + "，原因：" + refuseReason);
			msgBean.setAppUrl("/to/partner/cash");
			JSONObject other = new JSONObject();
			other.put("success", -1);
			msgBean.setOther(other.toJSONString());
			msgUniteService.sendPush(pushBean, msgBean);
		}
		return success;
	}

	@Override
	@TxTransaction(isStart = true)
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int doPayment(Long id, String paySn, String payMan, Date payDate, String operator) {
		PartnerCash partnerCash = partnerCashService.findById(id);
		int success = partnerCashService.doPay(id, paySn, partnerCash.getApplyAmount(), payMan, payDate);
		logger.error("[提现通过]..." + partnerCash.getPartnerId() + "." + partnerCash.getApplyAmount());
		if (success > 0) {
			partnerService.doWithdCash(partnerCash.getPartnerId(), partnerCash.getApplyAmount(),
					partnerCash.getApplyAmount(), operator);
			logger.error("[分销扣账]..." + partnerCash.getPartnerId() + "." + partnerCash.getApplyAmount());
			if (partnerCash.getId() != null) {
				PartnerItem log = new PartnerItem(PartnerLogType.CASH);
				log.setAmount(partnerCash.getApplyAmount());
				log.setPartnerId(partnerCash.getPartnerId());
				log.setSourceId(partnerCash.getId());
				log.setSourceSn(partnerCash.getSn());
				log.setCreator(operator);
				partnerItemService.insert(log);
				logger.error("[分销明细]..." + partnerCash.getPartnerId() + "." + partnerCash.getApplyAmount());
				Partner partner = partnerService.findById(partnerCash.getPartnerId());
				BigDecimal x = partnerCash.getApplyAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
				WeixinPushEntity msgObj = new WeixinPushEntity(partner.getOpenId(),
						"提现单号:" + partnerCash.getSn() + " 已提现成功", payDate, x, "提现成功", "支付流水号：" + partnerCash.getPaySn(),
						"/pages/member/withdrawal/list");
				weixinPushService.send(msgObj);
				PushBean pushBean = new PushBean(partner.getMemberId(), "您的提现已支付，提现金额 ¥ " + x + "，请注意查收~", 81);
				pushBean.setAppUrl("/to/partner/cash");
				MsgBean msgBean = new MsgBean(partner.getMemberId(), 81, "提现成功", "您的提现已支付，提现金额 ¥ " + x + "，请注意查收~");
				msgBean.setAppUrl("/to/partner/cash");
				JSONObject other = new JSONObject();
				other.put("success", 1);
				msgBean.setOther(other.toJSONString());
				msgUniteService.sendMsg(null, pushBean, msgBean, null);
			}
		}
		return success;
	}

	@Override
	@TxTransaction(isStart = true)
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int doAward(Long partnerId, Long inviteId, String toLoginCode, BigDecimal amount, String operator) {
		int success = partnerService.doAward(partnerId, amount, operator);
		if (success > 0) {
			partnerInviteService.doAward(inviteId);
			PartnerItem log = new PartnerItem(PartnerLogType.INVITE);
			log.setAmount(amount);
			log.setPartnerId(partnerId);
			log.setSourceId(inviteId);
			log.setSourceSn(toLoginCode);
			log.setCreator(operator);
			partnerItemService.insert(log);
		}
		return success;
	}

	@Override
	@TxTransaction(isStart = false)
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int doGift(Long partnerId, BigDecimal amount, String operator, Long orderId, String orderSn) {
		int success = partnerService.doGift(partnerId, amount, operator);
		if (success > 0) {
			PartnerItem log = new PartnerItem(PartnerLogType.GIFT);
			log.setAmount(amount);
			log.setPartnerId(partnerId);
			log.setSourceId(orderId);
			log.setSourceSn(orderSn);
			log.setCreator(operator);
			partnerItemService.insert(log);
		}
		return success;
	}

	@Override
	@TxTransaction(isStart = true)
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public PartnerWithhold doWithhold(PartnerWithhold partnerWithhold, PartnerLogType type) {
		partnerWithhold = partnerWithholdService.insert(partnerWithhold);
		if (partnerWithhold.getId() != null) {
			PartnerItem log = new PartnerItem(type);
			log.setAmount(partnerWithhold.getAmount());
			log.setPartnerId(partnerWithhold.getPartnerId());
			log.setSourceId(partnerWithhold.getId());
			log.setSourceSn(partnerWithhold.getSn());
			log.setCreator(partnerWithhold.getCreator());
			partnerItemService.insert(log);
			partnerService.doWithhold(partnerWithhold.getPartnerId(), partnerWithhold.getAmount(),
					partnerWithhold.getCreator(), type.getDisplay());
		}
		return partnerWithhold;
	}

}
