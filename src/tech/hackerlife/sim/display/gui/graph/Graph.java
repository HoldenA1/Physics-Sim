package tech.hackerlife.sim.display.gui.graph;

import java.awt.Graphics;
import java.util.ArrayList;
import tech.hackerlife.sim.maths.Vector2D;

public class Graph {
	private int width, height;
	private float xScale, yScale;
	private String xAxisLabel, yAxisLabel;
	private ArrayList<Vector2D> dataPoints = new ArrayList<Vector2D>();
	
	public Graph(String xAxisLabel, String yAxisLabel, int width, int height) {
		this.xAxisLabel = xAxisLabel;
		this.yAxisLabel = yAxisLabel;
		this.width = width;
		this.height = height;
	}
	
	public void addData(Vector2D dataPoint) {
		dataPoints.add(dataPoint);
	}
	
	public void draw(Graphics g) {
		
	}

}