package tech.hackerlife.sim.display.gui;

import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener {
	private boolean mouseButton = false;
	
	public boolean mouseButtonPressed() {
		return mouseButton;
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		mouseButton = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		mouseButton = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {		
	}

}