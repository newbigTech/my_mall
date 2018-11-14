package com.d2c.order.third.payment.wxpay.refund;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.d2c.order.third.payment.wxpay.core.WxAppConfig;

public class RefundConfig1 extends WXPayConfig {

	private String refund_notify_url;

	public String getRefund_notify_url() {
		return refund_notify_url;
	}

	public void setRefund_notify_url(String refund_notify_url) {
		this.refund_notify_url = refund_notify_url;
	}

	@Override
	String getAppID() {
		return WxAppConfig.APP_ID;
	}

	@Override
	String getMchID() {
		return WxAppConfig.MCH_ID;
	}

	@Override
	String getKey() {
		return WxAppConfig.PAY_KEY;
	}

	@Override
	InputStream getCertStream() {
		File directory = new File("");
		File f = new File(directory.getAbsolutePath() + "/apiclient_cert.p12");
		InputStream in = null;
		try {
			in = new FileInputStream(f);
			byte b[] = new byte[1024];
			int len = 0;
			int temp = 0;
			while ((temp = in.read()) != -1) {
				b[len] = (byte) temp;
				len++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return in;
	}

	@Override
	IWXPayDomain getWXPayDomain() {
		// TODO Auto-generated method stub
		return null;
	}

}
