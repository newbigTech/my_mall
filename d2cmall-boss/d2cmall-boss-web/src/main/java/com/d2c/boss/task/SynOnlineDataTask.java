package com.d2c.boss.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.d2c.boss.member.service.MemberInfoService;
import com.d2c.boss.member.service.MemberService;
import com.d2c.boss.offline.service.OfflineBrandService;
import com.d2c.boss.offline.service.OfflineOrderItemService;
import com.d2c.boss.offline.service.OfflineOrderService;
import com.d2c.boss.online.service.OnlineBrandService;
import com.d2c.boss.online.service.OnlineOrderItemService;
import com.d2c.boss.online.service.OnlineOrderService;
import com.d2c.boss.order.service.OrderItemService;
import com.d2c.boss.order.service.OrderService;
import com.d2c.boss.product.service.BrandService;
import com.d2c.boss.product.service.ProductService;
import com.d2c.boss.product.service.ProductSkuService;

@Component
public class SynOnlineDataTask {

	@Autowired
	private OnlineOrderService onlineOrderService;
	@Autowired
	private OfflineOrderService offlineOrderService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private OnlineOrderItemService onlineOrderItemService;
	@Autowired
	private OfflineOrderItemService offlineOrderItemService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductSkuService productSkuService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private OnlineBrandService onlineBrandService;
	@Autowired
	private OfflineBrandService offlineBrandService;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private MemberService memberService;

	private static final Log logger = LogFactory.getLog(SynOnlineDataTask.class);
	private static boolean requestData = false;

	/**
	 * 每10分钟请求官网数据
	 */
	@Scheduled(cron = "0 0/2 * * * ?")
	public void requestData() throws Exception {
		try {
			if (requestData) {
				requestData = false;
				// this.handleOnlineOrderInfos();
				// this.handleOfflineOrderInfos();
				// this.handleOnlineBrand();
				// this.handleOfflineBrand();
				// this.handleOnlineProductInfos();
				// this.handleOfflineProductInfo();
				// this.handleOnlineMemberInfos();
				// this.handleOfflineMemberInfos();
				requestData = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			requestData = true;
		}
	}

	public void handleOnlineMemberInfos() {
		// 处理线上用户数据
		memberInfoService.updateDevices();
		memberInfoService.insertOnlineMemberInfo();
		// 处理线上用户的状态
		memberInfoService.updateOnlineStatus();

		memberService.insertOnlineMember();
		memberService.updateOnlineStatus();
	}

	public void handleOfflineMemberInfos() {
		// 处理线上产品数据
		memberInfoService.insertOfflineMemberInfo();
		// 处理线上产品的状态
		memberInfoService.updateOfflineStatus();
	}

	public void handleOnlineProductInfos() {
		// 处理线上产品数据
		productService.updateOnlineProduct();
		productService.insertOnlineProduct();
		// 处理线上产品的状态
		productService.updateOnlineStatus();

		// 处理线上产品sku数据
		productSkuService.updateOnlineProductSku();
		productSkuService.insertOnlineProductSku();
		// 处理线上产品sku的状态
		productSkuService.updateOnlineStatus();
	}

	public void handleOfflineProductInfo() {
		// 处理线下产品数据
		productService.updateOfflineProduct();
		productService.insertOfflineProduct();
		// 处理线下产品的状态
		productService.updateOfflineStatus();
		// 处理线下产品sku数据
		productSkuService.updateOfflineProductSku();
		productSkuService.insertOfflineProductSku();
		// 处理线下产品sku的状态
		productSkuService.updateOfflineStatus();
	}

	public void handleOnlineBrand() {
		brandService.updateOnlineBrand();
		brandService.insertOnlineBrand();
		onlineBrandService.updateStatus();
	}

	public void handleOfflineBrand() {
		brandService.updateOfflineBrand();
		brandService.insertOfflineBrand();
		offlineBrandService.updateStatus();
	}

	public void handleOnlineOrderInfos() {
		// 统一主订单的设备来源
		onlineOrderService.updateSource();
		// 更新订单主表 状态=0
		orderService.updateOnlineOrders();
		orderService.insertOnlineOrders();
		// 处理状态变更
		onlineOrderService.updateStatus();
		// 更新明细主表 状态=0
		orderItemService.updateOnlineOrderItems();
		orderItemService.insertOnlineOrderItems();
		// 处理状态变更
		onlineOrderItemService.updateStatus();
	}

	public void handleOfflineOrderInfos() {
		// 处理订单状态
		offlineOrderService.updateSuccessStatus();
		offlineOrderService.updateReturnStatus();
		offlineOrderService.updateRecopyStatus();
		// 更新明细状态
		offlineOrderItemService.updateSuccessStatus();
		offlineOrderItemService.updateReturnStatus();
		offlineOrderItemService.updateRecopyStatus();
		// 删除顺丰正品仓非退货单
		offlineOrderService.deleteSfOrder();
		offlineOrderItemService.deleteSfOrderItem();
		// 更新订单主表
		orderService.updateOfflineOrders();
		orderService.insertOfflineOrders();
		// // 处理状态变更
		offlineOrderService.updateStatus();
		// 处理明细门店
		// offlineOrderItemService.updateStore();
		// 更新明细主表
		orderItemService.updateOfflineOrderItems();
		orderItemService.insertOfflineOrderItems();
		// // 处理状态变更
		offlineOrderItemService.updateStatus();
	}

}
