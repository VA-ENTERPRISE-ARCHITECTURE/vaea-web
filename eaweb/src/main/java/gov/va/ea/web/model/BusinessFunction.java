package gov.va.ea.web.model;

public class BusinessFunction {
	private	String elementId;
	private	String bfId;
	private String hierarchy_number;
	private	String bfName;
	private String parentBf;
	private String org;
	private int level;
	private int numberOfSystems;
	

	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	public String getBfId() {
		return bfId;
	}
	public void setBfId(String bfId) {
		this.bfId = bfId;
	}
	public String getHierarchy_number() {
		return hierarchy_number;
	}
	public void setHierarchy_number(String hierarchy_number) {
		this.hierarchy_number = hierarchy_number;
	}
	public String getBfName() {
		return bfName;
	}
	public void setBfName(String bfName) {
		this.bfName = bfName;
	}
	public String getParentBf() {
		return parentBf;
	}
	public void setParentBf(String parentBf) {
		this.parentBf = parentBf;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getNumberOfSystems() {
		return numberOfSystems;
	}
	public void setNumberOfSystems(int numberOfSystems) {
		this.numberOfSystems = numberOfSystems;
	}
	
}
