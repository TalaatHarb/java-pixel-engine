package net.talaatharb.gameengine.sandbox;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import net.talaatharb.gameengine.Game;
import net.talaatharb.gameengine.sandbox.simplemousefollower.SimpleSwingMouseFollowerGame;
import net.talaatharb.gameengine.sandbox.simplesquare.SimpleSwingSquareGame;

@Slf4j
public class Main {

	public static void main(final String[] args) {
		final Map<String, Game> games = new HashMap<>();
		Game game = null;

		// Add all games
		final String defaultGame = SimpleSwingSquareGame.TITLE;
		games.put(SimpleSwingSquareGame.TITLE, new SimpleSwingSquareGame());
		games.put(SimpleSwingMouseFollowerGame.TITLE,
				new SimpleSwingMouseFollowerGame());

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
