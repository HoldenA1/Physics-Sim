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
import tech.hackerlife.sim.physics.matter.Block;

public class SimPanel extends Panel {
	// GUI Objects
	GUIManager gui = new GUIManager();
	Button openGraphingToolButton, playButton, pauseButton;
	ArrayList<GraphingTool> graphingTools = new ArrayList<GraphingTool>();
	
	// Constants
	final float g = 9.8f;//N/kg
	
	// Physics objects
	float mass = 1;//kg
	float gravity = mass * g;
	Block m1;

	public SimPanel (int width, int height) {
		super(width, height);
		
		// GUI stuff
		openGraphingToolButton = new Button("Open Graphing Tool", 900, 200).withColor(Color.GRAY);
		gui.add(openGraphingToolButton);
		playButton = new Button("Play", 900, 100, 200, 50).withColor(Color.GREEN);
		gui.add(playButton);
		pauseButton = new Button("Pause", 900, 100, 200, 50).withColor(Color.RED);
		pauseButton.setVisibility(false);
		gui.add(pauseButton);
		
		// Physics Stuff
		objectManager = new ObjectManager();
		m1 = new Block(mass, new Vector2f(0,0), new Vector2f(1, 0), 1f, 3f).withColor(Color.BLUE).withName("Rocket");
		objectManager.add(m1);

		// Add gravity
		objectManager.addGravity();
	}
	
	public void draw(Graphics g, JPanel panel, Mouse mouse, float scale) {
		// Setup background
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
		
		if (playButton.isPressed()) {
			Main.ups = 50.0;
			playButton.setVisibility(false);
			pauseButton.setVisibility(true);
		} 
		if (pauseButton.isPressed()) {
			Main.ups = 0.0;
			playButton.setVisibility(true);
			pauseButton.setVisibility(false);
		}
		
		// GUI things
		gui.updateElements(g, panel, mouse);
		if (openGraphingToolButton.isPressed()) {
			graphingTools.add(new GraphingTool(objectManager));
		}
		
		// Physics things
		objectManager.draw(g, scale);
		m1.getVelocity().drawVector(g, m1.getPosition(), scale, Color.GREEN);
	}
	
	public void update(float scale) {
		objectManager.update(scale);
		
		for (GraphingTool g: graphingTools) {
			g.update();
		}
	}

}