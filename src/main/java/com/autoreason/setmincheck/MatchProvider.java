package com.autoreason.setmincheck;

/**
 * An interface to determine matchings of comparable elements with some other
 * object.
 *
 * @param <C> An implementation of {@link Comparable}
 * @param <T> Some object type for which a match can be defined
 */
public interface MatchProvider<C extends Comparable<C>, T> {

	/**
	 * Get the smallest element of type {@code C} that is greater than or equal to
	 * {@code current} and a match of {@code test}
	 * 
	 * @param current An object of some comparable type {@code C}
	 * @param test    An object of some type {@code T}
	 * @return The smallest object of type {@code C} which is greater than or equal
	 *         to {@code current} and matches the object {@code test}, otherwise
	 *         {@code null} if no such element exists
	 */
	C getSmallestMatchGreaterOrEqual(C current, T test);

}