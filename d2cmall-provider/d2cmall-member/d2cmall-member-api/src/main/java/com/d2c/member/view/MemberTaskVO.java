package com.d2c.member.view;

import com.d2c.common.api.dto.BaseDTO;

/**
 * 用户任务数据
 * @author wull
 */
public class MemberTaskVO extends BaseDTO {
	
	private static final long serialVersionUID = 3385780775527149555L;

	/**
	 * 任务编码
	 */
	private String code;
	
	/**
	 * 任务名称
	 */
	private String name;

	/**
	 * 任务说明
	 */
	private String explain;
	
	/**
	 * 任务类型
	 */
	private String type;
	
	/**
	 * 服务跳转URL
	 */
	private String url;
	
	/**
	 * 已执行次数
	 */
	private Integer count;
	
	/**
	 * 最大执行次数
	 */
	private Integer max;
	
	/**
	 * 排序
	 */
	private Integer sort;
	
	/**
	 * 是否已完成
	 */
	private Boolean done;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
