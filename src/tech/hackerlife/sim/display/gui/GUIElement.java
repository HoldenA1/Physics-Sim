package tech.hackerlife.sim.display.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public abstract class GUIElement {
	protected String label;
	protected int x, y, width, height;
	protected boolean hover = false, isHeld = false, isPressed = false, lastTimeHeld = false, isVisible = true;
	protected Font normalFont = new Font(Font.MONOSPACED, Font.PLAIN, 25);
	protected Color textColor = Color.BLACK;
	protected Color mainColor, drawColor;
	protected final static Color DEFAULT_COLOR = Color.GRAY;
	protected final static int DEFAULT_ELEMENT_SIZE = 52;
	protected final static int CHAR_SIZE = 15;
	
	public GUIElement(String label, int x, int y, int width, int height) {
		this.label = label;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.mainColor = DEFAULT_COLOR;
	}
	
	public GUIElement withColor(Color mainColor) {
		this.mainColor = mainColor;
		return this;
	}
	
	public GUIElement lightText() {
		textColor = Color.WHITE;
		return this;
	}
	
	public abstract void update(Graphics g, Point mousePos, Mouse mouse);
	
	/**
	 * gives the desired highlight color depending on what the mouse is doing
	 */
	protected Color chooseColor(boolean isHeld, boolean hover) {
		if (isHeld) {
			return mainColor.darker().darker();
		} else if (hover) {
			return mainColor.darker();
		} else {
			return mainColor;
		}
	}
	
	/**
	 * Checks to see if point p is contained in the button box
	 * @param p can be any point
	 * @return whether the point p is in the button box
	 */ 
	public boolean contains(Point p) {
		if (p == null) {
			p = new Point(-1, -1);
		}
		
		if (p.x >= x && p.x < width+x) {
			if (p.y >= y && p.y < height+y) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Sets whether to show the button or not
	 * @param isVisible sets the button's visibility
	 */
	public void setVisibility(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	public boolean isVisible() {
		return isVisible;
	}

}