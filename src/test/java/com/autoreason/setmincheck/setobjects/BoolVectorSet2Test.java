package com.autoreason.setmincheck.setobjects;

import static org.junit.Assert.assertEquals;

import java.util.BitSet;
import java.util.Set;

public class BoolVectorSet2Test extends SetRepresentTest<BoolVectorSet2> {

	@Override
	protected BoolVectorSet2 initializeOperator() {
		return new BoolVectorSet2();
	}

	@Override
	public void testConvertSet() {
		Set<Integer> set = Set.of(1, 3, 5);
		BoolVectorSet2 bvs = new BoolVectorSet2(set);
		BitSet bs = new BitSet(3);
		bs.set(0, 3);
		assertEquals(set, bvs.set);
		assertEquals(bs, bvs.setRepresentation);		
	}

}
