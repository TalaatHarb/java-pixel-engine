package net.talaatharb.gameengine.logic;

import net.talaatharb.gameengine.Game;
import net.talaatharb.gameengine.graphics.Renderable;

public abstract class GameLogic implements Updateable, Renderable {

	protected Game game;

	public GameLogic(final Game game) {
		this.game = game;
	}
}
