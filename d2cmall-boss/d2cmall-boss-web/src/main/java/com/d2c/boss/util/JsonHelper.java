package com.d2c.boss.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.util.JSONUtils;

public class JsonHelper {
	private static JsonHelper instance;
	private static String dateFormat;
	private static SerializeConfig mapping = new SerializeConfig();

	private JsonHelper() {
		registerMorpher();
	}

	public static JsonHelper instance() {
		if (instance == null)
			instance = new JsonHelper();
		return instance;
	}

	static {
		dateFormat = "yyyy-MM-dd HH:mm:ss";
	}

	/**
	 * 默认的处理时间
	 * 
	 * @param jsonText
	 * @return
	 */
	public static String toJSON(Object jsonText) {
		return JSON.toJSONString(jsonText, SerializerFeature.WriteDateUseDateFormat);
	}

	/**
	 * 自定义时间格式
	 * 
	 * @param jsonText
	 * @return
	 */
	public static String toJSON(String jsonText) {
		mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
		return JSON.toJSONString(jsonText, mapping);
	}

	public void registerMorpher() {
		String[] dateFormats = new String[] { "yyyy-MM-dd HH:mm:ss" };
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
	}

	public <T> T toObject(JSONObject jsonObject, Class<T> clazz) {
		return (T) JSONObject.toJavaObject(jsonObject, clazz);
	}

	public String toJSONString(Object object) {
		String jsonString = null;
		if (object != null) {
			jsonString = JSONObject.toJSONString(object, SerializerFeature.WriteMapNullValue);
		}
		return jsonString == null ? "{}" : jsonString;
	}

	public <T> List<T> toObjects(JSONArray jsonArray, Class<T> clazz) {
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			result.add(this.toObject(jsonObject, clazz));
		}
		return result;
	}
}
