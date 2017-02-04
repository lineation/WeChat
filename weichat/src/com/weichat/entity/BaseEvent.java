package com.weichat.entity;

public class BaseEvent extends BaseMessage{

	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	private String MsgId;
	// 事件类型
    private String Event;
    public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

    
    
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
    
}
