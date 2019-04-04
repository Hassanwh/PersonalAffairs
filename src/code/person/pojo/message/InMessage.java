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
public class InMessage implements Serializable
{

	// Fields
	private long msgId;
	private String fromUserName;
	private String toUserName;
	private long createTime;
	private String msgType;
	private String event;
	private String msgStatus="0";
	
	
	// Constructors
	public InMessage() {
	}	

	// Property accessors	
	public long getMsgId() {
		return this.msgId;
	}

	/*
	 * @param Long msgId (���ĺ��⣺��Ϣid;�����ݴ洢���ͣ�δ֪)
	 */
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public String getFromUserName() {
		return this.fromUserName;
	}

	/*
	 * @param String fromUserName (���ĺ��⣺���ͷ�΢�ź�;�����ݴ洢���ͣ�CHAR(28))
	 */
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getToUserName() {
		return this.toUserName;
	}

	/*
	 * @param String toUserName (���ĺ��⣺���շ�΢�ź�;�����ݴ洢���ͣ�CHAR(15))
	 */
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public long getCreateTime() {
		return this.createTime;
	}

	/*
	 * @param Integer createTime (���ĺ��⣺��Ϣ����ʱ��;�����ݴ洢���ͣ�INT)
	 */
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return this.msgType;
	}

	/*
	 * @param String msgType (���ĺ��⣺��Ϣ����;�����ݴ洢���ͣ�VARCHAR(12))
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getEvent() {
		return this.event;
	}

	/*
	 * @param String event (���ĺ��⣺�¼�����;�����ݴ洢���ͣ�VARCHAR(12))
	 */
	public void setEvent(String event) {
		this.event = event;
	}

	public String getMsgStatus() {
		return this.msgStatus;
	}

	/*
	 * @param String msgStatus (���ĺ��⣺��Ϣ״̬;�����ݴ洢���ͣ�CHAR(1))
	 */
	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	
    

	
}