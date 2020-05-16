package com.autoreason.setmincheck;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.TreeSet;

import org.junit.Test;

/**
 * Test for {@link MatchIterable}
 * 
 * @param <C> An implementation of {@link Comparable}
 * @param <T> Some object type for which a match can be defined
 */
public abstract class MatchIterableTest<C extends Comparable<C>, T> {

	@Test
	public void testIterator() {
		MatchProvider<C, T> matchProvider = defineMatchProvider();
		NavigableSet<C> col = defineCollections();
		T test = defineTest();
		// get matches using MatchIterable
		MatchIterable<C, T> matchIterable = new MatchIterable<C, T>(matchProvider, col, test);

		// compute expected Iterable of matches by considering every element of the
		// collection
		Iterable<C> expected = new Iterable<C>() {
			// transform Collection to NavigableSet
			NavigableSet<C> naviCol = new TreeSet<C>(col);

			@Override
			public Iterator<C> iterator() {
				return new Iterator<C>() {
					// initialize next element with first match found in the collection
					C next = getNextCandidate(naviCol.first());

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
							next = getNextCandidate(naviCol.higher(next));
							// return current element
							return cur;
						}
						// no next element available
						else {
							throw new NoSuchElementException();
						}
					}

					private C getNextCandidate(C cur) {
						do {
							// get next element in sorted collection
							cur = naviCol.higher(cur);
						} // starting from cur, consider every element in the collection until match found
						while ( cur != null && !cur.equals(matchProvider.getSmallestMatchGreaterOrEqual(cur, test)));

						return cur;
					}

				};
			}

		};

		// compare found matches
		Iterator<C> expIter = expected.iterator();
		Iterator<C> matchIter = matchIterable.iterator();
		while (expIter.hasNext()) {
			assertTrue(expIter.next().compareTo(matchIter.next()) == 0);
		}

	}

	/**
	 * Define an object of a {@link MatchProvider} implementation that realizes the
	 * matching between elements of type {@code C} and type {@code T}
	 * 
	 * @return An object of a {@link MatchProvider} implementation
	 */
	protected abstract MatchProvider<C, T> defineMatchProvider();

	/**
	 * Define a {@link NavigableSet} containing elements of type {@code C}
	 * 
	 * @return A {@link NavigableSet} containing elements of type {@code C}
	 */
	protected abstract NavigableSet<C> defineCollections();

	/**
	 * Define an object of type {@code T} that can be used for testing
	 * 
	 * @return An object of type {@code T} for which a match can be defined
	 */
	protected abstract T defineTest();
}
