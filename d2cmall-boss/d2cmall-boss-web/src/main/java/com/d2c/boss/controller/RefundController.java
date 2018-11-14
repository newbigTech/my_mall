package com.d2c.boss.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.d2c.boss.order.model.Refund;
import com.d2c.boss.order.service.RefundService;
import com.d2c.boss.sys.model.ApiLog;
import com.d2c.boss.sys.service.ApiLogService;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.boss.util.JsonHelper;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.common.api.response.Response;
import com.d2c.common.api.response.SuccessResponse;
import com.d2c.common.base.exception.BusinessException;

@Controller
@RequestMapping("/api/crm/refund")
public class RefundController extends BaseController {

	@Autowired
	private RefundService refundService;
	@Autowired
	private ApiLogService apiLogService;

	/**
	 * 发送给Boss refund相关信息
	 * 
	 * @throws BusinessException
	 */
	@Override
	public JSONObject dealDatas(Map<String, Object> resultMap) {
		JSONObject result = new JSONObject();
		PageModel page = new PageModel();
		ProQuery query = new ProQuery();
		ApiLog apiLog = new ApiLog();
		page = (PageModel) resultMap.get("page");
		query = (ProQuery) resultMap.get("query");
		apiLog = (ApiLog) resultMap.get("apiLog");
		try {
			PageResult<Refund> refundList = refundService.findRefundsByQuery(page, query);
			JSONArray datas = new JSONArray();
			result.put("total", refundList.getList().size());// 总条数
			result.put("index", refundList.getPageNumber());// 当前页码
			result.put("pageSize", refundList.getPageSize());// 每页记录条数
			result.put("pageCount", refundList.getPageCount());// 总页数
			for (Refund b : refundList.getList()) {
				datas.add(JsonHelper.instance().toJSONString(b));
			}
			result.put("status", 1);// 是否成功 (1成功，0失败)
			result.put("message", "操作成功！");// 操作信息
			result.put("datas", datas);
			apiLog.setEndDate(new Date());
			apiLog.setStatus(1);
			apiLog.setMessage("查询成功！");
			apiLog.setNum(refundList.getTotalCount());
			apiLogService.insert(apiLog);
		} catch (Exception e) {
			apiLog.setEndDate(new Date());
			apiLog.setStatus(0);
			apiLog.setMessage("获取数据、转换json存在错误，请联系系统管理员！");
			apiLog.setNum(0);
			apiLogService.insert(apiLog);
		}
		return result;
	}

	@Override
	protected Response doInsert(JSONObject data) {
		SuccessResponse result = new SuccessResponse();
		Refund refund = (Refund) JsonHelper.instance().toObject(data, Refund.class);
		refund = refundService.insert(refund);
		return result;
	}

}
