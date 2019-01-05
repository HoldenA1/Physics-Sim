package tech.hackerlife.sim.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
	private final static int HEIGHT_OFFSET = 40;
	private final static int WIDTH_OFFSET = 17;
	
	private float lastTime = 0;
	
	enum Panels {
		OBJECT_SELECTION, DATA_SELECTION, AXIS_SELECTION, GRAPHING;
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
	String[] displayDataBoxLabels = {"Position", "Velocity", "Acceleration", "Force"};
	Button graphButton;
	
	// Axis Selection
	boolean changeAxisSelectionVisibility = false;
	Button xAxis, yAxis;
	boolean graphX;
	
	// Graphing
	ArrayList<String> thingsToGraph = new ArrayList<String>();
	Graph graph;
	private int updates = 0;
	
	public GraphingTool(ObjectManager manager) {
		mouse = new Mouse();
		this.addMouseListener(mouse);
		this.addComponentListener(new ResizeListener());
		
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
		
		// Axis Selection
		xAxis = new Button("X-Axis", 20, 50);
		yAxis = new Button("Y-Axis", 20, 150);
		xAxis.setVisibility(false);
		yAxis.setVisibility(false);
		gui.add(xAxis);
		gui.add(yAxis);
		
		// Creates the window
		Window graphingToolFrame = new Window(NAME, WIDTH, HEIGHT).changeCloseOperation(JFrame.DISPOSE_ON_CLOSE).setResizeable(true);	
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
						changeAxisSelectionVisibility = true;
						panel = Panels.AXIS_SELECTION;
					}
				}
			}
			break;
		case AXIS_SELECTION:
			if (xAxis.isPressed() || yAxis.isPressed()) {
				if (xAxis.isPressed()) {
					graphX = true;
				} else {
					graphX = false;
				}
				changeAxisSelectionVisibility = true;
				String[] labels = new String[thingsToGraph.size()];
				for (int i = 0; i < thingsToGraph.size(); i++) {
					labels[i] = thingsToGraph.get(i);
				}
				graph = new Graph("Time", labels, WIDTH - WIDTH_OFFSET, HEIGHT - HEIGHT_OFFSET);
				graph.displayGrid(true);
				panel = Panels.GRAPHING;
			}
			break;
		case GRAPHING:
			graph.draw(g);
			break;
		}
		
		repaint();
	}
	
	public void update() {
		if (panel == Panels.GRAPHING) {
			float time = (float) (updates / Main.realTimeUPS);
			if (time - lastTime > 0.25 || time == 0) {
				for (int i = 0; i < thingsToGraph.size(); i++) {
					String thingToGraph = thingsToGraph.get(i);
					float position, velocity, acceleration, force;
					if (graphX) {
						position = selectedObject.getPosition().X();
						velocity = selectedObject.getVelocity().X();
						acceleration = selectedObject.getAcceleration().X();
						force = selectedObject.getForce().X();
					} else {
						position = selectedObject.getPosition().Y();
						velocity = selectedObject.getVelocity().Y();
						acceleration = selectedObject.getAcceleration().Y();
						force = selectedObject.getForce().Y();
					}
					if (thingToGraph.equals(displayDataBoxLabels[0])) {
						graph.addData(i, new Vector2D(time, position));
					} else if (thingToGraph.equals(displayDataBoxLabels[1])) {
						graph.addData(i, new Vector2D(time, velocity));
					} else if (thingToGraph.equals(displayDataBoxLabels[2])) {
						graph.addData(i, new Vector2D(time, acceleration));
					} else if (thingToGraph.equals(displayDataBoxLabels[3])) {
						graph.addData(i, new Vector2D(time, force));
					}
				}
				lastTime = time;
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
		if (changeAxisSelectionVisibility) {
			xAxis.setVisibility(!xAxis.isVisible());
			yAxis.setVisibility(!yAxis.isVisible());
			changeAxisSelectionVisibility = false;
			
		}
	}
	
	class ResizeListener extends ComponentAdapter {
        public void componentResized(ComponentEvent e) {
        	if (!(graph == null)) {
        		graph.resize(e.getComponent().getSize().width - WIDTH_OFFSET, e.getComponent().getSize().height);
        	}
        }
	}

}