package com.weichat.entity;
/**
 * 接收图片消息实体类
 * @author xxl
 *
 */
public class ReceiveImage extends BaseMessage{
	
	private String PicUrl;
	private String MediaId;
	
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
}
