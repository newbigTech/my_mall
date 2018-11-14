package com.d2c.flame.controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.d2c.common.api.response.Response;
import com.d2c.common.api.response.ResultHandler;
import com.d2c.common.base.utils.ListUt;
import com.d2c.flame.controller.base.BaseController;
import com.d2c.member.model.MemberInfo;
import com.d2c.member.mongo.model.MemberTaskExecDO;
import com.d2c.member.mongo.services.MemberTaskExecService;
import com.d2c.member.view.MemberTaskVO;

/**
 * 用户任务
 * @author wull
 */
@RestController
@RequestMapping(value = "/v3/api/member/task")
public class MemberTaskController extends BaseController {

	@Autowired
	private MemberTaskExecService memberTaskExecService;
	
	/**
	 * 完成任务调用
	 * API: /v3/api/member/task/done/{codeType}
	 */
	@RequestMapping(value = "/done/{codeType}", method = RequestMethod.GET)
	public Response taskDone(@PathVariable String codeType, String subCode) {
		MemberInfo member = this.getLoginMemberInfo();
		MemberTaskExecDO bean = memberTaskExecService.taskDone(member, codeType, subCode);
		return ResultHandler.success(bean);
	}
	
	/**
	 * 我的任务
	 * API: /v3/api/member/task/list
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Response taskList() {
		MemberInfo member = this.getLoginMemberInfo();
		List<MemberTaskVO> list = memberTaskExecService.findTaskList(member.getId());
		return ResultHandler.success(ListUt.groupToMap(list, "type"));
	}

}
