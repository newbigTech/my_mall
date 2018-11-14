package com.d2c.msg.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.d2c.msg.third.sms.emay.EmayDomesticSMSClient;
import com.d2c.msg.third.sms.emay.EmayGlobalSMSClient;
import com.d2c.msg.third.sms.montnets.MontnetsSMSClient;
import com.d2c.msg.third.vms.DyvmsClient;

/***
 * 短信发送
 */
@Service("smsService")
public class SmsServiceImpl implements SmsService {

	@Override
	public int sendSMS(String[] mobiles, String smsContent) {
		int success = EmayDomesticSMSClient.sendSMS(mobiles, smsContent);
		if (success == -1) {
			for (int i = 0; i < mobiles.length; i++) {
				String mobile = mobiles[i];
				if (!StringUtils.isEmpty(mobile)) {
					MontnetsSMSClient.sendSms(mobile, smsContent);
				}
			}
		}
		return success;
	}

	@Override
	public int sendSMS(String mobile, String smsContent) {
		int success = EmayDomesticSMSClient.sendSMS(mobile, smsContent);
		if (success == -1) {
			MontnetsSMSClient.sendSms(mobile, smsContent);
		}
		return success;
	}

	@Override
	public int sendGlobalSMS(String[] mobiles, String smsContent) {
		int success = EmayGlobalSMSClient.sendSMS(mobiles, smsContent);
		if (success == -1) {
			for (int i = 0; i < mobiles.length; i++) {
				String mobile = mobiles[i];
				if (!StringUtils.isEmpty(mobile)) {
					MontnetsSMSClient.sendSms(mobile, smsContent);
				}
			}
		}
		return success;
	}

	@Override
	public int sendGlobalSMS(String mobile, String smsContent) {
		int success = EmayGlobalSMSClient.sendSMS(mobile, smsContent);
		if (success == -1) {
			MontnetsSMSClient.sendSms(mobile, smsContent);
		}
		return success;
	}

	@Override
	public String getBalance() {
		StringBuilder builder = new StringBuilder();
		builder.append(EmayDomesticSMSClient.getBalance() * 10);
		return builder.toString();
	}

	@Override
	public int doSendVms(String mobile, String code, String tempId, JSONObject params) {
		return DyvmsClient.singleCallByTts(mobile, code, tempId, params);
	}

}
