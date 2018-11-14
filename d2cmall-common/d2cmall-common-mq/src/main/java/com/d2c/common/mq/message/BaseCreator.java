package com.d2c.common.mq.message;

import javax.jms.JMSException;
import javax.jms.Message;

import org.apache.activemq.ScheduledMessage;
import org.springframework.jms.core.MessageCreator;

public abstract class BaseCreator implements MessageCreator {

	protected Message message;

	protected Long seconds;

	/**
	 * 参数配置
	 */
	protected void setProperty() throws JMSException {
		// 消息延迟
		if (seconds != null && seconds > 0) {
			message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, seconds * 1000);
		}
	}

	public void setMessage(Message message)  throws JMSException{
		this.message = message;
		setProperty();
	}

	public Message getMessage() {
		return message;
	}

	public Long getSeconds() {
		return seconds;
	}

	public void setSeconds(Long seconds) {
		this.seconds = seconds;
	}

}
