package com.autoreason.setmincheck.setobjects;

import java.util.Set;

/**
 * An interface to convert a {@link Set} into a certain representation form with
 * the intention to allow better processing, like faster subset checking.
 *
 * @param <R> The data type for the set representation (e.g. long[])
 */
public interface SetConverter<R> {

	/**
	 * Convert a {@link Set} into the defined representation form of type {@code R}
	 * 
	 * @param set The {@link Set} that is transformed into the representation of
	 *            type {@code R}
	 * @return An object of type {@code R} that represents the given {@code set}
	 */
	public R convertSet(Set<?> set);

}
