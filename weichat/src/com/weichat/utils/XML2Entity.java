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
 * ��΢�Ŷ�xml����ת��Ϊ����,����װ��Ϣ��ʵ��
 * @author xxl
 *
 */
public class XML2Entity {
	public static BaseMessage getMsgEntity(Map<String, String> map){
		Logger logger = Logger.getLogger(XML2Entity.class);
		BaseMessage msg =null;
		
		Class<?> msgEntity = null;
		//�õ���Ϣ����
		String msgType = map.get("MsgType")	;
		//�õ�ʱ������
		String eventType = map.get("Event");
		logger.info("����������Ϣ����Ϊ��"+msgType);
		//���ݲ�ͬ����Ϣ���ͣ�������ͬ��ʵ����
		try{
			if(msgType.equalsIgnoreCase(MsgTypeUtil.REQ_MESSAGE_TYPE_TEXT)){
				//������ı���Ϣ���ͣ�ͨ�����䴴���ı�ʵ���࣬��ʵ����һ���ı�ʵ�����
				msgEntity = Class.forName("com.weichat.entity.ReceiveText");
				logger.info("����õ�ReceiveTextEntity��"+msgEntity);
				msg = (ReceiveText) msgEntity.newInstance();
				logger.info("�����ı���Ϣʵ�����ɹ�������"+msg);
				
			}else if (msgType.equalsIgnoreCase(MsgTypeUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				//�����ͼƬ��Ϣ���ͣ�ͨ�����䴴��ͼƬʵ���࣬��ʵ����һ��ͼƬʵ�����
				msgEntity = Class.forName("com.weichat.entity.ReceiveImage");
				logger.info("����õ�ReceiveImageEntity��"+msgEntity);
				msg = (ReceiveImage) msgEntity.newInstance();
				logger.info("����ͼƬ��Ϣʵ�����ɹ�������"+msg);
			}else if (msgType.equalsIgnoreCase(MsgTypeUtil.REQ_MESSAGE_TYPE_EVENT)) {
				//�����¼����ͽ���ʵ���װ
				if(eventType.equalsIgnoreCase(MsgTypeUtil.EVENT_TYPE_CLICK)){
					//����ǲ˵�����¼�
					msgEntity = Class.forName("com.weichat.entity.MenuEvent");
					logger.info("����õ�MenuEvent��"+msgEntity);
					msg = (MenuEvent) msgEntity.newInstance();
					logger.info("����MenuEventʵ�����ɹ�"+msg);
				}
			}

			//���÷������ʵ�����set����
			logger.info("ͨ�������װ��Ϣ��ʵ�忪ʼ������");
			for (Map.Entry<String, String> entry : map.entrySet()) {

				String mapKey = entry.getKey();
				String mapValue = entry.getValue();
				Field field = null;
				Method method = null;
				logger.info("��װ�ֶ�"+mapKey+"="+mapValue);
				//��ȡ����set�����еĲ����ֶ�
				if(mapKey.equalsIgnoreCase("ToUserName") ||mapKey.equalsIgnoreCase("FromUserName") ||
						mapKey.equalsIgnoreCase("CreateTime") ||mapKey.equalsIgnoreCase("MsgType") ||
						mapKey.equalsIgnoreCase("MsgId")|| mapKey.equalsIgnoreCase("Event") ){
					field = msgEntity.getSuperclass().getDeclaredField(mapKey);
					logger.info("�ֶ���"+field.getName());
					method = msgEntity.getSuperclass().getDeclaredMethod("set"+mapKey,field.getType());
					logger.info("������"+method.getName());
					logger.info("ͨ������õ�map�е�key="+mapKey+"��value="+mapValue+"��������Type="+field.getType());
					//����set����
					method.invoke(msg, mapValue); 
					logger.info("��װ"+mapKey+"������ɡ�����");
				}else{
					//��ȡ����set�����еĲ����ֶ�
					field = msgEntity.getDeclaredField(mapKey);
					logger.info("�ֶ���"+field.getName());
					method = msgEntity.getDeclaredMethod("set"+mapKey,field.getType());
					logger.info("������"+method.getName());
					logger.info("ͨ������õ�map�е�key="+mapKey+"��value="+mapValue+"��������Type="+field.getType());
					//����set����
					method.invoke(msg, mapValue); 
					logger.info("��װ"+mapKey+"������ɡ�����");
				}
			}
			logger.info("ͨ�������װ��Ϣ��ʵ����ɡ�����");
		}catch(Exception e){
			e.printStackTrace();
		}
		return msg;
	}
}
