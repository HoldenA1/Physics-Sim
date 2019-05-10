package tech.hackerlife.sim.display.panels;

import java.awt.Graphics;
import javax.swing.JPanel;
import tech.hackerlife.gui.*;

public class PanelManager {
	
	public enum PanelList {
		MAIN_MENU, SIM_PANEL
	}
	
	public static PanelList selectedPanel = PanelList.SIM_PANEL;
	
	// Panels
	MainMenuPanel mainMenuPanel;
	SimPanel simPanel;
	
	public PanelManager(int width, int height) {
		simPanel = new OrbitalSim(width, height-40);
		mainMenuPanel = new MainMenuPanel(width, height-40);
	}
	
	public void draw(Graphics g, JPanel panel, Mouse mouse, float scale) {
		switch(selectedPanel) {
		case MAIN_MENU:
			mainMenuPanel.draw(g, panel, mouse, scale);
			break;
		case SIM_PANEL:
			simPanel.draw(g, panel, mouse, scale);
			break;
		}
	}
	
	public void update(float scale) {
		switch(selectedPanel) {
		case MAIN_MENU:
			mainMenuPanel.update(scale);
			break;
		case SIM_PANEL:
			simPanel.update(scale);
			break;
		}
	}

}
