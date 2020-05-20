package net.talaatharb.gameengine.swing;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import lombok.extern.slf4j.Slf4j;
import net.talaatharb.gameengine.Game;
import net.talaatharb.gameengine.graphics.Renderer;
import net.talaatharb.gameengine.swing.graphics.SwingRenderer;

@Slf4j
public abstract class SwingGame extends Game {

	private static final Long CLOSE_DELAY = 100L;

	private BufferStrategy bufferStrategy;

	private final Canvas canvas;

	private final JFrame frame;

	public SwingGame() {
		this(DEFAULT_HEIGHT, DEFAULT_WIDTH, DEFAULT_SCALE, DEFAULT_TITLE);
	}

	public SwingGame(final int height, final int width, final int scale, final String title) {
		super(height, width, scale, title);

		this.renderer = new SwingRenderer(this.width, this.height);

		this.frame = new JFrame(this.title);
		this.frame.setResizable(false);

		this.canvas = new Canvas();
		this.canvas.setPreferredSize(new Dimension(this.scale * this.width, this.scale * this.height));

		this.frame.add(this.canvas);
		this.frame.pack();
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
		this.frame.setAlwaysOnTop(true);
		
		log.info(String.format("Game: %s constructed using Swing", title));
	}

	public SwingGame(final int height, final int width, final String title) {
		this(height, width, DEFAULT_SCALE, title);
	}

	/**
	 * Render game contents
	 */
	public abstract void render(final Renderer renderer);

	@Override
	protected void renderGame(final Renderer renderer) {
		if (bufferStrategy == null) {
			this.canvas.createBufferStrategy(3);
			bufferStrategy = this.canvas.getBufferStrategy();
		}
		final Graphics graphics = bufferStrategy.getDrawGraphics();

		// Clear with black color
		renderer.clear();

		render(renderer);

		// Draw layer
		graphics.drawImage(((SwingRenderer) renderer).getImage(), 0, 0, canvas.getWidth(), canvas.getHeight(), null);

		graphics.dispose();
		bufferStrategy.show();
	}

	@Override
	public void stop() {
		super.stop();

		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		}, CLOSE_DELAY);
	}
}
