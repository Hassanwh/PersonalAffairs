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
	 * @param Long msgId (���ĺ��⣺��ϢID;�����ݴ洢���ͣ�δ֪)
	 */
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public String getMadiaId() {
		return this.madiaId;
	}

	/*
	 * @param String madiaId (���ĺ��⣺��Ƶ��Ϣý��id;�����ݴ洢���ͣ�VARCHAR(300))
	 */
	public void setMadiaId(String madiaId) {
		this.madiaId = madiaId;
	}

	public String getThumbMediaId() {
		return this.thumbMediaId;
	}

	/*
	 * @param String thumbMediaId (���ĺ��⣺��Ƶ��Ϣ����ͼ��ý��id;�����ݴ洢���ͣ�VARCHAR(300))
	 */
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}


	
}