package com.weichat.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

public class MenuUtil {
	/**
	 * 
	 * @return
	 */
	public static String getAccess_token(){  

		String APPID="";
		String APPSECRET="";
		String url = "";
		String accessToken = null;
		//获取资源文件中配置的信息
		Logger logger = Logger.getLogger(MenuUtil.class);
		ResourceBundle resourceBundle = ResourceBundle.getBundle("wechatParams");
		APPID = resourceBundle.getString("appID");
		logger.info("获得appID:"+APPID);
		APPSECRET = resourceBundle.getString("appsecret");
		logger.info("获得APPSECRET:"+APPSECRET);
		url += resourceBundle.getString("access_token_url")+ APPID + "&secret=" +APPSECRET;
		logger.info("获得access_token_url:"+url);
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();    

			http.setRequestMethod("GET");      //必须是get方式请求    
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
			http.setDoOutput(true);        
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
			http.connect();

			InputStream is =http.getInputStream();
			int size =is.available();
			byte[] jsonBytes =new byte[size];
			is.read(jsonBytes);
			String message=new String(jsonBytes,"UTF-8");

			JSONObject demoJson = JSONObject.fromObject(message);
			accessToken = demoJson.getString("access_token");
			logger.info("获得access_token:"+accessToken);
			System.out.println(message);
		} catch (Exception e) {
			logger.error("获取accessToken失败"+e);
			e.printStackTrace();
		}
		return accessToken;
	}
}
