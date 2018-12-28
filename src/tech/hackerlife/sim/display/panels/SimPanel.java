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
import tech.hackerlife.sim.physics.matter.Ball;
import tech.hackerlife.sim.physics.matter.Block;

public class SimPanel extends Panel {
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
	Ball m2;
	Platform p;

	public SimPanel (int width, int height) {
		super(width, height);
		
		// GUI stuff
		b1 = new Button("Apply Normal Force", 900, 100).withColor(Color.GRAY);
		gui.add(b1);
		
		// Physics Stuff
		m1 = new Block(mass, new Vector2D(12,0), new Vector2D(2, 0), 1f, 1f).withColor(Color.BLUE);
		m2 = new Ball(mass, new Vector2D(19,0), new Vector2D(-3, 0), 1f);
		p = new Platform(new Vector2D(14,10), 20, 2f);
		obj.add(m1);
		obj.add(m2);
		obj.add(p);
		
		m2.setElastic(false);

		// Add gravity
		obj.addGravity();
	}
	
	public void draw(Graphics g, JPanel panel, Mouse mouse, float scale) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
		
		// GUI things
		gui.updateElements(g, panel, mouse);
		
		// Physics things
		obj.draw(g, scale);
		m1.getVelocity().drawVector(g, m1.getPosition(), scale, Color.GREEN);
		m2.getVelocity().drawVector(g, m2.getPosition(), scale, Color.GREEN);
	}
	
	public void update(float scale) {
		obj.update(scale);
	}

}