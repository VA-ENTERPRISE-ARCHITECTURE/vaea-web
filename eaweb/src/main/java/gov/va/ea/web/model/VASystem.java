package gov.va.ea.web.model;

import java.math.BigDecimal;
import java.util.List;

public class VASystem {

	private	BigDecimal systemID;
	private	String name;
	private String type;
	private	String acronym;
	private	String parentOrganization;
	private	String sponsorOrganization;
	private	String status;
	private	String portfolio;
	private	String description;
	private	String oitOfficeOfResponsibility;
	private	String systemCriticality;
	private	String externalFacing;
	private String productType;
	
	private List<Stakeholder> stakeholders;
	private List<Project> projects;
	
	
	public BigDecimal getSystemID() {
		return systemID;
	}
	public void setSystemID(BigDecimal systemID) {
		this.systemID = systemID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAcronym() {
		return acronym;
	}
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	public String getSponsorOrganization() {
		return sponsorOrganization;
	}
	public void setSponsorOrganization(String sponsorOrganization) {
		this.sponsorOrganization = sponsorOrganization;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOitOfficeOfResponsibility() {
		return oitOfficeOfResponsibility;
	}
	public void setOitOfficeOfResponsibility(String oitOfficeOfResponsibility) {
		this.oitOfficeOfResponsibility = oitOfficeOfResponsibility;
	}
	public String getSystemCriticality() {
		return systemCriticality;
	}
	public void setSystemCriticality(String systemCriticality) {
		this.systemCriticality = systemCriticality;
	}
	public String getExternalFacing() {
		return externalFacing;
	}
	public void setExternalFacing(String externalFacing) {
		this.externalFacing = externalFacing;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getParentOrganization() {
		return parentOrganization;
	}
	public void setParentOrganization(String parentOrganization) {
		this.parentOrganization = parentOrganization;
	}
	public List<Stakeholder> getStakeholders() {
		return stakeholders;
	}
	public void setStakeholders(List<Stakeholder> stakeholders) {
		this.stakeholders = stakeholders;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}	
	
}
