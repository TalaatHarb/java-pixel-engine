package net.talaatharb.gameengine.utils;

public class NumberUtils {

	public static final int bound(int number, final int lowerBound,
			final int upperBound) {
		if (number < lowerBound) {
			number = lowerBound;
		}

		if (number > upperBound) {
			number = upperBound;
		}

		return number;
	}

	private NumberUtils() {
	}
}
