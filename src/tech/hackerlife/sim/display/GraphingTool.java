package tech.hackerlife.sim.display;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tech.hackerlife.sim.Main;
import tech.hackerlife.sim.display.gui.GUIManager;
import tech.hackerlife.sim.display.gui.Mouse;
import tech.hackerlife.sim.display.gui.button.Button;
import tech.hackerlife.sim.display.gui.checkBox.CheckBox;
import tech.hackerlife.sim.display.gui.graph.Graph;
import tech.hackerlife.sim.maths.Vector2D;
import tech.hackerlife.sim.physics.ObjectManager;
import tech.hackerlife.sim.physics.matter.Matter;

public class GraphingTool extends JPanel {
	// Frame variables
	private final String NAME = "Graphing Tool";
	private static final long serialVersionUID = 3598369832919534225L;
	private final int WIDTH = 640;
	private final int HEIGHT = 480;
	
	enum Panels {
		OBJECT_SELECTION, DATA_SELECTION, GRAPHING;
	}
	
	Panels panel = Panels.OBJECT_SELECTION;
	
	Mouse mouse;
	ObjectManager manager;
	GUIManager gui = new GUIManager();
	
	// Object Selection
	boolean changeMatterSelectionVisibility = false;
	Button[] matterSelection;
	ArrayList<Matter> matter;
	Matter selectedObject;
	
	// Data Selection
	boolean changeDataSelectionVisibility = false;
	CheckBox[] displayDataBoxes = new CheckBox[4];
	String[] displayDataBoxLabels = {"Position (m)", "Velocity (m/s)", "Acceleration (m/s/s)", "Force (N)"};
	Button graphButton;
	
	// Graphing
	boolean changeGraphingVisibility = false;
	ArrayList<String> thingsToGraph = new ArrayList<String>();
	Graph graph;
	private int updates = 0;
	
	public GraphingTool(ObjectManager manager) {
		mouse = new Mouse();
		this.addMouseListener(mouse);
		
		this.manager = manager;
		
		// Object Selection
		matter = manager.getMatterList();
		matterSelection = new Button[matter.size()];
		for (int i = 0; i < matter.size(); i++) {
			matterSelection[i] = new Button(matter.get(i).getName(), 20, 50 + 100 * i);
			matterSelection[i].setVisibility(true);
			gui.add(matterSelection[i]);
		}
		if (matter.size() == 1) {
			selectedObject = matter.get(0);
			changeMatterSelectionVisibility = true;
			changeDataSelectionVisibility = true;
			panel = Panels.DATA_SELECTION;
		}
		
		// Data Selection
		for (int i = 0; i < displayDataBoxes.length; i++) {
			displayDataBoxes[i] = new CheckBox(displayDataBoxLabels[i], 20, 50 + 55 * i);
			displayDataBoxes[i].setVisibility(false);
			gui.add(displayDataBoxes[i]);
		}
		graphButton = new Button("Graph", 300, 50).withColor(Color.CYAN);
		graphButton.setVisibility(false);
		gui.add(graphButton);
		
		// Creates the window
		Window graphingToolFrame = new Window(NAME, WIDTH, HEIGHT).changeCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		graphingToolFrame.add(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.LIGHT_GRAY);
		
		checkChangeVisibilities();
		
		gui.updateElements(g, this, mouse);
		
		switch(panel) {
		case OBJECT_SELECTION:
			for (Button button: matterSelection) {
				if (button.isPressed()) {
					for (Matter m: matter) {
						if (m.getName().equals(button.getLabel())) {
							selectedObject = m;
						}
					}
					changeMatterSelectionVisibility = true;
					changeDataSelectionVisibility = true;
					panel = Panels.DATA_SELECTION;
				}
			}
			break;
		case DATA_SELECTION:
			if (graphButton.isPressed()) {
				for (CheckBox box: displayDataBoxes) {
					if (box.isChecked()) {
						thingsToGraph.add(box.getLabel());
						changeDataSelectionVisibility = true;
						changeGraphingVisibility = true;
						graph = new Graph("Time (s)", thingsToGraph.get(0), WIDTH, HEIGHT);
						panel = Panels.GRAPHING;
					}
				}
			}
			break;
		case GRAPHING:
			graph.draw(g);
			break;
		}
		
		repaint();
	}
	
	public void update() {
		if (thingsToGraph.get(0) != null) {
			String thingToGraph = thingsToGraph.get(0);
			for (String t: displayDataBoxLabels) {
				if (thingToGraph == t) {
					graph.addData(new Vector2D(selectedObject.getPosition().X(), (float)(updates / Main.realTimeUPS)));
				}
			}
			updates++;
		}
	}
	
	private void checkChangeVisibilities() {
		if (changeMatterSelectionVisibility) {
			for (Button button: matterSelection) {
				button.setVisibility(!button.isVisible());
			}
			changeMatterSelectionVisibility = false;
		}
		if (changeDataSelectionVisibility) {
			for (CheckBox box: displayDataBoxes) {
				box.setVisibility(!box.isVisible());
			}
			graphButton.setVisibility(!graphButton.isVisible());
			changeDataSelectionVisibility = false;
		}
		if (changeGraphingVisibility) {
			
			changeGraphingVisibility = false;
		}
	}

}