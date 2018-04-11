package gov.va.ea.web.model.tree;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Chart {
	
	private String container;
	private String rootOrientation;
	private String nodeAlign;
	private Connector connectors;
	private boolean animateOnInit;
	private Node node;
	private Animation animation;
	private String scrollbar;
	
	public boolean isAnimateOnInit() {
		return animateOnInit;
	}
	@JsonSetter("animateOnInit")
	public void setAnimateOnInit(boolean animateOnInit) {
		this.animateOnInit = animateOnInit;
	}
	public String getContainer() {
		return container;
	}
	public void setContainer(String container) {
		this.container = container;
	}
	public String getRootOrientation() {
		return rootOrientation;
	}
	@JsonSetter("rootOrientation")
	public void setRootOrientation(String rootOrientation) {
		this.rootOrientation = rootOrientation;
	}
	public String getNodeAlign() {
		return nodeAlign;
	}
	@JsonSetter("nodeAlign")
	public void setNodeAlign(String nodeAlign) {
		this.nodeAlign = nodeAlign;
	}
	public Connector getConnectors() {
		return connectors;
	}
	@JsonSetter("connectors")
	public void setConnectors(Connector connectors) {
		this.connectors = connectors;
	}
	public Node getNode() {
		return node;
	}
	public void setNode(Node node) {
		this.node = node;
	}
	public Animation getAnimation() {
		return animation;
	}
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
	public String getScrollbar() {
		return scrollbar;
	}
	public void setScrollbar(String scrollbar) {
		this.scrollbar = scrollbar;
	}
	
}
