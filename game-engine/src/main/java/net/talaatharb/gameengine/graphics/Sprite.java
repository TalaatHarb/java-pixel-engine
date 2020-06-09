package net.talaatharb.gameengine.graphics;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class Sprite {

	protected int height;
	protected int[] pixels;
	protected int width;

	public Sprite(final SpriteSheet spriteSheet, final int indexX,
			final int indexY, final int cellsX, final int cellsY) {

		if ((indexX + cellsX) > spriteSheet.getCellsX()
				|| (indexY + cellsY) > spriteSheet.getCellsY()) {
			log.debug(String.format(
					"Wrong size or indecies indexX = %d, indexY = %d, cellsX = %d, cellsY = %d for sprite sheet: %s",
					indexX, indexY, cellsX, cellsY, spriteSheet.getPath()));
			throw new IndexOutOfBoundsException(
					"Wrong indecies or size of sprite in sprite sheet: "
							+ spriteSheet.getPath());
		}

		final int spriteSheetSize = spriteSheet.getSize();

		this.height = cellsY * spriteSheetSize;
		this.width = cellsX * spriteSheetSize;

		this.pixels = loadPixels(spriteSheet, indexX, indexY, this.width,
				this.height);
	}

	protected int[] loadPixels(final SpriteSheet spriteSheet, final int indexX,
			final int indexY, final int width, final int height) {

		final int[] loadedPixels = new int[width * height];
		final int spriteSheetSize = spriteSheet.getSize();
		final int startX = indexX * spriteSheetSize;
		final int startY = indexY * spriteSheetSize;

		final int spriteSheetWidth = spriteSheet.getWidth();
		final int[] spriteSheetPixels = spriteSheet.getPixels();

		for (int y = 0; y < height; y++) {
			final int yShift = y * width;
			final int yShiftSheet = (startY + y) * spriteSheetWidth;
			for (int x = 0; x < width; x++) {
				loadedPixels[x + yShift] = spriteSheetPixels[(startX + x)
						+ yShiftSheet];
			}
		}
		return loadedPixels;
	}

}
