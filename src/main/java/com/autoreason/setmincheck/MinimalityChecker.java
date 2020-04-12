package com.autoreason.setmincheck;

import java.util.Collection;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;

import com.autoreason.setmincheck.setobjects.SetRepresent;

/**
 * 
 *
 * @param <C>
 * @param <T>
 */
public abstract class MinimalityChecker<C extends SetRepresent<C,?,?>, T> extends MatchIterator<C, Set<?>> {

	/**
	 * Check if a {@link Set} is minimal w.r.t. a {@link NavigableSet}, which means
	 * that the latter does not contain any (object that represents a) subset of the
	 * tested set
	 * 
	 * @param col  A {@code NavigableSet} with elements of the {@link Comparable}
	 *             type {@code C}
	 * @param test A {@link Set}
	 * @return {@code true} if no subset of {@code test} appears in the collection
	 *         {@code col}, otherwise {@code false}
	 */
	public boolean isMinimal(NavigableSet<C> col, Set<?> test) {
		// get iterator for elements from collection that are matching subset candidates of test
		Iterator<C> subsetIter = matchesOf(col, test).iterator();
		// go through found candidates and perform subset checking
		while (subsetIter.hasNext()) {
			if (subsetOf(subsetIter.next(), test)) {
				// subset found -> not minimal
				return false;
			}
		}
		// no subset found -> minimal
		return true;
	}

	/**
	 * Check if an object of the {@link Comparable} type {@code C} represents a
	 * subset of a given {@link Set}
	 * <p>
	 * Note: This functionality may be already implemented by
	 * {@link MatchProvider#matches}, in which case this method should always return
	 * {@code true}
	 * </p>
	 * 
	 * @param cand An object of the {@link Comparable} type {@code C}
	 * @param test A {@link Set}
	 * @return {@code true} if {@code cand} represents a subset of {@code test},
	 *         otherwise {@code false}
	 */
	protected abstract boolean subsetOf(C cand, Set<?> test);
}
