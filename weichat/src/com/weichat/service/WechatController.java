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
 * ΢�����̿�����
 * @author xxl
 *
 */
public class WechatController {
	/**
	 * ΢�Ŵ������̿���
	 * @param map �ͻ��˷��͹�����XML����
	 * @param msgType 
	 * @return ������ɣ� ��װ�õ�XML�������
	 * @throws UnsupportedEncodingException 
	 */
	public String wechatProgress(Map<String, String> map) throws UnsupportedEncodingException{
		Logger logger = Logger.getLogger(WechatController.class);
		logger.info("����wechatProgress��������ʼ΢�Ŵ������̡���������������");
		String msgType = map.get("MsgType");
		
		//������ı���Ϣ���ȼ���ǲ��Ƕ���õĵĹؼ��֣�������ǹؼ��֣�����ͼ������˻ش�����ǹؼ�����ô������ط�����лظ�
		if(msgType.equalsIgnoreCase( MsgTypeUtil.REQ_MESSAGE_TYPE_TEXT)){
			//�������յ���XML���ݣ�ת��Ϊ����
			logger.info("���յ��ı���Ϣ������ParseReceivedXML�����ʵ���װ����������");
			ReceiveText xml = (ReceiveText) XML2Entity.getMsgEntity(map);
			if(xml.getContent().equalsIgnoreCase("new")){//new String("�����Ż�".getBytes("UTF-8"))//URLEncoder.encode("�����Ż�","utf-8")
				logger.info("���յ�����ͨ�ı���ϢΪ�ؼ��֣������Ż�");
				String keywordsResult = messageService.getKeywordsResult(xml);
				return keywordsResult ; 
			}
			//����ͼ������˽ӿڴ���ģ�飬���ͼ������˵Ľ��
			logger.info("����ͼ������˽ӿڡ���������");
			String tlResult = new TulingController().getTulingRe(xml.getContent());
			//��װXML�ӿڷ�������
			logger.info("�õ�ͼ������˷��ص���Ϣ��"+ tlResult);
			String xmlResult = messageService.getXmlResult(xml, tlResult);
			logger.info("��װΪ΢�ŷ�����Ϣ��XML��ʽ��"+ xmlResult);
			return xmlResult;
		}else if (msgType.equalsIgnoreCase(MsgTypeUtil.REQ_MESSAGE_TYPE_IMAGE)) {//�����ͼƬ��Ϣ���ظ�����Ϣ��ͼƬ��Ϣ
			//�������յ���XML���ݣ�ת��Ϊ����
			logger.info("���յ��ı���Ϣ������ParseReceivedXML�����ʵ���װ����������");
			//�����յ���XML��Ϣ��װ��imageʵ����
			ReceiveImage xml = (ReceiveImage) XML2Entity.getMsgEntity(map);
			//������Ϣ�������ԭ�����ؽ��յ���ͼƬ
			String imgResult = messageService.getImgResult(xml);
			logger.info("����messageService��������÷��ؽ��"+imgResult);
			return imgResult;
		}else if (msgType.equalsIgnoreCase( MsgTypeUtil.REQ_MESSAGE_TYPE_EVENT)){
			//�������յ���XML���ݣ�ת��Ϊ����
			logger.info("���յ��¼���Ϣ������ParseReceivedXML�����ʵ���װ����������");
			//�����յ���XML��Ϣ��װ��CLICK�¼�ʵ����
			MenuEvent xml = (MenuEvent) XML2Entity.getMsgEntity(map);
			logger.info("����menuClick��Ϣ");
			String keywordsResult = messageService.getClickResult(xml);
			return keywordsResult ; 
		}
		return "��Ϣ�����ж�ʧ��";						
	}
}
