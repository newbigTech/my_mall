package com.d2c.common.mq.listener;

import javax.jms.MessageListener;

import com.d2c.common.mq.enums.MqEnum;

public interface BaseMqListener extends MessageListener {

	public MqEnum getMqEnum();

}
