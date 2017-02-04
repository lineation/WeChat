package com.weichat.entity;
/**
 * 微信服务器接收普通文本消息的实体
 * @author xxl
 *
 */
public class ReceiveText extends BaseMessage{

	private String Content;
	
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
		
}
