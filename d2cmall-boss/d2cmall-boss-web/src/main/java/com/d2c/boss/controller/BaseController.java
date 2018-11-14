package com.d2c.boss.controller;

import java.beans.PropertyEditorSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.d2c.boss.sys.model.ApiLog;
import com.d2c.boss.sys.service.ApiLogService;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.boss.util.Base64;
import com.d2c.boss.util.OauthUtils;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.response.Response;
import com.d2c.common.base.exception.BusinessException;

/**
 * 基础控制层类
 */
@Controller
public class BaseController {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	public static final String SECRET_KEY = "d2cd2c6d7da05cc8874640ba6aac060192d2cd2c";// 签名密匙

	@Autowired
	private ApiLogService apiLogService;

	public String formatDate(Date date) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return fmt.format(date);
	}

	public String getLoginIp(HttpServletRequest request) {
		String userIp = request.getHeader("x-forwarded-for") == null ? request.getRemoteAddr()
				: request.getHeader("x-forwarded-for");
		return userIp;
	}

	@RequestMapping(value = "/insert", method = { RequestMethod.POST })
	public @ResponseBody Response insert(HttpServletRequest request) throws Exception {
		JSONObject data = this.getJSONObject(request);
		Response resp = doInsert(data);
		if (resp.getStatus() > 0 && StringUtils.isEmpty(resp.getMessage())) {
			resp.setMessage("新增成功!");
		}
		return resp;
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String sendListInfo(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap = preCheck(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject result = dealDatas(resultMap);
		sendJson(response, result);
		return null;

	}

	protected JSONObject dealDatas(Map<String, Object> resultMap) {
		return null;

	}

	protected Map<String, Object> preCheck(HttpServletRequest request, HttpServletResponse response)
			throws BusinessException, UnsupportedEncodingException, IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject result = new JSONObject();
		String json = receivePost(request);
		String paramStr = new String(Base64.decode(json));
		Map<String, String> params = new HashMap<String, String>();
		params = OauthUtils.transStringToMap(paramStr);
		// 初始化日志
		ApiLog apiLog = new ApiLog();
		apiLog.setCode(request.getRequestURI());
		apiLog.setBeginDate(new Date());
		apiLog.setParameter(paramStr);
		String partnerCode = params.get("partner");
		apiLog.setName(partnerCode);
		apiLog.setIp(this.getLoginIp(request));

		if (!OauthUtils.checkSign(params)) {// 密匙检验不成功
			apiLog.setEndDate(new Date());
			apiLog.setStatus(0);
			apiLog.setMessage("密匙检验不成功!");
			apiLogService.insert(apiLog);//
			result.put("status", 0);// 是否成功 (1成功，0失败)
			result.put("message", "密匙检验不成功!");// 操作信息
			sendJson(response, result);
			return null;
		}

		Integer pageSize = 500;
		Integer pageNumber = 1;
		Date startModifyTime = new Date();
		Date endModifyTime = new Date();

		try {
			pageSize = Integer.valueOf(params.get("pageSize"));
			pageNumber = Integer.valueOf(params.get("pageNumber"));
			String startTime = params.get("startModifyTime");
			String endTime = params.get("endModifyTime");
			if (startTime == null && endTime == null) {
				apiLog.setEndDate(new Date());
				apiLog.setStatus(0);
				apiLog.setMessage("开始时间及结束时间不能为空！查询失败！");
				apiLogService.insert(apiLog);// (apiLog, request);
				result.put("status", 0);// 是否成功 (1成功，0失败)
				result.put("message", "开始时间及结束时间不能为空！查询失败！");// 操作信息
				sendJson(response, result);
			} else {
				startModifyTime = startTime == null ? null : new Date(Long.valueOf(startTime));
				endModifyTime = endTime == null ? null : new Date(Long.valueOf(endTime));
			}

		} catch (Exception e) {
			apiLog.setEndDate(new Date());
			apiLog.setStatus(0);
			apiLog.setMessage("获取参数失败！查询失败！");
			apiLogService.insert(apiLog);// (apiLog, request);
			result.put("status", 0);// 是否成功 (1成功，0失败)
			result.put("message", "获取参数失败！查询失败！");// 操作信息
			sendJson(response, result);
		}
		PageModel page = new PageModel();
		page.setPageNumber(pageNumber);
		page.setPageSize(pageSize);
		ProQuery query = new ProQuery();
		query.setEndModifyTime(endModifyTime);
		query.setStartModifyTime(startModifyTime);
		resultMap.put("page", page);
		resultMap.put("query", query);
		resultMap.put("apiLog", apiLog);
		return resultMap;

	}

	public JSONObject getJSONObject(HttpServletRequest request) throws Exception {
		String json = receivePost(request);
		JSONObject jsonObj = JSONObject.parseObject(json, Feature.OrderedField);
		// String md5Str1 = jsonObj.getString("md5Str");
		JSONObject jsonObject = jsonObj.getJSONObject("data");
		// String md5Str2 = DigestUtils.md5Hex(jsonObject.toString() +
		// SECRET_KEY);
		// if (!md5Str1.equals(md5Str2)) {// 密匙检验不成功
		// return null;
		// }
		return jsonObject;
	}

	protected Response doInsert(JSONObject data) {
		return null;
	}

	private String receivePost(HttpServletRequest request) throws IOException, UnsupportedEncodingException {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		String reqBody = sb.toString();
		return URLDecoder.decode(reqBody, "utf-8");
	} // 解决当日期为空时,转换错误的问题

	@org.springframework.web.bind.annotation.InitBinder
	protected void InitBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				try {
					if (StringUtils.isNotBlank(value)) {
						setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
					}
				} catch (ParseException e) {
					setValue(null);
				}
			}

			public String getAsText() {
				return new SimpleDateFormat("yyyy-MM-dd").format((Date) getValue());
			}

		});
	}

	public void sendJson(HttpServletResponse response, JSONObject result) {
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(result.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
}
