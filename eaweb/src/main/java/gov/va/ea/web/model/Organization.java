package gov.va.ea.web.model;

public class Organization {

	private	String orgId;
	private	String elementId;
	private	String orgName;
	private	String acronym;
	private	String mailStop;
	private String parentOrg;
	private String rootParentOrg;
	
	private int numberOfSystems;
	
	
	
	public int getNumberOfSystems() {
		return numberOfSystems;
	}
	public void setNumberOfSystems(int numberOfSystems) {
		this.numberOfSystems = numberOfSystems;
	}
	public String getRootParentOrg() {
		return rootParentOrg;
	}
	public void setRootParentOrg(String rootParentOrg) {
		this.rootParentOrg = rootParentOrg;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	private int level;
	
	
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getAcronym() {
		return acronym;
	}
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	public String getMailStop() {
		return mailStop;
	}
	public void setMailStop(String mailStop) {
		this.mailStop = mailStop;
	}
	public String getParentOrg() {
		return parentOrg;
	}
	public void setParentOrg(String parentOrg) {
		this.parentOrg = parentOrg;
	}	
	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	
}
