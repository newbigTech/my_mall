package com.d2c.order.service.tx;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.codingapi.tx.annotation.TxTransaction;
import com.d2c.common.base.exception.BusinessException;
import com.d2c.member.enums.BusinessTypeEnum;
import com.d2c.member.enums.PayTypeEnum;
import com.d2c.member.support.OrderInfo;
import com.d2c.msg.model.CollageOrderLog;
import com.d2c.msg.model.CollageOrderLog.CollageLogType;
import com.d2c.msg.model.SmsLog.SmsLogType;
import com.d2c.msg.service.CollageOrderLogService;
import com.d2c.msg.service.MsgUniteService;
import com.d2c.msg.support.MsgBean;
import com.d2c.msg.support.SmsBean;
import com.d2c.order.dto.CollageGroupDto;
import com.d2c.order.dto.CollageOrderDto;
import com.d2c.order.enums.PaymentTypeEnum;
import com.d2c.order.model.AccountItem;
import com.d2c.order.model.CollageGroup;
import com.d2c.order.model.CollageOrder;
import com.d2c.order.model.CollageOrder.CollageOrderStatus;
import com.d2c.order.model.CollageOrder.RoleType;
import com.d2c.order.service.CollageGroupService;
import com.d2c.order.service.CollageOrderService;
import com.d2c.product.model.CollagePromotion;
import com.d2c.product.service.CollagePromotionService;
import com.d2c.product.service.ProductSkuService;

@Service(protocol = { "dubbo" })
public class CollageTxServiceImpl implements CollageTxService {

	@Autowired
	private ProductSkuService productSkuService;
	@Autowired
	private CollageOrderService collageOrderService;
	@Autowired
	private CollageOrderLogService collageOrderLogService;
	@Autowired
	private CollageGroupService collageGroupService;
	@Autowired
	private CollagePromotionService collagePromotionService;
	@Reference
	private AccountTxService accountTxService;
	@Autowired
	private MsgUniteService msgUniteService;

	@Override
	@TxTransaction(isStart = true)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CollageOrderDto createCollageOrder(CollageOrder collageOrder) {
		// 判断是否已经存在该团该人非超时关闭记录
		CollageOrder oldCollageOrder = collageOrderService.findByMemberIdAndGroupId(collageOrder.getMemberId(),
				collageOrder.getGroupId());
		// 如果存在不能再次创建
		if (oldCollageOrder == null) {
			// 冻结库存
			productSkuService.doFreezeStock(collageOrder.getProductId(), collageOrder.getSkuId(), 1);
			collageOrder = collageOrderService.insert(collageOrder);

			if (collageOrder.getId() != null && collageOrder.getId() > 0) {
				CollageOrderDto dto = new CollageOrderDto();
				BeanUtils.copyProperties(collageOrder, dto);
				// 更新团的当前参团人数
				int result = collageGroupService.updateCurrentMemberCount(collageOrder.getGroupId(), 1);
				if (result < 1) {
					throw new BusinessException("参团失败");
				} else {
					CollageOrderLog log = new CollageOrderLog(collageOrder.getId(), CollageLogType.CREATE.name(),
							"拼团单创建成功", collageOrder.getLoginCode());
					collageOrderLogService.insert(log);
					return dto;
				}
			}
		}
		return null;
	}

	@Override
	@TxTransaction(isStart = true)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CollageOrderDto createCollageGroup(CollageGroup collageGroup, CollageOrder collageOrder) {
		// 创建团信息
		collageGroup = collageGroupService.insert(collageGroup);
		if (collageGroup.getId() != null && collageGroup.getId() > 0) {
			collageOrder.setType(RoleType.INITIATOR.getCode());
			collageOrder.setGroupId(collageGroup.getId());
			CollageOrderDto dto = this.createCollageOrder(collageOrder);
			if (dto == null) {
				throw new BusinessException("创团失败！");
			} else {
				return dto;
			}
		}
		return null;
	}

	@Override
	@TxTransaction(isStart = true)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int doClose(Long id, String closeMemo) {
		CollageOrder collageOrder = collageOrderService.findById(id);
		int success = collageOrderService.doClose(id, closeMemo);
		if (success > 0) {
			collageGroupService.updateCurrentMemberCount(collageOrder.getGroupId(), -1);
			if (collageOrder.getType().equals(CollageOrder.RoleType.INITIATOR.getCode())) {
				// 如果是团长要另行处理
				CollageGroupDto collageGroupDto = collageGroupService.findDtoById(collageOrder.getGroupId());
				if (collageGroupDto.getCurrentMemberCount() < 1) {
					// 如果现在没人了就直接关掉
					collageGroupService.doFailGroup(collageOrder.getGroupId());
				} else {
					List<CollageOrder> collageOrderList = collageGroupDto.getCollageOrders();
					for (int i = 0; i < collageOrderList.size(); i++) {
						CollageOrder order = collageOrderList.get(i);
						if (order.getStatus().equals(CollageOrder.CollageOrderStatus.PROCESS)
								|| i == collageOrderList.size() - 1) {
							// 先把现在的团长变成团员
							int result = collageOrderService.updateType(id, RoleType.MEMBER.getCode());
							if (result > 0) {
								collageOrderService.updateType(order.getId(), RoleType.INITIATOR.getCode());
								collageGroupService.updateInitiator(order.getGroupId(), order.getMemberId(),
										order.getMemberName(), order.getHeadPic());
								break;
							}
						}
					}
				}
			}
			// 解冻库存
			productSkuService.doFreezeStock(collageOrder.getProductId(), collageOrder.getSkuId(), -1);
			CollageOrderLog log = new CollageOrderLog(id, CollageLogType.CLOSE.name(), "拼团单超时支付关闭", "sys");
			collageOrderLogService.insert(log);
		}
		return success;
	}

	@Override
	@TxTransaction(isStart = false)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int doRefund(Long id) {
		int success = collageOrderService.doRefund(id);
		if (success > 0) {
			CollageOrder order = collageOrderService.findById(id);
			// 解冻库存
			productSkuService.doFreezeStock(order.getProductId(), order.getSkuId(), -1);
			CollageOrderLog log = new CollageOrderLog(id, CollageLogType.WAITFORREFUND.name(), "拼团失败，待退款", "sys");
			collageOrderLogService.insert(log);
			CollagePromotion promotion = collagePromotionService.findById(order.getPromotionId());
			String content = "很遗憾，您参与的拼团活动 '" + promotion.getName()
					+ "' 拼团失败了，系统将按照原路尽快给你退款，可到D2C全球好设计小程序或D2C APP中试试其他商品~";
			MsgBean msgBean = new MsgBean(order.getMemberId(), 61, "很遗憾，您参与的拼团活动失败了", content);
			msgBean.setAppUrl("/mycollage/list");
			msgBean.setPic(order.getProductImage());
			SmsBean smsBean = new SmsBean(null, order.getLoginCode(), SmsLogType.REMIND, content);
			msgUniteService.sendMsg(smsBean, null, msgBean, null);
		}
		return success;
	}

	@Override
	@TxTransaction(isStart = true)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int doFailGroup(Long id) {
		CollageGroup group = collageGroupService.findById(id);
		int success = collageGroupService.doFailGroup(id);
		if (success < 1) {
			throw new BusinessException("团号：" + group.getGroupSn() + "关闭失败！");
		}
		List<CollageOrder> list = collageOrderService.findByGroupId(id);
		for (CollageOrder collageOrder : list) {
			if (collageOrder.getStatus().equals(CollageOrderStatus.PROCESS.getCode())) {
				// 已支付走退款
				success = this.doRefund(collageOrder.getId());
			} else if (collageOrder.getStatus().equals(CollageOrderStatus.WAITFORPAY.getCode())) {
				// 未支付直接关闭
				success = this.doClose(collageOrder.getId(), "团ID:" + collageOrder.getGroupId() + "活动结束未支付关闭");
			}
			if (success < 1) {
				throw new BusinessException("团订单编号：" + collageOrder.getSn() + "关闭失败！");
			}
		}
		return success;
	}

	@Override
	@TxTransaction(isStart = true)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int doBackToWallet(Long id, String operator) {
		CollageOrderDto order = collageOrderService.findDtoById(id);
		if (order == null) {
			throw new BusinessException("该拼团单不存在");
		}
		if (!order.getStatus().equals(CollageOrderStatus.REFUND.getCode())) {
			throw new BusinessException("该拼团单状态不正确");
		}
		if (!order.getPaymentType().equals(PaymentTypeEnum.WALLET.getCode())) {
			throw new BusinessException("该拼团单支付的方式不是钱包");
		}
		OrderInfo bill = new OrderInfo(BusinessTypeEnum.COLLAGE.name(), PayTypeEnum.REFUND.name());
		BeanUtils.copyProperties(order, bill);
		bill.setBillSourceTime(new Date());
		AccountItem item = accountTxService.doRefund(bill);
		return collageOrderService.doRefundSuccess(id, operator, "钱包退款至原账户", PaymentTypeEnum.WALLET.getCode(),
				item.getSn());
	}

}
