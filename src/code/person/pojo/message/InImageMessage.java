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
public class InImageMessage extends InMessage implements Serializable
{

	// Fields
	private Long msgId;
	private String picUrl;
	
	
	// Constructors
	public InImageMessage() {
	}	

	// Property accessors	
	public long getMsgId() {
		return this.msgId;
	}

	/*
	 * @param Long msgId (中文含意：消息ID;　数据存储类型：未知)
	 */
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public String getPicUrl() {
		return this.picUrl;
	}

	/*
	 * @param String picUrl (中文含意：图片链接;　数据存储类型：VARCHAR(300))
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}


	
}