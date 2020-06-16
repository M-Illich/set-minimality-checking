package com.autoreason.setmincheck.setobjects;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

public class BitVectorSetTest extends SetConverterTest<long[]> {

	@Override
	protected long[] defineConvert() {
		int len = BitVectorSetConverter.setRepresentLength / 64 + ((BitVectorSetConverter.setRepresentLength % 64 == 0) ? 0 : 1);
		long[] con = new long[len];
		con[0] = 42;
		return con;
	}

	@Override
	protected SetConverter<long[]> defineSetConverter() {
		return new BitVectorSetConverter();
	}

	@Override
	protected Set<?> defineTest() {
		return Set.of(1, 3, 5);
	}

	@Override
	protected boolean equal(long[] a, long[] b) {
		return Arrays.equals(a, b);
	}

	@Test
	public void testCompareTo() {
		BitVectorSet a = new BitVectorSet(new long[] { 201 });
		BitVectorSet b = new BitVectorSet(new long[] { 201 });
		assertTrue(a.compareTo(b) == 0);

		a = new BitVectorSet(Set.of(1, 3, 8));
		b = new BitVectorSet(Set.of(1, 3, 8));
		assertTrue(a.compareTo(b) == 0);

		a = new BitVectorSet(Set.of(42));
		b = new BitVectorSet(Set.of(1, 3, 8));
		assertTrue(a.compareTo(b) == 1);
		assertTrue(b.compareTo(a) == -1);
	}

}
