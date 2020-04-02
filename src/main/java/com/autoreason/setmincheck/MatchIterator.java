package com.autoreason.setmincheck;

import java.util.Collection;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.TreeSet;

/**
 * 
 *
 * @param <C>
 * @param <T>
 */
public abstract class MatchIterator<C extends Comparable<C>, T> implements MatchProvider<C, T> {

	/**
	 * Find all elements from a {@link Collection} that match the given object of
	 * type {@code T}
	 * 
	 * @param col  A {@code Collection<C>} with elements of the {@link Comparable}
	 *             type {@code C}
	 * @param test An object of some type {@code T}
	 * @return An {@link Iterable} that contains, in an sorted order, every element
	 *         from {@code col} which {@link MatchProvider#matches} the object
	 *         {@code test}
	 */
	Iterable<C> matchesOf(Collection<C> col, T test) {
		return new Iterable<C>() {
			// transform Collection to NavigableSet
			NavigableSet<C> naviCol = new TreeSet<C>(col);

			// TODO handle NullPointerException ???

			@Override
			public Iterator<C> iterator() {
				return new Iterator<C>() {
					// initialize next element with first match
					C next = getFirstCandidate();

					/**
					 * Get first match from sorted collection
					 * 
					 * @return First element of {@code col} that is a match of {@code test} or
					 *         {@code null} if no such element exists
					 */
					private C getFirstCandidate() {
						// get first element of collection
						C cur = naviCol.first();
						// return first element if it is a match of test
						if (matches(cur, test)) {
							return cur;
						}
						// else, look for next match in collection
						else {
							return getNextCandidate(cur);
						}
					}

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
					 * Get the next match of {@code test} in the {@link NavigableSet}
					 * {@code naviCol} being greater than {@code cur}
					 * 
					 * @param cur
					 * @return The smallest element of {@code naviCol} that is a match of
					 *         {@code test} and greater than {@code cur}
					 */
					private C getNextCandidate(C cur) {
						// look for next match in collection
						while (cur != null) {
							// get next match (used to skip all elements from collection being smaller)
							C nextMatch = getNextMatch(cur, test);
							// stop if no next match available
							if (nextMatch == null) {
								cur = null;
								break;
							} else {
								// get next candidate from collection that is greater than match
								cur = naviCol.ceiling(nextMatch);

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
