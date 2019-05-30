package tech.hackerlife.sim.display.panels;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tech.hackerlife.gui.*;

public class SimSelectorPanel extends Panel {
	// GUI Objects
	GUIManager gui = new GUIManager();
	Button startSimButton, openGraphingToolButton;

	public SimSelectorPanel(int width, int height) {
		super(width, height);
			
		startSimButton = new Button("Select a Simulation", width / 2 - 200, 100, 400, 50).withColor(Color.GRAY);
//		openGraphingToolButton = new Button("Open Graphing Tool", width / 2 - 200, 200, 400, 50).withColor(Color.GRAY);
		
		gui.add(startSimButton);
//		gui.add(openGraphingToolButton);
	}

	public void draw(Graphics g, JPanel panel, Mouse mouse, float scale) {
		gui.updateElements(g, panel, mouse);
			
		if (startSimButton.isPressed()) {
			PanelManager.selectedPanel = PanelManager.PanelList.SIM_PANEL;
		}
	}
		
	public void update(float scale) {
	}

}