package com.d2c.flame.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.d2c.cms.model.AdResource;
import com.d2c.cms.model.Navigation;
import com.d2c.cms.service.AdResourceService;
import com.d2c.cms.service.NavigationService;
import com.d2c.common.api.response.Response;
import com.d2c.common.api.response.ResponseResult;
import com.d2c.common.api.response.SuccessResponse;
import com.d2c.common.base.exception.BusinessException;
import com.d2c.common.base.exception.NotLoginException;
import com.d2c.common.base.utils.security.Base64Ut;
import com.d2c.common.base.utils.security.D2CSign;
import com.d2c.common.base.utils.security.MD5Util;
import com.d2c.common.core.cache.old.CacheTimerHandler;
import com.d2c.common.mq.enums.MqEnum;
import com.d2c.flame.controller.base.BaseController;
import com.d2c.flame.property.HttpProperties;
import com.d2c.member.dto.MemberDto;
import com.d2c.member.model.Member;
import com.d2c.member.model.MemberInfo;
import com.d2c.member.model.MemberLotto.LotteryOpportunityEnum;
import com.d2c.msg.model.ShareLog;
import com.d2c.msg.model.Template;
import com.d2c.msg.search.model.SearcherMessage;
import com.d2c.msg.search.service.MessageSearcherService;
import com.d2c.msg.search.service.SearchKeySearcherService;
import com.d2c.msg.service.ShareLogService;
import com.d2c.msg.service.TemplateService;
import com.d2c.order.model.Logistics;
import com.d2c.order.model.Logistics.BusinessType;
import com.d2c.order.model.OrderItem;
import com.d2c.order.model.Setting;
import com.d2c.order.service.LogisticsService;
import com.d2c.order.service.OrderItemService;
import com.d2c.order.service.SettingService;
import com.d2c.order.third.kaola.KaolaClient;
import com.d2c.order.third.kaola.reponse.KaolaOrderStatus;
import com.d2c.order.third.kaola.reponse.Track;
import com.d2c.order.third.kaola.reponse.TrackLogistics;
import com.d2c.order.third.payment.alipay.core.pcwap.AlipayBase;
import com.d2c.order.third.payment.alipay.core.pcwap.AlipayCore;
import com.d2c.order.third.payment.alipay.sgin.BASE64;
import com.d2c.product.third.upyun.core.UpYun;

@Controller
@RequestMapping("")
public class HomeController extends BaseController {

	@Reference
	private SearchKeySearcherService searchKeySearcherService;
	@Autowired
	private NavigationService navigationService;
	@Autowired
	private TemplateService templateService;
	@Autowired
	private SettingService settingService;
	@Reference
	private MessageSearcherService messageSearcherService;
	@Autowired
	private LogisticsService logisticsService;
	@Autowired
	private AdResourceService adResourceService;
	@Autowired
	private ShareLogService shareLogService;
	@Autowired
	private OrderItemService orderItemService;

	/**
	 * 可用性测试
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/version", method = RequestMethod.GET)
	public SuccessResponse version() {
		SuccessResponse result = new SuccessResponse();
		String version = VERSION;
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
	 * 404
	 * 
	 * @return
	 */
	@RequestMapping(value = "/404", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String show404() {
		return "error/404";
	}

	/**
	 * 500
	 * 
	 * @return
	 */
	@RequestMapping(value = "/500", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
	public String show500() {
		return "error/500";
	}

	/**
	 * 清除缓存
	 * 
	 * @param key
	 */
	@RequestMapping(value = "/clear/cache", method = RequestMethod.GET)
	public void clearCache(String key) {
		if (key == null) {
			return;
		}
		if (StringUtils.isNotBlank(key)) {
			for (String k : key.split(",")) {
				CacheTimerHandler.removeCache(k);
			}
		}
	}

	/**
	 * 图片上传
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/pic/upload/{policy}", method = RequestMethod.GET)
	public Response upload(@PathVariable String policy) {
		SuccessResponse result = new SuccessResponse();
		String sign = policy + "&" + UpYun.FORM_API_SECRET_D2C_PIC;
		result.put("sign", MD5Util.encodeMD5Hex(sign));
		return result;
	}

	/**
	 * app到wap请求跳转
	 * 
	 * @param d2cBase
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/appToWap", method = RequestMethod.GET)
	public String appToWap(HttpServletResponse response) throws UnsupportedEncodingException {
		// 参数解析
		String urlPath = getRequest().getParameter("params");
		byte[] paramBytes = BASE64.decode(urlPath);
		if (paramBytes == null) {
			logger.error("参数不正确，URL:" + urlPath);
			return "redirect:/index";
		}
		String paramData = new String(BASE64.decode(urlPath), "utf-8");
		Map<String, String> paraMap = new HashMap<>();
		String[] params = paramData.split("@d2c@");
		for (String s : params) {
			String key = s.split("=")[0];
			String value = s.substring(key.length() + 1);
			paraMap.put(key, value);
		}
		// 签名验证
		Map<String, String> map = new HashMap<>();
		String url = paraMap.get("url");
		map.put("appId", paraMap.get("appId"));
		map.put("url", url);
		map.put("token", paraMap.get("token"));
		map.put("timeStamp", paraMap.get("timeStamp"));
		Map<String, String> sPara = AlipayCore.parasFilter(map);
		String mySign = AlipayBase.BuildMysign(sPara, D2CSign.APP_TO_WAP_KEY);
		if (!paraMap.get("sign").equals(mySign)) {
			logger.error("签名不正确，URL:" + urlPath);
			return "redirect:/index";
		}
		String token = paraMap.get("token");
		if (StringUtils.isNotBlank(token) || "0".equals(token)) {
			MemberDto appMember = this.getLoginDto(token);
			if (appMember != null) {
				Cookie tgc = new Cookie(Member.CASTGC, token);
				writeCookie(tgc, response);
			} else {
				// removeCookie(Member.CASTGC, response);
			}
		} else {
			removeCookie(Member.CASTGC, response);
		}
		if (url.indexOf("?") > 0) {
			url = url + "&invoked=1";
		} else {
			url = url + "?invoked=1";
		}
		Cookie uac = new Cookie(HttpProperties.USERAGENT, "1");
		writeCookie(uac, response);
		return "redirect:" + url;
	}

	/**
	 * 导航搜索
	 * 
	 */
	@RequestMapping(value = "c/{code}", method = RequestMethod.GET)
	public String navigation(@PathVariable String code, ModelMap model) {
		Navigation navigation = navigationService.findNavigationByCode(code);
		if (navigation != null) {
			// 导航菜单
			model.put("nav", navigation);
			if (navigation.getParentId() == null) {
				Navigation navParent = navigationService.findById(navigation.getParentId());
				model.put("navParent", navParent == null ? navParent : navParent);
			}
			if (!StringUtils.isEmpty(navigation.getUrl())) {
				return "redirect:" + navigation.getUrl();
			}
		}
		return "redirect:/index";
	}

	/**
	 * wap的导航菜单栏
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/navigation", method = RequestMethod.GET)
	public String renderNavigation(ModelMap model) {
		return "fragment/navigation";
	}

	/**
	 * 站内信
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
	public String message(ModelMap model, @PathVariable Long id) {
		SearcherMessage searcherMessage = messageSearcherService.findById(String.valueOf(id));
		model.put("message", searcherMessage);
		return "society/system_notification";
	}

	/**
	 * 物流查询
	 * 
	 * @param sn
	 * @param com
	 * @param model
	 * @param productPic
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logistics/info", method = RequestMethod.GET)
	public String list(String sn, String com, ModelMap model, String productImg, String ordersn, Long orderItemId)
			throws Exception {
		Logistics logistics = new Logistics();
		if (orderItemId != null) {
			OrderItem item = orderItemService.findById(orderItemId);
			if (item == null) {
				throw new BusinessException("该订单不存在");
			}
			KaolaOrderStatus kaolaOrderStatus = KaolaClient.getInstance()
					.queryOrderStatus(item.getOrderSn() + "-" + item.getWarehouseId());
			for (List<TrackLogistics> list : kaolaOrderStatus.getTrackLogisticss().values()) {
				if (list.get(0) != null) {
					TrackLogistics trackLogistics = list.get(0);
					logistics.setDeliverySn(trackLogistics.getBillno());
					logistics.setStatus(trackLogistics.getState());
					logistics.setDeliveryCode(item.getDeliveryCorpCode());
					logistics.setType(BusinessType.ORDER.name());
					JSONArray array = new JSONArray();
					for (Track track : trackLogistics.getTracks()) {
						JSONObject obj = new JSONObject();
						obj.put("context", track.getContext());
						obj.put("ftime", track.getTimeDetail());
						obj.put("time", track.getTime());
						array.add(obj);
					}
					logistics.setDeliveryInfo(array.toJSONString());
					break;
				}
			}
		} else {
			logistics = logisticsService.findBySnAndCom(sn, com, null);
		}

		JSONObject json = null;
		if (logistics != null) {
			json = (JSONObject) JSON.toJSON(logistics);
			if (!StringUtils.isEmpty(logistics.getDeliveryInfo())) {
				json.put("deliveryInfo", JSON.parse(logistics.getDeliveryInfo(), Feature.OrderedField).toString());
			}
		} else {
			logistics = new Logistics();
			logistics.setDeliverySn(sn);
			logistics.setDeliveryCode(com);
			logistics.setStatus(-1);
			json = (JSONObject) JSON.toJSON(logistics);
		}
		model.put("logistics", json);
		model.put("ordersn", ordersn);
		model.put("productImg", productImg);
		return "order/logistics_detail";
	}

	/**
	 * 广告位展示
	 * 
	 * @param appChannel
	 * @param type
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ad/{appChannel}/{type}", method = RequestMethod.GET)
	public String getType(@PathVariable String appChannel, @PathVariable String type, ModelMap model) {
		AdResource adResource = adResourceService.findByAppChannelAndType(appChannel, type);
		model.put("adResource", adResource);
		return "";
	}

	/**
	 * 通过APP分享链接进入
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/share/in", method = RequestMethod.GET)
	public String shareIn(String param) throws Exception {
		param = Base64Ut.decode(param);
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
			String url = URLDecoder.decode(String.valueOf(map.get("url")), "UTF-8");
			Object parentId = map.get("parent_id");
			if (parentId != null) {
				if (url.indexOf("?") > 0) {
					url = url + "&parent_id=" + String.valueOf(parentId);
				} else {
					url = url + "?parent_id=" + String.valueOf(parentId);
				}
			}
			shareLog.setMemberId(Long.parseLong(map.get("memberId")));
			shareLog.setDevice(String.valueOf(map.get("device")));
			shareLog.setChannel(String.valueOf(map.get("channel")));
			shareLog.setUrl(url);
			shareLog.setDirection(-1);
			shareLogService.insert(shareLog);
			return "redirect:" + url;
		} else {
			throw new BusinessException("您访问的页面不存在！");
		}
	}

	/**
	 * 用户分享日志
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/share/out", method = RequestMethod.POST)
	public String shareOut(ModelMap model, String url) {
		ResponseResult result = new ResponseResult();
		model.put("result", result);
		try {
			MemberInfo memberInfo = this.getLoginMemberInfo();
			// 增加抽奖次数
			Map<String, Object> awardMap = new HashMap<>();
			awardMap.put("memberId", memberInfo.getId());
			awardMap.put("lotteryOpportunityEnum", LotteryOpportunityEnum.SHAREACTIVITY.name());
			MqEnum.AWARD_QUALIFIED.send(awardMap);
		} catch (NotLoginException e) {
		}
		return "";
	}

}
