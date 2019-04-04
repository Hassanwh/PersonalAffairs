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
	 * @param Long msgId (���ĺ��⣺��ϢID;�����ݴ洢���ͣ�δ֪)
	 */
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public String getContent() {
		return this.content;
	}

	/*
	 * @param String content (���ĺ��⣺��Ϣ����;�����ݴ洢���ͣ�VARCHAR(512))
	 */
	public void setContent(String content) {
		this.content = content;
	}


	
}