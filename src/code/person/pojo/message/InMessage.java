package code.person.pojo.message;

import java.io.Serializable;


/**
 * Description:
 * <br/>Copyright (C), 2001-2011, 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:	2014-10-23
 * @author  ldh
 * @version  2.0
 */
public class InMessage implements Serializable
{

	// Fields
	private long msgId;
	private String fromUserName;
	private String toUserName;
	private long createTime;
	private String msgType;
	private String event;
	private String msgStatus="0";
	
	
	// Constructors
	public InMessage() {
	}	

	// Property accessors	
	public long getMsgId() {
		return this.msgId;
	}

	/*
	 * @param Long msgId (中文含意：消息id;　数据存储类型：未知)
	 */
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public String getFromUserName() {
		return this.fromUserName;
	}

	/*
	 * @param String fromUserName (中文含意：发送方微信号;　数据存储类型：CHAR(28))
	 */
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getToUserName() {
		return this.toUserName;
	}

	/*
	 * @param String toUserName (中文含意：接收方微信号;　数据存储类型：CHAR(15))
	 */
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public long getCreateTime() {
		return this.createTime;
	}

	/*
	 * @param Integer createTime (中文含意：消息创建时间;　数据存储类型：INT)
	 */
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return this.msgType;
	}

	/*
	 * @param String msgType (中文含意：消息类型;　数据存储类型：VARCHAR(12))
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getEvent() {
		return this.event;
	}

	/*
	 * @param String event (中文含意：事件类型;　数据存储类型：VARCHAR(12))
	 */
	public void setEvent(String event) {
		this.event = event;
	}

	public String getMsgStatus() {
		return this.msgStatus;
	}

	/*
	 * @param String msgStatus (中文含意：消息状态;　数据存储类型：CHAR(1))
	 */
	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	
    

	
}