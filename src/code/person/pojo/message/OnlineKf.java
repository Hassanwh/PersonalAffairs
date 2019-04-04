package code.person.pojo.message;

/**
 * 
 * 
 */
public class OnlineKf {
	
	private String kf_account;
	private int status;
	private String kf_id;
	private int auto_accept;
	private int accepted_case;
	public String getKf_account() {
		return kf_account;
	}
	public void setKf_account(String kfAccount) {
		kf_account = kfAccount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getKf_id() {
		return kf_id;
	}
	public void setKf_id(String kfId) {
		kf_id = kfId;
	}
	public int getAuto_accept() {
		return auto_accept;
	}
	public void setAuto_accept(int autoAccept) {
		auto_accept = autoAccept;
	}
	public int getAccepted_case() {
		return accepted_case;
	}
	public void setAccepted_case(int acceptedCase) {
		accepted_case = acceptedCase;
	}


}
