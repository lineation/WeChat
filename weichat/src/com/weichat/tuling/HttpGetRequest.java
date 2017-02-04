package com.weichat.tuling;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * http get请求类
 * @author xxl
 *
 */
public class HttpGetRequest {
	/**
	 * get 请求
	 * @param url 请求的地址和参数
	 */
	public static String get(String url){
		
		try{
			HttpGet request = new HttpGet(url);
			//执行Http请求
			HttpResponse response = HttpClients.createDefault().execute(request);
			//根据返回码判断请求是否成功
			String result = "";
			if(response.getStatusLine().getStatusCode() == 200){
				result = EntityUtils.toString(response.getEntity());
			}
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}		
	}
}
