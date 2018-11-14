package com.d2c.order.service.tx;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.codingapi.tx.annotation.TxTransaction;
import com.d2c.common.base.exception.BusinessException;
import com.d2c.member.dto.MemberDto;
import com.d2c.order.model.RedPacketsItem;
import com.d2c.order.model.RedPacketsItem.BusinessTypeEnum;
import com.d2c.order.mongo.model.BargainPriceDO;
import com.d2c.order.mongo.service.BargainPriceService;
import com.d2c.order.service.RedPacketsItemService;

@Service(protocol = { "dubbo" })
public class BargainPriceTxServiceImpl implements BargainPriceTxService {

	@Autowired
	private BargainPriceService bargainPriceService;
	@Autowired
	private RedPacketsItemService redPacketsItemService;
	@Reference
	private AccountTxService accountTxService;

	@Override
	@TxTransaction(isStart = true)
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int doRedPackets(MemberDto memberDto) {
		BargainPriceDO bargainPriceDO = bargainPriceService.findByUnionId(memberDto.getUnionId());
		if (bargainPriceDO == null) {
			throw new BusinessException("这个账号还没有帮忙砍价哦");
		}
		List<RedPacketsItem> list = redPacketsItemService.findByTypeAndMember(memberDto.getId(),
				BusinessTypeEnum.BARGAIN.name());
		if (list.size() > 0) {
			throw new BusinessException("砍价活动只能领一次红包，您已经领过了");
		}
		RedPacketsItem redPacketsItem = accountTxService.doSuccessRed(memberDto.getId(), BusinessTypeEnum.BARGAIN.name(),
				new BigDecimal(5));
		if (redPacketsItem.getId() != null) {
			return 1;
		}
		return 0;
	}

}
