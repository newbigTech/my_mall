package com.d2c.member.mongo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.d2c.common.base.exception.BusinessException;
import com.d2c.common.base.utils.AssertUt;
import com.d2c.common.base.utils.BeanUt;
import com.d2c.common.mongodb.base.ListMongoDao;
import com.d2c.member.model.MemberInfo;
import com.d2c.member.model.MemberTask;
import com.d2c.member.mongo.dao.MemberTaskExecMongoDao;
import com.d2c.member.mongo.model.MemberTaskExecDO;
import com.d2c.member.mongo.services.MemberTaskExecService;
import com.d2c.member.service.MemberIntegrationService;
import com.d2c.member.service.MemberTaskService;
import com.d2c.member.view.MemberTaskVO;

/**
 * 用户任务执行情况
 * @author wull
 */
@Service(protocol = "dubbo")
public class MemberTaskExecServiceImpl extends ListMongoDao<MemberTaskExecDO> implements MemberTaskExecService {

	@Autowired
	private MemberTaskExecMongoDao memberTaskExecMongoDao;
	@Autowired
	private MemberTaskService memberTaskService;
	@Autowired
	private MemberIntegrationService memberIntegrationService;
	
	/**
	 * 完成一次我的任务
	 */
	public MemberTaskExecDO taskDone(MemberInfo member, String codeType, String subCode){
		AssertUt.notNull(member);
		String code = MemberTask.buildCode(codeType, subCode);
		AssertUt.notBlank(code, "任务编码不能为空");
		MemberTask task = memberTaskService.findByCode(code);
		if(task == null) {
			throw new BusinessException("任务不存在，请检查");
		}
		MemberTaskExecDO item = memberTaskExecMongoDao.findOne(member.getId(), code);
		if(item == null){
			item = new MemberTaskExecDO(member.getId(), task);
		}else{
			item.syncTask(task);
			if(item.getDone()){
				throw new BusinessException(task + "任务已完成!");
			}
		}
		item.add();
		
		if(item.getDone()){
			memberIntegrationService.addTaskPoint(member, task);
		}
		memberTaskExecMongoDao.save(item);
		return item;
	}
	
	/**
	 * 用户每日任务刷新
	 */
	public long refreshOnDayTask() {
		return memberTaskExecMongoDao.updateDayTask();
	}
	
	/**
	 * 我的任务列表
	 */
	public List<MemberTaskVO> findTaskList(Long memberId){
		List<MemberTask> list = memberTaskService.findTaskList();
		List<MemberTaskExecDO> mys = memberTaskExecMongoDao.findList(memberId);
		List<MemberTaskVO> vl = new ArrayList<>();
		list.forEach(task -> {
			MemberTaskExecDO item = selectTask(mys, task.getCode());
			vl.add(toView(task, item));
		});
		return vl;
	}
	
	private MemberTaskVO toView(MemberTask task, MemberTaskExecDO item){
		MemberTaskVO vo = BeanUt.apply(new MemberTaskVO(), task);
		if(item != null){
			vo = BeanUt.applyIf(vo, item);
		}
		return vo;
	}
	
	private MemberTaskExecDO selectTask(List<MemberTaskExecDO> list, String code){
		if(list == null) return null;
		for(MemberTaskExecDO bean : list){
			if(bean.getCode().equals(code)){
				return bean;
			}
		}
		return null;
	}

}
