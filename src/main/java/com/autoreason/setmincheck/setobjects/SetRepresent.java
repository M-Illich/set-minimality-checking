package com.autoreason.setmincheck.setobjects;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class SetRepresent<T, R> implements Comparable<T> {

	/**
	 * A specific representation of a {@link Set} for simpler processing
	 */
	public R setRepresentation;

	/**
	 * Convert the {@link Set} elements contained in a {@link Collection} into an
	 * objects of type {@code T}
	 * 
	 * @param <S>
	 * 
	 * @param col A {@link Collection} of {@link Set} elements
	 * @return A {@link Collection} containing elements of type {@code T} that
	 *         represent the sets given in {@code col}
	 */
	public abstract <S> Collection<T> convertCollection(Collection<Set<S>> col);
	
}
