package net.talaatharb.gameengine.input;

import lombok.Getter;

public abstract class Mouse {

	@Getter
	protected int button;

	protected int scale;

	protected int x;

	protected int y;

	public Mouse(final int scale) {
		this.scale = scale;
	}

	public int getX() {
		return x / scale;
	}

	public int getY() {
		return y / scale;
	}

	public abstract void release();
}
