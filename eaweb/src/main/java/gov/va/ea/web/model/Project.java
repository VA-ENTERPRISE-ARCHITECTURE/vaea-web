package gov.va.ea.web.model;

import java.math.BigDecimal;

public class Project {

	private	BigDecimal projectId;
	private	String name;
	private String status;
	private	String EPSCode;
	private	String responsibleOrg;
	public BigDecimal getProjectId() {
		return projectId;
	}
	public void setProjectId(BigDecimal projectId) {
		this.projectId = projectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEPSCode() {
		return EPSCode;
	}
	public void setEPSCode(String ePSCode) {
		EPSCode = ePSCode;
	}
	public String getResponsibleOrg() {
		return responsibleOrg;
	}
	public void setResponsibleOrg(String responsibleOrg) {
		this.responsibleOrg = responsibleOrg;
	}
	
}
