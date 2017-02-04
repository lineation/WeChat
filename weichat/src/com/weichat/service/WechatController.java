package com.weichat.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.log4j.Logger;

import com.weichat.entity.MenuEvent;
import com.weichat.entity.ReceiveImage;
import com.weichat.entity.ReceiveText;
import com.weichat.service.messageService;
import com.weichat.tuling.TulingController;
import com.weichat.utils.MsgTypeUtil;
import com.weichat.utils.XML2Entity;

/**
 * 微信流程控制类
 * @author xxl
 *
 */
public class WechatController {
	/**
	 * 微信处理流程控制
	 * @param map 客户端发送过来的XML数据
	 * @param msgType 
	 * @return 处理完成， 封装好的XML结果数据
	 * @throws UnsupportedEncodingException 
	 */
	public String wechatProgress(Map<String, String> map) throws UnsupportedEncodingException{
		Logger logger = Logger.getLogger(WechatController.class);
		logger.info("调用wechatProgress方法，开始微信处理流程。。。。。。。。");
		String msgType = map.get("MsgType");
		
		//如果是文本消息，先检查是不是定义好的的关键字，如果不是关键字，调用图灵机器人回答，如果是关键字那么调用相关服务进行回复
		if(msgType.equalsIgnoreCase( MsgTypeUtil.REQ_MESSAGE_TYPE_TEXT)){
			//解析接收到的XML数据，转换为对象
			logger.info("接收到文本消息，调用ParseReceivedXML类进行实体封装。。。。。");
			ReceiveText xml = (ReceiveText) XML2Entity.getMsgEntity(map);
			if(xml.getContent().equalsIgnoreCase("new")){//new String("最新优惠".getBytes("UTF-8"))//URLEncoder.encode("最新优惠","utf-8")
				logger.info("接收到的普通文本消息为关键字：最新优惠");
				String keywordsResult = messageService.getKeywordsResult(xml);
				return keywordsResult ; 
			}
			//调用图灵机器人接口处理模块，获得图灵机器人的结果
			logger.info("调用图灵机器人接口。。。。。");
			String tlResult = new TulingController().getTulingRe(xml.getContent());
			//封装XML接口返回数据
			logger.info("得到图灵机器人返回的消息："+ tlResult);
			String xmlResult = messageService.getXmlResult(xml, tlResult);
			logger.info("封装为微信返回消息的XML格式："+ xmlResult);
			return xmlResult;
		}else if (msgType.equalsIgnoreCase(MsgTypeUtil.REQ_MESSAGE_TYPE_IMAGE)) {//如果是图片消息，回复该消息是图片消息
			//解析接收到的XML数据，转换为对象
			logger.info("接收到文本消息，调用ParseReceivedXML类进行实体封装。。。。。");
			//将接收到的XML信息封装到image实体中
			ReceiveImage xml = (ReceiveImage) XML2Entity.getMsgEntity(map);
			//调用消息处理服务，原样返回接收到的图片
			String imgResult = messageService.getImgResult(xml);
			logger.info("调用messageService方法，获得返回结果"+imgResult);
			return imgResult;
		}else if (msgType.equalsIgnoreCase( MsgTypeUtil.REQ_MESSAGE_TYPE_EVENT)){
			//解析接收到的XML数据，转换为对象
			logger.info("接收到事件消息，调用ParseReceivedXML类进行实体封装。。。。。");
			//将接收到的XML信息封装到CLICK事件实体中
			MenuEvent xml = (MenuEvent) XML2Entity.getMsgEntity(map);
			logger.info("接收menuClick消息");
			String keywordsResult = messageService.getClickResult(xml);
			return keywordsResult ; 
		}
		return "消息类型判断失败";						
	}
}
