package code.person.pojo.customer;

import java.io.Serializable;


/**
 * Description:
 * <br/>Copyright (C), 2001-2011, 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:	2014-10-21
 * @author  ldh
 * @version  2.0
 */
public class CusRel extends CusInfo implements Serializable
{

	// Fields
	private String id;
	private String openid;
	private String accountNo;
	private String relType;
	private String relStatus;
	private String acctNoSect;
	
	
	public String getRelStatus() {
		return relStatus;
	}



	public void setRelStatus(String relStatus) {
		this.relStatus = relStatus;
	}



	// Constructors
	public CusRel() {
	}	

	

	public String getRelType() {
		return this.relType;
	}

	/*
	 * @param String relType (中文含意：关联类型;　数据存储类型：CHAR(1))
	 */
	public void setRelType(String relType) {
		this.relType = relType;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}


	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAcctNoSect() {
		if(this.accountNo!=null&&accountNo.length()>8){
			acctNoSect = accountNo.substring(0,4)+"****"+accountNo.substring(accountNo.length()-4, accountNo.length());
		}
			
		return acctNoSect;
	}

	public void setAcctNoSect(String acctNoSect) {
		this.acctNoSect = acctNoSect;
	}	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}


}