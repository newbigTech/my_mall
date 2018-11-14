package com.d2c.backend.rest.cms;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.d2c.backend.rest.base.BaseCtrl;
import com.d2c.cms.model.AppNavigation;
import com.d2c.cms.query.AppNavigationSearcher;
import com.d2c.cms.service.AppNavigationService;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.common.api.response.Response;
import com.d2c.common.api.response.SuccessResponse;
import com.d2c.member.model.Admin;
import com.d2c.util.serial.JsonUtil;

@RestController
@RequestMapping("/rest/cms/appnavigation")
public class AppNavigationCtrl extends BaseCtrl<AppNavigationSearcher> {

	@Autowired
	private AppNavigationService appNavigationService;

	@Override
	protected Response doList(AppNavigationSearcher searcher, PageModel page) {
		PageResult<AppNavigation> pager = appNavigationService.findBySearcher(searcher, page);
		return new SuccessResponse(pager);
	}

	@Override
	protected int count(AppNavigationSearcher searcher) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected String getExportFileType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Map<String, Object>> getRow(AppNavigationSearcher searcher, PageModel page) {
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
	protected Response doHelp(AppNavigationSearcher searcher, PageModel page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Response findById(Long id) {
		SuccessResponse result = new SuccessResponse();
		AppNavigation appNavigation = appNavigationService.findById(id);
		result.put("appNavigation", appNavigation);
		return result;
	}

	@Override
	protected Response doUpdate(Long id, JSONObject data) {
		SuccessResponse result = new SuccessResponse();
		this.getLoginedAdmin();
		AppNavigation appNavigation = JsonUtil.instance().toObject(data, AppNavigation.class);
		int success = appNavigationService.update(appNavigation);
		if (success < 1) {
			result.setStatus(-1);
			result.setMessage("操作不成功");
		}
		return result;
	}

	@Override
	protected Response doInsert(JSONObject data) {
		this.getLoginedAdmin();
		SuccessResponse result = new SuccessResponse();
		AppNavigation appNavigation = JsonUtil.instance().toObject(data, AppNavigation.class);
		appNavigation = appNavigationService.insert(appNavigation);
		result.put("appNavigation", appNavigation);
		return result;
	}

	@Override
	protected Response doDelete(Long id) {
		Admin admin = this.getLoginedAdmin();
		SuccessResponse result = new SuccessResponse();
		int success = appNavigationService.updateStatus(id, -1, admin.getUsername());
		if (success < 1) {
			result.setStatus(-1);
			result.setMessage("删除不成功");
		}
		return result;
	}

	@Override
	protected Response doBatchDelete(Long[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "/mark/{status}", method = RequestMethod.POST)
	public Response mark(@PathVariable Integer status, Long[] ids) {
		SuccessResponse result = new SuccessResponse();
		Admin admin = this.getLoginedAdmin();
		int count = 0;
		for (Long id : ids) {
			int success = appNavigationService.updateStatus(id, status, admin.getUsername());
			if (success > 0) {
				count++;
			}
		}
		result.setMessage("操作成功" + count + "条，失败" + (ids.length - count) + "条");
		return result;
	}

}
