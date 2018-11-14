package com.d2c.order.third.payment.wxpay.refund;

import java.io.InputStream;

public class RefundConfig2 extends WXPayConfig {

	private String refund_notify_url;

	public String getRefund_notify_url() {
		return refund_notify_url;
	}

	public void setRefund_notify_url(String refund_notify_url) {
		this.refund_notify_url = refund_notify_url;
	}

	@Override
	String getAppID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	String getMchID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	InputStream getCertStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	IWXPayDomain getWXPayDomain() {
		// TODO Auto-generated method stub
		return null;
	}

}
