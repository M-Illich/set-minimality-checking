package com.autoreason.setmincheck.setobjects;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

public class BoolVectorSetTest extends SetRepresentTest<boolean[]> {

	@Override
	protected boolean equal(boolean[] a, boolean[] b) {
		return Arrays.equals(a, b);
	}

	@Override
	protected boolean[] defineConvert() {
		boolean[] bv = new boolean[64];
		bv[1] = true;
		bv[3] = true;
		bv[5] = true;
		return bv;
	}

	@Override
	protected SetRepresent<boolean[]> defineSetRepresent() {
		return new BoolVectorSet(new boolean[1]);
	}

	@Override
	protected Set<?> defineTest() {
		return Set.of(1, 3, 5);
	}

	@Test
	public void testCompareTo() {
		BoolVectorSet a = new BoolVectorSet(new boolean[] { true, false, true, true });
		BoolVectorSet b = new BoolVectorSet(new boolean[] { true, false, true, true });
		assertTrue(a.compareTo(b) == 0);

		a = new BoolVectorSet(Set.of(74, 93, 26));
		b = new BoolVectorSet(Set.of(74, 93, 26));
		assertTrue(a.compareTo(b) == 0);

		a = new BoolVectorSet(Set.of(13, 0, 5, 11));
		b = new BoolVectorSet(Set.of(3, 7, 15));
		assertTrue(a.compareTo(b) == -1);
		assertTrue(b.compareTo(a) == 1);
	}

}
