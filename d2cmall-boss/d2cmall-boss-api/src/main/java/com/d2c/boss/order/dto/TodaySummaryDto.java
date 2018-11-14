package com.d2c.boss.order.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.d2c.boss.member.model.Score;

public class TodaySummaryDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, Object> olTodayPay;
	private Map<String, Object> olTodayCod;
	private Map<String, Object> olYesterDayPay;
	private Map<String, Object> olYesterDayCod;
	private Map<String, Object> olWeekPay;
	private Map<String, Object> olWeekCod;
	private Map<String, Object> olMonthPay;
	private Map<String, Object> olMonthCod;
	private List<Map<String, Object>> offToday;
	private List<Map<String, Object>> offYesterDay;
	private List<Map<String, Object>> offWeek;
	private List<Map<String, Object>> offMonth;
	private Map<String, Object> offTodaySum;
	private Map<String, Object> offYesterDaySum;
	private Map<String, Object> offWeekSum;
	private Map<String, Object> offMonthSum;
	private List<Score> stores;

	public Map<String, Object> getOlTodayPay() {
		return olTodayPay;
	}

	public void setOlTodayPay(Map<String, Object> olTodayPay) {
		this.olTodayPay = olTodayPay;
	}

	public Map<String, Object> getOlTodayCod() {
		return olTodayCod;
	}

	public void setOlTodayCod(Map<String, Object> olTodayCod) {
		this.olTodayCod = olTodayCod;
	}

	public Map<String, Object> getOlYesterDayPay() {
		return olYesterDayPay;
	}

	public void setOlYesterDayPay(Map<String, Object> olYesterDayPay) {
		this.olYesterDayPay = olYesterDayPay;
	}

	public Map<String, Object> getOlYesterDayCod() {
		return olYesterDayCod;
	}

	public void setOlYesterDayCod(Map<String, Object> olYesterDayCod) {
		this.olYesterDayCod = olYesterDayCod;
	}

	public Map<String, Object> getOlWeekPay() {
		return olWeekPay;
	}

	public void setOlWeekPay(Map<String, Object> olWeekPay) {
		this.olWeekPay = olWeekPay;
	}

	public Map<String, Object> getOlWeekCod() {
		return olWeekCod;
	}

	public void setOlWeekCod(Map<String, Object> olWeekCod) {
		this.olWeekCod = olWeekCod;
	}

	public Map<String, Object> getOlMonthPay() {
		return olMonthPay;
	}

	public void setOlMonthPay(Map<String, Object> olMonthPay) {
		this.olMonthPay = olMonthPay;
	}

	public Map<String, Object> getOlMonthCod() {
		return olMonthCod;
	}

	public void setOlMonthCod(Map<String, Object> olMonthCod) {
		this.olMonthCod = olMonthCod;
	}

	public Map<String, Object> getOffTodaySum() {
		return offTodaySum;
	}

	public void setOffTodaySum(Map<String, Object> offTodaySum) {
		this.offTodaySum = offTodaySum;
	}

	public Map<String, Object> getOffYesterDaySum() {
		return offYesterDaySum;
	}

	public void setOffYesterDaySum(Map<String, Object> offYesterDaySum) {
		this.offYesterDaySum = offYesterDaySum;
	}

	public Map<String, Object> getOffWeekSum() {
		return offWeekSum;
	}

	public void setOffWeekSum(Map<String, Object> offWeekSum) {
		this.offWeekSum = offWeekSum;
	}

	public Map<String, Object> getOffMonthSum() {
		return offMonthSum;
	}

	public void setOffMonthSum(Map<String, Object> offMonthSum) {
		this.offMonthSum = offMonthSum;
	}

	public List<Map<String, Object>> getOffToday() {
		return offToday;
	}

	public void setOffToday(List<Map<String, Object>> offToday) {
		this.offToday = offToday;
	}

	public List<Map<String, Object>> getOffYesterDay() {
		return offYesterDay;
	}

	public void setOffYesterDay(List<Map<String, Object>> offYesterDay) {
		this.offYesterDay = offYesterDay;
	}

	public List<Map<String, Object>> getOffWeek() {
		return offWeek;
	}

	public void setOffWeek(List<Map<String, Object>> offWeek) {
		this.offWeek = offWeek;
	}

	public List<Map<String, Object>> getOffMonth() {
		return offMonth;
	}

	public void setOffMonth(List<Map<String, Object>> offMonth) {
		this.offMonth = offMonth;
	}

	public List<Score> getStores() {
		return stores;
	}

	public void setStores(List<Score> stores) {
		this.stores = stores;
	}

}
