package com.d2c.quartz.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.order.model.Order;
import com.d2c.order.model.Setting;
import com.d2c.order.service.OrderQueryService;
import com.d2c.order.service.SettingService;
import com.d2c.order.service.tx.AuctionTxService;
import com.d2c.order.service.tx.OrderTxService;
import com.d2c.product.model.AuctionProduct;
import com.d2c.product.service.AuctionProductService;
import com.d2c.quartz.task.base.BaseTask;
import com.d2c.quartz.task.base.EachPage;

@Component
public class ExpiredOrderTask extends BaseTask {

	@Autowired
	private OrderQueryService orderQueryService;
	@Autowired
	private SettingService settingService;
	@Autowired
	private AuctionProductService auctionProductService;
	@Reference
	private AuctionTxService auctionTxService;
	@Reference
	private OrderTxService orderTxService;

	@Scheduled(fixedDelay = 60 * 1000 * 5)
	public void execute() {
		if (properties.getDebug()) {
			return;
		}
		super.exec();
	}

	@Override
	public void execImpl() {
		this.autoCloseOrder();
		this.autoCloseSeckillOrder();
		this.autoCloseAuction();
	}

	/**
	 * 过期普通订单关闭 （3小时）
	 */
	private void autoCloseOrder() {
		Setting setting = settingService.findByCode(Setting.ORDERCLOSECODE);
		int closeTime = 3 * 60 * 60;
		try {
			closeTime = Integer.valueOf(Setting.defaultValue(setting, "86400").toString());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		final int closeSeconds = closeTime;
		try {
			this.processPager(100, new EachPage<Long>() {

				@Override
				public int count() {
					return orderQueryService.countExpireOrder(closeSeconds,
							new Order.OrderType[] { Order.OrderType.distribution, Order.OrderType.ordinary });
				}

				@Override
				public PageResult<Long> search(PageModel page) {
					return orderQueryService.findExpireOrder(closeSeconds,
							new Order.OrderType[] { Order.OrderType.distribution, Order.OrderType.ordinary }, page);
				}

				@Override
				public boolean each(Long object) {
					int result = orderTxService.doCloseExpireOrder(object);
					return result > 0 ? true : false;
				}
			});
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 过期秒杀订单关闭 （30分钟）
	 */
	private void autoCloseSeckillOrder() {
		Setting setting = settingService.findByCode(Setting.SECKILLCLOSECODE);
		int closeTime = 30 * 60;
		try {
			closeTime = Integer.valueOf(Setting.defaultValue(setting, "3600").toString());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		final int closeSeconds = closeTime;
		try {
			this.processPager(100, new EachPage<Long>() {

				@Override
				public int count() {
					return orderQueryService.countExpireOrder(closeSeconds,
							new Order.OrderType[] { Order.OrderType.seckill, Order.OrderType.bargain });
				}

				@Override
				public PageResult<Long> search(PageModel page) {
					return orderQueryService.findExpireOrder(closeSeconds,
							new Order.OrderType[] { Order.OrderType.seckill, Order.OrderType.bargain }, page);
				}

				@Override
				public boolean each(Long object) {
					int result = orderTxService.doCloseExpireOrder(object);
					return result > 0 ? true : false;
				}
			});
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 过期拍卖订单结束
	 */
	private void autoCloseAuction() {
		try {
			List<AuctionProduct> list = auctionProductService.findExpiredAuctionProduct();
			for (AuctionProduct auctionProduct : list) {
				try {
					auctionTxService.doEndAuction(auctionProduct);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
