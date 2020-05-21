package net.talaatharb.gameengine.input;

import lombok.Getter;

public abstract class Input {

	@Getter
	protected Keyboard keyboard;

	public abstract void release();

}
