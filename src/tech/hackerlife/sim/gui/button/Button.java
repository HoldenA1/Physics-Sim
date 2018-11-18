package tech.hackerlife.sim.gui.button;

import java.awt.*;
import tech.hackerlife.sim.gui.GUIElement;

public class Button extends GUIElement {
	
	public Button(String label, int x, int y) {
		super(label, x, y, (label.length()+2)*CHAR_SIZE, DEFAULT_ELEMENT_SIZE);
	}
	
	public Button(String label, int x, int y, Color buttonColor, boolean lightText) {
		super(label, x, y, (label.length()+2)*CHAR_SIZE, DEFAULT_ELEMENT_SIZE, buttonColor, lightText);
	}
	
	public Button(String label, int x, int y, int width, int height) {
		super(label, x, y, width, height);
	}
	
	public Button(String label, int x, int y, int width, int height, Color buttonColor, boolean lightText) {
		super(label, x, y, width, height, buttonColor, lightText);
	}
	
	/**
	 * Draws the button and detects if the mouse is hovering over it
	 * @param g the graphics component for rendering
	 * @param mouse the current mouse position
	 */
	public void update(Graphics g, Point mouse, boolean mousePressed) {
		if (isVisible) {
			hover = this.contains(mouse);
			isPressed = hover && mousePressed;
			
			drawColor = chooseColor(isPressed, hover);
			
			g.setColor(drawColor);
			g.fillRect(x, y, width, height);
			g.setColor(textColor);
			g.setFont(normalFont);
			g.drawString(label, width/2-(CHAR_SIZE*label.length())/2+x, height/2+13+y);
		}
	}
	
	/**
	 * @return true when button is pressed
	 */
	public boolean isPressed() {
		return isPressed;
	}

}
