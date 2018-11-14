package com.d2c.msg.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @param <PushMsgToSingleDeviceRequest>
 * @see APP消息推送服务
 *
 */
public interface MsgPushService {

	/**
	 * 个推
	 * 
	 * @param channelId
	 * @param msg
	 * @param url
	 */
	void doMsgPushByGt(String clientId, String msg, String url, String title, String subTitle, String deviceType,
			Integer inMessageType);

	/**
	 * 个推
	 * 
	 * @param channelId
	 * @param msg
	 * @param url
	 */
	void doMsgPushBossByGt(String clientId, String msg, String url, String title, String deviceType);

	/**
	 * 个推发送透传信息到APP
	 * 
	 * @param msg
	 * @param appUrl
	 * @param transmissionType
	 * @param sound
	 * @param offlineExpireTime
	 */
	void doPushTransmissionMsgToApp(JSONObject msg, int transmissionType, long offlineExpireTime, boolean isApnPayLoad,
			String msgType, String deviceType);

	/**
	 * 个推发送透传信息到APP
	 * 
	 * @param msg
	 * @param appUrl
	 * @param transmissionType
	 * @param sound
	 * @param offlineExpireTime
	 */
	void doPushTransmissionMsgToList(List<String> clientIds, JSONObject msg, int transmissionType,
			long offlineExpireTime, boolean isApnPayLoad, String msgType, String deviceType);

}
