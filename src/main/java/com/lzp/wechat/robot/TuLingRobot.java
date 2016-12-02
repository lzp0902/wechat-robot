package com.lzp.wechat.robot;

import java.io.IOException;

import com.lzp.wechat.Constant;
import com.lzp.wechat.model.TlInfoVO;
import com.lzp.wechat.util.Aes;
import com.lzp.wechat.util.Md5;
import com.lzp.wechat.util.PostServer;
import com.lzp.wechat.util.JSONUtil;

import com.alibaba.fastjson.JSONObject;

public class TuLingRobot implements Robot{
	private String api_key;
	private String api_secret;
	public TuLingRobot() {
		this.api_key = Constant.config.get("tlpk.api_key");
		this.api_secret = Constant.config.get("tlpk.api_secret");
	}

	@Override
	public String talk(String msg) {
		//图灵网站上的secret
		//图灵网站上的apiKey
		//待加密的json数据
		String data = "{\"key\":\""+this.api_key+"\",\"info\":\""+msg+"\"}";
		//获取时间戳
		String timestamp = String.valueOf(System.currentTimeMillis());
		//生成密钥
		String keyParam = this.api_secret + timestamp + this.api_key;
		String key = Md5.MD5(keyParam);
		
		//加密
		Aes mc = new Aes(key);
		data = mc.encrypt(data);		
		
		//封装请求参数
		JSONObject json = new JSONObject();
		json.put("key", this.api_key);
		json.put("timestamp", timestamp);
		json.put("data", data);
		//请求图灵api
		String message = "";
		String repStr = PostServer.SendPost(json.toString(), "http://www.tuling123.com/openapi/api");
		boolean isUnusual = false;
		try {
			TlInfoVO obj= (TlInfoVO) JSONUtil.writeJSON2Object(repStr, TlInfoVO.class);
			if ("100000".equals(obj.getCode())){
				message = obj.getText();
			} else {
				message = "原谅傻强不知道该怎么说";
			}
		} catch (IOException e) {
			e.printStackTrace();
			isUnusual = true;
		} finally {
			if (isUnusual) {
				message = "傻强向你抛出了异常";
			}
		}
		return message;
	}
}
