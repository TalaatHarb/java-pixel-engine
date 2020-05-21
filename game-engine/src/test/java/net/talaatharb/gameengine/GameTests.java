package net.talaatharb.gameengine;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;

import net.talaatharb.gameengine.graphics.Renderer;

public class GameTests {

	private static final long DEFAULT_DELAY = 2500L;
	private static final long DEFAULT_POLL = 100L;

	@Test
	public void emptyGameStartsAndStops() throws InterruptedException {
		final Game game = new Game() {

			@Override
			protected void renderGame() {
				render(renderer);
			}

			@Override
			public void update(final long delta) {
			}

			@Override
			public void render(Renderer renderer) {				
			}
		};
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				game.stop();
			}
		}, DEFAULT_DELAY);
		game.start();

		assertTrue(game.isRunning());

		Awaitility.await() // Await
				.atMost((int) Math.floor(1.1 * DEFAULT_DELAY), TimeUnit.MILLISECONDS) // Maximum time
				.with().pollInterval(DEFAULT_POLL, TimeUnit.MILLISECONDS) // Check interval
				.until(() -> !game.isRunning()); // Condition

		assertFalse(game.isRunning());
	}

}
