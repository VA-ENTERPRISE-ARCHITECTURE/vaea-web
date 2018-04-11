package gov.va.ea.web.model.tree;

import com.fasterxml.jackson.annotation.JsonSetter;

public class ChartConfig {
	
	private Chart chart;
	private NodeStructure nodeStructure;
	
	
	public Chart getChart() {
		return chart;
	}
	public void setChart(Chart chart) {
		this.chart = chart;
	}
	public NodeStructure getNodeStructure() {
		return nodeStructure;
	}
	@JsonSetter("nodeStructure")
	public void setNodeStructure(NodeStructure nodeStructure) {
		this.nodeStructure = nodeStructure;
	}
}
