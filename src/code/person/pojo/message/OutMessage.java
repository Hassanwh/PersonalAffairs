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
public class OutMessage implements Serializable
{

	// Fields
	private String FromUserName;
	private String ToUserName;
	private long CreateTime;
	private String MsgType;
	
	
	// Constructors
	public OutMessage() {
	}	

	public String getFromUserName() {
		return this.FromUserName;
	}

	/*
	 * @param String FromUserName (中文含意：发送方微信号;　数据存储类型：CHAR(28))
	 */
	public void setFromUserName(String FromUserName) {
		this.FromUserName = FromUserName;
	}

	public String getToUserName() {
		return this.ToUserName;
	}

	/*
	 * @param String ToUserName (中文含意：接收方微信号;　数据存储类型：CHAR(15))
	 */
	public void setToUserName(String ToUserName) {
		this.ToUserName = ToUserName;
	}

	public long getCreateTime() {
		return this.CreateTime;
	}

	/*
	 * @param Integer CreateTime (中文含意：消息创建时间;　数据存储类型：INT)
	 */
	public void setCreateTime(long CreateTime) {
		this.CreateTime = CreateTime;
	}

	public String getMsgType() {
		return this.MsgType;
	}

	/*
	 * @param String MsgType (中文含意：消息类型;　数据存储类型：VARCHAR(12))
	 */
	public void setMsgType(String MsgType) {
		this.MsgType = MsgType;
	}

	
}