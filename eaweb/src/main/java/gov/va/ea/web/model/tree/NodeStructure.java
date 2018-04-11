package gov.va.ea.web.model.tree;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;

public class NodeStructure {	

	private Text text;
	private String image;
	private String HTMLid;
	private String HTMLclass;
	private boolean collapsed;
	private boolean stackChildren;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<NodeStructure> children;
	
	@JsonIgnore
	private String parentId;
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getHTMLid() {
		return HTMLid;
	}
	@JsonSetter("HTMLid")
	public void setHTMLid(String hTMLid) {
		HTMLid = hTMLid;
	}

	
	public List<NodeStructure> getChildren() {
		return children;
	}
	public void setChildren(List<NodeStructure> children) {
		this.children = children;
	}
	public boolean isStackChildren() {
		return stackChildren;
	}
	public void setStackChildren(boolean stackChildren) {
		this.stackChildren = stackChildren;
	}
	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getHTMLclass() {
		return HTMLclass;
	}
	@JsonSetter("HTMLclass")
	public void setHTMLclass(String hTMLclass) {
		HTMLclass = hTMLclass;
	}
	public boolean isCollapsed() {
		return collapsed;
	}
	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}
	
}
