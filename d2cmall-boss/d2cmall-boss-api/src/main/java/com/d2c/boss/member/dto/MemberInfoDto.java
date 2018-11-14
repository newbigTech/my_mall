package com.d2c.boss.member.dto;

import java.util.List;

import com.d2c.boss.member.model.Member;
import com.d2c.boss.member.model.MemberInfo;

public class MemberInfoDto extends MemberInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 子账号信息
	 */
	private List<Member> members;

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

}
