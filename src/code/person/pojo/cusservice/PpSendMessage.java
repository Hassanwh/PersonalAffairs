package code.person.pojo.cusservice;

import java.io.Serializable;


public class PpSendMessage implements Serializable{
	private String msgId;
	private String cjid;
	private String msgType;
	private String msgClass;
	private String msgContent;
	private String msgurl;
	private String createTime;
	private String flag;
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getCjid() {
		return cjid;
	}
	public void setCjid(String cjid) {
		this.cjid = cjid;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgClass() {
		return msgClass;
	}
	public void setMsgClass(String msgClass) {
		this.msgClass = msgClass;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getMsgurl() {
		return msgurl;
	}
	public void setMsgurl(String msgurl) {
		this.msgurl = msgurl;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}