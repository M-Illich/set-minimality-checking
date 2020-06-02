package com.autoreason.setmincheck.setobjects;

import static org.junit.Assert.assertTrue;

import java.util.BitSet;
import java.util.Set;

import org.junit.Test;

public class BoolVectorSet2Test extends SetConverterTest<BitSet> {

	@Override
	protected boolean equal(BitSet a, BitSet b) {
		return a.equals(b);
	}

	@Override
	protected BitSet defineConvert() {
		BitSet bs = new BitSet(3);
		bs.set(1);
		bs.set(3);
		bs.set(5);
		return bs;
	}

	@Override
	protected SetConverter<BitSet> defineSetConverter() {
		return new BoolVectorSet2Converter();
	}

	@Override
	protected Set<?> defineTest() {
		return Set.of(1, 3, 5);
	}

	@Test
	public void testCompareTo() {
		BitSet bs = new BitSet(3);
		bs.set(1);
		bs.set(3);
		bs.set(5);
		BoolVectorSet2 a = new BoolVectorSet2(bs);
		BoolVectorSet2 b = new BoolVectorSet2(bs);
		assertTrue(a.compareTo(b) == 0);

		a = new BoolVectorSet2(Set.of(74, 93, 26));
		b = new BoolVectorSet2(Set.of(74, 93, 26));
		assertTrue(a.compareTo(b) == 0);

		a = new BoolVectorSet2(Set.of(13, 0, 5));
		b = new BoolVectorSet2(Set.of(3, 7, 11, 15));
		assertTrue(a.compareTo(b) == -1);
		assertTrue(b.compareTo(a) == 1);
	}

}
