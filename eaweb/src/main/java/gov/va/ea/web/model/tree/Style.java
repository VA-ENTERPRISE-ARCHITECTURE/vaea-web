package gov.va.ea.web.model.tree;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Style {
	
	private String stroke;
	private int opacity;
	private double strokeWidth;
	
	public String getStroke() {
		return stroke;
	}
	public void setStroke(String stroke) {
		this.stroke = stroke;
	}
	public int getOpacity() {
		return opacity;
	}
	public void setOpacity(int opacity) {
		this.opacity = opacity;
	}
	public double getStrokeWidth() {
		return strokeWidth;
	}
	@JsonSetter("stroke-width")
	public void setStrokeWidth(double strokeWidth) {
		this.strokeWidth = strokeWidth;
	}
}
