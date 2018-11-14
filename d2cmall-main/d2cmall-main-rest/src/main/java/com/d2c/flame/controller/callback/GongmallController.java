package com.d2c.flame.controller.callback;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.d2c.common.api.response.ResponseResult;
import com.d2c.flame.controller.base.BaseController;
import com.d2c.member.model.Partner;
import com.d2c.member.service.PartnerService;
import com.d2c.order.model.PartnerCash;
import com.d2c.order.service.PartnerCashService;
import com.d2c.order.service.tx.PartnerTxService;
import com.d2c.order.third.payment.gongmall.core.GongmallConfig;
import com.d2c.order.third.payment.gongmall.sign.SignHelper;

/**
 * 工猫通知回调
 */
@RestController
@RequestMapping("/gongmall")
public class GongmallController extends BaseController {

	@Autowired
	private PartnerService partnerService;
	@Autowired
	private PartnerCashService partnerCashService;
	@Autowired
	private GongmallConfig gongmallConfig;
	@Reference
	private PartnerTxService partnerTxService;

	/**
	 * 电子合同回调
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws FileUploadException
	 */
	@RequestMapping(value = "/employee/notify", method = RequestMethod.POST)
	public ResponseResult employeeNotify(HttpServletRequest request, HttpServletResponse response)
			throws FileUploadException, UnsupportedEncodingException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = upload.parseRequest(request);
		Map<String, String> param = new HashMap<String, String>();
		for (FileItem item : items) {
			if (item.isFormField()) {
				param.put(item.getFieldName(), item.getString("utf-8"));
			}
		}
		String signStr = param.get("sign");
		param.remove("sign");
		String status = param.get("status");
		String mobile = param.get("mobile");
		String sign = SignHelper.getSign(param, gongmallConfig.getAppSecret());
		if (signStr.equals(sign)) {
			if (status.equals("2")) {
				Partner partner = partnerService.findByLoginCode(mobile);
				if (partner != null && partner.getContract() == 0) {
					partnerService.doContract(partner.getId(), 1);
				}
			}
		}
		return new ResponseResult();
	}

	/**
	 * 提现结果回调
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws FileUploadException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/withdraw/notify", method = RequestMethod.POST)
	public ResponseResult withdrawNotify(HttpServletRequest request, HttpServletResponse response)
			throws FileUploadException, UnsupportedEncodingException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = upload.parseRequest(request);
		Map<String, String> param = new HashMap<String, String>();
		for (FileItem item : items) {
			if (item.isFormField()) {
				param.put(item.getFieldName(), item.getString("utf-8"));
			}
		}
		String signStr = param.get("sign");
		param.remove("sign");
		String status = param.get("status");
		String requestId = param.get("requestId");
		String amount = param.get("amount");
		String sign = SignHelper.getSign(param, gongmallConfig.getAppSecret());
		if (signStr.equals(sign)) {
			if (status.equals("1")) {
				PartnerCash cash = partnerCashService.findBySn(requestId);
				if (cash != null && cash.getStatus() != 8
						&& cash.getApplyTaxAmount().intValue() == new BigDecimal(amount).intValue()) {
					partnerTxService.doPayment(cash.getId(), cash.getSn(), "系统", new Date(), "工猫回调");
				}
			}
		}
		return new ResponseResult();
	}

}
