package net.talaatharb.gameengine.sandbox.simplemousefollower;

import net.talaatharb.gameengine.Game;
import net.talaatharb.gameengine.SwingGame;
import net.talaatharb.gameengine.graphics.Renderer;
import net.talaatharb.gameengine.logic.GameLogic;

public class SimpleSwingMouseFollowerGame extends SwingGame {

	public static final String TITLE = "Swing Simple Mouse Follower";

	public static void main(String[] args) {
		final Game game = new SimpleSwingMouseFollowerGame();
		game.start();
	}

	private GameLogic gameLogic;

	public SimpleSwingMouseFollowerGame() {
		super(300, 300, 2, TITLE);
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
