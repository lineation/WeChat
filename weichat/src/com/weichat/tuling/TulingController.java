package com.weichat.tuling;

import net.sf.json.JSONObject;

/**
 * tuling机器人接口接入流程控制
 * @author xxl
 *
 */
public class TulingController {
	/**
	 * 调用图灵接口，返回所需内容
	 * @param info
	 * @return
	 */
	public String getTulingRe(String info){
		// 调用图灵接口api，获取结果	
		String url = "http://www.tuling123.com/openapi/api?key=32843a55fc864318acd391dd2bfe07a1&info="+info;
		String tlResult = HttpGetRequest.get(url);
		//解析图灵结果数据，提取所需数据
		JSONObject json = JSONObject.fromObject(tlResult);
		tlResult = json.getString("text");
		return tlResult;
	}
}
