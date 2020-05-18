package net.talaatharb.gameengine;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.talaatharb.gameengine.graphics.Renderer;

@Slf4j
public abstract class Game implements Runnable {

	private static final Long CLOSE_DELAY = 100L;

	public static final int DEFAULT_HEIGHT = 360;

	public static final int DEFAULT_SCALE = 1;

	public static final String DEFAULT_TITLE = "Game";

	public static final int DEFAULT_WIDTH = 640;

	private BufferStrategy bufferStrategy;

	private final Canvas canvas;

	private final JFrame frame;

	private int height;

	private Renderer renderer;

	@Getter
	private boolean running = true;

	private int scale;

	private Thread thread;

	private String title;

	private int width;

	public Game() {
		this(DEFAULT_HEIGHT, DEFAULT_WIDTH, DEFAULT_SCALE, DEFAULT_TITLE);
	}

	public Game(final int height, final int width, final int scale, final String title) {
		this.height = height;
		this.scale = scale;
		this.title = title;
		this.width = width;

		this.renderer = new Renderer(this.width, this.height);

		this.frame = new JFrame(this.title);
		this.frame.setResizable(false);

		this.canvas = new Canvas();
		this.canvas.setPreferredSize(new Dimension(this.scale * this.width, this.scale * this.height));

		this.frame.add(this.canvas);
		this.frame.pack();
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}

	public Game(final int height, final int width, final String title) {
		this(height, width, DEFAULT_SCALE, title);
	}

	/**
	 * Render game contents
	 */
	public abstract void render();

	private void renderGame() {
		if (bufferStrategy == null) {
			this.canvas.createBufferStrategy(3);
			bufferStrategy = this.canvas.getBufferStrategy();
		}
		final Graphics graphics = bufferStrategy.getDrawGraphics();

		// Clear with black color
		renderer.clear();

		render();

		// Draw layer
		graphics.drawImage(renderer.getImage(), 0, 0, canvas.getWidth(), canvas.getHeight(), null);

		graphics.dispose();
		bufferStrategy.show();
	}

	public void run() {
		while (running) {
			updateGame();
			renderGame();
		}
	}

	public void start() {
		thread = new Thread(this, title);
		thread.start();
	}

	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			log.debug("Game Thread interrupted: " + title, e);
			thread.interrupt();
		}
		
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		}, CLOSE_DELAY);
	}

	/**
	 * Update game contents
	 */
	public abstract void update();

	private void updateGame() {
		update();
	}
}
