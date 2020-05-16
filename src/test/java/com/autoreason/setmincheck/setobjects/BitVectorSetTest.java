package com.autoreason.setmincheck.setobjects;

import java.util.Arrays;
import java.util.Set;

public class BitVectorSetTest extends SetRepresentTest<long[]> {

	@Override
	protected long[] defineConvert() {
		return new long[] {42};
	}

	@Override
	protected SetRepresent<long[]> defineSetRepresent() {
		return new BitVectorSet(new long[1]);
	}

	@Override
	protected Set<?> defineTest() {
		return Set.of(1,3,5);
	}

	@Override
	protected boolean equal(long[] a, long[] b) {
		return Arrays.equals(a, b);
	}

}
