package net.talaatharb.gameengine.graphics;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Renderer {
	
	protected int width;
	
	protected int height;

	protected int[] pixels;
	
	public Renderer(final int width, final int height) {
		this.width = width;
		this.height = height;
		
		log.info("Constructing renderer");
	}
	
	public void clear() {
		for(int y = 0; y < height; y++) {
			final int yShift = y * width;
			for (int x = 0; x < width; x++) {
				pixels[x + yShift] = 0x000000;
			}
		}
	}

}
