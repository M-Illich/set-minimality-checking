package com.autoreason.setmincheck.setobjects;

import java.util.Set;

/**
 * An interface to represent {@link Set} objects with the intention to
 * allow better processing, like faster subset checking.
 *
 * @param <R> The data type for the set representation (e.g. long[])
 */
public interface SetRepresent<R> {

	/**
	 * Convert a {@link Set} into the defined representation form of type {@code R}
	 * w.r.t. to a given attribute
	 * 
	 * @param set  The {@link Set} that is transformed into the representation of
	 *             type {@code R}
	 * @param attr An {@code Object} serving as attribute to define the form of the
	 *             intended representation (e.g. the length of a generated array)
	 * @return An object of type {@code R} that represents the given {@code set}
	 */
	public R convertSet(Set<?> set, Object attr);	

}
