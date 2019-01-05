package tech.hackerlife.sim.display.gui.button;

import java.awt.*;
import tech.hackerlife.sim.display.gui.GUIElement;
import tech.hackerlife.sim.display.gui.Mouse;

public class Button extends GUIElement {
	
	public Button(String label, int x, int y) {
		super(label, x, y, (label.length()+2)*CHAR_SIZE, DEFAULT_ELEMENT_SIZE);
	}
	
	public Button(String label, int x, int y, int width, int height) {
		super(label, x, y, width, height);
	}
	
	public Button withColor(Color color) {
		super.withColor(color);
		return this;
	}
	
	public Button lightText() {
		super.lightText();
		return this;
	}
	
	/**
	 * Draws the button and detects if the mouse is hovering over it
	 * @param g the graphics component for rendering
	 * @param mouse the current mouse position
	 */
	public void update(Graphics g, Point mousePos, Mouse mouse) {
		if (isVisible) {
			hover = this.contains(mousePos);
			isHeld = hover && mouse.mouseButtonPressed();
			isPressed = lastTimeHeld && !mouse.mouseButtonPressed();
			lastTimeHeld = isHeld;
			
			drawColor = chooseColor(isHeld, hover);
			
			g.setColor(drawColor);
			g.fillRect(x, y, width, height);
			g.setColor(textColor);
			g.setFont(normalFont);
			g.drawString(label, width/2-(CHAR_SIZE*label.length())/2+x, height/2+13+y);
		} else {
			isPressed = false;
		}
	}
	
	/**
	 * @return true when button is pressed
	 */
	public boolean isPressed() {
		return isPressed;
	}

}
