package com.weichat.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * 模拟用户向公众号发送普通文本消息
 * @author xxl
 *
 */
public class Test {
	/**
	 * post请求
	 * @param url 请求地址
	 * @param param 请求参数
	 * @return 接口返回的内容
	 */
	static Logger logger = Logger.getLogger(Test.class);
	private static String post(String url, String param){
		
		try{
			HttpPost request = new HttpPost(url);
			request.setEntity(new StringEntity(param));
			HttpResponse response = HttpClients.createDefault().execute(request);
			//根据返回码判断请求是否成功
			if(200 == response.getStatusLine().getStatusCode()){
				String result = EntityUtils.toString(response.getEntity());
				logger.info("post请求成功，返回数据为："+result);
				return result;
			}
			logger.error("POST返回的数据为："+response.getStatusLine().getStatusCode());
			return "post失败";
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("POST返回的数据为："+e);
			return "post异常";
		}

	} 
	public static void main(String[] args) throws UnsupportedEncodingException {
		//发送文本消息
		String content = URLEncoder.encode("new","utf-8");
		String textParam = "<xml><ToUserName><![CDATA[toUser1]]></ToUserName><FromUserName><![CDATA[fromUser1]]></FromUserName><CreateTime>13488318601</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA["+content+"]]></Content><MsgId>1234567890123458</MsgId></xml>";
		//发送图片消息
		String imageParam = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[image]]></MsgType><PicUrl><![CDATA[http://localhost/weichat/img/imgMsg.png]]></PicUrl><MediaId><![CDATA[media_id]]></MediaId><MsgId>1234567890123458</MsgId></xml>";
		//click
		String eventParam = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[CLICK]]></Event><EventKey><![CDATA[EVENTKEY]]></EventKey></xml>";
		//dadada.applinzi.com  localhost/weichat
		String url = "http://localhost/weichat/wx.do";
		String result = post(url, eventParam);
		//System.out.println(result);
		//System.out.println("流程结束。。。。。。。。。。。。。。。。。。。。。。");
		
		logger.info("流程结束。。。。。。。。。。。。。。。。。。。。。。");
//		InputStream in = menuService.class.getResourceAsStream("/com/weichat/service/menuXML.xml"); 
//		String xml = File2String.stream2String(in,"utf-8");
//		logger.info(xml);
//		logger.info("XML转json:"+XmlConverUtil.xmltoJson(xml));
	}
}
