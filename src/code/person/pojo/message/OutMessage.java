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
public class OutMessage implements Serializable
{

	// Fields
	private String FromUserName;
	private String ToUserName;
	private long CreateTime;
	private String MsgType;
	
	
	// Constructors
	public OutMessage() {
	}	

	public String getFromUserName() {
		return this.FromUserName;
	}

	/*
	 * @param String FromUserName (���ĺ��⣺���ͷ�΢�ź�;�����ݴ洢���ͣ�CHAR(28))
	 */
	public void setFromUserName(String FromUserName) {
		this.FromUserName = FromUserName;
	}

	public String getToUserName() {
		return this.ToUserName;
	}

	/*
	 * @param String ToUserName (���ĺ��⣺���շ�΢�ź�;�����ݴ洢���ͣ�CHAR(15))
	 */
	public void setToUserName(String ToUserName) {
		this.ToUserName = ToUserName;
	}

	public long getCreateTime() {
		return this.CreateTime;
	}

	/*
	 * @param Integer CreateTime (���ĺ��⣺��Ϣ����ʱ��;�����ݴ洢���ͣ�INT)
	 */
	public void setCreateTime(long CreateTime) {
		this.CreateTime = CreateTime;
	}

	public String getMsgType() {
		return this.MsgType;
	}

	/*
	 * @param String MsgType (���ĺ��⣺��Ϣ����;�����ݴ洢���ͣ�VARCHAR(12))
	 */
	public void setMsgType(String MsgType) {
		this.MsgType = MsgType;
	}

	
}