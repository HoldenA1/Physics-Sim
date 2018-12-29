package tech.hackerlife.sim.display.panels;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tech.hackerlife.sim.display.gui.GUIManager;
import tech.hackerlife.sim.display.gui.Mouse;
import tech.hackerlife.sim.display.gui.button.Button;
import tech.hackerlife.sim.maths.Vector2D;
import tech.hackerlife.sim.physics.ObjectManager;
import tech.hackerlife.sim.physics.environment.Platform;
import tech.hackerlife.sim.physics.matter.Block;

public class SimPanel extends Panel {
	// Scale
	Vector2D xAxis;
	Vector2D yAxis;
	Vector2D origin;
	
	// GUI Objects
	GUIManager gui = new GUIManager();
	Button b1;
	
	// Constants
	final float g = 9.8f;//N/kg
	
	// Physics objects
	ObjectManager obj = new ObjectManager();
	float mass = 1;//kg
	float gravity = mass * g;
	Block m1;
	Platform p;

	public SimPanel (int width, int height) {
		super(width, height);
		
		// Initialize scale with unit vectors of 2 meters
		xAxis = new Vector2D(2, 0);
		yAxis = new Vector2D(0, 2);
		origin = new Vector2D(0.5f, 0.5f); // Not at (0, 0) for better visibility
		
		// GUI stuff
		b1 = new Button("Apply Normal Force", 900, 100).withColor(Color.GRAY);
		gui.add(b1);
		
		// Physics Stuff
		m1 = new Block(mass, new Vector2D(30,6), new Vector2D(2, 0), 1f, 3f).withColor(Color.BLUE);
		p = new Platform(new Vector2D(50, 40), 40, 1f);
		obj.add(m1);
		obj.add(p);

		// Add gravity
		obj.addGravity();
	}
	
	public void draw(Graphics g, JPanel panel, Mouse mouse, float scale) {
		// Setup background
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
		
		// Draw scale
		xAxis.drawVector(g, origin, scale, Color.BLACK);
		yAxis.drawVector(g, origin, scale, Color.BLACK);
		
		// GUI things
		gui.updateElements(g, panel, mouse);
		
		// Physics things
		obj.draw(g, scale);
		m1.getVelocity().drawVector(g, m1.getPosition(), scale, Color.GREEN);
	}
	
	public void update(float scale) {
		obj.update(scale);
	}

}