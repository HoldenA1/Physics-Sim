package tech.hackerlife.sim;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tech.hackerlife.sim.display.*;
import tech.hackerlife.sim.maths.Vector2D;
import tech.hackerlife.sim.physics.Block;

public class Main extends JPanel {
	
	// Frame variables
	final static String NAME = "Physics Simulation";
	private static final long serialVersionUID = 1L;
	public final static double SCALE = 5; // Pixels per meter
	
	// Update loop variables
	long lastTime = System.nanoTime();
	long timer = System.currentTimeMillis();
	final double ns = 1000000000.0 / ups;
	double delta = 0;
	int updates = 0;
	// Updates a second - 50.0 is real-time
	public static final float realTimeUPS = 50.0f;
	static double ups = 50.0;
	
	// Constants
	final float g = 9.8f;
	
	Block m1 = new Block(10.0f, new Vector2D(4,3), new Vector2D(10,0), null, 2, 2);
	
	public Main() {
		m1.addForce(new Vector2D(0,g));
	}
	
	public static void main(String[] args) {
		Window frame = new Window(NAME);	
		frame.add(new Main());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.LIGHT_GRAY);
		
		m1.draw(g);
		
		// This is the update loop
		long now = System.nanoTime();
		delta += (now - lastTime) / ns;
		lastTime = now;
		while (delta >= 1) {
			
			m1.update();

			updates++;
			delta--;
		}
		
		// prints the updates per second
		if (System.currentTimeMillis() - timer > 1000) {
			timer += 1000;
			System.out.println("ups: " + updates);
			updates = 0;
		}
		
		repaint();
	}

}