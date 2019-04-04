package code.person.pojo.cusservice;

import java.io.Serializable;



public class OnlineService implements Serializable{
	private String openId;
	private int updTime;
	private String ajid;
	private String serStatus;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public int getUpdTime() {
		return updTime;
	}
	public void setUpdTime(int updTime) {
		this.updTime = updTime;
	}
	public String getAjid() {
		return ajid;
	}
	public void setAjid(String ajid) {
		this.ajid = ajid;
	}
	public String getSerStatus() {
		return serStatus;
	}
	public void setSerStatus(String serStatus) {
		this.serStatus = serStatus;
	}
	
	
}