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
public class InLinkMessage extends InMessage implements Serializable
{

	// Fields
	private long msgId;
	private String title;
	private String description;
	private String url;
	
	
	// Constructors
	public InLinkMessage() {
	}	

	// Property accessors	
	public long getMsgId() {
		return this.msgId;
	}

	/*
	 * @param Long msgId (中文含意：消息ID;　数据存储类型：未知)
	 */
	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getTitle() {
		return this.title;
	}

	/*
	 * @param String title (中文含意：消息标题;　数据存储类型：VARCHAR(50))
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	/*
	 * @param String description (中文含意：消息描述;　数据存储类型：VARCHAR(200))
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return this.url;
	}

	/*
	 * @param String url (中文含意：消息链接;　数据存储类型：VARCHAR(300))
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	
}