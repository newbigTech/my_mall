package com.d2c.order.service.tx;

import java.math.BigDecimal;
import java.util.Date;

import com.d2c.order.model.PartnerCash;
import com.d2c.order.model.PartnerItem.PartnerLogType;
import com.d2c.order.model.PartnerWithhold;

public interface PartnerTxService {

	/**
	 * 下单返利
	 * 
	 * @param id
	 * @param operator
	 * @param rebate
	 * @param diffAmount
	 * @return
	 */
	int doBillSuccess(Long id, String operator, Integer rebate, BigDecimal diffAmount);

	/**
	 * 申请提现
	 * 
	 * @param partnerCash
	 * @return
	 */
	PartnerCash doInsertCash(PartnerCash partnerCash);

	/**
	 * 拒绝提现
	 * 
	 * @param id
	 * @param refuseReason
	 * @param refuseMan
	 * @param confirmOperateMan
	 * @return
	 */
	int doRefuse(Long id, String refuseReason, String refuseMan, String confirmOperateMan);

	/**
	 * 同意提现
	 * 
	 * @param id
	 * @param paySn
	 * @param payMan
	 * @param payDate
	 * @param operator
	 * @return
	 */
	int doPayment(Long id, String paySn, String payMan, Date payDate, String operator);

	/**
	 * 邀请奖励
	 * 
	 * @param partnerId
	 * @param inviteId
	 * @param toLoginCode
	 * @param amount
	 * @param operator
	 * @return
	 */
	int doAward(Long partnerId, Long inviteId, String toLoginCode, BigDecimal amount, String operator);

	/**
	 * 礼包奖励
	 * 
	 * @param partnerId
	 * @param amount
	 * @param operator
	 * @param orderId
	 * @param orderSn
	 * @return
	 */
	int doGift(Long partnerId, BigDecimal amount, String operator, Long orderId, String orderSn);

	/**
	 * 门店扣款
	 * 
	 * @param partnerWithhold
	 * @param type
	 * @return
	 */
	PartnerWithhold doWithhold(PartnerWithhold partnerWithhold, PartnerLogType type);

}
