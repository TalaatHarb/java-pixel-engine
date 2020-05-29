package net.talaatharb.gameengine.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AWTSpriteSheetLoader implements SpriteSheetLoader {

	@Override
	public void load(final SpriteSheet spriteSheet) throws IOException {
		if (spriteSheet.isExternal()) {
			loadExternal(spriteSheet);
		} else {
			loadInternal(spriteSheet);
		}
	}

	private void loadExternal(final SpriteSheet spriteSheet) throws IOException {
		try {
			final BufferedImage image = ImageIO.read(new File(spriteSheet.getPath()));
			final int w = image.getWidth();
			final int h = image.getHeight();

			spriteSheet.setHeight(h);
			spriteSheet.setWidth(w);

			final int[] pixels = new int[w * h];
			image.getRGB(0, 0, w, h, pixels, 0, w);
			spriteSheet.setPixels(pixels);

		} catch (IOException e) {
			log.debug("Unable to load sprite sheet at location: " + spriteSheet.getPath());
			throw e;
		}
	}

	private void loadInternal(final SpriteSheet spriteSheet) throws IOException {
		final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		try {
			final BufferedImage image = ImageIO.read(classloader.getResource(spriteSheet.getPath()));
			final int w = image.getWidth();
			final int h = image.getHeight();

			spriteSheet.setHeight(h);
			spriteSheet.setWidth(w);

			final int[] pixels = new int[w * h];
			image.getRGB(0, 0, w, h, pixels, 0, w);
			spriteSheet.setPixels(pixels);

			spriteSheet.setCellsX(w / spriteSheet.getSize());
			spriteSheet.setCellsY(h / spriteSheet.getSize());

		} catch (IOException e) {
			log.debug("Unable to load sprite sheet from resources at location: " + spriteSheet.getPath());
			throw e;
		}
	}
}
