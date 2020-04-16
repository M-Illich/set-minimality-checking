package com.autoreason.setmincheck.setobjects;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.autoreason.setmincheck.MinimalityChecker;

/**
 * An abstract class used to represent {@link Set} objects with the intention to
 * allow better processing, like faster subset checking
 *
 * @param <T> A class that extends SetRepresent
 * @param <R> A data type for the set representation
 * @param <M> A {@link MinimalityChecker} subclass for {@code T}
 */
public abstract class SetRepresent<T, R, M extends MinimalityChecker<?>> implements Comparable<T> {

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
	 * 
	 * @param <S>
	 * @param set A {@link Set}
	 * @return An object of type {@code T} that represents the given {@code set}
	 */
	public abstract <S> T convertSet(Set<S> set);

	/**
	 * Get a {@link MinimalityChecker} for type {@code T}
	 * 
	 * @return An instance of an object that extends {@link MinimalityChecker}
	 */
	public abstract M getMinChecker();

}
