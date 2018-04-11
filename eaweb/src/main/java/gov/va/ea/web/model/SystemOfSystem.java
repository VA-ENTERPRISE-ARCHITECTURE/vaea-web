package gov.va.ea.web.model;

public class SystemOfSystem {

	private String childId;
	private String childName;
	private String childAcronym;
	
	private String parentId;
	private String parentName;
	private String parentAcronym;
	public String getChildId() {
		return childId;
	}
	public void setChildId(String childId) {
		this.childId = childId;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getChildAcronym() {
		return childAcronym;
	}
	public void setChildAcronym(String childAcronym) {
		this.childAcronym = childAcronym;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getParentAcronym() {
		return parentAcronym;
	}
	public void setParentAcronym(String parentAcronym) {
		this.parentAcronym = parentAcronym;
	}
	
}
