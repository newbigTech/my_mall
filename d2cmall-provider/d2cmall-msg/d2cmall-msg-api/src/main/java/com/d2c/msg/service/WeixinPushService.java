package com.d2c.msg.service;

import com.d2c.msg.third.wechat.WeixinPushEntity;

public interface WeixinPushService {

	void send(WeixinPushEntity msgObj);

}
