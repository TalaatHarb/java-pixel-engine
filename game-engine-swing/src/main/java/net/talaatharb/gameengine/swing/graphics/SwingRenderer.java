package net.talaatharb.gameengine.swing.graphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.talaatharb.gameengine.graphics.Renderer;

@Slf4j
public class SwingRenderer extends Renderer {

	@Getter
	private BufferedImage image;

	public SwingRenderer(final int width, final int height) {
		super(width, height);

		log.debug("Constructing swing renderer");
		this.image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
		this.pixels = ((DataBufferInt) this.image.getRaster().getDataBuffer()).getData();
	}
}
