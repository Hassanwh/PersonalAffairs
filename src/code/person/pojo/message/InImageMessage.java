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
	 * @param Long msgId (���ĺ��⣺��ϢID;�����ݴ洢���ͣ�δ֪)
	 */
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public String getPicUrl() {
		return this.picUrl;
	}

	/*
	 * @param String picUrl (���ĺ��⣺ͼƬ����;�����ݴ洢���ͣ�VARCHAR(300))
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}


	
}