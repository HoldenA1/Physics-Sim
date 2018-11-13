package tech.hackerlife.sim.display;

import java.awt.*;
import javax.swing.JPanel;

import tech.hackerlife.sim.maths.Vector2D;
import tech.hackerlife.sim.physics.Block;

public class Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	Vector2D zero = new Vector2D(0,0);
	
	Block m1 = new Block(10.0, new Vector2D(4,3), zero, zero, 5, 2);

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.LIGHT_GRAY);
		
		m1.draw(g);
		
	}

}