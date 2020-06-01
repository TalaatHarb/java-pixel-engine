package net.talaatharb.gameengine.graphics;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Renderer {

	protected int height;

	protected int[] pixels;

	protected int width;

	public Renderer(final int width, final int height) {
		this.width = width;
		this.height = height;

		log.debug("Constructing renderer");
	}

	public void clear() {
		for (int y = 0; y < height; y++) {
			final int yShift = y * width;
			for (int x = 0; x < width; x++) {
				pixels[x + yShift] = 0x000000;
			}
		}
	}

	public void fillRec(final int x, final int y, final int w, final int h,
			final int fillColor) {
		final int lastY = y + h;
		final int lastX = x + w;
		for (int j = y; j < lastY; j++) {

			if (j >= this.height || j < 0)
				continue;

			final int yShift = j * this.width;
			for (int i = x; i < lastX; i++) {
				if (i >= this.width || i < 0)
					continue;

				pixels[i + yShift] = fillColor;
			}
		}
	}

	public void sprite(final Sprite sprite, final int i, final int j) {
		final int w = sprite.getWidth();
		final int h = sprite.getHeight();
		final int[] spritePixels = sprite.getPixels();

		for (int y = 0; y < h; y++) {
			final int yWorld = j + y;
			if (yWorld >= this.height || yWorld < 0)
				continue;
			final int yShiftSprite = y * w;
			final int yShift = yWorld * width;
			for (int x = 0; x < w; x++) {
				final int xWorld = i + x;
				if (xWorld >= this.width || xWorld < 0)
					continue;
				pixels[xWorld + yShift] = spritePixels[x + yShiftSprite];
			}
		}
	}

}
