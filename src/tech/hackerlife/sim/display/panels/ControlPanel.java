package tech.hackerlife.sim.display.panels;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tech.hackerlife.sim.display.gui.GUIManager;
import tech.hackerlife.sim.display.gui.Mouse;
import tech.hackerlife.sim.display.gui.button.Button;
import tech.hackerlife.sim.maths.Vector2D;

public class ControlPanel extends Panel {
	// GUI Objects
	GUIManager manager = new GUIManager();
	Mouse mouse = new Mouse();
	Button button;

	public ControlPanel(Vector2D origin, int width, int height) {
		super(origin, width, height);
		
		button = new Button("button", (int) (100+origin.X()), (int) (100+origin.Y())).withColor(Color.GRAY);
		manager.add(button);
	}

	public void draw(Graphics g, JPanel panel, float scale) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int) origin.X(), (int) origin.Y(), width, height);
		
		manager.updateElements(g, panel, mouse);
	}

}