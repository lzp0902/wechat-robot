package com.lzp.wechat;

import java.util.Arrays;
import java.util.List;

import com.blade.kit.base.Config;

import com.lzp.wechat.model.WechatContact;

public class Constant {

	public static final String HTTP_OK = "200";
	public static final String BASE_URL = "https://webpush2.weixin.qq.com/cgi-bin/mmwebwx-bin";
	public static final String JS_LOGIN_URL = "https://login.weixin.qq.com/jslogin";
	public static final String QRCODE_URL = "https://login.weixin.qq.com/qrcode/";
	
	public static final String ITPK_API = "http://i.itpk.cn/api.php";
	
	public static final List<String> FILTER_USERS = Arrays.asList("1111111");
	
	public static final String[] SYNC_HOST = {
		"webpush.weixin.qq.com",
		"webpush2.weixin.qq.com",
		"webpush.wechat.com",
		"webpush1.wechat.com",
		"webpush2.wechat.com",
		"webpush1.wechatapp.com"
	};
	
	public static WechatContact CONTACT;
	
	// 全局配置文件
	public static Config config;
}
