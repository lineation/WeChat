package com.weichat.utils;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.log4j.Logger;

import com.weichat.entity.BaseEvent;
import com.weichat.entity.BaseMessage;
import com.weichat.entity.MenuEvent;
import com.weichat.entity.ReceiveImage;
import com.weichat.entity.ReceiveText;

/**
 * 将微信端xml数据转换为对象,并封装信息到实体
 * @author xxl
 *
 */
public class XML2Entity {
	public static BaseMessage getMsgEntity(Map<String, String> map){
		Logger logger = Logger.getLogger(XML2Entity.class);
		BaseMessage msg =null;
		
		Class<?> msgEntity = null;
		//得到消息类型
		String msgType = map.get("MsgType")	;
		//得到时间类型
		String eventType = map.get("Event");
		logger.info("解析到的消息类型为："+msgType);
		//根据不同的消息类型，创建不同的实体类
		try{
			if(msgType.equalsIgnoreCase(MsgTypeUtil.REQ_MESSAGE_TYPE_TEXT)){
				//如果是文本消息类型，通过反射创建文本实体类，并实例化一个文本实体对象
				msgEntity = Class.forName("com.weichat.entity.ReceiveText");
				logger.info("反射得到ReceiveTextEntity类"+msgEntity);
				msg = (ReceiveText) msgEntity.newInstance();
				logger.info("创建文本消息实体对象成功。。。"+msg);
				
			}else if (msgType.equalsIgnoreCase(MsgTypeUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				//如果是图片消息类型，通过反射创建图片实体类，并实例化一个图片实体对象
				msgEntity = Class.forName("com.weichat.entity.ReceiveImage");
				logger.info("反射得到ReceiveImageEntity类"+msgEntity);
				msg = (ReceiveImage) msgEntity.newInstance();
				logger.info("创建图片消息实体对象成功。。。"+msg);
			}else if (msgType.equalsIgnoreCase(MsgTypeUtil.REQ_MESSAGE_TYPE_EVENT)) {
				//根据事件类型进行实体封装
				if(eventType.equalsIgnoreCase(MsgTypeUtil.EVENT_TYPE_CLICK)){
					//如果是菜单点击事件
					msgEntity = Class.forName("com.weichat.entity.MenuEvent");
					logger.info("反射得到MenuEvent类"+msgEntity);
					msg = (MenuEvent) msgEntity.newInstance();
					logger.info("创建MenuEvent实体对象成功"+msg);
				}
			}

			//利用反射调用实体类的set方法
			logger.info("通过反射封装消息到实体开始。。。");
			for (Map.Entry<String, String> entry : map.entrySet()) {

				String mapKey = entry.getKey();
				String mapValue = entry.getValue();
				Field field = null;
				Method method = null;
				logger.info("封装字段"+mapKey+"="+mapValue);
				//获取父类set方法中的参数字段
				if(mapKey.equalsIgnoreCase("ToUserName") ||mapKey.equalsIgnoreCase("FromUserName") ||
						mapKey.equalsIgnoreCase("CreateTime") ||mapKey.equalsIgnoreCase("MsgType") ||
						mapKey.equalsIgnoreCase("MsgId")|| mapKey.equalsIgnoreCase("Event") ){
					field = msgEntity.getSuperclass().getDeclaredField(mapKey);
					logger.info("字段名"+field.getName());
					method = msgEntity.getSuperclass().getDeclaredMethod("set"+mapKey,field.getType());
					logger.info("方法名"+method.getName());
					logger.info("通过反射得到map中的key="+mapKey+"和value="+mapValue+"属性类型Type="+field.getType());
					//调用set方法
					method.invoke(msg, mapValue); 
					logger.info("封装"+mapKey+"属性完成。。。");
				}else{
					//获取子类set方法中的参数字段
					field = msgEntity.getDeclaredField(mapKey);
					logger.info("字段名"+field.getName());
					method = msgEntity.getDeclaredMethod("set"+mapKey,field.getType());
					logger.info("方法名"+method.getName());
					logger.info("通过反射得到map中的key="+mapKey+"和value="+mapValue+"属性类型Type="+field.getType());
					//调用set方法
					method.invoke(msg, mapValue); 
					logger.info("封装"+mapKey+"属性完成。。。");
				}
			}
			logger.info("通过反射封装消息到实体完成。。。");
		}catch(Exception e){
			e.printStackTrace();
		}
		return msg;
	}
}
