package tech.hackerlife.sim.display.panels;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tech.hackerlife.gui.Mouse;
import tech.hackerlife.math.Vector2f;
import tech.hackerlife.sim.physics.matter.Ball;

public class CannonTrajectorySim extends SimPanel {
	
	private Ball b;
	private float mass = 0.06f;
	private float k = 0.045f;
	private int timer = 0;

	public CannonTrajectorySim(int width, int height) {
		super(width, height);
		reset();
	}
	
	protected void reset() {
		objectManager.reset();
		
		timer = 0;
		
//		b = new Ball(mass, 1, new Vector2f(0, -0.7f), new Vector2f(101.9f, 0)).withColor(Color.BLACK); // horizontal
		b = new Ball(mass, 1, new Vector2f(0, -0.7f), new Vector2f(7*Math.PI/4, 101.9f)).withColor(Color.BLACK); // 45 degrees
		b.addConstantForce(new Vector2f(0, mass*9.8f));
		objectManager.add(b);
	}
	
	@Override
	public void draw(Graphics g, JPanel panel, Mouse mouse, float scale) {
		super.draw(g, panel, mouse, scale);
		b.drawForces(g, 10);
		b.getVelocity().drawVector(g, b.getPosition(), scale, Color.BLUE);
	}
	
	@Override
	public void update(float scale) {
		super.update(scale);
		timer++;
		if (b.getPosition().Y() >= 0) {
			System.out.println(b.getPosition().X() + "m | " + timer/50.0 + "s");
			pause();
		}
		b.addForce(new Vector2f(b.getVelocity().mult(-1).dir(), k * b.getVelocity().mag()));
	}

}