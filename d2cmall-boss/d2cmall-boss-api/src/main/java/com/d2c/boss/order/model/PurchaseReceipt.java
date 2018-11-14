package com.d2c.boss.order.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Table;

import com.d2c.common.api.model.PreUserDO;

/**
 * 
 * @see 采购入库通知单 TODO
 * 
 */
@Table(name = "scm_purchase_receipt")
public class PurchaseReceipt extends PreUserDO {

	private static final long serialVersionUID = 1L;

	/**
	 * 供应商
	 */
	private Long supplierId;
	/**
	 * 收货店铺
	 */
	private String shopCode;
	/**
	 * 入库单号
	 */
	private String orderSn;
	/**
	 * 单据日期
	 */
	private Date billDate;
	/**
	 * 提交时间
	 */
	private Date submitDate;
	/**
	 * 入库状态
	 */
	private String orderStatus;
	/**
	 * 标准金额
	 */
	private BigDecimal totalAmount;
	/**
	 * 组织编号
	 */
	private String orgId;
	/**
	 * 总数量
	 */
	private Integer itemNum;
	/**
	 * 总入库数量
	 */
	private Integer fitemNum;
	/**
	 * 客服备注
	 */
	private String remark;

	public enum OrderStatus {
		INITIAL(1), SUBMIT(2), CLOSE(-1), SUCCESS(8);
		private int code;
		private static Map<Integer, OrderStatus> holder = new HashMap<Integer, OrderStatus>();
		static {
			holder.put(1, OrderStatus.INITIAL);
			holder.put(2, OrderStatus.SUBMIT);
			holder.put(-1, OrderStatus.CLOSE);
			holder.put(8, OrderStatus.SUCCESS);
		}

		OrderStatus(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}

		public static OrderStatus getStatus(int i) {
			return holder.get(i);
		}
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public Integer getFitemNum() {
		return fitemNum;
	}

	public void setFitemNum(Integer fitemNum) {
		this.fitemNum = fitemNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
