package net.talaatharb.gameengine.sandbox.animatedsprite;

import net.talaatharb.gameengine.Game;
import net.talaatharb.gameengine.SwingGame;
import net.talaatharb.gameengine.graphics.Renderer;
import net.talaatharb.gameengine.logic.GameLogic;

public class AnimatedSpriteSwingGame extends SwingGame {

	public static final String TITLE = "Swing Animated Sprite";

	public static void main(String[] args) {
		final Game game = new AnimatedSpriteSwingGame();
		game.start();
	}

	private GameLogic gameLogic;

	public AnimatedSpriteSwingGame() {
		super(180, 180 * 16 / 9, 2, TITLE);
	}

	@Override
	public void render(Renderer renderer) {
		gameLogic.render(renderer);
	}

	@Override
	public void setup() {
		super.setup();
		gameLogic = new AnimatedSpriteLogic(this);
	}

	@Override
	public void update(long delta) {
		gameLogic.update(delta);
	}

}
