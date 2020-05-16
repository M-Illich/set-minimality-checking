package com.autoreason.setmincheck.setobjects;

import java.util.Set;

/**
 * An abstract class for the {@link SetRepresent} interface defining attributes
 * for the original {@link Set} and the object it is represented by.
 * 
 * @param <R> The data type for the set representation (e.g. long[])
 */
public abstract class AbstractSetRepresent<R> implements SetRepresent<R> {

	/**
	 * A specific representation of a {@link Set} for simpler processing
	 */
	public R setRepresentation;

	/**
	 * The original {@link Set} used to create the {@link #setRepresentation}
	 */
	public Set<?> originalSet;

}
