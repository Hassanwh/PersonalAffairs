package code.person.pojo.cusservice;

import java.io.Serializable;


public class ResultCode implements Serializable{
	private String code;
	private String msg;
	
	public ResultCode(String code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}