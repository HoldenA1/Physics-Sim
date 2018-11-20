package tech.hackerlife.sim.display.panels;

import java.awt.Graphics;
import javax.swing.JPanel;

import tech.hackerlife.sim.display.gui.Mouse;
import tech.hackerlife.sim.maths.Vector2D;

public abstract class Panel {
	protected int width, height;
	protected Vector2D origin;
	
	public Panel(Vector2D origin, int width, int height) {
		this.origin = origin;
		this.width = width;
		this.height = height;
	}
	
	public abstract void draw(Graphics g, JPanel panel, Mouse mouse, float scale);
}
