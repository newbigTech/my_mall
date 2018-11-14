package com.d2c.member.enums;

public enum MemberTaskType {

	DAY_TASK("每日任务"),
	NEW_TASK("新手任务"),
	TIME_TASK("限时任务");

	String display;

	MemberTaskType(String display) {
		this.display = display;
	}

	@Override
	public String toString() {
		return display;
	}
	
}