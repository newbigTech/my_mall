package com.d2c.member.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 签到积分奖励
 * 
 * @author wwn
 *
 */
public enum SignRewardEnum {
	FIRST(1, 1), SECOND(2, 2), THIRD(3, 4), FORTH(4, 8), FIFTH(5, 16), SIXTH(6, 32), SEVENTH(7, 64);
	private Integer day;
	private Integer reward;

	private static Map<Integer, Integer> holder = new HashMap<>();

	public static Map<Integer, Integer> getHolder() {
		return holder;
	}

	static {
		for (SignRewardEnum signReward : SignRewardEnum.values()) {
			holder.put(signReward.getDay(), signReward.getReward());
		}
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getReward() {
		return reward;
	}

	public void setReward(Integer reward) {
		this.reward = reward;
	}

	SignRewardEnum(Integer day, Integer reward) {
		this.day = day;
		this.reward = reward;
	}

	public static Integer getRewardByDay(Integer day) {
		return holder.get(day);
	}

}
