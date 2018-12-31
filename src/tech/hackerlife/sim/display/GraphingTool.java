package tech.hackerlife.sim.display;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tech.hackerlife.sim.display.gui.GUIManager;
import tech.hackerlife.sim.display.gui.Mouse;
import tech.hackerlife.sim.display.gui.button.Button;
import tech.hackerlife.sim.physics.ObjectManager;
import tech.hackerlife.sim.physics.matter.Matter;

public class GraphingTool extends JPanel {
	// Frame variables
	private final String NAME = "Graphing Tool";
	private static final long serialVersionUID = 3598369832919534225L;
	
	enum Screens {
		OBJECT_SELECTION, GRAPHING;
	}
	
	Screens screen = Screens.OBJECT_SELECTION;
	
	Mouse mouse;
	ObjectManager manager;
	
	// Object Selection
	GUIManager gui = new GUIManager();
	Button[] matterSelection;
	ArrayList<Matter> matter;
	Matter selectedObject;
	
	public GraphingTool(ObjectManager manager) {
		mouse = new Mouse();
		this.addMouseListener(mouse);
		
		this.manager = manager;
		
		matter = manager.getMatterList();
		matterSelection = new Button[matter.size()];
		for (int i = 0; i < matter.size(); i++) {
			matterSelection[i] = new Button(matter.get(i).getName(), 20, 50 + 100 * i);
			gui.add(matterSelection[i]);
		}
		
		Window graphingToolFrame = new Window(NAME).changeCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		graphingToolFrame.add(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.LIGHT_GRAY);
		
		gui.updateElements(g, this, mouse);
		
		switch(screen) {
		case OBJECT_SELECTION:
			for (Button b: matterSelection) {
				if (b.isPressed()) {
					for (Button button: matterSelection) {
						button.setVisibility(false);
					}
					for (Matter m: matter) {
						if (m.getName().equals(b.getLabel())) {
							selectedObject = m;
						}
					}
					screen = Screens.GRAPHING;
				}
			}
			break;
		case GRAPHING:
			g.drawString(selectedObject.getPosition().toString(), 50, 50);
			break;
		}
		
		repaint();
	}

}