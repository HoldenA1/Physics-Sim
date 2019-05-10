package tech.hackerlife.sim.display.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import tech.hackerlife.sim.Main;
import tech.hackerlife.sim.display.GraphingTool;
import tech.hackerlife.gui.*;
import tech.hackerlife.math.Vector2f;
import tech.hackerlife.sim.physics.ObjectManager;

public abstract class SimPanel extends Panel {
	// GUI Objects
	protected GUIManager gui = new GUIManager();
	protected Button openGraphingToolButton, playButton, pauseButton, resetButton;
	ArrayList<GraphingTool> graphingTools = new ArrayList<GraphingTool>();
	
	private final int MENU_LOCATION = 900;
	private Vector2f centerpoint;

	public SimPanel (int width, int height) {
		super(width, height);
		
		// GUI stuff
		openGraphingToolButton = new Button("Open Graphing Tool", MENU_LOCATION, 250);
		playButton = new Button("Play", MENU_LOCATION, 100, 200, 50).withColor(Color.GREEN);
		pauseButton = new Button("Pause", MENU_LOCATION, 100, 200, 50).withColor(Color.RED);
		resetButton = new Button("Reset", MENU_LOCATION, 175, 200, 50).withColor(Color.GREEN);
		
		gui.add(openGraphingToolButton);
		gui.add(playButton);
		pauseButton.setVisibility(false);
		gui.add(pauseButton);
		gui.add(resetButton);
		
		centerpoint = new Vector2f(MENU_LOCATION / 2, height / 2);
		
		// Physics Stuff
		objectManager = new ObjectManager();
	}
	
	public void draw(Graphics g, JPanel panel, Mouse mouse, float scale) {
		// Draw Menu
		g.create(0, 0, MENU_LOCATION, height);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
		
		gui.updateElements(g, panel, mouse);
		
		if (playButton.isPressed()) play(); 
		else if (pauseButton.isPressed()) pause();
		if (resetButton.isPressed()) {
			pause();
			reset();
		}
		if (openGraphingToolButton.isPressed()) {
			graphingTools.add(new GraphingTool(objectManager));
		}
		
		g.drawLine(MENU_LOCATION-50, 0, MENU_LOCATION-50, height+100);
		
		// Draw Objects
		g.translate((int)centerpoint.X(), (int)centerpoint.Y());
		objectManager.draw(g, scale);
		
	}
	
	/**
	 * Initialize all objects used in the sim here
	 */
	protected void reset() {
//		graphingTools.forEach();
	}
	
	private void pause() {
		Main.ups = 0.0;
		playButton.setVisibility(true);
		pauseButton.setVisibility(false);
	}
	
	private void play() {
		Main.ups = 50.0;
		playButton.setVisibility(false);
		pauseButton.setVisibility(true);
	}
	
	public void update(float scale) {
		objectManager.update(scale);
		
		for (GraphingTool g: graphingTools) {
			g.update();
		}
	}

}