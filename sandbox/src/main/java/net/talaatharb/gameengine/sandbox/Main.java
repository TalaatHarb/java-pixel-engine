package net.talaatharb.gameengine.sandbox;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import net.talaatharb.gameengine.Game;
import net.talaatharb.gameengine.sandbox.simplesquare.SimpleSwingSquare;

@Slf4j
public class Main {

	private static final String SIMPLE_SWING_SQUARE = "SimpleSwingSquare";

	public static void main(final String[] args) {
		final Map<String, Game> games = new HashMap<>();
		Game game = null;

		// Add all games
		final String defaultGame = SIMPLE_SWING_SQUARE;
		games.put(SIMPLE_SWING_SQUARE, new SimpleSwingSquare());

		if ((args == null) || args.length == 0) {
			log.info("Opening Default game: " + defaultGame);
			game = games.get(defaultGame);
		} else {
			final String gameName = args[0];
			log.info("Trying to open game: " + gameName);
			game = games.get(gameName);
			if (game == null) {
				log.warn("Unable to open game: " + gameName);
				log.info("Opening Default game: " + defaultGame);
				game = games.get(defaultGame);
			}
		}

		game.start();
	}

}
