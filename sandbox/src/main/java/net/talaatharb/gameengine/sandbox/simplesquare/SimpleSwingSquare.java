package net.talaatharb.gameengine.sandbox.simplesquare;

import net.talaatharb.gameengine.Game;
import net.talaatharb.gameengine.SwingGame;
import net.talaatharb.gameengine.graphics.Renderer;
import net.talaatharb.gameengine.logic.GameLogic;

public class SimpleSwingSquare extends SwingGame {
	
	public static final String TITLE = "Simple Square";

	public static void main(String[] args) {
		final Game game = new SimpleSwingSquare();
		game.start();
	}

	private GameLogic gameLogic;

	public SimpleSwingSquare() {
		super(500, 500, TITLE);
	}

	@Override
	public void render(Renderer renderer) {
		gameLogic.render(renderer);
	}

	@Override
	public void setup() {
		super.setup();
		gameLogic = new SimpleSquareLogic(this);
	}

	@Override
	public void update(long delta) {
		gameLogic.update(delta);
	}

}
