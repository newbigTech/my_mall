package com.d2c.flame.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.d2c.common.api.response.ResponseResult;
import com.d2c.common.base.utils.security.Base64Ut;
import com.d2c.common.base.utils.security.MD5Util;
import com.d2c.common.mq.enums.MqEnum;
import com.d2c.flame.controller.base.BaseController;
import com.d2c.member.model.MemberLotto.LotteryOpportunityEnum;
import com.d2c.msg.model.ShareLog;
import com.d2c.msg.model.Template;
import com.d2c.msg.search.service.SearchKeySearcherService;
import com.d2c.msg.service.ShareLogService;
import com.d2c.msg.service.TemplateService;
import com.d2c.order.model.Setting;
import com.d2c.order.service.SettingService;
import com.d2c.product.third.upyun.core.UpYun;

@RestController
@RequestMapping(value = "/v3/api/home")
public class HomeController extends BaseController {

	@Autowired
	private ShareLogService shareLogService;
	@Autowired
	private SettingService settingService;
	@Autowired
	private TemplateService templateService;
	@Reference
	private SearchKeySearcherService searchKeySearcherService;

	/**
	 * 可用性测试
	 * 
	 * @return
	 */
	@RequestMapping(value = "/version", method = RequestMethod.GET)
	public ResponseResult version() {
		ResponseResult result = new ResponseResult();
		String version = "20170504";
		Setting setting = settingService.findByCode(Setting.STOCKSYNC);
		Template template = templateService.findById(1L);
		Set<String> keys = searchKeySearcherService.search("张");
		result.put("shop_db", setting == null ? false : true);
		result.put("log_db", template == null ? false : true);
		result.put("search_connect", keys == null ? false : true);
		result.put("version", version);
		return result;
	}

	/**
	 * 用户分享日志
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/share/out", method = RequestMethod.GET)
	public ResponseResult shareOut(HttpServletRequest request) throws Exception {
		ResponseResult result = new ResponseResult();
		String param = Base64Ut.decode(request.getParameter("param"));
		if (StringUtils.isNotBlank(param)) {
			Map<String, String> map = new HashMap<>();
			String[] params = param.split("&");
			for (String keyvalue : params) {
				String[] pair = keyvalue.split("=");
				if (pair.length == 2) {
					map.put(pair[0], pair[1]);
				}
			}
			ShareLog shareLog = new ShareLog();
			String url = String.valueOf(map.get("url"));
			shareLog.setMemberId(Long.parseLong(map.get("memberId")));
			shareLog.setDevice(String.valueOf(map.get("device")));
			shareLog.setChannel(String.valueOf(map.get("channel")));
			shareLog.setUrl(url);
			shareLog.setDirection(1);
			shareLogService.insert(shareLog);
			// 增加抽奖次数
			Map<String, Object> awardMap = new HashMap<>();
			awardMap.put("memberId", shareLog.getMemberId());
			awardMap.put("lotteryOpportunityEnum", LotteryOpportunityEnum.SHAREACTIVITY.name());
			MqEnum.AWARD_QUALIFIED.send(awardMap);
		}
		return result;
	}

	/**
	 * 图片上传
	 * 
	 * @param policy
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/picture/upload/{policy}", method = RequestMethod.GET)
	public ResponseResult pictureUpload(@PathVariable String policy) throws Exception {
		ResponseResult result = new ResponseResult();
		String sign = policy + "&" + UpYun.FORM_API_SECRET_D2C_PIC;
		result.put("sign", MD5Util.encodeMD5Hex(sign));
		return result;
	}

	/**
	 * 视频上传
	 * 
	 * @param policy
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/vedio/upload/{policy}", method = RequestMethod.GET)
	public ResponseResult vedioUpload(@PathVariable String policy) throws Exception {
		ResponseResult result = new ResponseResult();
		String sign = policy + "&" + UpYun.FORM_API_SECRET_D2C_VEDIO;
		result.put("sign", MD5Util.encodeMD5Hex(sign));
		return result;
	}

}
