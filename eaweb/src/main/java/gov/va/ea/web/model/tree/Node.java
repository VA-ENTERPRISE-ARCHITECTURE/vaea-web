package gov.va.ea.web.model.tree;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Node {
	
	private boolean collapsable;
	private String HTMLclass;
	private boolean stackChildren;
	
	public boolean isCollapsable() {
		return collapsable;
	}
	public void setCollapsable(boolean collapsable) {
		this.collapsable = collapsable;
	}
	public String getHTMLclass() {
		return HTMLclass;
	}
	@JsonSetter("HTMLclass")
	public void setHTMLclass(String hTMLclass) {
		HTMLclass = hTMLclass;
	}
	public boolean isStackChildren() {
		return stackChildren;
	}
	public void setStackChildren(boolean stackChildren) {
		this.stackChildren = stackChildren;
	}
	
}
