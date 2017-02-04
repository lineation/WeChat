package com.weichat.service;

import java.util.Date;

import com.weichat.entity.MenuEvent;
import com.weichat.entity.ReceiveImage;
import com.weichat.entity.ReceiveText;
/**
 * 消息处理服务
 * 封装微信公众的返回数据
 * @author xxl
 *
 */
public class messageService {
	/**
	 * 封装图片消息的返回数据
	 * @param xml 接收到的XML对象
	 * @return
	 */
	public static String getImgResult(ReceiveImage xml){
		StringBuffer sb = new StringBuffer();
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(xml.getFromUserName());
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(xml.getToUserName());
		sb.append("]]></FromUserName><CreateTime>");
		sb.append(xml.getCreateTime());
		sb.append("</CreateTime><MsgType><![CDATA[image]]></MsgType><Image><MediaId><![CDATA[");
		sb.append(xml.getMediaId());
		sb.append("]]></MediaId></Image></xml>");
		
		return sb.toString();
		
	}
	/**
	 * 封装文本消息XML结果数据
	 * @param xml  接收到的XML对象
	 * @param t1Result 图灵机器人处理结果
	 * @return
	 */
	public static String getXmlResult(ReceiveText xml,String t1Result){
		
		StringBuffer sb = new StringBuffer();
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(xml.getFromUserName());
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(xml.getToUserName());
		sb.append("]]></FromUserName><CreateTime>");
		sb.append(new Date().getTime());
		sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");
		sb.append(t1Result);
		sb.append("]]></Content></xml>");
		
		return sb.toString();
		
	}
	public static String getKeywordsResult(ReceiveText xml){
		
		StringBuffer sb = new StringBuffer();
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(xml.getFromUserName());
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(xml.getToUserName());
		sb.append("]]></FromUserName><CreateTime>");
		sb.append(new Date().getTime());
		sb.append("</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>1</ArticleCount><Articles><item><Title><![CDATA[最新优惠]]></Title><Description><![CDATA[向小林的最新优惠测试]]></Description><PicUrl><![CDATA[");
		sb.append("http://dadada.applinzi.com/img/imgMsg.png");
		sb.append("]]></PicUrl><Url><![CDATA[");
		sb.append("www.baidu.com");
		sb.append("]]></Url></item></Articles></xml>"); 
		
		return sb.toString();
		
	}
	public static String getClickResult(MenuEvent xml) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(xml.getFromUserName());
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(xml.getToUserName());
		sb.append("]]></FromUserName><CreateTime>");
		sb.append(new Date().getTime());
		sb.append("</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>1</ArticleCount><Articles><item><Title><![CDATA[最新优惠]]></Title><Description><![CDATA[向小林的最新优惠测试]]></Description><PicUrl><![CDATA[");
		sb.append("http://dadada.applinzi.com/img/imgMsg.png");
		sb.append("]]></PicUrl><Url><![CDATA[");
		sb.append("www.baidu.com");
		sb.append("]]></Url></item></Articles></xml>"); 
		
		return sb.toString();
	}
}
