package com.d2c.order.service.tx;

import java.math.BigDecimal;

import com.d2c.member.enums.DeviceTypeEnum;
import com.d2c.order.enums.PaymentTypeEnum;
import com.d2c.order.model.base.IPaymentInterface;

public interface PaymentTxService {

	/**
	 * 钱包支付
	 * 
	 * @param order
	 * @param password
	 * @param deviceType
	 * @return
	 */
	int doWalletPayment(IPaymentInterface order, String password, DeviceTypeEnum deviceType);

	/**
	 * 支付成功
	 * 
	 * @param orderType
	 * @param orderSn
	 * @param payAmount
	 * @param paySn
	 * @param payMan
	 * @param payType
	 * @param mchId
	 * @param appId
	 * @return
	 */
	int doPaySuccess(String orderType, String orderSn, BigDecimal payAmount, String paySn, String payMan,
			PaymentTypeEnum payType, String mchId, String appId);

}
