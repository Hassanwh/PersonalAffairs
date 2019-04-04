package code.person.pojo.customer;

import java.io.Serializable;


/**
 * Description:
 * <br/>Copyright (C), 2001-2011, 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:	2014-10-20
 * @author  ldh
 * @version  2.0
 */
public class CusInfo implements Serializable
{

	// Fields
	private String openid;
	private String nickname;
	private String sex;
	private String city;
	private String country;
	private String province;
	private String subscribe_time;
	private String inCusId;
	private String inCusName;
	private String idType;
	private String idNo;
	private String[] tagid_list;
	private String language;
	private String remark;
	private String groupid;
	private String unionid;
	private String headimgurl;
	private String subscribe;
	
	

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	// Constructors
	public CusInfo() {
	}	


	public String getSex() {
		return this.sex;
	}

	/*
	 * @param String sex (中文含意：性别;　数据存储类型：CHAR(1))
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCity() {
		return this.city;
	}

	/*
	 * @param String city (中文含意：城市;　数据存储类型：VARCHAR(30))
	 */
	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	/*
	 * @param String country (中文含意：国家;　数据存储类型：VARCHAR(30))
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return this.province;
	}

	/*
	 * @param String province (中文含意：省;　数据存储类型：VARCHAR(30))
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	public String getInCusName() {
		return inCusName;
	}

	public void setInCusName(String inCusName) {
		this.inCusName = inCusName;
	}

	public String getInCusId() {
		return inCusId;
	}

	public void setInCusId(String inCusId) {
		this.inCusId = inCusId;
	}

	public String[] getTagid_list() {
		return tagid_list;
	}

	public void setTagid_list(String[] tagid_list) {
		this.tagid_list = tagid_list;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	
	
}