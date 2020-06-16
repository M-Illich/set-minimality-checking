package com.autoreason.setmincheck.setobjects;

/**
 * An abstract class that extends the {@link SetConverter} interface by an
 * attribute to determine the form of the intended set conversion, like defining
 * the length of an array
 *
 * @param <R> The data type for the set representation (e.g. long[])
 * @param <T> The data type for the attribute that influences the form of the
 *            set representation (e.g. length of array)
 */
public abstract class AbstractSetConverter<R, T> implements SetConverter<R> {
	/**
	 * Parameter of type {@code T} that determines the form of the set
	 * representation computed by {@link #convertSet}
	 */
	T convertAttribute;

	/**
	 * positive {@code int} by which the set size is divided to determine the length
	 * of the bit vector
	 */
	public static int DIVISOR = 64;

}
