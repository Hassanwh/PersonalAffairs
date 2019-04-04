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
public class ScanCodeEvent extends InMessage implements Serializable
{


	private String eventKey;
	private String ticket;
	
	
	// Constructors
	public ScanCodeEvent() {
	}	


	public String getEventKey() {
		return this.eventKey;
	}

	/*
	 * @param String eventKey (���ĺ��⣺�¼�KEYֵ;�����ݴ洢���ͣ�CHAR(32))
	 */
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getTicket() {
		return this.ticket;
	}

	/*
	 * @param String ticket (���ĺ��⣺���ڻ�ȡ��ά��ͼƬ;�����ݴ洢���ͣ�VARCHAR(300))
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}


	
}