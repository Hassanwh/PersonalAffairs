package code.person.pojo.message;


/**
 * 多客服
 * 
 */
public class OutServiceMessage extends OutMessage {
	// 指定客服人员
	private TransInfo TransInfo;

	public TransInfo getTransInfo() {
		return TransInfo;
	}

	public void setTransInfo(TransInfo TransInfo) {
		this.TransInfo = TransInfo;
	}

}
