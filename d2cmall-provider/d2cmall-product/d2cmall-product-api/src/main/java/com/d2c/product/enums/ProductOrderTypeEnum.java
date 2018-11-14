package com.d2c.product.enums;

import com.d2c.common.api.page.PageHelper;
import com.d2c.common.api.page.base.PageSort;

/**
 * 商品排序类型
 * 
 * @author Administrator
 *
 */
public enum ProductOrderTypeEnum {
	/*
	 * 1.综合排序:显示规则“有货“-”主推”-“按销量”-“上架时间-无货” 2.最新：上架时间-有货-主推； 3.销量：销量-有货-主推 4.价格：
	 * 价格-有货-主推； 4、店铺作品排序：有货-系列-推荐值
	 * 
	 */

	pa("价格从低到高", "minPrice|asc"), 
	pd("价格从高到低", "minPrice"), 
	da("上架时间最早", "upMarketDate|asc"), 
	dd("上架时间最晚", "upMarketDate"), 
	sa("销量从少到多", "sales|asc"), 
	sd("销量从多到少", "sales"), 
	ps("分销销量排行", "partnerSales"), 
	bd("品牌商品默认排序", "seriesUpDate"),
	ca("创建时间最早", "createDate|asc"),
	cd("创建时间最晚", "createDate");
	
	private String display;
	
	private PageSort sort;

	ProductOrderTypeEnum(String display, String... orders) {
		this.display = display;
		this.sort = PageHelper.buildSort(orders);
	}
	
	public static PageSort getPageSort(String key){
		try{
			return valueOf(key).getSort();
		}catch (Exception e) {
			return null;
		}
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public PageSort getSort() {
		return sort;
	}

	public void setSort(PageSort sort) {
		this.sort = sort;
	}

	public Boolean equals(String obj) {
		return this.toString().equals(obj);
	}
}
