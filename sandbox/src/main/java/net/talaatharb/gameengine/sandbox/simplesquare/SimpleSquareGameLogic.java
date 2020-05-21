package net.talaatharb.gameengine.sandbox.simplesquare;

import net.talaatharb.gameengine.Game;
import net.talaatharb.gameengine.graphics.Renderer;
import net.talaatharb.gameengine.input.Keyboard;
import net.talaatharb.gameengine.logic.GameLogic;

public class SimpleSquareGameLogic extends GameLogic {
	private static final int SQUARE_COLOR = 0xFF00FF;

	private static final int SQUARE_SIDE = 32;

	private static final int SQUARE_SPEED = 5;

	private int height;

	private Keyboard keyboard;

	private int width;
	private int x;

	private int y;

	public SimpleSquareGameLogic(final Game game) {
		super(game);
		keyboard = game.getInput().getKeyboard();
		width = game.getWidth();
		height = game.getHeight();
	}

	@Override
	public void render(Renderer renderer) {
		renderer.fillRec(x, y, SQUARE_SIDE, SQUARE_SIDE, SQUARE_COLOR);
	}

	@Override
	public void update(long delta) {
		if (keyboard.isKeyPressed(Keyboard.VK_UP))
			y -= SQUARE_SPEED;

		if (keyboard.isKeyPressed(Keyboard.VK_DOWN))
			y += SQUARE_SPEED;

		if (keyboard.isKeyPressed(Keyboard.VK_LEFT))
			x -= SQUARE_SPEED;

		if (keyboard.isKeyPressed(Keyboard.VK_RIGHT))
			x += SQUARE_SPEED;

		if (x < 0)
			x = 0;

		if (x > (width - SQUARE_SIDE))
			x = (width - SQUARE_SIDE);

		if (y < 0)
			y = 0;

		if (y > (height - SQUARE_SIDE))
			y = (height - SQUARE_SIDE);

		if (keyboard.isKeyPressed(Keyboard.VK_ESCAPE)) {
			this.game.stop();
		}
	}
}
