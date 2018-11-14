package com.d2c.member.mongo.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.d2c.common.base.utils.StringUt;
import com.d2c.common.mongodb.model.BaseMongoDO;
import com.d2c.member.model.MemberTask;

@Document
public class MemberTaskExecDO extends BaseMongoDO {

	private static final long serialVersionUID = -2582856287391456493L;

	@Indexed
	private Long memberId;

	/**
	 * 任务编码
	 */
	@Indexed
	private String code;

	/**
	 * 任务类型
	 */
	@Indexed
	private String taskType;

	/**
	 * 附送积分
	 */
	private Integer point;
	
	/**
	 * 执行次数
	 */
	private Integer count = 0;
	
	/**
	 * 最大执行次数
	 */
	private Integer maxCount = 0;
	
	/**
	 * 是否已完成
	 */
	private Boolean done = false;
	
	public MemberTaskExecDO() {}
	
	public MemberTaskExecDO(Long memberId, MemberTask task) {
		this.memberId = memberId;
		this.code = task.getCode();
		this.taskType = task.getType();
		this.point = task.getPoint();
		this.maxCount = task.getMax();
	}
	
	/**
	 * 再次同步任务作业
	 */
	public void syncTask(MemberTask task){
		this.maxCount = task.getMax();
		if(count >= maxCount){
			done = true;
		}
	}
	
	void initId(){
		this.id = StringUt.join("_", memberId, code);
	}
	
	public Integer add(){
		count ++;
		if(count >= maxCount){
			done = true;
		}
		return count;
	}
	
	//*************************************

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

}
