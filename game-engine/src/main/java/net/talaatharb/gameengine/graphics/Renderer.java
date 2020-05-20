package net.talaatharb.gameengine.graphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import lombok.Getter;

public class Renderer {
	
	protected int width;
	
	protected int height;
	
	@Getter
	private BufferedImage image;

	protected int[] pixels;
	
	public Renderer(final int width, final int height) {
		this.width = width;
		this.height = height;
		
		this.image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
		this.pixels = ((DataBufferInt) this.image.getRaster().getDataBuffer()).getData();
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
