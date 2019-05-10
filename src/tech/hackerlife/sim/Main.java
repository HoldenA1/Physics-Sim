package tech.hackerlife.sim;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tech.hackerlife.gui.*;
import tech.hackerlife.sim.display.panels.PanelManager;

public class Main extends JPanel {
	// Frame variables
	private final static String NAME = "Physics Simulation";
	private final static int WIDTH = 1280, HEIGHT = 720;
	private static final long serialVersionUID = 1L;
	public final static float SCALE = 0.5f; // Pixels per meter
	
	// Update loop variables
	private long lastTime = System.nanoTime();
	private long timer = System.currentTimeMillis();
	private double ns = 1000000000.0 / ups;
	private double delta = 0;
	private int updates = 0;
	
	// When ups is 50, things move in real-time
	public static final float realTimeUPS = 50.0f;
	public static double ups = 0.0;
	
	// Panels
	PanelManager pm = new PanelManager(WIDTH, HEIGHT);
	
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
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.LIGHT_GRAY);
		
		pm.draw(g, this, mouse, SCALE);
		
		update();
		
		repaint();
	}
	
	private void update() {
		// This is the update loop
		ns = 1000000000.0 / ups;
		long now = System.nanoTime();
		delta += (now - lastTime) / ns;
		lastTime = now;
		while (delta >= 1) {
			
			// Call your updates here
			pm.update(SCALE);

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