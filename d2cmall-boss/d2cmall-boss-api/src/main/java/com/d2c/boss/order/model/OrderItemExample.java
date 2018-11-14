package com.d2c.boss.order.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderItemExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public OrderItemExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Long value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Long value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Long value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Long value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Long value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Long value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Long> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Long> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Long value1, Long value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Long value1, Long value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andCreateDateIsNull() {
			addCriterion("create_date is null");
			return (Criteria) this;
		}

		public Criteria andCreateDateIsNotNull() {
			addCriterion("create_date is not null");
			return (Criteria) this;
		}

		public Criteria andCreateDateEqualTo(Date value) {
			addCriterion("create_date =", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotEqualTo(Date value) {
			addCriterion("create_date <>", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateGreaterThan(Date value) {
			addCriterion("create_date >", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
			addCriterion("create_date >=", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateLessThan(Date value) {
			addCriterion("create_date <", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateLessThanOrEqualTo(Date value) {
			addCriterion("create_date <=", value, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateIn(List<Date> values) {
			addCriterion("create_date in", values, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotIn(List<Date> values) {
			addCriterion("create_date not in", values, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateBetween(Date value1, Date value2) {
			addCriterion("create_date between", value1, value2, "createDate");
			return (Criteria) this;
		}

		public Criteria andCreateDateNotBetween(Date value1, Date value2) {
			addCriterion("create_date not between", value1, value2, "createDate");
			return (Criteria) this;
		}

		public Criteria andModifyDateIsNull() {
			addCriterion("modify_date is null");
			return (Criteria) this;
		}

		public Criteria andModifyDateIsNotNull() {
			addCriterion("modify_date is not null");
			return (Criteria) this;
		}

		public Criteria andModifyDateEqualTo(Date value) {
			addCriterion("modify_date =", value, "modifyDate");
			return (Criteria) this;
		}

		public Criteria andModifyDateNotEqualTo(Date value) {
			addCriterion("modify_date <>", value, "modifyDate");
			return (Criteria) this;
		}

		public Criteria andModifyDateGreaterThan(Date value) {
			addCriterion("modify_date >", value, "modifyDate");
			return (Criteria) this;
		}

		public Criteria andModifyDateGreaterThanOrEqualTo(Date value) {
			addCriterion("modify_date >=", value, "modifyDate");
			return (Criteria) this;
		}

		public Criteria andModifyDateLessThan(Date value) {
			addCriterion("modify_date <", value, "modifyDate");
			return (Criteria) this;
		}

		public Criteria andModifyDateLessThanOrEqualTo(Date value) {
			addCriterion("modify_date <=", value, "modifyDate");
			return (Criteria) this;
		}

		public Criteria andModifyDateIn(List<Date> values) {
			addCriterion("modify_date in", values, "modifyDate");
			return (Criteria) this;
		}

		public Criteria andModifyDateNotIn(List<Date> values) {
			addCriterion("modify_date not in", values, "modifyDate");
			return (Criteria) this;
		}

		public Criteria andModifyDateBetween(Date value1, Date value2) {
			addCriterion("modify_date between", value1, value2, "modifyDate");
			return (Criteria) this;
		}

		public Criteria andModifyDateNotBetween(Date value1, Date value2) {
			addCriterion("modify_date not between", value1, value2, "modifyDate");
			return (Criteria) this;
		}

		public Criteria andLastModifyManIsNull() {
			addCriterion("last_modify_man is null");
			return (Criteria) this;
		}

		public Criteria andLastModifyManIsNotNull() {
			addCriterion("last_modify_man is not null");
			return (Criteria) this;
		}

		public Criteria andLastModifyManEqualTo(String value) {
			addCriterion("last_modify_man =", value, "lastModifyMan");
			return (Criteria) this;
		}

		public Criteria andLastModifyManNotEqualTo(String value) {
			addCriterion("last_modify_man <>", value, "lastModifyMan");
			return (Criteria) this;
		}

		public Criteria andLastModifyManGreaterThan(String value) {
			addCriterion("last_modify_man >", value, "lastModifyMan");
			return (Criteria) this;
		}

		public Criteria andLastModifyManGreaterThanOrEqualTo(String value) {
			addCriterion("last_modify_man >=", value, "lastModifyMan");
			return (Criteria) this;
		}

		public Criteria andLastModifyManLessThan(String value) {
			addCriterion("last_modify_man <", value, "lastModifyMan");
			return (Criteria) this;
		}

		public Criteria andLastModifyManLessThanOrEqualTo(String value) {
			addCriterion("last_modify_man <=", value, "lastModifyMan");
			return (Criteria) this;
		}

		public Criteria andLastModifyManLike(String value) {
			addCriterion("last_modify_man like", value, "lastModifyMan");
			return (Criteria) this;
		}

		public Criteria andLastModifyManNotLike(String value) {
			addCriterion("last_modify_man not like", value, "lastModifyMan");
			return (Criteria) this;
		}

		public Criteria andLastModifyManIn(List<String> values) {
			addCriterion("last_modify_man in", values, "lastModifyMan");
			return (Criteria) this;
		}

		public Criteria andLastModifyManNotIn(List<String> values) {
			addCriterion("last_modify_man not in", values, "lastModifyMan");
			return (Criteria) this;
		}

		public Criteria andLastModifyManBetween(String value1, String value2) {
			addCriterion("last_modify_man between", value1, value2, "lastModifyMan");
			return (Criteria) this;
		}

		public Criteria andLastModifyManNotBetween(String value1, String value2) {
			addCriterion("last_modify_man not between", value1, value2, "lastModifyMan");
			return (Criteria) this;
		}

		public Criteria andCreatorIsNull() {
			addCriterion("creator is null");
			return (Criteria) this;
		}

		public Criteria andCreatorIsNotNull() {
			addCriterion("creator is not null");
			return (Criteria) this;
		}

		public Criteria andCreatorEqualTo(String value) {
			addCriterion("creator =", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorNotEqualTo(String value) {
			addCriterion("creator <>", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorGreaterThan(String value) {
			addCriterion("creator >", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorGreaterThanOrEqualTo(String value) {
			addCriterion("creator >=", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorLessThan(String value) {
			addCriterion("creator <", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorLessThanOrEqualTo(String value) {
			addCriterion("creator <=", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorLike(String value) {
			addCriterion("creator like", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorNotLike(String value) {
			addCriterion("creator not like", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorIn(List<String> values) {
			addCriterion("creator in", values, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorNotIn(List<String> values) {
			addCriterion("creator not in", values, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorBetween(String value1, String value2) {
			addCriterion("creator between", value1, value2, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorNotBetween(String value1, String value2) {
			addCriterion("creator not between", value1, value2, "creator");
			return (Criteria) this;
		}

		public Criteria andOrderSnIsNull() {
			addCriterion("order_sn is null");
			return (Criteria) this;
		}

		public Criteria andOrderSnIsNotNull() {
			addCriterion("order_sn is not null");
			return (Criteria) this;
		}

		public Criteria andOrderSnEqualTo(String value) {
			addCriterion("order_sn =", value, "orderSn");
			return (Criteria) this;
		}

		public Criteria andOrderSnNotEqualTo(String value) {
			addCriterion("order_sn <>", value, "orderSn");
			return (Criteria) this;
		}

		public Criteria andOrderSnGreaterThan(String value) {
			addCriterion("order_sn >", value, "orderSn");
			return (Criteria) this;
		}

		public Criteria andOrderSnGreaterThanOrEqualTo(String value) {
			addCriterion("order_sn >=", value, "orderSn");
			return (Criteria) this;
		}

		public Criteria andOrderSnLessThan(String value) {
			addCriterion("order_sn <", value, "orderSn");
			return (Criteria) this;
		}

		public Criteria andOrderSnLessThanOrEqualTo(String value) {
			addCriterion("order_sn <=", value, "orderSn");
			return (Criteria) this;
		}

		public Criteria andOrderSnLike(String value) {
			addCriterion("order_sn like", value, "orderSn");
			return (Criteria) this;
		}

		public Criteria andOrderSnNotLike(String value) {
			addCriterion("order_sn not like", value, "orderSn");
			return (Criteria) this;
		}

		public Criteria andOrderSnIn(List<String> values) {
			addCriterion("order_sn in", values, "orderSn");
			return (Criteria) this;
		}

		public Criteria andOrderSnNotIn(List<String> values) {
			addCriterion("order_sn not in", values, "orderSn");
			return (Criteria) this;
		}

		public Criteria andOrderSnBetween(String value1, String value2) {
			addCriterion("order_sn between", value1, value2, "orderSn");
			return (Criteria) this;
		}

		public Criteria andOrderSnNotBetween(String value1, String value2) {
			addCriterion("order_sn not between", value1, value2, "orderSn");
			return (Criteria) this;
		}

		public Criteria andProductSnIsNull() {
			addCriterion("product_sn is null");
			return (Criteria) this;
		}

		public Criteria andProductSnIsNotNull() {
			addCriterion("product_sn is not null");
			return (Criteria) this;
		}

		public Criteria andProductSnEqualTo(String value) {
			addCriterion("product_sn =", value, "productSn");
			return (Criteria) this;
		}

		public Criteria andProductSnNotEqualTo(String value) {
			addCriterion("product_sn <>", value, "productSn");
			return (Criteria) this;
		}

		public Criteria andProductSnGreaterThan(String value) {
			addCriterion("product_sn >", value, "productSn");
			return (Criteria) this;
		}

		public Criteria andProductSnGreaterThanOrEqualTo(String value) {
			addCriterion("product_sn >=", value, "productSn");
			return (Criteria) this;
		}

		public Criteria andProductSnLessThan(String value) {
			addCriterion("product_sn <", value, "productSn");
			return (Criteria) this;
		}

		public Criteria andProductSnLessThanOrEqualTo(String value) {
			addCriterion("product_sn <=", value, "productSn");
			return (Criteria) this;
		}

		public Criteria andProductSnLike(String value) {
			addCriterion("product_sn like", value, "productSn");
			return (Criteria) this;
		}

		public Criteria andProductSnNotLike(String value) {
			addCriterion("product_sn not like", value, "productSn");
			return (Criteria) this;
		}

		public Criteria andProductSnIn(List<String> values) {
			addCriterion("product_sn in", values, "productSn");
			return (Criteria) this;
		}

		public Criteria andProductSnNotIn(List<String> values) {
			addCriterion("product_sn not in", values, "productSn");
			return (Criteria) this;
		}

		public Criteria andProductSnBetween(String value1, String value2) {
			addCriterion("product_sn between", value1, value2, "productSn");
			return (Criteria) this;
		}

		public Criteria andProductSnNotBetween(String value1, String value2) {
			addCriterion("product_sn not between", value1, value2, "productSn");
			return (Criteria) this;
		}

		public Criteria andProductNameIsNull() {
			addCriterion("product_name is null");
			return (Criteria) this;
		}

		public Criteria andProductNameIsNotNull() {
			addCriterion("product_name is not null");
			return (Criteria) this;
		}

		public Criteria andProductNameEqualTo(String value) {
			addCriterion("product_name =", value, "productName");
			return (Criteria) this;
		}

		public Criteria andProductNameNotEqualTo(String value) {
			addCriterion("product_name <>", value, "productName");
			return (Criteria) this;
		}

		public Criteria andProductNameGreaterThan(String value) {
			addCriterion("product_name >", value, "productName");
			return (Criteria) this;
		}

		public Criteria andProductNameGreaterThanOrEqualTo(String value) {
			addCriterion("product_name >=", value, "productName");
			return (Criteria) this;
		}

		public Criteria andProductNameLessThan(String value) {
			addCriterion("product_name <", value, "productName");
			return (Criteria) this;
		}

		public Criteria andProductNameLessThanOrEqualTo(String value) {
			addCriterion("product_name <=", value, "productName");
			return (Criteria) this;
		}

		public Criteria andProductNameLike(String value) {
			addCriterion("product_name like", value, "productName");
			return (Criteria) this;
		}

		public Criteria andProductNameNotLike(String value) {
			addCriterion("product_name not like", value, "productName");
			return (Criteria) this;
		}

		public Criteria andProductNameIn(List<String> values) {
			addCriterion("product_name in", values, "productName");
			return (Criteria) this;
		}

		public Criteria andProductNameNotIn(List<String> values) {
			addCriterion("product_name not in", values, "productName");
			return (Criteria) this;
		}

		public Criteria andProductNameBetween(String value1, String value2) {
			addCriterion("product_name between", value1, value2, "productName");
			return (Criteria) this;
		}

		public Criteria andProductNameNotBetween(String value1, String value2) {
			addCriterion("product_name not between", value1, value2, "productName");
			return (Criteria) this;
		}

		public Criteria andProductImgIsNull() {
			addCriterion("product_img is null");
			return (Criteria) this;
		}

		public Criteria andProductImgIsNotNull() {
			addCriterion("product_img is not null");
			return (Criteria) this;
		}

		public Criteria andProductImgEqualTo(String value) {
			addCriterion("product_img =", value, "productImg");
			return (Criteria) this;
		}

		public Criteria andProductImgNotEqualTo(String value) {
			addCriterion("product_img <>", value, "productImg");
			return (Criteria) this;
		}

		public Criteria andProductImgGreaterThan(String value) {
			addCriterion("product_img >", value, "productImg");
			return (Criteria) this;
		}

		public Criteria andProductImgGreaterThanOrEqualTo(String value) {
			addCriterion("product_img >=", value, "productImg");
			return (Criteria) this;
		}

		public Criteria andProductImgLessThan(String value) {
			addCriterion("product_img <", value, "productImg");
			return (Criteria) this;
		}

		public Criteria andProductImgLessThanOrEqualTo(String value) {
			addCriterion("product_img <=", value, "productImg");
			return (Criteria) this;
		}

		public Criteria andProductImgLike(String value) {
			addCriterion("product_img like", value, "productImg");
			return (Criteria) this;
		}

		public Criteria andProductImgNotLike(String value) {
			addCriterion("product_img not like", value, "productImg");
			return (Criteria) this;
		}

		public Criteria andProductImgIn(List<String> values) {
			addCriterion("product_img in", values, "productImg");
			return (Criteria) this;
		}

		public Criteria andProductImgNotIn(List<String> values) {
			addCriterion("product_img not in", values, "productImg");
			return (Criteria) this;
		}

		public Criteria andProductImgBetween(String value1, String value2) {
			addCriterion("product_img between", value1, value2, "productImg");
			return (Criteria) this;
		}

		public Criteria andProductImgNotBetween(String value1, String value2) {
			addCriterion("product_img not between", value1, value2, "productImg");
			return (Criteria) this;
		}

		public Criteria andTopCategoryIsNull() {
			addCriterion("top_category is null");
			return (Criteria) this;
		}

		public Criteria andTopCategoryIsNotNull() {
			addCriterion("top_category is not null");
			return (Criteria) this;
		}

		public Criteria andTopCategoryEqualTo(String value) {
			addCriterion("top_category =", value, "topCategory");
			return (Criteria) this;
		}

		public Criteria andTopCategoryNotEqualTo(String value) {
			addCriterion("top_category <>", value, "topCategory");
			return (Criteria) this;
		}

		public Criteria andTopCategoryGreaterThan(String value) {
			addCriterion("top_category >", value, "topCategory");
			return (Criteria) this;
		}

		public Criteria andTopCategoryGreaterThanOrEqualTo(String value) {
			addCriterion("top_category >=", value, "topCategory");
			return (Criteria) this;
		}

		public Criteria andTopCategoryLessThan(String value) {
			addCriterion("top_category <", value, "topCategory");
			return (Criteria) this;
		}

		public Criteria andTopCategoryLessThanOrEqualTo(String value) {
			addCriterion("top_category <=", value, "topCategory");
			return (Criteria) this;
		}

		public Criteria andTopCategoryLike(String value) {
			addCriterion("top_category like", value, "topCategory");
			return (Criteria) this;
		}

		public Criteria andTopCategoryNotLike(String value) {
			addCriterion("top_category not like", value, "topCategory");
			return (Criteria) this;
		}

		public Criteria andTopCategoryIn(List<String> values) {
			addCriterion("top_category in", values, "topCategory");
			return (Criteria) this;
		}

		public Criteria andTopCategoryNotIn(List<String> values) {
			addCriterion("top_category not in", values, "topCategory");
			return (Criteria) this;
		}

		public Criteria andTopCategoryBetween(String value1, String value2) {
			addCriterion("top_category between", value1, value2, "topCategory");
			return (Criteria) this;
		}

		public Criteria andTopCategoryNotBetween(String value1, String value2) {
			addCriterion("top_category not between", value1, value2, "topCategory");
			return (Criteria) this;
		}

		public Criteria andSecCategoryIsNull() {
			addCriterion("sec_category is null");
			return (Criteria) this;
		}

		public Criteria andSecCategoryIsNotNull() {
			addCriterion("sec_category is not null");
			return (Criteria) this;
		}

		public Criteria andSecCategoryEqualTo(String value) {
			addCriterion("sec_category =", value, "secCategory");
			return (Criteria) this;
		}

		public Criteria andSecCategoryNotEqualTo(String value) {
			addCriterion("sec_category <>", value, "secCategory");
			return (Criteria) this;
		}

		public Criteria andSecCategoryGreaterThan(String value) {
			addCriterion("sec_category >", value, "secCategory");
			return (Criteria) this;
		}

		public Criteria andSecCategoryGreaterThanOrEqualTo(String value) {
			addCriterion("sec_category >=", value, "secCategory");
			return (Criteria) this;
		}

		public Criteria andSecCategoryLessThan(String value) {
			addCriterion("sec_category <", value, "secCategory");
			return (Criteria) this;
		}

		public Criteria andSecCategoryLessThanOrEqualTo(String value) {
			addCriterion("sec_category <=", value, "secCategory");
			return (Criteria) this;
		}

		public Criteria andSecCategoryLike(String value) {
			addCriterion("sec_category like", value, "secCategory");
			return (Criteria) this;
		}

		public Criteria andSecCategoryNotLike(String value) {
			addCriterion("sec_category not like", value, "secCategory");
			return (Criteria) this;
		}

		public Criteria andSecCategoryIn(List<String> values) {
			addCriterion("sec_category in", values, "secCategory");
			return (Criteria) this;
		}

		public Criteria andSecCategoryNotIn(List<String> values) {
			addCriterion("sec_category not in", values, "secCategory");
			return (Criteria) this;
		}

		public Criteria andSecCategoryBetween(String value1, String value2) {
			addCriterion("sec_category between", value1, value2, "secCategory");
			return (Criteria) this;
		}

		public Criteria andSecCategoryNotBetween(String value1, String value2) {
			addCriterion("sec_category not between", value1, value2, "secCategory");
			return (Criteria) this;
		}

		public Criteria andLeafCategoryIsNull() {
			addCriterion("leaf_category is null");
			return (Criteria) this;
		}

		public Criteria andLeafCategoryIsNotNull() {
			addCriterion("leaf_category is not null");
			return (Criteria) this;
		}

		public Criteria andLeafCategoryEqualTo(String value) {
			addCriterion("leaf_category =", value, "leafCategory");
			return (Criteria) this;
		}

		public Criteria andLeafCategoryNotEqualTo(String value) {
			addCriterion("leaf_category <>", value, "leafCategory");
			return (Criteria) this;
		}

		public Criteria andLeafCategoryGreaterThan(String value) {
			addCriterion("leaf_category >", value, "leafCategory");
			return (Criteria) this;
		}

		public Criteria andLeafCategoryGreaterThanOrEqualTo(String value) {
			addCriterion("leaf_category >=", value, "leafCategory");
			return (Criteria) this;
		}

		public Criteria andLeafCategoryLessThan(String value) {
			addCriterion("leaf_category <", value, "leafCategory");
			return (Criteria) this;
		}

		public Criteria andLeafCategoryLessThanOrEqualTo(String value) {
			addCriterion("leaf_category <=", value, "leafCategory");
			return (Criteria) this;
		}

		public Criteria andLeafCategoryLike(String value) {
			addCriterion("leaf_category like", value, "leafCategory");
			return (Criteria) this;
		}

		public Criteria andLeafCategoryNotLike(String value) {
			addCriterion("leaf_category not like", value, "leafCategory");
			return (Criteria) this;
		}

		public Criteria andLeafCategoryIn(List<String> values) {
			addCriterion("leaf_category in", values, "leafCategory");
			return (Criteria) this;
		}

		public Criteria andLeafCategoryNotIn(List<String> values) {
			addCriterion("leaf_category not in", values, "leafCategory");
			return (Criteria) this;
		}

		public Criteria andLeafCategoryBetween(String value1, String value2) {
			addCriterion("leaf_category between", value1, value2, "leafCategory");
			return (Criteria) this;
		}

		public Criteria andLeafCategoryNotBetween(String value1, String value2) {
			addCriterion("leaf_category not between", value1, value2, "leafCategory");
			return (Criteria) this;
		}

		public Criteria andQuantityIsNull() {
			addCriterion("quantity is null");
			return (Criteria) this;
		}

		public Criteria andQuantityIsNotNull() {
			addCriterion("quantity is not null");
			return (Criteria) this;
		}

		public Criteria andQuantityEqualTo(Integer value) {
			addCriterion("quantity =", value, "quantity");
			return (Criteria) this;
		}

		public Criteria andQuantityNotEqualTo(Integer value) {
			addCriterion("quantity <>", value, "quantity");
			return (Criteria) this;
		}

		public Criteria andQuantityGreaterThan(Integer value) {
			addCriterion("quantity >", value, "quantity");
			return (Criteria) this;
		}

		public Criteria andQuantityGreaterThanOrEqualTo(Integer value) {
			addCriterion("quantity >=", value, "quantity");
			return (Criteria) this;
		}

		public Criteria andQuantityLessThan(Integer value) {
			addCriterion("quantity <", value, "quantity");
			return (Criteria) this;
		}

		public Criteria andQuantityLessThanOrEqualTo(Integer value) {
			addCriterion("quantity <=", value, "quantity");
			return (Criteria) this;
		}

		public Criteria andQuantityIn(List<Integer> values) {
			addCriterion("quantity in", values, "quantity");
			return (Criteria) this;
		}

		public Criteria andQuantityNotIn(List<Integer> values) {
			addCriterion("quantity not in", values, "quantity");
			return (Criteria) this;
		}

		public Criteria andQuantityBetween(Integer value1, Integer value2) {
			addCriterion("quantity between", value1, value2, "quantity");
			return (Criteria) this;
		}

		public Criteria andQuantityNotBetween(Integer value1, Integer value2) {
			addCriterion("quantity not between", value1, value2, "quantity");
			return (Criteria) this;
		}

		public Criteria andOriginalPriceIsNull() {
			addCriterion("original_price is null");
			return (Criteria) this;
		}

		public Criteria andOriginalPriceIsNotNull() {
			addCriterion("original_price is not null");
			return (Criteria) this;
		}

		public Criteria andOriginalPriceEqualTo(BigDecimal value) {
			addCriterion("original_price =", value, "originalPrice");
			return (Criteria) this;
		}

		public Criteria andOriginalPriceNotEqualTo(BigDecimal value) {
			addCriterion("original_price <>", value, "originalPrice");
			return (Criteria) this;
		}

		public Criteria andOriginalPriceGreaterThan(BigDecimal value) {
			addCriterion("original_price >", value, "originalPrice");
			return (Criteria) this;
		}

		public Criteria andOriginalPriceGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("original_price >=", value, "originalPrice");
			return (Criteria) this;
		}

		public Criteria andOriginalPriceLessThan(BigDecimal value) {
			addCriterion("original_price <", value, "originalPrice");
			return (Criteria) this;
		}

		public Criteria andOriginalPriceLessThanOrEqualTo(BigDecimal value) {
			addCriterion("original_price <=", value, "originalPrice");
			return (Criteria) this;
		}

		public Criteria andOriginalPriceIn(List<BigDecimal> values) {
			addCriterion("original_price in", values, "originalPrice");
			return (Criteria) this;
		}

		public Criteria andOriginalPriceNotIn(List<BigDecimal> values) {
			addCriterion("original_price not in", values, "originalPrice");
			return (Criteria) this;
		}

		public Criteria andOriginalPriceBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("original_price between", value1, value2, "originalPrice");
			return (Criteria) this;
		}

		public Criteria andOriginalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("original_price not between", value1, value2, "originalPrice");
			return (Criteria) this;
		}

		public Criteria andSalePriceIsNull() {
			addCriterion("sale_price is null");
			return (Criteria) this;
		}

		public Criteria andSalePriceIsNotNull() {
			addCriterion("sale_price is not null");
			return (Criteria) this;
		}

		public Criteria andSalePriceEqualTo(BigDecimal value) {
			addCriterion("sale_price =", value, "salePrice");
			return (Criteria) this;
		}

		public Criteria andSalePriceNotEqualTo(BigDecimal value) {
			addCriterion("sale_price <>", value, "salePrice");
			return (Criteria) this;
		}

		public Criteria andSalePriceGreaterThan(BigDecimal value) {
			addCriterion("sale_price >", value, "salePrice");
			return (Criteria) this;
		}

		public Criteria andSalePriceGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("sale_price >=", value, "salePrice");
			return (Criteria) this;
		}

		public Criteria andSalePriceLessThan(BigDecimal value) {
			addCriterion("sale_price <", value, "salePrice");
			return (Criteria) this;
		}

		public Criteria andSalePriceLessThanOrEqualTo(BigDecimal value) {
			addCriterion("sale_price <=", value, "salePrice");
			return (Criteria) this;
		}

		public Criteria andSalePriceIn(List<BigDecimal> values) {
			addCriterion("sale_price in", values, "salePrice");
			return (Criteria) this;
		}

		public Criteria andSalePriceNotIn(List<BigDecimal> values) {
			addCriterion("sale_price not in", values, "salePrice");
			return (Criteria) this;
		}

		public Criteria andSalePriceBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("sale_price between", value1, value2, "salePrice");
			return (Criteria) this;
		}

		public Criteria andSalePriceNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("sale_price not between", value1, value2, "salePrice");
			return (Criteria) this;
		}

		public Criteria andCouponAmountIsNull() {
			addCriterion("coupon_amount is null");
			return (Criteria) this;
		}

		public Criteria andCouponAmountIsNotNull() {
			addCriterion("coupon_amount is not null");
			return (Criteria) this;
		}

		public Criteria andCouponAmountEqualTo(BigDecimal value) {
			addCriterion("coupon_amount =", value, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountNotEqualTo(BigDecimal value) {
			addCriterion("coupon_amount <>", value, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountGreaterThan(BigDecimal value) {
			addCriterion("coupon_amount >", value, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("coupon_amount >=", value, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountLessThan(BigDecimal value) {
			addCriterion("coupon_amount <", value, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountLessThanOrEqualTo(BigDecimal value) {
			addCriterion("coupon_amount <=", value, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountIn(List<BigDecimal> values) {
			addCriterion("coupon_amount in", values, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountNotIn(List<BigDecimal> values) {
			addCriterion("coupon_amount not in", values, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("coupon_amount between", value1, value2, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andCouponAmountNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("coupon_amount not between", value1, value2, "couponAmount");
			return (Criteria) this;
		}

		public Criteria andOrderPromotionAmountIsNull() {
			addCriterion("order_promotion_amount is null");
			return (Criteria) this;
		}

		public Criteria andOrderPromotionAmountIsNotNull() {
			addCriterion("order_promotion_amount is not null");
			return (Criteria) this;
		}

		public Criteria andOrderPromotionAmountEqualTo(BigDecimal value) {
			addCriterion("order_promotion_amount =", value, "orderPromotionAmount");
			return (Criteria) this;
		}

		public Criteria andOrderPromotionAmountNotEqualTo(BigDecimal value) {
			addCriterion("order_promotion_amount <>", value, "orderPromotionAmount");
			return (Criteria) this;
		}

		public Criteria andOrderPromotionAmountGreaterThan(BigDecimal value) {
			addCriterion("order_promotion_amount >", value, "orderPromotionAmount");
			return (Criteria) this;
		}

		public Criteria andOrderPromotionAmountGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("order_promotion_amount >=", value, "orderPromotionAmount");
			return (Criteria) this;
		}

		public Criteria andOrderPromotionAmountLessThan(BigDecimal value) {
			addCriterion("order_promotion_amount <", value, "orderPromotionAmount");
			return (Criteria) this;
		}

		public Criteria andOrderPromotionAmountLessThanOrEqualTo(BigDecimal value) {
			addCriterion("order_promotion_amount <=", value, "orderPromotionAmount");
			return (Criteria) this;
		}

		public Criteria andOrderPromotionAmountIn(List<BigDecimal> values) {
			addCriterion("order_promotion_amount in", values, "orderPromotionAmount");
			return (Criteria) this;
		}

		public Criteria andOrderPromotionAmountNotIn(List<BigDecimal> values) {
			addCriterion("order_promotion_amount not in", values, "orderPromotionAmount");
			return (Criteria) this;
		}

		public Criteria andOrderPromotionAmountBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("order_promotion_amount between", value1, value2, "orderPromotionAmount");
			return (Criteria) this;
		}

		public Criteria andOrderPromotionAmountNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("order_promotion_amount not between", value1, value2, "orderPromotionAmount");
			return (Criteria) this;
		}

		public Criteria andSale1IsNull() {
			addCriterion("sale1 is null");
			return (Criteria) this;
		}

		public Criteria andSale1IsNotNull() {
			addCriterion("sale1 is not null");
			return (Criteria) this;
		}

		public Criteria andSale1EqualTo(String value) {
			addCriterion("sale1 =", value, "sale1");
			return (Criteria) this;
		}

		public Criteria andSale1NotEqualTo(String value) {
			addCriterion("sale1 <>", value, "sale1");
			return (Criteria) this;
		}

		public Criteria andSale1GreaterThan(String value) {
			addCriterion("sale1 >", value, "sale1");
			return (Criteria) this;
		}

		public Criteria andSale1GreaterThanOrEqualTo(String value) {
			addCriterion("sale1 >=", value, "sale1");
			return (Criteria) this;
		}

		public Criteria andSale1LessThan(String value) {
			addCriterion("sale1 <", value, "sale1");
			return (Criteria) this;
		}

		public Criteria andSale1LessThanOrEqualTo(String value) {
			addCriterion("sale1 <=", value, "sale1");
			return (Criteria) this;
		}

		public Criteria andSale1Like(String value) {
			addCriterion("sale1 like", value, "sale1");
			return (Criteria) this;
		}

		public Criteria andSale1NotLike(String value) {
			addCriterion("sale1 not like", value, "sale1");
			return (Criteria) this;
		}

		public Criteria andSale1In(List<String> values) {
			addCriterion("sale1 in", values, "sale1");
			return (Criteria) this;
		}

		public Criteria andSale1NotIn(List<String> values) {
			addCriterion("sale1 not in", values, "sale1");
			return (Criteria) this;
		}

		public Criteria andSale1Between(String value1, String value2) {
			addCriterion("sale1 between", value1, value2, "sale1");
			return (Criteria) this;
		}

		public Criteria andSale1NotBetween(String value1, String value2) {
			addCriterion("sale1 not between", value1, value2, "sale1");
			return (Criteria) this;
		}

		public Criteria andSale2IsNull() {
			addCriterion("sale2 is null");
			return (Criteria) this;
		}

		public Criteria andSale2IsNotNull() {
			addCriterion("sale2 is not null");
			return (Criteria) this;
		}

		public Criteria andSale2EqualTo(String value) {
			addCriterion("sale2 =", value, "sale2");
			return (Criteria) this;
		}

		public Criteria andSale2NotEqualTo(String value) {
			addCriterion("sale2 <>", value, "sale2");
			return (Criteria) this;
		}

		public Criteria andSale2GreaterThan(String value) {
			addCriterion("sale2 >", value, "sale2");
			return (Criteria) this;
		}

		public Criteria andSale2GreaterThanOrEqualTo(String value) {
			addCriterion("sale2 >=", value, "sale2");
			return (Criteria) this;
		}

		public Criteria andSale2LessThan(String value) {
			addCriterion("sale2 <", value, "sale2");
			return (Criteria) this;
		}

		public Criteria andSale2LessThanOrEqualTo(String value) {
			addCriterion("sale2 <=", value, "sale2");
			return (Criteria) this;
		}

		public Criteria andSale2Like(String value) {
			addCriterion("sale2 like", value, "sale2");
			return (Criteria) this;
		}

		public Criteria andSale2NotLike(String value) {
			addCriterion("sale2 not like", value, "sale2");
			return (Criteria) this;
		}

		public Criteria andSale2In(List<String> values) {
			addCriterion("sale2 in", values, "sale2");
			return (Criteria) this;
		}

		public Criteria andSale2NotIn(List<String> values) {
			addCriterion("sale2 not in", values, "sale2");
			return (Criteria) this;
		}

		public Criteria andSale2Between(String value1, String value2) {
			addCriterion("sale2 between", value1, value2, "sale2");
			return (Criteria) this;
		}

		public Criteria andSale2NotBetween(String value1, String value2) {
			addCriterion("sale2 not between", value1, value2, "sale2");
			return (Criteria) this;
		}

		public Criteria andProductSkuSnIsNull() {
			addCriterion("product_sku_sn is null");
			return (Criteria) this;
		}

		public Criteria andProductSkuSnIsNotNull() {
			addCriterion("product_sku_sn is not null");
			return (Criteria) this;
		}

		public Criteria andProductSkuSnEqualTo(String value) {
			addCriterion("product_sku_sn =", value, "productSkuSn");
			return (Criteria) this;
		}

		public Criteria andProductSkuSnNotEqualTo(String value) {
			addCriterion("product_sku_sn <>", value, "productSkuSn");
			return (Criteria) this;
		}

		public Criteria andProductSkuSnGreaterThan(String value) {
			addCriterion("product_sku_sn >", value, "productSkuSn");
			return (Criteria) this;
		}

		public Criteria andProductSkuSnGreaterThanOrEqualTo(String value) {
			addCriterion("product_sku_sn >=", value, "productSkuSn");
			return (Criteria) this;
		}

		public Criteria andProductSkuSnLessThan(String value) {
			addCriterion("product_sku_sn <", value, "productSkuSn");
			return (Criteria) this;
		}

		public Criteria andProductSkuSnLessThanOrEqualTo(String value) {
			addCriterion("product_sku_sn <=", value, "productSkuSn");
			return (Criteria) this;
		}

		public Criteria andProductSkuSnLike(String value) {
			addCriterion("product_sku_sn like", value, "productSkuSn");
			return (Criteria) this;
		}

		public Criteria andProductSkuSnNotLike(String value) {
			addCriterion("product_sku_sn not like", value, "productSkuSn");
			return (Criteria) this;
		}

		public Criteria andProductSkuSnIn(List<String> values) {
			addCriterion("product_sku_sn in", values, "productSkuSn");
			return (Criteria) this;
		}

		public Criteria andProductSkuSnNotIn(List<String> values) {
			addCriterion("product_sku_sn not in", values, "productSkuSn");
			return (Criteria) this;
		}

		public Criteria andProductSkuSnBetween(String value1, String value2) {
			addCriterion("product_sku_sn between", value1, value2, "productSkuSn");
			return (Criteria) this;
		}

		public Criteria andProductSkuSnNotBetween(String value1, String value2) {
			addCriterion("product_sku_sn not between", value1, value2, "productSkuSn");
			return (Criteria) this;
		}

		public Criteria andBrandNameIsNull() {
			addCriterion("brand_name is null");
			return (Criteria) this;
		}

		public Criteria andBrandNameIsNotNull() {
			addCriterion("brand_name is not null");
			return (Criteria) this;
		}

		public Criteria andBrandNameEqualTo(String value) {
			addCriterion("brand_name =", value, "brandName");
			return (Criteria) this;
		}

		public Criteria andBrandNameNotEqualTo(String value) {
			addCriterion("brand_name <>", value, "brandName");
			return (Criteria) this;
		}

		public Criteria andBrandNameGreaterThan(String value) {
			addCriterion("brand_name >", value, "brandName");
			return (Criteria) this;
		}

		public Criteria andBrandNameGreaterThanOrEqualTo(String value) {
			addCriterion("brand_name >=", value, "brandName");
			return (Criteria) this;
		}

		public Criteria andBrandNameLessThan(String value) {
			addCriterion("brand_name <", value, "brandName");
			return (Criteria) this;
		}

		public Criteria andBrandNameLessThanOrEqualTo(String value) {
			addCriterion("brand_name <=", value, "brandName");
			return (Criteria) this;
		}

		public Criteria andBrandNameLike(String value) {
			addCriterion("brand_name like", value, "brandName");
			return (Criteria) this;
		}

		public Criteria andBrandNameNotLike(String value) {
			addCriterion("brand_name not like", value, "brandName");
			return (Criteria) this;
		}

		public Criteria andBrandNameIn(List<String> values) {
			addCriterion("brand_name in", values, "brandName");
			return (Criteria) this;
		}

		public Criteria andBrandNameNotIn(List<String> values) {
			addCriterion("brand_name not in", values, "brandName");
			return (Criteria) this;
		}

		public Criteria andBrandNameBetween(String value1, String value2) {
			addCriterion("brand_name between", value1, value2, "brandName");
			return (Criteria) this;
		}

		public Criteria andBrandNameNotBetween(String value1, String value2) {
			addCriterion("brand_name not between", value1, value2, "brandName");
			return (Criteria) this;
		}

		public Criteria andBrandCodeIsNull() {
			addCriterion("brand_code is null");
			return (Criteria) this;
		}

		public Criteria andBrandCodeIsNotNull() {
			addCriterion("brand_code is not null");
			return (Criteria) this;
		}

		public Criteria andBrandCodeEqualTo(String value) {
			addCriterion("brand_code =", value, "brandCode");
			return (Criteria) this;
		}

		public Criteria andBrandCodeNotEqualTo(String value) {
			addCriterion("brand_code <>", value, "brandCode");
			return (Criteria) this;
		}

		public Criteria andBrandCodeGreaterThan(String value) {
			addCriterion("brand_code >", value, "brandCode");
			return (Criteria) this;
		}

		public Criteria andBrandCodeGreaterThanOrEqualTo(String value) {
			addCriterion("brand_code >=", value, "brandCode");
			return (Criteria) this;
		}

		public Criteria andBrandCodeLessThan(String value) {
			addCriterion("brand_code <", value, "brandCode");
			return (Criteria) this;
		}

		public Criteria andBrandCodeLessThanOrEqualTo(String value) {
			addCriterion("brand_code <=", value, "brandCode");
			return (Criteria) this;
		}

		public Criteria andBrandCodeLike(String value) {
			addCriterion("brand_code like", value, "brandCode");
			return (Criteria) this;
		}

		public Criteria andBrandCodeNotLike(String value) {
			addCriterion("brand_code not like", value, "brandCode");
			return (Criteria) this;
		}

		public Criteria andBrandCodeIn(List<String> values) {
			addCriterion("brand_code in", values, "brandCode");
			return (Criteria) this;
		}

		public Criteria andBrandCodeNotIn(List<String> values) {
			addCriterion("brand_code not in", values, "brandCode");
			return (Criteria) this;
		}

		public Criteria andBrandCodeBetween(String value1, String value2) {
			addCriterion("brand_code between", value1, value2, "brandCode");
			return (Criteria) this;
		}

		public Criteria andBrandCodeNotBetween(String value1, String value2) {
			addCriterion("brand_code not between", value1, value2, "brandCode");
			return (Criteria) this;
		}

		public Criteria andShopCodeIsNull() {
			addCriterion("shop_code is null");
			return (Criteria) this;
		}

		public Criteria andShopCodeIsNotNull() {
			addCriterion("shop_code is not null");
			return (Criteria) this;
		}

		public Criteria andShopCodeEqualTo(String value) {
			addCriterion("shop_code =", value, "shopCode");
			return (Criteria) this;
		}

		public Criteria andShopCodeNotEqualTo(String value) {
			addCriterion("shop_code <>", value, "shopCode");
			return (Criteria) this;
		}

		public Criteria andShopCodeGreaterThan(String value) {
			addCriterion("shop_code >", value, "shopCode");
			return (Criteria) this;
		}

		public Criteria andShopCodeGreaterThanOrEqualTo(String value) {
			addCriterion("shop_code >=", value, "shopCode");
			return (Criteria) this;
		}

		public Criteria andShopCodeLessThan(String value) {
			addCriterion("shop_code <", value, "shopCode");
			return (Criteria) this;
		}

		public Criteria andShopCodeLessThanOrEqualTo(String value) {
			addCriterion("shop_code <=", value, "shopCode");
			return (Criteria) this;
		}

		public Criteria andShopCodeLike(String value) {
			addCriterion("shop_code like", value, "shopCode");
			return (Criteria) this;
		}

		public Criteria andShopCodeNotLike(String value) {
			addCriterion("shop_code not like", value, "shopCode");
			return (Criteria) this;
		}

		public Criteria andShopCodeIn(List<String> values) {
			addCriterion("shop_code in", values, "shopCode");
			return (Criteria) this;
		}

		public Criteria andShopCodeNotIn(List<String> values) {
			addCriterion("shop_code not in", values, "shopCode");
			return (Criteria) this;
		}

		public Criteria andShopCodeBetween(String value1, String value2) {
			addCriterion("shop_code between", value1, value2, "shopCode");
			return (Criteria) this;
		}

		public Criteria andShopCodeNotBetween(String value1, String value2) {
			addCriterion("shop_code not between", value1, value2, "shopCode");
			return (Criteria) this;
		}

		public Criteria andShopNameIsNull() {
			addCriterion("shop_name is null");
			return (Criteria) this;
		}

		public Criteria andShopNameIsNotNull() {
			addCriterion("shop_name is not null");
			return (Criteria) this;
		}

		public Criteria andShopNameEqualTo(String value) {
			addCriterion("shop_name =", value, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameNotEqualTo(String value) {
			addCriterion("shop_name <>", value, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameGreaterThan(String value) {
			addCriterion("shop_name >", value, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameGreaterThanOrEqualTo(String value) {
			addCriterion("shop_name >=", value, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameLessThan(String value) {
			addCriterion("shop_name <", value, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameLessThanOrEqualTo(String value) {
			addCriterion("shop_name <=", value, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameLike(String value) {
			addCriterion("shop_name like", value, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameNotLike(String value) {
			addCriterion("shop_name not like", value, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameIn(List<String> values) {
			addCriterion("shop_name in", values, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameNotIn(List<String> values) {
			addCriterion("shop_name not in", values, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameBetween(String value1, String value2) {
			addCriterion("shop_name between", value1, value2, "shopName");
			return (Criteria) this;
		}

		public Criteria andShopNameNotBetween(String value1, String value2) {
			addCriterion("shop_name not between", value1, value2, "shopName");
			return (Criteria) this;
		}

		public Criteria andPromotionAmountIsNull() {
			addCriterion("promotion_amount is null");
			return (Criteria) this;
		}

		public Criteria andPromotionAmountIsNotNull() {
			addCriterion("promotion_amount is not null");
			return (Criteria) this;
		}

		public Criteria andPromotionAmountEqualTo(BigDecimal value) {
			addCriterion("promotion_amount =", value, "promotionAmount");
			return (Criteria) this;
		}

		public Criteria andPromotionAmountNotEqualTo(BigDecimal value) {
			addCriterion("promotion_amount <>", value, "promotionAmount");
			return (Criteria) this;
		}

		public Criteria andPromotionAmountGreaterThan(BigDecimal value) {
			addCriterion("promotion_amount >", value, "promotionAmount");
			return (Criteria) this;
		}

		public Criteria andPromotionAmountGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("promotion_amount >=", value, "promotionAmount");
			return (Criteria) this;
		}

		public Criteria andPromotionAmountLessThan(BigDecimal value) {
			addCriterion("promotion_amount <", value, "promotionAmount");
			return (Criteria) this;
		}

		public Criteria andPromotionAmountLessThanOrEqualTo(BigDecimal value) {
			addCriterion("promotion_amount <=", value, "promotionAmount");
			return (Criteria) this;
		}

		public Criteria andPromotionAmountIn(List<BigDecimal> values) {
			addCriterion("promotion_amount in", values, "promotionAmount");
			return (Criteria) this;
		}

		public Criteria andPromotionAmountNotIn(List<BigDecimal> values) {
			addCriterion("promotion_amount not in", values, "promotionAmount");
			return (Criteria) this;
		}

		public Criteria andPromotionAmountBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("promotion_amount between", value1, value2, "promotionAmount");
			return (Criteria) this;
		}

		public Criteria andPromotionAmountNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("promotion_amount not between", value1, value2, "promotionAmount");
			return (Criteria) this;
		}

		public Criteria andSourceIdIsNull() {
			addCriterion("source_id is null");
			return (Criteria) this;
		}

		public Criteria andSourceIdIsNotNull() {
			addCriterion("source_id is not null");
			return (Criteria) this;
		}

		public Criteria andSourceIdEqualTo(Long value) {
			addCriterion("source_id =", value, "sourceId");
			return (Criteria) this;
		}

		public Criteria andSourceIdNotEqualTo(Long value) {
			addCriterion("source_id <>", value, "sourceId");
			return (Criteria) this;
		}

		public Criteria andSourceIdGreaterThan(Long value) {
			addCriterion("source_id >", value, "sourceId");
			return (Criteria) this;
		}

		public Criteria andSourceIdGreaterThanOrEqualTo(Long value) {
			addCriterion("source_id >=", value, "sourceId");
			return (Criteria) this;
		}

		public Criteria andSourceIdLessThan(Long value) {
			addCriterion("source_id <", value, "sourceId");
			return (Criteria) this;
		}

		public Criteria andSourceIdLessThanOrEqualTo(Long value) {
			addCriterion("source_id <=", value, "sourceId");
			return (Criteria) this;
		}

		public Criteria andSourceIdIn(List<Long> values) {
			addCriterion("source_id in", values, "sourceId");
			return (Criteria) this;
		}

		public Criteria andSourceIdNotIn(List<Long> values) {
			addCriterion("source_id not in", values, "sourceId");
			return (Criteria) this;
		}

		public Criteria andSourceIdBetween(Long value1, Long value2) {
			addCriterion("source_id between", value1, value2, "sourceId");
			return (Criteria) this;
		}

		public Criteria andSourceIdNotBetween(Long value1, Long value2) {
			addCriterion("source_id not between", value1, value2, "sourceId");
			return (Criteria) this;
		}
	}

	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}

	public static class Criterion {
		private String condition;

		private Object value;

		private Object secondValue;

		private boolean noValue;

		private boolean singleValue;

		private boolean betweenValue;

		private boolean listValue;

		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}
}