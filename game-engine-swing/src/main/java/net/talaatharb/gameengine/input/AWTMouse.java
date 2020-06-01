package net.talaatharb.gameengine.input;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class AWTMouse extends Mouse
		implements
			MouseListener,
			MouseMotionListener {

	private Component parentComponent;

	public AWTMouse(final Component parentComponent, final int scale) {
		super(scale);
		this.parentComponent = parentComponent;
		this.parentComponent.addMouseListener(this);
		this.parentComponent.addMouseMotionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Unused
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Unused
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Unused
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Unused
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		button = e.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		button = -1;
	}

	@Override
	public void release() {
		parentComponent.removeMouseListener(this);
		parentComponent.removeMouseMotionListener(this);
	}
}
