package net.talaatharb.gameengine;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.talaatharb.gameengine.graphics.Renderable;
import net.talaatharb.gameengine.graphics.Renderer;
import net.talaatharb.gameengine.graphics.SpriteSheetLoader;
import net.talaatharb.gameengine.input.Input;
import net.talaatharb.gameengine.logic.Updateable;

@Slf4j
public abstract class Game implements Runnable, Updateable, Renderable {

	public static final int DEFAULT_HEIGHT = 360;

	public static final int DEFAULT_SCALE = 1;

	public static final String DEFAULT_TITLE = "Game";

	public static final int DEFAULT_WIDTH = 640;

	@Getter
	protected int height;

	@Getter
	protected Input input;

	protected Renderer renderer;

	@Getter
	protected boolean running = true;

	@Getter
	protected int scale;

	@Getter
	protected SpriteSheetLoader spriteSheetLoader;

	protected Thread thread;

	protected String title;

	@Getter
	protected int width;

	public Game() {
		this(DEFAULT_HEIGHT, DEFAULT_WIDTH, DEFAULT_SCALE, DEFAULT_TITLE);
	}

	public Game(final int height, final int width, final int scale, final String title) {
		this.height = height;
		this.scale = scale;
		this.title = title;
		this.width = width;

		log.debug("Constructing game: " + this.title);
		log.debug(String.format("Width: %d, Height: %d, Scale: %d", this.width, this.height, this.scale));
	}

	public Game(final int height, final int width, final String title) {
		this(height, width, DEFAULT_SCALE, title);
	}

	protected abstract void renderGame();

	public void run() {
		log.info("Game starting");

		setup();

		final long updateTime = ((long) 1e9) / 60;
		final long second = (long) 1e9;

		long lastTime = System.nanoTime();
		long now = 0;

		long delta = 0;
		long deltaUpdate = 0;
		long accumlatedTime = 0;
		long approximateTime = 0;

		int updates = 0;
		long frames = 0;

		while (running) {
			now = System.nanoTime();
			delta = now - lastTime;
			deltaUpdate += delta;
			accumlatedTime += delta;

			if (deltaUpdate >= updateTime) {
				update(delta);
				updates++;

				deltaUpdate -= updateTime;
			}

			renderGame();
			frames++;

			if ((accumlatedTime - approximateTime) >= second) {
				log.info(String.format("ups: %d, fps: %d, time: %f", updates, frames, (1.0 * accumlatedTime) / second));
				updates = 0;
				frames = 0;
				approximateTime += second;
			}

			lastTime = now;
		}
		stop();
	}

	public abstract void setup();

	public void start() {
		thread = new Thread(this, title);
		thread.start();
	}

	public void stop() {
		if (running) {
			log.info("Game stopped");
			running = false;
			if (input != null) {
				input.release();
			}
			try {
				thread.join();
			} catch (InterruptedException e) {
				log.warn("Game Thread interrupted: " + title, e);
				thread.interrupt();
			}
		}
	}
}
