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
	 * @param String openId (中文含意：微信号;　数据存储类型：CHAR(28))
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Date getLockDate() {
		return this.lockDate;
	}

	/*
	 * @param Date lockDate (中文含意：锁定日期;　数据存储类型：DATE)
	 */
	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}

	public String getLockBy() {
		return this.lockBy;
	}

	/*
	 * @param String lockBy (中文含意：锁定渠道;　数据存储类型：CHAR(1))
	 */
	public void setLockBy(String lockBy) {
		this.lockBy = lockBy;
	}


	
}