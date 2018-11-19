package tech.hackerlife.sim.display.gui.checkBox;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import tech.hackerlife.sim.display.gui.GUIElement;

public class CheckBox extends GUIElement {
	private boolean checked = false;
	private boolean pressedLastTime = false;

	public CheckBox(String label, int x, int y) {
		super(label, x, y, DEFAULT_ELEMENT_SIZE, DEFAULT_ELEMENT_SIZE);
	}
	
	public CheckBox(String label, int x, int y, int size) {
		super(label, x, y, size, size);
	}
	
	public CheckBox withColor(Color color) {
		super.withColor(color);
		return this;
	}
	
	public CheckBox lightText() {
		super.lightText();
		return this;
	}

	public void update(Graphics g, Point mouse, boolean mousePressed) {
		if (isVisible) {
			hover = this.contains(mouse);
			isPressed = hover && mousePressed;
			if (isPressed && !pressedLastTime) {
				checked = !checked;
			}
			pressedLastTime = isPressed;
			
			drawColor = chooseColor(isPressed, hover);
			
			// Draws Box
			g.setColor(drawColor);
			g.fillRect(x, y, width, height);
			g.setColor(textColor);
			g.drawRect(x, y, width, height);
			// Draws X
			if (checked) {
				g.drawLine(x, y, x+width, y+height);
				g.drawLine(x, y+height, x+width, y);
			}
			// Draws Text
			g.setFont(normalFont);
			g.drawString(label, width+x+CHAR_SIZE, height/2+13+y);
		}
	}
	
	/**
	 * @return true when checkbox is checked
	 */
	public boolean isChecked() {
		return checked;
	}

}
