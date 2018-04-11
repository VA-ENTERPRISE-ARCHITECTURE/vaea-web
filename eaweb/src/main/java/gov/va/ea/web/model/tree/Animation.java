package gov.va.ea.web.model.tree;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Animation {
	
	private String nodeAnimation;
	private int nodeSpeed;
    private String connectorsAnimation;
    private int connectorsSpeed;
    
    public String getNodeAnimation() {
		return nodeAnimation;
	}
    @JsonSetter("nodeAnimation")
	public void setNodeAnimation(String nodeAnimation) {
		this.nodeAnimation = nodeAnimation;
	}
	public int getNodeSpeed() {
		return nodeSpeed;
	}
	@JsonSetter("nodeSpeed")
	public void setNodeSpeed(int nodeSpeed) {
		this.nodeSpeed = nodeSpeed;
	}
	public String getConnectorsAnimation() {
		return connectorsAnimation;
	}
	@JsonSetter("connectorsAnimation")
	public void setConnectorsAnimation(String connectorsAnimation) {
		this.connectorsAnimation = connectorsAnimation;
	}
	public int getConnectorsSpeed() {
		return connectorsSpeed;
	}
	@JsonSetter("connectorsSpeed")
	public void setConnectorsSpeed(int connectorsSpeed) {
		this.connectorsSpeed = connectorsSpeed;
	}
}
