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
		//��ȡ��Դ�ļ������õ���Ϣ
		Logger logger = Logger.getLogger(MenuUtil.class);
		ResourceBundle resourceBundle = ResourceBundle.getBundle("wechatParams");
		APPID = resourceBundle.getString("appID");
		logger.info("���appID:"+APPID);
		APPSECRET = resourceBundle.getString("appsecret");
		logger.info("���APPSECRET:"+APPSECRET);
		url += resourceBundle.getString("access_token_url")+ APPID + "&secret=" +APPSECRET;
		logger.info("���access_token_url:"+url);
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();    

			http.setRequestMethod("GET");      //������get��ʽ����    
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
			http.setDoOutput(true);        
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//���ӳ�ʱ30��
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //��ȡ��ʱ30��
			http.connect();

			InputStream is =http.getInputStream();
			int size =is.available();
			byte[] jsonBytes =new byte[size];
			is.read(jsonBytes);
			String message=new String(jsonBytes,"UTF-8");

			JSONObject demoJson = JSONObject.fromObject(message);
			accessToken = demoJson.getString("access_token");
			logger.info("���access_token:"+accessToken);
			System.out.println(message);
		} catch (Exception e) {
			logger.error("��ȡaccessTokenʧ��"+e);
			e.printStackTrace();
		}
		return accessToken;
	}
}
