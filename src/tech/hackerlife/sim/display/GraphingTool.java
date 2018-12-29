package tech.hackerlife.sim.display;

import javax.swing.JFrame;
import javax.swing.JPanel;
import tech.hackerlife.sim.display.gui.Mouse;

public class GraphingTool extends JPanel {
	// Frame variables
	private final String NAME = "Graphing Tool";
	private final int WIDTH = 1280, HEIGHT = 720;
	private static final long serialVersionUID = 3598369832919534225L;
	
	Mouse mouse;
	
	public GraphingTool() {
		mouse = new Mouse();
		this.addMouseListener(mouse);
		
		Window graphingToolFrame = new Window(NAME, WIDTH, HEIGHT).changeCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		graphingToolFrame.add(this);
	}

}
