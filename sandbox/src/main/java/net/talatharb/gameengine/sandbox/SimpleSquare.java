package net.talatharb.gameengine.sandbox;

import net.talaatharb.gameengine.Game;
import net.talaatharb.gameengine.graphics.Renderer;
import net.talaatharb.gameengine.input.Keyboard;
import net.talaatharb.gameengine.swing.SwingGame;

public class SimpleSquare extends SwingGame {

	private static final int SQUARE_COLOR = 0xFF00FF;

	private static final int SQUARE_SIDE = 32;

	private static final int SQUARE_SPEED = 5;

	public static void main(String[] args) {
		final Game game = new SimpleSquare();
		game.start();
	}

	private int x;

	private int y;

	@Override
	public void render(Renderer renderer) {
		renderer.fillRec(x, y, SQUARE_SIDE, SQUARE_SIDE, SQUARE_COLOR);
	}

	@Override
	public void update(long delta) {
		if (input.getKeyboard().isKeyPressed(Keyboard.VK_UP))
			y -= SQUARE_SPEED;

		if (input.getKeyboard().isKeyPressed(Keyboard.VK_DOWN))
			y += SQUARE_SPEED;

		if (input.getKeyboard().isKeyPressed(Keyboard.VK_LEFT))
			x -= SQUARE_SPEED;

		if (input.getKeyboard().isKeyPressed(Keyboard.VK_RIGHT))
			x += SQUARE_SPEED;

		if (x < 0)
			x = 0;

		if (x > (width - SQUARE_SIDE))
			x = (width - SQUARE_SIDE);

		if (y < 0)
			y = 0;

		if (y > (height - SQUARE_SIDE))
			y = (height - SQUARE_SIDE);

		if (input.getKeyboard().isKeyPressed(Keyboard.VK_ESCAPE)) {
			this.stop();
		}
	}

}
