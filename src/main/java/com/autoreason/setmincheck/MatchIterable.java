package com.autoreason.setmincheck;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;

/**
 * An implementation of {@link Iterable} containing in a sorted order every
 * element of a {@link NavigableSet} that matches a given object of type
 * {@code T} based on the matching relation defined by some
 * {@link MatchProvider} instance
 *
 * @param <C> An implementation of {@link Comparable}
 * @param <T> Some object type for which a match can be defined
 */
public class MatchIterable<C extends Comparable<C>, T> implements Iterable<C> {

	MatchProvider<C, T> matchProvider;
	NavigableSet<C> col;
	T test;

	MatchIterable(MatchProvider<C, T> mP, NavigableSet<C> nS, T t) {
		this.matchProvider = mP;
		this.col = nS;
		this.test = t;
	}

	@Override
	public Iterator<C> iterator() {
		return new Iterator<C>() {
			// initialize next element with first match found in the collection
			C next = getNextCandidate(col.first());

			@Override
			public boolean hasNext() {
				return next != null;
			}

			@Override
			public C next() {
				if (next != null) {
					// safe current element
					C cur = next;
					// determine next match being greater than current
					next = getNextCandidate(col.higher(next));
					// return current element
					return cur;
				}
				// no next element available
				else {
					throw new NoSuchElementException();
				}
			}

			/**
			 * Get the next match of {@code test} in the {@link NavigableSet} {@code col}
			 * being greater than or equal to {@code cur}
			 * 
			 * @param cur An instance of a {@link Comparable} implementation
			 * @return The smallest element of {@code col} that is a match of {@code test}
			 *         and greater than or equal to {@code cur}, or {@code null} if such an
			 *         element does not exist
			 */
			private C getNextCandidate(C cur) {
				C nextElement = cur;
				C nextMatch;
				do {
					// get the next match of test being greater than or equal to the current element
					nextMatch = matchProvider.getSmallestMatchGreaterOrEqual(nextElement, test);
					if(nextMatch == null) {
						break;
					}
					// get next element from collection that is greater than or equal to the next
					// match
					nextElement = col.ceiling(nextMatch);
				}
				// repeat until element is a match of test (or element is null)
				while (!nextElement.equals(nextMatch));

				return nextMatch;
			}

		};
	}

}
