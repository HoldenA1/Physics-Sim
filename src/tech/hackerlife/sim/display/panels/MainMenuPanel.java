package tech.hackerlife.sim.display.panels;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tech.hackerlife.gui.*;

public class MainMenuPanel extends Panel {
	// GUI Objects
	GUIManager gui = new GUIManager();
	Button simSelectorButton;

	public MainMenuPanel(int width, int height) {
		super(width, height);
		
		simSelectorButton = new Button("Select a Simulation", width / 2 - 200, 100, 400, 50).withColor(Color.GRAY);
		gui.add(simSelectorButton);
	}

	public void draw(Graphics g, JPanel panel, Mouse mouse, float scale) {
		gui.updateElements(g, panel, mouse);
		
		if (simSelectorButton.isPressed()) {
			PanelManager.selectedPanel = PanelManager.PanelList.SIM_PANEL;
		}
	}

	public void update(float scale) {}

}