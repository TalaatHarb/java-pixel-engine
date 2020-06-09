package net.talaatharb.gameengine.graphics;

import lombok.AccessLevel;
import lombok.Setter;
import net.talaatharb.gameengine.logic.Updateable;

public class CFAnimatedSprite extends Sprite implements Updateable {

	public static final long DEFAULT_FRAME_TIME = (long) (1e9 / 4);

	public static final int DIRECTION_X = 0;

	public static final int DIRECTION_Y = 1;

	protected int currentFrame = 0;

	protected int[][] frames;

	@Setter(value = AccessLevel.PROTECTED)
	protected long frameTime;

	protected int numFrames;

	protected long time = 0;

	public CFAnimatedSprite(final SpriteSheet spriteSheet, final int indexX,
			final int indexY, final int cellsX, final int cellsY,
			final int numFrames) {
		this(spriteSheet, indexX, indexY, cellsX, cellsY, numFrames,
				DIRECTION_X);
	}

	public CFAnimatedSprite(final SpriteSheet spriteSheet, final int indexX,
			final int indexY, final int cellsX, final int cellsY,
			final int numFrames, final int direction) {
		super(spriteSheet, indexX, indexY, cellsX, cellsY);
		this.frameTime = DEFAULT_FRAME_TIME;
		this.numFrames = numFrames;
		frames = new int[numFrames][];
		frames[0] = this.pixels;
		for (int i = 1; i < numFrames; i++) {
			if (direction == DIRECTION_X) {
				frames[i] = loadPixels(spriteSheet, indexX + i * cellsX, indexY,
						width, height);
			} else {
				frames[i] = loadPixels(spriteSheet, indexX, indexY + i * cellsY,
						width, height);
			}
		}
	}

	public void reset() {
		time = 0;
		currentFrame = 0;
		this.pixels = frames[currentFrame];
	}

	@Override
	public void update(final long delta) {
		time += delta;
		if (time >= frameTime) {
			time = time % frameTime;
			currentFrame++;
			if (currentFrame == numFrames) {
				currentFrame = 0;
			}
			this.pixels = frames[currentFrame];
		}
	}

}
