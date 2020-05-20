package net.talaatharb.gameengine.swing.input;

import java.awt.Component;

import net.talaatharb.gameengine.input.Input;

public class AWTInput extends Input{
	
	public AWTInput(final Component parentComponent) {
		keyboard = new AWTKeyboard(parentComponent);
	}

	@Override
	public void release() {
		keyboard.release();		
	}
}
