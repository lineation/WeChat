package com.weichat.tuling;

import net.sf.json.JSONObject;

/**
 * tuling�����˽ӿڽ������̿���
 * @author xxl
 *
 */
public class TulingController {
	/**
	 * ����ͼ��ӿڣ�������������
	 * @param info
	 * @return
	 */
	public String getTulingRe(String info){
		// ����ͼ��ӿ�api����ȡ���	
		String url = "http://www.tuling123.com/openapi/api?key=32843a55fc864318acd391dd2bfe07a1&info="+info;
		String tlResult = HttpGetRequest.get(url);
		//����ͼ�������ݣ���ȡ��������
		JSONObject json = JSONObject.fromObject(tlResult);
		tlResult = json.getString("text");
		return tlResult;
	}
}
