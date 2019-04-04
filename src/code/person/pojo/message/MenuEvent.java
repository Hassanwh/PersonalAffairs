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
public class MenuEvent extends InMessage implements Serializable
{

	// Fields
	private String eventKey;
	
	
	// Constructors
	public MenuEvent() {
	}	

	public String getEventKey() {
		return this.eventKey;
	}

	/*
	 * @param String eventKey (中文含意：事件KEY值;　数据存储类型：CHAR(4))
	 */
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}


	
}