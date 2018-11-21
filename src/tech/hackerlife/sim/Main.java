package tech.hackerlife.sim;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tech.hackerlife.sim.display.*;
import tech.hackerlife.sim.display.gui.Mouse;
import tech.hackerlife.sim.display.panels.SimPanel;

public class Main extends JPanel {
	
	// Frame variables
	final static String NAME = "Physics Simulation";
	final static int WIDTH = 1280, HEIGHT = 720;
	private static final long serialVersionUID = 1L;
	public final static float SCALE = 20; // Pixels per meter
	
	// Update loop variables
	long lastTime = System.nanoTime();
	long timer = System.currentTimeMillis();
	final double ns = 1000000000.0 / ups;
	double delta = 0;
	int updates = 0;
	
	// Game speed - 50.0 is real-time
	public static final float realTimeUPS = 50.0f;
	static double ups = 50.0;
	
	// Panels
	SimPanel panel = new SimPanel(WIDTH, HEIGHT-40);
	
	Mouse mouse;
	
	public Main() {
		// Adds mouse listener to the JPanel
		mouse = new Mouse();
		this.addMouseListener(mouse);
	}
	
	public static void main(String[] args) {
		Window frame = new Window(NAME, WIDTH, HEIGHT);	
		frame.add(new Main());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.LIGHT_GRAY);
		
		panel.draw(g, this, mouse, SCALE);
		
		update();
		
		repaint();
	}
	
	private void update() {
		// This is the update loop
		long now = System.nanoTime();
		delta += (now - lastTime) / ns;
		lastTime = now;
		while (delta >= 1) {
			
			// Call your updates here
			panel.update(SCALE);

			updates++;
			delta--;
		}
		
		// prints the updates per second
		if (System.currentTimeMillis() - timer > 1000) {
			timer += 1000;
			System.out.println("ups: " + updates);
			updates = 0;
		}
	}

}