package net.talaatharb.gameengine.sandbox.simplemousefollower;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;
import net.talaatharb.gameengine.Game;
import net.talaatharb.gameengine.graphics.Renderer;
import net.talaatharb.gameengine.graphics.Sprite;
import net.talaatharb.gameengine.graphics.SpriteSheet;
import net.talaatharb.gameengine.input.Mouse;
import net.talaatharb.gameengine.logic.GameLogic;
import net.talaatharb.gameengine.utils.NumberUtils;

@Slf4j
public class SimpleMouseFollowerLogic extends GameLogic {
	private static final int NUM_SPRITES = 3;
	private static final Random RANDOM = new Random();
	private static final String SHEET_PATH = "simplemousefollower/sheet.png";
	private static final int SPEED = 5;

	private static final int TARGET_HEIGHT = 8;
	private static final int TARGET_WIDTH = 8;
	private int height;
	private List<int[]> locations;
	private Mouse mouse;

	private int num;

	private SpriteSheet sheet;
	private List<Sprite> sprites;
	private int[] target;
	private int width;

	public SimpleMouseFollowerLogic(Game game) {
		super(game);
		width = game.getWidth();
		height = game.getHeight();

		try {
			sheet = new SpriteSheet(SHEET_PATH, game.getSpriteSheetLoader());
		} catch (IOException e) {
			log.debug("Unable to load sprite sheet at: " + SHEET_PATH);
		}

		sprites = new ArrayList<>();
		locations = new ArrayList<>();
		for (int i = 0; i < NUM_SPRITES; i++) {
			final Sprite sprite = new Sprite(sheet, i, 0, 1, 1);
			sprites.add(sprite);
			locations.add(new int[] { RANDOM.nextInt(width - sprite.getWidth()),
					RANDOM.nextInt(height - sprite.getHeight()) });
		}
		num = sprites.size();

		target = new int[] { width / 2, height / 2 };
		mouse = this.game.getInput().getMouse();
	}

	@Override
	public void render(final Renderer renderer) {
		for (int i = 0; i < num; i++) {
			final int[] location = locations.get(i);
			renderer.fillRec(target[0] - (TARGET_WIDTH >> 1), target[1] - (TARGET_HEIGHT >> 1), TARGET_WIDTH,
					TARGET_HEIGHT, 0xFF00FF);
			renderer.sprite(sprites.get(i), location[0], location[1]);
		}
	}

	@Override
	public void update(final long delta) {
		target[0] = mouse.getX();
		target[1] = mouse.getY();

		target[0] = NumberUtils.bound(target[0], 0, width);
		target[1] = NumberUtils.bound(target[1], 0, height);

		for (int i = 0; i < num; i++) {
			final Sprite sprite = sprites.get(i);
			final int size = sheet.getSize();
			final int[] location = locations.get(i);
			final float[] difference = new float[] { target[0] - location[0] - (sprite.getWidth() >> 1),
					target[1] - location[1] - (sprite.getHeight() >> 1) };
			float abs = (float) Math.sqrt(difference[0] * difference[0] + difference[1] * difference[1]);
			if (abs < 0.01f) {
				difference[0] = 2.0f;
				difference[1] = 2.0f;
			} else {
				difference[0] /= abs;
				difference[1] /= abs;
			}

			final int speed = RANDOM.nextInt(SPEED);
			location[0] = (int) (location[0] + difference[0] * speed);
			location[1] = (int) (location[1] + difference[1] * speed);

			location[0] = NumberUtils.bound(location[0], 0, width - size);
			location[1] = NumberUtils.bound(location[1], 0, height - size);
		}
	}

}
