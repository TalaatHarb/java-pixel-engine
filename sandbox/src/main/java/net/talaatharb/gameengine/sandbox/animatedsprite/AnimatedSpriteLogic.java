package net.talaatharb.gameengine.sandbox.animatedsprite;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import net.talaatharb.gameengine.Game;
import net.talaatharb.gameengine.graphics.CFAnimatedSprite;
import net.talaatharb.gameengine.graphics.Renderer;
import net.talaatharb.gameengine.graphics.SpriteSheet;
import net.talaatharb.gameengine.input.Keyboard;
import net.talaatharb.gameengine.logic.GameLogic;

@Slf4j
public class AnimatedSpriteLogic extends GameLogic {
	private static final int CHARACTER_HEIGHT = 48;

	private static final int CHARACTER_WIDTH = 32;

	private static final int MOVEMEMNT_SPEED = 2;

	private static final int NUM_SPRITES = 4;

	private static final String SHEET_PATH = "animatedsprite/sample-character.png";

	private static final int TRANSPARENCY_COLOR = 0xFFFF00FF;

	private int direction = 0;

	private int height;

	private Keyboard keyboard;

	private int lastDirection = 0;

	private SpriteSheet sheet;

	private CFAnimatedSprite[] sprites;

	private int width;

	private int x;

	private int y;

	public AnimatedSpriteLogic(final Game game) {
		super(game);
		keyboard = game.getInput().getKeyboard();
		width = game.getWidth();
		height = game.getHeight();

		try {
			sheet = new SpriteSheet(SHEET_PATH, game.getSpriteSheetLoader());
		} catch (IOException e) {
			log.debug("Unable to load sprite sheet at: " + SHEET_PATH);
		}

		sprites = new CFAnimatedSprite[NUM_SPRITES];
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new CFAnimatedSprite(sheet, 0, i * 3, 2, 3, 4);
		}
	}

	@Override
	public void render(Renderer renderer) {
		renderer.spriteWithTransparency(sprites[direction], x, y,
				TRANSPARENCY_COLOR);
	}

	@Override
	public void update(long delta) {
		boolean keyPressed = false;
		if (keyboard.isKeyPressed(Keyboard.VK_UP)) {
			y -= MOVEMEMNT_SPEED;
			direction = 3;
			keyPressed = true;
		}

		if (keyboard.isKeyPressed(Keyboard.VK_DOWN)) {
			y += MOVEMEMNT_SPEED;
			direction = 0;
			keyPressed = true;
		}

		if (keyboard.isKeyPressed(Keyboard.VK_LEFT)) {
			x -= MOVEMEMNT_SPEED;
			direction = 1;
			keyPressed = true;
		}

		if (keyboard.isKeyPressed(Keyboard.VK_RIGHT)) {
			x += MOVEMEMNT_SPEED;
			direction = 2;
			keyPressed = true;
		}

		if (keyboard.isKeyPressed(Keyboard.VK_ESCAPE)) {
			this.game.stop();
		}

		if (x < 0)
			x = 0;

		if (x > (width - CHARACTER_WIDTH))
			x = (width - CHARACTER_WIDTH);

		if (y < 0)
			y = 0;

		if (y > (height - CHARACTER_HEIGHT))
			y = (height - CHARACTER_HEIGHT);

		if (lastDirection != direction)
			sprites[lastDirection].reset();

		lastDirection = direction;

		if (keyPressed) {
			sprites[direction].update(delta);
		}else {
			sprites[direction].reset();
		}
	}
}
