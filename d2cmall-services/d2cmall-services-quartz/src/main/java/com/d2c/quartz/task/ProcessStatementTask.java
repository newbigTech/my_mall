package com.d2c.quartz.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.msg.model.ErrorLog;
import com.d2c.msg.service.ErrorLogService;
import com.d2c.order.model.Order;
import com.d2c.order.model.OrderItem;
import com.d2c.order.model.Reship;
import com.d2c.order.model.StatementItem;
import com.d2c.order.model.Store;
import com.d2c.order.service.OrderItemService;
import com.d2c.order.service.OrderService;
import com.d2c.order.service.ReshipService;
import com.d2c.order.service.StatementItemService;
import com.d2c.order.service.StoreService;
import com.d2c.product.model.ProductSku;
import com.d2c.product.service.ProductSkuService;
import com.d2c.quartz.task.base.BaseTask;

@Component
public class ProcessStatementTask extends BaseTask {

	@Autowired
	private StatementItemService statementItemService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private ErrorLogService errorLogService;
	@Autowired
	private ProductSkuService productSkuService;
	@Autowired
	private ReshipService reshipService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private OrderService orderService;

	/**
	 * 生成对账商品明细
	 */
	@Scheduled(cron = "0 0 3 * * ?")
	public void execute() {
		if (properties.getDebug()) {
			return;
		}
		super.exec();
	}

	@Override
	public void execImpl() {
		this.insertStatementItem();
	}

	private void insertStatementItem() {
		try {
			StatementItem statement = statementItemService.findLastOne(StatementItem.SourceType.OnlineOrder.getCode());
			if (statement != null) {
				this.doSysInsert(new Date(statement.getCreateDate().getTime()),
						StatementItem.SourceType.OnlineOrder.getCode());
			} else {
				createStatement(StatementItem.SourceType.OnlineOrder.getCode());
			}
			StatementItem reshipStatement = statementItemService
					.findLastOne(StatementItem.SourceType.OnlineReship.getCode());
			if (reshipStatement != null) {
				this.doSysInsert(new Date(reshipStatement.getCreateDate().getTime()),
						StatementItem.SourceType.OnlineReship.getCode());
			} else {
				createStatement(StatementItem.SourceType.OnlineReship.getCode());
			}
		} catch (Exception e) {
			logger.error("insertStatementItem error!" + e.getMessage(), e);
		}
	}

	private void createStatement(Integer type) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		// 开启时间
		try {
			Date date = sf.parse("2018-01-01");
			// 当下时间
			Calendar c = Calendar.getInstance();
			Date date1 = sf.parse(
					c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + (c.get(Calendar.DAY_OF_MONTH)));
			if (date1.after(date)) {
				// 往前推一天
				this.doSysInsert(new Date(date.getTime()), type);
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private String doSysInsert(Date beginDate, Integer type) {
		String error = "";
		// 签收的
		if (type != null && type == StatementItem.SourceType.OnlineOrder.getCode()) {
			PageModel page = new PageModel();
			PageResult<OrderItem> orderItems = new PageResult<>();
			do {
				orderItems = orderItemService.findDtoForStatement(beginDate, page);
				for (OrderItem orderItem : orderItems.getList()) {
					try {
						ProductSku productSku = productSkuService.findById(orderItem.getProductSkuId());
						Order order = orderService.findById(orderItem.getOrderId());
						Store store = storeService.findById(orderItem.getStoreId());
						StatementItem statementItem = new StatementItem(orderItem, productSku, order, store,
								StatementItem.ItemStaus.INIT.getCode());
						statementItem.setType(StatementItem.SourceType.OnlineOrder.getCode());
						statementItemService.insert(statementItem);
					} catch (Exception e) {
						error = "订单编号：" + orderItem.getOrderSn() + "；款号 ：" + orderItem.getProductSkuSn() + "," + "id："
								+ orderItem.getDesignerId() + e.getMessage();
						ErrorLog errorLog = new ErrorLog();
						errorLog.createErrorLog("sys", "backend", error);
						errorLogService.insert(errorLog);
					}
				}
				page.setP(page.getP() + 1);
			} while (orderItems.isNext());

		} else if (type != null && type == StatementItem.SourceType.OnlineReship.getCode()) {
			PageModel page = new PageModel();
			PageResult<Reship> reships = null;
			do {
				reships = reshipService.findReshipForStatement(beginDate, page);
				for (Reship reship : reships.getList()) {
					try {
						ProductSku productSku = productSkuService.findById(reship.getProductSkuId());
						OrderItem orderItem = orderItemService.findById(reship.getOrderItemId());
						StatementItem statementItem = new StatementItem(reship, productSku, orderItem,
								StatementItem.ItemStaus.INIT.getCode());
						statementItem.setType(StatementItem.SourceType.OnlineReship.getCode());
						// 款号，订单编号一样的就做补偿
						statementItemService.insert(statementItem);
					} catch (Exception e) {
						error = "退货编号：" + reship.getReshipSn() + "；款号 ：" + reship.getProductSkuSn() + "," + "id："
								+ reship.getId() + e.getMessage();
						ErrorLog errorLog = new ErrorLog();
						errorLog.createErrorLog("sys", "backend", error);
						errorLogService.insert(errorLog);
					}
				}
				page.setP(page.getP() + 1);
			} while (reships.isNext());
		}
		return error;
	}

}
