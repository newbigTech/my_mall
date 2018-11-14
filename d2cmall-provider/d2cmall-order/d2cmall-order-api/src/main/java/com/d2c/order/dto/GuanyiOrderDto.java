package com.d2c.order.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.d2c.order.model.GuanyiOrder;
import com.d2c.order.model.GuanyiOrderItem;

public class GuanyiOrderDto extends GuanyiOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GuanyiOrderDto() {
		super();
	}

	public GuanyiOrderDto(JSONObject obj) {
		super(obj);
	}

	private List<GuanyiOrderItem> items;

	public enum ShopCodeEnum {
		// 管易门店编号对应的官网门店code
		D2C001("001"), D2C002(null), D2C003("D2C003"), D2C004(null), D2C005("D2C005"), D2C006("D2C006"),

		D2C007("002"), D2C008("0023"), D2C009("002"), D2C010("002"), D2C011("D2C011"), D2C013("D2C100"),

		D2C014("D2C100"), D2C015("D2C100"), D2C016("D2C100"), D2C017("D2C100"), D2C018("D2C100"), D2C023("D2C100"),

		D2C022("D2C022"), D2C024(null);

		private String storeCode;
		private static Map<String, ShopCodeEnum> shopCodeMap = new HashMap<>();;

		ShopCodeEnum(String storeCode) {
			this.storeCode = storeCode;
		}

		static {
			for (ShopCodeEnum shopCodeEnum : ShopCodeEnum.values()) {
				shopCodeMap.put(shopCodeEnum.name(), shopCodeEnum);
			}
		}

		public String getStoreCode() {
			return storeCode;
		}

		public static Map<String, ShopCodeEnum> getShopCodeMap() {
			return shopCodeMap;
		}

		public static void setShopCodeMap(Map<String, ShopCodeEnum> shopCodeMap) {
			ShopCodeEnum.shopCodeMap = shopCodeMap;
		}
	}

	public List<GuanyiOrderItem> getItems() {
		return items;
	}

	public void setItems(List<GuanyiOrderItem> items) {
		this.items = items;
	}

}
