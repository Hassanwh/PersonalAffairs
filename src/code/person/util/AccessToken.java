package code.person.util;

/**
 * ΢��ͨ�ýӿ�ƾ֤
 * 
 */
//��������
public class AccessToken {
	// ��ȡ����ƾ֤
	private String id;
	
	private String token;
	
	private String ticket;
	
	private int createTime;
	// ƾ֤��Чʱ�䣬��λ����
	private int expiresIn;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}
	
}