package gov.va.ea.web.model.tree;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Text {

	private String title;
	private String name;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String numberOfSystems;
	public String getNumberOfSystems() {
		return numberOfSystems;
	}
	@JsonSetter("data-ns")
	public void setNumberOfSystems(String numberOfSystems) {
		this.numberOfSystems = numberOfSystems;
	}
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Contact contact;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
