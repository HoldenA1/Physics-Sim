package tech.hackerlife.sim;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tech.hackerlife.sim.display.*;
import tech.hackerlife.sim.display.gui.Mouse;
import tech.hackerlife.sim.display.panels.ControlPanel;
import tech.hackerlife.sim.display.panels.PhysicsPanel;
import tech.hackerlife.sim.maths.Vector2D;

public class Main extends JPanel {
	
	// Frame variables
	final static String NAME = "Physics Simulation";
	final static int WIDTH = 1280, HEIGHT = 720;
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
	
	// Panels
	PhysicsPanel physics = new PhysicsPanel(new Vector2D(0,0), 960, 720);
	ControlPanel control = new ControlPanel(new Vector2D(960,0), 320, 720);
	
	Mouse mouse = new Mouse();
	
	public Main() {
		this.addMouseListener(mouse);
	}
	
	public static void main(String[] args) {
		Window frame = new Window(NAME, WIDTH, HEIGHT);	
		frame.add(new Main());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.LIGHT_GRAY);
		
		physics.draw(g, this, mouse, SCALE);
		control.draw(g, this, mouse, SCALE);
		
		// This is the update loop
		long now = System.nanoTime();
		delta += (now - lastTime) / ns;
		lastTime = now;
		while (delta >= 1) {
			
			physics.update(this);

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