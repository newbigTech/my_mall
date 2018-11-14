package com.d2c.util.date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 日期插入到数据库中查找
 * 
 * @author Lain
 *
 */
public class HolidayUtil {
	private static final String key = "e7b8ee14119177cda33f3703c5db7090";
	private static RestTemplate restTemplate = new RestTemplate();

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		BeanFactory factory = new ClassPathXmlApplicationContext("classpath*:/META-INF/spring-common/mybatis-log.xml");
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) factory.getBean("sqlSessionFactory");
		JdbcTemplate jdbcTemplate = (JdbcTemplate) factory.getBean("jdbcTemplate");

		SqlSession sqlSession = sqlSessionFactory.openSession();
		int year = 2018;
		int month = 1;
		for (; month < 13;) {
			JSONObject json = restTemplate.getForObject(
					"http://v.juhe.cn/calendar/month?year-month=" + year + "-" + month + "&key=" + key,
					JSONObject.class);
			if ("Success".equals(json.getString("reason"))) {
				String holidayStr = json.getJSONObject("result").getJSONObject("data").getString("holiday");
				JSONArray array = new JSONArray();
				if ("{".equals(holidayStr.substring(0, 1))) {
					array.add(JSON.parseObject(holidayStr));
				} else {
					array = JSON.parseArray(holidayStr);
				}
				for (int i = 0; i < array.size(); i++) {
					JSONObject holidayInfo = array.getJSONObject(i);
					JSONArray holidayItems = holidayInfo.getJSONArray("list");
					for (int j = 0; j < holidayItems.size(); j++) {
						// jdbcTemplate.insert("INSERT INTO sys_holiday (`date`)
						// VALUES(\""
						// + holidayItems.getJSONObject(j).getString("date") +
						// "\")");
						jdbcTemplate.execute("REPLACE INTO sys_holiday (`date`) VALUES(\""
								+ holidayItems.getJSONObject(j).getString("date") + "\")");
					}
				}
			}
			month++;
		}
		List<Date> dates = getWeekDayList();
		for (Date date : dates) {
			jdbcTemplate.execute("REPLACE INTO sys_holiday (`date`) VALUES(\"" + DateUtil.second2str(date) + "\")");
		}
		sqlSession.close();
	}

	// 这个月以后5个月的所有周末

	private static List<Date> getWeekDayList() {
		List<Date> result = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.YEAR, 2018);
		calendar.set(Calendar.MONTH, 0);
		int month = calendar.get(Calendar.MONTH);
		for (; calendar.get(Calendar.MONTH) - month < 11;) {
			if (calendar.get(Calendar.DAY_OF_WEEK) == 1 || calendar.get(Calendar.DAY_OF_WEEK) == 7) {
				result.add(calendar.getTime());
			}
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		return result;
	}
}

class Holiday2 {
	/**
	 * 节日
	 */
	private String name;
	/**
	 * 日期
	 */
	private String date;
	/**
	 * 1节假日 2是双休
	 */
	private Integer status;

	public Holiday2(JSONObject json) {
		this.date = json.getString("date");
		this.status = Integer.parseInt(json.getString("status"));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}