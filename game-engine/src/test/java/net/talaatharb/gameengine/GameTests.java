package net.talaatharb.gameengine;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;

public class GameTests {

	private static final long DEFAULT_DELAY = 2000L;
	private static final long DEFAULT_POLL = 100L;

	@Test
	public void emptyGameStartsAndStops() throws InterruptedException {
		final Game game = new Game() {
			@Override
			public void render() {
			}

			@Override
			public void update() {
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
