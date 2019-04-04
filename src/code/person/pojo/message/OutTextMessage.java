package code.person.pojo.message;


/**
 * 文本消息
 * 
 */
public class OutTextMessage extends OutMessage {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
