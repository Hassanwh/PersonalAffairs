package code.person.service.menu.impl;

public class ParentButton extends Button{
	private ChildButton[] sub_button;

	public ChildButton[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(ChildButton[] sub_button) {
		this.sub_button = sub_button;
	}
	
}