package net.talaatharb.gameengine.input;

import java.awt.Component;

public class AWTInput extends Input {

	public AWTInput(final Component parentComponent, final int scale) {
		keyboard = new AWTKeyboard(parentComponent);
		mouse = new AWTMouse(parentComponent, scale);
	}

	@Override
	public void release() {
		keyboard.release();
		mouse.release();
	}
}
