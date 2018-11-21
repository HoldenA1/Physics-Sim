package tech.hackerlife.sim.display.panels;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tech.hackerlife.sim.display.gui.GUIManager;
import tech.hackerlife.sim.display.gui.Mouse;
import tech.hackerlife.sim.display.gui.button.Button;
import tech.hackerlife.sim.maths.Vector2D;
import tech.hackerlife.sim.physics.environment.Platform;
import tech.hackerlife.sim.physics.matter.Block;

public class SimPanel extends Panel {
	// GUI Objects
	GUIManager manager = new GUIManager();
	Button button;
	
	// Constants
	final float g = 9.8f;//N/kg
	
	// Physics objects
	float mass = 1;//kg
	float gravity = mass * g;
	Block m1 = new Block(mass, new Vector2D(4,0), new Vector2D(0, 0), 1f, 1f).withColor(Color.BLUE);
	Platform pl = new Platform(new Vector2D(10, 20), 10, 1);

	public SimPanel (int width, int height) {
		super(width, height);
		
		button = new Button("Apply Normal Force", 900, 100).withColor(Color.GRAY);
		manager.add(button);
		
		Vector2D forceofG = new Vector2D(0,gravity);
		m1.addForce(forceofG);
	}

	public void draw(Graphics g, JPanel panel, Mouse mouse, float scale) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
		
		// GUI things
		manager.updateElements(g, panel, mouse);
		
		// Physics things
		pl.draw(g, scale);
		m1.drawForces(g, scale);
		m1.draw(g, scale);
	}
	
	public void update(float scale) {
		m1.update(this, scale);
	}

}