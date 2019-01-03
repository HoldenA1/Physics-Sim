package tech.hackerlife.sim.display.gui.graph;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import tech.hackerlife.sim.maths.Vector2D;

public class Graph {
	private final int CHAR_WIDTH = 15, CHAR_HEIGHT = 26;
	private int width, height, titleX, titleY;
	private int xBuffer = CHAR_WIDTH * 4; // Space for 3 chars and one of extra room
	private int yBuffer = CHAR_HEIGHT * 4;// Space for a char and for a line
	
	private float minX, maxX, maxY, minY;
	private int xScale, yScale; // Distance between lines
	private int xNumOfLines = 7, yNumOfLines = 6;
	int[] xLabels = {0,1,2,3,4,5,6};//new int[xNumOfLines];
	int[] yLabels = {0,1,2,3,4,5};//new int[yNumOfLines];
	
	private String title;
	private Font normalFont = new Font(Font.MONOSPACED, Font.PLAIN, 25);
	private ArrayList<Vector2D> dataPoints = new ArrayList<Vector2D>();
	
	public Graph(String xAxisLabel, String yAxisLabel, int width, int height) {
		title = yAxisLabel + " vs. " + xAxisLabel;
		titleY = CHAR_HEIGHT;
		titleX = width / 2 - (title.length() * CHAR_WIDTH) / 2;
		this.width = width - CHAR_WIDTH * 2; // so its not going through the side
		this.height = height;
		xScale = (int) ((this.width - xBuffer) / (xNumOfLines-1));
		yScale = (int) ((this.height - yBuffer - titleY) / (yNumOfLines-1));
	}
	
	public void addData(Vector2D dataPoint) {
		dataPoints.add(dataPoint);
		if (dataPoint.X() > maxX) {
			maxX = dataPoint.X();
		} else if (dataPoint.X() < minX) {
			minX = dataPoint.X();
		}
		
		if (dataPoint.Y() > maxY) {
			maxY = dataPoint.Y();
		} else if (dataPoint.Y() < minY) {
			minY = dataPoint.Y();
		}
	}
	
	public void draw(Graphics g) {
		// Draw Title
		g.setFont(normalFont);
		g.drawString(title, titleX, titleY);
		
		// Draw Axes
		g.drawLine(xBuffer, titleY, xBuffer, height - yBuffer); // Vertical
		g.drawLine(xBuffer, height - yBuffer, width, height - yBuffer); // Horizontal
		
		// Draw Scale
		for (int yLines = 0; yLines < yNumOfLines; yLines++) { // Vertical
			// Vertical is minus since up is positive
			g.drawLine(xBuffer - CHAR_WIDTH / 2, height - yBuffer - yLines * yScale, xBuffer + CHAR_WIDTH / 2, height - yBuffer - yLines * yScale);
			// Labels
			g.drawString(Integer.toString(yLabels[yLines]), xBuffer - CHAR_WIDTH, height - yBuffer - yLines * yScale);
		}
		for (int xLines = 0; xLines < xNumOfLines; xLines++) { // Horizontal
			g.drawLine(xBuffer + xLines * xScale, height - yBuffer - CHAR_HEIGHT / 2, xBuffer + xLines * xScale, height - yBuffer + CHAR_HEIGHT / 2);
			// Labels
			g.drawString(Integer.toString(xLabels[xLines]), xBuffer + xLines * xScale, height - yBuffer + CHAR_HEIGHT);
		}
		
		// Draw Line
	}

}