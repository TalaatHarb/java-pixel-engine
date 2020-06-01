package net.talaatharb.gameengine.input;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AWTKeyboard extends Keyboard implements KeyListener {

	private Component parentComponent;

	public AWTKeyboard(final Component parentComponent) {
		super();
		this.parentComponent = parentComponent;
		parentComponent.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// nothing to happen for key typed events
	}

	@Override
	public void release() {
		this.parentComponent.removeKeyListener(this);
	}

}
