package com.weichat.service;

import java.util.Date;

import com.weichat.entity.MenuEvent;
import com.weichat.entity.ReceiveImage;
import com.weichat.entity.ReceiveText;
/**
 * ��Ϣ�������
 * ��װ΢�Ź��ڵķ�������
 * @author xxl
 *
 */
public class messageService {
	/**
	 * ��װͼƬ��Ϣ�ķ�������
	 * @param xml ���յ���XML����
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
	 * ��װ�ı���ϢXML�������
	 * @param xml  ���յ���XML����
	 * @param t1Result ͼ������˴�����
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
		sb.append("</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>1</ArticleCount><Articles><item><Title><![CDATA[�����Ż�]]></Title><Description><![CDATA[��С�ֵ������Żݲ���]]></Description><PicUrl><![CDATA[");
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
		sb.append("</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>1</ArticleCount><Articles><item><Title><![CDATA[�����Ż�]]></Title><Description><![CDATA[��С�ֵ������Żݲ���]]></Description><PicUrl><![CDATA[");
		sb.append("http://dadada.applinzi.com/img/imgMsg.png");
		sb.append("]]></PicUrl><Url><![CDATA[");
		sb.append("www.baidu.com");
		sb.append("]]></Url></item></Articles></xml>"); 
		
		return sb.toString();
	}
}
