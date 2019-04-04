package code.person.service.menu.impl;

public class OtherButton extends ChildButton{
	private String key;
	private String[] sub_button;
	public String[] getSub_button() {
		return sub_button;
	}
	public void setSub_button(String[] sub_button) {
		this.sub_button = sub_button;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}