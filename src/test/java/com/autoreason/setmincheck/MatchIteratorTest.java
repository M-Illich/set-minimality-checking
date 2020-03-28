package com.autoreason.setmincheck;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

import org.junit.Test;

import com.autoreason.setmincheck.MatchIterator;

public class MatchIteratorTest<C extends Comparable<C>, T, M extends MatchIterator<C, T>>
		extends MatchProviderTest<C, T> {

	M matchIterator;
	Collection<C> col;
	T test;

	@Test
	public void testMatchesOf() {
		// compute expected Iterable of matches by considering every element of the collection
		Iterable<C> expected = new Iterable<C>() {
			// transform Collection to NavigableSet
			NavigableSet<C> naviCol = new TreeSet<C>(col);

			@Override
			public Iterator<C> iterator() {
				return new Iterator<C>() {
					// current position in set
					int curPos = 0;
					// current element
					C curElement = naviCol.first();

					@Override
					public boolean hasNext() {
						return curPos < naviCol.size();
					}

					@Override
					public C next() {
						C cur = curElement;
						// update current element by next one
						curElement = naviCol.ceiling(cur);
						// go to next element in collection if current element does not match the test
						// object
						if (!matchIterator.matches(cur, test)) {
							return next();
						}
						// return (old) current element
						return cur;
					}

				};
			}

		};

		// compute Iterable with tested method
		Iterable<C> iterMatches = matchIterator.matchesOf(col, test);

		// TODO maybe every element of Iterable must be compared ???
		assertEquals(iterMatches, expected);

	}

}
