package com.d2c.boss.online.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.d2c.boss.third.common.D2cApiOauth;

@Service("d2cOpenApiService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class D2cOpenApiServiceImpl implements D2cOpenApiService {
	//
	// @Autowired
	// private BrandTagsService brandTagsService;

	@Override
	public Map<String, Object> findBrandTags(int pagerSize, int pagerNumber, Date lastSysDate, List<BrandTags> restList)
			throws Exception {
		D2cApiOauth d2cApiOauth = D2cApiOauth.getInstance();
		List<Map<String, Object>> params = d2cApiOauth.getParams("/api/crm/brandtags/list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagerSize", pagerSize);
		map.put("pagerNumber", pagerNumber);
		map.put("lastSysDate", lastSysDate.getTime());
		params.add(map);
		JSONObject json = d2cApiOauth.invoke();
		int pageCount = json.getInt("pageCount");
		handleBrandTags(json, restList);
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("pageCount", pageCount);
		restMap.put("datas", restList);
		return restMap;
	}

	private void handleBrandTags(JSONObject json, List<BrandTags> restList) throws JSONException {
		JSONArray brandTagsArray = json.getJSONArray("datas");
		if (brandTagsArray == null || brandTagsArray.length() == 0) {
			return;
		}
		for (int i = 0; i < brandTagsArray.length(); i++) {
			JSONObject brandTagJsonObj = brandTagsArray.getJSONObject(i);
			BrandTags brandTag = new BrandTags();
			brandTag.setId(brandTagJsonObj.getLong("id"));
			brandTag.setModifyDate(new Date(brandTagJsonObj.getLong("modifyDate")));
			brandTag.setCreateDate(new Date(brandTagJsonObj.getLong("createDate")));
			brandTag.setName(brandTagJsonObj.getString("name"));
			brandTag.setStatus(brandTagJsonObj.getInt("status"));
			restList.add(brandTag);
		}
	}

	@Override
	public Map<String, Object> findProductTags(int pagerSize, int pagerNumber, Date lastSysDate,
			List<ProductTags> restList) throws Exception {
		D2cApiOauth d2cApiOauth = D2cApiOauth.getInstance();
		List<Map<String, Object>> params = d2cApiOauth.getParams("/api/crm/producttags/list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagerSize", pagerSize);
		map.put("pagerNumber", pagerNumber);
		map.put("lastSysDate", lastSysDate.getTime());
		params.add(map);
		JSONObject json = d2cApiOauth.invoke();
		int pageCount = json.getInt("pageCount");
		handleProductTags(json, restList);
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("pageCount", pageCount);
		restMap.put("datas", restList);
		return restMap;
	}

	private void handleProductTags(JSONObject json, List<ProductTags> restList) throws JSONException {
		JSONArray productTagsArray = json.getJSONArray("datas");
		if (productTagsArray == null || productTagsArray.length() == 0) {
			return;
		}
		for (int i = 0; i < productTagsArray.length(); i++) {
			JSONObject productTagJsonObj = productTagsArray.getJSONObject(i);
			ProductTags tag = new ProductTags();
			tag.setId(productTagJsonObj.getLong("id"));
			tag.setModifyDate(new Date(productTagJsonObj.getLong("modifyDate")));
			tag.setCreateDate(new Date(productTagJsonObj.getLong("createDate")));
			tag.setName(productTagJsonObj.getString("name"));
			tag.setStatus(productTagJsonObj.getInt("status"));
			restList.add(tag);
		}
	}

	@Override
	public Map<String, Object> findProductCategorys(int pagerSize, int pagerNumber, List<ProductCategory> restList)
			throws Exception {
		D2cApiOauth d2cApiOauth = D2cApiOauth.getInstance();
		List<Map<String, Object>> params = d2cApiOauth.getParams("/api/crm/productcategory/list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagerSize", pagerSize);
		map.put("pagerNumber", pagerNumber);
		params.add(map);
		JSONObject json = d2cApiOauth.invoke();
		int pageCount = json.getInt("pageCount");
		handleProductCategorys(json, restList);
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("pageCount", pageCount);
		restMap.put("datas", restList);
		return restMap;
	}

	private void handleProductCategorys(JSONObject json, List<ProductCategory> restList) throws JSONException {
		JSONArray productCategoryArray = json.getJSONArray("datas");
		if (productCategoryArray == null || productCategoryArray.length() == 0) {
			return;
		}
		for (int i = 0; i < productCategoryArray.length(); i++) {
			JSONObject productCategoryJsonObj = productCategoryArray.getJSONObject(i);
			ProductCategory productCategory = new ProductCategory();
			productCategory.setCode(productCategoryJsonObj.getString("code"));
			productCategory.setModifyDate(new Date(productCategoryJsonObj.getLong("modifyDate")));
			productCategory.setCreateDate(new Date(productCategoryJsonObj.getLong("createDate")));
			productCategory.setName(productCategoryJsonObj.getString("name"));
			productCategory.setStatus(productCategoryJsonObj.getInt("status"));
			productCategory.setParent(productCategoryJsonObj.getString("parent"));
			productCategory.setDepth(productCategoryJsonObj.getInt("depth"));
			productCategory.setAllCode(productCategoryJsonObj.getString("allCode"));
			restList.add(productCategory);
		}
	}

	@Override
	public Map<String, Object> findCouponDefs(int pagerSize, int pagerNumber, Date lastSysDate,
			List<CouponDef> restList) throws Exception {
		D2cApiOauth d2cApiOauth = D2cApiOauth.getInstance();
		List<Map<String, Object>> params = d2cApiOauth.getParams("/api/crm/coupondef/list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagerSize", pagerSize);
		map.put("pagerNumber", pagerNumber);
		map.put("lastSysDate", lastSysDate.getTime());
		params.add(map);
		JSONObject json = d2cApiOauth.invoke();
		int pageCount = json.getInt("pageCount");
		handleCouponDefs(json, restList);
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("pageCount", pageCount);
		restMap.put("datas", restList);
		return restMap;
	}

	private void handleCouponDefs(JSONObject json, List<CouponDef> restList) throws JSONException {
		JSONArray couponDefsArray = json.getJSONArray("datas");
		if (couponDefsArray == null || couponDefsArray.length() == 0) {
			return;
		}
		for (int i = 0; i < couponDefsArray.length(); i++) {
			JSONObject couponDefJsonObj = couponDefsArray.getJSONObject(i);
			CouponDef couponDef = new CouponDef();
			couponDef.setId(couponDefJsonObj.getLong("id"));
			couponDef.setType(couponDefJsonObj.getString("type"));
			couponDef.setAssociation(couponDefJsonObj.getString("association"));
			couponDef.setName(couponDefJsonObj.getString("name"));
			couponDef.setSubTitle(couponDefJsonObj.getString("subTitle"));
			couponDef.setEnableDate(new Date(couponDefJsonObj.getLong("enableDate")));
			couponDef.setExpireDate(new Date(couponDefJsonObj.getLong("expireDate")));
			couponDef.setActiveDay(couponDefJsonObj.getInt("activeDay"));
			couponDef.setActiveHour(couponDefJsonObj.getInt("activeHour"));
			couponDef.setClaimStart(new Date(couponDefJsonObj.getLong("claimStart")));
			couponDef.setClaimEnd(new Date(couponDefJsonObj.getLong("claimEnd")));
			couponDef.setAmount(couponDefJsonObj.getInt("amount"));
			couponDef.setNeedAmount(couponDefJsonObj.getInt("needAmount"));
			couponDef.setQuantity(couponDefJsonObj.getInt("quantity"));
			couponDef.setClaimed(couponDefJsonObj.getInt("claimed"));
			couponDef.setClaimLimit(couponDefJsonObj.getInt("claimLimit"));
			couponDef.setEnable(couponDefJsonObj.getInt("enable"));
			couponDef.setRandom(couponDefJsonObj.getInt("random"));
			couponDef.setRemark(couponDefJsonObj.getString("remark"));
			couponDef.setCreateDate(new Date(couponDefJsonObj.getLong("createDate")));
			couponDef.setModifyDate(new Date(couponDefJsonObj.getLong("modifyDate")));
			restList.add(couponDef);
		}
	}

	@Override
	public Map<String, Object> findCoupons(int pagerSize, int pagerNumber, Date lastSysDate, List<Coupon> restList)
			throws Exception {
		D2cApiOauth d2cApiOauth = D2cApiOauth.getInstance();
		List<Map<String, Object>> params = d2cApiOauth.getParams("/api/crm/coupon/list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagerSize", pagerSize);
		map.put("pagerNumber", pagerNumber);
		map.put("lastSysDate", lastSysDate.getTime());
		params.add(map);
		JSONObject json = d2cApiOauth.invoke();
		int pageCount = json.getInt("pageCount");
		handleCoupons(json, restList);
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("pageCount", pageCount);
		restMap.put("datas", restList);
		return restMap;
	}

	private void handleCoupons(JSONObject json, List<Coupon> restList) throws JSONException {
		JSONArray couponArray = json.getJSONArray("datas");
		if (couponArray == null || couponArray.length() == 0) {
			return;
		}
		for (int i = 0; i < couponArray.length(); i++) {
			JSONObject couponJsonObj = couponArray.getJSONObject(i);
			Coupon coupon = new Coupon();
			coupon.setId(couponJsonObj.getLong("id"));
			coupon.setName(couponJsonObj.getString("name"));
			coupon.setCode(couponJsonObj.getString("code"));
			coupon.setAmount(couponJsonObj.getInt("amount"));
			coupon.setType(couponJsonObj.getString("type"));
			coupon.setLoginCode(couponJsonObj.getString("loginCode"));
			coupon.setEnableDate(new Date(couponJsonObj.getLong("enableDate")));
			coupon.setExpireDate(new Date(couponJsonObj.getLong("expireDate")));
			coupon.setStatus(couponJsonObj.getString("status"));
			coupon.setDefineId(couponJsonObj.getLong("defineId"));
			coupon.setRemark(couponJsonObj.getString("remark"));
			coupon.setCreateDate(new Date(couponJsonObj.getLong("createDate")));
			coupon.setModifyDate(new Date(couponJsonObj.getLong("modifyDate")));
			restList.add(coupon);
		}
	}

	@Override
	public Map<String, Object> findComments(int pagerSize, int pagerNumber, Date lastSysDate, List<Comment> restList)
			throws Exception {
		D2cApiOauth d2cApiOauth = D2cApiOauth.getInstance();
		List<Map<String, Object>> params = d2cApiOauth.getParams("/api/crm/comment/list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagerSize", pagerSize);
		map.put("pagerNumber", pagerNumber);
		map.put("lastSysDate", lastSysDate.getTime());
		params.add(map);
		JSONObject json = d2cApiOauth.invoke();
		int pageCount = json.getInt("pageCount");
		handleComments(json, restList);
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("pageCount", pageCount);
		restMap.put("datas", restList);
		return restMap;
	}

	private void handleComments(JSONObject json, List<Comment> restList) throws JSONException {
		JSONArray commentArray = json.getJSONArray("datas");
		if (commentArray == null || commentArray.length() == 0) {
			return;
		}
		for (int i = 0; i < commentArray.length(); i++) {
			JSONObject tempJsonObj = commentArray.getJSONObject(i);
			Comment temp = new Comment();
			temp.setId(tempJsonObj.getLong("id"));
			temp.setLoginCode(tempJsonObj.getString("loginCode"));
			temp.setProductSn(tempJsonObj.getString("productSn"));
			temp.setHeadPic(tempJsonObj.getString("headPic"));
			temp.setContent(tempJsonObj.getString("content"));
			temp.setPic(tempJsonObj.getString("pic"));
			temp.setSalesProperty(tempJsonObj.getString("salesProperty"));
			temp.setProductScore(tempJsonObj.getInt("productScore"));
			temp.setPackageScore(tempJsonObj.getInt("packageScore"));
			temp.setDeliveryServiceScore(tempJsonObj.getInt("deliveryServiceScore"));
			temp.setDeliverySpeedScore(tempJsonObj.getInt("deliverySpeedScore"));
			temp.setDevice(tempJsonObj.getString("device"));
			temp.setAppVersion(tempJsonObj.getString("appVersion"));
			temp.setCreateDate(new Date(tempJsonObj.getLong("createDate")));
			temp.setModifyDate(new Date(tempJsonObj.getLong("modifyDate")));
			restList.add(temp);
		}
	}

	@Override
	public Map<String, Object> findRecharges(int pagerSize, int pagerNumber, Date lastSysDate, List<Recharge> restList)
			throws Exception {
		D2cApiOauth d2cApiOauth = D2cApiOauth.getInstance();
		List<Map<String, Object>> params = d2cApiOauth.getParams("/api/crm/recharge/list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagerSize", pagerSize);
		map.put("pagerNumber", pagerNumber);
		map.put("lastSysDate", lastSysDate.getTime());
		params.add(map);
		JSONObject json = d2cApiOauth.invoke();
		int pageCount = json.getInt("pageCount");
		handleRecharges(json, restList);
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("pageCount", pageCount);
		restMap.put("datas", restList);
		return restMap;
	}

	private void handleRecharges(JSONObject json, List<Recharge> restList) throws JSONException {
		JSONArray rechargeArray = json.getJSONArray("datas");
		if (rechargeArray == null || rechargeArray.length() == 0) {
			return;
		}
		for (int i = 0; i < rechargeArray.length(); i++) {
			JSONObject tempJsonObj = rechargeArray.getJSONObject(i);
			Recharge temp = new Recharge();
			temp.setId(tempJsonObj.getLong("id"));
			temp.setSn(tempJsonObj.getString("sn"));
			temp.setLoginCode(tempJsonObj.getString("loginCode"));
			temp.setRechargeAmount(new BigDecimal(tempJsonObj.getDouble("rechargeAmount")));
			temp.setGiftAmount(new BigDecimal(tempJsonObj.getDouble("giftAmount")));
			temp.setRuleName(tempJsonObj.getString("ruleName"));
			temp.setPayDate(new Date(tempJsonObj.getLong("payDate")));
			temp.setStatus(tempJsonObj.getInt("status"));
			temp.setPayType(tempJsonObj.getInt("payType"));
			temp.setCreateDate(new Date(tempJsonObj.getLong("createDate")));
			temp.setModifyDate(new Date(tempJsonObj.getLong("modifyDate")));
			restList.add(temp);
		}
	}

	@Override
	public Map<String, Object> findOnlineOrders(int pagerSize, int pagerNumber, Date lastSysDate,
			List<OnlineOrder> restList) throws Exception {
		D2cApiOauth d2cApiOauth = D2cApiOauth.getInstance();
		List<Map<String, Object>> params = d2cApiOauth.getParams("/api/crm/order/list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagerSize", pagerSize);
		map.put("pagerNumber", pagerNumber);
		map.put("lastSysDate", lastSysDate.getTime());
		params.add(map);
		JSONObject json = d2cApiOauth.invoke();
		int pageCount = json.getInt("pageCount");
		handleOnlineOrders(json, restList);
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("pageCount", pageCount);
		restMap.put("datas", restList);
		return restMap;
	}

	private void handleOnlineOrders(JSONObject json, List<OnlineOrder> restList) throws JSONException {
		JSONArray onlineOrdersArray = json.getJSONArray("datas");
		if (onlineOrdersArray == null || onlineOrdersArray.length() == 0) {
			return;
		}
		for (int i = 0; i < onlineOrdersArray.length(); i++) {
			JSONObject orderJsonObj = onlineOrdersArray.getJSONObject(i);
			OnlineOrder onlineOrder = new OnlineOrder();
			onlineOrder.setId(orderJsonObj.getLong("id"));
			onlineOrder.setLoginCode(orderJsonObj.getString("loginCode"));
			onlineOrder.setReceiver(orderJsonObj.getString("receiver"));
			onlineOrder.setContact(orderJsonObj.getString("contact"));
			onlineOrder.setProvince(orderJsonObj.getString("province"));
			onlineOrder.setCity(orderJsonObj.getString("city"));
			onlineOrder.setDistrict(orderJsonObj.getString("district"));
			onlineOrder.setAddress(orderJsonObj.getString("address"));
			onlineOrder.setSn(orderJsonObj.getString("sn"));
			onlineOrder.setSource(orderJsonObj.getString("source"));
			onlineOrder.setStatus(orderJsonObj.getInt("status"));
			onlineOrder.setTotalAmount(new BigDecimal(orderJsonObj.getDouble("totalAmount")));
			onlineOrder.setPayAmount(new BigDecimal(orderJsonObj.getDouble("payAmount")));
			onlineOrder.setProductTotalAmount(new BigDecimal(orderJsonObj.getDouble("productTotalAmount")));
			onlineOrder.setOrderPromotionAmount(new BigDecimal(orderJsonObj.getDouble("orderPromotionAmount")));
			onlineOrder.setProductPromotionAmount(new BigDecimal(orderJsonObj.getDouble("productPromotionAmount")));
			onlineOrder.setCouponAmount(new BigDecimal(orderJsonObj.getDouble("couponAmount")));
			onlineOrder.setShippingRates(new BigDecimal(orderJsonObj.getDouble("shippingRates")));
			onlineOrder.setDeliverySn(orderJsonObj.getString("deliverySn"));
			onlineOrder.setDeliveryComp(orderJsonObj.getString("deliveryComp"));
			onlineOrder.setDeliveryTime(new Date(orderJsonObj.getLong("deliveryTime")));
			onlineOrder.setPaymentTime(new Date(orderJsonObj.getLong("paymentTime")));
			onlineOrder.setCouponed(orderJsonObj.getInt("couponed"));
			onlineOrder.setCouponInfo(orderJsonObj.getString("couponInfo"));
			onlineOrder.setCreateDate(new Date(orderJsonObj.getLong("createDate")));
			onlineOrder.setModifyDate(new Date(orderJsonObj.getLong("modifyDate")));
			onlineOrder.setUpStatus(1);
			restList.add(onlineOrder);
		}
	}

	@Override
	public Map<String, Object> findOnlineOrderItems(int pagerSize, int pagerNumber, Date lastSysDate,
			List<OnlineOrderItem> restList) throws Exception {
		D2cApiOauth d2cApiOauth = D2cApiOauth.getInstance();
		List<Map<String, Object>> params = d2cApiOauth.getParams("/api/crm/orderitem/list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagerSize", pagerSize);
		map.put("pagerNumber", pagerNumber);
		map.put("lastSysDate", lastSysDate.getTime());
		params.add(map);
		JSONObject json = d2cApiOauth.invoke();
		int pageCount = json.getInt("pageCount");
		handleOnlineOrderItems(json, restList);
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("pageCount", pageCount);
		restMap.put("datas", restList);
		return restMap;
	}

	private void handleOnlineOrderItems(JSONObject json, List<OnlineOrderItem> restList) throws JSONException {
		JSONArray onlineOrderItemsArray = json.getJSONArray("datas");
		if (onlineOrderItemsArray == null || onlineOrderItemsArray.length() == 0) {
			return;
		}
		for (int i = 0; i < onlineOrderItemsArray.length(); i++) {
			JSONObject orderItemJsonObj = onlineOrderItemsArray.getJSONObject(i);
			OnlineOrderItem temp = new OnlineOrderItem();
			temp.setId(orderItemJsonObj.getLong("id"));
			temp.setOrderSn(orderItemJsonObj.getString("orderSn"));
			temp.setProductSn(orderItemJsonObj.getString("productSn"));
			temp.setProductName(orderItemJsonObj.getString("productName"));
			temp.setProductImg(orderItemJsonObj.getString("productImg"));
			temp.setQuantity(orderItemJsonObj.getInt("quantity"));
			temp.setOriginalPrice(new BigDecimal(orderItemJsonObj.getDouble("originalPrice")));
			temp.setSalePrice(new BigDecimal(orderItemJsonObj.getDouble("salePrice")));
			temp.setCouponAmount(new BigDecimal(orderItemJsonObj.getDouble("couponAmount")));
			temp.setOrderPromotionAmount(new BigDecimal(orderItemJsonObj.getDouble("orderPromotionAmount")));
			temp.setSale1(orderItemJsonObj.getString("sale1"));
			temp.setSale2(orderItemJsonObj.getString("sale2"));
			temp.setProductSkuSn(orderItemJsonObj.getString("productSkuSn"));
			temp.setBrandCode(orderItemJsonObj.getString("brandCode"));
			temp.setBrandName(orderItemJsonObj.getString("brandName"));
			temp.setShopCode(orderItemJsonObj.getString("shopCode"));
			temp.setShopName(orderItemJsonObj.getString("shopName"));
			temp.setPromotionAmount(new BigDecimal(orderItemJsonObj.getDouble("promotionAmount")));
			temp.setCreateDate(new Date(orderItemJsonObj.getLong("createDate")));
			temp.setModifyDate(new Date(orderItemJsonObj.getLong("modifyDate")));
			temp.setUpStatus(1);
			restList.add(temp);
		}
	}

	@Override
	public Map<String, Object> findOnlineMemberInfos(int pagerSize, int pagerNumber, Date lastSysDate,
			List<OnlineMemberInfo> restList) throws Exception {
		D2cApiOauth d2cApiOauth = D2cApiOauth.getInstance();
		List<Map<String, Object>> params = d2cApiOauth.getParams("/api/crm/memberinfo/list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagerSize", pagerSize);
		map.put("pagerNumber", pagerNumber);
		map.put("lastSysDate", lastSysDate.getTime());
		params.add(map);
		JSONObject json = d2cApiOauth.invoke();
		int pageCount = json.getInt("pageCount");
		handleOnlineMemberInfos(json, restList);
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("pageCount", pageCount);
		restMap.put("datas", restList);
		return restMap;
	}

	private void handleOnlineMemberInfos(JSONObject json, List<OnlineMemberInfo> restList) throws JSONException {
		JSONArray onlineMemberInfosArray = json.getJSONArray("datas");
		if (onlineMemberInfosArray == null || onlineMemberInfosArray.length() == 0) {
			return;
		}
		for (int i = 0; i < onlineMemberInfosArray.length(); i++) {
			JSONObject orderItemJsonObj = onlineMemberInfosArray.getJSONObject(i);
			OnlineMemberInfo temp = new OnlineMemberInfo();
			temp.setId(orderItemJsonObj.getLong("id"));
			temp.setLoginCode(orderItemJsonObj.getString("loginCode"));
			temp.setType(orderItemJsonObj.getInt("type"));
			temp.setMobile(orderItemJsonObj.getString("mobile"));
			temp.setNickname(orderItemJsonObj.getString("nickname"));
			temp.setName(orderItemJsonObj.getString("name"));
			temp.setProvince(orderItemJsonObj.getString("province"));
			temp.setCity(orderItemJsonObj.getString("city"));
			temp.setDistrict(orderItemJsonObj.getString("district"));
			temp.setStreet(orderItemJsonObj.getString("street"));
			temp.setBirthday(new Date(orderItemJsonObj.getLong("birthday")));
			temp.setWeixin(orderItemJsonObj.getString("weixin"));
			temp.setQq(orderItemJsonObj.getString("qq"));
			temp.setEmail(orderItemJsonObj.getString("email"));
			temp.setHeadPic(orderItemJsonObj.getString("headPic"));
			temp.setHeight(new BigDecimal(orderItemJsonObj.getDouble("height")));
			temp.setWeight(new BigDecimal(orderItemJsonObj.getDouble("weight")));
			temp.setChest(new BigDecimal(orderItemJsonObj.getDouble("chest")));
			temp.setWaistline(new BigDecimal(orderItemJsonObj.getDouble("waistline")));
			temp.setFootLength(new BigDecimal(orderItemJsonObj.getDouble("footLength")));
			temp.setChest(new BigDecimal(orderItemJsonObj.getDouble("chest")));
			temp.setSource(orderItemJsonObj.getString("source"));
			temp.setReferee(orderItemJsonObj.getString("referee"));
			temp.setCreateDate(new Date(orderItemJsonObj.getLong("createDate")));
			temp.setModifyDate(new Date(orderItemJsonObj.getLong("modifyDate")));
			temp.setUpStatus(1);
			restList.add(temp);
		}
	}

	@Override
	public Map<String, Object> findOnlineMembers(int pagerSize, int pagerNumber, Date lastSysDate,
			List<OnlineMember> restList) throws Exception {
		D2cApiOauth d2cApiOauth = D2cApiOauth.getInstance();
		List<Map<String, Object>> params = d2cApiOauth.getParams("/api/crm/member/list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagerSize", pagerSize);
		map.put("pagerNumber", pagerNumber);
		map.put("lastSysDate", lastSysDate.getTime());
		params.add(map);
		JSONObject json = d2cApiOauth.invoke();
		int pageCount = json.getInt("pageCount");
		handleOnlineMembers(json, restList);
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("pageCount", pageCount);
		restMap.put("datas", restList);
		return restMap;
	}

	private void handleOnlineMembers(JSONObject json, List<OnlineMember> restList) throws JSONException {
		JSONArray onlineMembersArray = json.getJSONArray("datas");
		if (onlineMembersArray == null || onlineMembersArray.length() == 0) {
			return;
		}
		for (int i = 0; i < onlineMembersArray.length(); i++) {
			JSONObject orderItemJsonObj = onlineMembersArray.getJSONObject(i);
			OnlineMember temp = new OnlineMember();
			temp.setId(orderItemJsonObj.getLong("id"));
			temp.setLoginCode(orderItemJsonObj.getString("loginCode"));
			temp.setLoginIp(orderItemJsonObj.getString("loginIp"));
			temp.setLoginNumber(orderItemJsonObj.getInt("loginNumber"));
			temp.setLoginDate(new Date(orderItemJsonObj.getLong("loginDate")));
			temp.setSource(orderItemJsonObj.getString("source"));
			temp.setDevice(orderItemJsonObj.getString("device"));
			temp.setOpenId(orderItemJsonObj.getString("openId"));
			temp.setUserInfo(orderItemJsonObj.getString("userInfo"));
			temp.setMemberInfoId(orderItemJsonObj.getLong("memberInfoId"));
			temp.setOldMemberInfoId(orderItemJsonObj.getLong("oldMemberInfoId"));
			temp.setDeviceLabel(orderItemJsonObj.getString("deviceLabel"));
			temp.setBindDate(new Date(orderItemJsonObj.getLong("bindDate")));
			temp.setCreateDate(new Date(orderItemJsonObj.getLong("createDate")));
			temp.setModifyDate(new Date(orderItemJsonObj.getLong("modifyDate")));
			temp.setUpStatus(1);
			restList.add(temp);
		}
	}

	@Override
	public Map<String, Object> findOlineRefunds(int pagerSize, int pagerNumber, Date lastSysDate, List<Refund> restList)
			throws Exception {
		D2cApiOauth d2cApiOauth = D2cApiOauth.getInstance();
		List<Map<String, Object>> params = d2cApiOauth.getParams("/api/crm/refund/list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagerSize", pagerSize);
		map.put("pagerNumber", pagerNumber);
		map.put("lastSysDate", lastSysDate.getTime());
		params.add(map);
		JSONObject json = d2cApiOauth.invoke();
		int pageCount = json.getInt("pageCount");
		handleOlineRefunds(json, restList);
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("pageCount", pageCount);
		restMap.put("datas", restList);
		return restMap;
	}

	private void handleOlineRefunds(JSONObject json, List<Refund> restList) throws JSONException {
		JSONArray refundArray = json.getJSONArray("datas");
		if (refundArray == null || refundArray.length() == 0) {
			return;
		}
		for (int i = 0; i < refundArray.length(); i++) {
			JSONObject tempJsonObj = refundArray.getJSONObject(i);
			Refund temp = new Refund();
			temp.setId(tempJsonObj.getLong("id"));
			temp.setSn(tempJsonObj.getString("sn"));
			temp.setLoginCode(tempJsonObj.getString("loginCode"));
			temp.setTradeAmount(new BigDecimal(tempJsonObj.getDouble("tradeAmount")));
			temp.setStatus(tempJsonObj.getInt("status"));
			temp.setOrderSn(tempJsonObj.getString("orderSn"));
			temp.setReason(tempJsonObj.getString("reason"));
			temp.setQuantity(tempJsonObj.getInt("quantity"));
			temp.setPrice(new BigDecimal(tempJsonObj.getDouble("price")));
			temp.setProductSn(tempJsonObj.getString("productSn"));
			temp.setProductSkuSn(tempJsonObj.getString("productSkuSn"));
			temp.setBrandCode(tempJsonObj.getString("brandCode"));
			temp.setBrandName(tempJsonObj.getString("brandName"));
			temp.setApplyAmount(new BigDecimal(tempJsonObj.getDouble("applyAmount")));
			temp.setTotalAmount(new BigDecimal(tempJsonObj.getDouble("totalAmount")));
			temp.setPayAmount(new BigDecimal(tempJsonObj.getDouble("payAmount")));
			temp.setPayDate(new Date(tempJsonObj.getLong("payDate")));
			temp.setCreateDate(new Date(tempJsonObj.getLong("createDate")));
			temp.setModifyDate(new Date(tempJsonObj.getLong("modifyDate")));
			restList.add(temp);
		}
	}

	@Override
	public Map<String, Object> findOnlineReships(int pagerSize, int pagerNumber, Date lastSysDate,
			List<Reship> restList) throws Exception {
		D2cApiOauth d2cApiOauth = D2cApiOauth.getInstance();
		List<Map<String, Object>> params = d2cApiOauth.getParams("/api/crm/reship/list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagerSize", pagerSize);
		map.put("pagerNumber", pagerNumber);
		map.put("lastSysDate", lastSysDate.getTime());
		params.add(map);
		JSONObject json = d2cApiOauth.invoke();
		int pageCount = json.getInt("pageCount");
		handleOnlineReships(json, restList);
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("pageCount", pageCount);
		restMap.put("datas", restList);
		return restMap;
	}

	private void handleOnlineReships(JSONObject json, List<Reship> restList) throws JSONException {
		JSONArray reshipArray = json.getJSONArray("datas");
		if (reshipArray == null || reshipArray.length() == 0) {
			return;
		}
		for (int i = 0; i < reshipArray.length(); i++) {
			JSONObject tempJsonObj = reshipArray.getJSONObject(i);
			Reship temp = new Reship();
			temp.setId(tempJsonObj.getLong("id"));
			temp.setSn(tempJsonObj.getString("sn"));
			temp.setLoginCode(tempJsonObj.getString("loginCode"));
			temp.setTradeAmount(new BigDecimal(tempJsonObj.getDouble("tradeAmount")));
			temp.setStatus(tempJsonObj.getInt("status"));
			temp.setOrderSn(tempJsonObj.getString("orderSn"));
			temp.setReason(tempJsonObj.getString("reason"));
			temp.setDeliverySn(tempJsonObj.getString("deliverySn"));
			temp.setDeliveryComp(tempJsonObj.getString("deliveryComp"));
			temp.setQuantity(tempJsonObj.getInt("quantity"));
			temp.setProductSn(tempJsonObj.getString("productSn"));
			temp.setProductSkuSn(tempJsonObj.getString("productSkuSn"));
			temp.setBrandCode(tempJsonObj.getString("brandCode"));
			temp.setBrandName(tempJsonObj.getString("brandName"));
			temp.setCreateDate(new Date(tempJsonObj.getLong("createDate")));
			temp.setModifyDate(new Date(tempJsonObj.getLong("modifyDate")));
			restList.add(temp);
		}
	}

	@Override
	public Map<String, Object> findOnlineExchanges(int pagerSize, int pagerNumber, Date lastSysDate,
			List<Exchange> restList) throws Exception {
		D2cApiOauth d2cApiOauth = D2cApiOauth.getInstance();
		List<Map<String, Object>> params = d2cApiOauth.getParams("/api/crm/exchange/list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagerSize", pagerSize);
		map.put("pagerNumber", pagerNumber);
		map.put("lastSysDate", lastSysDate.getTime());
		params.add(map);
		JSONObject json = d2cApiOauth.invoke();
		int pageCount = json.getInt("pageCount");
		handleOnlineExchanges(json, restList);
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("pageCount", pageCount);
		restMap.put("datas", restList);
		return restMap;
	}

	private void handleOnlineExchanges(JSONObject json, List<Exchange> restList) throws JSONException {
		JSONArray exchangeArray = json.getJSONArray("datas");
		if (exchangeArray == null || exchangeArray.length() == 0) {
			return;
		}
		for (int i = 0; i < exchangeArray.length(); i++) {
			JSONObject tempJsonObj = exchangeArray.getJSONObject(i);
			Exchange temp = new Exchange();
			temp.setId(tempJsonObj.getLong("id"));
			temp.setSn(tempJsonObj.getString("sn"));
			temp.setLoginCode(tempJsonObj.getString("loginCode"));
			temp.setStatus(tempJsonObj.getInt("status"));
			temp.setOrderSn(tempJsonObj.getString("orderSn"));
			temp.setReason(tempJsonObj.getString("reason"));
			temp.setMemo(tempJsonObj.getString("memo"));
			temp.setQuantity(tempJsonObj.getInt("quantity"));
			temp.setProductSn(tempJsonObj.getString("productSn"));
			temp.setProductSkuSn(tempJsonObj.getString("productSkuSn"));
			temp.setBrandCode(tempJsonObj.getString("brandCode"));
			temp.setBrandName(tempJsonObj.getString("brandName"));
			temp.setCreateDate(new Date(tempJsonObj.getLong("createDate")));
			temp.setModifyDate(new Date(tempJsonObj.getLong("modifyDate")));
			restList.add(temp);
		}
	}

	@Override
	public Map<String, Object> findOnlineProducts(int pagerSize, int pagerNumber, Date lastSysDate,
			List<Product> restList) throws Exception {
		D2cApiOauth d2cApiOauth = D2cApiOauth.getInstance();
		List<Map<String, Object>> params = d2cApiOauth.getParams("/api/crm/product/list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagerSize", pagerSize);
		map.put("pagerNumber", pagerNumber);
		map.put("lastSysDate", lastSysDate.getTime());
		params.add(map);
		JSONObject json = d2cApiOauth.invoke();
		int pageCount = json.getInt("pageCount");
		handleOnlineProducts(json, restList);
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("pageCount", pageCount);
		restMap.put("datas", restList);
		return restMap;
	}

	private void handleOnlineProducts(JSONObject json, List<Product> restList) throws JSONException {
		JSONArray productArray = json.getJSONArray("datas");
		if (productArray == null || productArray.length() == 0) {
			return;
		}
		for (int i = 0; i < productArray.length(); i++) {
			JSONObject tempJsonObj = productArray.getJSONObject(i);
			Product temp = new Product();
			temp.setId(tempJsonObj.getLong("id"));
			temp.setSn(tempJsonObj.getString("sn"));
			temp.setExternalSn(tempJsonObj.getString("externalSn"));
			temp.setName(tempJsonObj.getString("name"));
			temp.setTags(tempJsonObj.getString("tags"));
			temp.setImg(tempJsonObj.getString("img"));
			temp.setRemark(tempJsonObj.getString("remark"));
			temp.setOriginalPrice(new BigDecimal(tempJsonObj.getDouble("originalPrice")));
			// temp.setSalePrice(new
			// BigDecimal(tempJsonObj.getDouble("salePrice")));
			temp.setLeafCategory(tempJsonObj.getString("leafCategory"));
			temp.setBrandCode(tempJsonObj.getString("brandCode"));
			temp.setBrandName(tempJsonObj.getString("brandName"));
			temp.setCreateDate(new Date(tempJsonObj.getLong("createDate")));
			temp.setModifyDate(new Date(tempJsonObj.getLong("modifyDate")));
			restList.add(temp);
		}
	}

	@Override
	public Map<String, Object> findOnlineProductSkus(int pagerSize, int pagerNumber, Date lastSysDate,
			List<ProductSku> restList) throws Exception {
		D2cApiOauth d2cApiOauth = D2cApiOauth.getInstance();
		List<Map<String, Object>> params = d2cApiOauth.getParams("/api/crm/productsku/list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagerSize", pagerSize);
		map.put("pagerNumber", pagerNumber);
		map.put("lastSysDate", lastSysDate.getTime());
		params.add(map);
		JSONObject json = d2cApiOauth.invoke();
		int pageCount = json.getInt("pageCount");
		handleOnlineProductSkus(json, restList);
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("pageCount", pageCount);
		restMap.put("datas", restList);
		return restMap;
	}

	private void handleOnlineProductSkus(JSONObject json, List<ProductSku> restList) throws JSONException {
		JSONArray productSkuArray = json.getJSONArray("datas");
		if (productSkuArray == null || productSkuArray.length() == 0) {
			return;
		}
		for (int i = 0; i < productSkuArray.length(); i++) {
			JSONObject tempJsonObj = productSkuArray.getJSONObject(i);
			ProductSku temp = new ProductSku();
			temp.setId(tempJsonObj.getLong("id"));
			temp.setSn(tempJsonObj.getString("sn"));

			temp.setCreateDate(new Date(tempJsonObj.getLong("createDate")));
			temp.setModifyDate(new Date(tempJsonObj.getLong("modifyDate")));
			restList.add(temp);
		}
	}

	@Override
	public Map<String, Object> findOnlineBrands(int pagerSize, int pagerNumber, Date lastSysDate, List<Brand> restList)
			throws Exception {
		D2cApiOauth d2cApiOauth = D2cApiOauth.getInstance();
		List<Map<String, Object>> params = d2cApiOauth.getParams("/api/crm/designer/list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagerSize", pagerSize);
		map.put("pagerNumber", pagerNumber);
		map.put("lastSysDate", lastSysDate.getTime());
		params.add(map);
		JSONObject json = d2cApiOauth.invoke();
		int pageCount = json.getInt("pageCount");
		handleOnlineBrands(json, restList);
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("pageCount", pageCount);
		restMap.put("datas", restList);
		return restMap;
	}

	private void handleOnlineBrands(JSONObject json, List<Brand> restList) throws JSONException {
		JSONArray brandArray = json.getJSONArray("datas");
		if (brandArray == null || brandArray.length() == 0) {
			return;
		}
		for (int i = 0; i < brandArray.length(); i++) {
			JSONObject tempJsonObj = brandArray.getJSONObject(i);
			Brand temp = new Brand();
			temp.setId(tempJsonObj.getLong("id"));
			temp.setCode(tempJsonObj.getString("code"));
			temp.setName(tempJsonObj.getString("name"));
			temp.setDesigner(tempJsonObj.getString("designer"));
			temp.setCountry(tempJsonObj.getString("country"));
			temp.setShop(tempJsonObj.getString("shop"));
			temp.setDescription(tempJsonObj.getString("description"));
			temp.setTopCategory(tempJsonObj.getString("topCategory"));
			temp.setSecCategory(tempJsonObj.getString("secCategory"));
			temp.setTags(tempJsonObj.getString("tags"));
			temp.setPageGroup(tempJsonObj.getString("pageGroup"));
			temp.setCreateDate(new Date(tempJsonObj.getLong("createDate")));
			temp.setModifyDate(new Date(tempJsonObj.getLong("modifyDate")));
			restList.add(temp);
		}
	}

	@Override
	public Map<String, Object> findOnlineShops(int pagerSize, int pagerNumber, Date lastSysDate, List<Shop> restList)
			throws Exception {
		D2cApiOauth d2cApiOauth = D2cApiOauth.getInstance();
		List<Map<String, Object>> params = d2cApiOauth.getParams("/api/crm/shop/list");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagerSize", pagerSize);
		map.put("pagerNumber", pagerNumber);
		map.put("lastSysDate", lastSysDate.getTime());
		params.add(map);
		JSONObject json = d2cApiOauth.invoke();
		int pageCount = json.getInt("pageCount");
		handleOnlineShops(json, restList);
		Map<String, Object> restMap = new HashMap<String, Object>();
		restMap.put("pageCount", pageCount);
		restMap.put("datas", restList);
		return restMap;
	}

	private void handleOnlineShops(JSONObject json, List<Shop> restList) throws JSONException {
		JSONArray shopArray = json.getJSONArray("datas");
		if (shopArray == null || shopArray.length() == 0) {
			return;
		}
		for (int i = 0; i < shopArray.length(); i++) {
			JSONObject tempJsonObj = shopArray.getJSONObject(i);
			Shop temp = new Shop();
			temp.setId(tempJsonObj.getLong("id"));
			temp.setCode(tempJsonObj.getString("code"));
			temp.setName(tempJsonObj.getString("name"));
			temp.setAddress(tempJsonObj.getString("address"));
			temp.setProvince(tempJsonObj.getString("province"));
			temp.setCity(tempJsonObj.getString("city"));
			temp.setDistrict(tempJsonObj.getString("district"));
			temp.setStatus(tempJsonObj.getString("status"));
			temp.setCreateDate(new Date(tempJsonObj.getLong("createDate")));
			temp.setModifyDate(new Date(tempJsonObj.getLong("modifyDate")));
			restList.add(temp);
		}
	}

}
