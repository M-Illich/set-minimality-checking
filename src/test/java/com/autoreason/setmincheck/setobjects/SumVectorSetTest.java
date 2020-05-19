package com.autoreason.setmincheck.setobjects;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class SumVectorSetTest extends SetRepresentTest<long[]> {

	@Override
	protected long[] defineConvert() {
		return new long[] { 5050, 15050, 11275 };
	}

	@Override
	protected SetRepresent<long[]> defineSetRepresent() {
		return new SumVectorSet(new long[1]);
	}

	@Override
	protected Set<?> defineTest() {
		HashSet<Integer> set = new HashSet<>();
		for (int i = 1; i <= 250; i++) {
			set.add(i);
		}
		// { 5050, 15050, 11275 }
		return set;
	}

	@Override
	protected boolean equal(long[] a, long[] b) {
		return Arrays.equals(a, b);
	}

	@Test
	public void testCompareTo() {
		SumVectorSet a = new SumVectorSet(new long[] { 201 });
		SumVectorSet b = new SumVectorSet(new long[] { 201 });
		assertTrue(a.compareTo(b) == 0);

		HashSet<Integer> set = new HashSet<>();
		for (int i = 1; i <= 150; i++) {
			set.add(i);
		}
		a = new SumVectorSet(set);
		b = new SumVectorSet(set);
		assertTrue(a.compareTo(b) == 0);

		a = new SumVectorSet(Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
		b = new SumVectorSet(Set.of(20, 30));
		assertTrue(a.compareTo(b) == -1);
		assertTrue(b.compareTo(a) == 1);
	}

}
