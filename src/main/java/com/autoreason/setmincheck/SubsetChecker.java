package com.autoreason.setmincheck;

import java.util.Set;

import com.autoreason.setmincheck.setobjects.SetRepresent;

/**
 * An interface to determine when a {@link SetRepresent} implementation
 * represents a subset of another given set.
 *
 * @param <C> An implementation of {@link SetRepresent}
 */
public interface SubsetChecker<C extends SetRepresent<?>> {

	/**
	 * Check if an object of type {@link SetRepresent} represents a subset of a
	 * given {@link Set}
	 * 
	 * @param cand An implementation of {@link SetRepresent}
	 * @param test A {@link Set}
	 * @return {@code true} if {@code cand} represents a subset of {@code test},
	 *         otherwise {@code false}
	 */
	public boolean subsetOf(C cand, Set<?> test);

}
