package code.person.pojo;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userNo;
	private String password;
	private String userName;
	private String sex;
	private String city;
	private String country;
	private String province;
	private String subscribeTime;
	private String userPhone;
	
	
	
	public User() {
	}

	

	public User(String userNo, String password, String userName, String sex,
			String city, String country, String province, String subscribeTime,
			String userPhone) {
		this.userNo = userNo;
		this.password = password;
		this.userName = userName;
		this.sex = sex;
		this.city = city;
		this.country = country;
		this.province = province;
		this.subscribeTime = subscribeTime;
		this.userPhone = userPhone;
	}



	public String getUserNo() {
		return userNo;
	}



	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getProvince() {
		return province;
	}



	public void setProvince(String province) {
		this.province = province;
	}



	public String getSubscribeTime() {
		return subscribeTime;
	}



	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}



	public String getUserPhone() {
		return userPhone;
	}



	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	
	
	
}
