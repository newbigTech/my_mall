package com.d2c.common.mq;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.d2c.common.mq.message.MapCreator;
import com.d2c.common.mq.message.ObjectCreator;
import com.d2c.common.mq.message.TextCreator;

public class TopicHandler {

	private static TopicHandler hander;

	@Autowired
	private JmsTemplate topicJmsTemplate;

	@PostConstruct
	public void init() {
		hander = this;
	}

	public static void sendText(final Destination dest, final String text) {
		hander.topicJmsTemplate.send(dest, new TextCreator(text));
	}

	public static void sendText(final Destination dest, final String text, final Long seconds) {
		hander.topicJmsTemplate.send(dest, new TextCreator(text, seconds));
	}

	public static void sendObject(final Destination dest, final Serializable obj) {
		hander.topicJmsTemplate.send(dest, new ObjectCreator(obj));
	}

	public static void sendObject(final Destination dest, final Serializable obj, final Long seconds) {
		hander.topicJmsTemplate.send(dest, new ObjectCreator(obj, seconds));
	}

	public static void sendMap(final Destination dest, final Map<String, Object> map) {
		hander.topicJmsTemplate.send(dest, new MapCreator(map));
	}

	public static void sendMap(final Destination dest, final Map<String, Object> map, final Long seconds) {
		hander.topicJmsTemplate.send(dest, new MapCreator(map, seconds));
	}

}
