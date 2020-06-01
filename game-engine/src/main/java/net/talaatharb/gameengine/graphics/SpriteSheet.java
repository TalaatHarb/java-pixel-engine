package net.talaatharb.gameengine.graphics;

import java.io.IOException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(value = AccessLevel.PACKAGE)
public class SpriteSheet {

	public static final boolean DEFAULT_EXTERNAL = false;
	public static final int DEFAULT_SIZE = 16;

	private int cellsX;
	private int cellsY;
	private final boolean external;

	private int height;
	private final String path;
	private int[] pixels;
	private final int size;
	private int width;

	public SpriteSheet(final String path, final int size,
			final boolean external, final SpriteSheetLoader loader)
			throws IOException {
		this.path = path;
		this.size = size;
		this.external = external;
		loader.load(this);
	}

	public SpriteSheet(final String path, final SpriteSheetLoader loader)
			throws IOException {
		this(path, DEFAULT_SIZE, DEFAULT_EXTERNAL, loader);
	}
}
