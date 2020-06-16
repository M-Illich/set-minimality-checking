package com.autoreason.setmincheck.setobjects;

/**
 * An abstract class that extends the {@link SetConverter} interface by an
 * attribute to determine the form of the intended set conversion, like defining
 * the length of an array
 *
 * @param <R> The data type for the set representation (e.g. long[])
 */
public abstract class AbstractSetConverter<R> implements SetConverter<R> {
	/**
	 * {@code int} value determining the length of the set representation computed
	 * by {@link #convertSet}
	 */
	public static int setRepresentLength = 128;

}
