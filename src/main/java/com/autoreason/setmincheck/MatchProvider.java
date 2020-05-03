package com.autoreason.setmincheck;

/**
 * An interface for defining the relation between objects such that one is a
 * match of the other one.
 *
 * @param <C> An implementation of {@link Comparable}
 * @param <T> Some object type for which a match can be defined
 */
public interface MatchProvider<C extends Comparable<C>, T> {

	/**
	 * Get the smallest element of type {@code C} that is greater than
	 * {@code previous} and a match of {@code test}
	 * 
	 * @param previous An object of some type {@code C}
	 * @param test     An object of some type {@code T}
	 * @return smallest object of type {@code C} which is greater than
	 *         {@code previous} and {@link #matches} the object {@code test} or
	 *         {@code null} if no such element exists
	 */
	C getNextMatch(C previous, T test);

	/**
	 * Check if {@code candidate} satisfies some certain condition to be a match of
	 * {@code test}
	 * 
	 * @param candidate An object of type {@code C}
	 * @param test      An object of type {@code T}
	 * @return {@code true} if {@code candidate} is a match of {@code test},
	 *         otherwise {@code false}
	 */
	boolean matches(C candidate, T test);

}