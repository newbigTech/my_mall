package com.d2c.order.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codingapi.tx.annotation.TxTransaction;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.common.base.exception.BusinessException;
import com.d2c.common.core.annotation.AsyncMethod;
import com.d2c.member.model.MemberInfo;
import com.d2c.member.model.Partner;
import com.d2c.member.service.MemberInfoService;
import com.d2c.member.service.PartnerService;
import com.d2c.msg.model.OrderLog;
import com.d2c.msg.model.OrderLog.OrderLogType;
import com.d2c.msg.service.MsgUniteService;
import com.d2c.msg.service.OrderLogService;
import com.d2c.msg.support.MsgBean;
import com.d2c.msg.support.PushBean;
import com.d2c.mybatis.service.ListServiceImpl;
import com.d2c.order.dao.OrderMapper;
import com.d2c.order.dto.AddressDto;
import com.d2c.order.dto.CollageOrderDto;
import com.d2c.order.dto.OrderDto;
import com.d2c.order.dto.OrderItemDto;
import com.d2c.order.enums.PaymentTypeEnum;
import com.d2c.order.model.AccountItem;
import com.d2c.order.model.CollageOrder;
import com.d2c.order.model.Coupon;
import com.d2c.order.model.Order;
import com.d2c.order.model.Order.OrderStatus;
import com.d2c.order.model.Order.OrderTerminal;
import com.d2c.order.model.Order.OrderType;
import com.d2c.order.model.OrderItem;
import com.d2c.order.model.OrderItem.ItemStatus;
import com.d2c.order.model.RequisitionItem;
import com.d2c.order.model.RequisitionItem.ItemType;
import com.d2c.order.model.Setting;
import com.d2c.order.model.base.IAddressInterface;
import com.d2c.order.model.base.IMemberInterface;
import com.d2c.order.mongo.service.BargainPriceService;
import com.d2c.order.query.RequisitionItemSearcher;
import com.d2c.order.third.kaola.KaolaClient;
import com.d2c.product.model.Product;
import com.d2c.product.model.Product.ProductSource;
import com.d2c.product.model.ProductSku;
import com.d2c.product.service.ProductService;
import com.d2c.product.service.ProductSkuService;
import com.d2c.util.string.RandomUtil;

@Service("orderService")
@Transactional(readOnly = true, rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
public class OrderServiceImpl extends ListServiceImpl<Order> implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private OrderLogService orderLogService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private AccountItemService accountItemService;
	@Autowired
	private PartnerBillService partnerBillService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductSkuService productSkuService;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private MsgUniteService msgUniteService;
	@Autowired
	private ReshipService reshipService;
	@Autowired
	private RefundService refundService;
	@Autowired
	private ExchangeService exchangeService;
	@Autowired
	private SettingService settingService;
	@Autowired
	private CouponQueryService couponQueryService;
	@Autowired
	private RequisitionItemService requisitionItemService;
	@Autowired
	private RedPacketsItemService redPacketsItemService;
	@Autowired
	private PartnerService partnerService;
	@Autowired
	private BargainPriceService bargainPriceService;

	/**
	 * 记录订单日志
	 * 
	 * @param orderId
	 * @param logType
	 * @param operator
	 * @param info
	 */
	private void createOrderLog(Long orderId, OrderLogType logType, String operator, String info) {
		OrderLog orderLog = new OrderLog();
		orderLog.setOrderId(orderId);
		orderLog.setCreator(operator);
		orderLog.setCreateDate(new Date());
		orderLog.setInfo(info);
		orderLog.setOrderLogType(logType);
		try {
			orderLogService.insert(orderLog);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public Order findByOrderSn(String orderSn) {
		return orderMapper.findByOrderSn(orderSn);
	}

	@Override
	public Order findById(Long id) {
		return findOneById(id);
	}

	@Override
	public OrderDto findByTempId(String tempId) {
		if (StringUtils.isBlank(tempId)) {
			return null;
		}
		Order order = orderMapper.findByTempId(tempId);
		if (order == null) {
			return null;
		}
		OrderDto dto = new OrderDto();
		BeanUtils.copyProperties(order, dto);
		List<OrderItem> orderItems = orderItemService.findByOrderId(order.getId());
		List<OrderItemDto> itemDtos = new ArrayList<>();
		for (OrderItem item : orderItems) {
			OrderItemDto temp = new OrderItemDto();
			BeanUtils.copyProperties(item, temp);
			itemDtos.add(temp);
		}
		dto.setOrderItems(itemDtos);
		return dto;
	}

	@Override
	public OrderDto findOrderDtoById(Long id) {
		Order order = findById(id);
		if (order == null) {
			return null;
		}
		OrderDto dto = new OrderDto();
		BeanUtils.copyProperties(order, dto);
		dto.setPayment(paymentService.findById(dto.getPaymentId()));
		List<OrderItem> orderItems = orderItemService.findByOrderId(order.getId());
		List<OrderItemDto> itemDtos = new ArrayList<>();
		for (OrderItem item : orderItems) {
			OrderItemDto temp = new OrderItemDto();
			BeanUtils.copyProperties(item, temp);
			itemDtos.add(temp);
		}
		dto.setOrderItems(itemDtos);
		return dto;
	}

	@Override
	public List<Long> findRemindOrder(long time1, long time2) {
		return orderMapper.findRemindOrder(time1, time2);
	}

	@Override
	public PageResult<Map<String, Object>> findPartnerOrder(Date endDate, PageModel page) {
		PageResult<Map<String, Object>> pager = new PageResult<>(page);
		int totalCount = orderMapper.countPartnerOrder(endDate);
		List<Map<String, Object>> list = new ArrayList<>();
		if (totalCount > 0) {
			list = orderMapper.findPartnerOrder(endDate, page);
		}
		pager.setTotalCount(totalCount);
		pager.setList(list);
		return pager;
	}

	@Override
	public OrderDto doFindByOrderSnAndMemberInfoId(String orderSn, Long memberInfoId) {
		Order order = orderMapper.findByOrderSnAndMemberInfoId(orderSn, memberInfoId);
		OrderDto dto = convertDto(order);
		return dto;
	}

	@Override
	public OrderDto doFindByIdAndMemberInfoId(Long orderId, Long memberInfoId) {
		Order order = orderMapper.findByIdAndMemberInfoId(orderId, memberInfoId);
		OrderDto dto = convertDto(order);
		return dto;
	}

	private OrderDto convertDto(Order order) {
		if (order == null)
			return null;
		OrderDto dto = new OrderDto();
		BeanUtils.copyProperties(order, dto);
		// 默认设置支持货到付款
		dto.setCod(1);
		Setting setting = this.settingService.findByCode(Setting.ORDERCLOSECODE);
		dto.setCloseTime(Integer.valueOf(Setting.defaultValue(setting, "86400").toString()));
		List<OrderItem> orderItems = this.orderItemService.findByOrderId(order.getId());
		List<OrderItemDto> itemDtos = new ArrayList<>();
		for (OrderItem item : orderItems) {
			// 如果订单中存在不支持货到付款的明细，整个订单不支持货到付款
			if (item.getCod() != 1) {
				dto.setCod(0);
			}
			OrderItemDto temp = new OrderItemDto();
			BeanUtils.copyProperties(item, temp);
			if (item.getReshipId() != null) {
				temp.setReship(reshipService.findById(item.getReshipId()));
			}
			if (item.getRefundId() != null) {
				temp.setRefund(refundService.findById(item.getRefundId()));
			}
			if (item.getExchangeId() != null) {
				temp.setExchange(exchangeService.findById(item.getExchangeId()));
			}
			itemDtos.add(temp);
		}
		dto.setOrderItems(itemDtos);
		return dto;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public OrderDto doInitPartner(MemberInfo memberInfo, OrderDto orderDto, Long parent_id, Integer level,
			boolean qualified) {
		// 礼包商品
		if (qualified) {
			Partner old = partnerService.findByMemberId(memberInfo.getId());
			if (old == null && parent_id != null) {
				// 首次开店
				orderDto.setTerminal(OrderTerminal.Partner.name());
				orderDto.setTerminalId("first:" + parent_id + ":" + (level == null ? 2 : level));
			} else if (old != null) {
				// 二次购买
				orderDto.setTerminal(OrderTerminal.Partner.name());
				orderDto.setTerminalId("gift:" + old.getId());
			}
			return orderDto;
		}
		// 普通商品
		Long partnerId = parent_id;
		if (memberInfo.getPartnerId() != null) {
			// 分销自买
			partnerId = memberInfo.getPartnerId();
		} else if (memberInfo.getParentId() != null) {
			// 推荐购买
			partnerId = memberInfo.getParentId();
		}
		if (partnerId == null) {
			return orderDto;
		}
		Partner partner = partnerService.findById(partnerId);
		if (partner != null && partner.getStatus() >= 0) {
			// 设置返利关系
			orderDto.setPartnerId(partner.getId());
			orderDto.setTerminal(OrderTerminal.Partner.name());
			Long masterId = null;
			Long superId = null;
			Long parentId = null;
			switch (partner.getLevel()) {
			case 0:
				// 直接返利是AM
				partnerId = partner.getId();
				break;
			case 1:
				// 直接返利是DM
				partnerId = partner.getId();
				masterId = partner.getMasterId();
				Partner super1 = this.searchPartner(partner.getParentId(), 1);
				superId = (super1 != null ? super1.getId() : null);
				break;
			case 2:
				// 直接返利是买手
				partnerId = partner.getId();
				masterId = partner.getMasterId();
				Partner parent = this.searchPartner(partner.getParentId(), 1);
				parentId = (parent != null ? parent.getId() : null);
				if (parent != null) {
					Partner super2 = this.searchPartner(parent.getParentId(), 1);
					superId = (super2 != null ? super2.getId() : null);
				}
				break;
			default:
				break;
			}
			for (OrderItemDto orderItem : orderDto.getOrderItems()) {
				orderItem.setPartnerId(partnerId);
				orderItem.setParentId(parentId);
				orderItem.setSuperId(superId);
				orderItem.setMasterId(masterId);
			}
			// 设置下级会员
			if (memberInfo.getParentId() == null && memberInfo.getPartnerId() == null) {
				memberInfoService.doBindParent(memberInfo.getId(), parent_id);
			}
		}
		return orderDto;
	}

	private Partner searchPartner(Long id, int level) {
		if (id == null) {
			return null;
		}
		Partner p = partnerService.findById(id);
		if (p != null && p.checkAvailable() && p.getLevel() == level) {
			return p;
		}
		return null;
	}

	@Override
	@TxTransaction
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Order insert(Order order) {
		order = this.save(order);
		createOrderLog(order.getId(), OrderLogType.WaitingForPay, order.getLastModifyMan(), "创建订单成功");
		return order;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int delete(Long orderId, String operator) {
		Order order = this.findById(orderId);
		OrderStatus status = OrderStatus.getStatus(order.getOrderStatus());
		int success = 0;
		if (status.equals(OrderStatus.Initial) || status.equals(OrderStatus.UserClose)
				|| status.equals(OrderStatus.MallClose)) {
			if (order.getId() != null) {
				success = orderMapper.deleteById(order.getId());
				order.setDeleted(1);
				this.createOrderLog(order.getId(), OrderLogType.Delete, operator, "删除订单");
			}
		}
		return success;
	}

	/**
	 * 直接修改订单状态
	 * 
	 * @param orderId
	 * @param oldOrderStatus
	 * @param orderStatus
	 * @param operator
	 */
	@Override
	@TxTransaction
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public int updateOrderStatus(Long orderId, OrderStatus oldOrderStatus, OrderStatus orderStatus, String operator) {
		int result = orderMapper.updateOrderStatus(orderId, orderStatus);
		// 记录订单日志
		createOrderLog(orderId, OrderLogType.Update, operator,
				"修改订单状态：旧：" + oldOrderStatus.getName() + "，新：" + orderStatus.getName());
		return result;
	}

	/**
	 * 修改收货地址
	 * 
	 * @param orderId
	 * @param contact
	 * @param province
	 * @param city
	 * @param address
	 * @param receiver
	 * @param lastMan
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public int updateOrderShippingAddress(Long orderId, String contact, String province, String city, String address,
			String receiver, String lastMan) {
		Order order = this.findById(orderId);
		int result = 0;
		if (order.getOrderStatus() < 4) {
			result = orderMapper.updateOrderShippingAddress(orderId, contact, province, city, "", address, receiver);
			RequisitionItemSearcher searcher = new RequisitionItemSearcher();
			searcher.setOrderSn(order.getOrderSn());
			PageModel pager = new PageModel();
			pager.setPageSize(100);
			PageResult<RequisitionItem> items = requisitionItemService.findBySearcher(searcher, pager);
			String newAddress = province + " " + city + " " + address;
			for (RequisitionItem item : items.getList()) {
				if (item.getType() == ItemType.STOREALLC.getCode()
						|| (item.getType() == ItemType.ORDERPCH.getCode() && item.getDirect() == 1)) {
					if (item.getStatus() < 3) {
						result = requisitionItemService.updateLogisticAddress(item.getId(), receiver, contact,
								newAddress, lastMan);
					}
				}
			}
			if (result > 0) {
				createOrderLog(orderId, OrderLogType.Update, lastMan,
						"收货信息 原始：" + order.getDeliveryAddress() + "  " + order.getReciver() + "  " + order.getContact()
								+ "，收货信息  新：" + province + city + address + "  " + receiver + "  " + contact);
			}
		}
		return result;
	}

	/**
	 * 修改客服备注
	 * 
	 * @param orderId
	 * @param adminMemo
	 * @param lastMan
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public int updateAdminMemo(Long orderId, String adminMemo, String lastMan) {
		Order order = this.findById(orderId);
		int result = orderMapper.updateAdminMemo(orderId, adminMemo);
		createOrderLog(orderId, OrderLogType.Update, lastMan,
				"客服备注修改 原始：" + (order.getAdminMemo() == null ? "" : order.getAdminMemo()) + "，客服备注修改 新：" + adminMemo);
		return result;
	}

	@Override
	@TxTransaction
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public int updateCloseInfo(Long id, String closeReason, String orderCloseMan, int orderStatus) {
		return orderMapper.updateCloseInfo(id, closeReason, orderCloseMan, orderStatus);
	}

	/**
	 * 根据物流编号更新订单
	 * 
	 * @param deliverySn
	 * @param orderStatus
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public int updateOrderStatusByDeliverySn(String deliverySn, OrderStatus orderStatus) {
		return orderMapper.updateOrderStatusByDeliverySn(deliverySn, orderStatus);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public int updateFlag(Long id, int flag) {
		return orderMapper.updateFlag(id, flag);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public int updateInvoiced(Long id, int invoiced, String operator) {
		int success = orderMapper.updateInvoiced(id, invoiced, operator);
		createOrderLog(id, OrderLogType.Invoiced, operator, "订单开票");
		return success;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public OrderDto updateTempOrder(Long orderId, Long addressId, String code, String memo, Long memberInfoId) {
		Order oldOrder = this.findById(orderId);
		if (oldOrder.getOrderStatus() == 1) {
			// 订单确认页进入付款页，优惠券锁定
			// 防止付款页点击返回进入确认页，优惠券消耗两次
			couponService.doUnlockCoupon(oldOrder.getId());
			// 重新设置优惠券
			Coupon coupon = couponQueryService.findByCode(code, memberInfoId);
			BigDecimal couponAmount = couponService.doLockCoupon(oldOrder.getId(), oldOrder.getOrderSn(),
					oldOrder.getTotalAmount(), code);
			if (coupon != null) {
				orderMapper.updateCouponAmount(oldOrder.getId(), coupon.getId(), couponAmount);
			}
			// 重新设置收货地址和备注
			AddressDto address = addressService.findById(addressId);
			oldOrder.setReciverInfo(address);
			oldOrder.setMemo(memo);
			oldOrder.setLastModifyMan(memberInfoId + "");
			orderMapper.updateOrderReciverInfoAndMemo(oldOrder);
			// 记录订单日志
			DecimalFormat df = new DecimalFormat("0.00");
			String oldInfo = "优惠：" + df.format(oldOrder.getCouponAmount()) + "地址：" + this.getReceiverInfo(oldOrder)
					+ "备注：" + oldOrder.getMemo();
			String newInfo = "优惠：" + df.format(couponAmount) + "地址：" + address.toString() + "备注：" + oldOrder.getMemo();
			createOrderLog(oldOrder.getId(), OrderLogType.Update,
					oldOrder.getLoginCode() != null ? oldOrder.getLoginCode() : memberInfoId + "",
					"修改订单信息：旧：" + oldInfo + "，新：" + newInfo);
			OrderDto newOrderDto = this.findOrderDtoById(orderId);
			return newOrderDto;
		} else {
			return null;
		}
	}

	@Override
	@TxTransaction
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int updateCouponAmount(Long orderId, Long couponId, BigDecimal couponAmount) {
		return orderMapper.updateCouponAmount(orderId, couponId, couponAmount);
	}

	private String getReceiverInfo(Order order) {
		StringBuilder sb = new StringBuilder();
		sb.append("收货信息：");
		String reciver = order.getReciver() == null ? "" : order.getReciver();
		String contact = order.getContact() == null ? "" : order.getContact();
		String address = order.getAddress() == null ? "" : order.getAddress();
		String province = order.getProvince() == null ? "" : order.getProvince();
		String city = order.getCity() == null ? "" : order.getCity();
		sb.append(province).append(city).append(address).append(reciver).append(contact);
		return sb.toString();
	}

	@Override
	@TxTransaction
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int doPaySuccess(String orderSn, Long paymentId, String paySn, Integer paymentType, BigDecimal payedAmount) {
		Order order = orderMapper.findByOrderSn(orderSn);
		if (order == null) {
			logger.error("[Order] 订单：" + orderSn + "不存在！");
			return 0;
		}
		if (order.getOrderStatus().intValue() > OrderStatus.WaitingForPay.getCode()) {
			logger.error("[Order] 订单：" + orderSn + "已经支付成功了！");
			return 0;
		}
		int success = 0;
		if (order.getTotalPay().intValue() == payedAmount.intValue()) {
			order.setPaidAmount(payedAmount);
			order.setPaymentId(paymentId);
			order.setPaymentType(paymentType);
			order.setPaymentSn(paySn);
			// 订单明细
			List<OrderItem> orderItems = orderItemService.findByOrderSn(orderSn);
			// 订单支付成功
			success = orderMapper.updatePaySuccess(order.getId(), paymentId, paymentType, paySn, payedAmount);
			// 拆分钱包金额
			if (order.getPaymentType().equals(PaymentTypeEnum.WALLET.getCode())) {
				this.splitAccount(paySn, orderItems);
			}
			// 订单明细支付成功
			orderItemService.doPaySuccess(order, orderItems);
			// 核销红包
			if (order.getRedId() != null)
				redPacketsItemService.updateStatus(order.getRedId(), 8);
			// 核销优惠券
			if (order.getCouponId() != null)
				couponService.doSuccessCoupon(order.getCouponId());
			// 分销订单处理
			if (order.getTerminal() != null && order.getTerminal().equals(OrderTerminal.Partner.name())) {
				partnerBillService.doHandlePartner(order, orderItems);
			}
			// 砍价订单处理
			if (order.getType().equals(OrderType.bargain.name()) && order.getBargainId() != null) {
				bargainPriceService.updateBargainSuccess(String.valueOf(order.getBargainId()));
			}
			// 记录订单日志
			createOrderLog(order.getId(), OrderLogType.WaitingForDelivery, "支付回调",
					"支付成功，支付方式：" + PaymentTypeEnum.getByCode(paymentType).getDisplay() + "，支付编号：" + paySn);
			// @Async-店主零售签收
			if (order.getType().equals(OrderType.distribution.name())) {
				orderItemService.doDplusSign(orderItems);
			}
		}
		return success;
	}

	/**
	 * 拆分钱包金额
	 * 
	 * @param order
	 * @param orderItems
	 */
	private void splitAccount(String paySn, List<OrderItem> orderItems) {
		AccountItem bill = accountItemService.findBySn(paySn);
		if (bill == null) {
			return;
		}
		if (orderItems.size() == 1) {
			OrderItem orderItem = orderItems.get(0);
			// 只有一条，直接取，否则会出现小数点
			orderItem.setCashAmount(bill.getAmount());
			orderItem.setGiftAmount(bill.getGiftAmount());
		} else {
			// 计算所有可用商品的拆分金额
			BigDecimal splitAmount = new BigDecimal(0);
			BigDecimal splitGiftAmount = new BigDecimal(0);
			for (int i = 0; i < orderItems.size(); i++) {
				OrderItem orderItem = orderItems.get(i);
				if (i == orderItems.size() - 1) {
					orderItem.setCashAmount(bill.getAmount().subtract(splitAmount));
					orderItem.setGiftAmount(bill.getGiftAmount().subtract(splitGiftAmount));
				} else {
					BigDecimal cashAmount = this.rateCurrency(bill.getAmount(), bill.getSubTotal(),
							orderItem.getActualAmount());
					BigDecimal giftAmount = orderItem.getActualAmount().subtract(cashAmount);
					orderItem.setCashAmount(cashAmount);
					orderItem.setGiftAmount(giftAmount);
					splitAmount = splitAmount.add(cashAmount);
					splitGiftAmount = splitGiftAmount.add(giftAmount);
				}
			}
		}
	}

	/**
	 * 金额百分比计算
	 * 
	 * @param availableAmount
	 * @param totalAmount
	 * @param outAmount
	 * @return
	 */
	private BigDecimal rateCurrency(BigDecimal availableAmount, BigDecimal totalAmount, BigDecimal outAmount) {
		BigDecimal cashAmount = availableAmount.multiply(outAmount).multiply(new BigDecimal("10000"))
				.divide(totalAmount.multiply(new BigDecimal("100")), 0, RoundingMode.HALF_UP)
				.divide(new BigDecimal("100"));
		return cashAmount;
	}

	/**
	 * 整单发货
	 * 
	 * @param orderId
	 * @param deliveryCorpName
	 * @param deliverySn
	 * @param deliverMan
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public int doDeliverOrder(Long orderId, String deliveryCorpName, String deliverySn, String deliverMan, String ip) {
		int success = 0;
		if (StringUtils.isEmpty(deliveryCorpName)) {
			throw new BusinessException("物流公司不能为空");
		}
		if (StringUtils.isEmpty(deliverySn)) {
			throw new BusinessException("物流单号不能为空");
		}
		List<OrderItemDto> orderItems = orderItemService.findDtoByOrderId(orderId);
		OrderItem lastItem = null;
		StringBuilder productName = new StringBuilder();
		for (OrderItemDto orderItem : orderItems) {
			if (orderItem.getStatus().equals(OrderItem.ItemStatus.NORMAL.name()) && orderItem.getRequisition() < 1
					&& "none".equalsIgnoreCase(orderItem.getAftering())) {
				int result = orderItemService.doDeliveryItem(orderItem.getId(), 0,
						StringUtils.isNotBlank(orderItem.getDeliveryBarCode()) ? orderItem.getDeliveryBarCode()
								: orderItem.getProductSkuSn(),
						deliveryCorpName, deliverySn, deliverMan, false, orderItem.getProductQuantity());
				productName.append("," + orderItem.getProductName());
				if (result > 0) {
					success = result;
				}
			}
			lastItem = orderItem;
		}
		createOrderLog(orderId, OrderLogType.Delivered, deliverMan,
				"整单发货:物流公司：" + deliveryCorpName + "物流编号：" + deliverySn);
		String alert = "D2C提醒您，您购买的商品：" + productName.substring(1, productName.length()) + " 已发货，物流公司："
				+ deliveryCorpName + " 物流单号：" + deliverySn;
		doSendDeliveryMsg(lastItem, alert, ip);
		return success;
	}

	private void doSendDeliveryMsg(OrderItem lastItem, String msg, String ip) {
		if (lastItem != null) {
			MemberInfo memberInfo = memberInfoService.findById(lastItem.getBuyerMemberId());
			if (memberInfo != null) {
				String subject = "发货通知";
				String content = msg;
				PushBean pushBean = new PushBean(lastItem.getBuyerMemberId(), content, 11);
				pushBean.setAppUrl("/details/order/" + lastItem.getOrderSn());
				MsgBean msgBean = new MsgBean(lastItem.getBuyerMemberId(), 11, subject, content);
				msgBean.setAppUrl("/details/order/" + lastItem.getOrderSn());
				msgBean.setPic(lastItem.getProductImg());
				msgUniteService.sendPush(pushBean, msgBean);
			}
		}
	}

	/**
	 * 当订单明细变为发货后，订单相应状态的变更
	 * 
	 * @param orderId
	 * @param deliveryCorpName
	 * @param deliverySn
	 * @param deliverMan
	 */
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public int doDeliveryByItem(Long orderId, String deliveryCorpName, String deliverySn, String deliverMan) {
		int result = 0;
		int colseNum = 0;
		int deliveredNum = 0;
		List<OrderItem> items = orderItemService.findSimpleByOrderId(orderId);
		for (OrderItem oi : items) {
			String statusStr = oi.getStatus();
			ItemStatus status = ItemStatus.getItemStatusByName(statusStr);
			if (ItemStatus.CLOSE.equals(status) || ItemStatus.MALLCLOSE.equals(status)
					|| ItemStatus.AFTERCLOSE.equals(status)) {
				colseNum = colseNum + 1;
			}
			if (ItemStatus.DELIVERED.equals(status) || ItemStatus.SUCCESS.equals(status)
					|| ItemStatus.SIGNED.equals(status)) {
				deliveredNum = deliveredNum + 1;
			}
		}
		// 除了关闭的orderItem，其他orderItem都已发货，order改为已发货
		if ((colseNum + deliveredNum) == items.size() && deliveredNum > 0) {
			result = orderMapper.doDeliveryOrder(orderId, deliveryCorpName, deliverySn);
		}
		if (result > 0) {
			createOrderLog(orderId, OrderLogType.Delivered, deliverMan,
					"最后一条明细：物流公司：" + deliveryCorpName + "物流编号：" + deliverySn);
		}
		return result;
	}

	/**
	 * 当订单明细变为最终态，订单相应状态的变更
	 * 
	 * @param orderId
	 * @param oldOrderStatus
	 * @param orderStatus
	 * @param operator
	 * @return
	 */
	@Override
	@TxTransaction
	@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
	public int doSuccessByItem(Long orderId, OrderStatus oldOrderStatus, String operator) {
		int result = 0;
		int colseNum = 0;
		int successNum = 0;
		List<OrderItem> items = orderItemService.findSimpleByOrderId(orderId);
		// BigDecimal totalAmount = new BigDecimal(0);
		for (OrderItem oi : items) {
			ItemStatus status = ItemStatus.getItemStatusByName(oi.getStatus());
			if (status.getCode() < 0 && status.getCode() != ItemStatus.AFTERCLOSE.getCode()) {
				colseNum = colseNum + 1;
			} else if (ItemStatus.SUCCESS.equals(status) || ItemStatus.AFTERCLOSE.equals(status)) {
				successNum = successNum + 1;
				// if (ItemStatus.SUCCESS.equals(status) && rebate) {
				// totalAmount = totalAmount.add(oi.getActualAmount());
				// }
			}
		}
		if (colseNum == items.size()) {
			result = this.updateOrderStatus(orderId, oldOrderStatus, OrderStatus.MallClose, operator);
		} else if ((colseNum + successNum) == items.size() && successNum > 0) {
			result = this.updateOrderStatus(orderId, oldOrderStatus, OrderStatus.Success, operator);
		}
		// if (rebate) {
		// this.doRebate(orderId, totalAmount);
		// }
		return result;
	}

	// private void doRebate(Long orderId, BigDecimal totalAmount) {
	// RedPacketsItem redPacketsItem =
	// redPacketsItemService.findByTransactionAndType(orderId,
	// RedPacketsItem.BusinessTypeEnum.REBATE.name());
	// if (redPacketsItem != null && redPacketsItem.getStatus() == 1) {
	// BigDecimal redAmount = new BigDecimal(0);
	// if (totalAmount.compareTo(new BigDecimal(3000)) >= 0) {
	// redAmount = new BigDecimal(388);
	// } else if (totalAmount.compareTo(new BigDecimal(2000)) >= 0
	// && totalAmount.compareTo(new BigDecimal(3000)) < 0) {
	// redAmount = new BigDecimal(188);
	// } else if (totalAmount.compareTo(new BigDecimal(1000)) >= 0
	// && totalAmount.compareTo(new BigDecimal(2000)) < 0) {
	// redAmount = new BigDecimal(88);
	// }
	// if (redAmount.compareTo(new BigDecimal(0)) > 0) {
	// redPacketsItemService.doRebate(redPacketsItem.getId(), redAmount);
	// }
	// }
	// }

	@Override
	public List<Order> findByTerminal(String terminal, String terminalId) {
		return orderMapper.findByTerminal(terminal, terminalId);
	}

	@Override
	public int countBargain(Long memberId, Date startDate, Date endDate) {
		return orderMapper.countBargain(memberId, startDate, endDate);
	}

	@Override
	@AsyncMethod
	public void pushKaolaOrder(String orderSn, List<OrderItem> orderItems) {
		Set<Long> warehouseIds = new HashSet<>();
		for (OrderItem oi : orderItems) {
			if (oi.getProductSource() != null && oi.getProductSource().equals(ProductSource.KAOLA.name())) {
				warehouseIds.add(oi.getWarehouseId());
			}
		}
		for (Long warehouseId : warehouseIds) {
			try {
				KaolaClient.getInstance().payOrder(orderSn + "-" + warehouseId);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	@Async
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doSimulateOrder(CollageOrder collageOrder) {
		CollageOrderDto coDto = new CollageOrderDto();
		BeanUtils.copyProperties(collageOrder, coDto);
		// 初始化订单
		OrderDto oDto = this.initOrder(coDto);
		List<OrderItemDto> orderItems = new ArrayList<>();
		OrderItemDto oiDto = this.initOrderItem(coDto, oDto);
		orderItems.add(oiDto);
		oDto.setOrderItems(orderItems);
		// 初始化分销
		MemberInfo memberInfo = memberInfoService.findById(coDto.getMemberId());
		this.doInitPartner(memberInfo, oDto, coDto.getPartnerId(), 2, false);
		// 生成订单
		Order newOrder = this.insert(oDto);
		oiDto.setOrderIdAndSn(newOrder.getId(), newOrder.getOrderSn());
		orderItemService.insert(oiDto);
		// 支付订单
		this.doPaySuccess(newOrder.getOrderSn(), coDto.getPaymentId(), coDto.getPaySn(), coDto.getPaymentType(),
				coDto.getPaidAmount());
	}

	private OrderDto initOrder(CollageOrderDto coDto) {
		OrderDto oDto = new OrderDto();
		// 会员信息
		IMemberInterface iMember = coDto;
		BeanUtils.copyProperties(iMember, oDto);
		// 地址信息
		IAddressInterface iAddress = coDto;
		BeanUtils.copyProperties(iAddress, oDto);
		// 单据信息
		oDto.setCreator(coDto.getLoginCode());
		oDto.setLastModifyMan(coDto.getMemberId() + ":" + coDto.getLoginCode());
		oDto.setId(null);
		oDto.setTempId(RandomUtil.getRandomString(32));
		oDto.setOrderSn(coDto.getSn());
		oDto.setType(OrderType.collage.name());
		oDto.setOrderStatus(OrderStatus.WaitingForPay.getCode());
		oDto.setProductTotalQuantity(1);
		oDto.setProductTotalPrice(coDto.getTotalAmount());
		oDto.setTotalAmount(coDto.getTotalAmount());
		oDto.setShippingRates(coDto.getShippingRates());
		oDto.setDevice(coDto.getAppTerminal());
		oDto.setAppVersion(coDto.getAppVersion());
		oDto.setCollageId(coDto.getPromotionId());
		oDto.setPartnerId(coDto.getPartnerId());
		oDto.setDrawee(coDto.getDrawee());
		return oDto;
	}

	private OrderItemDto initOrderItem(CollageOrderDto coDto, OrderDto oDto) {
		OrderItemDto oiDto = new OrderItemDto();
		// 单据信息
		oiDto.setId(null);
		oiDto.setType(oDto.getType());
		oiDto.setStatus(ItemStatus.INIT.name());
		oiDto.setBuyerMemberId(oDto.getMemberId());
		oiDto.setBuyerMemberName(oDto.getLoginCode());
		// 商品信息
		Product product = productService.findById(coDto.getProductId());
		oiDto.setProductId(product.getId());
		oiDto.setSaleCategory(product.getSaleCategory());
		oiDto.setProductSaleType(product.getProductSaleType());
		oiDto.setProductSellType(product.getProductSellType());
		oiDto.setProductTradeType(product.getProductTradeType());
		oiDto.setProductSource(product.getSource());
		oiDto.setProductSn(product.getInernalSn());
		oiDto.setExternalSn(product.getExternalSn());
		oiDto.setProductName(product.getName());
		oiDto.setProductImg(product.getProductImageCover());
		oiDto.setProductPrice(oDto.getProductTotalPrice());
		oiDto.setProductQuantity(oDto.getProductTotalQuantity());
		oiDto.setDeliveryQuantity(oDto.getProductTotalQuantity());
		oiDto.setOriginalPrice(product.getOriginalPrice());
		oiDto.setProductRemark(product.getRemark());
		oiDto.setDesignerId(product.getDesignerId());
		oiDto.setDesignerCode(product.getDesignerCode());
		oiDto.setDesignerName(product.getDesignerName());
		// 条码信息
		ProductSku sku = productSkuService.findById(coDto.getSkuId());
		oiDto.setDeliveryBarCode(sku.getSn());
		oiDto.setProductSkuId(sku.getId());
		oiDto.setProductSkuSn(sku.getSn());
		oiDto.setProductSkuName(sku.getName());
		oiDto.setSp1(sku.getSp1());
		oiDto.setSp2(sku.getSp2());
		oiDto.setSp3(sku.getSp3());
		oiDto.setCod(product.getCod());
		oiDto.setAfter(product.getAfter());
		oiDto.setTaxation(product.getTaxation());
		// 全局返利系数
		Setting ratio = settingService.findByCode(Setting.REBATERATIO);
		BigDecimal parentRatio = product.getFirstRatio().multiply(product.getGrossRatio());
		BigDecimal partnerRatio = product.getSecondRatio().multiply(product.getGrossRatio());
		String ratioValue = Setting.defaultValue(ratio, new Integer(1)).toString();
		parentRatio = parentRatio.multiply(new BigDecimal(ratioValue)).setScale(2, BigDecimal.ROUND_HALF_UP);
		partnerRatio = partnerRatio.multiply(new BigDecimal(ratioValue)).setScale(2, BigDecimal.ROUND_HALF_UP);
		oiDto.setPartnerRatio(partnerRatio);
		oiDto.setParentRatio(parentRatio);
		oiDto.setSuperRatio(new BigDecimal(0.02));
		oiDto.setMasterRatio(new BigDecimal(0.02));
		return oiDto;
	}

}
