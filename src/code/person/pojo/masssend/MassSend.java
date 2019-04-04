package code.person.pojo.masssend;

import java.util.HashMap;


public class MassSend{
	
	private String[] touser;
	private HashMap mpnews;
	private String msgtype;
	public String[] getTouser() {
		return touser;
	}
	public void setTouser(String[] touser) {
		this.touser = touser;
	}
	public HashMap getMpnews() {
		return mpnews;
	}
	public void setMpnews(HashMap mpnews) {
		this.mpnews = mpnews;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public void setMpnews(String mediaId){
		this.mpnews = new HashMap();
		mpnews.put("media_id", mediaId);
	}
	public void setTouser(String openId){
		this.touser = new String[1];
		touser[0] = openId;
	}
}