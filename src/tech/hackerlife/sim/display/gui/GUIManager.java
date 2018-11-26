package tech.hackerlife.sim.display.gui;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GUIManager {
private ArrayList<GUIElement> elements;
	
	public GUIManager() {
		elements = new ArrayList<GUIElement>();
	}
	
	public void add(GUIElement element) {
		elements.add(element);
	}
	
	public void updateElements(Graphics g, JPanel panel, Mouse mouse) {	
		for (GUIElement e: elements)
			e.update(g, panel.getMousePosition(), mouse);
	}

}
