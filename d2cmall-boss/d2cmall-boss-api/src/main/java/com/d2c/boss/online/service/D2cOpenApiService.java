package com.d2c.boss.online.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.d2c.boss.member.model.Comment;
import com.d2c.boss.member.model.Coupon;
import com.d2c.boss.member.model.CouponDef;
import com.d2c.boss.member.model.Recharge;
import com.d2c.boss.online.model.OnlineMember;
import com.d2c.boss.online.model.OnlineMemberInfo;
import com.d2c.boss.online.model.OnlineOrder;
import com.d2c.boss.online.model.OnlineOrderItem;
import com.d2c.boss.order.model.Exchange;
import com.d2c.boss.order.model.Refund;
import com.d2c.boss.order.model.Reship;
import com.d2c.boss.product.model.Brand;
import com.d2c.boss.product.model.BrandTags;
import com.d2c.boss.product.model.Product;
import com.d2c.boss.product.model.ProductCategory;
import com.d2c.boss.product.model.ProductSku;
import com.d2c.boss.product.model.ProductTags;
import com.d2c.boss.product.model.Shop;

public interface D2cOpenApiService {
	//
	// int findBrands(int pagerSize, int pagerNumber, Date lastSysDate,
	// List<OnlineBrand> restList) throws Exception;
	//
	// int findProducts(int pagerSize, int pagerNumber, Date lastSysDate,
	// List<OnlineProduct> restList) throws Exception;
	//
	// int findSkus(int pagerSize, int pagerNumber, Date lastSysDate,
	// List<OnlineSku> resList2) throws Exception;
	//
	// int findOrders(int pagerSize, int pagerNumber, Date lastSysDate,
	// List<OnlineSalesOrder> resList) throws Exception;
	//
	// int findOrderItems(int pagerSize, int pagerNumber, Date lastSysDate,
	// List<OnlineSalesOrderItem> resList2)
	// throws Exception;
	//
	// JSONObject findMembers(int pagerSize, int pagerNumber) throws Exception;
	//
	// int postStock(JSONArray data) throws Exception;

	// crm

	Map<String, Object> findBrandTags(int pagerSize, int pagerNumber, Date lastSysDate, List<BrandTags> restList)
			throws Exception;

	Map<String, Object> findProductTags(int pagerSize, int pagerNumber, Date lastSysDate, List<ProductTags> restList)
			throws Exception;

	Map<String, Object> findCouponDefs(int pagerSize, int pagerNumber, Date lastSysDate, List<CouponDef> restList)
			throws Exception;

	Map<String, Object> findCoupons(int pagerSize, int pagerNumber, Date lastSysDate, List<Coupon> restList)
			throws Exception;

	Map<String, Object> findProductCategorys(int pagerSize, int pagerNumber, List<ProductCategory> restList)
			throws Exception;

	Map<String, Object> findOnlineOrders(int pagerSize, int pagerNumber, Date lastSysDate, List<OnlineOrder> restList)
			throws Exception;

	Map<String, Object> findOnlineOrderItems(int pagerSize, int pagerNumber, Date lastSysDate,
			List<OnlineOrderItem> restList) throws Exception;

	Map<String, Object> findOnlineMemberInfos(int pagerSize, int pagerNumber, Date lastSysDate,
			List<OnlineMemberInfo> restList) throws Exception;

	Map<String, Object> findOnlineMembers(int pagerSize, int pagerNumber, Date lastSysDate, List<OnlineMember> restList)
			throws Exception;

	Map<String, Object> findComments(int pagerSize, int pagerNumber, Date lastSysDate, List<Comment> restList)
			throws Exception;

	Map<String, Object> findRecharges(int pagerSize, int pagerNumber, Date lastSysDate, List<Recharge> restList)
			throws Exception;

	Map<String, Object> findOlineRefunds(int pagerSize, int pagerNumber, Date lastSysDate, List<Refund> restList)
			throws Exception;

	Map<String, Object> findOnlineReships(int pagerSize, int pagerNumber, Date lastSysDate, List<Reship> restList)
			throws Exception;

	Map<String, Object> findOnlineExchanges(int pagerSize, int pagerNumber, Date lastSysDate, List<Exchange> restList)
			throws Exception;

	Map<String, Object> findOnlineProducts(int pagerSize, int pagerNumber, Date lastSysDate, List<Product> restList)
			throws Exception;

	Map<String, Object> findOnlineProductSkus(int pagerSize, int pagerNumber, Date lastSysDate,
			List<ProductSku> restList) throws Exception;

	Map<String, Object> findOnlineBrands(int pagerSize, int pagerNumber, Date lastSysDate, List<Brand> restList)
			throws Exception;

	Map<String, Object> findOnlineShops(int pagerSize, int pagerNumber, Date lastSysDate, List<Shop> restList)
			throws Exception;
}
