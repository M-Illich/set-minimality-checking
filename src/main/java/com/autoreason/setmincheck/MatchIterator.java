package com.autoreason.setmincheck;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;

/**
 * An abstract class with the functionality to iterate over all the elements of
 * a sorted set that are considered matches of a given object.
 *
 * @param <C> An implementation of {@link Comparable}
 * @param <T> Some object type for which a match can be defined
 */
public abstract class MatchIterator<C extends Comparable<C>, T> implements MatchProvider<C, T> {

	/**
	 * Find all elements from a {@link NavigableSet} that match the given object of
	 * type {@code T}
	 * 
	 * @param col  A {@code NavigableSet<C>} with elements of the {@link Comparable}
	 *             type {@code C}
	 * @param test An object of some type {@code T}
	 * @return An {@link Iterable} that contains, in an sorted order, every element
	 *         from {@code col} which {@link MatchProvider#matches} the object
	 *         {@code test}
	 */
	protected Iterable<C> matchesOf(NavigableSet<C> col, T test) {
		return new Iterable<C>() {

			@Override
			public Iterator<C> iterator() {
				return new Iterator<C>() {
					// initialize next element with first match
					C next = getFirstCandidate();

					@Override
					public boolean hasNext() {
						return next != null;
					}

					@Override
					public C next() {
						if (next != null) {
							// safe current element
							C cur = next;
							// get next match being greater than current
							next = getNextCandidate(next);
							// return current element
							return cur;
						}
						// no next element available
						else {
							throw new NoSuchElementException();
						}
					}

					/**
					 * Get first match from sorted collection
					 * 
					 * @return First element of {@code col} that is a match of {@code test} or
					 *         {@code null} if no such element exists
					 */
					private C getFirstCandidate() {
						// get first element of collection
						C cur = col.first();
						// return first element if it is a match of test
						if (matches(cur, test)) {
							return cur;
						}
						// else, look for next match in collection
						else {
							return getNextCandidate(cur);
						}
					}

					/**
					 * Get the next match of {@code test} in the {@link NavigableSet}
					 * {@code naviCol} being greater than {@code cur}
					 * 
					 * @param cur
					 * @return The smallest element of {@code naviCol} that is a match of
					 *         {@code test} and greater than {@code cur}
					 */
					private C getNextCandidate(C cur) {
						// look for next match in collection
						C nextMatch;
						while (cur != null) {
							// get next match (used to skip all elements from collection being smaller)
							nextMatch = getNextMatch(cur, test);

							// stop if no next match available
							if (nextMatch == null) {
								cur = null;
							} else {
								// get next candidate from collection that is greater than or equal to match
								cur = col.ceiling(nextMatch);
								// check if candidate is a match of test
								if (matches(cur, test)) {
									break;
								}
							}
						}
						// return next element
						return cur;
					}

				};
			}

		};

	}

}
