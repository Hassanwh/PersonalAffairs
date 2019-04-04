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
public class InVideoMessage extends InMessage implements Serializable
{

	// Fields
	private long msgId;
	private String madiaId;
	private String thumbMediaId;
	
	
	// Constructors
	public InVideoMessage() {
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

	public String getMadiaId() {
		return this.madiaId;
	}

	/*
	 * @param String madiaId (中文含意：视频消息媒体id;　数据存储类型：VARCHAR(300))
	 */
	public void setMadiaId(String madiaId) {
		this.madiaId = madiaId;
	}

	public String getThumbMediaId() {
		return this.thumbMediaId;
	}

	/*
	 * @param String thumbMediaId (中文含意：视频消息缩略图的媒体id;　数据存储类型：VARCHAR(300))
	 */
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}


	
}