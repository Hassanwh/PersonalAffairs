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
public class InTextMessage extends InMessage implements Serializable
{

	// Fields
	private long msgId;
	private String content;
	
	
	// Constructors
	public InTextMessage() {
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

	public String getContent() {
		return this.content;
	}

	/*
	 * @param String content (中文含意：消息内容;　数据存储类型：VARCHAR(512))
	 */
	public void setContent(String content) {
		this.content = content;
	}


	
}