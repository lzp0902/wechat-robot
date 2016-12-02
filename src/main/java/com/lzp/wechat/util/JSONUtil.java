package com.lzp.wechat.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {
	private static ObjectMapper objectMapper = new ObjectMapper();
	static {
		// 解析器支持解析单引号
		objectMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		// 解析器支持解析结束符
		objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 设置Date类型对象的转换格式
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object writeJSON2Object(String json, Class obj) throws IOException {
		return objectMapper.readValue(json, obj); // 转换为HashMap对象
	}
}
