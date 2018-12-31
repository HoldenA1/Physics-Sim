package tech.hackerlife.sim.display.panels;

import java.awt.Graphics;
import javax.swing.JPanel;
import tech.hackerlife.sim.display.gui.Mouse;
import tech.hackerlife.sim.physics.ObjectManager;

public abstract class Panel {
	protected int width, height;
	protected ObjectManager objectManager;
	
	public Panel(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public abstract void draw(Graphics g, JPanel panel, Mouse mouse, float scale);
	
	public abstract void update(float scale);
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
