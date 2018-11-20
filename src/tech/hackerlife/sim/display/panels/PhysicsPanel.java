package tech.hackerlife.sim.display.panels;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import tech.hackerlife.sim.display.gui.Mouse;
import tech.hackerlife.sim.maths.Vector2D;
import tech.hackerlife.sim.physics.environment.Platform;
import tech.hackerlife.sim.physics.matter.Block;

public class PhysicsPanel extends Panel {
	// Constants
	final float g = 9.8f;
	
	// Physics objects
	float mass = 1;//kg
	float gravity = mass * g;
	Block m1 = new Block(mass, new Vector2D(4,0), new Vector2D(0, 0), 2, 2).withColor(Color.BLUE);
	Platform pl = new Platform(new Vector2D(10, 20), 10, 1);

	public PhysicsPanel(Vector2D origin, int width, int height) {
		super(origin, width, height);
		m1.addForce(new Vector2D(0,gravity));
	}

	@Override
	public void draw(Graphics g, JPanel panel, Mouse mouse, float scale) {
		g.setColor(Color.GRAY);
		g.fillRect((int) origin.X(), (int) origin.Y(), width, panel.getHeight());
		pl.draw(g, scale);
		
		m1.drawForces(g, scale);
		m1.draw(g, scale);
	}
	
	public void update(JPanel panel) {
		m1.update(panel);
	}

}