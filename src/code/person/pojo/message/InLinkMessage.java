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
	 * @param Long msgId (���ĺ��⣺��ϢID;�����ݴ洢���ͣ�δ֪)
	 */
	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getTitle() {
		return this.title;
	}

	/*
	 * @param String title (���ĺ��⣺��Ϣ����;�����ݴ洢���ͣ�VARCHAR(50))
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	/*
	 * @param String description (���ĺ��⣺��Ϣ����;�����ݴ洢���ͣ�VARCHAR(200))
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return this.url;
	}

	/*
	 * @param String url (���ĺ��⣺��Ϣ����;�����ݴ洢���ͣ�VARCHAR(300))
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	
}