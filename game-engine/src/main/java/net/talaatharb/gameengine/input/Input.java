package net.talaatharb.gameengine.input;

import lombok.Getter;

public abstract class Input {

	@Getter
	protected Keyboard keyboard;

	@Getter
	protected Mouse mouse;

	public abstract void release();

}
