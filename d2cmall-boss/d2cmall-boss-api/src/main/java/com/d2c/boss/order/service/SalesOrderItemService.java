package com.d2c.boss.order.service;

import java.util.Date;
import java.util.List;

import com.d2c.boss.order.dto.MonthQuantityDto;
import com.d2c.boss.order.dto.ProductDetailDto;
import com.d2c.boss.order.dto.ProductReturnedDetailDto;
import com.d2c.boss.order.dto.WeekQuantityDto;
import com.d2c.boss.order.model.SalesOrderItem;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.response.SuccessResponse;

public interface SalesOrderItemService {

	SalesOrderItem findById(String orderItemId);

	SalesOrderItem findBySourceIdAndStoreId(String sourceId, String storeId);

	void save(List<SalesOrderItem> orderItems);

	List<Integer> getStatus();

	/**
	 * 
	 * 日明细
	 * 
	 * @param brandId
	 *            品牌ID
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @return 店铺，商品图片，供应商款号，颜色，尺码，数量
	 */
	List<ProductDetailDto> dayDetailByBrand(List<String> brandId, Integer year, Integer month, Integer day);

	/**
	 * 
	 * 月明细
	 * 
	 * @param brandId
	 *            品牌ID
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 店铺，商品图片，供应商款号，颜色，尺码，数量
	 */
	SuccessResponse monthDetailByBrand(List<String> brandId, Integer year, Integer month, PageModel page);

	/**
	 * 
	 * 1-12月按月统计某设计师商品数量
	 * 
	 * @param brandId
	 *            品牌ID
	 * @param year
	 *            年
	 * @return 月份，数量
	 */
	List<MonthQuantityDto> sumAllMonthByBrand(List<String> brandId);

	/**
	 * 
	 * 单日销售总量
	 * 
	 * @param brandId
	 *            品牌ID
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @return 数量
	 */
	Integer daySaleSumByBrand(List<String> brandId, Integer year, Integer month, Integer day);

	/**
	 * 
	 * 单日退货总量
	 * 
	 * @param brandId
	 *            品牌ID
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @return 数量
	 */
	Integer dayReturnSumByBrand(List<String> brandId, Integer year, Integer month, Integer day);

	/**
	 * 
	 * 1-12月按月统计某设计师退货数量
	 * 
	 * @param brandId
	 *            品牌ID
	 * @param year
	 *            年
	 * @return 月份，数量
	 */
	List<MonthQuantityDto> sumAllMonthReturnedByBrand(List<String> brandId);

	/**
	 * 
	 * 月退货明细
	 * 
	 * @param brandId
	 *            品牌ID
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 店铺，商品图片，供应商款号，颜色，尺码，数量，退货原因
	 */
	SuccessResponse monthReturnedDetailByBrand(List<String> brandId, Integer year, Integer month, PageModel page);

	/**
	 * 
	 * 按周统计某设计师商品数量
	 * 
	 * @param brandId
	 *            品牌ID
	 * @param year
	 *            年
	 * @return 周次，数量
	 */
	List<WeekQuantityDto> sumAllWeekByBrand(List<String> brandId);

	/**
	 * 
	 * 周明细
	 * 
	 * @param brandId
	 *            品牌ID
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 店铺，商品图片，供应商款号，颜色，尺码，数量
	 */
	SuccessResponse weekDetailByBrand(List<String> brandId, Integer year, Integer week, PageModel page);

	/**
	 * 
	 * 单周销售总量
	 * 
	 * @param brandId
	 *            品牌ID
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 * @return 数量
	 */
	Integer weekSumByBrand(List<String> brandId, Integer year, Integer week);

	/**
	 * 
	 * 各店铺日明细
	 * 
	 * @param storeSn
	 *            品牌ID
	 * @param date
	 *            年
	 * @return 店铺，商品图片，供应商款号，颜色，尺码，数量
	 */
	SuccessResponse dayDetailByStore(String storeSn, Date day, PageModel page);

	void updateCloseItem();

	void updateOnline();

	void insertOnline();

	void updateOffline();

	void insertOffline();

	void updateBillDate();

	void updateBrand();

	void updateColorSize();

	void updatePicture();

	void updateOrderId();

	void doProductInvisible();

	List<ProductDetailDto> setProductDetail(List<Object[]> ol);

	List<ProductReturnedDetailDto> setProductReturnedDetail(List<Object[]> ol);

	List<Object[]> productSaleByDay();

	List<Object[]> productSaleByWeek();

	List<Object[]> productSaleByMonth();

}
