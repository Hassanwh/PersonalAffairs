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
public class InVoiceMessage extends InMessage implements Serializable
{

	// Fields
	private long msgId;
	private String mediaid;
	private String format;
	private String recognition;
	
	
	// Constructors
	public InVoiceMessage() {
	}	

	// Property accessors	
	public long getMsgId() {
		return this.msgId;
	}

	/*
	 * @param Long msgId (���ĺ��⣺��Ϣ��ʶ;�����ݴ洢���ͣ�δ֪)
	 */
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public String getMediaid() {
		return this.mediaid;
	}

	/*
	 * @param String mediaid (���ĺ��⣺ý��ID;�����ݴ洢���ͣ�VARCHAR(300))
	 */
	public void setMediaid(String mediaid) {
		this.mediaid = mediaid;
	}

	public String getFormat() {
		return this.format;
	}

	/*
	 * @param String format (���ĺ��⣺������ʽ;�����ݴ洢���ͣ�VARCHAR(10))
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	public String getRecognition() {
		return this.recognition;
	}

	/*
	 * @param String recognition (���ĺ��⣺����ʶ����;�����ݴ洢���ͣ�VARCHAR(300))
	 */
	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}


	
}