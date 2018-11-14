package com.d2c.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codingapi.tx.annotation.TxTransaction;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.member.model.Partner;
import com.d2c.member.service.PartnerService;
import com.d2c.msg.service.MsgUniteService;
import com.d2c.msg.service.WeixinPushService;
import com.d2c.msg.support.MsgBean;
import com.d2c.msg.support.PushBean;
import com.d2c.msg.third.wechat.WeixinPushEntity;
import com.d2c.mybatis.service.ListServiceImpl;
import com.d2c.order.dao.PartnerItemMapper;
import com.d2c.order.model.PartnerItem;
import com.d2c.order.model.PartnerItem.PartnerLogType;
import com.d2c.order.query.PartnerItemSearcher;

@Service("partnerItemService")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class PartnerItemServiceImpl extends ListServiceImpl<PartnerItem> implements PartnerItemService {

	@Autowired
	private PartnerItemMapper partnerItemMapper;
	@Autowired
	private PartnerService partnerService;
	@Autowired
	private WeixinPushService weixinPushService;
	@Autowired
	private MsgUniteService msgUniteService;

	@Override
	public PageResult<PartnerItem> findBySearcher(PartnerItemSearcher searcher, PageModel page) {
		PageResult<PartnerItem> pager = new PageResult<PartnerItem>(page);
		Integer totalCount = partnerItemMapper.countBySearcher(searcher);
		List<PartnerItem> list = new ArrayList<PartnerItem>();
		if (totalCount > 0) {
			list = partnerItemMapper.findBySearcher(searcher, page);
		}
		pager.setTotalCount(totalCount);
		pager.setList(list);
		return pager;
	}

	@Override
	@TxTransaction
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public PartnerItem insert(PartnerItem partnerLog) {
		partnerLog = this.save(partnerLog);
		if (partnerLog.getId() > 0) {
			Partner partner = partnerService.findById(partnerLog.getPartnerId());
			String sourceSn = (partnerLog.getSourceSn() == null ? "" : partnerLog.getSourceSn());
			WeixinPushEntity msgObj = new WeixinPushEntity(partner.getOpenId(),
					PartnerLogType.valueOf(partnerLog.getSourceType()).getDisplay(), partnerLog.getCreateDate(),
					partnerLog.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP), partnerLog.getDirection(),
					partner.getTotalAmount().subtract(partner.getCashAmount()).subtract(partner.getApplyAmount())
							.setScale(2, BigDecimal.ROUND_HALF_UP),
					PartnerLogType.valueOf(partnerLog.getSourceType()).getDesc() + sourceSn, "/pages/center/index");
			weixinPushService.send(msgObj);
			PushBean pushBean = new PushBean(partner.getMemberId(),
					"个人账户已" + (1 == partnerLog.getDirection() ? "到账¥" : "扣减¥")
							+ partnerLog.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "，业务类型："
							+ (partnerLog.getSourceSn() != null
									? PartnerLogType.valueOf(partnerLog.getSourceType()).getDisplay() : "其他")
							+ "，点击查看~",
					83);
			pushBean.setAppUrl("/to/partner/account/item");
			MsgBean msgBean = new MsgBean(partner.getMemberId(), 83, "账户流水",
					"个人账户已" + (1 == partnerLog.getDirection() ? "到账¥" : "扣减¥")
							+ partnerLog.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "，业务类型："
							+ (partnerLog.getSourceSn() != null
									? PartnerLogType.valueOf(partnerLog.getSourceType()).getDisplay() : "其他")
							+ "，点击查看~");
			msgBean.setAppUrl("/to/partner/account/item");
			msgUniteService.sendPush(pushBean, msgBean);
		}
		return partnerLog;
	}

	@Override
	public List<Map<String, Object>> findSummaryByType(Long partnerId) {
		return partnerItemMapper.findSummaryByType(partnerId);
	}

}
