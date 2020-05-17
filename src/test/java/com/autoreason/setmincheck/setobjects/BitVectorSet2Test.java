package com.autoreason.setmincheck.setobjects;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

public class BitVectorSet2Test extends SetRepresentTest<long[]> {

	@Override
	protected long[] defineConvert() {
		return new long[] {42};
	}

	@Override
	protected SetRepresent<long[]> defineSetRepresent() {
		return new BitVectorSet2(new long[1]);
	}

	@Override
	protected Set<?> defineTest() {
		return Set.of(1,3,5);
	}

	@Override
	protected boolean equal(long[] a, long[] b) {
		return Arrays.equals(a, b);
	}
	
	@Test
	public void testCompareTo() {
		BitVectorSet2 a = new BitVectorSet2(new long[] {201});
		BitVectorSet2 b = new BitVectorSet2(new long[] {201});
		assertTrue(a.compareTo(b) == 0);
		
		a = new BitVectorSet2(Set.of(1,3,8));
		b = new BitVectorSet2(Set.of(1,3,8));
		assertTrue(a.compareTo(b) == 0);
		
		a = new BitVectorSet2(Set.of(42));
		b = new BitVectorSet2(Set.of(1,3,8));
		assertTrue(a.compareTo(b) == -1);
		assertTrue(b.compareTo(a) == 1);
	}

}
