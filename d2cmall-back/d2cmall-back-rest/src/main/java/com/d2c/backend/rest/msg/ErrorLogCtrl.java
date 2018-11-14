package com.d2c.backend.rest.msg;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.d2c.backend.rest.base.BaseCtrl;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.common.api.response.Response;
import com.d2c.common.api.response.SuccessResponse;
import com.d2c.common.base.utils.BeanUt;
import com.d2c.msg.model.ErrorLog;
import com.d2c.msg.query.ErrorLogSearcher;
import com.d2c.msg.service.ErrorLogService;

@RestController
@RequestMapping("/rest/sys/errorLog")
public class ErrorLogCtrl extends BaseCtrl<ErrorLogSearcher> {

	@Autowired
	private ErrorLogService errorLogService;

	@Override
	protected Response doList(ErrorLogSearcher searcher, PageModel page) {
		BeanUt.trimString(searcher);
		PageResult<ErrorLog> pager = errorLogService.findBySearcher(page, searcher);
		return new SuccessResponse(pager);
	}

	@Override
	protected int count(ErrorLogSearcher searcher) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected String getExportFileType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Map<String, Object>> getRow(ErrorLogSearcher searcher, PageModel page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getExportTitles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Response doHelp(ErrorLogSearcher searcher, PageModel page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Response findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Response doUpdate(Long id, JSONObject data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Response doInsert(JSONObject data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Response doDelete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Response doBatchDelete(Long[] ids) {
		SuccessResponse result = new SuccessResponse();
		int count = 0;
		for (Long id : ids) {
			int success = errorLogService.delete(id);
			if (success > 0) {
				count++;
			}
		}
		result.setMessage("成功删除" + count + "条");
		return result;
	}

}
