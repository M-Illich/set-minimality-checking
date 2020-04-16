package com.autoreason.setmincheck;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import com.autoreason.setmincheck.MatchIterator;
import com.autoreason.setmincheck.setobjects.BoolVectorSet;

public abstract class MatchIteratorTest<C extends Comparable<C>, T, M extends MatchIterator<C, T>>
		extends MatchProviderTest<C, T, M> {

	@Test
	public void runMatchIteratorTests() {
		// get random seed
		long seed = getSeed();
		// conduct test
		try {
			testMatchesOf(seed);
		} catch (Throwable e) {
			throw new RuntimeException("seed: " + seed, e);
		}
	}

	private void testMatchesOf(long seed) {
		// define test objects
		NavigableSet<C> col = defineCollection(seed);
		T test = defineTest(seed);

		// compute Iterable with tested method
		Iterable<C> iterMatches = matchOperator.matchesOf(col, test);

		// compute expected Iterable of matches by considering every element of the
		// collection
		Iterable<C> expected = new Iterable<C>() {
			// transform Collection to NavigableSet
			NavigableSet<C> naviCol = new TreeSet<C>(col);

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

					private C getFirstCandidate() {
						// get first element of collection
						C cur = naviCol.first();
						// return first element if it is a match of test
						if (matchOperator.matches(cur, test)) {
							return cur;
						}
						// else, look for next match in collection
						else {
							return getNextCandidate(cur);
						}
					}

					private C getNextCandidate(C cur) {
						// look for next match in collection
						while (cur != null) {
							// get next element from collection
							cur = naviCol.higher(cur);
							// check if candidate is a match of test
							if (matchOperator.matches(cur, test)) {
								break;
							}
						}
						// return next element
						return cur;
					}

				};
			}

		};

		// compare found matches
		Iterator<C> expIter = expected.iterator();
		Iterator<C> matchIter = iterMatches.iterator();
		
//		// TEST TODO
//		System.out.println("test: " + Arrays.toString(new BoolVectorSet((Set)test).setRepresentation));
//		System.out.println();
//		
//		System.out.println("expected: ");
//		while (expIter.hasNext()) {
//			System.out.println(Arrays.toString(((BoolVectorSet)expIter.next()).setRepresentation));
//		}
//		System.out.println();
//		System.out.println("assumed: ");
//		while (matchIter.hasNext()) {
//			System.out.println(Arrays.toString(((BoolVectorSet)matchIter.next()).setRepresentation));
//		}
//		
		while (expIter.hasNext()) {
			assertEquals(expIter.next(), matchIter.next());
		}

	}

	/**
	 * Define a {@link NavigableSet} for testing purposes
	 * 
	 * @param seed A {@link long} value used as seed to generate a random object
	 * @return A {@link NavigableSet} containing elements of type {@code C}
	 */
	protected abstract NavigableSet<C> defineCollection(long seed);

}
