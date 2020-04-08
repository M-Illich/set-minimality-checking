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
	 * @param setCol A {@link Collection} of {@link Set} elements
	 * @return A {@link Collection} containing elements of type {@code T} that
	 *         represent the sets given in {@code setCol}
	 */
	public <S> Collection<T> convertCollection(Collection<Set<S>> setCol) {
		Collection<T> convertCol = new HashSet<T>();
		// create a representation for each set
		for (Set<S> set : setCol) {
			convertCol.add(convertSet(set));
		}
		return convertCol;
	}

	/**
	 * Convert a {@link Set} into a representative object of type {@code T}
	 * @param <S>
	 * @param set A {@link Set}
	 * @return An object of type {@code T} that represents the given {@code set}
	 */
	public abstract <S> T convertSet(Set<S> set);
	
}
