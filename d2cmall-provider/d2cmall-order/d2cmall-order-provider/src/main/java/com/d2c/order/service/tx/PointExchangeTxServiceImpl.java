package com.d2c.order.service.tx;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.tx.annotation.TxTransaction;
import com.d2c.common.base.exception.BusinessException;
import com.d2c.member.enums.PointRuleTypeEnum;
import com.d2c.member.model.MemberInfo;
import com.d2c.member.model.MemberIntegration;
import com.d2c.member.model.MemberIntegration.StatusEnum;
import com.d2c.member.service.MemberIntegrationService;
import com.d2c.order.enums.CouponSourceEnum;
import com.d2c.order.model.Coupon;
import com.d2c.order.model.CouponDef;
import com.d2c.order.service.CouponDefQueryService;
import com.d2c.order.service.CouponDefService;
import com.d2c.product.model.ExternalCard;
import com.d2c.product.model.PointProduct;
import com.d2c.product.model.PointProduct.PointProductTypeEnum;
import com.d2c.product.service.ExternalCardService;
import com.d2c.product.service.PointProductService;
import com.d2c.util.serial.SerialNumUtil;

@Service(protocol = { "dubbo" })
public class PointExchangeTxServiceImpl implements PointExchangeTxService {

	@Autowired
	private PointProductService pointProductService;
	@Autowired
	private MemberIntegrationService memberIntegrationService;
	@Autowired
	private ExternalCardService externalCardService;
	@Autowired
	private CouponDefService couponDefService;
	@Autowired
	private CouponDefQueryService couponDefQueryService;

	@Override
	@TxTransaction(isStart = true)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Map<String, JSONObject> doExchange(PointProduct pointProduct, MemberInfo memberInfo) {
		Map<String, JSONObject> map = new HashMap<>();
		int success = pointProductService.updateCount(pointProduct.getId(), -1);
		if (success > 0) {
			String sn = SerialNumUtil.buildPointExchangeSn();
			PointProductTypeEnum type = PointProductTypeEnum.valueOf(pointProduct.getType());
			String remark = null;
			Long transactionId = null;
			switch (type) {
			case CARD:
				// 兑换外部卡
				success = externalCardService.doUse(memberInfo.getId(), sn, pointProduct.getSourceId(),
						pointProduct.getId());
				if (success < 1) {
					throw new BusinessException("兑换不成功，该商品已经兑完了！");
				} else {
					ExternalCard card = externalCardService.findBySn(memberInfo.getId(), sn);
					transactionId = card.getId();
					remark = card.getCode();
					map.put("card", card.toJson());
				}
				break;
			case COUPON:
				// 兑换优惠券
				CouponDef couponDef = couponDefQueryService.findById(pointProduct.getProductId());
				if (couponDef == null || couponDef.getId() == 1) {
					throw new BusinessException("优惠券不存在！");
				}
				Coupon coupon = couponDefService.doClaimedCoupon(couponDef.getId(), memberInfo.getId(),
						memberInfo.getLoginCode(), memberInfo.getLoginCode(), CouponSourceEnum.D2CMALL.name(), false);
				transactionId = coupon.getId();
				map.put("coupon", coupon.toJson());
				break;
			default:
				break;
			}
			if (transactionId != null) {
				MemberIntegration memberIntegration = new MemberIntegration(memberInfo.getId(),
						memberInfo.getLoginCode(), PointRuleTypeEnum.EXCHANGE.name(), pointProduct.getPoint(),
						transactionId, sn, new Date(), "积分兑换商品'" + pointProduct.getName() + "'", -1,
						StatusEnum.SUCCESS.getCode(), pointProduct.getAmount(), pointProduct.getName(),
						pointProduct.getIntroPic(), remark, pointProduct.getId());
				success = memberIntegrationService.doSendIntegration(memberIntegration);
				if (success < 1) {
					throw new BusinessException("兑换不成功！");
				}
			} else {
				throw new BusinessException("兑换不成功！");
			}
		}
		return map;
	}

}
