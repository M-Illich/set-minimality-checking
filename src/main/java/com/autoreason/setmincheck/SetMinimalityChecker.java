package com.autoreason.setmincheck;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;

import com.autoreason.setmincheck.setobjects.SetRepresent;

public class SetMinimalityChecker {

	/**
	 * Check if a {@link Set} is minimal w.r.t. a {@link NavigableSet}, which means
	 * that the latter does not contain any element that represents a subset of the
	 * tested set
	 * 
	 * @param <S>           An implementation of both {@link SetRepresent} and
	 *                      {@link Comparable}
	 * @param col           A {@code NavigableSet} with elements of the
	 *                      {@link Comparable} type {@code C}
	 * @param test          A {@link Set}
	 * @param matchProvider A {@link MatchProvider} defining the matching relation
	 *                      between {@code test} and the elements of {@code col}
	 * @return {@code true} if no subset representation of {@code test} appears in
	 *         the collection {@code col}, otherwise {@code false}
	 */
	public static <S extends SetRepresent<?> & Comparable<S>> boolean isMinimal(NavigableSet<S> col, Set<?> test,
			MatchProvider<S, Set<?>> matchProvider) {

		// get iterator for elements from collection that match test
		Iterator<S> subsetIter = new MatchIterable<S, Set<?>>(matchProvider, col, test).iterator();
		// go through found candidates and perform subset checking
		while (subsetIter.hasNext()) {
			if (SubsetChecker.<S>subsetOf(subsetIter.next(), test)) {
				// subset found -> not minimal
				return false;
			}
		}
		// no subset found -> minimal
		return true;
	}
}
