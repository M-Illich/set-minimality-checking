package com.autoreason.setmincheck.setobjects;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

public class BitVectorSetTest extends SetRepresentTest<BitVectorSet> {

	@Override
	protected BitVectorSet initializeOperator() {
		return new BitVectorSet();
	}

	@Override
	public void testConvertSet() {
		Set<Integer> set = Set.of(1, 3, 5);
		BitVectorSet bvs = new BitVectorSet(set);
		assertEquals(set, bvs.set);
		assertArrayEquals(new long[] { 42 }, bvs.setRepresentation);

		set = new HashSet<Integer>();
		for (int i = 0; i < 127; i++) {
			set.add(i);
		}
		bvs = new BitVectorSet(set);
		assertArrayEquals(new long[] { -1, Long.MAX_VALUE }, bvs.setRepresentation);

	}

}
