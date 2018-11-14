package com.d2c.msg.third.push.common;

import com.gexin.rp.sdk.http.IGtPush;

public class GtPushUtil extends IGtPush {

	public GtPushUtil(String appKey, String masterSecret) {
		super(appKey, masterSecret);
	}

	private static IGtPush push;

	public static String appId = "7hMGYcZXnP8mtDL8HHLZU6";
	public static String appKey = "ruoORCobkiAHtvizaAWHe7";
	public static String masterSecret = "tbhdKoItyu8KM45r1RHh35";

	public synchronized static IGtPush getInstance() {
		if (push == null) {
			try {
				push = new IGtPush(appKey, masterSecret);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return push;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		GtPushUtil.appId = appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		GtPushUtil.appKey = appKey;
	}

	public String getMasterSecret() {
		return masterSecret;
	}

	public void setMasterSecret(String masterSecret) {
		GtPushUtil.masterSecret = masterSecret;
	}

}
