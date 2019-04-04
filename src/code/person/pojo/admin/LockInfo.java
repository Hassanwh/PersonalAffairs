package code.person.pojo.admin;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 * <br/>Copyright (C), 2001-2011, 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:	2015-06-10
 * @author  ldh
 * @version  2.0
 */
public class LockInfo implements Serializable
{

	// Fields
	private String openId;
	private Date lockDate;
	private String lockBy;
	
	
	// Constructors
	public LockInfo() {
	}	

	// Property accessors	
	public String getOpenId() {
		return this.openId;
	}

	/*
	 * @param String openId (���ĺ��⣺΢�ź�;�����ݴ洢���ͣ�CHAR(28))
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Date getLockDate() {
		return this.lockDate;
	}

	/*
	 * @param Date lockDate (���ĺ��⣺��������;�����ݴ洢���ͣ�DATE)
	 */
	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}

	public String getLockBy() {
		return this.lockBy;
	}

	/*
	 * @param String lockBy (���ĺ��⣺��������;�����ݴ洢���ͣ�CHAR(1))
	 */
	public void setLockBy(String lockBy) {
		this.lockBy = lockBy;
	}


	
}