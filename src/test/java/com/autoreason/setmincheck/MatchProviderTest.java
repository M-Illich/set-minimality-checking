package com.autoreason.setmincheck;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @param <C> An implementation of {@link Comparable}
 * @param <T> Some object type for which a match can be defined
 */
public abstract class MatchProviderTest<C extends Comparable<C>, T> {

	@Test
	public void testGetNextGreaterOrEqualMatch() {
		T test = defineTest();
		MatchProvider<C, T> matchProvider = defineMatchProvider(test);
		C current = defineMatch();
		assertTrue(current.compareTo(matchProvider.getSmallestMatchGreaterOrEqual(current, test)) == 0);

		current = defineNotMatch();
		C next = defineNextMatch();
		assertTrue(next.compareTo(matchProvider.getSmallestMatchGreaterOrEqual(current, test)) == 0);

	}

	/**
	 * Define an object of a {@link MatchProvider} implementation that realizes the
	 * matching between elements of type {@code C} and type {@code T}
	 * 
	 * @param test An object of type {@code T} for which the matching relation is
	 *             defined
	 * 
	 * @return An object of a {@link MatchProvider} implementation
	 */
	protected abstract MatchProvider<C, T> defineMatchProvider(T test);

	/**
	 * Define an object of type {@code T} that can be used for testing
	 * 
	 * @return An object of type {@code T} for which a match can be defined
	 */
	protected abstract T defineTest();

	/**
	 * Define an object of type {@code C} that is a match of the tested instance of
	 * type {@code T}
	 * 
	 * @return An object of type {@code C} that matches the object defined by
	 *         {@link #defineTest()}
	 */
	protected abstract C defineMatch();

	/**
	 * Define an object of type {@code C} that is not a match of the tested instance
	 * of type {@code T}
	 * 
	 * @return An object of type {@code C} that does not match the object defined by
	 *         {@link #defineTest()}
	 */
	protected abstract C defineNotMatch();

	/**
	 * Define an object of type {@code C} that is a match of the tested instance of
	 * type {@code T} and is greater than the instance defined by
	 * {@link #defineNotMatch()}
	 * 
	 * @return An object of type {@code C} that matches the object defined by
	 *         {@link #defineTest()} and is greater than the instance defined by
	 *         {@link #defineNotMatch()}
	 */
	protected abstract C defineNextMatch();

}
