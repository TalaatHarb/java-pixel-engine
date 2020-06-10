package net.talaatharb.gameengine.graphics;

import lombok.AccessLevel;
import lombok.Setter;
import net.talaatharb.gameengine.logic.Updateable;

public class CFAnimatedSprite extends Sprite implements Updateable {

	public static final long DEFAULT_FRAME_TIME = (long) (1e9 / 4);

	public static final int DIRECTION_X = 0;

	public static final int DIRECTION_Y = 1;

	protected int currentFrame = 0;

	protected Sprite[] frames;

	@Setter(value = AccessLevel.PROTECTED)
	protected long frameTime;

	protected int numFrames;

	protected long time = 0;

	public CFAnimatedSprite(final Sprite[] sprites) {
		super(sprites[0].width, sprites[0].height, sprites[0].pixels);
		this.frameTime = DEFAULT_FRAME_TIME;
		this.frames = sprites;
		this.numFrames = sprites.length;
	}

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
		frames = new Sprite[numFrames];
		frames[0] = new Sprite(this.width, this.height, this.pixels);
		for (int i = 1; i < numFrames; i++) {
			if (direction == DIRECTION_X) {
				frames[i] = new Sprite(this.width, this.height,
						loadPixels(spriteSheet, indexX + i * cellsX, indexY,
								width, height));
			} else {
				frames[i] = new Sprite(this.width, this.height,
						loadPixels(spriteSheet, indexX, indexY + i * cellsY,
								width, height));
			}
		}
	}

	public void reset() {
		time = 0;
		currentFrame = 0;
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
		}
	}

	@Override
	public int getHeight() {
		return this.frames[currentFrame].height;
	}

	@Override
	public int getWidth() {
		return this.frames[currentFrame].width;
	}

	@Override
	public int[] getPixels() {
		return this.frames[currentFrame].pixels;
	}

}
