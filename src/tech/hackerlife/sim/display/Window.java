package tech.hackerlife.sim.display;

import javax.swing.JFrame;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	final int DEFAULT_WIDTH = 640, DEFAULT_HEIGHT = 480;
	
	public Window(String name, int width, int height) {
		super(name);
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * @param operation use JFrame.EXIT_ON_CLOSE for example
	 */
	public Window changeCloseOperation(int operation) {
		setDefaultCloseOperation(operation);
		return this;		
	}
	
	public Window(String name) {
		this(name, WIDTH, HEIGHT);
	}
	
}