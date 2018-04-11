package gov.va.ea.web.utils;

import gov.va.ea.web.model.tree.Animation;
import gov.va.ea.web.model.tree.Chart;
import gov.va.ea.web.model.tree.Connector;
import gov.va.ea.web.model.tree.Node;
import gov.va.ea.web.model.tree.Style;

public final class WebUtils {
	
	private WebUtils() {
	}
	
	public static Chart getWebConfig(String layout) {
		
		if(layout == null || layout.isEmpty()) {
			layout = "NORTH";
		}
		
		Chart chart = new Chart();
		chart.setContainer("#custom-colored");
		chart.setRootOrientation(layout);
		chart.setNodeAlign("BOTTOM");
		chart.setAnimateOnInit(true);
		chart.setScrollbar("None");
		
		Animation animation = new Animation();
		animation.setNodeAnimation("easeOutBounce");
		animation.setNodeSpeed(700);
		animation.setConnectorsSpeed(700);
		animation.setConnectorsAnimation("bounce");
		chart.setAnimation(animation);
		
		Node node = new Node();
		node.setCollapsable(true);
		node.setHTMLclass("brmNode");
		if(layout.equalsIgnoreCase("NORTH")) {
			node.setStackChildren(true);
		}
		chart.setNode(node);	
		
		Connector connectors = new Connector();
		connectors.setType("step");
		Style style = new Style();
		style.setStroke("grey");
		style.setOpacity(1);
		style.setStrokeWidth(0.5);	
		connectors.setStyle(style);
		
		chart.setConnectors(connectors);
		
		return chart;

	}

}
