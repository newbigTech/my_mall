package com.d2c.boss.order.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public void setOredCriteria(List<Criteria> oredCriteria) {
		this.oredCriteria = oredCriteria;
	}

	public OrderExample() {
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

		public Criteria andLoginCodeIsNull() {
			addCriterion("login_code is null");
			return (Criteria) this;
		}

		public Criteria andLoginCodeIsNotNull() {
			addCriterion("login_code is not null");
			return (Criteria) this;
		}

		public Criteria andLoginCodeEqualTo(String value) {
			addCriterion("login_code =", value, "loginCode");
			return (Criteria) this;
		}

		public Criteria andLoginCodeNotEqualTo(String value) {
			addCriterion("login_code <>", value, "loginCode");
			return (Criteria) this;
		}

		public Criteria andLoginCodeGreaterThan(String value) {
			addCriterion("login_code >", value, "loginCode");
			return (Criteria) this;
		}

		public Criteria andLoginCodeGreaterThanOrEqualTo(String value) {
			addCriterion("login_code >=", value, "loginCode");
			return (Criteria) this;
		}

		public Criteria andLoginCodeLessThan(String value) {
			addCriterion("login_code <", value, "loginCode");
			return (Criteria) this;
		}

		public Criteria andLoginCodeLessThanOrEqualTo(String value) {
			addCriterion("login_code <=", value, "loginCode");
			return (Criteria) this;
		}

		public Criteria andLoginCodeLike(String value) {
			addCriterion("login_code like", value, "loginCode");
			return (Criteria) this;
		}

		public Criteria andLoginCodeNotLike(String value) {
			addCriterion("login_code not like", value, "loginCode");
			return (Criteria) this;
		}

		public Criteria andLoginCodeIn(List<String> values) {
			addCriterion("login_code in", values, "loginCode");
			return (Criteria) this;
		}

		public Criteria andLoginCodeNotIn(List<String> values) {
			addCriterion("login_code not in", values, "loginCode");
			return (Criteria) this;
		}

		public Criteria andLoginCodeBetween(String value1, String value2) {
			addCriterion("login_code between", value1, value2, "loginCode");
			return (Criteria) this;
		}

		public Criteria andLoginCodeNotBetween(String value1, String value2) {
			addCriterion("login_code not between", value1, value2, "loginCode");
			return (Criteria) this;
		}

		public Criteria andReceiverIsNull() {
			addCriterion("receiver is null");
			return (Criteria) this;
		}

		public Criteria andReceiverIsNotNull() {
			addCriterion("receiver is not null");
			return (Criteria) this;
		}

		public Criteria andReceiverEqualTo(String value) {
			addCriterion("receiver =", value, "receiver");
			return (Criteria) this;
		}

		public Criteria andReceiverNotEqualTo(String value) {
			addCriterion("receiver <>", value, "receiver");
			return (Criteria) this;
		}

		public Criteria andReceiverGreaterThan(String value) {
			addCriterion("receiver >", value, "receiver");
			return (Criteria) this;
		}

		public Criteria andReceiverGreaterThanOrEqualTo(String value) {
			addCriterion("receiver >=", value, "receiver");
			return (Criteria) this;
		}

		public Criteria andReceiverLessThan(String value) {
			addCriterion("receiver <", value, "receiver");
			return (Criteria) this;
		}

		public Criteria andReceiverLessThanOrEqualTo(String value) {
			addCriterion("receiver <=", value, "receiver");
			return (Criteria) this;
		}

		public Criteria andReceiverLike(String value) {
			addCriterion("receiver like", value, "receiver");
			return (Criteria) this;
		}

		public Criteria andReceiverNotLike(String value) {
			addCriterion("receiver not like", value, "receiver");
			return (Criteria) this;
		}

		public Criteria andReceiverIn(List<String> values) {
			addCriterion("receiver in", values, "receiver");
			return (Criteria) this;
		}

		public Criteria andReceiverNotIn(List<String> values) {
			addCriterion("receiver not in", values, "receiver");
			return (Criteria) this;
		}

		public Criteria andReceiverBetween(String value1, String value2) {
			addCriterion("receiver between", value1, value2, "receiver");
			return (Criteria) this;
		}

		public Criteria andReceiverNotBetween(String value1, String value2) {
			addCriterion("receiver not between", value1, value2, "receiver");
			return (Criteria) this;
		}

		public Criteria andContactIsNull() {
			addCriterion("contact is null");
			return (Criteria) this;
		}

		public Criteria andContactIsNotNull() {
			addCriterion("contact is not null");
			return (Criteria) this;
		}

		public Criteria andContactEqualTo(String value) {
			addCriterion("contact =", value, "contact");
			return (Criteria) this;
		}

		public Criteria andContactNotEqualTo(String value) {
			addCriterion("contact <>", value, "contact");
			return (Criteria) this;
		}

		public Criteria andContactGreaterThan(String value) {
			addCriterion("contact >", value, "contact");
			return (Criteria) this;
		}

		public Criteria andContactGreaterThanOrEqualTo(String value) {
			addCriterion("contact >=", value, "contact");
			return (Criteria) this;
		}

		public Criteria andContactLessThan(String value) {
			addCriterion("contact <", value, "contact");
			return (Criteria) this;
		}

		public Criteria andContactLessThanOrEqualTo(String value) {
			addCriterion("contact <=", value, "contact");
			return (Criteria) this;
		}

		public Criteria andContactLike(String value) {
			addCriterion("contact like", value, "contact");
			return (Criteria) this;
		}

		public Criteria andContactNotLike(String value) {
			addCriterion("contact not like", value, "contact");
			return (Criteria) this;
		}

		public Criteria andContactIn(List<String> values) {
			addCriterion("contact in", values, "contact");
			return (Criteria) this;
		}

		public Criteria andContactNotIn(List<String> values) {
			addCriterion("contact not in", values, "contact");
			return (Criteria) this;
		}

		public Criteria andContactBetween(String value1, String value2) {
			addCriterion("contact between", value1, value2, "contact");
			return (Criteria) this;
		}

		public Criteria andContactNotBetween(String value1, String value2) {
			addCriterion("contact not between", value1, value2, "contact");
			return (Criteria) this;
		}

		public Criteria andMobileIsNull() {
			addCriterion("mobile is null");
			return (Criteria) this;
		}

		public Criteria andMobileIsNotNull() {
			addCriterion("mobile is not null");
			return (Criteria) this;
		}

		public Criteria andMobileEqualTo(String value) {
			addCriterion("mobile =", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileNotEqualTo(String value) {
			addCriterion("mobile <>", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileGreaterThan(String value) {
			addCriterion("mobile >", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileGreaterThanOrEqualTo(String value) {
			addCriterion("mobile >=", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileLessThan(String value) {
			addCriterion("mobile <", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileLessThanOrEqualTo(String value) {
			addCriterion("mobile <=", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileLike(String value) {
			addCriterion("mobile like", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileNotLike(String value) {
			addCriterion("mobile not like", value, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileIn(List<String> values) {
			addCriterion("mobile in", values, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileNotIn(List<String> values) {
			addCriterion("mobile not in", values, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileBetween(String value1, String value2) {
			addCriterion("mobile between", value1, value2, "mobile");
			return (Criteria) this;
		}

		public Criteria andMobileNotBetween(String value1, String value2) {
			addCriterion("mobile not between", value1, value2, "mobile");
			return (Criteria) this;
		}

		public Criteria andProvinceIsNull() {
			addCriterion("province is null");
			return (Criteria) this;
		}

		public Criteria andProvinceIsNotNull() {
			addCriterion("province is not null");
			return (Criteria) this;
		}

		public Criteria andProvinceEqualTo(String value) {
			addCriterion("province =", value, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceNotEqualTo(String value) {
			addCriterion("province <>", value, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceGreaterThan(String value) {
			addCriterion("province >", value, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceGreaterThanOrEqualTo(String value) {
			addCriterion("province >=", value, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceLessThan(String value) {
			addCriterion("province <", value, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceLessThanOrEqualTo(String value) {
			addCriterion("province <=", value, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceLike(String value) {
			addCriterion("province like", value, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceNotLike(String value) {
			addCriterion("province not like", value, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceIn(List<String> values) {
			addCriterion("province in", values, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceNotIn(List<String> values) {
			addCriterion("province not in", values, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceBetween(String value1, String value2) {
			addCriterion("province between", value1, value2, "province");
			return (Criteria) this;
		}

		public Criteria andProvinceNotBetween(String value1, String value2) {
			addCriterion("province not between", value1, value2, "province");
			return (Criteria) this;
		}

		public Criteria andCityIsNull() {
			addCriterion("city is null");
			return (Criteria) this;
		}

		public Criteria andCityIsNotNull() {
			addCriterion("city is not null");
			return (Criteria) this;
		}

		public Criteria andCityEqualTo(String value) {
			addCriterion("city =", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityNotEqualTo(String value) {
			addCriterion("city <>", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityGreaterThan(String value) {
			addCriterion("city >", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityGreaterThanOrEqualTo(String value) {
			addCriterion("city >=", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityLessThan(String value) {
			addCriterion("city <", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityLessThanOrEqualTo(String value) {
			addCriterion("city <=", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityLike(String value) {
			addCriterion("city like", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityNotLike(String value) {
			addCriterion("city not like", value, "city");
			return (Criteria) this;
		}

		public Criteria andCityIn(List<String> values) {
			addCriterion("city in", values, "city");
			return (Criteria) this;
		}

		public Criteria andCityNotIn(List<String> values) {
			addCriterion("city not in", values, "city");
			return (Criteria) this;
		}

		public Criteria andCityBetween(String value1, String value2) {
			addCriterion("city between", value1, value2, "city");
			return (Criteria) this;
		}

		public Criteria andCityNotBetween(String value1, String value2) {
			addCriterion("city not between", value1, value2, "city");
			return (Criteria) this;
		}

		public Criteria andDistrictIsNull() {
			addCriterion("district is null");
			return (Criteria) this;
		}

		public Criteria andDistrictIsNotNull() {
			addCriterion("district is not null");
			return (Criteria) this;
		}

		public Criteria andDistrictEqualTo(String value) {
			addCriterion("district =", value, "district");
			return (Criteria) this;
		}

		public Criteria andDistrictNotEqualTo(String value) {
			addCriterion("district <>", value, "district");
			return (Criteria) this;
		}

		public Criteria andDistrictGreaterThan(String value) {
			addCriterion("district >", value, "district");
			return (Criteria) this;
		}

		public Criteria andDistrictGreaterThanOrEqualTo(String value) {
			addCriterion("district >=", value, "district");
			return (Criteria) this;
		}

		public Criteria andDistrictLessThan(String value) {
			addCriterion("district <", value, "district");
			return (Criteria) this;
		}

		public Criteria andDistrictLessThanOrEqualTo(String value) {
			addCriterion("district <=", value, "district");
			return (Criteria) this;
		}

		public Criteria andDistrictLike(String value) {
			addCriterion("district like", value, "district");
			return (Criteria) this;
		}

		public Criteria andDistrictNotLike(String value) {
			addCriterion("district not like", value, "district");
			return (Criteria) this;
		}

		public Criteria andDistrictIn(List<String> values) {
			addCriterion("district in", values, "district");
			return (Criteria) this;
		}

		public Criteria andDistrictNotIn(List<String> values) {
			addCriterion("district not in", values, "district");
			return (Criteria) this;
		}

		public Criteria andDistrictBetween(String value1, String value2) {
			addCriterion("district between", value1, value2, "district");
			return (Criteria) this;
		}

		public Criteria andDistrictNotBetween(String value1, String value2) {
			addCriterion("district not between", value1, value2, "district");
			return (Criteria) this;
		}

		public Criteria andAddressIsNull() {
			addCriterion("address is null");
			return (Criteria) this;
		}

		public Criteria andAddressIsNotNull() {
			addCriterion("address is not null");
			return (Criteria) this;
		}

		public Criteria andAddressEqualTo(String value) {
			addCriterion("address =", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressNotEqualTo(String value) {
			addCriterion("address <>", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressGreaterThan(String value) {
			addCriterion("address >", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressGreaterThanOrEqualTo(String value) {
			addCriterion("address >=", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressLessThan(String value) {
			addCriterion("address <", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressLessThanOrEqualTo(String value) {
			addCriterion("address <=", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressLike(String value) {
			addCriterion("address like", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressNotLike(String value) {
			addCriterion("address not like", value, "address");
			return (Criteria) this;
		}

		public Criteria andAddressIn(List<String> values) {
			addCriterion("address in", values, "address");
			return (Criteria) this;
		}

		public Criteria andAddressNotIn(List<String> values) {
			addCriterion("address not in", values, "address");
			return (Criteria) this;
		}

		public Criteria andAddressBetween(String value1, String value2) {
			addCriterion("address between", value1, value2, "address");
			return (Criteria) this;
		}

		public Criteria andAddressNotBetween(String value1, String value2) {
			addCriterion("address not between", value1, value2, "address");
			return (Criteria) this;
		}

		public Criteria andSnIsNull() {
			addCriterion("sn is null");
			return (Criteria) this;
		}

		public Criteria andSnIsNotNull() {
			addCriterion("sn is not null");
			return (Criteria) this;
		}

		public Criteria andSnEqualTo(String value) {
			addCriterion("sn =", value, "sn");
			return (Criteria) this;
		}

		public Criteria andSnNotEqualTo(String value) {
			addCriterion("sn <>", value, "sn");
			return (Criteria) this;
		}

		public Criteria andSnGreaterThan(String value) {
			addCriterion("sn >", value, "sn");
			return (Criteria) this;
		}

		public Criteria andSnGreaterThanOrEqualTo(String value) {
			addCriterion("sn >=", value, "sn");
			return (Criteria) this;
		}

		public Criteria andSnLessThan(String value) {
			addCriterion("sn <", value, "sn");
			return (Criteria) this;
		}

		public Criteria andSnLessThanOrEqualTo(String value) {
			addCriterion("sn <=", value, "sn");
			return (Criteria) this;
		}

		public Criteria andSnLike(String value) {
			addCriterion("sn like", value, "sn");
			return (Criteria) this;
		}

		public Criteria andSnNotLike(String value) {
			addCriterion("sn not like", value, "sn");
			return (Criteria) this;
		}

		public Criteria andSnIn(List<String> values) {
			addCriterion("sn in", values, "sn");
			return (Criteria) this;
		}

		public Criteria andSnNotIn(List<String> values) {
			addCriterion("sn not in", values, "sn");
			return (Criteria) this;
		}

		public Criteria andSnBetween(String value1, String value2) {
			addCriterion("sn between", value1, value2, "sn");
			return (Criteria) this;
		}

		public Criteria andSnNotBetween(String value1, String value2) {
			addCriterion("sn not between", value1, value2, "sn");
			return (Criteria) this;
		}

		public Criteria andSourceIsNull() {
			addCriterion("source is null");
			return (Criteria) this;
		}

		public Criteria andSourceIsNotNull() {
			addCriterion("source is not null");
			return (Criteria) this;
		}

		public Criteria andSourceEqualTo(String value) {
			addCriterion("source =", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceNotEqualTo(String value) {
			addCriterion("source <>", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceGreaterThan(String value) {
			addCriterion("source >", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceGreaterThanOrEqualTo(String value) {
			addCriterion("source >=", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceLessThan(String value) {
			addCriterion("source <", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceLessThanOrEqualTo(String value) {
			addCriterion("source <=", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceLike(String value) {
			addCriterion("source like", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceNotLike(String value) {
			addCriterion("source not like", value, "source");
			return (Criteria) this;
		}

		public Criteria andSourceIn(List<String> values) {
			addCriterion("source in", values, "source");
			return (Criteria) this;
		}

		public Criteria andSourceNotIn(List<String> values) {
			addCriterion("source not in", values, "source");
			return (Criteria) this;
		}

		public Criteria andSourceBetween(String value1, String value2) {
			addCriterion("source between", value1, value2, "source");
			return (Criteria) this;
		}

		public Criteria andSourceNotBetween(String value1, String value2) {
			addCriterion("source not between", value1, value2, "source");
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

		public Criteria andStatusIsNull() {
			addCriterion("status is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("status is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(Integer value) {
			addCriterion("status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(Integer value) {
			addCriterion("status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(Integer value) {
			addCriterion("status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(Integer value) {
			addCriterion("status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(Integer value) {
			addCriterion("status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<Integer> values) {
			addCriterion("status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<Integer> values) {
			addCriterion("status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(Integer value1, Integer value2) {
			addCriterion("status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("status not between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andTotalAmountIsNull() {
			addCriterion("total_amount is null");
			return (Criteria) this;
		}

		public Criteria andTotalAmountIsNotNull() {
			addCriterion("total_amount is not null");
			return (Criteria) this;
		}

		public Criteria andTotalAmountEqualTo(BigDecimal value) {
			addCriterion("total_amount =", value, "totalAmount");
			return (Criteria) this;
		}

		public Criteria andTotalAmountNotEqualTo(BigDecimal value) {
			addCriterion("total_amount <>", value, "totalAmount");
			return (Criteria) this;
		}

		public Criteria andTotalAmountGreaterThan(BigDecimal value) {
			addCriterion("total_amount >", value, "totalAmount");
			return (Criteria) this;
		}

		public Criteria andTotalAmountGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("total_amount >=", value, "totalAmount");
			return (Criteria) this;
		}

		public Criteria andTotalAmountLessThan(BigDecimal value) {
			addCriterion("total_amount <", value, "totalAmount");
			return (Criteria) this;
		}

		public Criteria andTotalAmountLessThanOrEqualTo(BigDecimal value) {
			addCriterion("total_amount <=", value, "totalAmount");
			return (Criteria) this;
		}

		public Criteria andTotalAmountIn(List<BigDecimal> values) {
			addCriterion("total_amount in", values, "totalAmount");
			return (Criteria) this;
		}

		public Criteria andTotalAmountNotIn(List<BigDecimal> values) {
			addCriterion("total_amount not in", values, "totalAmount");
			return (Criteria) this;
		}

		public Criteria andTotalAmountBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("total_amount between", value1, value2, "totalAmount");
			return (Criteria) this;
		}

		public Criteria andTotalAmountNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("total_amount not between", value1, value2, "totalAmount");
			return (Criteria) this;
		}

		public Criteria andPayAmountIsNull() {
			addCriterion("pay_amount is null");
			return (Criteria) this;
		}

		public Criteria andPayAmountIsNotNull() {
			addCriterion("pay_amount is not null");
			return (Criteria) this;
		}

		public Criteria andPayAmountEqualTo(BigDecimal value) {
			addCriterion("pay_amount =", value, "payAmount");
			return (Criteria) this;
		}

		public Criteria andPayAmountNotEqualTo(BigDecimal value) {
			addCriterion("pay_amount <>", value, "payAmount");
			return (Criteria) this;
		}

		public Criteria andPayAmountGreaterThan(BigDecimal value) {
			addCriterion("pay_amount >", value, "payAmount");
			return (Criteria) this;
		}

		public Criteria andPayAmountGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("pay_amount >=", value, "payAmount");
			return (Criteria) this;
		}

		public Criteria andPayAmountLessThan(BigDecimal value) {
			addCriterion("pay_amount <", value, "payAmount");
			return (Criteria) this;
		}

		public Criteria andPayAmountLessThanOrEqualTo(BigDecimal value) {
			addCriterion("pay_amount <=", value, "payAmount");
			return (Criteria) this;
		}

		public Criteria andPayAmountIn(List<BigDecimal> values) {
			addCriterion("pay_amount in", values, "payAmount");
			return (Criteria) this;
		}

		public Criteria andPayAmountNotIn(List<BigDecimal> values) {
			addCriterion("pay_amount not in", values, "payAmount");
			return (Criteria) this;
		}

		public Criteria andPayAmountBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("pay_amount between", value1, value2, "payAmount");
			return (Criteria) this;
		}

		public Criteria andPayAmountNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("pay_amount not between", value1, value2, "payAmount");
			return (Criteria) this;
		}

		public Criteria andProductTotalAmountIsNull() {
			addCriterion("product_total_amount is null");
			return (Criteria) this;
		}

		public Criteria andProductTotalAmountIsNotNull() {
			addCriterion("product_total_amount is not null");
			return (Criteria) this;
		}

		public Criteria andProductTotalAmountEqualTo(BigDecimal value) {
			addCriterion("product_total_amount =", value, "productTotalAmount");
			return (Criteria) this;
		}

		public Criteria andProductTotalAmountNotEqualTo(BigDecimal value) {
			addCriterion("product_total_amount <>", value, "productTotalAmount");
			return (Criteria) this;
		}

		public Criteria andProductTotalAmountGreaterThan(BigDecimal value) {
			addCriterion("product_total_amount >", value, "productTotalAmount");
			return (Criteria) this;
		}

		public Criteria andProductTotalAmountGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("product_total_amount >=", value, "productTotalAmount");
			return (Criteria) this;
		}

		public Criteria andProductTotalAmountLessThan(BigDecimal value) {
			addCriterion("product_total_amount <", value, "productTotalAmount");
			return (Criteria) this;
		}

		public Criteria andProductTotalAmountLessThanOrEqualTo(BigDecimal value) {
			addCriterion("product_total_amount <=", value, "productTotalAmount");
			return (Criteria) this;
		}

		public Criteria andProductTotalAmountIn(List<BigDecimal> values) {
			addCriterion("product_total_amount in", values, "productTotalAmount");
			return (Criteria) this;
		}

		public Criteria andProductTotalAmountNotIn(List<BigDecimal> values) {
			addCriterion("product_total_amount not in", values, "productTotalAmount");
			return (Criteria) this;
		}

		public Criteria andProductTotalAmountBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("product_total_amount between", value1, value2, "productTotalAmount");
			return (Criteria) this;
		}

		public Criteria andProductTotalAmountNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("product_total_amount not between", value1, value2, "productTotalAmount");
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

		public Criteria andProductPromotionAmountIsNull() {
			addCriterion("product_promotion_amount is null");
			return (Criteria) this;
		}

		public Criteria andProductPromotionAmountIsNotNull() {
			addCriterion("product_promotion_amount is not null");
			return (Criteria) this;
		}

		public Criteria andProductPromotionAmountEqualTo(BigDecimal value) {
			addCriterion("product_promotion_amount =", value, "productPromotionAmount");
			return (Criteria) this;
		}

		public Criteria andProductPromotionAmountNotEqualTo(BigDecimal value) {
			addCriterion("product_promotion_amount <>", value, "productPromotionAmount");
			return (Criteria) this;
		}

		public Criteria andProductPromotionAmountGreaterThan(BigDecimal value) {
			addCriterion("product_promotion_amount >", value, "productPromotionAmount");
			return (Criteria) this;
		}

		public Criteria andProductPromotionAmountGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("product_promotion_amount >=", value, "productPromotionAmount");
			return (Criteria) this;
		}

		public Criteria andProductPromotionAmountLessThan(BigDecimal value) {
			addCriterion("product_promotion_amount <", value, "productPromotionAmount");
			return (Criteria) this;
		}

		public Criteria andProductPromotionAmountLessThanOrEqualTo(BigDecimal value) {
			addCriterion("product_promotion_amount <=", value, "productPromotionAmount");
			return (Criteria) this;
		}

		public Criteria andProductPromotionAmountIn(List<BigDecimal> values) {
			addCriterion("product_promotion_amount in", values, "productPromotionAmount");
			return (Criteria) this;
		}

		public Criteria andProductPromotionAmountNotIn(List<BigDecimal> values) {
			addCriterion("product_promotion_amount not in", values, "productPromotionAmount");
			return (Criteria) this;
		}

		public Criteria andProductPromotionAmountBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("product_promotion_amount between", value1, value2, "productPromotionAmount");
			return (Criteria) this;
		}

		public Criteria andProductPromotionAmountNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("product_promotion_amount not between", value1, value2, "productPromotionAmount");
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

		public Criteria andShippingRatesIsNull() {
			addCriterion("shipping_rates is null");
			return (Criteria) this;
		}

		public Criteria andShippingRatesIsNotNull() {
			addCriterion("shipping_rates is not null");
			return (Criteria) this;
		}

		public Criteria andShippingRatesEqualTo(BigDecimal value) {
			addCriterion("shipping_rates =", value, "shippingRates");
			return (Criteria) this;
		}

		public Criteria andShippingRatesNotEqualTo(BigDecimal value) {
			addCriterion("shipping_rates <>", value, "shippingRates");
			return (Criteria) this;
		}

		public Criteria andShippingRatesGreaterThan(BigDecimal value) {
			addCriterion("shipping_rates >", value, "shippingRates");
			return (Criteria) this;
		}

		public Criteria andShippingRatesGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("shipping_rates >=", value, "shippingRates");
			return (Criteria) this;
		}

		public Criteria andShippingRatesLessThan(BigDecimal value) {
			addCriterion("shipping_rates <", value, "shippingRates");
			return (Criteria) this;
		}

		public Criteria andShippingRatesLessThanOrEqualTo(BigDecimal value) {
			addCriterion("shipping_rates <=", value, "shippingRates");
			return (Criteria) this;
		}

		public Criteria andShippingRatesIn(List<BigDecimal> values) {
			addCriterion("shipping_rates in", values, "shippingRates");
			return (Criteria) this;
		}

		public Criteria andShippingRatesNotIn(List<BigDecimal> values) {
			addCriterion("shipping_rates not in", values, "shippingRates");
			return (Criteria) this;
		}

		public Criteria andShippingRatesBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("shipping_rates between", value1, value2, "shippingRates");
			return (Criteria) this;
		}

		public Criteria andShippingRatesNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("shipping_rates not between", value1, value2, "shippingRates");
			return (Criteria) this;
		}

		public Criteria andDeliverySnIsNull() {
			addCriterion("delivery_sn is null");
			return (Criteria) this;
		}

		public Criteria andDeliverySnIsNotNull() {
			addCriterion("delivery_sn is not null");
			return (Criteria) this;
		}

		public Criteria andDeliverySnEqualTo(String value) {
			addCriterion("delivery_sn =", value, "deliverySn");
			return (Criteria) this;
		}

		public Criteria andDeliverySnNotEqualTo(String value) {
			addCriterion("delivery_sn <>", value, "deliverySn");
			return (Criteria) this;
		}

		public Criteria andDeliverySnGreaterThan(String value) {
			addCriterion("delivery_sn >", value, "deliverySn");
			return (Criteria) this;
		}

		public Criteria andDeliverySnGreaterThanOrEqualTo(String value) {
			addCriterion("delivery_sn >=", value, "deliverySn");
			return (Criteria) this;
		}

		public Criteria andDeliverySnLessThan(String value) {
			addCriterion("delivery_sn <", value, "deliverySn");
			return (Criteria) this;
		}

		public Criteria andDeliverySnLessThanOrEqualTo(String value) {
			addCriterion("delivery_sn <=", value, "deliverySn");
			return (Criteria) this;
		}

		public Criteria andDeliverySnLike(String value) {
			addCriterion("delivery_sn like", value, "deliverySn");
			return (Criteria) this;
		}

		public Criteria andDeliverySnNotLike(String value) {
			addCriterion("delivery_sn not like", value, "deliverySn");
			return (Criteria) this;
		}

		public Criteria andDeliverySnIn(List<String> values) {
			addCriterion("delivery_sn in", values, "deliverySn");
			return (Criteria) this;
		}

		public Criteria andDeliverySnNotIn(List<String> values) {
			addCriterion("delivery_sn not in", values, "deliverySn");
			return (Criteria) this;
		}

		public Criteria andDeliverySnBetween(String value1, String value2) {
			addCriterion("delivery_sn between", value1, value2, "deliverySn");
			return (Criteria) this;
		}

		public Criteria andDeliverySnNotBetween(String value1, String value2) {
			addCriterion("delivery_sn not between", value1, value2, "deliverySn");
			return (Criteria) this;
		}

		public Criteria andDeliveryCompIsNull() {
			addCriterion("delivery_comp is null");
			return (Criteria) this;
		}

		public Criteria andDeliveryCompIsNotNull() {
			addCriterion("delivery_comp is not null");
			return (Criteria) this;
		}

		public Criteria andDeliveryCompEqualTo(String value) {
			addCriterion("delivery_comp =", value, "deliveryComp");
			return (Criteria) this;
		}

		public Criteria andDeliveryCompNotEqualTo(String value) {
			addCriterion("delivery_comp <>", value, "deliveryComp");
			return (Criteria) this;
		}

		public Criteria andDeliveryCompGreaterThan(String value) {
			addCriterion("delivery_comp >", value, "deliveryComp");
			return (Criteria) this;
		}

		public Criteria andDeliveryCompGreaterThanOrEqualTo(String value) {
			addCriterion("delivery_comp >=", value, "deliveryComp");
			return (Criteria) this;
		}

		public Criteria andDeliveryCompLessThan(String value) {
			addCriterion("delivery_comp <", value, "deliveryComp");
			return (Criteria) this;
		}

		public Criteria andDeliveryCompLessThanOrEqualTo(String value) {
			addCriterion("delivery_comp <=", value, "deliveryComp");
			return (Criteria) this;
		}

		public Criteria andDeliveryCompLike(String value) {
			addCriterion("delivery_comp like", value, "deliveryComp");
			return (Criteria) this;
		}

		public Criteria andDeliveryCompNotLike(String value) {
			addCriterion("delivery_comp not like", value, "deliveryComp");
			return (Criteria) this;
		}

		public Criteria andDeliveryCompIn(List<String> values) {
			addCriterion("delivery_comp in", values, "deliveryComp");
			return (Criteria) this;
		}

		public Criteria andDeliveryCompNotIn(List<String> values) {
			addCriterion("delivery_comp not in", values, "deliveryComp");
			return (Criteria) this;
		}

		public Criteria andDeliveryCompBetween(String value1, String value2) {
			addCriterion("delivery_comp between", value1, value2, "deliveryComp");
			return (Criteria) this;
		}

		public Criteria andDeliveryCompNotBetween(String value1, String value2) {
			addCriterion("delivery_comp not between", value1, value2, "deliveryComp");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeIsNull() {
			addCriterion("delivery_time is null");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeIsNotNull() {
			addCriterion("delivery_time is not null");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeEqualTo(Date value) {
			addCriterion("delivery_time =", value, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeNotEqualTo(Date value) {
			addCriterion("delivery_time <>", value, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeGreaterThan(Date value) {
			addCriterion("delivery_time >", value, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("delivery_time >=", value, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeLessThan(Date value) {
			addCriterion("delivery_time <", value, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeLessThanOrEqualTo(Date value) {
			addCriterion("delivery_time <=", value, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeIn(List<Date> values) {
			addCriterion("delivery_time in", values, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeNotIn(List<Date> values) {
			addCriterion("delivery_time not in", values, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeBetween(Date value1, Date value2) {
			addCriterion("delivery_time between", value1, value2, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andDeliveryTimeNotBetween(Date value1, Date value2) {
			addCriterion("delivery_time not between", value1, value2, "deliveryTime");
			return (Criteria) this;
		}

		public Criteria andPaymentTimeIsNull() {
			addCriterion("payment_time is null");
			return (Criteria) this;
		}

		public Criteria andPaymentTimeIsNotNull() {
			addCriterion("payment_time is not null");
			return (Criteria) this;
		}

		public Criteria andPaymentTimeEqualTo(Date value) {
			addCriterion("payment_time =", value, "paymentTime");
			return (Criteria) this;
		}

		public Criteria andPaymentTimeNotEqualTo(Date value) {
			addCriterion("payment_time <>", value, "paymentTime");
			return (Criteria) this;
		}

		public Criteria andPaymentTimeGreaterThan(Date value) {
			addCriterion("payment_time >", value, "paymentTime");
			return (Criteria) this;
		}

		public Criteria andPaymentTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("payment_time >=", value, "paymentTime");
			return (Criteria) this;
		}

		public Criteria andPaymentTimeLessThan(Date value) {
			addCriterion("payment_time <", value, "paymentTime");
			return (Criteria) this;
		}

		public Criteria andPaymentTimeLessThanOrEqualTo(Date value) {
			addCriterion("payment_time <=", value, "paymentTime");
			return (Criteria) this;
		}

		public Criteria andPaymentTimeIn(List<Date> values) {
			addCriterion("payment_time in", values, "paymentTime");
			return (Criteria) this;
		}

		public Criteria andPaymentTimeNotIn(List<Date> values) {
			addCriterion("payment_time not in", values, "paymentTime");
			return (Criteria) this;
		}

		public Criteria andPaymentTimeBetween(Date value1, Date value2) {
			addCriterion("payment_time between", value1, value2, "paymentTime");
			return (Criteria) this;
		}

		public Criteria andPaymentTimeNotBetween(Date value1, Date value2) {
			addCriterion("payment_time not between", value1, value2, "paymentTime");
			return (Criteria) this;
		}

		public Criteria andCouponedIsNull() {
			addCriterion("couponed is null");
			return (Criteria) this;
		}

		public Criteria andCouponedIsNotNull() {
			addCriterion("couponed is not null");
			return (Criteria) this;
		}

		public Criteria andCouponedEqualTo(Integer value) {
			addCriterion("couponed =", value, "couponed");
			return (Criteria) this;
		}

		public Criteria andCouponedNotEqualTo(Integer value) {
			addCriterion("couponed <>", value, "couponed");
			return (Criteria) this;
		}

		public Criteria andCouponedGreaterThan(Integer value) {
			addCriterion("couponed >", value, "couponed");
			return (Criteria) this;
		}

		public Criteria andCouponedGreaterThanOrEqualTo(Integer value) {
			addCriterion("couponed >=", value, "couponed");
			return (Criteria) this;
		}

		public Criteria andCouponedLessThan(Integer value) {
			addCriterion("couponed <", value, "couponed");
			return (Criteria) this;
		}

		public Criteria andCouponedLessThanOrEqualTo(Integer value) {
			addCriterion("couponed <=", value, "couponed");
			return (Criteria) this;
		}

		public Criteria andCouponedIn(List<Integer> values) {
			addCriterion("couponed in", values, "couponed");
			return (Criteria) this;
		}

		public Criteria andCouponedNotIn(List<Integer> values) {
			addCriterion("couponed not in", values, "couponed");
			return (Criteria) this;
		}

		public Criteria andCouponedBetween(Integer value1, Integer value2) {
			addCriterion("couponed between", value1, value2, "couponed");
			return (Criteria) this;
		}

		public Criteria andCouponedNotBetween(Integer value1, Integer value2) {
			addCriterion("couponed not between", value1, value2, "couponed");
			return (Criteria) this;
		}

		public Criteria andCouponInfoIsNull() {
			addCriterion("coupon_info is null");
			return (Criteria) this;
		}

		public Criteria andCouponInfoIsNotNull() {
			addCriterion("coupon_info is not null");
			return (Criteria) this;
		}

		public Criteria andCouponInfoEqualTo(String value) {
			addCriterion("coupon_info =", value, "couponInfo");
			return (Criteria) this;
		}

		public Criteria andCouponInfoNotEqualTo(String value) {
			addCriterion("coupon_info <>", value, "couponInfo");
			return (Criteria) this;
		}

		public Criteria andCouponInfoGreaterThan(String value) {
			addCriterion("coupon_info >", value, "couponInfo");
			return (Criteria) this;
		}

		public Criteria andCouponInfoGreaterThanOrEqualTo(String value) {
			addCriterion("coupon_info >=", value, "couponInfo");
			return (Criteria) this;
		}

		public Criteria andCouponInfoLessThan(String value) {
			addCriterion("coupon_info <", value, "couponInfo");
			return (Criteria) this;
		}

		public Criteria andCouponInfoLessThanOrEqualTo(String value) {
			addCriterion("coupon_info <=", value, "couponInfo");
			return (Criteria) this;
		}

		public Criteria andCouponInfoLike(String value) {
			addCriterion("coupon_info like", value, "couponInfo");
			return (Criteria) this;
		}

		public Criteria andCouponInfoNotLike(String value) {
			addCriterion("coupon_info not like", value, "couponInfo");
			return (Criteria) this;
		}

		public Criteria andCouponInfoIn(List<String> values) {
			addCriterion("coupon_info in", values, "couponInfo");
			return (Criteria) this;
		}

		public Criteria andCouponInfoNotIn(List<String> values) {
			addCriterion("coupon_info not in", values, "couponInfo");
			return (Criteria) this;
		}

		public Criteria andCouponInfoBetween(String value1, String value2) {
			addCriterion("coupon_info between", value1, value2, "couponInfo");
			return (Criteria) this;
		}

		public Criteria andCouponInfoNotBetween(String value1, String value2) {
			addCriterion("coupon_info not between", value1, value2, "couponInfo");
			return (Criteria) this;
		}

		public Criteria andSuccessTimeIsNull() {
			addCriterion("success_time is null");
			return (Criteria) this;
		}

		public Criteria andSuccessTimeIsNotNull() {
			addCriterion("success_time is not null");
			return (Criteria) this;
		}

		public Criteria andSuccessTimeEqualTo(Date value) {
			addCriterion("success_time =", value, "successTime");
			return (Criteria) this;
		}

		public Criteria andSuccessTimeNotEqualTo(Date value) {
			addCriterion("success_time <>", value, "successTime");
			return (Criteria) this;
		}

		public Criteria andSuccessTimeGreaterThan(Date value) {
			addCriterion("success_time >", value, "successTime");
			return (Criteria) this;
		}

		public Criteria andSuccessTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("success_time >=", value, "successTime");
			return (Criteria) this;
		}

		public Criteria andSuccessTimeLessThan(Date value) {
			addCriterion("success_time <", value, "successTime");
			return (Criteria) this;
		}

		public Criteria andSuccessTimeLessThanOrEqualTo(Date value) {
			addCriterion("success_time <=", value, "successTime");
			return (Criteria) this;
		}

		public Criteria andSuccessTimeIn(List<Date> values) {
			addCriterion("success_time in", values, "successTime");
			return (Criteria) this;
		}

		public Criteria andSuccessTimeNotIn(List<Date> values) {
			addCriterion("success_time not in", values, "successTime");
			return (Criteria) this;
		}

		public Criteria andSuccessTimeBetween(Date value1, Date value2) {
			addCriterion("success_time between", value1, value2, "successTime");
			return (Criteria) this;
		}

		public Criteria andSuccessTimeNotBetween(Date value1, Date value2) {
			addCriterion("success_time not between", value1, value2, "successTime");
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