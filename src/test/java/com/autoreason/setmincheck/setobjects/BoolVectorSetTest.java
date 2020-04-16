package com.autoreason.setmincheck.setobjects;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

public class BoolVectorSetTest extends SetRepresentTest<BoolVectorSet> {

	@Override
	protected BoolVectorSet initializeOperator() {
		return new BoolVectorSet();
	}

	@Override
	public void testConvertSet() {
		Set<Integer> set = Set.of(1, 3, 5);
		BoolVectorSet bvs = new BoolVectorSet(set);
		assertEquals(set, bvs.set);
		assertArrayEquals(new boolean[] { true,true,true }, bvs.setRepresentation);		
	}

}
