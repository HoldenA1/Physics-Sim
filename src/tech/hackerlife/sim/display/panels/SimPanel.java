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
	// GUI Objects
	GUIManager gui = new GUIManager();
	Button b1, b2;
	
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
		
		// GUI stuff
		b1 = new Button("Apply Normal Force", 900, 100).withColor(Color.GRAY);
		b2 = new Button("Remove Normal Force", 900, 100).withColor(Color.RED);
		gui.add(b1);
		gui.add(b2);
		b2.setVisibility(false);
		
		// Physics Stuff
		m1 = new Block(mass, new Vector2D(23,0), new Vector2D(0, 0), 1f, 1f);
		p = new Platform(new Vector2D(14,10), 20, 3f);
		obj.add(m1);
		obj.add(p);

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
		
		g.setColor(Color.BLUE);
		g.drawLine((int)(scale*(m1.getPosition().X()-1.5)), (int)(scale*(m1.getPosition().Y())), (int)(scale*(m1.getPosition().X()+1.5)), (int)(scale*(m1.getPosition().Y())));
		
	}
	
	public void update(float scale) {
		obj.update(scale);
	}

}