package tech.hackerlife.sim;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tech.hackerlife.sim.display.*;
import tech.hackerlife.sim.gui.*;
import tech.hackerlife.sim.gui.button.Button;
import tech.hackerlife.sim.maths.Vector2D;
import tech.hackerlife.sim.physics.Block;

public class Main extends JPanel {
	
	// Frame variables
	final static String NAME = "Physics Simulation";
	private static final long serialVersionUID = 1L;
	public final static float SCALE = 10; // Pixels per meter
	
	// Update loop variables
	long lastTime = System.nanoTime();
	long timer = System.currentTimeMillis();
	final double ns = 1000000000.0 / ups;
	double delta = 0;
	int updates = 0;
	
	// Game speed - 50.0 is real-time
	public static final float realTimeUPS = 50.0f;
	static double ups = 50.0;
	
	// Constants
	final float g = 9.8f;
	
	// GUI Objects
	GUIManager b = new GUIManager();
	Mouse  m = new Mouse();
	Button button = new Button("button", 100, 100);
	
	// Physics objects
	float mass = 10;//kg
	Block m1 = new Block(10.0f, new Vector2D(4,30), new Vector2D(7, -20), 2, 2);
	
	public Main() {
		m1.addForce(new Vector2D(0,g*mass));
		b.add(button);
	}
	
	public static void main(String[] args) {
		Window frame = new Window(NAME);	
		frame.add(new Main());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.LIGHT_GRAY);
		
		m1.draw(g, SCALE);
		
		b.updateElements(g, this, m);
		
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