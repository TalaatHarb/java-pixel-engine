package net.talaatharb.gameengine.sandbox.simplemousefollower;

import net.talaatharb.gameengine.Game;
import net.talaatharb.gameengine.SwingGame;
import net.talaatharb.gameengine.graphics.Renderer;
import net.talaatharb.gameengine.logic.GameLogic;

public class SimpleSwingMouseFollower extends SwingGame {

	public static void main(String[] args) {
		final Game game = new SimpleSwingMouseFollower();
		game.start();
	}

	private GameLogic gameLogic;

	public SimpleSwingMouseFollower() {
		super(300, 300, 2, "Simple Swing Mouse Follower");
	}

	@Override
	public void render(Renderer renderer) {
		gameLogic.render(renderer);
	}

	@Override
	public void setup() {
		super.setup();
		gameLogic = new SimpleMouseFollowerLogic(this);
	}

	@Override
	public void update(long delta) {
		gameLogic.update(delta);
	}

}
