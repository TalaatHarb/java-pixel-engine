package net.talaatharb.gameengine.sandbox.simplesquare;

import net.talaatharb.gameengine.Game;
import net.talaatharb.gameengine.graphics.Renderer;
import net.talaatharb.gameengine.logic.GameLogic;
import net.talaatharb.gameengine.swing.SwingGame;

public class SimpleSwingSquare extends SwingGame {

	public static void main(String[] args) {
		final Game game = new SimpleSwingSquare();
		game.start();
	}

	private GameLogic gameLogic;

	public SimpleSwingSquare() {
		super(500, 500, "Simple Square");
	}
	
	@Override
	public void render(Renderer renderer) {
		gameLogic.render(renderer);
	}

	@Override
	public void setup() {
		super.setup();
		gameLogic = new SimpleSquareGameLogic(this);
	}

	@Override
	public void update(long delta) {
		gameLogic.update(delta);
	}

}
